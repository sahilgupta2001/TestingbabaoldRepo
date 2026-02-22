package baseLibrary;

import java.io.FileInputStream;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import driverfactory.DriverFactory;
import utils.ExcelUtility;

public class BaseLibrary implements ExcelUtility{
	
	protected WebDriver driver;
	
	public BaseLibrary() {
		this.driver = DriverFactory.getDriver();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeMethod
	public void setup() {
		DriverFactory.initDriver();
	}
	
	// ---------------------------------------------------- //
	//  				  ExcelUtility 						//
	// ---------------------------------------------------- //
	
	@Override
	public String getReadData(int sheetNo, int colNo, int rowNo) {
		String val = "";
		String path = "";
		
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(sheetNo);
			val = sheet.getRow(rowNo).getCell(colNo).getStringCellValue();
		} catch (Exception e) {
			System.out.println("Issue in get Read Data: " + e);
		}
		return val;
	}
	}

