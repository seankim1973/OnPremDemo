package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class TestHook extends TestBase{
			
	public void Click(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait (driver, 10);
		
		if(element.isDisplayed() == false) {
			driver = (WebDriver) wait.until(ExpectedConditions.elementToBeClickable(element));	
		}
		else {
			element.click();
		}
	}
	
	public void EnterText(WebElement element, String text) {
		
		WebDriverWait wait = new WebDriverWait (driver, 10);
				
		if(element.isDisplayed() == false) {
			driver = (WebDriver) wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		else {
			element.sendKeys(text);
		}		
	}
	
	
	
}
