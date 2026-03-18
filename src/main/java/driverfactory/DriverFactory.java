package driverfactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import configreader.ConfigReader;

public class DriverFactory {

	private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	// ---------------------------------------------------- //
	//                   Page Load Timeout                  //
	// ---------------------------------------------------- //

	private static final int PAGE_LOAD_TIMEOUT_SECONDS = 60;

	// ---------------------------------------------------- //
	//                     Init Driver                      //
	// ---------------------------------------------------- //

	public static void initDriver() {

		String browser = ConfigReader.get("browser");
		log.info("Initializing browser: {}", browser);

		switch (browser.toLowerCase()) {
			case "chrome":  driver.set(new ChromeDriver());  break;
			case "firefox": driver.set(new FirefoxDriver()); break;
			case "edge":    driver.set(new EdgeDriver());    break;
			default: throw new RuntimeException("Browser not supported: " + browser);
		}

		// Maximize window
		getDriver().manage().window().maximize();
		log.debug("Browser window maximized");

		// ✅ Set page load timeout — fail fast instead of waiting 5 minutes
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT_SECONDS));
		log.debug("Page load timeout set to {} seconds", PAGE_LOAD_TIMEOUT_SECONDS);

		// Navigate to URL
		String url = ConfigReader.get("url");
		log.info("Navigating to URL: {}", url);
		getDriver().get(url);
		log.info("URL loaded successfully: {}", url);
	}

	// ---------------------------------------------------- //
	//                     Get Driver                       //
	// ---------------------------------------------------- //

	public static WebDriver getDriver() {
		return driver.get();
	}

	// ---------------------------------------------------- //
	//                     Quit Driver                      //
	// ---------------------------------------------------- //

	public static void quitDriver() {
		getDriver().quit();
		driver.remove();
		log.info("Browser quit and driver removed");
	}
}