package pages_for_element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseLibrary.BaseLibrary;

public class ElementPage extends BaseLibrary{
	
	// Constructor of the class
	public ElementPage() {
		PageFactory.initElements(driver, this);
	}
	
	// All xpath for elements
	
	// Close xpath
	@FindBy(xpath="//button[contains(text(), '×')]")
	private WebElement close;
	
	// Practice xpath
	@FindBy(xpath="")
	private WebElement practice;
	
	// Elements xpath
	@FindBy(xpath="")
	private WebElement elements;
}
