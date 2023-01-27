
package browserconfig;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2020-04-15
********************************************************************************************************/

public class RemoteChromeBrowser {

	public Capabilities getRemoteChromeCapabilities() {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("browserName", "chrome");
		return cap;
	}

	public WebDriver getRemoteChromeDriver(String hubUrl,Capabilities cap) throws MalformedURLException {
		//BasicConfigurator.configure();
		WebDriverManager.chromedriver().setup();
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}

}
