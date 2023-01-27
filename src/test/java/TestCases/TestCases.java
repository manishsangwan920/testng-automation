package TestCases;

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
import reporting.ExtentReportHelper;
import settings.ObjectRepo;


/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/


public class TestCases extends BaseTest {
	
	@Test
	public static void test() {
		
		System.out.println("running test");
		try {
			
				    
			
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Unable to launch Browser");
		}
		
	}
	
	
	
}  



