package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2016-04-15
********************************************************************************************************/

public class HomePage extends PageBase {
	private WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	/** Web Elements */
	
	@FindBy(id="welcome_user")
	@CacheLookup
	public  WebElement distributersName;
	
	@FindBy(xpath = "//span[@class='user-name-header ml-auto']")
	@CacheLookup
	public  WebElement welcomeDistributersName;
	
	@FindBy(xpath = "//h1[@class='dashboard_title page_title']")
	@CacheLookup
	public  WebElement dashboard;
	
	@FindBy(id = "myChartTask")
	@CacheLookup
	public  WebElement taskStatus;
	
	@FindBy(id = "myChartDistributor")
	@CacheLookup
	public  WebElement distributersInfo;
	
	@FindBy(xpath = "//p[contains(text(),'Account Task Records')]")
	@CacheLookup
	public  WebElement titleAccountTaskRecords;
	
	@FindBy(id = "myChartAccount")
	@CacheLookup
	public  WebElement accountTaskRecords;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[6]/div")
	@CacheLookup
	public  WebElement solcareObservationRecords;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[6]/div")
	@CacheLookup
	public  WebElement titleServiceEngineerRecords;
	
	@FindBy(id = "myChartService")
	@CacheLookup
	public  WebElement serviceEngineerRecords;
	
	@FindBy(xpath = "//a[@role='button' and @data-widget='pushmenu']")
	@CacheLookup
	public  WebElement pushMenuButton;
	
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
	
	@FindBy(xpath = "//a[@data-toggle='dropdown']")
	@CacheLookup
	public  WebElement profileIcon;
	
	@FindBy(id = "profile")
	@CacheLookup
	public  WebElement profile;
	
	@FindBy(id = "reset-password")
	@CacheLookup
	public  WebElement resetPassword ;
	
	@FindBy(xpath = "//a[contains(text(),'Logout ')]")
	@CacheLookup
	public  WebElement logoutButton;
	}
