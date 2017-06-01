package pageBase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

	public static WebDriver driver;
	private static DesiredCapabilities cap = null;
	
	public WebDriver getWebDriver(String platform, String browserName, String remoteUrl) throws MalformedURLException {
					
		cap = new DesiredCapabilities();
		
		if (platform.equals("WIN8_1")) {
			cap.setPlatform(Platform.WIN8_1);
			getSystemProperty(platform, browserName);
			getRemoteWebDriverBrowser(browserName);
			driver = new RemoteWebDriver(new URL(remoteUrl), cap);
		}
		else if (platform.equals("MAC")) {
			cap.setPlatform(Platform.MAC);
			getSystemProperty(platform, browserName);
			getRemoteWebDriverBrowser(browserName);
			driver = new RemoteWebDriver(new URL(remoteUrl), cap);
		}
		else if (platform.equals("")) {
			driver = getLocalWebDriver(browserName);
		}
	
		return driver;
	}
	
	private String getSystemProperty(String platform, String browserName) {
			
		String systemProperty = null;
		
		if (platform.toLowerCase().contains("win")) {
			
			if (browserName.equals("firefox")) {
				systemProperty = System.setProperty("webdriver.gecko.driver", "C:\\Automation\\geckodriver.exe");
			}
			else if (browserName.equals("chrome")) {
				systemProperty = System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
			}
			else if (browserName.equals("MicrosoftEdge")) {
				systemProperty = System.setProperty("webdriver.edge.driver", "C:\\Automation\\MicrosoftWebDriver.exe");
			}
		}
		else if (platform.toLowerCase().contains("mac")) {
			
			if (browserName.equals("chrome")) {
				systemProperty = System.setProperty("webdriver.chrome.driver", "/OnPremDemo/lib/chromedriver_mac64.zip");
			}
			else if (browserName.equals("firefox")) {
				systemProperty = System.setProperty("webdriver.gecko.driver", "/OnPremDemo/lib/geckodriver-v0.16.1-macos.tar.gz");
			}
			else if (browserName.equals("safari")) {
				systemProperty = System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
			}
		}
		
		return systemProperty;
	}
	
	private String getSystemProperty(String browserName) {
		
		String systemProperty = null;
		if (browserName.equals("firefox")) {
			systemProperty = System.setProperty("webdriver.gecko.driver", "/Users/seankim/Downloads/geckodriver");
		}
		else if (browserName.equals("chrome")) {
			systemProperty = System.setProperty("webdriver.chrome.driver", "/Users/seankim/Documents/workspace/drivers/chromedriver_229");
		}
		else if (browserName.equals("MicrosoftEdge")) {
			systemProperty = System.setProperty("webdriver.edge.driver", "C:\\Automation\\MicrosoftWebDriver.exe");
		}
		else if (browserName.equals("safari")) {
			systemProperty = System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");

		}
		
		return systemProperty;
	}
	
	protected DesiredCapabilities getRemoteWebDriverBrowser (String browserName) {
				
		if (browserName.equals("firefox")) {
			cap = DesiredCapabilities.firefox();
		}
		else if (browserName.equals("chrome")) {
			cap = DesiredCapabilities.chrome();
		}
		else if (browserName.equals("MicrosoftEdge")) {
			cap = DesiredCapabilities.edge(); 
		}
		else if (browserName.equals("safari")) {
			cap = DesiredCapabilities.safari();
		}
		
		return cap;		
	}
	
	protected WebDriver getLocalWebDriver(String browserName) {
		
		if (browserName.equalsIgnoreCase("firefox")) {
			getSystemProperty(browserName);
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("chrome")) {
			getSystemProperty(browserName);
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("MicrosoftEdge")) {
			getSystemProperty(browserName);
			driver = new InternetExplorerDriver();
		}
		else if (browserName.equalsIgnoreCase("safari")) {
			getSystemProperty(browserName);
			driver = new SafariDriver();
		}
		
		return driver;
	}

}
