package pages_for_element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baseLibrary.BaseLibrary;
import driverfactory.DriverFactory;

public class RadioButtonsPage extends BaseLibrary {
	
	private static final Logger log = LoggerFactory.getLogger(RadioButtonsPage.class);

	public RadioButtonsPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		log.debug("RadioButtonsPage Initialized");
	}
	
	@FindBy(xpath="//button[contains(text(), '×')]")
	private WebElement close;
	
	@FindBy(xpath="//a[contains(text(), 'Practice')]")
	private WebElement practice;
	
	@FindBy(xpath="//button[@data-target='#elements']")
	private WebElement elements;
	
	@FindBy(xpath="//a[contains(text(), 'radio buttons')]")
	private WebElement radiobuttons;
	
	@FindBy(xpath="//input[@value='yes']")
	private WebElement yes;
	
	@FindBy(xpath="//input[@value='impressive']")
	private WebElement impressive;
	
	@FindBy(xpath="//input[@value='no']")
	private WebElement no;
	
	@FindBy(xpath="//p[contains(text(), 'You have selected yes')]")
	private WebElement yesmessage;
	
	@FindBy(xpath="//p[contains(text(), 'You have selected impressive')]")
	private WebElement impressivemessage;
	
	@FindBy(xpath="//p[contains(text(), 'You have selected no')]")
	private WebElement nomessage;

	public RadioButtonsPage clickOnClose() {
		log.info("Attempting to closed the advertisement banner");
		try {
			waitForClickability(close).click();
			log.info("Advertisement banner is closed successfully.");
		} catch (Exception e) {
			log.warn("Close banner was not interactable or already dismissed");
		}
		return this;
	}
	 
	public RadioButtonsPage clickOnPractice() {
		log.info("Clicking on 'Practice' Navigating link.");
		waitForClickability(practice).click();
		log.info("Navigate to Practice page.");
		return this;
	}
	
	public RadioButtonsPage clickOnElements() {
		log.info("Expanding Elements Accordion.");
		waitForClickability(elements).click();
		log.info("Elements expanding successfuly.");
		return this;
	}
	
	public RadioButtonsPage clickOnRadioButtons() {
		log.info("Radio Buttons page is open");
		waitForClickability(radiobuttons).click();
		log.info("Radio Buttons page opened successfully");
		return this;
	}
	
	public RadioButtonsPage checkYes() {
		log.info("Clicking Yes Radio Button");
		waitForClickability(yes).click();
		log.debug("Yes Radio Button Clicked.");
		return this;
	}
	
	public RadioButtonsPage checkImpressive() {
		log.info("Clicking Impressive Radio Button");
		waitForClickability(impressive).click();
		log.debug("Impressive Radio Button Clicked.");
		return this;
	}
	
	public RadioButtonsPage checkNo() {
		log.info("Clicking No Radio Button");
		waitForClickability(no).click();
		log.debug("No Radio Button Clicked.");
		return this;
	}

	public boolean isYesChecked() {
		boolean checked = yes.isSelected();
		log.debug("Yes radiobutton selected: {}", checked);
		return checked;
	}
	
	public boolean isImpressiveChecked() {
		boolean checked = impressive.isSelected();
		log.debug("Impressive radiobutton selected: {}", checked);
		return checked;
	}
	
	public boolean isNoChecked() {
		boolean checked = no.isSelected();
		log.debug("No radiobutton selected: {}", checked);
		return checked;
	}
	
	public boolean isYesMessageDisplayed() {
		try {
			boolean visible = waitForClickability(yesmessage).isDisplayed();
			log.debug("Yes message displayed: {}", visible);
			return visible;
		} catch (Exception e) {
			log.warn("Yes message not displayed: {}", e.getMessage());
			return false;
		}
	} 
	
	public boolean isImpressiveMessageDisplayed() {
		try {
			boolean visible = waitForClickability(impressivemessage).isDisplayed();
			log.debug("Impressive message displayed: {}", visible);
			return visible;
		} catch (Exception e) {
			log.warn("Impressive message not displayed: {}", e.getMessage());
			return false;
		}
	}
	
	public boolean isNoMessageDisplayed() {
		try {
			boolean visible = waitForClickability(nomessage).isDisplayed();
			log.debug("No message displayed: {}", visible);
			return visible;
		} catch (Exception e) {
			log.warn("No message not displayed: {}", e.getMessage());
			return false;
		}
	}
	
	public String getYesMessageText() {
		String text = waitForVisibility(yesmessage).getText();
		log.debug("Yes message text: {}", text);
		return text;
	}
	
	public String getImpressiveMessageText() {
		String text = waitForVisibility(impressivemessage).getText();
		log.debug("Impressive message text: {}", text);
		return text;
	}
	
	public String getNoMessageText() {
		String text = waitForVisibility(nomessage).getText();
		log.debug("No message text: {}", text);
		return text;
	}
}