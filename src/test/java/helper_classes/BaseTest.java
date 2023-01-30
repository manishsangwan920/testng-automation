package helper_classes;

import static settings.ObjectRepo.browser;
import static settings.ObjectRepo.test;

import java.lang.reflect.Method;
import java.sql.Driver;

import org.apache.commons.mail.EmailException;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import static settings.ObjectRepo.driver;
import com.relevantcodes.extentreports.LogStatus;

import browserconfig.BrowserType;
import browserconfig.InitializeWebDriver;
import configreader.PropertyFileReader;
import reporting.ExtentReportHelper;
import settings.ObjectRepo;

public class BaseTest {

	@BeforeSuite
	public void setUp() {
		System.out.println("Setting Up Test Suite");
		ExtentReportHelper.setUpReport();
		ObjectRepo.reader = new PropertyFileReader();
	}
	
	@BeforeMethod
	public void beforeTest(Method testMethod) throws Exception {
		System.out.println("Executing Test");
		ExtentReportHelper.startTest(testMethod.getName());
		System.out.println("Launching "+browser+" browser");
		InitializeWebDriver wd = new InitializeWebDriver();
		wd.setUpDriver(BrowserType.Chrome);
		test.log(LogStatus.INFO, "Chrome Launch successfully");
		ObjectRepo.driver.get(ObjectRepo.reader.getWebsite());
		test.log(LogStatus.INFO, "Exxon Application Launch successfully");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("finished Test");
		ObjectRepo.driver.quit();
		ExtentReportHelper.endTest();
	}
	
	@AfterSuite
	public static void endReport() throws EmailException {
		ExtentReportHelper.endReport();
		
		ObjectRepo.reader=null;
		System.out.println("Test Suite Execution Complete");

	}
	
	

}
