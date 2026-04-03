package tests_for_element;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseLibrary.BaseLibrary;
import pages_for_element.WebTablesPage;

public class WebTablesTest extends BaseLibrary {
	
	private WebTablesPage webtablespage;
	
	@BeforeMethod
	public void initpages() {
		webtablespage = new WebTablesPage();
	}
	
	@Test(priority=1,
			groups={ "smoke", "regression"})
	public void clickOnClose() {
		webtablespage.clickOnClose();
	}
	
	@Test(priority=2,
			groups= { "smoke", "regression"})
	public void clickOnPractice() {
		webtablespage.clickOnPractice();
	}
	
	@Test(priority=3,
			groups= {"regression"})
	public void clickOnElements() {
		webtablespage.clickOnElements();
	}
	
	@Test(priority=4,
			groups= {"regression"})
	public void clickOnWebTables() {
		webtablespage.clickOnWebTables();
	}
	
}
