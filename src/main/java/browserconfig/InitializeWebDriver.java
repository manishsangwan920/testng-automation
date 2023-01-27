
package browserconfig;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import exception.NoSuitableDriverFoundException;
import settings.ObjectRepo;


/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/

public class InitializeWebDriver {

	public WebDriver standAloneStepUp(BrowserType bType) throws Exception {
		try {
			switch (bType) {

			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
					return chrome.getChromeDriver(chrome.getChromeCapabilities());
			case RemoteChrome:
				RemoteChromeBrowser remotechrome = new RemoteChromeBrowser();
					return remotechrome.getRemoteChromeDriver(ObjectRepo.reader.getRemoteExecutionEnvURL(), remotechrome.getRemoteChromeCapabilities());

			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				return firefox.getFirefoxDriver(firefox.getFirefoxCapabilities());
				
			case Edge:
				EdgeBrowser Edge = EdgeBrowser.class.newInstance();
				return Edge.getEdgeDriver();

		
			default:
				throw new NoSuitableDriverFoundException(" Driver Not Found : "
						+ ObjectRepo.browser);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	public void setUpDriver(BrowserType bType) throws Exception {
		ObjectRepo.driver = standAloneStepUp(bType);
		ObjectRepo.driver
				.manage()
				.timeouts()
				.pageLoadTimeout(ObjectRepo.reader.getPageLoadTimeOut(),
						TimeUnit.SECONDS);
		ObjectRepo.driver
				.manage()
				.timeouts()
				.implicitlyWait(ObjectRepo.reader.getImplicitWait(),
						TimeUnit.SECONDS);
		ObjectRepo.driver.manage().window().maximize();

	}

	public static void tearDownDriver(){
		try {
			if (ObjectRepo.driver != null) {
				ObjectRepo.driver.quit();
				ObjectRepo.reader = null;
				ObjectRepo.driver = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
