package com.happiestmind.automation.TestCases;

import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.happiestmind.automation.pagefactory.GoogleSearchPage;
import com.happiestmind.automation.util.BrowserFactory;

public class GoogleSearchTest {
	
	@Test
	public void searchGoogle() throws InterruptedException, IOException, URISyntaxException{
		
		WebDriver driver = BrowserFactory.startBrowser("chrome", "http://www.google.com");
		
		GoogleSearchPage googleSearchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
		
		googleSearchPage.search("HelloWorld");
		
		driver.quit();
	}
	

}
