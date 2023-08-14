
package  elementhelper;

import static  settings.ObjectRepo.driver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import  reporting.ExtentReportHelper;
import  settings.ObjectRepo;
import com.relevantcodes.extentreports.LogStatus;


public class ButtonHelper{
	


	public static void click(WebElement button, String btnName) throws Exception {
		try {
			Thread.sleep(3000);
			button.click();
			ObjectRepo.test.log(LogStatus.INFO, btnName+" Clicked");
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Click Button "+btnName);
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	}
	
	public static void clickJSExecutorElement(WebElement button, String btnName) throws Exception {
		try {
			JavascriptExecutor js = ((JavascriptExecutor)driver);
			js.executeScript("arguments[0].click();",button);
			ObjectRepo.test.log(LogStatus.INFO, btnName+" Clicked");
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Click Button "+btnName);
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	}
	
	 public static List<WebElement> getWebElement(String xpath) {
		  List<WebElement> el = driver.findElements(By.xpath(xpath));
		  return el;
	 }
	
	public static ArrayList<String> clickPaginationButton(int totalPages, String xpath) throws Exception {
		ArrayList<String> arrlist = new ArrayList<String>();
		for(int i=1; i<=totalPages; i++) {
			 List<WebElement> el = getWebElement(xpath);
			 for(WebElement ele : el) {
				 arrlist.add(ele.getText());
			 }
//			 int totalweb = el.size();
//			 totalConnectedWebsite = totalConnectedWebsite + totalweb;
			 
			 String term =Integer.toString(i+1);
				
				if(term.equals(Integer.toString(totalPages+1) )) {
					break;
				}
				
			    WebElement btn = driver.findElement(By.xpath("//*[@aria-label='Page "+term+"']"));
				ButtonHelper.click(btn, btn.getText());
		 }
		return arrlist;
	}
	
	
	
}
