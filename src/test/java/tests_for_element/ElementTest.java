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
	
	@Test(priority=1)
	public void clickOnClose() {
		elementpage.clickOnClose();
	}
	
	@Test(priority=2)
	public void clickOnPractice() {
		elementpage.clickOnPractice();
	}
	
	@Test(priority=3)
	public void clickOnElements() {
		elementpage.clickOnElements();
	}
}
