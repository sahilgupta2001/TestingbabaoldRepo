package pages_for_element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baseLibrary.BaseLibrary;
import driverfactory.DriverFactory;

public class ElementPage extends BaseLibrary{
	
	private static final Logger log = LoggerFactory.getLogger(ElementPage.class);
	
	// ---------------------------------------------------- //
	//                     Constructor                      //
	// ---------------------------------------------------- //

	public ElementPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		log.debug("ElementPage Initialized");
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
	
	// ---------------------------------------------------- //
	//                       Actions                        //
	// ---------------------------------------------------- //

	public ElementPage clickOnClose() {
		log.info("Attempting to close the advertisement banner");
		try {
			waitForClickability(close).click();
			log.info("Advertisement banner closed successfully");
		} catch (Exception e) {
			log.warn("Close banner was not interactable or already dismissed: {}", e);
		}
		return this;
	}
	
	public ElementPage clickOnPractice() {
		log.info("Clicking on 'Practice' navigation link");
		waitForClickability(practice).click();
		log.info("Navigated to Practice page");
		return this;
	}
	
	public ElementPage clickOnElements() {
		log.info("Expanding 'Elements' accordion");
		waitForClickability(elements).click();
		log.info("Elements accordion expanded");
		return this;
	}
	
	public String getPageTitle() {
		String title = DriverFactory.getDriver().getTitle();
		log.debug("Current page title: {}", title);
		return title;
	}
}
