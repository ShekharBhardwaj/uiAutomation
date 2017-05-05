package com.happiestmind.automation.util;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.happiestmind.automation.driverloader.LoadAutomationDriver;

public class BrowserFactory {

	private static WebDriver driver;

	public static WebDriver startBrowser(String browser, String url) throws IOException, URISyntaxException, InterruptedException {

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", LoadAutomationDriver.loadGeckoDriver());
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary(PropertiesLoader.loadConfigurations().getProperty("firefox.binary.loc.mac"));
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", LoadAutomationDriver.loadChromeDriver());
			driver = new ChromeDriver();
			MiscUtils.driverWait(driver);
		} else if (browser.equalsIgnoreCase("ie")) {

			throw new NotImplementedException("IE is not implemented yet");

		}

		driver.manage().window().maximize();
		driver.get(url);

		return driver;

	}

}
