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
	
	@FindBy(id = "nodata")
	@CacheLookup
	public  WebElement NoDatataskStatus;
	
	@FindBy(id = "myChartDistributor")
	@CacheLookup
	public  WebElement distributersInfo;
	
	@FindBy(xpath = "//p[contains(text(),'Account Task Records')]")
	@CacheLookup
	public  WebElement titleAccountTaskRecords;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[5]/div")
	@CacheLookup
	public  WebElement accountTaskRecords;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[6]/div")
	@CacheLookup
	public  WebElement solcareObservationRecords;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[6]/div")
	@CacheLookup
	public  WebElement titleServiceEngineerRecords;
	
	@FindBy(id = "detailReport")
	@CacheLookup
	public  WebElement serviceEngineerRecords;
	
	@FindBy(xpath = "//a[@role='button' and @data-widget='pushmenu']")
	@CacheLookup
	public  WebElement pushMenuButton;
	
	@FindBy(xpath = "//a[@data-toggle='dropdown']")
	@CacheLookup
	public  WebElement profileIcon;
	
	@FindBy(id = "profile")
	@CacheLookup
	public  WebElement profile;
	
	@FindBy(id = "reset-password")
	@CacheLookup
	public  WebElement resetPassword ;
	
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	@CacheLookup
	public  WebElement logoutButton;
	
	@FindBy(id = "company_name")
	@CacheLookup
	public  WebElement companyName ;
	
	@FindBy(xpath = "//img[@alt='Exxon Logo']")
	@CacheLookup
	public  WebElement companynamelogo ;
	
	
	@FindBy(id = "oldpass")
	@CacheLookup
	public  WebElement existingPassword;
	
	@FindBy(id = "newpass")
	@CacheLookup
	public  WebElement newPassword;
	
	@FindBy(id = "confirmpass")
	@CacheLookup
	public  WebElement confirmPassword;
	
	@FindBy(xpath = "//button[@class='close' and @data-dismiss='modal']")
	@CacheLookup
	public  WebElement closeResetPasswordWindow;
	
	@FindBy(id = "event2")
	@CacheLookup
	public  WebElement resetPasswordSubmit;

	@FindBy(id = "message")
	@CacheLookup
	public  WebElement errorMsgPasswordMismatch;
	
	@FindBy(xpath = "//select[@id='Precisionselect']")
	@CacheLookup
	public  WebElement SelectAccount;
	
	@FindBy(xpath = "//*[@id=\"Precisionselect\"]/option[2]")
	@CacheLookup
	public  WebElement SelectAccountname1;
	
	@FindBy(id = "allmachine")
	@CacheLookup
	public  WebElement Selectmachine;
	
	@FindBy(id = "downloadCSV")
	@CacheLookup
	public  WebElement ExportCSV;
	
	@FindBy(id = "downloadPdf")
	@CacheLookup
	public  WebElement ExportChart;
	
	
	}
