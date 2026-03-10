package pages_for_element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseLibrary.BaseLibrary;
import driverfactory.DriverFactory;

public class ElementPage extends BaseLibrary{
	
	// ---------------------------------------------------- //
	//                     Constructor                      //
	// ---------------------------------------------------- //

	public ElementPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	// ---------------------------------------------------- //
	//                     Web Elements                     //
	// ---------------------------------------------------- //
	
	// Close xpath
	@FindBy(xpath="//button[contains(text(), '×')]")
	private WebElement close;
	
	// Practice xpath
	@FindBy(xpath="//a[contains(text(), 'Practice')]")
	private WebElement practice;
	
	// Elements xpath
	@FindBy(xpath="//button[@data-target='#elements']")
	private WebElement elements;
	
	// ---------------------------------------------------- //
	//                       Actions                        //
	// ---------------------------------------------------- //

	public void clickOnClose() {
		close.click();
	}
	
	public void clickOnPractice() {
		practice.click();
	}
	
	public void clickOnElements() {
		elements.click();
	}
}
