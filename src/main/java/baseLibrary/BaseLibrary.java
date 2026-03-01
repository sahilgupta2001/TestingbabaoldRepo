package baseLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import driverfactory.DriverFactory;
import utils.ApplicationUtility;
import utils.ExcelUtility;
import utils.ScreenshotUtility;
import utils.WaitUtility;

public class BaseLibrary implements ExcelUtility,ScreenshotUtility,WaitUtility,ApplicationUtility {
	
	
	private static final Logger log = LoggerFactory.getLogger(BaseLibrary.class);
	
	// ---------------------------------------------------- //
    //                      Constants                       //
    // ---------------------------------------------------- //
	
	private static final String EXCEL_PATH = System.getProperty("user.dir") + "/src/test/resources/testdata/contacts.xlsx";
	private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/screenshots/";
	private static final String PASS_DIR = SCREENSHOT_DIR + "pass/";
	private static final String FAIL_DIR = SCREENSHOT_DIR +	"fail/";	
	
	protected WebDriver driver;
	
	// ---------------------------------------------------- //
    //                     Constructor                      //
    // ---------------------------------------------------- //
	
	public BaseLibrary() {	
		this.driver = DriverFactory.getDriver();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	// ---------------------------------------------------- //
    //                  Setup & TearDown                    //
    // ---------------------------------------------------- //
	
	@BeforeMethod
	public void setup() {
		DriverFactory.initDriver();
		this.driver = DriverFactory.getDriver();
		
		new File(PASS_DIR).mkdirs();
		new File(FAIL_DIR).mkdirs();
		
		log.info("Browser initialized Succesfully");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		
		if (result.getStatus() == ITestResult.SUCCESS) {
			log.info("Test Passed: {}", testName);
			takeScreenshotOnPass(testName);
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			log.info("Test Failed: {}", testName);
			takeScreenshotOnFail(testName);
		}
		
		if (driver != null) {
			driver.quit();
			log.info("Browser closed Successfully");
		}
	}
	
	// ---------------------------------------------------- //
	//  				  ExcelUtility 						//
	// ---------------------------------------------------- //
	
	@Override
	public String getReadData(int sheetNo, int colNo, int rowNo) {
		validateInputs(sheetNo, colNo, rowNo);
		
		log.debug("Reading Excel - Sheet: {}, Column: {}, Row: {}", sheetNo, colNo, rowNo);
		String val = "";	
		
		try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
			 XSSFWorkbook wb = new XSSFWorkbook(fis)) 
		{	
			XSSFSheet sheet = wb.getSheetAt(sheetNo);
			
			if (sheet == null) {
				log.error("Sheet not found at index: {}", sheetNo);
				throw new RuntimeException("Sheet not found at index: " + sheetNo);
			}
			if (sheet.getRow(rowNo) == null) {
				log.error("Row not found at index: {}", rowNo);
				throw new RuntimeException("Row not found at index: " + rowNo);
			}
			if (sheet.getRow(rowNo).getCell(colNo) == null) {
				log.error("Column not found at index: {}", colNo);
				throw new RuntimeException("Column not found at index: " + colNo);
			}
			
			val = sheet.getRow(rowNo).getCell(colNo).getStringCellValue();
			log.info("Excel data read successfully - Value: {}", val);
			
		} catch (IOException e) {
			log.error("Failed to read Excel file at path: {}", EXCEL_PATH, e);
			throw new RuntimeException("Excel Read Failed: " + e.getMessage(), e);
		}
		return val;
	}
	
	// ---------------------------------------------------- //
	//  				  ScreenshotUtility 				//
	// ---------------------------------------------------- //
		
	@Override
	public void takeScreenshotOnPass(String testName) {
		captureScreenshot(testName, PASS_DIR, "PASS");
	}

	@Override
	public void takeScreenshotOnFail(String testName) {
		captureScreenshot(testName, FAIL_DIR, "FAIL");
	}

	@Override
	public String getScreenshotPath(String status, String testName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String folder = status.equalsIgnoreCase("PASS") ? PASS_DIR : FAIL_DIR;	
		return folder + testName + "_" + status + "_" + timestamp + ".png";
	}
	
	private void captureScreenshot(String testName, String folder, String status) {
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String filePath = getScreenshotPath(status, testName);
			FileUtils.copyFile(srcFile, new File(filePath));
			log.info("[{}] Screenshot saved: {}", status, filePath);
		} catch (IOException e) {
			log.error("Failed to capture Screenshot for test: {}", testName, e);
		}
	}
	
	// ---------------------------------------- //
	//				Validation					//
	// ---------------------------------------- //
	
	private void validateInputs(int sheetNo, int rowNo, int colNo) {
		if (sheetNo < 0 ) throw new IllegalArgumentException("Sheet number cannot be negative: " + sheetNo);
		if (rowNo < 0) throw new IllegalArgumentException("Row number cannot be negative: " + rowNo);
		if (colNo < 0) throw new IllegalArgumentException("Column number cannot be negative: " + colNo); 
	}

	
	// ---------------------------------------------------- //
	//  				  ApplicationUtility 				//
	// ---------------------------------------------------- //
	
	@Override
	public void doubleclick(WebElement ele) {
		Actions act = new Actions(driver);
		act.doubleClick(ele).perform();
	}

	@Override
	public void rightclick(WebElement ele) {
		Actions act = new Actions(driver);
		act.contextClick(ele).perform();
	}

	@Override
	public void actionclick(WebElement ele) {
		Actions act = new Actions(driver);
		act.click(ele).perform();
	}

	@Override
	public void windowHandle(int tabNo) {
		Set<String> handles = driver.getWindowHandles();
		ArrayList<String> handle = new ArrayList<>(handles);
		driver.switchTo().window(handle.get(tabNo));
	}
	
	@Override
	public void uploadFile(String filepath) {
		try {
			StringSelection sel = new StringSelection(filepath);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(sel, null);
			
			Robot rob = new Robot();
			rob.delay(500);
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_V);
			rob.keyRelease(KeyEvent.VK_V);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.delay(250);
			rob.keyRelease(KeyEvent.VK_ENTER);
			
		} catch (AWTException e) {
			log.error("Robot initialization failed (headless environment?): {}", e);
			throw new RuntimeException("File upload Failed - Robot could not be created: " + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			log.error("Clipboard unavailable during file upload: {}", e.getMessage(), e);
			throw new RuntimeException("File upload failed - Clipboard access error: " + e.getMessage(), e);
		} catch (Exception e) {
			log.error("Unexpected error during file upload for path '{}': {}", filepath, e.getMessage(), e);
			throw new RuntimeException("File upload failed unexpectedly: " + e.getMessage(), e);
		}
	}

	@Override
	public void selectByVisibleText(WebElement ele, String text) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(text);
	}

	@Override
	public void selectByIndex(WebElement ele, int index) {
		Select sel = new Select(ele);
		sel.selectByIndex(index);
	}

	@Override
	public void selectByValue(WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByValue(value);
	}

	@Override
	public void movetoElement(WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele);
	}
}

