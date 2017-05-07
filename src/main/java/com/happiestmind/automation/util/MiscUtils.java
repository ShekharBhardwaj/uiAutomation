package com.happiestmind.automation.util;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.happiestmind.automation.loaders.PropertiesLoader;

public class MiscUtils {
	
	
	final static Logger LOG = Logger.getLogger(MiscUtils.class);

	/**
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void driverSync(WebDriver driver) throws InterruptedException {

		synchronized (driver) {
			driver.wait(2000);

		}

	}

	/**
	 * 
	 * @param element
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void isElementPresent(WebElement element, WebDriver driver) throws InterruptedException {
		int counter = 0;
	
		while (!element.isDisplayed()) {
			driverSync(driver);
			LOG.info("Polling for the element"+ element +"| retry no."+counter);
			counter ++;
			if(counter==Integer.parseInt(PropertiesLoader.loadConfigurations().getProperty("max.retries")))
			{
				break;
			}

		}
	}

}
