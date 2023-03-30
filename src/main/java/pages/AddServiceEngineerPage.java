package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class AddServiceEngineerPage extends PageBase {
	private WebDriver driver;
	public AddServiceEngineerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath = "//*[@id='reRenderSer']/tr/td[1]")
	@CacheLookup
	public List<WebElement> AllSeviceEngineersNames;

	@FindBy(xpath = "//*[@id='reRenderSer']/tr/td[2]")
	@CacheLookup
	public List<WebElement> AllSeviceEngineersPhone;
	
}
