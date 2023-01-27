package interfaces;

/*******************************************************************************************************
* @author  Vikas Sangwan
* @since   2016-04-15
********************************************************************************************************/


 public interface IconfigReader {
	 
	public String getWebsite();
	public int getPageLoadTimeOut();
	public int getImplicitWait();
	public int getExplicitWait();
	public String getWaitTime();
	public String getExecutionEnv();
	public String getReportOverwrite();
	public String getRemoteExecutionEnvURL();
	public int getWaitForAnswer();
	
}
 