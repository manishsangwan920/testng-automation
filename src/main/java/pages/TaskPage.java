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
	
	@FindBy(id = "taskRepeatEndDate2")
	@CacheLookup
	public  WebElement MonthlytaskRepeatEndDate;
	
	@FindBy(id = "taskRepeatEndDate1")
	@CacheLookup
	public  WebElement WeeklytaskRepeatEndDate;
	
	@FindBy(id = "selectWeek")
	@CacheLookup
	public  WebElement selectWeek;
	
	@FindBy(id = "selectMonth")
	@CacheLookup
	public  WebElement selectMonth;
	

	@FindBy(id = "selectDate")
	@CacheLookup
	public  WebElement selectDate;
	
	@FindBy(id = "selectDate")
	@CacheLookup
	public List<WebElement> selectDateList;
	
	
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
	
	@FindBy(xpath = "//*[@id=\"weeklyBlock\"]/div[1]")
	@CacheLookup
	public  WebElement dayOfTheWeek;
	
	@FindBy(xpath = "//*[@id='WatchersFunc']/div/ul/li")
	@CacheLookup
	public List<WebElement> WatcherList;
	
	@FindBy(xpath = "//*[@id='MachineFunc']/div/ul/li")
	@CacheLookup
	public List<WebElement> MachineList;
	
	@FindBy(xpath = "//*[@id='MachineFunc']/div/ul/li[@class='active']")
	@CacheLookup
	public WebElement MachineOneActiveFields;
	
	@FindBy(xpath = "//*[@id='MachineFunc']/div/ul/li[@class='active']")
	@CacheLookup
	public List<WebElement> MachineActiveFieldsList;
	
	@FindBy(xpath = "//*[@id='MachineFunc']/div/button")
	@CacheLookup
	public WebElement MachinesFeildTitle;
	
	@FindBy(xpath = "//label[@for='option-e']")
	@CacheLookup
	public  WebElement Fri;
	
	@FindBy(id = "enggGroup")
	@CacheLookup
	public List<WebElement> listServiceEngineer;
	
	@FindBy(id = "selectedAccount")
	@CacheLookup
	public List<WebElement> ListAccount;
		
	@FindBy(xpath = "//*[@id=\"MachineFunc\"]/div/button/span")
	@CacheLookup
	public  WebElement verifySelectedMachine ;
	
	//create task pop up ends
	
	//edit task pop up start
	
	@FindBy(id = "editTaskName")
	@CacheLookup
	public WebElement editTaskName;

	@FindBy(id = "editDueDate")
	@CacheLookup
	public WebElement editDueDate;
	
	@FindBy(id = "editSelectedAccount")
	@CacheLookup
	public WebElement editSelectedAccount;
	
	@FindBy(id = "editWatcherGroup")
	@CacheLookup
	public WebElement editWatcherGroup;
	
	@FindBy(id = "editTaskType")
	@CacheLookup
	public WebElement editTaskType;
	
	@FindBy(id = "editNoneBtn")
	@CacheLookup
	public WebElement editNoneBtn;
	
	@FindBy(id = "editDailyBtn")
	@CacheLookup
	public WebElement editDailyBtn;
	
	@FindBy(id = "editWeeklyBtn")
	@CacheLookup
	public WebElement editWeeklyBtn;
	
	@FindBy(id = "editMonthlyBtn")
	@CacheLookup
	public WebElement editMonthlyBtn;

	@FindBy(xpath = "//*[@id=\"EditTask\"]/div/div/div[1]/button")
	@CacheLookup
	public WebElement closeEditPopup;
	
	//edit task pop up ends
		
	@FindBy(xpath = "//tbody[@id='reRender']/tr[1]/td[1]")
	@CacheLookup
	public  WebElement verifyTaskName;
	
	@FindBy(xpath = "//tbody[@id='reRender']/tr[1]/td[2]")
	@CacheLookup
	public  WebElement verifyAccountName;
	
	@FindBy(xpath = "//tbody[@id='reRender']/tr[1]/td[3]")
	@CacheLookup
	public  WebElement verifyServiceEngineerName;
	
	@FindBy(xpath = "//tbody[@id='reRender']/tr[1]/td[4]")
	@CacheLookup
	public  WebElement verifyDueDate;
	
	@FindBy(xpath = "//tbody[@id='reRender']/tr[1]/td[5]")
	@CacheLookup
	public  WebElement VerifyTaskStatus;
	
	@FindBy(xpath = "//tbody[@id='reRender']/tr[1]/td[6]/button")
	@CacheLookup
	public  WebElement addComment;
	
	@FindBy(xpath = "//*[@id=\"commentsBox321\"]/dIV[1]/P[2]")
	@CacheLookup
	public  WebElement VerifyComment;
	
	@FindBy(id = "comment")
	@CacheLookup
	public WebElement writeComment;
	
	@FindBy(id = "commentBtn")
	@CacheLookup
	public WebElement addCommentbtnPop;
	
	@FindBy(xpath = "//*[@id=\"AddComment\"]/div/div/div[1]/button")
	@CacheLookup
	public  WebElement closecommentpopup;
	
	@FindBy(xpath = "//*[@id=\"reRender\"]/tr[1]/td[7]/button[3]")
	@CacheLookup
	public  WebElement DeleteTask;
	
	
	@FindBy(xpath = "//*[@id=\"reRender\"]/tr[1]/td[7]/button[1]")
	@CacheLookup
	public  WebElement editTask;
	
	
	@FindBy(xpath = "//*[@id=\"reRender\"]/tr[1]/td[7]/button[2]")
	@CacheLookup
	public  WebElement DownloadTask;
		
	@FindBy(xpath = "//*[@id='Instruction_Task_Excel']/div/div/div/button[@class='close']")
	@CacheLookup
	public  WebElement CloseDownloadExcel;	
	
	@FindBy(xpath = "//a[@target='_blank']/img")
	@CacheLookup
	public  WebElement DownloadSampleExcelonPopUP;
	
	@FindBy(xpath = "//span[contains(text(),'Download Excel')]")
	@CacheLookup
	public  WebElement DownloadExcel;
	
	
	@FindBy(xpath = "//button[@data-target='#uploadCSV']/span")
	@CacheLookup
	public  WebElement UploadExcelButton;
	
	
	@FindBy(xpath = "//input[@id='excelFile']")
	@CacheLookup
	public  WebElement UploadExcelInput;
	
	@FindBy(xpath = "//*[@id='uploadTask']")
	@CacheLookup
	public  WebElement UploadExcelButtonPopup;
	
	
	@FindBy(xpath = "//*[@id='failedTaskRender']/tr/td[8]")
	@CacheLookup
	public  WebElement TaskFailLog;
	
	
	@FindBy(id = "dateInput")
	@CacheLookup
	public WebElement DateFilter;
	
	
	@FindBy(xpath = "//button[@onclick='resetData()']")
	@CacheLookup
	public  WebElement ResetFilters;
	

	@FindBy(id = "taskdrop")
	@CacheLookup
	public  WebElement statusFilter;
}
