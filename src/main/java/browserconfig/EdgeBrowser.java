package browserconfig;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utility.ResourceHelper;







public class EdgeBrowser {
	
	public Capabilities getEdgeCapabilities() {
		EdgeOptions edgeOptions = new EdgeOptions();
		return edgeOptions;
	}
	
	public WebDriver getEdgeDriver() {
		System.setProperty("webdriver.edge.driver", ResourceHelper.getResourcePath("driver/msedgedriver.exe"));
		//System.setProperty("webdriver.edge.logfile",ResourceHelper.getResourcePath("logs/edgelogs/")+ "edge" + DateTimeHelper.getCurrentDateTime()+ ".log");
		return new EdgeDriver();
	}
	
	public WebDriver getIExplorerDriver(String hubUrl,Capabilities cap) throws MalformedURLException {
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}

}
