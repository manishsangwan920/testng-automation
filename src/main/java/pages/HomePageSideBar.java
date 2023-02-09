package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePageSideBar extends PageBase {
	private WebDriver driver;
	public HomePageSideBar(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	/** Web Elements */
	
	
	
	@FindBy(id = "dashboard")
	@CacheLookup
	public  WebElement dashboardSideBar;
	
	@FindBy(id = "userTaskList")
	@CacheLookup
	public  WebElement Tasks;
	
	@FindBy(id = "qutaskickTaskList")
	@CacheLookup
	public  WebElement quickTasks;
	
	@FindBy(id = "accounts")
	@CacheLookup
	public  WebElement accounts;
	
	@FindBy(id = "service_engineers_watcher")
	@CacheLookup
	public  WebElement serviceEngineersWatcher;

	@FindBy(id = "machines")
	@CacheLookup
	public  WebElement machines;
	
	@FindBy(id = "calendar")
	@CacheLookup
	public  WebElement calendar;
	
	@FindBy(id = "settings")
	@CacheLookup
	public  WebElement logOutButtonSideBar;
}
