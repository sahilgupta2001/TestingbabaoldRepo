package tests_for_element;

//import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseLibrary.BaseLibrary;
import pages_for_element.TextBoxPage;

public class TextBoxTest extends BaseLibrary {
	
	// -------------------------------------------- //
	// 				Page Objects 					//
	// -------------------------------------------- //
	
	private TextBoxPage textboxpage;
	
	// -------------------------------------------- //
	//				Setup							//
	// -------------------------------------------- //
	
	@BeforeMethod
	public void initPages() {
		textboxpage = new TextBoxPage();
	}
	
	// -------------------------------------------- //
	// 					Smoke Test					//
	// -------------------------------------------- //
	
	@Test(priority=1,
			groups = { "smoke", "regression"})
	public void clickOnClose() {
		textboxpage.clickOnClose();
	}
	
	@Test(priority=2,
			groups = { "smoke", "regression"})
	public void clickOnPractice() {
		textboxpage.clickOnClose();
		textboxpage.clickOnPractice();
	}
	
	@Test(priority=3,
			groups = { "smoke", "regression"})
	public void clickOnElement() {
		textboxpage.clickOnClose();
		textboxpage.clickOnPractice();
		textboxpage.clickOnElement();
	}
	
	@Test(priority=4,
			groups = { "smoke", "regression"})
	public void clickOnTextBox() {
		textboxpage.clickOnClose();
		textboxpage.clickOnPractice();
		textboxpage.clickOnElement();
		textboxpage.clickOnTextBox();
	}
	
	// -------------------------------------------- //
	// 					Regression Test				//
	// -------------------------------------------- //
	
	@Test(priority=5,
			groups = { "regression"})
	public void fillTextBoxForm() {
		textboxpage.clickOnClose();
		textboxpage.clickOnPractice();
		textboxpage.clickOnElement();
		textboxpage.clickOnTextBox();
		textboxpage.fillAllTheDetails();
	}
}
