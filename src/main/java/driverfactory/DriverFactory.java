package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import configreader.ConfigReader;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void initDriver() {
		
		String browser = ConfigReader.get("browser");
		
		switch (browser.toLowerCase()) {
		
		case "chrome":
			driver.set(new ChromeDriver());
			break;

		case "firefox":
			driver.set(new FirefoxDriver());
			break;
		
		case "edge":
			driver.set(new EdgeDriver());
			break;
			
		default:
			throw new RuntimeException("Browser not Supported: " + browser);	
		}
		// For window maximize
		getDriver().manage().window().maximize();
		// URL(Open Application)
		getDriver().get(ConfigReader.get("url"));
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	// For quit driver
	public static void quitDriver() {
		getDriver().quit();
		driver.remove();
	}
}
