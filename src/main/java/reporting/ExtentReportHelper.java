 package reporting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import configreader.ExcelReader;
import configreader.PropertyFileReader;
import settings.ObjectRepo;
import utility.ResourceHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/

public class ExtentReportHelper {
	
	public static void setUpReport() {
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));
		String date = dtf.format(now).toString().replace(" ", "-").replace(":", "_").replace("/", "_");
		PropertyFileReader rd = new PropertyFileReader();
		if(rd.getReportOverwrite().equalsIgnoreCase("yes")) {
			ObjectRepo.report = new ExtentReports(System.getProperty("user.dir")+"/reports/ExtentReport.html", true);	
		}else {
			ObjectRepo.report = new ExtentReports(System.getProperty("user.dir")+"/reports/ExtentReport"+date+".html", false);	
			
		}
		ObjectRepo.report
		.addSystemInfo("Environment", "Automation Testing")
		
        .addSystemInfo("Host Name", "SoftwareTestingMaterial")
        .addSystemInfo("User Name", System.getProperty("user.name"));
		//ObjectRepo.report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));


	}
	
	public static void startTest(String TestName) {
		ObjectRepo.test = ObjectRepo.report.startTest(TestName);
		
	}
	
	public static void endTest() {
		ObjectRepo.report.endTest(ObjectRepo.test);
		
	}
	
	public static void endReport() {
		ObjectRepo.report.flush();
		ObjectRepo.report.close();
		
	}
	
	public static String getScreenshot() throws Exception {		
	        String dateName = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) ObjectRepo.driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir")+"/reports/Screenshot"+dateName+".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
	}
	
	public static void logFailWithScreenshot(String Message){
		try {
			ObjectRepo.test.log(LogStatus.FAIL, Message);
			String screenshotPath = getScreenshot();
			ObjectRepo.test.log(LogStatus.INFO, ObjectRepo.test.addScreenCapture(screenshotPath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
