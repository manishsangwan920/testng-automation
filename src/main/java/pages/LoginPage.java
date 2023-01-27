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
	
	@FindBy(xpath = "(//*[@class='Tooltip_tip__1AmT_']//following::ul/*)[2]")
	@CacheLookup
	public  WebElement editOption;
	
	@FindBy(xpath = "(//*[@class='Button_root__1hsq2 Button_variant-contained__3XbRQ Button_color-primary__Hkfvx'])[2]")
	@CacheLookup
	public  WebElement runDomainAdaptationButton;
	
	
	
	
	



}
