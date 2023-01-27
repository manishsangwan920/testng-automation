package pages;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

/*******************************************************************************************************
* @author  Shafkat Mustafa
* @since   2016-04-15
********************************************************************************************************/

@SuppressWarnings("rawtypes")
public abstract class PageBase{
	
	private WebDriver driver;
	
	private By getFindByAnno(FindBy anno){
		switch (anno.how()) {
		
		case CLASS_NAME:
			return new By.ByClassName(anno.using());
		case CSS:
			return new By.ByCssSelector(anno.using());
		case ID:
			return new By.ById(anno.using());
		case LINK_TEXT:
			return new By.ByLinkText(anno.using());
		case NAME:
			return new By.ByName(anno.using());
		case PARTIAL_LINK_TEXT:
			return new By.ByPartialLinkText(anno.using());
		case XPATH:
			return new By.ByXPath(anno.using());
		default :
			throw new IllegalArgumentException("Locator not Found : " + anno.how() + " : " + anno.using());
		}
	}
	
	protected By getElemetLocator(Object obj,String element) throws Exception {
		Class childClass = obj.getClass();
		By locator = null;
		try {
			locator = getFindByAnno(childClass.
					 getDeclaredField(element).
					 getAnnotation(FindBy.class));
		} catch (Exception e) {
			throw e;
		}
		return locator;
	}
	
	public void waitForElement(WebElement element,int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.pollingEvery(250,TimeUnit.MILLISECONDS);
		wait.until(elementLocated(element));
	}
	
	private Function<WebDriver, Boolean> elementLocated(final WebElement element) {
		return new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				return element.isDisplayed();
			}
		};
	}
	
	public PageBase(WebDriver driver) {
		if(driver == null)
			throw new IllegalArgumentException("Driver object is null");
		
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		this.driver = driver;
	}
	
	public boolean checkForTitle(String title){
		if(title == null || title.isEmpty())
			throw new IllegalArgumentException(title);
		return driver.getTitle().trim().contains(title);
	}
	
}
