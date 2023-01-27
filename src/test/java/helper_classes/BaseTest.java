package helper_classes;

import org.apache.commons.mail.EmailException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

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
	public void beforeTest() {
		System.out.println("Executing Test");
		ExtentReportHelper.startTest("Test name");
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
