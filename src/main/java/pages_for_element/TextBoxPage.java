package pages_for_element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baseLibrary.BaseLibrary;
import driverfactory.DriverFactory;

public class TextBoxPage extends BaseLibrary {

	private static final Logger log = LoggerFactory.getLogger(TextBoxPage.class);
	
	public TextBoxPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	//
	@FindBy(xpath="")
	private WebElement closebutton;
	
	@FindBy(xpath="")
	private WebElement practicebutton;
	
	@FindBy(xpath="")
	private WebElement elementbutton;
	
	@FindBy(xpath="")
	private WebElement textboxbutton;
	
	@FindBy(xpath="")
	private WebElement fullnamefield;
	
	@FindBy(xpath="")
	private WebElement emailfield;
	
	@FindBy(xpath="")
	private WebElement currentadressfield;
	
	@FindBy(xpath="")
	private WebElement permanentaddressfield;
	
	
	public void clickOnClose() {
		waitForClickability(closebutton);
	}
	
	public void clickOnPractice() {
		waitForClickability(practicebutton);
	}
	
	public void clickOnElement() {
		waitForClickability(elementbutton);
	}
	
	public void clickOnTextBox() {
		waitForClickability(textboxbutton);
	}
	
	public void fillAllTheDetails() {
		
	}
}
