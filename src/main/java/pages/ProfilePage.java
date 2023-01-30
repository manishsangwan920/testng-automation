package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;




public class ProfilePage extends PageBase {
	private WebDriver driver;
	public ProfilePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	/** Web Elements */
	@FindBy(id="profilePicture")
	@CacheLookup
	public  WebElement uploadPicture;
	
	@FindBy(id="saveProfilePic")
	@CacheLookup
	public  WebElement savePicture;
	
	@FindBy(id="edit-id")
	@CacheLookup
	public  WebElement Edit;
	
	@FindBy(id="profilePic")
	@CacheLookup
	public  WebElement profilePicture;
	
	@FindBy(id="getProfilePic")
	@CacheLookup
	public  WebElement getProfilePic;

}
