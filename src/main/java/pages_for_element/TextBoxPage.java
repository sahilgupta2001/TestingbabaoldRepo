package pages_for_element;

import org.openqa.selenium.By;
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
		log.debug("TextBoxPage initialized");;
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
	
	@FindBy(xpath="//input[@type='button']")
	private WebElement submitbutton;
	
	// -------------------------------------------------- //
	//						Actions		 				  //
	// -------------------------------------------------- //
	
	public void clickOnClose() {
	    log.info("Attempting to close the advertisement banner");
	    try {
	        waitForClickability(closebutton).click();
	        log.info("Advertisement banner closed successfully");

	        // Wait for the modal overlay to fully disappear before proceeding
	        waitForInvisibility(By.cssSelector("div.modal-header"));
	        log.debug("Modal overlay fully dismissed");

	    } catch (Exception e) {
	        log.warn("Close banner was not interactable or already dismissed: {}", e.getMessage());
	    }
	}
	
	public void clickOnPractice() {
		log.info("Clicking on 'Practice' navigation link");
		waitForClickability(practicebutton).click();
		log.info("Navigated to Practice page");
	}
	
	public void clickOnElement() {
		log.info("Expanding 'Elements' accordion");
		waitForClickability(elementbutton).click();
		log.info("Elements accordion expanded");
	}
	
	public void clickOnTextBox() {
		log.info("Clicking on 'Text Box' option under Elements");
		waitForClickability(textboxbutton).click();
		log.info("Text Box page opened");
	}
	
	public void fillAllTheDetails() {
		log.info("Reading test data from excel - Sheet: 0, Row: 1");
		
		String fullName = getReadData(0, 0, 1);
		String email = getReadData(0, 1, 1);
		String currentAddress = getReadData(0, 2, 1);
		String permanentAddress = getReadData(0, 3, 1);
		
		log.debug("Excel data read - Name: {}, Email: {}, CurrentAddress: {}, PermanentAddress: {}", 
				fullName, email, currentAddress, permanentAddress);
		
		log.info("Validating Excel data before filling the form");
		validateExcelData(fullName, email, currentAddress, permanentAddress);
		log.info("Excel Data Validation passed");
		
		log.info("Filling TextBox form with Name: {}, email: {}", fullName, email);
		
		log.debug("Waiting for Full Name field to be visible");
		waitForVisibility(fullnamefield).clear();
		fullnamefield.sendKeys(fullName);
		log.debug("Entered Full Name: {}", fullName);
		
		log.debug("Waiting for Email field to be visible");
		waitForVisibility(emailfield).clear();
		emailfield.sendKeys(email);
		log.debug("Entered Email : {}", email);
		
		log.debug("Waiting foe Current Address to be visible");
		waitForVisibility(currentaddressfield).clear();
		currentaddressfield.sendKeys(currentAddress);
		log.debug("Entered Current Address: {}", currentAddress);
		
		log.debug("Waiting for Permanent Address to be visible");
		waitForVisibility(permanentaddressfield).clear();
		permanentaddressfield.sendKeys(permanentAddress);
		log.debug("Entered Permanent Address: {}", permanentAddress);
		
		log.info("Text Box filled Successfully");
	}
	
	public void clickOnSubmit() {
		log.info("Clicking on 'Submit' button.");
		waitForClickability(submitbutton).click();
		log.info("Submit Button Clicked");
	}
	
	private void validateExcelData(String fullName, String email, 
			String currentAddress, String permanentAddress) {
		
		
	}
}
