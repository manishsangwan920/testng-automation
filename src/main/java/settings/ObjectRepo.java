
package settings;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;

import interfaces.IconfigReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2016-04-15
********************************************************************************************************/

public class ObjectRepo {
	public static WebDriver driver;
	public static IconfigReader reader;
	public static ExtentTest test;
	public static ExtentReports report;
	public static Row testDataVariables;
	public static Row testData;
	public static String browser;
	public static String Environment;
	public static String Instance;
	public static String QA;
	public static String Prod;
}

