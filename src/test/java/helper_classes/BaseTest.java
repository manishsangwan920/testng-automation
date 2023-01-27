package helper_classes;

import static settings.ObjectRepo.browser;
import static settings.ObjectRepo.test;

import java.sql.Driver;

import org.apache.commons.mail.EmailException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

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
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("Executing Test");
		ExtentReportHelper.startTest("Test name");
		System.out.println("Launching "+browser+" browser");
		InitializeWebDriver wd = new InitializeWebDriver();
		wd.setUpDriver(BrowserType.Chrome);
		test.log(LogStatus.INFO, "Chrome Launch successfully");
		ObjectRepo.driver.get(ObjectRepo.reader.getWebsite());
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("finished Test");
		ExtentReportHelper.endTest();
	}
	
	@AfterSuite
	public static void endReport() throws EmailException {
		ExtentReportHelper.endReport();
		
		ObjectRepo.reader=null;
		System.out.println("Test Suite Execution Complete");

	}
	
	

}
