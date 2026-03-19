package tests_for_element;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseLibrary.BaseLibrary;
import pages_for_element.CheckBoxPage;

public class CheckBoxTest extends BaseLibrary {
	
	// ---------------------------------------------------- //
	//                    Page Objects                      //
	// ---------------------------------------------------- //
 
	private CheckBoxPage checkboxpage;
 
	// ---------------------------------------------------- //
	//                      Setup                           //
	// ---------------------------------------------------- //
 
	@BeforeMethod
	public void initPages() {
		checkboxpage = new CheckBoxPage();
	}
 
	// ---------------------------------------------------- //
	//                    Smoke Tests                       //
	// ---------------------------------------------------- //
 
	@Test(priority = 1,
			groups = {"smoke", "regression"})
	public void clickOnClose() {
		checkboxpage.clickOnClose();
	}
	
	@Test(priority = 2,
			groups = {"smoke", "regression"})
	public void clickOnPractice() {
		checkboxpage
				.clickOnClose()
				.clickOnPractice();
	}
	
	@Test(priority = 3,
			groups = {"smoke", "regression"})
	public void clickOnElements() {
		checkboxpage
				.clickOnClose()
				.clickOnPractice()
				.clickOnElements();
	}
	
	@Test(priority = 4,
			groups = {"smoke", "regression"})
	public void clickOnCheckBox() {
		checkboxpage
				.clickOnClose()
				.clickOnPractice()
				.clickOnElements()
				.clickOnCheckBox();
	}
	
	// ---------------------------------------------------- //
	//                   Regression Tests                   //
	// ---------------------------------------------------- //

	@Test(priority = 5,
			groups = {"regression"})
	public void verifyMobileCheckBoxSelection() {
		checkboxpage
				.clickOnClose()
				.clickOnPractice()
				.clickOnElements()
				.clickOnCheckBox()
				.checkMobile();
		
		Assert.assertTrue(
				checkboxpage.isMobileChecked(), 
				"Mobile checkbox should be selected after clicking it.");
		
		Assert.assertTrue(
				checkboxpage.isLaptopChecked(), 
				"");
		}
	}


