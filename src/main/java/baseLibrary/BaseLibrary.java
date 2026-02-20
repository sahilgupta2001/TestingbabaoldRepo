package baseLibrary;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import driverfactory.DriverFactory;

public class BaseLibrary {
	
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
	}

