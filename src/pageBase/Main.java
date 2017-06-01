package pageBase;

import com.aventstack.extentreports.ExtentTest;

public abstract class Main extends pageBase.Utilities{

		
	public WebDriverFactory webDriverFactory() {
		return new WebDriverFactory();
	}
	
	public Utilities utilities() {
		return new Utilities();
	}

	public ExtentManager extentManager() {
		return new ExtentManager();
	}
	
	public ExtentTest extentTest() {
		return null;
	
	}
}
