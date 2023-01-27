
package elementhelper;

import static settings.ObjectRepo.driver;

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

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/

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
	
	
	public static boolean IsElementPresent(WebElement el){
		boolean value = false;
		try {
			if(el.isDisplayed()) 
				value = true;
		}catch(Exception e) {
			value = false;
		}
		return value;
		
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
	
	public static void VerifyDataFromExcel(WebElement el, String Name) {
		String str = el.getText();
		String webele = str.replaceAll("0 Connected | 0 Loading","");
		String webElement = webele.replaceAll("\n","");
		if(webElement.contains(Name)) {
			ObjectRepo.test.log(LogStatus.PASS,Name +" Is Displayed");
		}else {
			ObjectRepo.test.log(LogStatus.FAIL,Name +" Is Not Displayed");
		}
	}

	public static void ValidateRelevantElementIsDisplayed(List<WebElement> List_el,String Name,String message){
		for (WebElement el :List_el) {
			String ele = el.getText();
		    if(ele.equals(Name)) {
		    	ObjectRepo.test.log(LogStatus.PASS,message+" Related Answer is display");
		    	break;
		    }
		}
	}
	
	
	public static WebElement getOldOrNewLocator(WebElement oldElement,WebElement NewElement){
		Sheet Runmgr = ExcelReader.readInstance();
		String project = Runmgr.getRow(1).getCell(2).toString();
		if(project.equals("Old")) {
			return oldElement;
		}
		return NewElement;
	}
	
	public static WebElement getTestOrProdLocator(WebElement testElement,WebElement prodElement){
		Sheet Runmgr = ExcelReader.readInstance();
		String project = Runmgr.getRow(1).getCell(1).toString();
		if(project.equals("QA")) {
			return testElement;
		}
		return prodElement;
	}
	
	
	  public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
	    {
	        // Create a new ArrayList
	        ArrayList<T> newList = new ArrayList<T>();
	        // Traverse through the first list
	        for (T element : list) {
	            // If this element is not present in newList
	            // then add it
	            if (!newList.contains(element)) {
	                newList.add(element);
	            }
	        }
	  
	        // return the new list
	        return newList;
	    }

	  
	
	  
	  
	  public static boolean isDownVotedQuestionAssigned(String question){
			WebElement ele = driver.findElement(By.xpath("(//*[text()='"+question+"']//parent::*)[1]"));
			String text =ele.getAttribute("class");
			boolean assigne = text.contains("QuestionSuggestionItem_disabled__1YMjh");
			if(assigne==false) {
	 			return false;
			}else {
				System.out.println("Download Question Already Assigned!");
				return true;
			}
		
		}
	  
	  public static String getEnvironment(){
			Sheet Runmgr = ExcelReader.readInstance();
			String project = Runmgr.getRow(1).getCell(1).toString();
			return project;
			
		}
	
}
