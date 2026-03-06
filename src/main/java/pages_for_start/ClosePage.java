package pages_for_start;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseLibrary.BaseLibrary;

public class ClosePage extends BaseLibrary{
	
	public ClosePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="")
	private WebElement close;
}
