package utils;

public interface ScreenshotUtility {
	
	void takeScreenshotOnPass(String testName);
	
	void takeScreenshotOnFail(String testName);
	
	String getScreenshotPath(String status, String testName);
}
