package TestCases;
import static settings.ObjectRepo.driver;
import org.apache.commons.mail.EmailException;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static settings.ObjectRepo.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.LogStatus;
import static settings.ObjectRepo.browser;
import browserconfig.BrowserType;
import browserconfig.InitializeWebDriver;
import configreader.ExcelReader;
import configreader.PropertyFileReader;
import exception.NoSuitableDriverFoundException;
import helper_classes.BaseTest;
import pages.LoginPage;
import reporting.ExtentReportHelper;
import settings.ObjectRepo;
import stepdefination.Stepdefination;


/*******************************************************************************************************
* @author  Vikas Sangwan
*
********************************************************************************************************/


public class TestCases extends BaseTest {
	
	@Test(enabled=true)
	public static void Test_Login_Page_Content() {
		Stepdefination.LoginPageContents();
	}
	
	@Test(enabled=true)
	public static void Test_Login_With_Valid_Data_For_Existing_User() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
	}
	
	@Test(enabled=true)
	public static void Test_Login_With_Invalid_Data_For_Existing_User()  {
		Stepdefination.VerifyInvalidLogin(ExcelReader.ReadTestData("invalid_email"),ExcelReader.ReadTestData("invalid_password"));
		
	}
	
	@Test(enabled=true)
	public static void Test_Login_without_Accepting_Terms_And_Conditions() {
		Stepdefination.LoginWithoutTermsCheckbox(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"));	
	}
	
	@Test(enabled=true)
	public static void Test_Login_without_Entering_Any_Credentials() {
		Stepdefination.LoginWithoutCredentials();
	}
	
	@Test(enabled=true)
	public static void Test_Terms_And_Condition_Is_Clickable_And_Return_Expected_Content() {
		Stepdefination.TermsAndCondition();
	}
	
	@Test(enabled=true)
	public static void  Forgot_Password_Link_Is_Clickable_And_Functional() {
		Stepdefination.ForgetPassword();
	}
	
	@Test(enabled=true)
	public static void User_Is_Able_To_See_And_Accept_cookies() {
		Stepdefination.AcceptCookies();
	}
	
	@Test(enabled=true)
	public static void User_Is_Able_To_See_Dashboard() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.DashboardIsPresent();
	} 
	
	@Test(enabled=true)
	public static void Company_Name_Is_Present_On_Header_On_HomePage() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.IsCompanyNamePresent("Yugasa Software Labs");
	}

	@Test(enabled=true)
	public static void Welcome_Distributers_Name_IS_Displayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.WelcomeDistibutersName();
	}
	
	@Test(enabled=true)
	public static void Are_Profile_ResetPassword_And_Logout_Button_Are_Present() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.profileAndRestPassword();
	}
	
	@Test(enabled=true)
	public static void Upload_Profile_Picture_And_Save_It() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.UploadProfilePicture();
	}
	
	@Test(enabled=true)
	public static void Edit_Profile_Picture_After_Uploading_And_Saving_Different_Picture_First() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.EditProfilePicture();
	}
	
	@Test(enabled=true)
	public static void Test_Email_In_Profie_Is_Not_Editable() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.editProfileEmail();
	}
	
	@Test (enabled=true)
	public static void Test_User_Profile_Data_Is_Editable_And_When_Updated_Changes_Are_Visible_Dashboard() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.editUserProfile(ExcelReader.ReadTestData("edit_profile_user_name"),ExcelReader.ReadTestData("edit_profile_user_contact_num"),ExcelReader.ReadTestData("edit_profile_company_name"));
	}
	
	@Test(enabled=true)
	public static void Error_message_Should_Be_Displayed_If_User_Enter_Wrong_Existing_Password_While_Reseting_Password() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.WrongExistingPassword(ExcelReader.ReadTestData("invalid_password"),ExcelReader.ReadTestData("reseted_password") );
	}
	
	@Test(enabled=true)
	public static void Test_If_Error_Message_Is_Displayed_When_New_Password_And_Confirm_Password_Are_Not_Matching() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.PasswordNotMatching(ExcelReader.ReadTestData("password"),ExcelReader.ReadTestData("invalid_password"),ExcelReader.ReadTestData("reseted_password"));
	}
	
	@Test(enabled=true)
	public static void Test_If_The_criteria_is_satisfied_If_User_Enter_a_password_which_met_the_requirement() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.PasswordMeetingCriteria();
	}
	
	
	@Test(enabled=true)
	public static void Test_Is_ResetPassword_PopUp_Closing_After_Clicking_Close_Button() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.closeResetpasswordpopUP();
	}
	
	@Test(enabled=true)
	public static void Test_If_User_Is_Able_To_Logout_Of_Application_On_clicking_Logout_Button_Under_Profile_Icon() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.LogoutProfileIcon();
	}
	
	@Test(enabled=true)
	public static void Test_If_User_Is_Able_To_Logout_Of_Application_On_clicking_Logout_Button_On_SideBar() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.LogoutSideBar();
	}
		
	@Test(enabled=true)
	public static void Test_Task_Status_Is_Displayed_On_Dashbaoard() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.TaskStatus();
	}
	
	@Test(enabled=true)
	public static void Test_Distributor_Info_Is_Displayed_On_Dashboard() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.DistributersInfo();
	}
	
	@Test(enabled=true)
	public static void 	Test_Account_Task_Record_Are_Displayed_On_Dashboard() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
	    Stepdefination.AccountStatusRecord();
	}
	
	@Test(enabled=true)
	public static void 	Test_Solcare_observation_Records_Are_Displayed_On_Dashboard() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.SolcareObservationRecords();
	}
	
	@Test(enabled=true)
	public static void 	Test_Solcare_observation_Records_Are_Displayed_And_User_Is_Able_To_Export_CSV() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");				
		Stepdefination.downloadcsv();
	}
	
	
	@Test(enabled=true)
	public static void 	Test_Solcare_observation_Records_Are_Displayed_And_User_Is_Able_To_Export_Chart(){
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.downloadChart("all");
	}
	
	@Test(enabled=true)
	public static void Test_Solcare_observation_Records_Are_Displayed_And_User_Is_Able_To_Export_Chart_For_A_Specifiic_Machine(){
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.downloadChart("one");
	}
	
	@Test(enabled=true)
	public static void 	Service_Engineer_records_Are_Displayed_On_Dashboard() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.serviceEngineerRecords();
	}
	
	@Test (enabled=true)
	public static void Test_Create_Task_Popup_Is_Dispalyed_And_It_Has_All_The_Fields() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.createTaskPopUp();
	}
	@Test (enabled=true)
	public static void Test_User_is_Able_To_Create_New_Task() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTask(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
	}
	
	@Test(enabled=true)
	public static void TC35A_Verify_that_if_No_Task_Name_Is_Entered_an_error_message_is_dispayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithMissingFields(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),"TaskName");
	}
	
	@Test(enabled=true)
	public static void TC35B_Verify_that_if_No_service_engineer_is_Selected_an_error_message_is_dispayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithMissingFields(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),"ServiceEngineer");
	}
	
	@Test(enabled=true)
	public static void TC35C_Verify_that_if_No_Account_is_Selected_an_error_message_is_dispayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithMissingFields(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),"SelectedAccount");
	}
	
	@Test(enabled=true)
	public static void TC35D_Verify_that_if_No_Due_Date_Is_Entered_an_error_message_is_dispayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithMissingFields(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),"DueDate");
	}
		
	@Test(enabled=true)
	public static void TC35E_Verify_that_if_Machines_Are_Not_Selected_an_error_message_is_dispayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithMissingFields(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),"Machines");
	}
	
	@Test(enabled=true)
	public static void TC35F_Verify_that_if_TaskType_Is_Not_Selected_an_error_message_is_dispayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithMissingFields(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),"TaskType");
	}	
	
	@Test(enabled=true)
	public static void TC36_Verify_that_if_there_are_no_service_engineer_Accounts_and_machines_are_not_added_an_error_message_is_dispayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithThreeMissingFields(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
	}
	
	@Test(enabled=true)
	public static void Verify_That_User_Is_Able_To_Select_Service_Engineer_And_Selected_Service_Engineer_Is_visible() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.VerifySelectedFeildIsShowing("ServiceEngineer");
	}
	
	@Test(enabled=true)
	public static void Verify_That_User_Is_Able_To_Select_Accounts_And_Selected_Account_Is_visible() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.VerifySelectedFeildIsShowing("Account");
	}
	
	@Test(enabled=true)
	public static void Verify_That_User_Is_Able_To_Select_machines_And_Selected_Machines_Are_visible() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.VerifyMachinesFeildIsShowing();
	}
	
	@Test(enabled=true)
	public static void Verify_That_Watchers_Field_Is_Disabled_Untill_Service_Engineer_Is_Not_Selected() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.WatcherDisabled(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
	}
	
	@Test(enabled=true)
	public static void Verify_That_User_Is_Not_Able_Select_The_Same_Service_Engineer_And_Watcher() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.SelectSameWatcherAndServiceEngineer();
	}
	
	
	@Test(enabled=true)
	public static void Verify_That_machines_Field_Is_Disabled_Untill_Account_Is_Not_Selected() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.MachinesDisabled(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
	}
	
	@Test(enabled=true)
	public static void Verify_That_User_Is_Able_To_Select_A_Single_Machine() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.VerifymultipleMachinesCanBeSelected(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),"one");
	}
	
	@Test(enabled=true)//impr
	public static void Verify_That_User_Is_Able_To_Select_More_Than_One_Machine() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.VerifymultipleMachinesCanBeSelected(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),"Multiple");
	}

	@Test(enabled=true)
	public static void Verify_That_User_Is_Able_To_Select_All_the_Machines(){
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.VerifymultipleMachinesCanBeSelected(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),"All");
	}
	
	@Test(enabled=true)
	public static void Verify_That_User_Is_Able_To_See_And_Select_All_Four_TaskTypes(){
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.AllFourTaskTypeAreDisplayed();
	}
	
	@Test (enabled=true)
	public static void Verify_Test_User_is_Able_To_Create_New_Task_With_Solcare_Service_As_TaskType() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithDifferentTaskTypes(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),1);
	}
	
	@Test (enabled=true)
	public static void Verify_Test_User_is_Able_To_Create_New_Task_With_Oil_Skimming_As_TaskType() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithDifferentTaskTypes(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),2);
	}
	
	@Test (enabled=true)
	public static void Verify_Test_User_is_Able_To_Create_New_Task_With_Sump_Cleaning_As_TaskType() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithDifferentTaskTypes(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),3);
	}
	
	@Test (enabled=true)
	public static void Verify_Test_User_is_Able_To_Create_New_Task_With_Bacteria_Fungi_Check_As_TaskType() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CreateTaskWithDifferentTaskTypes(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),4);
	}
	
	@Test (enabled=true)
	public static void Test_If_There_are_service_Engineer_availble_the_vendor_Should_be_Able_To_see_all_of_those_while_Creating_Task() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.preAvailbleServiceEingeer();
	}
	
	@Test (enabled=true)
	public static void Test_If_There_are_Accounts_availble_the_vendor_Should_be_Able_To_see_all_of_those_while_Creating_Task() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.preAvailbleAccount();
	}
	
	@Test (enabled=true)
	public static void Verify_If_None_Button_Is_PreSelected_By_Default() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.NoneButtonIsSelectedByDefault();
	}
	
	@Test (enabled=true)
	public static void Verify_On_selecting_Schedule_Daily_Button_Select_End_Date_Field_Should_Be_Displayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.EndDateFieldDisplayed(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
	}
	
	@Test (enabled=true)
	public static void Verify_On_selecting_Schedule_weekly_Button_Select_End_Date_Field_And_Select_Week_and_WeekDays_Should_Be_Displayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.selectScheduleWeekly(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));;
	}
	
	@Test (enabled=true)
	public static void Verify_On_selecting_Schedule_monthly_Button_Select_End_Date_Field_And_Select_month_DropDown_Should_Be_Displayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.selectScheduleMonthly(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
	}
	
	@Test (enabled=true)
	public static void Verify_On_selecting_Schedule_monthly_Button_And_Selecting_Every_Month_All_Dates_Should_Be_Present() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.selectScheduleMonthlSelectmonth(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),true);
	}
	
	@Test (enabled=true)
	public static void Verify_On_selecting_Schedule_monthly_Button_And_Selecting_Alternate_Month_All_Dates_Should_Be_Present() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.selectScheduleMonthlSelectmonth(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"),false);
	}
	
	@Test (enabled=true)
	public static void Verify_On_clicking_Download_Excel_An_Instruction_Pop_Up_Is_Displayed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.DownloadExcelInstructionPopUp();
	}
	
	@Test (enabled=true)
	public static void Verify_On_clicking_Close_Icon_Download_Excel_Instruction_Pop_Up_Is_Closed() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.CloseDownloadExcelInstructionPopUp();;
	}
	
	@Test (enabled=true)
	public static void Verify_On_clicking_Download_Sample_Excel_on_Instruction_Pop_Up_An_Excel_File_Is_Downloaded() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.DownloadSampleExcel();
	}
	
	@Test (enabled=true)
	public static void Verify_On_clicking_upload_Excel_A_Pop_Up_Is_Displayed_which_Has_Options_To_Upload_File() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadExcelDisplayed();
	}
	
	@Test (enabled=true)
	public static void Test_If_The_Upload_Excel_Button_Is_Disabled_If_No_File_Is_Uploded() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadExcelIsDisabled();
	}
		
	@Test (enabled=true)
	public static void TEST_If_User_Is_Able_To_Reset_Password_And_login_With_New_Password() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.ResetPassword(ExcelReader.ReadTestData("email"), ExcelReader.ReadTestData("reseted_password"), "Dashboard");;
	}
	
	@Test (enabled=true)
	public static void Verify_That_After_Selecting_A_machine_The_Selected_Name_Displayed_Is_Correct() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.VerifyMachineName();
	}
	
	@Test (enabled=true)
	public static void Test_If_An_Error_Message_Is_Displayed_When_User_Upload_An_Empty_Excel() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadEmptyExcel();
	}
	
	@Test (enabled=true)
	public static void Test_If_Error_Message_Is_Dispalyed_In_Case_Of_A_Empty_Account_Feild_Uploded_Excel_File() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadMissingFeildExcel("account");
	}
	
	@Test (enabled=true)
	public static void Test_If_Error_Message_Is_Dispalyed_In_Case_Of_A_Empty_duedate_Feild_Uploded_Excel_File() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadMissingFeildExcel("duedate");
	}
	@Test (enabled=true)
	public static void Test_If_Error_Message_Is_Dispalyed_In_Case_Of_A_Empty_Taskname_Feild_Uploded_Excel_File() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadMissingFeildExcel("TaskName");
	}
	@Test (enabled=true)
	public static void Test_If_Error_Message_Is_Dispalyed_In_Case_Of_A_Empty_Machine_Code_Feild_Uploded_Excel_File() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadMissingFeildExcel("machinecode");
	}
	@Test (enabled=true)
	public static void Test_If_Error_Message_Is_Dispalyed_In_Case_Of_A_Empty_Service_engineer_number_Feild_Uploded_Excel_File() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadMissingFeildExcel("serviceengineerphone");
	}
	@Test (enabled=true)/////////////////////////
	public static void Test_If_Error_Message_Is_Dispalyed_In_Case_Of_A_Past_Due_Date() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		//Stepdefination.uploadExcel("account");
	}
	
	@Test (enabled=true)
	public static void Test_If_User_Is_Able_To_Create_A_Task_By_Uploading_Excel_In_The_Given_Format() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadExcel(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("duedate"));
	}
	
	@Test (enabled=true)
	public static void Test_If_An_Error_Message_Is_Displayed_If_Uploaded_Excel_Contains_Account_Name_Which_is_Not_On_Present_Website() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadExcelWithDifferentValue("Account_name");
	}
	
	@Test (enabled=true)
	public static void Test_If_An_Error_Message_Is_Displayed_If_Uploaded_Excel_Contains_Watcher_Which_is_Not_On_Present_Website() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadExcelWithDifferentValue("Watcher");
	}
	
	@Test (enabled=true)
	public static void Test_If_An_Error_Message_Is_Displayed_If_Uploaded_Excel_Contains_Service_engineer_number_Which_is_Not_On_Present_Website() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.uploadExcelWithDifferentValue("Service_engineer_number");
	}
}
