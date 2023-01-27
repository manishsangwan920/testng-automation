
package generic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import elementhelper.ButtonHelper;
import reporting.ExtentReportHelper;
import settings.ObjectRepo;
import com.relevantcodes.extentreports.LogStatus;


/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/

public class GenericHelper {

	public static String getTestData(String var) {
		try {
			
			return null;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void validateElementText(WebElement element, String var, String Text, boolean CaseValidation) throws Exception {
		// TODO Auto-generated method stub
		try {
			if(CaseValidation) {
				if(element.getText().equals(Text)) {
					ObjectRepo.test.log(LogStatus.PASS, var+" is displayed as "+Text);
				}else {
					ObjectRepo.test.log(LogStatus.FAIL, var+" is NOT displayed as "+Text);
				}
			}else {
				if(element.getText().equalsIgnoreCase(Text)) {
					ObjectRepo.test.log(LogStatus.PASS, var+" is displayed as "+Text);
				}else {
					ObjectRepo.test.log(LogStatus.FAIL, var+" is NOT displayed as "+Text);
				}
			}
			
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Unable to find "+var);
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
		}
	}
	
	
	public static boolean validateTimeisWithinPastMinutes(String time, int minutes) {
		try {
			Date date = new Date(System.currentTimeMillis() - minutes*60*1000);
		    Calendar calendar1 = Calendar.getInstance();
		    calendar1.setTime(date);
		    calendar1.add(Calendar.DATE, 1);

		    Date d = new SimpleDateFormat("M/d/yyyy, h:m:s aa").parse(time);
		    Calendar calendar3 = Calendar.getInstance();
		    calendar3.setTime(d);
		    calendar3.add(Calendar.DATE, 1);

		    Date x = calendar3.getTime();
		    if (x.after(calendar1.getTime())) {
		        return true;
		    }else {
		    	return false;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}
	}
	
	
	public static String scrollHorizontalandFindElementText(WebElement section, WebElement element) {
		ObjectRepo.driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
		for(int i =0; i<8; i++) {
			try {
				return element.getText();
			}catch(Exception e) {
				((JavascriptExecutor) ObjectRepo.driver).executeScript("arguments[0].scrollLeft += 800", section);
			}
		}
		return "";
	}
	
	
	public static double fahrenheittocelsius(double x) {
		return (x-32)*(0.5556);
	}
	
	public static void scrollElementInView(WebElement element, String elName) {
		try {
			Actions actions = new Actions(ObjectRepo.driver);
			actions.moveToElement(element);
			actions.perform();
		}catch (Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Unable to find "+elName);
		}
	
	}
	

	
	public static void scrollSectionVertically(WebElement toScroll) {
		((JavascriptExecutor) ObjectRepo.driver).executeScript("arguments[0].scrollBy(0, 1000)", toScroll);
	}
	
	
	public static void selectoptionfromdropDown(String channelName) throws Exception {
		WebElement element = ObjectRepo.driver.findElement(By.xpath("//*[text()='"+channelName+"']"));
		ButtonHelper.click(element,channelName);
	
	}
	
	public static int generateRamdomNumber(int min, int max){
		int randomNumbar = (int)(Math.random()*(max-min+1)+min);  
		return randomNumbar;
	}  
	
	
	
	
	

}











