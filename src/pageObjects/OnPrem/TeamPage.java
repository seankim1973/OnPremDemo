package pageObjects.OnPrem;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TeamPage extends pageBase.Main{
			
	public TeamPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using ="//input[@id='myInput']")
	public WebElement inputSearchBox;
	
	@FindBy(how = How.ID, using = "staff-list")
	private WebElement divStaffList; 
	
	@FindBy(how = How.CLASS_NAME, using ="staff-member-holder")
	private List<WebElement> divStaffMemberHolderList;

	@FindBy(how = How.XPATH, using ="//div[@class='staff-member-holder'][contains(@style, 'display: inline-block')]")
	private List<WebElement> inlineStaffMemberHolderElement;
	
	@FindBy(how = How.XPATH, using = "//div[@id='no-team-results'][contains(@style, 'display: inline-block')]")
	private WebElement noResultsFoundMsgElement;
	
	
	private int resultCount = 0;
	private String memberNameTitle;
	private List<WebElement> elementsList = null;
	
	
	//Returns true if staff-member-holder div tags are present	
	private Boolean isStaffMemberHolderDivPresent() {
		
		if(divStaffMemberHolderList.size() >= 1) {
			return true;		
		}
		else {		
			return false;			
		}
	}
	
	
	public void performSearchByText(String searchText) {
		
		try {
			//Checks for staff-member-holder div tags while team search iframe is loading
			if(isStaffMemberHolderDivPresent() == false) {			
				WebDriverWait wait = (new WebDriverWait (driver, 5));
				divStaffMemberHolderList = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(divStaffList, By.className("staff-member-holder")));							
			}
			//Performs search using searchText value
			else {
				inputSearchBox.sendKeys(searchText);
				elementsList = inlineStaffMemberHolderElement;				
			}		
		}
		catch (Exception e) {		
			e.printStackTrace();		
		}
	}
	
		
	//Returns number of team members resulted from search
	public int performSearchAndGetCount(String searchText) {
		
		performSearchByText(searchText);
		resultCount = elementsList.size();
		return resultCount;
	}
	
		
	//Returns search result elements
	private List<WebElement> getTeamMemberElements() {
		
		elementsList = inlineStaffMemberHolderElement;
		return elementsList;	
	}
	
	public int getTeamMemberCount() {
		
		getTeamMemberElements();
		resultCount = elementsList.size();
		return resultCount;
	}
	
	
	//Returns names of team members resulted from search
	public String getTeamMemberNameAndTitle() {
				
		StringBuilder builder = new StringBuilder();		
		getTeamMemberElements();
		WebElement staffMemberElement;
		
		for(WebElement element : elementsList) {
			staffMemberElement = (WebElement)element.findElement(By.className("staff-member"));
			String name = staffMemberElement.getAttribute("data-name");
			String title = staffMemberElement.getAttribute("data-title");
			
			builder.append(name + " : " + title);
			builder.append("\n");
		}
		
		memberNameTitle = builder.toString();
		return memberNameTitle;
	}
	
}
