package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import elementhelper.ButtonHelper;
import elementhelper.TextBoxHelper;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2016-04-15
********************************************************************************************************/

public class LoginPage extends PageBase {
	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	/** Web Elements */
	
	@FindBy(id = "email")
	@CacheLookup
	public  WebElement emailTextBox;
	
	
	@FindBy(id = "pwd")
	@CacheLookup
	public  WebElement passwordTextBox;
	
	@FindBy(id = "terms")
	@CacheLookup
	public  WebElement loginButton;
	
	@FindBy(xpath = "//div[@class='login-page']")
	@CacheLookup
	public  WebElement loginForm;
	
	
	@FindBy(xpath = "//input[@type='checkbox' and @name='remember']")
	@CacheLookup
	public  WebElement checkBox;
	
	@FindBy(id = "erro")
	@CacheLookup
	public  WebElement errorMessage;
	
	@FindBy(xpath = "/html/body/div/div[1]/div/div/form/div[3]/a")
	@CacheLookup
	public  WebElement termsAndCondition;
	
	@FindBy(xpath = "//a[contains(text(),'Forgot password?')]")
	@CacheLookup
	public  WebElement forgetPassword;
	
	@FindBy(id = "forgotEmail")
	@CacheLookup
	public  WebElement forgotPasswordEmailTextBox;
	
	@FindBy(xpath = "//div[@class='card-body']")
	@CacheLookup
	public  WebElement cookieCard;
	
	@FindBy(xpath = "//a[contains(text(),'Accept')]")
	@CacheLookup
	public  WebElement cookieAcceptButton;
	
	
	
	
	
	
	
	



}
