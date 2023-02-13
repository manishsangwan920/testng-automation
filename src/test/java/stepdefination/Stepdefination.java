package stepdefination;

import com.relevantcodes.extentreports.LogStatus;
import static settings.ObjectRepo.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static settings.ObjectRepo.driver;
import elementhelper.ButtonHelper;
import elementhelper.DropDownHelper;
import elementhelper.TextBoxHelper;
import generic.GenericHelper;
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
			TextBoxHelper.enterText(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterText(lp.passwordTextBox, "Password",password);
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
			TextBoxHelper.enterText(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterText(lp.passwordTextBox, "Password",password);
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
			TextBoxHelper.enterText(lp.emailTextBox, "User Name",userName);
			TextBoxHelper.enterText(lp.passwordTextBox, "Password",password);
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
			String chromeMessage = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();			
			  if(pp.profilePicture.isDisplayed()&&chromeMessage.equalsIgnoreCase("Your profile picture is successfully updated."))
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
			ButtonHelper.click(hp.profile,"Profile"); 
			ButtonHelper.click(pp.uploadPicture, "Upload Picture Button");			
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
		    TextBoxHelper.enterText(pp.editProfileName, "Name", name);
		    pp.editProfilePhone.clear();
		    TextBoxHelper.enterText(pp.editProfilePhone, "PHONE", phNumber);
		    pp.editProfileCompany.clear();
		    TextBoxHelper.enterText(pp.editProfileCompany, "Company", companyname);
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
		   pp.editProfileName.sendKeys("Parikshit");
		   pp.editProfileCompany.sendKeys("Yugasa Software Labs");
		   pp.editProfilePhone.sendKeys("9711474332");
		   pp.editProfileSave.click();
		   Thread.sleep(2000);
		   driver.switchTo().alert().accept();
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
			TextBoxHelper.enterText(hp.existingPassword,"Existing password",oldpassword);
			TextBoxHelper.enterText(hp.newPassword,"new password",newpassword);
			TextBoxHelper.enterText(hp.confirmPassword,"new password",newpassword);
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
			TextBoxHelper.enterText(hp.existingPassword,"Existing password",oldpassword);
			TextBoxHelper.enterText(hp.newPassword,"new password",newpassword);
			TextBoxHelper.enterText(hp.confirmPassword,"confirm password",confpassword);
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
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
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
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
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
			Select drpaccount = new Select(hp.SelectAccount);
			drpaccount.selectByValue("358");
			Thread.sleep(3000);
			
			
			String downloadFilepath =System.getProperty("user.dir");
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			WebDriver driver = new ChromeDriver(options);
			
			ButtonHelper.click(hp.ExportCSV, "export csv");
			
			Thread.sleep(30000);
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
	
	public static void CreateTask(String Taskname, String Description,String dueDate) {
		try {  
			HomePageSideBar hps = new HomePageSideBar(driver);	
			TaskPage tp=new TaskPage(driver);
			ButtonHelper.click(hps.Tasks, "Task Button on sidebar");
			ButtonHelper.click(tp.createTask, "Create task button");
			Thread.sleep(4000);
			TextBoxHelper.enterText(tp.TaskName,"Task Name", Taskname);
			DropDownHelper.selectRandomElementByIndex(tp.ServiceEngineer,"service engineer");
			DropDownHelper.selectRandomElementByIndex(tp.SelectedAccount,"Select account");
			TextBoxHelper.enterText(tp.DueDate,"Due Date", dueDate);		
			tp=new TaskPage(driver);
			ButtonHelper.click(tp.EnabledWatcher, "watcher button");			
			DropDownHelper.selectRandomElementFromList(tp.WatcherList,"watcher");
			Thread.sleep(2000);
			try {
				if(driver.switchTo().alert().getText().equalsIgnoreCase("The assignee cannot be a watcher")) {
				driver.switchTo().alert().accept();
				tp=new TaskPage(driver);
				ButtonHelper.click(tp.EnabledWatcher, "watcher button");			
				DropDownHelper.selectRandomElementFromList(tp.WatcherList,"watcher");
				}
			}catch(NoAlertPresentException e) {				
			}
			TextBoxHelper.enterText(tp.TaskDescription,"Description", Description);
			DropDownHelper.selectRandomElementByIndex(tp.TaskType,"Task Type");
			ButtonHelper.click(tp.ScheduleNoneBtn, "schedule none button");
			String screenshotPath = ExtentReportHelper.getScreenshot();
			test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
			ButtonHelper.click(tp.SaveCreatedTask, "Save button");
			 WebDriverWait wait = new WebDriverWait(driver, 30);
			  wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			if(tp.verifyTaskName.getText().equalsIgnoreCase(Taskname))
				test.log(LogStatus.PASS, "User was able to create new task successfully");	
			else
				test.log(LogStatus.FAIL, "User was not able to create new task");	
			tp.DeleteTask.click();			
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
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
				TextBoxHelper.enterText(tp.TaskName,"Task Name", Taskname);
			if(fieldname!="ServiceEngineer") {
				DropDownHelper.selectRandomElementByIndex(tp.ServiceEngineer,"service engineer");
				tp=new TaskPage(driver);
				ButtonHelper.click(tp.EnabledWatcher, "watcher button");			
				DropDownHelper.selectRandomElementFromList(tp.WatcherList,"watcher");
				Thread.sleep(2000);
				try {
					if(driver.switchTo().alert().getText().equalsIgnoreCase("The assignee cannot be a watcher")) {
					driver.switchTo().alert().accept();
					tp=new TaskPage(driver);
					Thread.sleep(2000);
					ButtonHelper.click(tp.EnabledWatcher, "watcher button");			
					DropDownHelper.selectRandomElementFromList(tp.WatcherList,"watcher");
					}
				}catch(NoAlertPresentException e) {				
				}
			}
			if(fieldname!="SelectedAccount")
				DropDownHelper.selectRandomElementByIndex(tp.SelectedAccount,"Select account");
			if(fieldname!="DueDate")
				TextBoxHelper.enterText(tp.DueDate,"Due Date", dueDate);						
			if(fieldname!="TaskType")
				DropDownHelper.selectRandomElementByIndex(tp.TaskType,"Task Type");
			if(fieldname=="Machines") {
				ButtonHelper.click(tp.Enabledmachine, "Machines button");			
				DropDownHelper.selectElementByIndexFromList(tp.MachineList,"Machines",1);
			}				
			TextBoxHelper.enterText(tp.TaskDescription,"Description", Description);
			ButtonHelper.click(tp.ScheduleNoneBtn, "schedule none button");		
			ButtonHelper.click(tp.SaveCreatedTask, "Save button");					
			WebDriverWait wait = new WebDriverWait(driver, 30);
		 	  wait.until(ExpectedConditions.alertIsPresent());
			  String msg = driver.switchTo().alert().getText();
			if(msg.equalsIgnoreCase("This service engineer does not exist.")||msg.equalsIgnoreCase("Due date should be greater than current date.")||msg.equalsIgnoreCase("This account does not exist."))				
				test.log(LogStatus.PASS, "error message '"+msg+ "' was displayed when user missed a mandatory field while creating task");
		    else			
				test.log(LogStatus.FAIL, "No error message was displayed when user missed a mandatory field while creating task");
			driver.switchTo().alert().accept();
			tp=new TaskPage(driver);
			if(tp.verifyTaskName.getText().equalsIgnoreCase(Taskname)) {
				test.log(LogStatus.FAIL, "User was able to create new task even though a mandatory field was not selected");
				tp.DeleteTask.click();			
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
			}
			else
				test.log(LogStatus.PASS, "User was not able to create new task Without selecting all the mandatory fields");	
			
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
			TextBoxHelper.enterText(tp.TaskName,"Task Name", Taskname);
			TextBoxHelper.enterText(tp.DueDate,"Due Date", dueDate);
			Thread.sleep(2000);			
			TextBoxHelper.enterText(tp.TaskDescription,"Description", Description);
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
			if(FieldName=="ServiceEngineer") {
				data=DropDownHelper.selectRandomElementByIndexAndGetText(tp.ServiceEngineer,"service engineer");			
			    data2=tp.ServiceEngineer.getAttribute("value");
			}
			if(FieldName=="SelectedAccount") {
				data=DropDownHelper.selectRandomElementByIndexAndGetText(tp.SelectedAccount,"Select account");			
			    data2=tp.SelectedAccount.getAttribute("value");
			}				
			System.out.println(data);
		    System.out.println(tp.ServiceEngineer.getAttribute("value"));
		    if(data.equalsIgnoreCase(data2))
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
			DropDownHelper.selectRandomElementByIndex(tp.SelectedAccount,"Select account");
			Thread.sleep(6000);
			ButtonHelper.click(tp.Enabledmachine, "Machines button");	
			DropDownHelper.selectElementByIndexFromList(tp.MachineList,"Machines",1);
			DropDownHelper.selectRandomElementFromList(tp.MachineList,"Machines");			
		    String data=tp.MachinesFeildTitle.getAttribute("title");
		    System.out.println(data);
		    String data2=tp.MachineListActiveFields.getText();
		    
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
			TextBoxHelper.enterText(tp.TaskName,"Task Name", Taskname);
			DropDownHelper.selectRandomElementByIndex(tp.SelectedAccount,"Select account");
			TextBoxHelper.enterText(tp.DueDate,"Due Date", dueDate);					
			TextBoxHelper.enterText(tp.TaskDescription,"Description", Description);
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
			TextBoxHelper.enterText(tp.TaskName,"Task Name", Taskname);
			DropDownHelper.selectRandomElementByIndex(tp.ServiceEngineer,"Service Engineer");
			TextBoxHelper.enterText(tp.DueDate,"Due Date", dueDate);					
			TextBoxHelper.enterText(tp.TaskDescription,"Description", Description);
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
	
	
	
	
	
	

}
