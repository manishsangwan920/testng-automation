
package elementhelper;

import static settings.ObjectRepo.driver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.util.Asserts;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import configreader.ExcelReader;

import reporting.ExtentReportHelper;
import settings.ObjectRepo;
import com.mongodb.diagnostics.logging.Logger;
import com.relevantcodes.extentreports.LogStatus;


public class GenericElements extends ObjectRepo {
	
	public static void ValidateElementIsDisplayed(WebElement el, String Name){
		try {
			if(el.isDisplayed()) 
				ObjectRepo.test.log(LogStatus.PASS, Name+" is displayed");
			else
				ObjectRepo.test.log(LogStatus.FAIL, Name+" is not displayed");
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found "+"'"+Name+"'");
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	}
	
	public static void ValidateElementIsNotDisplayed(WebElement el, String Name){
		try {
			if(el.isEnabled()) 
				ObjectRepo.test.log(LogStatus.PASS, Name+" is not displayed");
			else
				ObjectRepo.test.log(LogStatus.FAIL, Name+" is displayed");
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found "+Name);
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	}

	
	public static void ValidateTheNullValueOfTheElementsIsTheDisplay(WebElement el, String Name) {
		try {
			if(el.getAttribute("value").equals("")) 
				ObjectRepo.test.log(LogStatus.PASS, Name+" is empty displayed");
			else
				ObjectRepo.test.log(LogStatus.FAIL, Name+" is not empty displayed");
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Element not found "+Name);
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
		
	}
	
	public static void VerifyPageURL(String expectedURL, String Name) {
		 String baseurl = ObjectRepo.reader.getWebsite();
		 if(driver.getCurrentUrl().equals(baseurl+expectedURL)) {
			ObjectRepo.test.log(LogStatus.PASS,Name +" Is Displayed");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,Name+" Is Not Displayed");
		}
	}
	
	  
	  public static boolean isFileDownloaded(String downloadPath, String fileName) {
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();

		  for (int i = 0; i < dirContents.length; i++) {
		      if (dirContents[i].getName().equals(fileName)) {
		          // File has been found, it can now be deleted:
		          dirContents[i].delete();
		          return true;
		      }
		          }
		      return false;
	  }
	  
	  public static int NumberOfFilesInPresentInFolder(String folderPath) {
		  File dir = new File(folderPath);
		  File[] dirContents = dir.listFiles();
		  return dirContents.length;
		      
	  }
	  
}
