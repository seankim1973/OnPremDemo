package pageBase;

import pageObjects.OnPrem.MainNav;
import pageObjects.OnPrem.TeamPage;

public abstract class OnPrem extends pageBase.Main  {
	
	public static MainNav _MainNav() {
		return new MainNav(driver);
	}
	
	public static TeamPage _TeamPage() {
		return new TeamPage(driver);
	}
	
}
