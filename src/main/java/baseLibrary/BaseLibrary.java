package baseLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import driverfactory.DriverFactory;
import utils.ExcelUtility;
import utils.ScreenshotUtility;

public class BaseLibrary implements ExcelUtility,ScreenshotUtility {
	
	
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
}

