package pageObjects.OnPrem;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainNav extends pageBase.Main {
		
	public MainNav(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "aboutus")
	public WebElement btnAboutUs;
	
	@FindBy(how = How.ID, using = "team")
	public WebElement btnTeam;
	
	@FindBy(how = How.ID, using = "services")
	public WebElement btnServices;
	
	@FindBy(how = How.ID, using = "careers")
	public WebElement btnCareers;
	
	@FindBy(how = How.ID, using = "contract")
	public WebElement btnContract;
	
}


