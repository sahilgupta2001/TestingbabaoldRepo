package utils;

import org.openqa.selenium.WebElement;

public interface ApplicationUtility {
	
	public void doubleclick(WebElement ele);
	
	public void rightclick(WebElement ele);
	
	public void actionclick(WebElement ele);
	
	public void windowHandle(int tabNo);
	
	public void uploadFile(String filepath);
	
	public void selectByVisibleText(WebElement ele, String text);
	public void selectByIndex(WebElement ele, int index);
	public void selectByValue(WebElement ele, String value);
	
	public void movetoElement(WebElement ele);

}
