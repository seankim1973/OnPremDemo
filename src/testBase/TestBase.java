package testBase;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pageBase.Utilities;
import pageBase.WebDriverFactory;


public class TestBase extends pageBase.Main {
	
	WebDriver driver = WebDriverFactory.driver;
	
	private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private ExtentTest parent;
	private ExtentTest child;
    private static String filePath = (System.getProperty("user.dir") + "/test-output/Extent.html");
    
	public ExtentTest logger(String logDetails) {
    	ExtentTest logger;
    	logger = child.info(logDetails);
    	return logger;
    }
     
    @BeforeClass
    public synchronized void beforeClass() {
        parent = extent.createTest(getClass().getName());
        parentTest.set(parent);
    }

	@BeforeTest
	@Parameters({"platform","browserName", "remoteUrl"})
	public synchronized void beforeTest(@Optional String platform, String browserName, @Optional String remoteUrl)
			throws InterruptedException, MalformedURLException {
		
		extent = pageBase.ExtentManager.createInstance(filePath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		extent.attachReporter(htmlReporter);
		
		driver = webDriverFactory().getWebDriver(platform, browserName, remoteUrl);
		driver.manage().window().maximize();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.onprem.com/index.html");
		
		Thread.sleep(5000);	
	}
	
	@BeforeMethod
    public synchronized void beforeMethod(Method method) throws Exception {
        child = ((ExtentTest) parentTest.get()).createNode(method.getName());
        test.set(child);      
	}
	
	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws Exception {
        
		String screenshotPath = Utilities.captureScreenshot(driver, child);
		
		if (result.getStatus() == ITestResult.FAILURE) {
            ((ExtentTest) test.get()).fail(result.getThrowable());
            test.get().addScreenCaptureFromPath(screenshotPath);
        }    
        else if (result.getStatus() == ITestResult.SKIP) {
            ((ExtentTest) test.get()).skip(result.getThrowable());
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            ((ExtentTest) test.get()).pass("Test Passed");
            test.get().addScreenCaptureFromPath(screenshotPath);
        }	
		
		extent.flush();	
    }
	
	@AfterTest
	public synchronized void afterTest() {
			
		driver.close();	
	}
	
	@AfterClass
	public synchronized void afterClass() {
		
	}
	
	@AfterSuite
	public synchronized void afterSuite() {
		driver.quit();
	}
	
}
