package com.happiestmind.automation.browsers;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.happiestmind.automation.loaders.AutomationDriverLoader;
import com.happiestmind.automation.loaders.PropertiesLoader;

public class BrowserFactory {

	final static Logger LOG = Logger.getLogger(BrowserFactory.class);
	private static WebDriver driver;

	/****
	 * 
	 * @param browser
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 */
	public static WebDriver startBrowser(String browser, String url)
			throws IOException, URISyntaxException, InterruptedException {
		LOG.info("Initializing the WebDriver");
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", AutomationDriverLoader.loadGeckoDriver());
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary(PropertiesLoader.loadConfigurations().getProperty("firefox.binary.loc.mac"));
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", AutomationDriverLoader.loadChromeDriver());
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {

			throw new NotImplementedException("IE is not implemented yet");

		} else if (browser.equalsIgnoreCase("safari")) {

			throw new NotImplementedException("safari is not implemented yet");

		}
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		return driver;

	}

}
