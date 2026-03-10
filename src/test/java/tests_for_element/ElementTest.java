package tests_for_element;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseLibrary.BaseLibrary;
import pages_for_element.ElementPage;

public class ElementTest extends BaseLibrary {

	// ---------------------------------------------------- //
	//                    Page Objects                      //
	// ---------------------------------------------------- //
	
	private ElementPage elementpage;
	
	// ---------------------------------------------------- //
	//                      Setup                           //
	// ---------------------------------------------------- //
	
	@BeforeMethod
	public void initPages() {
		elementpage = new ElementPage();
	}
	
	// ---------------------------------------------------- //
	//                      Tests                           //
	// ---------------------------------------------------- //
	
	@Test(priority=1, groups = { "smoke","regression"})
	public void clickOnClose() {
		elementpage.clickOnClose();
	}
	
	@Test(priority=2, groups = { "smoke", "regression"})
	public void clickOnPractice() {
		elementpage.clickOnClose();
		elementpage.clickOnPractice();
	}
	
	@Test(priority=3, groups = { "regression"})
	public void clickOnElements() {
		elementpage.clickOnClose();
		elementpage.clickOnPractice();
		elementpage.clickOnElements();
	}
}
