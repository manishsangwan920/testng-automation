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


/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/


public class TestCases extends BaseTest {
	
	@Test
	public static void Test_Login_Page_Content() {
		try {
			LoginPage loginPage = new LoginPage(driver);
			
			if(loginPage.emailTextBox.isDisplayed()){
				test.log(LogStatus.PASS, "Email text box is displayed");	
			}else {
				test.log(LogStatus.FAIL, "Email text box is not displayed");			
			}
			
				
			
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
		
	}
	
	
	
}  



