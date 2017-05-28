package com.happiestmind.automation.pagefactory;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.happiestmind.automation.exception.AutomationCustomException;
import com.happiestmind.automation.selenium.CustomWebElement;
import com.happiestmind.automation.util.MiscUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class GoogleSearchPage {

	WebDriver driver;

	public GoogleSearchPage(WebDriver local_driver) {
		super();
		this.driver = local_driver;
	}

	@FindBy(how = How.NAME, using = "q")
	WebElement searchBox;

	@FindBy(how = How.ID, using = "_fZl")
	WebElement searchButton;
	
	
	public void searchTextEnter(String searchText , ExtentTest extentLogger, ExtentReports extent, String step, String className) throws AutomationCustomException, IOException{
		CustomWebElement.sendKeys(step, searchBox, driver, extentLogger, extent, searchText, className);
	}
	
	
	public void clickSearchIcon(ExtentTest extentLogger, ExtentReports extent, String step,  String className) throws AutomationCustomException, IOException {
		CustomWebElement.click(searchButton, driver, extentLogger, extent, step, className);
	}
	

	public void search(String searchText , ExtentTest extentLogger, ExtentReports extent, String step,  String className) throws Exception  {
		MiscUtils.isElementPresent(searchBox, driver, extentLogger, extent);
		searchBox.sendKeys(searchText);

		MiscUtils.isElementPresent(searchButton, driver, extentLogger, extent);
		searchButton.click();

	}

}
