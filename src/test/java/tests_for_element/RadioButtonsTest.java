package tests_for_element;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseLibrary.BaseLibrary;
import pages_for_element.RadioButtonsPage;

public class RadioButtonsTest extends BaseLibrary {

	private RadioButtonsPage radiobuttonspage;
	
	@BeforeMethod
	public void initPages() {
		radiobuttonspage = new RadioButtonsPage();
	}
	
	@Test(priority = 1,
			groups = {"smoke", "regression"})
	public void clickOnClose() {
		radiobuttonspage.clickOnClose();
	}
	
	@Test(priority = 2,
			groups = {"smoke", "regression"})
	public void clickOnPractice() {
		radiobuttonspage
				.clickOnClose()
				.clickOnPractice();
	}
	
	@Test(priority = 3,
			groups = {"smoke", "regression"})
	public void clickOnElements() {
		radiobuttonspage
				.clickOnClose()
				.clickOnPractice()
				.clickOnElements();
	}
	
	@Test(priority = 4,
			groups = {"smoke", "regression"})
	public void clickOnRadioButtons() {
		radiobuttonspage
				.clickOnClose()
				.clickOnPractice()
				.clickOnElements()
				.clickOnRadioButtons();
	}
	
	// ---------------------------------------------------- //
	//                   Regression Tests                   //
	// ---------------------------------------------------- //

	@Test(priority = 5,
			groups = {"regression"})
	public void verifyYesRadioButtonsSelection() {
		radiobuttonspage
				.clickOnClose()
				.clickOnPractice()
				.clickOnElements()
				.clickOnRadioButtons()
				.checkYes();
		
		Assert.assertTrue(
				radiobuttonspage.isYesChecked(), 
				"Yes radio buttons should be selected after clicking it.");
		
		Assert.assertTrue(
				radiobuttonspage.isYesMessageDisplayed(), 
				"Yes selection message should be visible after checking Yes.");
		}
	
	@Test(priority = 6,
			groups = {"regression"})
	public void verifyImpressiveRadioButtonsSelection() {
		radiobuttonspage
				.clickOnClose()
				.clickOnPractice()
				.clickOnElements()
				.clickOnRadioButtons()
				.checkImpressive();
		
		Assert.assertTrue(
				radiobuttonspage.isImpressiveChecked(),
				"Impressive radio buttons should be selected after clicking it.");
		
		Assert.assertTrue(
				radiobuttonspage.isImpressiveMessageDisplayed(),
				"Impressive selection message should be visible after checking Impressive.");
	}
	
	@Test(priority = 7,
			groups = {"regression"})
	public void verifyNoRadioButtonsSelection() {
		radiobuttonspage
				.clickOnClose()
				.clickOnPractice()
				.clickOnElements()
				.clickOnRadioButtons()
				.checkNo();
		
		Assert.assertTrue(
				radiobuttonspage.isNoChecked(),
				"No checkbox should be selected after clicking it.");
		
		Assert.assertTrue(
				radiobuttonspage.isNoMessageDisplayed(),
				"No selection message should be visible after checking No.");
	}
	
	@Test(priority = 8, 
			groups = {"regression"})
	public void verifyRadioButtonSelectionSwitches() {
	    radiobuttonspage
	            .clickOnClose()
	            .clickOnPractice()
	            .clickOnElements()
	            .clickOnRadioButtons()
	            .checkYes();

	    Assert.assertTrue(radiobuttonspage.isYesChecked(), "Yes should be selected.");

	    radiobuttonspage.checkImpressive();

	    // After selecting Impressive, Yes must be deselected
	    Assert.assertFalse(radiobuttonspage.isYesChecked(),       "Yes should be deselected after selecting Impressive.");
	    Assert.assertTrue(radiobuttonspage.isImpressiveChecked(), "Impressive should now be selected.");
	}
	
	
}
