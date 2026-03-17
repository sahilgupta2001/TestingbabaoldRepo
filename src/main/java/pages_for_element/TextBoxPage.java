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
	
	// -------------------------------------------------- //
	// ------------------- Constructor ------------------ //
	// -------------------------------------------------- //
	
	public TextBoxPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	// -------------------------------------------------- //
	//						WebElements 				  //
	// -------------------------------------------------- //
	//
	@FindBy(xpath="//button[contains(text(),'×')]")
	private WebElement closebutton;
	
	@FindBy(xpath="//a[contains(text(), 'Practice')]")
	private WebElement practicebutton;
	
	@FindBy(xpath="//button[@data-target='#elements']")
	private WebElement elementbutton;
	
	@FindBy(xpath="//a[contains(text(), 'text box')]")
	private WebElement textboxbutton;
	
	@FindBy(xpath="//input[@id='fullname1']")
	private WebElement fullnamefield;
	
	@FindBy(xpath="//input[@id='fullemail1']")
	private WebElement emailfield;
	
	@FindBy(xpath="//textarea[@id='fulladdresh1']")
	private WebElement currentaddressfield;
	
	@FindBy(xpath="//textarea[@id='paddresh1']")
	private WebElement permanentaddressfield;
	
	// -------------------------------------------------- //
	//						Actions		 				  //
	// -------------------------------------------------- //
	
	public void clickOnClose() {
		waitForClickability(closebutton).click();
	}
	
	public void clickOnPractice() {
		waitForClickability(practicebutton).click();
	}
	
	public void clickOnElement() {
		waitForClickability(elementbutton).click();
	}
	
	public void clickOnTextBox() {
		waitForClickability(textboxbutton).click();
	}
	
	public void fillAllTheDetails() {
		String fullName = getReadData(0, 0, 1);
		String email = getReadData(0, 1, 1);
		String currentAddress = getReadData(0, 2, 1);
		String permanentAddress = getReadData(0, 3, 1);
		
		validateExcelData(fullName, email, currentAddress, permanentAddress);
		
		log.info("Filling TextBox form with Name: {}, email: {}", fullName, email);
		
		waitForVisibility(fullnamefield).clear();
		fullnamefield.sendKeys(fullName);
		log.debug("Entered Full Name: {}", fullName);
		
		waitForVisibility(emailfield).clear();
		emailfield.sendKeys(email);
		log.debug("Entered Email : {}", email);
		
		waitForVisibility(currentaddressfield).clear();
		currentaddressfield.sendKeys(currentAddress);
		log.debug("Entered Current Address: {}", currentAddress);
		
		waitForVisibility(permanentaddressfield).clear();
		permanentaddressfield.sendKeys(permanentAddress);
		log.debug("Entered Permanent Address: {}", permanentAddress);
		
	}

	private void validateExcelData(String fullName, String email, String currentAddress, String permanentAddress) {
		// TODO Auto-generated method stub
		
	}
}
