package driverfactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import configreader.ConfigReader;

/**
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
*/



public class DriverFactory {
	
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	/** Holds on WebDriver per thread - mandatory for parallel test execution*/
	private static final ThreadLocal<WebDriver> driverHolder = new ThreadLocal<>();
	
	// Private instantiation - this is a pure static utility class
	private DriverFactory() {}
	
	/**
	 * Initializes a WebDriver for the current thread based on the configured browser.
	 * Also maximize the window, applies timeouts, and opens the application URL.
	 * Called from: BaseLibrary @BeforeMethod
	 */
	public static void initDriver() {
		String browser = ConfigReader.get("browser", "chrome");
		log.info("Initialising '{}' driver on thread [{}]", browser, Thread.currentThread().threadID());
		
		WebDriver driver = createDriver(browser);
		configureDriver(driver);
		driverHolder.set(driver);
		
		String url = ConfigReader.get("url");
		log.info("Navigating to: {}", url);
		driver.get(url);
	}
}