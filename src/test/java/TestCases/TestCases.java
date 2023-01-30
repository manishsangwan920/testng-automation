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
	
	@Test
	public static void Test_Login_Page_Content() {
		Stepdefination.LoginPageContents();
	}
	
	@Test
	public static void Test_Login_With_Valid_Data_For_Existing_User() throws Exception {
		Stepdefination.LoginApplication("parikshit.yugasa@gmail.com" ,"Adi@123","Dashboard");
	}
	
	@Test
	public static void Test_Login_With_Invalid_Data_For_Existing_User() throws Exception {
		Stepdefination.VerifyInvalidLogin("arikshit.yugasa@gmail.com" ,"Adi@123");
		driver.close();
	}
	
	@Test
	public static void Test_Login_without_Accepting_Terms_And_Conditions() {
		Stepdefination.LoginWithoutTermsCheckbox("parikshit.yugasa@gmail.com" ,"Adi@123");	
	}
	
	@Test
	public static void Test_Login_without_Entering_Any_Credentials() {
		Stepdefination.LoginWithoutCredentials();
	}
	
	@Test
	public static void Test_Terms_And_Condition_Is_Clickable_And_Return_Expected_Content() {
		Stepdefination.TermsAndCondition();
	}
	
	@Test
	public static void  Forgot_Password_Link_Is_Clickable_And_Functional() {
		Stepdefination.ForgetPassword();
	}
	
	@Test
	public static void User_Is_Able_To_See_And_Accept_cookies() {
		Stepdefination.AcceptCookies();
	}
	
	@Test
	public static void User_Is_Able_To_See_Dashboard() {
		Stepdefination.LoginApplication("parikshit.yugasa@gmail.com" ,"Adi@123","Dashboard");
		Stepdefination.DashboardIsPresent();
	}  

	@Test
	public static void Welcome_Distributers_Name() {
		Stepdefination.LoginApplication("parikshit.yugasa@gmail.com" ,"Adi@123","Dashboard");
		Stepdefination.WelcomeDistibutersName();
	}
	
	@Test
	public static void Are_Profile_ResetPassword_Logout_Present() {
		Stepdefination.LoginApplication("parikshit.yugasa@gmail.com" ,"Adi@123","Dashboard");
		Stepdefination.profileAndRestPassword();
	}
	
	@Test
	public static void Upload_Profile_Picture_And_Save_It() {
		Stepdefination.LoginApplication("parikshit.yugasa@gmail.com" ,"Adi@123","Dashboard");
		Stepdefination.UploadProfilePicture();
	}
	
	@Test
	public static void Edit_Profile_Picture_After_Uploading_And_Saving_Different_Picture_First() {
		Stepdefination.LoginApplication("parikshit.yugasa@gmail.com" ,"Adi@123","Dashboard");
		Stepdefination.EditProfilePicture();
	}
	
		
}
