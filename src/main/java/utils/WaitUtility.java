package utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WaitUtility - Interface defining explicit wait contract for Selenium automation.
 * Implemented by BaseLibrary
 */

public interface WaitUtility {
	
	Logger log = LoggerFactory.getLogger(WaitUtility.class);
	
	// ---------------------------------------------------- //
    //                   Abstract Contract                  //
    // ---------------------------------------------------- //
	
	WebDriver getDriver();
	
	// ---------------------------------------------------- //
    //                     Defaults                         //
    // ---------------------------------------------------- //
	
	long DEFAULT_TIMEOUT_SECONDS = 30;
	long DEFAULT_POLLING_MILLIS = 500;
	long DEFAULT_RETRY_ATTEMPTS = 3;
	
	// ---------------------------------------------------- //
    //                  Core Wait Builder                   //
    // ---------------------------------------------------- //
	
	/**
	 * Builds a WebDriverWait with ignored common transient exceptions.
	 */
	default WebDriverWait buildWait(long timeoutInSeconds) {
		return buildWait(timeoutInSeconds, DEFAULT_POLLING_MILLIS);
	}
	
	default WebDriverWait buildWait(long timeoutInSeconds, long pollingMillis) {
		return (WebDriverWait) new WebDriverWait(getDriver(),Duration.ofSeconds(timeoutInSeconds),
				Duration.ofMillis(pollingMillis))
				.ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementNotInteractableException.class);
	}
	
	// ---------------------------------------------------- //
    //                   Element Visibility                 //
    // ---------------------------------------------------- //
	
	default WebElement waitForVisibility(By locator) {
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	default WebElement waitForVisibility(WebElement element) {
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(element));
	}
}
