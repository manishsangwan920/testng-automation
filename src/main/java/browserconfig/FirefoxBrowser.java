
package browserconfig;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ResourceHelper;

/*******************************************************************************************************
* @author  Shafkat Mustafa
* @since   2016-04-15
********************************************************************************************************/

public class FirefoxBrowser {
	
	public Capabilities getFirefoxCapabilities() {
		DesiredCapabilities firefox = DesiredCapabilities.firefox();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("marionette", true);
		return firefox;
	}
	
	public WebDriver getFirefoxDriver(Capabilities cap) {
		//WebDriverManager.firefoxdriver().setup();
		System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath("driver/geckodriver.exe"));
		return new FirefoxDriver(cap);
	}
	


}
