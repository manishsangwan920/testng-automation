package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class AddMachinesPage extends PageBase {
	private WebDriver driver;
	public AddMachinesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//*[@id=\"reRenderMachines\"]/tr/td[1]")
	@CacheLookup
	public List<WebElement> AccountNames;
	
	@FindBy(xpath = "//*[@id=\"reRenderMachines\"]/tr/td[3]")
	@CacheLookup
	public List<WebElement> MachineUniqueCode;
}
