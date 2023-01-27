package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

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
	
	
	
	
	



}
