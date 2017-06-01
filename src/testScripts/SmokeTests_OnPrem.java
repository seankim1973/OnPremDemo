package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageBase.*;
import testBase.TestHook;


public class SmokeTests_OnPrem extends TestHook {

	@Test
	public void performSearchGetMemberCount() throws Exception {	
		
		String searchText = "partner";	
		
		Click(OnPrem._MainNav().btnTeam);
			
		int searchResultCount = OnPrem._TeamPage().performSearchAndGetCount(searchText);
				
		Assert.assertTrue(searchResultCount > 0);
		
		String output = searchResultCount + " " + searchText + " team members were found.";
		System.out.println(output);
		logger(output);
	}

	
	@Test
	public void getMemberNameTitle() throws Exception {
				
		String searchText = "analyst";
		String memberNameTitle = null;
		int memberCount = 0;
			
		Click(OnPrem._MainNav().btnTeam);
		OnPrem._TeamPage().performSearchByText(searchText);
		
		memberCount = OnPrem._TeamPage().getTeamMemberCount();
		memberNameTitle = OnPrem._TeamPage().getTeamMemberNameAndTitle();
		
		Assert.assertTrue(memberCount > 0);
		
		System.out.println("The folowing " + memberCount + " have been found: /n" + memberNameTitle);	
		String output = "The folowing " + memberCount + " have been found: </br>" + memberNameTitle;
		logger(output);		
	}
}
