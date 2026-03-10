package tests_for_start;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseLibrary.BaseLibrary;
import pages_for_start.ClosePage;

public class CloseTest extends BaseLibrary {
	
	ClosePage ob;
	
	@Test
	public void clickonclose() {
		ob = new ClosePage();
		ob.clickonclose(); 
	}
}
