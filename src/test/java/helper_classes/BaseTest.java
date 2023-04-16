package helper_classes;

import static settings.ObjectRepo.browser;
import static settings.ObjectRepo.test;

import java.lang.reflect.Method;
import java.sql.Driver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.mail.EmailException;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import static settings.ObjectRepo.driver;
import com.relevantcodes.extentreports.LogStatus;

import browserconfig.BrowserType;
import browserconfig.InitializeWebDriver;
import configreader.ExcelReader;
import configreader.PropertyFileReader;
import reporting.ExtentReportHelper;
import settings.ObjectRepo;

public class BaseTest {

	@BeforeSuite
	public void setUp() throws Exception {
		System.out.println("Setting Up Test Suite");
		ExtentReportHelper.setUpReport();
		ObjectRepo.reader = new PropertyFileReader();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		LocalDateTime now = LocalDateTime.now(); 
		LocalDateTime Date = now.plusDays(4);
		ExcelReader.updateTestDataExcel("duedate",dtf.format(Date));
		
		
		
	}
	
	@BeforeMethod
	public void beforeTest(Method testMethod) throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now1 = LocalDateTime.now();
		ExcelReader.updateTestDataExcel("taskname","Test Task"+dtf.format(now1));
		System.out.println("Executing Test");
		ExtentReportHelper.startTest(testMethod.getName());
		System.out.println("Launching "+browser+" browser");
		InitializeWebDriver wd = new InitializeWebDriver();
		wd.setUpDriver(BrowserType.Chrome);
		test.log(LogStatus.INFO, "Chrome Launch successfully");
		ObjectRepo.driver.get(ObjectRepo.reader.getWebsite());
		test.log(LogStatus.INFO, "Exxon Application Launch successfully");
	}
	
	@AfterMethod
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
