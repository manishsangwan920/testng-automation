package utility;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2016-04-15
********************************************************************************************************/

public class ResourceHelper {

	public static String getResourcePath(String resource) {
		String path = getBaseResourcePath()+"/src/main/resources/" + resource;
		return path;
	}
	
	public static String getBaseResourcePath() {
		String path =System.getProperty("user.dir");
		return path;
	}
	
	public static InputStream getResourcePathInputStream(String resource) throws FileNotFoundException {
		return new FileInputStream(ResourceHelper.getResourcePath(resource));
	}
	
}
