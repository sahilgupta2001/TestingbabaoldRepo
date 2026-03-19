package pages_for_element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baseLibrary.BaseLibrary;
import driverfactory.DriverFactory;

public class CheckBoxPage extends BaseLibrary {
	
	private static final Logger log = LoggerFactory.getLogger(CheckBoxPage.class);
	
	// ---------------------------------------- //
	// 				Constructor					//
	// ---------------------------------------- //
	
	public CheckBoxPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		log.debug("CheckBoxPage Initalized");
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
	
	@FindBy(xpath="//a[contains(text(),'check box')]")
	private WebElement checkboxbutton;
	
	@FindBy(xpath="//input[@id='myCheck']")
	private WebElement mobilecheck;
	
	@FindBy(xpath="//h6[contains(text(),'You are selected Mobile')]")
	private WebElement mobilemessage;
	
	@FindBy(xpath="//input[@id='mylaptop']")
	private WebElement laptopcheck;
	
	@FindBy(xpath="//h6[contains(text(), 'You are Selected Laptop')]")
	private WebElement laptopmessage;
	
	@FindBy(xpath="//input[@id='mydesktop']")
	private WebElement desktopcheck;
	
	@FindBy(xpath="//h6[contains(text(), 'You are Selected Desktop')]")
	private WebElement desktopmessage;
	
	// ---------------------------------------------------- //
	//                       Actions                        //
	// ---------------------------------------------------- //
	
	public CheckBoxPage clickOnClose() {
		log.info("Attempting to close the advertisement banner");
		try {
			waitForClickability(close).click();
			log.info("Advertisement banner closed successfully");
		} catch (Exception e) {
			log.warn("Close banner was not interactable or already dismissed");
		}
		return this;
	}
	
	public CheckBoxPage clickOnPractice() {
		log.info("Clicking on 'Practice' Navigation link");
		waitForClickability(practice).click();
		log.info("Navigated to Practice Page");
		return this;
	}
	
	public CheckBoxPage clickOnElements() {
		log.info("Expanding 'Elements' accordion");
		waitForClickability(elements).click();
		log.info("Elements accordion expanded");
		return this;
	}
	
	public CheckBoxPage clickOnCheckBox() {
		log.info("CheckBox page is open");
		waitForClickability(checkboxbutton).click();
		log.info("CheckBox page is open successfully");
		return this;
	}
	

	// ---------------------------------------------------- //
	//                  Checkbox Interactions               //
	// ---------------------------------------------------- //
 
	public CheckBoxPage checkMobile() {
		log.info("Clicking Mobile checkbox");
		waitForClickability(mobilecheck).click();
		log.info("Mobile checkbox clicked");
		return this;
	}
	
	public CheckBoxPage checkLaptop() {
		log.info("Clicking Laptop checkbox");
		waitForClickability(laptopcheck).click();
		log.info("Laptop checkbox clicked");
		return this;
	}
	
	public CheckBoxPage checkDesktop() {
		log.info("Clicking Desktop checkbox");
		waitForClickability(desktopcheck).click();
		log.info("Desktop checkbox clicked");
		return this;
	}
	
	// ---------------------------------------------------- //
	//                  State Verifications                 //
	// ---------------------------------------------------- //
	
	public boolean isMobileChecked() {
		boolean checked = mobilecheck.isSelected();
		log.debug("Mobile checkbox selected: {}", checked);
		return checked;
	}

	public boolean isLaptopChecked() {
		boolean checked = laptopcheck.isSelected();
		log.debug("Laptop checkbox selected: {}", checked);
		return checked;
	}
	
	public boolean isDesktopChecked() {
		boolean checked = desktopcheck.isSelected();
		log.debug("Desktop checkbox selected: {}", checked);
		return checked;
	}
	
	// ---------------------------------------------------- //
	//                  Message Verifications               //
	// ---------------------------------------------------- //
 
	public boolean isMobileMessageDisplayed() {
		try {
			boolean visible = waitForClickability(mobilemessage).isDisplayed();
			log.debug("Mobile message displayed: {}", visible);
			return visible;
		} catch (Exception e) {
			log.warn("Mobile message not found: {}", e.getMessage());
		}
		return false;
	}
	
	public boolean isLaptopMessageDisplayed() {
		try {
			boolean visible = waitForClickability(laptopmessage).isDisplayed();
			log.debug("Laptop message displayed: {}", visible);
			return visible;
		} catch (Exception e) {
			log.warn("Laptop message not found: {}", e.getMessage());
		}
		return false;
	}
	
	public boolean isDesktopMessageDisplayed() {
		try {
			boolean visible = waitForClickability(desktopmessage).isDisplayed();
			log.debug("Desktop message displayed: {}", visible);
			return visible;
		} catch (Exception e) {
			log.warn("Desktop message not found: {}", e.getMessage());
		}
		return false;
	}
	
	public String getMobileMessageText() {
		String text = waitForVisibility(mobilemessage).getText();
		log.debug("Mobile message Text: {}", text);
		return text;
	}
	
	public String getLaptopMessageText() {
		String text = waitForVisibility(laptopmessage).getText();
		log.debug("Laptop message Text: {}", text);
		return text;
	}
	
	public String getDesktopMessageText() {
		String text = waitForVisibility(desktopmessage).getText();
		log.debug("Desktop message Text: {}", text);
		return text;
	}
}
