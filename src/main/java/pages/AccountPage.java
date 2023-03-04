package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends PageBase {
	private WebDriver driver;
	public AccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath = "//*[@id='reRender2']/tr/td[1]")
	@CacheLookup
	public List<WebElement> AllAccountNames;

}
