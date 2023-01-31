package TestCases;
import static settings.ObjectRepo.driver;
import org.apache.commons.mail.EmailException;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static settings.ObjectRepo.test;
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
* @since   2020-04-15
********************************************************************************************************/


public class TestCases extends BaseTest {
	
	@Test(enabled=true)
	public static void Test_Login_Page_Content() {
		Stepdefination.LoginPageContents();
	}
	
	@Test(enabled=true)
	public static void Test_Login_With_Valid_Data_For_Existing_User() throws Exception {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
	}
	
	@Test(enabled=true)
	public static void Test_Login_With_Invalid_Data_For_Existing_User() throws Exception {
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
	public static void Welcome_Distributers_Name() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.WelcomeDistibutersName();
	}
	
	@Test(enabled=true)
	public static void Are_Profile_ResetPassword_Logout_Present() {
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
		Stepdefination.editUserProfile("TestROBOT","9500124390","ELITE AUTOMATIONS");
	}
	
	@Test(enabled=true)
	public static void Error_message_Should_Be_Displayed_If_User_Enter_Wrong_Existing_Password_While_Reseting_Password() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.WrongExistingPassword("Test@123", "Test@abc");
	}
	
	@Test(enabled=true)
	public static void Test_If_Error_Message_Is_Displayed_When_New_Password_And_Confirm_Password_Are_Not_Matching() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.PasswordNotMatching("Test@123", "Test@abc","Test@ab");
	}
	
	@Test(enabled=true)
	public static void Test_Is_ResetPassword_PopUp_Closing_After_Clicking_Close_Button() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.closeResetpasswordpopUP();
	}
	
	@Test(enabled=true)
	public static void Test_If_User_Is_Able_To_Logout_Of_Application_On_clicking_Logout_Button() {
		Stepdefination.LoginApplication(ExcelReader.ReadTestData("email"),ExcelReader.ReadTestData("password"),"Dashboard");
		Stepdefination.LogoutProfileIcon();
	}
	
}
