package stepdefination;

import com.relevantcodes.extentreports.LogStatus;

import configreader.ExcelReader;

import static settings.ObjectRepo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static settings.ObjectRepo.driver;
import elementhelper.ButtonHelper;
import elementhelper.DropDownHelper;
import elementhelper.TextBoxHelper;
import generic.GenericHelper;
import pages.AccountPage;
import pages.AddMachinesPage;
import pages.AddServiceEngineerPage;
import pages.HomePage;
import pages.HomePageSideBar;
import pages.LoginPage;
import pages.ProfilePage;
import pages.TaskPage;
import reporting.ExtentReportHelper;
import settings.ObjectRepo;
import elementhelper.GenericElements;

public class Stepdefination {
	
	
	public static void LoginPageContents() {
		try {
			LoginPage loginPage = new LoginPage(driver);
			String actualTitle = driver.getTitle();
			if(actualTitle.equalsIgnoreCase("Exxon"))
				test.log(LogStatus.PASS, "Title matched as EXXON");
			else
				test.log(LogStatus.FAIL, "Title did not match");
			
			if(loginPage.loginForm.isDisplayed())
				test.log(LogStatus.PASS, "Login page is displayed");
			else
				test.log(LogStatus.FAIL, "Login page is not displayed");
			
			if(loginPage.emailTextBox.isDisplayed())
				test.log(LogStatus.PASS, "Email text box is displayed");	
			else 
				test.log(LogStatus.FAIL, "Email text box is not displayed");			
			
			if(loginPage.passwordTextBox.isDisplayed())
				test.log(LogStatus.PASS, "Password text box is displayed");
			else
				test.log(LogStatus.FAIL, "Password text box is not displayed");
			
			if(loginPage.loginButton.isDisplayed())
				test.log(LogStatus.PASS, "Login button is displayed");
			else
				test.log(LogStatus.FAIL, "Login button is not displayed");
				
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}		
	}
	
	public static void LoginApplication(String userName, String password,String welcomeName)  {
		try {
			LoginPage lp = new LoginPage(driver);
			HomePage hp = new HomePage(driver);
			TextBoxHelper.enterTextString(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterTextString(lp.passwordTextBox, "Password",password);
			ButtonHelper.click(lp.checkBox, "Terms and Conditions Checkbox");
			ButtonHelper.click(lp.loginButton, "Login Button");	
			String DashBoard =hp.dashboard.getText();
		   if(DashBoard.equals(welcomeName))
			   test.log(LogStatus.PASS, "User successfull logged into the application");
		   else
			   test.log(LogStatus.FAIL, "User was not able to login to application");  
			   
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void VerifyInvalidLogin(String userName, String password)  {
		try {
			LoginPage lp = new LoginPage(driver);
			TextBoxHelper.enterTextString(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterTextString(lp.passwordTextBox, "Password",password);
			ButtonHelper.click(lp.checkBox, "Terms and Conditions Checkbox");
			ButtonHelper.click(lp.loginButton, "Login Button");
			Thread.sleep(3000);
		   if(lp.errorMessage.isDisplayed())
			   test.log(LogStatus.PASS, "User Login failed either username or password is incorrect");
		   else
			   test.log(LogStatus.FAIL, "User Logged into the application with invalid credential ");
			   
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void LoginWithoutTermsCheckbox(String userName, String password){
		try {
			LoginPage lp = new LoginPage(driver);
			HomePage hp = new HomePage(driver);
			TextBoxHelper.enterTextString(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterTextString(lp.passwordTextBox, "Password",password);
	    	   if(lp.loginButton.isEnabled()) {
	    		   ButtonHelper.click(lp.loginButton, "Login Button");
	    		   String dash =hp.dashboard.getText();
		    	   if(dash.equals("Dashboard"))
					test.log(LogStatus.FAIL, "User was able login without clicking terms and condition checkbox");
		    	   else
		    		   test.log(LogStatus.FAIL, "User was not able login but login button is clickable");  
	    	   }else {
					test.log(LogStatus.PASS, "Login button is disabled"); 
					test.log(LogStatus.PASS, "User was not able to login to the application when TnC checkbox was not selected"); 
	    	   }
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}	
	}
	
	public static void LoginWithoutCredentials() {
		try {
			LoginPage lp = new LoginPage(driver);
			HomePage hp = new HomePage(driver);
			 if(lp.loginButton.isEnabled()) {
	    		   ButtonHelper.click(lp.loginButton, "Login Button");
	    		   String dash =hp.dashboard.getText();
		    	   if(dash.equals("Dashboard"))
					test.log(LogStatus.FAIL, "User was able login without entering any credential");
		    	   else
		    		   test.log(LogStatus.FAIL, "User was not able login but login button is displayed when TnC checkbox was not selected");  
	    	   }else {
					test.log(LogStatus.PASS, "User was not able to login to the application"); 
	    	   }
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}	

		
	}
	
	public static void TermsAndCondition() {
		try {
			LoginPage lp = new LoginPage(driver);
			ButtonHelper.click(lp.termsAndCondition, "Terms And Condition");
			Thread.sleep(3000);
			String mainWindowHandle = driver.getWindowHandle();
	        Set<String> allWindowHandles = driver.getWindowHandles();
	        Iterator<String> iterator = allWindowHandles.iterator();
	        while (iterator.hasNext()) {
	            String ChildWindow = iterator.next();
	            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
	            driver.switchTo().window(ChildWindow);
	            String currentURL = driver.getCurrentUrl();
	    		URL URL = new URL(currentURL);
	    		InputStream Inputfile = URL.openStream();
	    		BufferedInputStream file =new BufferedInputStream(Inputfile);
	    		PDDocument document = PDDocument.load(file);
	    		String pdfContent= new PDFTextStripper().getText(document);
	    		URL LocalURL = new URL("file:///D:/newautomations/testng-automation/src/test/resources/TestData/termsandconditions.pdf");
	    		InputStream Testfile = LocalURL.openStream();
	    		BufferedInputStream file2 =new BufferedInputStream(Testfile);
	    		PDDocument document2 = PDDocument.load(file2);
	    		String testPdfContent= new PDFTextStripper().getText(document2);
	    		if(testPdfContent.equalsIgnoreCase(pdfContent))
	    			 test.log(LogStatus.PASS, "the terms and condition link is clickable and contents inside link are as expected :"+testPdfContent);
				   else
					   test.log(LogStatus.FAIL, "Either the terms and condition link is not clickable or contents inside the link are unexpected :"+testPdfContent);  		    			
	            }
	        }
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void ForgetPassword() {
		try{
			LoginPage lp = new LoginPage(driver);
		    ButtonHelper.click(lp.forgetPassword, "forget password?");
		    Thread.sleep(3000);
		    if(lp.forgotPasswordEmailTextBox.isDisplayed())
		    	test.log(LogStatus.PASS, "Forgot password link is clickable and functional");
			else
				test.log(LogStatus.FAIL, "Either forgot password link is not clickable or not functional"); 
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void AcceptCookies() {
		try{
			LoginPage lp = new LoginPage(driver);
		    Thread.sleep(3000);
		    if(lp.cookieCard.isDisplayed()) {
		    	test.log(LogStatus.INFO, "Accept cooikes pop up is displayed");
		    	ButtonHelper.click(lp.cookieAcceptButton, "Accept Button");
		    	test.log(LogStatus.PASS, "User was able to see and accept cookies");
		    }else{
				test.log(LogStatus.FAIL, "Either cooikes pop up is not displayed or User is not able to accept the cookies");}
			}catch(Exception e) {
				e.printStackTrace();
				test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}		
	}
	
	public static void DashboardIsPresent() {
		try{
			HomePage hp = new HomePage(driver);		
		    Thread.sleep(3000);
		    if(hp.dashboard.isDisplayed()&&hp.taskStatus.isDisplayed()&&hp.distributersInfo.isDisplayed()&&hp.accountTaskRecords.isDisplayed()) {
		    	if(hp.solcareObservationRecords.isDisplayed()&&hp.titleAccountTaskRecords.isDisplayed()&&hp.titleServiceEngineerRecords.isDisplayed())
		    		test.log(LogStatus.PASS, "User was able see Dashboard");
		    }else {
		    	test.log(LogStatus.FAIL, "One or more component of dashboard are not present");
		    }
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void IsCompanyNamePresent(String companyname){
		try {  
			HomePage hp = new HomePage(driver);
			if(hp.companyName.getText().equals(companyname))
			test.log(LogStatus.PASS, "After login Company name is displayed on Header of HomePage");
	        else
			test.log(LogStatus.FAIL, "company name is not present on header of HomePage");			
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	}
	}
	
	public static void IsCompanyNamePresent(){
		try {  
			HomePage hp = new HomePage(driver);
			if(hp.companynamelogo.isDisplayed())
			test.log(LogStatus.PASS, "After login Company name is displayed on Header of HomePage");
	        else
			test.log(LogStatus.FAIL, "company name is not present on header of HomePage");			
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	}
	}
	
	public static void WelcomeDistibutersName(){
		try {  
			HomePage hp = new HomePage(driver);
			String welcomeName = hp.welcomeDistributersName.getText();
			if(welcomeName.equalsIgnoreCase("welcome Parikshit"))
				test.log(LogStatus.PASS, "welcome and distributers name is displayed");
	         else
			test.log(LogStatus.FAIL, "Either welcome or distributers name is not present");			
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void profileAndRestPassword() {
		try{
			HomePage hp = new HomePage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");
			if(hp.profile.isDisplayed()&&hp.resetPassword.isDisplayed()&&hp.logoutButton.isDisplayed())
				test.log(LogStatus.PASS, "profile,reset password and logout button are present");
	         else
			test.log(LogStatus.FAIL, "Either profile or reset password or logout is not present");				
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
				
	}
	
	public static void UploadProfilePicture(){
		try {
			
			HomePage hp = new HomePage(driver);
			ProfilePage pp =new ProfilePage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");
			ButtonHelper.click(hp.profile,"Profile"); 
			ButtonHelper.click(pp.uploadPicture, "Upload Picture Button");			
			Robot robo = new Robot();
			robo.delay(2000);
			System.getProperty("user.dir");
			StringSelection addressString = new StringSelection(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\profilepicture.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(addressString,null);
			robo.keyPress(KeyEvent.VK_CONTROL);
			robo.keyPress(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_CONTROL);
			robo.keyRelease(KeyEvent.VK_V);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
			ButtonHelper.click(pp.savePicture, "Save Picture");
			Thread.sleep(3000);
			String chromeMessage = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();	
			Thread.sleep(2000);
			  if(pp.profilePicture.isDisplayed()&&chromeMessage.equalsIgnoreCase("user profile picture is successfully updated."))
				  test.log(LogStatus.PASS, "Profile Picture was uploaded and saved successfully");
		         else
				test.log(LogStatus.FAIL, "either profile picture was not uploaded or it was not saved");				
			}catch(Exception e) {
				e.printStackTrace();
				test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
			}  
		
	}
	
	public static void EditProfilePicture() {
		try {
			HomePage hp = new HomePage(driver);
			ProfilePage pp =new ProfilePage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");
			ButtonHelper.click(hp.profile,"Profile from dropdown is"); 
			ButtonHelper.click(pp.uploadPicture, "Upload Picture Button");			
			Robot robo = new Robot();
			robo.delay(2000);
			StringSelection addressString = new StringSelection(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\profilepicture.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(addressString,null);
			robo.keyPress(KeyEvent.VK_CONTROL);
			robo.keyPress(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_CONTROL);
			robo.keyRelease(KeyEvent.VK_V);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
			ButtonHelper.click(pp.savePicture, "Save Picture");
			Thread.sleep(1000);
			String chromeMessage = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();			
			  if(pp.profilePicture.isDisplayed()&&chromeMessage.equalsIgnoreCase("Your profile picture is successfully updated."))
				  test.log(LogStatus.PASS, "Profile Picture was uploaded and saved successfully");
		         else
				test.log(LogStatus.FAIL, "either profile picture was not uploaded or it was not saved");
			pp = new ProfilePage(driver);
			ButtonHelper.click(pp.uploadPicture, "Upload Picture");
			robo.delay(2000);
			StringSelection addressString2 = new StringSelection(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\profilepicture2.jpeg");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(addressString2,null);
			robo.keyPress(KeyEvent.VK_CONTROL);
			robo.keyPress(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_CONTROL);
			robo.keyRelease(KeyEvent.VK_V);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
			ButtonHelper.click(pp.savePicture, "Save Picture");
			Thread.sleep(3000);
			String chromeMessage2 = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();			
			  if(pp.profilePicture.isDisplayed()&&chromeMessage2.equalsIgnoreCase("Your profile picture is successfully updated."))
				  test.log(LogStatus.PASS, "Profile Picture was edited and saved sucessfully");
		         else
				test.log(LogStatus.FAIL, "Either the new profile picture was not uploaded or not saved");				
			}catch(Exception e) {
				e.printStackTrace();
				test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
			}  
		
	}
	
	public static void editProfileEmail() {
		try {
			HomePage hp = new HomePage(driver);
			ProfilePage pp =new ProfilePage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");
			ButtonHelper.click(hp.profile,"Profile Button");
			ButtonHelper.click(pp.Edit," Edit Button");
			Thread.sleep(3000);	    
		    if(pp.editProfileEmail.getAttribute("readonly").equalsIgnoreCase("true"))
		    	test.log(LogStatus.PASS, "Users email address is nor editable");
		    	else
		    		test.log(LogStatus.FAIL, "Users email address is editable");				    	
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		} 							
	}
	
	public static void editUserProfile(String name,String phNumber,String companyname) {
		try {
			HomePage hp = new HomePage(driver);
			ProfilePage pp =new ProfilePage(driver);
			HomePageSideBar sb = new HomePageSideBar(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");
			ButtonHelper.click(hp.profile,"Profile");
			ButtonHelper.click(pp.Edit," Edit Button");
			Thread.sleep(3000);	    
		    if(pp.editProfileName.getAttribute("contenteditable").equalsIgnoreCase("true"))
		    	test.log(LogStatus.PASS, "Users name is  editable");
		    	else
		    		test.log(LogStatus.FAIL, "Users name is not  editable");
		    if(pp.editProfilePhone.getAttribute("contenteditable").equalsIgnoreCase("true"))
		    	test.log(LogStatus.PASS, "Users phone number is  editable");
		    	else
		    		test.log(LogStatus.FAIL, "Users phone number is not  editable");
		    if(pp.editProfileCompany.getAttribute("contenteditable").equalsIgnoreCase("true"))
		    	test.log(LogStatus.PASS, "company name is  editable");
		    	else
		    		test.log(LogStatus.FAIL, "company name is not  editable");
		    pp.editProfileName.clear();
		    TextBoxHelper.enterTextString(pp.editProfileName, "Name", name);
		    pp.editProfilePhone.clear();
		    TextBoxHelper.enterTextString(pp.editProfilePhone, "PHONE", phNumber);
		    pp.editProfileCompany.clear();
		    TextBoxHelper.enterTextString(pp.editProfileCompany, "Company", companyname);
		    ButtonHelper.click(pp.editProfileSave, "Save Button");
		    Thread.sleep(3000);
		    driver.switchTo().alert().accept();	
		    ButtonHelper.click(sb.dashboardSideBar,"Dashbaord on Sidebar tab");
		    if(hp.companyName.getText().equals(companyname))
				test.log(LogStatus.PASS, "Edited Company name is displayed on Header of HomePage");
		    else
		       	test.log(LogStatus.FAIL, "Edited company name is not present on header of home page");
		    if(hp.distributersName.getText().equals(name))
		    	test.log(LogStatus.PASS, "Edited user name is displayed on Dashboard");
	        else
	        	test.log(LogStatus.FAIL, "Edited user name is not present");
		    Thread.sleep(3000);
		    hp = new HomePage(driver);
		    hp.profileIcon.click();
		    hp.profile.click();
			pp =new ProfilePage(driver);
			pp.Edit.click();	   
		   Thread.sleep(3000);
		   pp.editProfileName.clear();
		   pp.editProfilePhone.clear();
		   pp.editProfileCompany.clear();
		   pp.editProfileName.sendKeys(ExcelReader.ReadTestData("original_edit_profile_user_name"));
		   pp.editProfileCompany.sendKeys(ExcelReader.ReadTestData("original_edit_profile_company_name"));
		   pp.editProfilePhone.sendKeys(ExcelReader.ReadTestData("original_edit_profile_user_contact_num "));
		   pp.editProfileSave.click();
		   Thread.sleep(2000);
		   driver.switchTo().alert().accept();
		   sb =new HomePageSideBar(driver);
		   ButtonHelper.click(sb.dashboardSideBar,"Dashbaord on Sidebar tab");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		} 
	}
	
	public static void WrongExistingPassword(String oldpassword,String newpassword) {
		try {
			HomePage hp = new HomePage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");		
			ButtonHelper.click(hp.resetPassword," Reset Password");
			Thread.sleep(3000);
			TextBoxHelper.enterTextString(hp.existingPassword,"Existing password",oldpassword);
			TextBoxHelper.enterTextString(hp.newPassword,"new password",newpassword);
			TextBoxHelper.enterTextString(hp.confirmPassword,"new password",newpassword);
			ButtonHelper.click(hp.resetPasswordSubmit," Submit Button");
			Thread.sleep(3000);
			if(driver.switchTo().alert().getText().equalsIgnoreCase("The existing password does not match with the password you have provided. Please type your correct existing password and try again."))
				test.log(LogStatus.PASS, "Error Message is displayed when wrong existing password is entered while reseting password");
        		else
        			test.log(LogStatus.FAIL, "NO Error Message is displayed when wrong existing password is entered while reseting password");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		} 
	}
	
	public static void PasswordNotMatching(String oldpassword,String newpassword,String confpassword) {
		try {
			HomePage hp = new HomePage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");		
			ButtonHelper.click(hp.resetPassword," Reset Password");
			Thread.sleep(3000);
			TextBoxHelper.enterTextString(hp.existingPassword,"Existing password",oldpassword);
			TextBoxHelper.enterTextString(hp.newPassword,"new password",newpassword);
			TextBoxHelper.enterTextString(hp.confirmPassword,"confirm password",confpassword);
			Thread.sleep(3000);
			if(hp.errorMsgPasswordMismatch.isDisplayed()&&hp.errorMsgPasswordMismatch.getText().equalsIgnoreCase("Password are not matching"))
				test.log(LogStatus.PASS, "Error Message is displayed when new password and confirm password are not matching while reseting password");
        		else
        			test.log(LogStatus.FAIL, "NO Error Message is displayed when new password and confirm password are not matching while reseting password");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		} 		
	}
	
	public static void PasswordMeetingCriteria() {
		try {
			HomePage hp = new HomePage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");		
			ButtonHelper.click(hp.resetPassword," Reset Password");
			Thread.sleep(3000);
			String randomNumber = Integer.toString(ThreadLocalRandom.current().nextInt(0,9));
			System.out.println(randomNumber);
			TextBoxHelper.enterTextString(hp.newPassword,"new password",randomNumber);
			if(hp.verifyNumber.getAttribute("class").equalsIgnoreCase("valid"))
				test.log(LogStatus.PASS, "If User Enter A Number 'At least One number' criteria is satisfied");
    		else
    			test.log(LogStatus.FAIL, "If User Enter A Number 'At least One number' criteria is Not satisfied");	
			String C1 = "QWERTYUIOPLKJHGFDSAZXCVBNM";
			String capitalletter = Character.toString(C1.charAt(ThreadLocalRandom.current().nextInt(C1.length())));
			System.out.println(capitalletter);
			 TextBoxHelper.enterTextString(hp.newPassword,"new password",capitalletter);
			if(hp.verifyCapitalLetter.getAttribute("class").equalsIgnoreCase("valid"))
				test.log(LogStatus.PASS, "If User Enter A Uppercase Letter 'At least One capital Letter' criteria is satisfied");
    		else
    			test.log(LogStatus.FAIL, "If User Enter A UpperCase letter 'At least One capital Letter' criteria is Not satisfied");
		        String generatedString = RandomStringUtils.randomAlphabetic(4);
		        System.out.println(generatedString);
		        String completepassword=generatedString+capitalletter+randomNumber;
		        TextBoxHelper.enterTextString(hp.newPassword,"new password",completepassword);
			if(hp.verifyNumber.getAttribute("class").equalsIgnoreCase("valid"))
				test.log(LogStatus.PASS, "If User Enter  more than six letters 'At least contains six Letter' criteria is satisfied");
        		else
        			test.log(LogStatus.FAIL,"If User Enter more than six letters 'At least contains six Letter' criteria is Not satisfied");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		} 		
	}

	public static void ResetPassword(String userName,String reseted_password,String welcomeName) {
		try {
			HomePage hp = new HomePage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");		
			ButtonHelper.click(hp.resetPassword," Reset Password");
			Thread.sleep(3000);
			TextBoxHelper.enterTextString(hp.existingPassword,"Existing password",ExcelReader.ReadTestData("password"));
			TextBoxHelper.enterTextString(hp.newPassword,"new password",ExcelReader.ReadTestData("reseted_password"));
			TextBoxHelper.enterTextString(hp.confirmPassword,"confirm password",ExcelReader.ReadTestData("reseted_password"));
			ButtonHelper.click(hp.resetPasswordSubmit," Submit Button");
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.alertIsPresent());
			if(driver.switchTo().alert().getText().equalsIgnoreCase("Your pasword has been successfully reset."))
				test.log(LogStatus.PASS, "User was able to reset password");
			else
				test.log(LogStatus.FAIL, "User was not able reset password"); 
			driver.switchTo().alert().accept();
			ButtonHelper.click(hp.profileIcon, "profile icon");		
			ButtonHelper.click(hp.logoutButton," Logout Button under Profile icon");
			LoginPage lp = new LoginPage(driver);			
			TextBoxHelper.enterTextString(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterTextString(lp.passwordTextBox, "Password",reseted_password);
			ButtonHelper.click(lp.checkBox, "Terms and Conditions Checkbox");
			ButtonHelper.click(lp.loginButton, "Login Button");
			HomePage hp1 = new HomePage(driver);
			 if(hp.dashboard.getText().equals(welcomeName))
				   test.log(LogStatus.PASS, "User successfull logged into the application with new reseted password");
			 else
				   test.log(LogStatus.FAIL, "User was not able to login to application with the new reseted password");
			 hp1.profileIcon.click();
			 hp1.resetPassword.click();			
			 hp1.existingPassword.sendKeys(ExcelReader.ReadTestData("reseted_password"));
			 hp1.newPassword.sendKeys(ExcelReader.ReadTestData("password"));
			 hp1.confirmPassword.sendKeys(ExcelReader.ReadTestData("password"));
			 hp1.resetPasswordSubmit.click();
			 wait.until(ExpectedConditions.alertIsPresent());
			 driver.switchTo().alert().accept();
			 
			   
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		} 		
	}
	
	public static void closeResetpasswordpopUP() {
		try {
			HomePage hp = new HomePage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");		
			ButtonHelper.click(hp.resetPassword," Reset Password");
			Thread.sleep(3000);
			ButtonHelper.click(hp.closeResetPasswordWindow," Close Button");
			Thread.sleep(3000);
			if(hp.existingPassword.isDisplayed())
				test.log(LogStatus.FAIL, "Reset Password PopUp Is Not Closed after clicking On The Close Button");
        		else
        			test.log(LogStatus.PASS, "Reset Password popup Is Closed after clicking On The Close Button");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		} 		
	}
	
	public static void LogoutProfileIcon() {
		try {
			HomePage hp = new HomePage(driver);
			LoginPage lp =new LoginPage(driver);
			ButtonHelper.click(hp.profileIcon, "profile icon");		
			ButtonHelper.click(hp.logoutButton," Logout Button under Profile icon");
			
			if(lp.loginForm.isDisplayed())
				test.log(LogStatus.PASS, "User logged out of application successfully");
        		else
        			test.log(LogStatus.FAIL, "user was not able to logout of application");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		} 		
	}
	
	public static void LogoutSideBar() {
		try {
			LoginPage lp =new LoginPage(driver);
			HomePageSideBar sb = new HomePageSideBar(driver);
			ButtonHelper.click(sb.logOutButtonSideBar," Logout Button on Side Bar");
			if(lp.loginForm.isDisplayed())
				test.log(LogStatus.PASS, "User logged out of application successfully");
        		else
        			test.log(LogStatus.FAIL, "user was not able to logout of application");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		} 		
	}
    
	public static void TaskStatus() {
		try {  
			HomePage hp = new HomePage(driver);	
			Thread.sleep(3000);
			String dataCheck=hp.taskStatus.getText();
			System.out.println(dataCheck);
			File srcFile=hp.taskStatus.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/reports/TaskStatus.png"));
			if(hp.NoDatataskStatus.isDisplayed()) {
				test.log(LogStatus.FAIL, "Task Status Is Not Displayed");
			    test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/TaskStatus.png"));
			}else {
			test.log(LogStatus.PASS, "Task Status Is Displayed");	
			test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/TaskStatus.png"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void DistributersInfo() {
		try {  
			HomePage hp = new HomePage(driver);	
			Thread.sleep(3000);
			File srcFile=hp.distributersInfo.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/reports/DistributersInfo.png"));
			if(hp.distributersInfo.isDisplayed()) {
				test.log(LogStatus.PASS, "Distributers info Is Displayed");
			    test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/DistributersInfo.png"));
			}else {
			test.log(LogStatus.FAIL, "Distributers Info Is Not Displayed");	
			test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/DistributersInfo.png"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void AccountStatusRecord() {
		try {  
			HomePage hp = new HomePage(driver);	
			Thread.sleep(3000);
			String dataCheck=hp.taskStatus.getText();
			System.out.println(dataCheck);
			File srcFile=hp.accountTaskRecords.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/reports/AccountStatusRecord.png"));
			if(hp.accountTaskRecords.isDisplayed()) {
				test.log(LogStatus.PASS, "Account Task Records are Displayed");
			    test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/AccountStatusRecord.png"));
			}else {
			test.log(LogStatus.FAIL, "Account Task Records are Not Displayed");	
			test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/AccountStatusRecord.png"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void	SolcareObservationRecords() {
		try {  
			HomePage hp = new HomePage(driver);	
			Thread.sleep(4000);
			Select drpaccount = new Select(hp.SelectAccount);
			drpaccount.selectByValue("358");
			Thread.sleep(3000);
			File srcFile=hp.solcareObservationRecords.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/reports/SolcareObservationRecords.png"));
			if(hp.solcareObservationRecords.isDisplayed()) {
				test.log(LogStatus.PASS, "Solcare Observation Records are Displayed");
			    test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/SolcareObservationRecords.png"));
			}else {
			test.log(LogStatus.FAIL, "Solcare Observation Records are Not Displayed");	
			test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/SolcareObservationRecords.png"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void	downloadcsv() {
		try { 			
			HomePage hp = new HomePage(driver);	
			Thread.sleep(4000);
			DropDownHelper.selectRandomElementByIndex(hp.SelectAccount, "Account");
			Thread.sleep(3000);				
			ButtonHelper.click(hp.ExportCSV, "export csv");			
			Thread.sleep(30000);
			int num=GenericElements.NumberOfFilesInPresentInFolder(System.getProperty("user.home")+"\\Downloads");
			boolean check=false;
			for(int i=1;i<=num;i++)
			{
				if(GenericElements.isFileDownloaded(System.getProperty("user.home")+"\\Downloads",ExcelReader.ReadTestData("name_of_export_csv_file")+".csv")) {
					check=true;
					break;
				}else{
					check=GenericElements.isFileDownloaded(System.getProperty("user.home")+"\\Downloads",ExcelReader.ReadTestData("name_of_export_csv_file")+"("+i+").csv");
					if(check)
						break;
				}
			}
			if(check) {				
				test.log(LogStatus.PASS, "Use Is Able To Download CSV File After Clicking Export CSV");	
			}else {
				test.log(LogStatus.FAIL, "Use Is Not Able To Download CSV File After Clicking Export CSV");
				test.log(LogStatus.INFO, "Name And extension of the downloded file should be same as present in test data");
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void	downloadChart(String OneOrAll) {
		try { 			
			HomePage hp = new HomePage(driver);	
			Thread.sleep(4000);
			DropDownHelper.selectRandomElementByIndex(hp.SelectAccount, "Account");
			Thread.sleep(3000);										
			
			int num=GenericElements.NumberOfFilesInPresentInFolder(System.getProperty("user.home")+"\\Downloads")+1;
			boolean check=false;
			if(OneOrAll=="all")
			{  
				ButtonHelper.click(hp.ExportChart, "Export CSV");
				Thread.sleep(30000);
				for(int i=1;i<=num;i++)
				{
					if(GenericElements.isFileDownloaded(System.getProperty("user.home")+"\\Downloads",ExcelReader.ReadTestData("name_export_chart_allMachines")+"."+ExcelReader.ReadTestData("extention_export_chart_allMachines"))) {
						check=true;
						break;
					}else{
						check=GenericElements.isFileDownloaded(System.getProperty("user.home")+"\\Downloads",ExcelReader.ReadTestData("name_export_chart_allMachines")+"("+i+")."+ExcelReader.ReadTestData("extention_export_chart_allMachines"));
						if(check)
							break;
					}
				}
			}
			if(OneOrAll=="one")
			{
				hp = new HomePage(driver);									
				DropDownHelper.selectRandomElementByIndexAndGetText(hp.Selectmachine, "Machine");				    				
				ButtonHelper.click(hp.ExportChart, "export csv");
				Thread.sleep(30000);
				for(int i=1;i<=num;i++)
				{
					if(GenericElements.isFileDownloaded(System.getProperty("user.home")+"\\Downloads",ExcelReader.ReadTestData("name_export_chart_specificMachine")+"."+ExcelReader.ReadTestData("extention_export_chart_specificMachine"))) {
						check=true;
						break;
					}else{
						check=GenericElements.isFileDownloaded(System.getProperty("user.home")+"\\Downloads",ExcelReader.ReadTestData("name_export_chart_specificMachine")+"("+i+")."+ExcelReader.ReadTestData("extention_export_chart_specificMachine"));
						if(check)
							break;
					}
				}
			}
			if(check)
				test.log(LogStatus.PASS, "Use Is Able To Download Chart After Clicking Export Chart");
			else
				test.log(LogStatus.FAIL, "Use Is Not Able To Download Chart After Clicking Export Chart");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	
	public static void serviceEngineerRecords() {
		try {  
			HomePage hp = new HomePage(driver);	
			Thread.sleep(6000);
			File srcFile=hp.serviceEngineerRecords.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/reports/serviceEngineerRecords.png"));
			if(hp.serviceEngineerRecords.isDisplayed()) {
				test.log(LogStatus.PASS, "service Engineer Records are Displayed");
			    test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/serviceEngineerRecords.png"));
			}else {
			test.log(LogStatus.FAIL, "service Engineer Records are Not Displayed");	
			test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/serviceEngineerRecords.png"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void createTaskPopUp() {
		try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			Thread.sleep(4000);
			File srcFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/reports/createTaskPopUp.png"));
		    if(tp.SaveCreatedTask.isDisplayed()&&tp.CloseCreateTaskPopUp.isDisplayed()&&tp.SaveCreatedTask.isDisplayed()&&tp.DueDate.isDisplayed()&&tp.SelectedAccount.isDisplayed()&&tp.ScheduleNoneBtn.isDisplayed()) {
		    	if(tp.TaskDescription.isDisplayed()&&tp.Disabledmachine.isDisplayed()&&tp.DisabledWatchers.isDisplayed()&&tp.ScheduleMonthlyBtn.isDisplayed()&&tp.ScheduleDailyBtn.isDisplayed()&&tp.ScheduleWeeklyBtn.isDisplayed()) {		      
		    	 test.log(LogStatus.PASS, " Create Task Pop Up is Displayed after clicking on create task button");
		    	 test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/createTaskPopUp.png"));
		    	}
			}else {
				test.log(LogStatus.FAIL, "create task popup is Not Displayed or some field on the popup is not present");	
				test.log(LogStatus.INFO, test.addScreenCapture(System.getProperty("user.dir")+"/reports/createTaskPopUp.png"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	
	
	public static void BasicCreateTaskHalfHelper(String Taskname, String Description,String dueDate) {
    	try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			Thread.sleep(4000);
			TextBoxHelper.enterTextString(tp.TaskName,"Task Name", Taskname);
			DropDownHelper.selectRandomElementByIndex(tp.ServiceEngineer,"service engineer");
			DropDownHelper.selectRandomElementByIndex(tp.SelectedAccount,"Account");
			TextBoxHelper.enterTextString(tp.DueDate,"Due Date", dueDate);		
			tp=new TaskPage(driver);
			ButtonHelper.click(tp.EnabledWatcher, "watcher button");			
			DropDownHelper.selectRandomElementFromDivList(tp.WatcherList,"watcher",0);
			Thread.sleep(2000);
			for(int i=0;i<=5;i++) {
				try {
					if(driver.switchTo().alert().getText().equalsIgnoreCase("The assignee cannot be a watcher")) {
					driver.switchTo().alert().accept();
					tp=new TaskPage(driver);							
					DropDownHelper.selectRandomElementFromDivList(tp.WatcherList,"watcher",0);
					}
				}catch(NoAlertPresentException e) {	
					break;
				}
			}
			TextBoxHelper.enterTextString(tp.TaskDescription,"Description", Description);
			DropDownHelper.selectRandomElementByIndex(tp.TaskType,"Task Type");
			ButtonHelper.click(tp.ScheduleNoneBtn, "schedule none button");
			String screenshotPath = ExtentReportHelper.getScreenshot();
			test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
    	}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
    	
    }
	
	 public static void SaveTaskAndApplyFilter(){ 
	    	try {
		    	 TaskPage tp1=new TaskPage(driver);
				 ButtonHelper.click(tp1.SaveCreatedTask, "Save button");
				 WebDriverWait wait = new WebDriverWait(driver, 50);
				 wait.until(ExpectedConditions.alertIsPresent());
				 driver.switchTo().alert().accept();
				 Thread.sleep(10000);
				 driver.navigate().refresh();
				 TaskPage tp2=new TaskPage(driver);
				 Thread.sleep(5000);
				 TextBoxHelper.enterTextString(tp2.DateFilter, "Filter Date",ExcelReader.ReadTestData("duedate") );
				 Thread.sleep(5000);
	    	}catch(Exception e) {
		    	e.printStackTrace();
		    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		    }
	    }
	 
	    public static void DeleteTaskWithoutMessage() {
	    	try {
	    		 TaskPage tp=new TaskPage(driver);
	    		 tp.DeleteTask.click();
	    		 Thread.sleep(5000);
	    		 WebDriverWait wait = new WebDriverWait(driver, 50);
				 wait.until(ExpectedConditions.alertIsPresent());
				 driver.switchTo().alert().accept();
				 wait.until(ExpectedConditions.alertIsPresent());
				 driver.switchTo().alert().accept();
	    	}catch(Exception e) {
		    	e.printStackTrace();
		    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		    }
	    }
	
	public static void CreateTask(String Taskname, String Description,String dueDate) {
		try {  
			 BasicCreateTaskHalfHelper(Taskname,Description,dueDate);
			 SaveTaskAndApplyFilter();
			 TaskPage tp=new TaskPage(driver);
			if(tp.verifyTaskName.getText().equalsIgnoreCase(Taskname)) {
				test.log(LogStatus.PASS, "User was able to create new task successfully");	
				DeleteTaskWithoutMessage();
			}else{
				test.log(LogStatus.FAIL, "User was not able to create new task");	
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
		                                                                                                                                      
	public static void CreateTaskWithMissingFields(String Taskname, String Description,String dueDate,String fieldname){
		try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			Thread.sleep(4000);
			if(fieldname!="TaskName")
				TextBoxHelper.enterTextString(tp.TaskName,"Task Name", Taskname);
			if(fieldname!="ServiceEngineer") {
				DropDownHelper.selectRandomElementByIndex(tp.ServiceEngineer,"service engineer");
				tp=new TaskPage(driver);
				ButtonHelper.click(tp.EnabledWatcher, "watcher button");			
				DropDownHelper.selectRandomElementFromDivList(tp.WatcherList,"watcher",0);
				Thread.sleep(2000);
				for(int i=1;i<=5;i++) {				
				try {
					if(driver.switchTo().alert().getText().equalsIgnoreCase("The assignee cannot be a watcher")) {
					driver.switchTo().alert().accept();
					tp=new TaskPage(driver);
					Thread.sleep(2000);								
					DropDownHelper.selectRandomElementFromDivList(tp.WatcherList,"watcher",0);
					}
				}catch(NoAlertPresentException e) {	
					break;
				}
				}
			}
			if(fieldname!="SelectedAccount")
				DropDownHelper.selectRandomElementByIndex(tp.SelectedAccount,"Account");
			if(fieldname!="DueDate")
				TextBoxHelper.enterTextString(tp.DueDate,"Due Date", dueDate);						
			if(fieldname!="TaskType")
				DropDownHelper.selectRandomElementByIndex(tp.TaskType,"Task Type");
			if(fieldname=="Machines") {
				ButtonHelper.click(tp.Enabledmachine, "Machines button");			
				DropDownHelper.selectElementByIndexFromDivList(tp.MachineList,"Machines",1);
			}				
			TextBoxHelper.enterTextString(tp.TaskDescription,"Description", Description);
			String screenshotPath = ExtentReportHelper.getScreenshot();
			test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
			ButtonHelper.click(tp.ScheduleNoneBtn, "schedule none button");	
			
			ButtonHelper.click(tp.SaveCreatedTask, "Save button");					
			WebDriverWait wait = new WebDriverWait(driver, 50);
		 	  wait.until(ExpectedConditions.alertIsPresent());
			  String msg = driver.switchTo().alert().getText();
			if(msg.equalsIgnoreCase("This service engineer does not exist.")||msg.equalsIgnoreCase("Due date should be greater than current date.")||msg.equalsIgnoreCase("Task name required")||msg.equalsIgnoreCase("This account does not exist."))				
				test.log(LogStatus.PASS, "error message '"+msg+ "' was displayed when user missed a mandatory field while creating task");
		    else			
				test.log(LogStatus.FAIL, "No error message was displayed when user missed a mandatory field while creating task");
			driver.switchTo().alert().accept();
			
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void CreateTaskWithThreeMissingFields(String Taskname, String Description,String dueDate){
		try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			Thread.sleep(4000);
			TextBoxHelper.enterTextString(tp.TaskName,"Task Name", Taskname);
			TextBoxHelper.enterTextString(tp.DueDate,"Due Date", dueDate);
			Thread.sleep(2000);			
			TextBoxHelper.enterTextString(tp.TaskDescription,"Description", Description);
			DropDownHelper.selectRandomElementByIndex(tp.TaskType,"TaskType");				
			ButtonHelper.click(tp.ScheduleNoneBtn, "schedule none button");
			ButtonHelper.click(tp.SaveCreatedTask, "Save button");
			Thread.sleep(4000);
			if(driver.switchTo().alert().getText().equalsIgnoreCase("This service engineer does not exist."))
				test.log(LogStatus.PASS, "Error message was dispalyed when no service engineer,Accounts and machines are not added ");	
			else
				test.log(LogStatus.FAIL, " No Error message was dispalyed when service engineer, Accounts and machines are not added ");
			driver.switchTo().alert().accept();
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void VerifySelectedFeildIsShowing(String FieldName) {
		try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			String data=null,data2=null;
			boolean present=false;
			if(FieldName=="ServiceEngineer") {
				data=DropDownHelper.selectRandomElementByIndexAndGetText(tp.ServiceEngineer,"service engineer");			
			    data2=tp.ServiceEngineer.getAttribute("value");
			    present=tp.ServiceEngineer.isDisplayed(); 
			}
			if(FieldName=="Account") {
				data=DropDownHelper.selectRandomElementByIndexAndGetText(tp.SelectedAccount,"Account");			
			   present=tp.SelectedAccount.isDisplayed();
			}				
			
		    if(present)
		    		test.log(LogStatus.PASS, FieldName+ " was selected and selected "+FieldName+ " is visible");	
			else
				test.log(LogStatus.FAIL, " Either "+FieldName+" was not selected or it is not visible after selection  ");		    		
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void VerifyMachinesFeildIsShowing() {
		try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			DropDownHelper.selectRandomElementByIndex(tp.SelectedAccount,"account");
			Thread.sleep(6000);
			ButtonHelper.click(tp.Enabledmachine, "Machines button");	
			DropDownHelper.selectElementByIndexFromDivList(tp.MachineList,"Machines",1);
			DropDownHelper.selectRandomElementFromDivList(tp.MachineList,"Machines",1);			
		    String data=tp.MachinesFeildTitle.getAttribute("title");
		    System.out.println(data);
		    String data2=tp.MachineOneActiveFields.getText();
		    
		    if(data.contains(data2))
	    		test.log(LogStatus.PASS,  "Machines were selected and selected machines are visible");	
		    else
			test.log(LogStatus.FAIL, " Either machines were not selected or machines are not visible after selection ");
			
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void WatcherDisabled(String Taskname, String Description,String dueDate) {
		try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			Thread.sleep(4000);
			TextBoxHelper.enterTextString(tp.TaskName,"Task Name", Taskname);
			DropDownHelper.selectRandomElementByIndex(tp.SelectedAccount,"Account");
			TextBoxHelper.enterTextString(tp.DueDate,"Due Date", dueDate);					
			TextBoxHelper.enterTextString(tp.TaskDescription,"Description", Description);
			DropDownHelper.selectRandomElementByIndex(tp.TaskType,"Task Type");
			ButtonHelper.click(tp.ScheduleNoneBtn, "schedule none button");
			String screenshotPath = ExtentReportHelper.getScreenshot();
			test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
			if(tp.DisabledWatchers.isEnabled())
				test.log(LogStatus.FAIL, "Watchers field is enabled Even if service engineer is not select");	
			else
				test.log(LogStatus.PASS, "Watchers field is Disabled if service engineer is not select");	
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void MachinesDisabled(String Taskname, String Description,String dueDate) {
		try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			Thread.sleep(4000);
			TextBoxHelper.enterTextString(tp.TaskName,"Task Name", Taskname);
			DropDownHelper.selectRandomElementByIndex(tp.ServiceEngineer,"Service Engineer");
			TextBoxHelper.enterTextString(tp.DueDate,"Due Date", dueDate);					
			TextBoxHelper.enterTextString(tp.TaskDescription,"Description", Description);
			DropDownHelper.selectRandomElementByIndex(tp.TaskType,"Task Type");
			ButtonHelper.click(tp.ScheduleNoneBtn, "schedule none button");
			String screenshotPath = ExtentReportHelper.getScreenshot();
			test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
			if(tp.DisabledWatchers.isEnabled())
				test.log(LogStatus.FAIL, "Machines field is enabled Even if Account is not select");	
			else
				test.log(LogStatus.PASS, "Machines field is Disabled if Account is not select");	
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
    public static void SelectSameWatcherAndServiceEngineer() {
    	try {   
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			int index=DropDownHelper.selectRandomElementByIndex(tp.ServiceEngineer,"Service Engineer");
			tp=new TaskPage(driver);
			ButtonHelper.click(tp.EnabledWatcher, "watcher button");			
			DropDownHelper.selectElementByIndexFromDivList(tp.WatcherList,"watcher", index-1);
			Thread.sleep(3000);
		    if(driver.switchTo().alert().getText().equalsIgnoreCase("The assignee cannot be a watcher"))
	    		test.log(LogStatus.PASS,  "User is not able to select same service engineer and watcher");	
		    else
				test.log(LogStatus.FAIL, "User is able to select same service engineer and watcher");
					
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
    
    
    
    public static void VerifyMachineName() {
    	try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			Thread.sleep(1000);
			DropDownHelper.selectRandomElementByIndex(tp.SelectedAccount,"Account");
			Thread.sleep(3000);
			ButtonHelper.click(tp.Enabledmachine, "machine button");
			String machineName=null;
			if(DropDownHelper.SizeOfDivList(tp.MachineList)==1||DropDownHelper.SizeOfDivList(tp.MachineList)==1) {
				machineName=tp.verifySelectedMachine.getText();
				test.log(LogStatus.INFO,  "For the selected account no machines Are avilable");
			}
			else if(DropDownHelper.SizeOfDivList(tp.MachineList)==2) {			
				DropDownHelper.selectElementByIndexFromDivList(tp.MachineList,"Machines",0);
				machineName=DropDownHelper.selectElementByIndexFromDivListGetText(tp.MachineList,"Machines",1);
			}else {
				DropDownHelper.selectElementByIndexFromDivList(tp.MachineList,"Machines",0);
				machineName=DropDownHelper.selectRandomElementFromDivListAndGetText(tp.MachineList,"Machines",0);
			}				
			if(machineName.equalsIgnoreCase(tp.verifySelectedMachine.getText()))
				test.log(LogStatus.PASS,  "Selected machine name displayed is correct");	
		    else
				test.log(LogStatus.FAIL, "Selected machine name displayed is not correct");
    	}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
    }
    
    public static void VerifymultipleMachinesCanBeSelected(String Taskname, String Description,String dueDate, String numberofMachine) {
		try {  						
			BasicCreateTaskHalfHelper(Taskname,Description,dueDate);
			TaskPage tp=new TaskPage(driver);
			Thread.sleep(3000);
			ButtonHelper.click(tp.Enabledmachine, "Machines button");	
			if(tp.MachineList.size()<=1){
				test.log(LogStatus.INFO, "For the selected account no machines are available to Select");
			}else {
				if(numberofMachine.equalsIgnoreCase("one")) {
					DropDownHelper.selectElementByIndexFromDivList(tp.MachineList,"Machines",0);
					Thread.sleep(3000);
					DropDownHelper.selectRandomElementFromDivList(tp.MachineList,"Machines",1);	
					String data=tp.MachinesFeildTitle.getAttribute("title");
				    System.out.println(data);
				    String data2=tp.MachineOneActiveFields.getText();		    
				    if(data.contains(data2))
			    		test.log(LogStatus.PASS, "User is able to select only One Machine and selected machine is visible");	
				    else
				    	test.log(LogStatus.FAIL, " Either machine was not selected or selected machine is not visible after selection ");				
				}
				
				if(numberofMachine.equalsIgnoreCase("multiple"))
				{
					DropDownHelper.selectElementByIndexFromDivList(tp.MachineList,"Machines",0);
					Thread.sleep(1000);
					String screenshotPath = ExtentReportHelper.getScreenshot();
					test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));				
					if(tp.MachineList.size()==2) {
						test.log(LogStatus.INFO, "For the selected account only one machine is available to Select");
					}else{
						DropDownHelper.selectRandomElementFromDivList(tp.MachineList,"machine", 1);
						DropDownHelper.selectRandomElementFromDivList(tp.MachineList,"machine", 1);
						int size1=tp.MachineActiveFieldsList.size();
						if(size1>1)
							test.log(LogStatus.PASS, "User is able to select multiple Machines and selected machines are visible");	
					    else
							test.log(LogStatus.FAIL, " Either multiple the machines were not selected or machines are not visible after selection ");
					}
				}
				
				if(numberofMachine.equalsIgnoreCase("All")) {
					DropDownHelper.selectElementByIndexFromDivList(tp.MachineList,"Machines",1);
					Thread.sleep(1000);
					DropDownHelper.selectElementByIndexFromDivList(tp.MachineList,"Machines",1);
					int size1=tp.MachineActiveFieldsList.size();
					int size2=tp.MachineList.size();
					size1++;
					if(size1==size2)
						test.log(LogStatus.PASS, "User is able to select All Machines and selected machines are visible");	
				    else
						test.log(LogStatus.FAIL, " Either all the machines were not selected or machines are not visible after selection ");
				}
			}
			SaveTaskAndApplyFilter();
			if(tp.verifyTaskName.getText().equalsIgnoreCase(Taskname)) {
				test.log(LogStatus.PASS, "User was able to create new task successfully");
				DeleteTaskWithoutMessage();
			}else{
				test.log(LogStatus.FAIL, "User was not able to create new task");	
			}			
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
    public static void AllFourTaskTypeAreDisplayed() {
    	try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			for(int i=1;i<DropDownHelper.sizeOfSelect(tp.TaskType, "Task Type");i++) {
				switch(i) {
				case 1:
					String data1=DropDownHelper.selectElementByIndexAndGetText(tp.TaskType, "Task Type", i);								
					if(data1.contains("Solcare Service"))
						test.log(LogStatus.PASS, "Solcare service is present in the	TaskType DropDown field");	
				    else
					test.log(LogStatus.FAIL, "Solcare service is not present in the	TaskType DropDown field");
					break;
				case 2:
					String data2=DropDownHelper.selectElementByIndexAndGetText(tp.TaskType, "Task Type", i);								
					if(data2.contains("Oil Skimming"))
						test.log(LogStatus.PASS, "Oil Skimming is present in the TaskType DropDown field");	
				    else
					test.log(LogStatus.FAIL, "Oil Skimming is not present in the TaskType DropDown field");
					break;
				case 3:
					String data3=DropDownHelper.selectElementByIndexAndGetText(tp.TaskType, "Task Type", i);								
					if(data3.contains("Sump Cleaning"))
						test.log(LogStatus.PASS, "Sump Cleaning is present in the TaskType DropDown field");	
				    else
					test.log(LogStatus.FAIL, "Sump Cleaning is not present in the TaskType DropDown field");
					break;
				case 4:
					String data4=DropDownHelper.selectElementByIndexAndGetText(tp.TaskType, "Task Type", i);								
					if(data4.contains("Bacteria/Fungi Check"))
						test.log(LogStatus.PASS, "Bacteria/Fungi Check is present in the TaskType DropDown field");	
				    else
					test.log(LogStatus.FAIL, "Bacteria/Fungi Check not present in the TaskType DropDown field");
					break;
				}
			}
    	}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
    }

    public static void CreateTaskWithDifferentTaskTypes(String Taskname, String Description,String dueDate,int TaskTypeIndex) {
		try {  
			BasicCreateTaskHalfHelper(Taskname, Description, dueDate);
			TaskPage tp=new TaskPage(driver);
			String data=null;
			if(TaskTypeIndex==1)
				data=DropDownHelper.selectElementByIndexAndGetText(tp.TaskType, "Task Type", 1);
			if(TaskTypeIndex==2)
				data=DropDownHelper.selectElementByIndexAndGetText(tp.TaskType, "Task Type", 2);
			if(TaskTypeIndex==3)
				data=DropDownHelper.selectElementByIndexAndGetText(tp.TaskType, "Task Type", 3);
			if(TaskTypeIndex==4)
				data=DropDownHelper.selectElementByIndexAndGetText(tp.TaskType, "Task Type", 4);
			ButtonHelper.click(tp.ScheduleNoneBtn, "schedule none button");
			String screenshotPath = ExtentReportHelper.getScreenshot();
			test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
			SaveTaskAndApplyFilter();
			if(tp.verifyTaskName.getText().equalsIgnoreCase(Taskname)) {
				test.log(LogStatus.PASS, "User was able to create new task successfully With TaskType "+data);
				DeleteTaskWithoutMessage();
			}else{
				test.log(LogStatus.FAIL, "User was not able to create new task With TaskType "+data);	
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
    
    public static void NoneButtonIsSelectedByDefault() {
    	try {   
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");			
		    if(tp.ScheduleNoneBtn.getAttribute("class").contains("selected"))
	    		test.log(LogStatus.PASS,  "Schedule None Button Is PreSelected By Default");	
		    else
				test.log(LogStatus.FAIL, "Schedule None Button Is Not PreSelected By Default");
					
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
        
    public static void EndDateFieldDisplayed(String Taskname, String Description,String dueDate) {
    	try {   				
			BasicCreateTaskHalfHelper(Taskname,Description,dueDate);
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(tp.ScheduleDailyBtn, "Schedule Daily Button");
			Thread.sleep(2000);
		    if(tp.taskRepeatEndDate.isDisplayed())
	    		test.log(LogStatus.PASS,  "Selecting Schedule Daily Button Select End Date Field Is Displayed");	
		    else
				test.log(LogStatus.FAIL, "Selecting Schedule Daily Button Select End Date Field Is Not Displayed");
		    DropDownHelper.selectRandomElementByIndex(tp.taskRepeatEndDate,"Task Repeat End Date");
		    String screenshotPath = ExtentReportHelper.getScreenshot();
			test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
		    SaveTaskAndApplyFilter();
			if(tp.verifyTaskName.getText().equalsIgnoreCase(Taskname)) {
				test.log(LogStatus.PASS, "User was able to create new task successfully");	
				DeleteTaskWithoutMessage();
			}else {
				test.log(LogStatus.FAIL, "User was not able to create new task");	
			}					
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
    
    public static void selectScheduleWeekly(String Taskname, String Description,String dueDate) {
    	try {   			
			BasicCreateTaskHalfHelper(Taskname,Description,dueDate);
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(tp.ScheduleWeeklyBtn, "Schedule weekly Button");
			Thread.sleep(3000);
		    if(tp.WeeklytaskRepeatEndDate.isDisplayed())
	    		test.log(LogStatus.PASS,  "Selecting Schedule weekly Button Select End Date Field Is Displayed");	
		    else
				test.log(LogStatus.FAIL, "Selecting Schedule weekly Button Select End Date Field Is Not Displayed");
		    if(tp.selectWeek.isDisplayed())
	    		test.log(LogStatus.PASS,  "Selecting Schedule weekly Button Select Week Field Is Displayed");	
		    else
				test.log(LogStatus.FAIL, "Selecting Schedule weekly Button Select Week Field Is Not Displayed");
		    if(tp.dayOfTheWeek.isDisplayed())
	    		test.log(LogStatus.PASS,  "Selecting Schedule weekly Button Select Day Of Week Field Is Displayed");	
		    else
				test.log(LogStatus.FAIL, "Selecting Schedule weekly Button Select Day Of Week Field Is Not Displayed");
			String data=tp.selectWeek.getText();
			if(data.contains("Every week")&&data.contains("Alternate week"))
	    		test.log(LogStatus.PASS,  "Selecting Schedule weekly Button Select week dropdown contains both Every week And Alternate week options");	
		    else
				test.log(LogStatus.FAIL, "Selecting Schedule weekly Button Select week does not contain both Every week And Alternate week options");
			String data2=DropDownHelper.selectRandomElementByIndexAndGetText(tp.selectWeek, "select week");
			if(data2.equalsIgnoreCase("Every week")||data2.equalsIgnoreCase("Alternate week"))
	    		test.log(LogStatus.PASS,  "Use is able to select alternative week or every week as schedule");	
		    else
				test.log(LogStatus.FAIL, "Use is not able to select alternative week or every week as schedule");
			DropDownHelper.selectRandomElementByIndex(tp.selectWeek,"task repeat fequency every week or altenate week");
			tp=new TaskPage(driver);
			ButtonHelper.click(tp.Fri, "Day of thr week");
			DropDownHelper.selectRandomElementByIndex(tp.WeeklytaskRepeatEndDate,"Task Repeat End Date");			
			String screenshotPath = ExtentReportHelper.getScreenshot();
			test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
			SaveTaskAndApplyFilter();			
			if(tp.verifyTaskName.getText().equalsIgnoreCase(Taskname)) {
				test.log(LogStatus.PASS, "User was able to create new task successfully");	
				DeleteTaskWithoutMessage();
			}else {
				test.log(LogStatus.FAIL, "User was not able to create new task");	
			}					
		}catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
    
    public static void selectScheduleMonthly(String Taskname, String Description,String dueDate){
    	try {   
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			ButtonHelper.click(tp.ScheduleMonthlyBtn, "Schedule Monthly Button");
			Thread.sleep(3000);
		    if(tp.MonthlytaskRepeatEndDate.isDisplayed())
	    		test.log(LogStatus.PASS,  "Selecting Schedule Monthly Button Select End Date Field Is Displayed");	
		    else
				test.log(LogStatus.FAIL, "Selecting Schedule Monthly Button Select End Date Field Is Not Displayed");
		    if(tp.selectMonth.isDisplayed())
	    		test.log(LogStatus.PASS,  "Selecting Schedule Monthly Button Select month Field Is Displayed");	
		    else
				test.log(LogStatus.FAIL, "Selecting Schedule Monthly Button Select month Field Is Not Displayed");
			String data=tp.selectMonth.getText();
			if(data.contains("Every month")&&data.contains("Alternate month"))
	    		test.log(LogStatus.PASS,  "Selecting Schedule Monthly Button Select month dropdown contains both Every month And Alternate month options");	
		    else
				test.log(LogStatus.FAIL, "Selecting Schedule Monthly Button Select month does not contain both Every month And Alternate month options");
			String data2=DropDownHelper.selectRandomElementByIndexAndGetText(tp.selectMonth, "select month");
			if(data2.equalsIgnoreCase("Every month")||data2.equalsIgnoreCase("Alternate month"))
	    		test.log(LogStatus.PASS,  "Use is able to select alternative month or every month as schedule");	
		    else
				test.log(LogStatus.FAIL, "Use is not able to select alternative month or every month as schedule");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
    
    public static void selectScheduleMonthlSelectmonth(String Taskname, String Description,String dueDate,boolean everyMonth) {
    	try {    			
			BasicCreateTaskHalfHelper(Taskname,Description,dueDate);
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(tp.ScheduleMonthlyBtn, "Schedule monthly Button");
			if(everyMonth)
				DropDownHelper.selectElementByIndex(tp.selectMonth,"every month", 1);
			else
				DropDownHelper.selectElementByIndex(tp.selectMonth,"Alternate month", 2);
			boolean check=false;
			for(int i=1;i<=31;i++) {
				String s=String.valueOf(i);
				
				if(tp.selectDateList.get(0).getText().contains(s))
					check=true;
			}
			if(check)
				test.log(LogStatus.PASS,  "Use is able to see all the dates after selecting schedule every month");	
		    else
				test.log(LogStatus.FAIL, "Use is not able to see all the dates after selecting schedule every month");
			DropDownHelper.selectRandomElementByIndex(tp.selectDate, "Month repeat date");
			DropDownHelper.selectRandomElementByIndex(tp.MonthlytaskRepeatEndDate, "task Repeat End Date");
			SaveTaskAndApplyFilter();
			if(tp.verifyTaskName.getText().equalsIgnoreCase(Taskname)) {
				test.log(LogStatus.PASS, "User was able to create new task successfully");	
				DeleteTaskWithoutMessage();
			}else {
				test.log(LogStatus.FAIL, "User was not able to create new task");	
			}					
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
    
    
    public static void DownloadExcelInstructionPopUp() {
    	try { 
    		HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.DownloadExcel,"Download Excel Button");
			Thread.sleep(1000);
			if(tp.CloseDownloadExcel.isDisplayed()&&tp.DownloadSampleExcelonPopUP.isDisplayed())
				test.log(LogStatus.PASS,  "On Clicking Download Excel an instruction pop up window is displayed ");	
		    else
				test.log(LogStatus.FAIL, "On Clicking Download Excel No instruction pop up window is displayed ");
    	}catch(Exception e) {
    		e.printStackTrace();
    		test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
    	}
    }
    
    public static void CloseDownloadExcelInstructionPopUp(){
    	try { 
    		HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.DownloadExcel,"Download Excel Button");
			Thread.sleep(1000);
			ButtonHelper.click(tp.CloseDownloadExcel, "close Download Excel Instruction Pop Up icon");
			if(tp.createTask.isDisplayed()&&tp.DownloadExcel.isDisplayed())
				test.log(LogStatus.PASS,  "On Clicking close Icon on Download Excel instruction pop up window the Pop up window is closed");	
		    else
				test.log(LogStatus.FAIL, "On Clicking close Icon on Download Excel instruction pop up window the pop up window is not closed");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
	}
    
    public static void DownloadSampleExcel(){
    	try {
    		HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.DownloadExcel,"Download Excel Button");
			Thread.sleep(1000);
			ButtonHelper.click(tp.DownloadSampleExcelonPopUP,"Download Sample Excel Button on popup window");
			Thread.sleep(30000);
			int num=GenericElements.NumberOfFilesInPresentInFolder(System.getProperty("user.home")+"\\Downloads");
			boolean check=false;
			for(int i=1;i<=num;i++)
			{
				if(GenericElements.isFileDownloaded(System.getProperty("user.home")+"\\Downloads",ExcelReader.ReadTestData("name_download_sample_excel_taskpage")+"."+ExcelReader.ReadTestData("extention_download_sample_excel_taskpage"))) {
					check=true;
					break;
				}else{
					check=GenericElements.isFileDownloaded(System.getProperty("user.home")+"\\Downloads",ExcelReader.ReadTestData("name_download_sample_excel_taskpage")+"("+i+")."+ExcelReader.ReadTestData("extention_download_sample_excel_taskpage"));
					if(check)
						break;
				}
			}
			if(check) {				
				test.log(LogStatus.PASS, "Use Is Able To Download a Sample Excel File After Clicking Download sample excel button");	
			}else {
				test.log(LogStatus.FAIL, "Use Is Not Able To Download a Sample Excel File After Clicking Download sample excel button");
				test.log(LogStatus.INFO, "Name And extension of the downloded file should be same as present in test data");
			}
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
	}
    
    
    public static void uploadExcelDisplayed() {
    	try {
    		HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.UploadExcelButton, "Upload Excel Button on TaskPage");
			Thread.sleep(1000);
			if(tp.UploadExcelInput.isDisplayed()&&tp.UploadExcelButtonPopup.isDisplayed())
				test.log(LogStatus.PASS,  "On Clicking upload Excel an pop up window is displayed which has choose File and upload option ");	
		    else
				test.log(LogStatus.FAIL, "On Clicking upload Excel No pop up window is displayed ");
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    
    public static void uploadExcelIsDisabled() {
    	try {
    		HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.UploadExcelButton, "Upload Excel Button on TaskPage");
			Thread.sleep(1000);
			if(tp.UploadExcelButtonPopup.isEnabled())
				test.log(LogStatus.FAIL,  "On Clicking upload Excel an pop up window is displayed and upload option is enabled even if no file is uploaded ");	
		    else
				test.log(LogStatus.PASS, "On Clicking upload Excel an pop up window is displayed and upload option is disabled if no file is uploaded  ");
			tp.UploadExcelInput.sendKeys(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\task_sample.xlsx");
			if(tp.UploadExcelButtonPopup.isEnabled())
				test.log(LogStatus.PASS,  "upload option is enabled after uploading a file");	
		    else
				test.log(LogStatus.FAIL, "upload option is still disabled even after uploading a file");
			
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void preAvailbleServiceEingeer() {
    	try {
    		HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			AddServiceEngineerPage se = new AddServiceEngineerPage(driver);
			ButtonHelper.click(hps.serviceEngineersWatcher, "service Engineers/Watcher Button on sidebar");
			String elementText="";
			String abc="";
			for(int i =0;i<se.AllSeviceEngineersNames.size();i++) {
				 elementText =se.AllSeviceEngineersNames.get(i).getText(); 
				 System.out.println(elementText); 
				}			
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			ButtonHelper.click(tp.ServiceEngineer, "Service Engineer dropDown");			
			for(int i =0;i<tp.listServiceEngineer.size();i++) {
				  abc =tp.listServiceEngineer.get(i).getText(); 
				 System.out.println(abc); 
				}
			if(abc.contains(elementText))
				test.log(LogStatus.PASS,  "All the Service engineer which are present before creating task are visible to vendor while creating the task ");	
		    else
				test.log(LogStatus.FAIL, "All the Service engineer which are present before creating task are not visible to vendor while creating the task ");		
			
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void preAvailbleAccount() {
    	try {
    		HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			AccountPage ac = new AccountPage(driver);
			ButtonHelper.click(hps.accounts, "accounts Button on sidebar");
			String elementText="";
			String abc="";
			for(int i =0;i<ac.AllAccountNames.size();i++) {
				 elementText = ac.AllAccountNames.get(i).getText(); 
				 System.out.println(elementText); 
				}			
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			ButtonHelper.click(tp.SelectedAccount, "Account dropDown");			
			for(int i =0;i<tp.ListAccount.size();i++) {
				  abc =tp.ListAccount.get(i).getText(); 
				 System.out.println(abc); 
				}
			if(abc.contains(elementText))
				test.log(LogStatus.PASS,  "All the accounts which are present before creating task are visible to vendor while creating the task ");	
		    else
				test.log(LogStatus.FAIL, "All the accounts which are present before creating task are not visible to vendor while creating the task ");
			
			
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    public static void FillSampleExcel(){ 
    	try {
    		HomePageSideBar hps = new HomePageSideBar(driver);	
			AddMachinesPage mp = new AddMachinesPage(driver);
			AddServiceEngineerPage se = new AddServiceEngineerPage(driver);
			ButtonHelper.click(hps.machines, "Machines Button on sidebar");
			int num =ThreadLocalRandom.current().nextInt(0,mp.AccountNames.size());
			String elementText=mp.AccountNames.get(num).getText();
			ExcelReader.updateSampleExcel("data","Account_name",elementText);
			ExcelReader.updateSampleExcel("data","Machine_unique_code",mp.MachineUniqueCode.get(num).getText());			
			ButtonHelper.click(hps.serviceEngineersWatcher, "Service Engineers/Watcher Button on sidebar");
			int num1 =ThreadLocalRandom.current().nextInt(0,se.AllSeviceEngineersNames.size());
			String elementText1=se.AllSeviceEngineersNames.get(num1).getText();
			ExcelReader.updateSampleExcel("data","Watchers",elementText1);
			int num2 =ThreadLocalRandom.current().nextInt(0,se.AllSeviceEngineersNames.size());
			for(int i=1;i<=6;i++) {
				if(num1==num2)
					num2 =ThreadLocalRandom.current().nextInt(0,se.AllSeviceEngineersNames.size());
				else
					break;
			}			
			String elementText2=se.AllSeviceEngineersPhone.get(num2).getText();
			ExcelReader.updateSampleExcel("data","Service_engineer_number",elementText2);		
			ExcelReader.updateSampleExcel("data","Description",ExcelReader.ReadTestData("taskdescription"));
			ExcelReader.updateSampleExcel("data","Task_name",ExcelReader.ReadTestData("taskname"));					
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
			LocalDateTime now = LocalDateTime.now(); 
			LocalDateTime FutureDate = now.plusDays(4);
			ExcelReader.updateSampleExcel("data","Due_date",dtf.format(FutureDate));
			System.out.println(dtf.format(FutureDate));
    	}catch(Exception e){
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    	
    }
    public static void UploadSampleExcel(){ 
    	try {
    		HomePageSideBar hps = new HomePageSideBar(driver);	
    		ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(tp.UploadExcelButton, "Upload Excel Button on TaskPage");
			Thread.sleep(1000);
			tp.UploadExcelInput.sendKeys(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\task_sample.xlsx");
			ButtonHelper.click(tp.UploadExcelButtonPopup, "Upload Excel Button on pop up");
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();	
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
        
    public static void uploadMissingFeildExcel(String NameOfMissingFeild) {
    	try {
    		FillSampleExcel();   		
			if(NameOfMissingFeild.equalsIgnoreCase("account"))
				ExcelReader.updateSampleExcel("data","Account_name","");
			if(NameOfMissingFeild.equalsIgnoreCase("dueDate"))
				ExcelReader.updateSampleExcel("data","Due_date","");
			if(NameOfMissingFeild.equalsIgnoreCase("machinecode"))
				ExcelReader.updateSampleExcel("data","Machine_unique_code","");
			if(NameOfMissingFeild.equalsIgnoreCase("serviceengineerphone"))
				ExcelReader.updateSampleExcel("data","Service_engineer_number","");
			if(NameOfMissingFeild.equalsIgnoreCase("TaskName"))
				ExcelReader.updateSampleExcel("data","Task_name","");
			UploadSampleExcel();
			TaskPage tp=new TaskPage(driver);
			if(tp.TaskFailLog.isDisplayed()) {
				test.log(LogStatus.PASS,  "Error Message With Reason Is Displayed When Empty Excel File Is Uploded By the User");
				test.log(LogStatus.INFO,  " Reason Is Displayed Is"+tp.TaskFailLog.getText());
				
			}else {
				test.log(LogStatus.FAIL, "No Error message Is Displayed When User Uploaded An Empty Excel");
			}
			
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void uploadEmptyExcel() {
    	try {   		
			ExcelReader.updateSampleExcel("data","Account_name","");
			ExcelReader.updateSampleExcel("data","Service_engineer_number","");	
			ExcelReader.updateSampleExcel("data","Machine_unique_code","");						
			ExcelReader.updateSampleExcel("data","Watchers","");							
			ExcelReader.updateSampleExcel("data","Description","");
			ExcelReader.updateSampleExcel("data","Task_name","");									
			ExcelReader.updateSampleExcel("data","Due_date","");
			UploadSampleExcel();
			TaskPage tp=new TaskPage(driver);			
			if(tp.TaskFailLog.isDisplayed()) {
				test.log(LogStatus.PASS,  "Error Message With Reason Is Displayed When Empty Excel File Is Uploded By the User");
				test.log(LogStatus.INFO,  " Reason Displayed Is : "+tp.TaskFailLog.getText());
				
			}else {
				test.log(LogStatus.FAIL, "No Error message Is Displayed When User Uploaded An Empty Excel");
			}
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void uploadExcel(String Taskname1,String dueDate1) {
    	try {
    		FillSampleExcel();   					
			UploadSampleExcel();					  
				
				TaskPage tp=new TaskPage(driver);
				Thread.sleep(2000);
				TextBoxHelper.enterTextString(tp.DateFilter, "Filter Date", dueDate1);
				Thread.sleep(2000);
				if(tp.verifyTaskName.getText().equalsIgnoreCase(Taskname1)) {
					test.log(LogStatus.PASS, "User was able to create new task successfully");	
					Thread.sleep(2000);
					WebDriverWait wait = new WebDriverWait(driver, 50);	
					tp.DeleteTask.click();			
					wait.until(ExpectedConditions.alertIsPresent());
					driver.switchTo().alert().accept();
					wait.until(ExpectedConditions.alertIsPresent());
					driver.switchTo().alert().accept();
				}else {
					test.log(LogStatus.FAIL, "User was not able to create new task");	
				}							
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void uploadExcelWithDifferentValue(String Value) {
    	try {
    		FillSampleExcel();  
    		if(Value.equalsIgnoreCase("Account_name"))
				ExcelReader.updateSampleExcel("data","Account_name","Mtestaccount");			
			if(Value.equalsIgnoreCase("Service_engineer_number"))
				ExcelReader.updateSampleExcel("data","Service_engineer_number",ExcelReader.ReadTestData("original_edit_profile_user_contact_num"));
			if(Value.equalsIgnoreCase("Watcher"))
				ExcelReader.updateSampleExcel("data","Watchers","Testbot");
			UploadSampleExcel();
			TaskPage tp=new TaskPage(driver);
			if(tp.TaskFailLog.isDisplayed()) {
				test.log(LogStatus.PASS,  "Error Message With Reason Is Displayed When "+Value+" field in excel contains "+Value+" which is not already exixting on website");
				test.log(LogStatus.INFO,  " Reason Displayed Is : "+tp.TaskFailLog.getText());				
			}else {
				test.log(LogStatus.FAIL, "No Error message Is Displayed When User Uploaded An Empty Excel");
			}
			  			
    	}catch(Exception e){
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }   
    }
    
 
    public static void SameServiceEngineer() {
    	try {
    		FillSampleExcel();  
    		HomePageSideBar hps = new HomePageSideBar(driver);	
			AddServiceEngineerPage se = new AddServiceEngineerPage(driver);
			ButtonHelper.click(hps.serviceEngineersWatcher, "Service Engineers/Watcher Button on sidebar");
    		int num1 =ThreadLocalRandom.current().nextInt(0,se.AllSeviceEngineersNames.size());
			String elementText1=se.AllSeviceEngineersNames.get(num1).getText();
			ExcelReader.updateSampleExcel("data","Watchers",elementText1);
			String elementText2=se.AllSeviceEngineersPhone.get(num1).getText();
			ExcelReader.updateSampleExcel("data","Service_engineer_number",elementText2);	
			UploadSampleExcel();
			TaskPage tp=new TaskPage(driver);
			if(tp.TaskFailLog.isDisplayed()) {
				test.log(LogStatus.PASS,  "Error Message With Reason Is Displayed When User Upload Excel which Has Same Service Engineer And Watcher");
				test.log(LogStatus.INFO,  " Reason Displayed Is : "+tp.TaskFailLog.getText());				
			}else {
				test.log(LogStatus.FAIL, "Error Message With Reason Is Displayed When User Upload Excel which Has Same Service Engineer And Watcher");
			}
			  			
    	}catch(Exception e){
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }   
    }
    public static void checkDueDate() {
    	try {
    		FillSampleExcel();  
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
			LocalDateTime now = LocalDateTime.now(); 
			LocalDateTime Pastdate = now.plusDays(-4);
			ExcelReader.updateSampleExcel("data","Due_date",dtf.format(Pastdate));
			UploadSampleExcel();
			TaskPage tp=new TaskPage(driver);
			if(tp.TaskFailLog.isDisplayed()) {
				test.log(LogStatus.PASS,  "Error Message With Reason Is Displayed When User Upload Excel with past date");
				test.log(LogStatus.INFO,  " Reason Displayed Is : "+tp.TaskFailLog.getText());				
			}else {
				test.log(LogStatus.FAIL, "Error Message With Reason Is Displayed When User Upload Excel which Has dueDate as past date ");
			}
			  			
    	}catch(Exception e){
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }   
    }
    
    public static void checkEndDate(String schedule) {
    	try {   
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			 int size1=0;
			if(schedule.equalsIgnoreCase("monthly")) {
				 ButtonHelper.click(tp.ScheduleMonthlyBtn, "Schedule Monthly Button");
				 size1=DropDownHelper.sizeOfSelect(tp.MonthlytaskRepeatEndDate,"Monthly task Repeat EndDate");
			 }
			else if(schedule.equalsIgnoreCase("weekly")) {
				 ButtonHelper.click(tp.ScheduleWeeklyBtn, "Schedule weekly Button");
				 size1=DropDownHelper.sizeOfSelect(tp.WeeklytaskRepeatEndDate,"weekly task Repeat EndDate");
			 }else {
				 ButtonHelper.click(tp.ScheduleDailyBtn, "Schedule Daily Button");
				 size1=DropDownHelper.sizeOfSelect(tp.taskRepeatEndDate,"Daily task Repeat EndDate");
			 }
			boolean check=true;			 
			for(int i=1;i<=size1;i++){
				String startDate=null;
				if(schedule.equalsIgnoreCase("monthly"))
					startDate=DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", i-1);
				if(schedule.equalsIgnoreCase("weekly"))
					 startDate=DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", i-1);
				else
					 startDate=DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", i-1);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
				Date start = sdf.parse(startDate);
				LocalDateTime now = LocalDateTime.now(); 						
				String endDate = dtf.format(now);
				Date end = sdf.parse(endDate);
				if(start.before(end)) {
					check=false;
					break;
				}				
			}
			if(check)
				test.log(LogStatus.PASS,  "Only Present And Future Quater Dates Are Present For Selection As The End Date Of A Repeating Task");
			else
				test.log(LogStatus.FAIL,  "Past Quater Date Is Present For Selection As The End Date Of A Repeating Task");
    	}catch(Exception e){
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }  
    	
    }
    
    public static void checkEndDateAreQuaterBasis(String schedule) {
    	try { 
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
			LocalDateTime now = LocalDateTime.now();
			Date first = sdf.parse("31/03/"+dtf.format(now));				
			Date first1 = sdf.parse("30/06/"+dtf.format(now));				
			Date first2 = sdf.parse("31/08/"+dtf.format(now));				
			Date first3 = sdf.parse("31/12/"+dtf.format(now));
			boolean check=false;
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			 int size1=0;
			if(schedule.equalsIgnoreCase("monthly")) {
				 ButtonHelper.click(tp.ScheduleMonthlyBtn, "Schedule Monthly Button");
				 size1=DropDownHelper.sizeOfSelect(tp.MonthlytaskRepeatEndDate,"Monthly task Repeat EndDate");
				 if(size1==4){
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 0)).equals(first)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 1)).equals(first1)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 2)).equals(first2)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else if(size1==3) {
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 1)).equals(first1)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 2)).equals(first2)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else if(size1==2) {
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 2)).equals(first2)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else if(size1==1){
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.MonthlytaskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else {
						check =false;
					}
			}else if(schedule.equalsIgnoreCase("weekly")) {
				 ButtonHelper.click(tp.ScheduleWeeklyBtn, "Schedule weekly Button");
				 size1=DropDownHelper.sizeOfSelect(tp.WeeklytaskRepeatEndDate,"weekly task Repeat EndDate");
				 if(size1==4){
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 0)).equals(first)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 1)).equals(first1)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 2)).equals(first2)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else if(size1==3) {
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 1)).equals(first1)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 2)).equals(first2)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else if(size1==2) {
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 2)).equals(first2)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else if(size1==1){
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.WeeklytaskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else {
						check =false;
					}
			 }else {
				 ButtonHelper.click(tp.ScheduleDailyBtn, "Schedule Daily Button");
				 size1=DropDownHelper.sizeOfSelect(tp.taskRepeatEndDate,"Daily task Repeat EndDate");
				 if(size1==4){
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 0)).equals(first)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 1)).equals(first1)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 2)).equals(first2)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else if(size1==3) {
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 1)).equals(first1)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 2)).equals(first2)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else if(size1==2) {
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 2)).equals(first2)&&sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else if(size1==1){
						if(sdf.parse(DropDownHelper.selectElementByIndexAndGetText(tp.taskRepeatEndDate,"End date", 3)).equals(first3))
							check=true;
					}else {
						check =false;
					}
			 }			
								
			if(check)
				test.log(LogStatus.PASS,  "End Dates Of A "+schedule+" Repeating Task Are Quaterly Basis");
			else
				test.log(LogStatus.FAIL,  "End Dates Of A "+schedule+"  Repeating Task Are Not Quaterly Basis");
    	}catch(Exception e){
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }  
    	
    }
    
    public static void uploadExcelVerifyFeilds(String dueDate1) {
    	try {
    		FillSampleExcel();   					
			UploadSampleExcel();
			Thread.sleep(4000);	
			HomePageSideBar hps=new HomePageSideBar(driver);
			hps.serviceEngineersWatcher.click();
			Thread.sleep(4000);
			AddServiceEngineerPage se = new AddServiceEngineerPage(driver);
			System.out.println(se.AllSeviceEngineersPhone.get(1).getText());
			System.out.println(ExcelReader.ReadSampleExcel("data","Service_engineer_number"));
			int num=DropDownHelper.findIndexOfTextElement(se.AllSeviceEngineersPhone,ExcelReader.ReadSampleExcel("data","Service_engineer_number"));
			String engineername=se.AllSeviceEngineersNames.get(num).getText();
			System.out.println(engineername);
			driver.navigate().refresh();
			hps.Tasks.click();
			TaskPage tp=new TaskPage(driver);
			Thread.sleep(10000);
			TextBoxHelper.enterTextString(tp.DateFilter, "Filter Date", dueDate1);	
			Thread.sleep(10000);
			if(tp.verifyTaskName.getText().equalsIgnoreCase(ExcelReader.ReadTestData("taskname"))){
				test.log(LogStatus.PASS, "Task name present on application after creating a task is same as in uploaded excel");	
				if(tp.verifyServiceEngineerName.getText().equalsIgnoreCase(engineername))
					test.log(LogStatus.PASS, "Service engineer name present on application after creating a task is same as in uploaded excel");
				else
					test.log(LogStatus.FAIL, "Service engineer name present on application after creating a task is not same as in uploaded excel");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String startDate = tp.verifyDueDate.getText();
				String endDate = ExcelReader.ReadSampleExcel("data","Due_date");
				System.out.println(ExcelReader.ReadSampleExcel("data","Due_date"));
				Date start = sdf.parse(startDate);
				System.out.println(start);
				sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date end = sdf.parse(endDate);
				System.out.println(end);
				if(start.equals(end))
					test.log(LogStatus.PASS, "Due Date present on application after creating a task is same as in uploaded excel");
				else
					test.log(LogStatus.FAIL, "Due Date present on application after creating a task is not same as in uploaded excel");
				if(tp.verifyAccountName.getText().equalsIgnoreCase(ExcelReader.ReadSampleExcel("data","Account_name")))
					test.log(LogStatus.PASS, "Account name present on application after creating a task is same as in uploaded excel");
				else
					test.log(LogStatus.FAIL, "Account name present on application after creating a task is not same as in uploaded excel");
				if(tp.VerifyTaskStatus.isDisplayed())
					test.log(LogStatus.PASS, "Task status is displayed");
				else 
					test.log(LogStatus.FAIL, "Task status is not displayed");
				if(tp.addComment.isEnabled())
					test.log(LogStatus.PASS, "Add comment button is displayed");
				else
					test.log(LogStatus.FAIL, "Add comment button is not displayed");
				DeleteTaskWithoutMessage();
			}else {
					test.log(LogStatus.FAIL, "User was not able to create new task");	
			}							
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void CreatTaskVerifyFeilds() {
    	try {
    		 BasicCreateTaskHalfHelper(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
    		 TaskPage tp=new TaskPage(driver);
    		 String Text1=DropDownHelper.selectRandomElementByIndexAndGetText(tp.ServiceEngineer,"service engineer");
    		 String Text2=DropDownHelper.selectRandomElementByIndexAndGetText(tp.SelectedAccount,"Account");
    		 SaveTaskAndApplyFilter();
    		 tp=new TaskPage(driver);
 			 if(tp.verifyTaskName.getText().equalsIgnoreCase(ExcelReader.ReadTestData("taskname"))){
 				test.log(LogStatus.PASS, "Task name present on application after creating a task is same as by entered user");	
 				if(tp.verifyServiceEngineerName.getText().equalsIgnoreCase(Text1))
 					test.log(LogStatus.PASS, "Service engineer name present on application after creating a task is same as by entered user");
 				else
 					test.log(LogStatus.FAIL, "Service engineer name present on application after creating a task is not same as entered by user");
 				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 				String startDate = tp.verifyDueDate.getText();
 				String endDate = ExcelReader.ReadTestData("duedate");
 				Date start = sdf.parse(startDate);			
 				Date end = sdf.parse(endDate);
 				if(start.equals(end))
 					test.log(LogStatus.PASS, "Due Date present on application after creating a task is same as entered by user");
 				else
 					test.log(LogStatus.FAIL, "Due Date present on application after creating a task is not same as entered by user");
 				if(tp.verifyAccountName.getText().equalsIgnoreCase(Text2))
 					test.log(LogStatus.PASS, "Account name present on application after creating a task is same as entered by user");
 				else
 					test.log(LogStatus.FAIL, "Account name present on application after creating a task is not same as entered by user");
 				if(tp.VerifyTaskStatus.isDisplayed())
 					test.log(LogStatus.PASS, "Task status is displayed");
 				else 
 					test.log(LogStatus.FAIL, "Task status is not displayed");
 				if(tp.addComment.isEnabled())
 					test.log(LogStatus.PASS, "Add comment button is displayed");
 				else
 					test.log(LogStatus.FAIL, "Add comment button is not displayed");
 				DeleteTaskWithoutMessage();
 			}else {
				test.log(LogStatus.FAIL, "User was not able to create new task");	
 			}
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
 
    
    public static void verifyTaskStatus() {
    	try {
    		BasicCreateTaskHalfHelper(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
    		SaveTaskAndApplyFilter();
    		TaskPage tp=new TaskPage(driver);
 			 if(tp.verifyTaskName.getText().equalsIgnoreCase(ExcelReader.ReadTestData("taskname"))){
 				 test.log(LogStatus.PASS, "User Was Able To Create Task Successfully");
 				 if(tp.VerifyTaskStatus.getText().equalsIgnoreCase("incomplete"))
 					 test.log(LogStatus.PASS, "Task status displayed is "+tp.VerifyTaskStatus.getText());
 				 else 
 					 test.log(LogStatus.FAIL, "Task status displayed is "+tp.VerifyTaskStatus.getText());
 				DeleteTaskWithoutMessage();
			}else{
				test.log(LogStatus.FAIL, "User was not able to create new task");	
			}	
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void verifyalertafetDeletingTask() {
    	try {
    		BasicCreateTaskHalfHelper(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
    		SaveTaskAndApplyFilter();
    		TaskPage tp=new TaskPage(driver);
    		ButtonHelper.click(tp.DeleteTask,"Delete Task");
    		 WebDriverWait wait = new WebDriverWait(driver, 50);
    		 wait.until(ExpectedConditions.alertIsPresent());
    		 if( driver.switchTo().alert().getText().equalsIgnoreCase("Want to delete?")) {
    			 test.log(LogStatus.PASS, "on click of delete an confrimation alert is coming with text "+"'"+ driver.switchTo().alert().getText()+"'"); 			  	
				 driver.switchTo().alert().accept();
				 wait.until(ExpectedConditions.alertIsPresent());
				 driver.switchTo().alert().accept();
    		 }else{
    			 test.log(LogStatus.FAIL, "on click of delete No confrimation alert is coming");
    		 }
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    
    
    public static void AddComment() {
    	try {
    		BasicCreateTaskHalfHelper(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
    		SaveTaskAndApplyFilter();
    		TaskPage tp=new TaskPage(driver);
 			 if(tp.verifyTaskName.getText().equalsIgnoreCase(ExcelReader.ReadTestData("taskname"))){				 
 				 test.log(LogStatus.PASS, "User Was Able To Create Task Successfully");
 				 ButtonHelper.click(tp.addComment, "Add Comment Button");
 				 TextBoxHelper.enterTextString(tp.writeComment,"comment",ExcelReader.ReadTestData("add_comment"));
 				 ButtonHelper.click(tp.addCommentbtnPop, "Add Comment Button On Popup"); 				 
 				 if(tp.VerifyComment.getText().equalsIgnoreCase(ExcelReader.ReadTestData("add_comment")))
 					test.log(LogStatus.PASS, "User is able to add comment to task"); 
 				 else
 					test.log(LogStatus.PASS, "User is not able to add comment to task");
 				ButtonHelper.click(tp.closecommentpopup, "Close Comment Popup");
 				DeleteTaskWithoutMessage();
			}else{
				test.log(LogStatus.FAIL, "User was not able to create new task");	
			}	
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void verifyDeletingTask() {
    	try {
    		BasicCreateTaskHalfHelper(ExcelReader.ReadTestData("taskname"),ExcelReader.ReadTestData("taskdescription"),ExcelReader.ReadTestData("duedate"));
    		SaveTaskAndApplyFilter();
    		TaskPage tp=new TaskPage(driver);   		
    		 WebDriverWait wait = new WebDriverWait(driver, 50);   		 
    		 if(tp.VerifyTaskStatus.getText().equalsIgnoreCase("incomplete")){
    			 if(tp.DeleteTask.isEnabled()){    				 
	    			 ButtonHelper.click(tp.DeleteTask,"Delete Task");
	    			 wait.until(ExpectedConditions.alertIsPresent());
	    			 test.log(LogStatus.INFO, "on click of delete an confrimation alert is coming with text "+"'"+ driver.switchTo().alert().getText()+"'"); 			  	
					 driver.switchTo().alert().accept();
					 wait.until(ExpectedConditions.alertIsPresent());
					 if( driver.switchTo().alert().getText().equalsIgnoreCase("Test Task is successfully deleted.")) {
						 test.log(LogStatus.PASS, "User Is Successfully Able delete a task whose status is incomplete"); 
						 driver.switchTo().alert().accept();
					 }else {
						 test.log(LogStatus.FAIL, "User Is  Unable delete a task whose status is incomplete");
					 }
    			 }else {
    				 test.log(LogStatus.FAIL, "Delete Button is Disabled"); 
    			 }
    		 }else{
    			 test.log(LogStatus.FAIL, "Task Status is other than incomplete");
    		 }
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void verifyDownloadTaskButton(String TaskStatus,String window) {
    	try {
    		HomePageSideBar hps=new HomePageSideBar(driver);
    		ButtonHelper.click(hps.Tasks, "Task Button");
    		TaskPage tp=new TaskPage(driver);
    		if(TaskStatus.equalsIgnoreCase("incomplete")) {
    		DropDownHelper.selectElementByvalue(tp.statusFilter,"Status Filter", "incomplete");
    		Thread.sleep(2000);
    		if(tp.DownloadTask.isEnabled())
    			test.log(LogStatus.FAIL, "Download Button is enabled when task status is "+TaskStatus);
    		else
    			test.log(LogStatus.PASS, "Download Button is Disabled when task status is "+TaskStatus);
    		}
    		if(TaskStatus.equalsIgnoreCase("complete")) {
        		DropDownHelper.selectElementByvalue(tp.statusFilter,"Status Filter", "complete");
        		Thread.sleep(2000);
        		if(tp.DownloadTask.isEnabled())
        			test.log(LogStatus.PASS, "Download Button is enabled when task status is "+TaskStatus);
        		else
        			test.log(LogStatus.FAIL, "Download Button is Disabled when task status is "+TaskStatus);
        		String originalWindow = driver.getWindowHandle();
        		assert driver.getWindowHandles().size() == 1;
        		ButtonHelper.click(tp.DownloadTask, "Download Task");
        		
        		for (String windowHandle : driver.getWindowHandles()) {
        		    if(!originalWindow.contentEquals(windowHandle)) {
        		        driver.switchTo().window(windowHandle);
        		        break;
        		    }
        		}
        		String str = driver.getCurrentUrl();
        		System.out.println(str);
        		if(str.contains("Solcare_Visit_Report"))
        			test.log(LogStatus.PASS, "Download Button is enabled And user Is Able To Download File");
        		else
        			test.log(LogStatus.FAIL, "Download Button is enabled And user Is Not Able To Download File");
        		if(window.equalsIgnoreCase("checkwindow")) {
        			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        			System.out.println("No. of tabs: " + tabs.size());
        				if(tabs.size()==2)
        					test.log(LogStatus.PASS, " Download File Open in a new window with title "+"'"+driver.getWindowHandle()+"'");
        				else
        					test.log(LogStatus.FAIL, "Download Button is enabled And user Is Not Able To Download File");
        		}
    		}
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    public static void EditTaskenabled() {
    	try {   		
    		HomePageSideBar hps=new HomePageSideBar(driver);
    		TaskPage tp=new TaskPage(driver); 
    		ButtonHelper.click(hps.Tasks, "Task Button");
    		 		    
    		DropDownHelper.selectElementByvalue(tp.statusFilter,"Status Filter", "incomplete");
    		
    		if(tp.VerifyTaskStatus.getText().equalsIgnoreCase("incomplete")){
    		if(tp.editTask.isEnabled())
    			test.log(LogStatus.PASS, "edit task Button is enabled when task status is "+tp.VerifyTaskStatus.getText());
    		else
    			test.log(LogStatus.FAIL, "edit task Button is Disabled when task status is "+tp.VerifyTaskStatus.getText());
    		}    	  		
    		
    		TaskPage tp1=new TaskPage(driver);
    		  		
    		DropDownHelper.selectElementByvalue(tp1.statusFilter,"Status Filter", "overdue");
    		 		
    		if(tp1.VerifyTaskStatus.getText().equalsIgnoreCase("incomplete")){
    		if(tp1.editTask.isEnabled())
    			test.log(LogStatus.PASS, "edit task Button is enabled when task status is "+tp1.VerifyTaskStatus.getText());
    		else
    			test.log(LogStatus.FAIL, "edit task Button is Disabled when task status is "+tp1.VerifyTaskStatus.getText());
    		}  
    		TaskPage tp3=new TaskPage(driver);  		
    		DropDownHelper.selectElementByvalue(tp3.statusFilter,"Status Filter", "complete");
    		
    		if(tp3.VerifyTaskStatus.getText().equalsIgnoreCase("complete")){
    		if(tp3.editTask.isEnabled())
    			test.log(LogStatus.FAIL, "edit task Button is enabled when task status is "+tp3.VerifyTaskStatus.getText());
    		else
    			test.log(LogStatus.PASS, "edit task Button is Disabled when task status is "+tp3.VerifyTaskStatus.getText());
    		}
    		TaskPage tp4=new TaskPage(driver);
    		
    		DropDownHelper.selectElementByvalue(tp4.statusFilter,"Status Filter", "pending");
    		Thread.sleep(4000);
    		System.out.println(tp4.VerifyTaskStatus.getText());
    		if(tp4.VerifyTaskStatus.getText().contains("pending")){
        		if(tp4.editTask.isEnabled())
        			test.log(LogStatus.FAIL, "edit task Button is enabled when task status is "+tp4.VerifyTaskStatus.getText());
        		else
        			test.log(LogStatus.PASS, "edit task Button is Disabled when task status is "+tp4.VerifyTaskStatus.getText());
        		}
    		
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }
    
    
    
    public static void EditTaskpopdisplayed() {
    	try {
    		HomePageSideBar hps=new HomePageSideBar(driver);
    		ButtonHelper.click(hps.Tasks, "Task Button");
    		TaskPage tp=new TaskPage(driver);  		    
    		DropDownHelper.selectElementByvalue(tp.statusFilter,"Status Filter", "incomplete");
    		Thread.sleep(2000);
    		if(tp.editTask.isEnabled()) {
    			test.log(LogStatus.PASS, "edit task Button is enabled when task status is "+tp.VerifyTaskStatus.getText());
    			ButtonHelper.click(tp.editTask, "edit task button");
    			Thread.sleep(2000);
    			if(tp.editTaskName.isDisplayed())
    				test.log(LogStatus.PASS, "edit task popup Is Displayed After Clicking On Edit TasK Button");
    			else
    				test.log(LogStatus.PASS, "edit task popup Is Not Displayed After Clicking On Edit TasK Button");
    		}else {
    			test.log(LogStatus.FAIL, "edit task Button is Disabled when task status is "+tp.VerifyTaskStatus.getText());
    		}
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }	
    
    public static void EditTaskpopCloses() {
    	try {
    		EditTaskpopdisplayed();
    		TaskPage tp =new TaskPage(driver);
    		ButtonHelper.click(tp.closeEditPopup, "Edit PopUp Close Button");
    		if(tp.createTask.isDisplayed())
    			test.log(LogStatus.PASS,"After clicking On The Close Buttton User Is Able Close The Edit Task Window");
    		else
    			test.log(LogStatus.PASS,"After clicking On The Close Buttton User Is Not Able Close The Edit Task Window");
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }	
    
    public static void EditTaskpopFeilds() {
    	try {
    		HomePageSideBar hps=new HomePageSideBar(driver);
    		ButtonHelper.click(hps.Tasks, "Task Button");
    		TaskPage tp=new TaskPage(driver);  		    
    		DropDownHelper.selectElementByvalue(tp.statusFilter,"Status Filter", "incomplete");
    		Thread.sleep(2000);
    		if(tp.editTask.isEnabled()) {
    			test.log(LogStatus.PASS, "edit task Button is enabled when task status is "+tp.VerifyTaskStatus.getText());
    			ButtonHelper.click(tp.editTask, "edit task button");
    			Thread.sleep(2000);
    			if(tp.editSelectedAccount.isEnabled()&&tp.editTaskName.isEnabled()&&tp.editTaskType.isEnabled()&&tp.editWatcherGroup.isEnabled())
    				test.log(LogStatus.PASS, "ALL Fields except Schedule are editable");
    			else
    				test.log(LogStatus.FAIL, "One or More Fields except Schedule are Not editable");
    			if(tp.editDailyBtn.isEnabled()||tp.editMonthlyBtn.isEnabled()||tp.editNoneBtn.isEnabled()||tp.editWeeklyBtn.isEnabled())
    				test.log(LogStatus.FAIL, "Schedule Of Task Is  editable");
    			else
    				test.log(LogStatus.PASS, "Schedule Of Task Is not editable");
    		}else {
    			test.log(LogStatus.FAIL, "edit task Button is Disabled when task status is "+tp.VerifyTaskStatus.getText());
    		}
    	}catch(Exception e) {
	    	e.printStackTrace();
	    	test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
	    }
    }	
    
}
