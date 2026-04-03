package pages_for_element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baseLibrary.BaseLibrary;
import driverfactory.DriverFactory;

public class WebTablesPage extends BaseLibrary {
	
private static final Logger log = LoggerFactory.getLogger(CheckBoxPage.class);
	
	// ---------------------------------------- //
	// 				Constructor					//
	// ---------------------------------------- //
	
	public WebTablesPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		log.debug("WebTablesPage Initalized");
	}
	
	// ---------------------------------------------------- //
	//                     Web Elements                     //
	// ---------------------------------------------------- //
		
	@FindBy(xpath="//button[contains(text(), '×')]")
	private WebElement close;
		
	@FindBy(xpath="//a[contains(text(), 'Practice')]")
	private WebElement practice;
		
	@FindBy(xpath="//button[@data-target='#elements']")
	private WebElement elements;
	
	@FindBy(xpath="//a[contains(text(), 'web tables')]")
	private WebElement webtables;
	
	@FindBy(xpath="")
	private WebElement namefield;
	
	@FindBy(xpath="")
	private WebElement emailfield;
	
	@FindBy(xpath="")
	private WebElement save;
	// ---------------------------------------------------- //
	//                       Actions                        //
	// ---------------------------------------------------- //
	
	public WebTablesPage clickOnClose() {
		log.info("Attempting to close the advertisement banner");
		try {
			waitForClickability(close).click();
			log.info("Advertisement banner closed successfully");
		} catch (Exception e) {
			log.warn("Close banner was not interactable or already dismissed");
		}
		return this;
	}
	
	public WebTablesPage clickOnPractice() {
		log.info("Clicking on 'Practice' Navigation link");
		waitForClickability(practice).click();
		log.info("Navigated to Practice Page");
		return this;
	}
	
	public WebTablesPage clickOnElements() {
		log.info("Expanding 'Elements' accordion");
		waitForClickability(elements).click();
		log.info("Elements accordion expanded");
		return this;
	}
	
	public WebTablesPage clickOnWebTables() {
		waitForClickability(webtables).click();
		return this;
	}
	
	public WebTablesPage fillAlltheDetails() {
		log.info("Reading test data from excel - Sheet: 0, Row: 1");
		
		String fullName = getReadData(0, 0, 1);
		String email = getReadData(0, 1, 1);
		
		log.debug("Excel Data read - name: {}, email: {}",
				fullName, email);
		
		return this;
	}
}
