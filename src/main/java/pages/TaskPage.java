package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class TaskPage extends PageBase {
	
	private WebDriver driver;
	public TaskPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	/** Web Elements */
	
	
	
	@FindBy(xpath = "//button[@data-target='#myModal']")
	@CacheLookup
	public  WebElement createTask;
	
	//create task pop-up start
	
	@FindBy(xpath = "//h4[contains(text(),'Create Task')]")
	@CacheLookup
	public  WebElement TitleCreateTask;
	
	@FindBy(id = "taskName1")
	@CacheLookup
	public  WebElement TaskName;
	
	@FindBy(id = "createDueDate")
	@CacheLookup
	public  WebElement DueDate;
	
	@FindBy(id = "taskDescription")
	@CacheLookup
	public  WebElement TaskDescription;
	
	@FindBy(id = "selectedAccount")
	@CacheLookup
	public  WebElement SelectedAccount;
	
	@FindBy(id = "enggGroup")
	@CacheLookup
	public  WebElement ServiceEngineer;
	
	@FindBy(id = "taskType")
	@CacheLookup
	public  WebElement TaskType;
	
	@FindBy(id = "watcherGroup1")
	@CacheLookup
	public  WebElement DisabledWatchers;
	
	@FindBy(xpath = "//*[@id=\"MachineDis\"]/span/select")
	@CacheLookup
	public  WebElement Disabledmachine;
	
	@FindBy(id = "noneBtn")
	@CacheLookup
	public  WebElement ScheduleNoneBtn;
	
	@FindBy(id = "dailyBtn")
	@CacheLookup
	public  WebElement ScheduleDailyBtn;
	
	@FindBy(id = "weeklyBtn")
	@CacheLookup
	public  WebElement ScheduleWeeklyBtn;
	
	@FindBy(id = "monthlyBtn")
	@CacheLookup
	public  WebElement ScheduleMonthlyBtn;

	@FindBy(id = "taskRepeatEndDate")
	@CacheLookup
	public  WebElement taskRepeatEndDate;
	
	@FindBy(id = "selectWeek")
	@CacheLookup
	public  WebElement selectWeek;
	
	@FindBy(xpath = "//button[@onclick='resetMyModalPopUp()']")
	@CacheLookup
	public  WebElement CloseCreateTaskPopUp;
	
	@FindBy(id = "createTaskEvent")
	@CacheLookup
	public  WebElement SaveCreatedTask;
		
	@FindBy(xpath = "//*[@id=\"WatchersFunc\"]/div/button")
	@CacheLookup
	public  WebElement EnabledWatcher;
	
	@FindBy(id = "MachineFunc")
	@CacheLookup
	public  WebElement Enabledmachine;
	
	@FindBy(xpath = "//*[@id='WatchersFunc']/div/ul/li")
	@CacheLookup
	public List<WebElement> WatcherList;
	
	
	//create task pop up ends
	
		
	@FindBy(xpath = "//tbody[@id='reRender']/tr[1]/td[1]")
	@CacheLookup
	public  WebElement verifyTaskName;
	
	@FindBy(xpath = "//*[@id=\"reRender\"]/tr[1]/td[7]/button[3]")
	@CacheLookup
	public  WebElement DeleteTask;
	
	
	
	
	
	
	

}
