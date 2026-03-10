package tests_for_start;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseLibrary.BaseLibrary;
import pages_for_start.ClosePage;

public class CloseTest extends BaseLibrary {

	// ---------------------------------------------------- //
	//                    Page Objects                      //
	// ---------------------------------------------------- //

	private ClosePage closePage;

	// ---------------------------------------------------- //
	//                      Setup                           //
	// ---------------------------------------------------- //

	@BeforeMethod
	public void initPages() {
		closePage = new ClosePage();
	}

	// ---------------------------------------------------- //
	//                      Tests                           //
	// ---------------------------------------------------- //

	@Test
	public void clickOnClose() {
		closePage.clickOnClose();
	}
}