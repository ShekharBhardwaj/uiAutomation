package com.happiestmind.automation.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.happiestmind.automation.util.MiscUtils;

public class GoogleSearchPage {

	WebDriver driver;

	public GoogleSearchPage(WebDriver local_driver) {
		super();
		this.driver = local_driver;
	}

	@FindBy(how = How.NAME, using = "q")
	WebElement searchBox;

	@FindBy(how = How.NAME, using = "btnK")
	WebElement searchButton;

	public void search(String searchText) throws InterruptedException {
		MiscUtils.isElementPresent(searchBox, driver);
		searchBox.sendKeys(searchText);
		/*MiscUtils.isElementPresent(searchButton, driver);
		searchButton.click();*/
	}

}
