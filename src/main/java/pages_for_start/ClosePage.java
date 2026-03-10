package pages_for_start;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseLibrary.BaseLibrary;
import driverfactory.DriverFactory;

public class ClosePage extends BaseLibrary {

	// ---------------------------------------------------- //
	//                     Constructor                      //
	// ---------------------------------------------------- //

	public ClosePage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	// ---------------------------------------------------- //
	//                     Web Elements                     //
	// ---------------------------------------------------- //

	@FindBy(xpath = "//button[contains(text(), '×')]")
	private WebElement close;

	// ---------------------------------------------------- //
	//                       Actions                        //
	// ---------------------------------------------------- //

	public void clickOnClose() {
		close.click();
	}
}