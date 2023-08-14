
package elementhelper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import reporting.ExtentReportHelper;
import settings.ObjectRepo;
import com.mongodb.diagnostics.logging.Logger;
import com.relevantcodes.extentreports.LogStatus;


public class TextBoxHelper {
	
	public static void enterTextString(WebElement textbox, String Name, String Value){
		try {
			textbox.clear();			
			textbox.sendKeys(Value);
			ObjectRepo.test.log(LogStatus.INFO, Value+" entered in textbox "+Name);
			
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Unable to enter Text in "+Name);
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
		
	}
	
	
	
	public static void validateTextBoxValue(WebElement textbox, String Name, String Value) throws Exception {
		try {
			if(textbox.getAttribute("value").equals(Value))
				ObjectRepo.test.log(LogStatus.PASS, "Textbox "+Name+" has value as "+Value);
			else
				ObjectRepo.test.log(LogStatus.FAIL, "Textbox "+Name+" does not have value as "+Value);
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Unable to find textbox "+Name);
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
		
	}

}
