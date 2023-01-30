package stepdefination;

import com.relevantcodes.extentreports.LogStatus;
import static settings.ObjectRepo.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


import static settings.ObjectRepo.driver;
import elementhelper.ButtonHelper;
import elementhelper.TextBoxHelper;
import generic.GenericHelper;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
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
			TextBoxHelper.enterText(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterText(lp.passwordTextBox, "Password",password);
			ButtonHelper.click(lp.checkBox, "Terms and Conditions Checkbox");
			ButtonHelper.click(lp.loginButton, "Login Button");	
			String DashBoard =hp.dashboard.getText();
		   if(DashBoard.equals(welcomeName))
			   test.log(LogStatus.PASS, "login successfull");
		   else
			   test.log(LogStatus.FAIL, "login failed");  
			   
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void VerifyInvalidLogin(String userName, String password)  {
		try {
			LoginPage lp = new LoginPage(driver);
			TextBoxHelper.enterText(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterText(lp.passwordTextBox, "Password",password);
			ButtonHelper.click(lp.checkBox, "Terms and Conditions Checkbox");
			ButtonHelper.click(lp.loginButton, "Login Button");
			Thread.sleep(3000);
		   if(lp.errorMessage.isDisplayed())
			   test.log(LogStatus.PASS, "Login failed either username or password is incorrect");
		   else
			   test.log(LogStatus.FAIL, "Logged into the application with invalid credential ");
			   
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
		}
	}
	
	public static void LoginWithoutTermsCheckbox(String userName, String password){
		try {
			LoginPage lp = new LoginPage(driver);
			HomePage hp = new HomePage(driver);
			TextBoxHelper.enterText(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterText(lp.passwordTextBox, "Password",password);
			ButtonHelper.click(lp.loginButton, "Login Button");
			String usersName =hp.dashboard.getText();
	    	   if(usersName.equals("Dashboard"))
					test.log(LogStatus.FAIL, "login successfull");
				else
					test.log(LogStatus.FAIL, "login failed");  	
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.PASS, "Login button is not clickable :"+e.getMessage());
		}	
	}
	
	public static void LoginWithoutCredentials() {
		try {
			LoginPage lp = new LoginPage(driver);
			HomePage hp = new HomePage(driver);
			ButtonHelper.click(lp.loginButton, "Login Button");
			String usersName =hp.dashboard.getText();
	    	   if(usersName.equals("Dashboard"))
	    		   test.log(LogStatus.FAIL, "login successfull");
			   else
				   test.log(LogStatus.FAIL, "login failed");  	
		}catch(Exception e) {
		e.printStackTrace();
		test.log(LogStatus.PASS, "Login button is not clickable :"+e.getMessage());
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
	    		URL LocalURL = new URL("file:///D:/newautomations/Solcare/termsandconditions.pdf");
	    		InputStream Testfile = LocalURL.openStream();
	    		BufferedInputStream file2 =new BufferedInputStream(Testfile);
	    		PDDocument document2 = PDDocument.load(file2);
	    		String testPdfContent= new PDFTextStripper().getText(document2);
	    		if(testPdfContent.equalsIgnoreCase(pdfContent))
	    			 test.log(LogStatus.PASS, "the terms and condition link is clickable and contents inside link are as expected");
				   else
					   test.log(LogStatus.FAIL, "Either the terms and condition link is not clickable or contents inside the link are unexpected");  		    			
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
		    	ButtonHelper.click(lp.cookieAcceptButton, "Accept");
		    	test.log(LogStatus.PASS, "User is able to accept cookies");
		    }else{
				test.log(LogStatus.FAIL, "either cooikes pop up is not displayed or User is not able to accept the cookies");}
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
		    		test.log(LogStatus.PASS, "User is able see Dashboard");
		    }else {
		    	test.log(LogStatus.FAIL, "One or more component of dashboard are not present");
		    }
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
			ButtonHelper.click(pp.uploadPicture, "Upload Picture");			
			Robot robo = new Robot();
			robo.delay(2000);
			StringSelection addressString = new StringSelection("file:///D:/newautomations/testng-automation/src/test/resources/TestData/profilepicture.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(addressString,null);
			robo.keyPress(KeyEvent.VK_CONTROL);
			robo.keyPress(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_CONTROL);
			robo.keyRelease(KeyEvent.VK_V);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
			ButtonHelper.click(pp.savePicture, "Save Picture");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			
			  if(pp.profilePicture.isDisplayed())
				  test.log(LogStatus.PASS, "Profile Picture is uploaded and saved");
		         else
				test.log(LogStatus.FAIL, "either profile picture was not uploaded or not saved");				
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
			ButtonHelper.click(hp.profile,"Profile"); 
			ButtonHelper.click(pp.uploadPicture, "Upload Picture");			
			Robot robo = new Robot();
			robo.delay(2000);
			StringSelection addressString = new StringSelection("file:///D:/newautomations/testng-automation/src/test/resources/TestData/profilepicture.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(addressString,null);
			robo.keyPress(KeyEvent.VK_CONTROL);
			robo.keyPress(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_CONTROL);
			robo.keyRelease(KeyEvent.VK_V);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
			ButtonHelper.click(pp.savePicture, "Save Picture");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			pp =new ProfilePage(driver);
			ButtonHelper.click(pp.uploadPicture, "Upload Picture");	
			StringSelection addressString2 = new StringSelection("file:///D:/newautomations/testng-automation/src/test/resources/TestData/profilepicture2.jpeg");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(addressString2,null);
			robo.keyPress(KeyEvent.VK_CONTROL);
			robo.keyPress(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_CONTROL);
			robo.keyRelease(KeyEvent.VK_V);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
			ButtonHelper.click(pp.savePicture, "Save Picture");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			  if(pp.profilePicture.isDisplayed())
				  test.log(LogStatus.PASS, "Profile Picture is edited and saved");
		         else
				test.log(LogStatus.FAIL, "either profile picture was not uploaded or not saved");				
			}catch(Exception e) {
				e.printStackTrace();
				test.log(LogStatus.FAIL, "Exception while executing test :"+e.getMessage());
			}  
		
	}

}
