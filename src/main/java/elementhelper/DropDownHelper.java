package elementhelper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import reporting.ExtentReportHelper;
import settings.ObjectRepo;

public class DropDownHelper {
	
	public static int selectRandomElementByIndex(WebElement dropdown,String Name) {
		try {
			Thread.sleep(3000);			
			Select dropbutton =new Select(dropdown);
			List<WebElement> dd = dropbutton.getOptions();
			int index=ThreadLocalRandom.current().nextInt(1,dd.size());
			System.out.println(index);
			dropbutton.selectByIndex(index);			
			ObjectRepo.test.log(LogStatus.INFO, Name+" Is Selected From Drodown List");	
			return index;
		}catch(Exception e) {
			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Select Element From DropDown list "+Name);
			ExtentReportHelper.logFailWithScreenshot(e.getMessage());	
			return 0;
		}
	}
	
     public static void selectRandomElementFromDivList(List<WebElement> dropdown, String name,int StartIndex) {
    	 try {
	        List<WebElement> itemsInDropdown = dropdown;
	    	int size = itemsInDropdown.size();
	    	int randomNumber = ThreadLocalRandom.current().nextInt(StartIndex,size);	
	    	itemsInDropdown.get(randomNumber).click();
	    	ObjectRepo.test.log(LogStatus.INFO, name+" Is Selected From Drodown List");
 		}catch(Exception e) {
 			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Select "+name+" From DropDown list");
 			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
 		} 	 
     }
     
     public static void selectElementByIndex(WebElement dropdown,String Name,int index) {
 		try {
 			Thread.sleep(3000);			
 			Select dropbutton =new Select(dropdown);
 			dropbutton.selectByIndex(index);					
 			ObjectRepo.test.log(LogStatus.INFO, Name+" Is Selected From Drodown List");
 		}catch(Exception e) {
 			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Select Element From DropDown list "+Name);
 			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
 		}
 	}
     
     public static void deselectElementByIndex(WebElement dropdown,String Name,int index) {
  		try {
  			Thread.sleep(3000);			
  			Select dropbutton =new Select(dropdown);
  			dropbutton.deselectByIndex(index);					
  			ObjectRepo.test.log(LogStatus.INFO, Name+" Is deSelected From Drodown List");
  		}catch(Exception e) {
  			ObjectRepo.test.log(LogStatus.FAIL, "Unable to deSelect Element From DropDown list "+Name);
  			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
  		}
     }
     
     public static void selectElementByIndexFromDivList(List<WebElement> dropdown, String name,int index) {
    	 try {
	        List<WebElement> itemsInDropdown = dropdown;
	    	itemsInDropdown.get(index).click();
	    	ObjectRepo.test.log(LogStatus.INFO, name+" Is Selected From Drodown List");
 		}catch(Exception e) {
 			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Select "+name+" From DropDown list");
 			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
 		} 	 
     }
     
     public static String selectRandomElementByIndexAndGetText(WebElement dropdown,String Name) {
    	 try {
 			Thread.sleep(3000);			
 			Select dropbutton =new Select(dropdown);
 			List<WebElement> dd = dropbutton.getOptions();
 			int index=ThreadLocalRandom.current().nextInt(1,dd.size());	
 			System.out.println(index);
 			dropbutton.selectByIndex(index);
 			WebElement option = dropbutton.getFirstSelectedOption();		 			
 			ObjectRepo.test.log(LogStatus.INFO, Name+" Is Selected From Drodown List");
 			return option.getText();
 		}catch(Exception e) {
 			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Select Element From DropDown list "+Name);
 			ExtentReportHelper.logFailWithScreenshot(e.getMessage());	
 			return null;
 		}
 	}
     
     public static String selectElementByIndexAndGetText(WebElement dropdown,String Name,int index){
  		try {
  			Thread.sleep(3000);			
  			Select dropbutton =new Select(dropdown);
  			dropbutton.selectByIndex(index);	
  			WebElement option = dropbutton.getFirstSelectedOption();	
  			ObjectRepo.test.log(LogStatus.INFO, Name+" Is Selected From Drodown List");
  			return option.getText();
  		}catch(Exception e) {
  			ObjectRepo.test.log(LogStatus.FAIL, "Unable to Select Element From DropDown list "+Name);
  			ExtentReportHelper.logFailWithScreenshot(e.getMessage());
  			return null;
  		}
  	}

     public static int sizeOfSelect(WebElement dropdown,String Name) {
 		try {
 			Thread.sleep(3000);			
 			Select dropbutton =new Select(dropdown);
 			List<WebElement> dd = dropbutton.getOptions();			
 			return dd.size();
 		}catch(Exception e) {
 			ObjectRepo.test.log(LogStatus.FAIL, "Unable to get size of dropdown list "+Name);
 			ExtentReportHelper.logFailWithScreenshot(e.getMessage());	
 			return 0;
 		}
 	}
}
