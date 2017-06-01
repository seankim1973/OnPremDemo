package pageBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class Utilities extends WebDriverFactory{
	
	public static String captureScreenshot(WebDriver driver, ExtentTest child) throws Exception {
				
		String methodName = child.toString();
		String timeStamp = getDate();
		String screenshotName = (methodName + timeStamp).toString();	

		Thread.sleep(1000);
		TakesScreenshot scrn = (TakesScreenshot)driver;
		File source = scrn.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+ "/ErrorScreenshot/" + screenshotName + ".png";
		File destination = new File(dest);

		FileUtils.copyFile(source, destination);
		
		return dest;
	}
		
	public static String getDate() {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date().getTime());
		return timeStamp;
	}
		
}
