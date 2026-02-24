package utils;

import java.time.Duration;
import java.util.List;

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
		log.debug("Waiting for visibility of element: {}", locator);
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	default WebElement waitForVisibility(WebElement element) {
		log.debug("Waiting for visibility of WebElement");
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(element));
	}
	
	default WebElement waitForVisibility(By locator, long timeoutInSeconds) {
		log.debug("Waiting {}s for visibility of element: {}", timeoutInSeconds, locator);
		return buildWait(timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	default List<WebElement> waitForVisibilityOfAll(By locator) {
		log.debug("Waiting for visibility of all elements: {}", locator);
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	default boolean waitForInvisibiliyt(By locator) {
		log.debug("Waiting for invisibility of element: {}", locator);
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	default boolean waitForInvisibility(By locator, long timeoutInSeconds) {
		log.debug("Waiting {}s for invisibility of element: {}", timeoutInSeconds, locator);
		return buildWait(timeoutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	 // ---------------------------------------------------- //
     //                   Element Presence                   //
     // ---------------------------------------------------- //
	
	default WebElement waitForPresence(By locator) {
		log.debug("Waiting for Presence of element: {}", locator);
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	default List<WebElement> waitForPresenceOfAll(By locator) {
		log.debug("Waiting for presence for all element: {}", locator);
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	// ---------------------------------------------------- //
    //                  Element Clickability                //
    // ---------------------------------------------------- //
	
	default WebElement waitForClickability(By locator) {
		log.debug("Waiting for clickability of element: {}", locator);
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	default WebElement waitForClickability(By locator, long timeoutInSeconds) {
		log.debug("Waiting {}s for clickability of element: {}", timeoutInSeconds, locator);
		return buildWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	default WebElement waitForClickability(WebElement element) {
		log.debug("Waiting for clickability of WebElement");
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	 // ---------------------------------------------------- //
     //                    Text Conditions                   //
     // ---------------------------------------------------- //
	
	default boolean waitForTextInElement(By locator, String text) {
		log.debug("Waiting for text '{}' in element: {}", text, locator);
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}
	
	default boolean waitForExactText(By locator, String exacttext) {
		log.debug("Waiting for exacttext '{}' in element: {}", exacttext, locator);
		return buildWait(DEFAULT_TIMEOUT_SECONDS).until(driver -> {
			try {
				return driver.findElement(locator).getText().trim().equals(exacttext);
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				return false;
			}
		});
	}
	
	
}
