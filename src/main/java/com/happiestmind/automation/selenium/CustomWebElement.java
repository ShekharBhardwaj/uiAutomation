package com.happiestmind.automation.selenium;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.happiestmind.automation.errorhandler.ExtentErrorHandler;
import com.happiestmind.automation.util.ExtentSucessLogger;
import com.happiestmind.automation.util.MiscUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class CustomWebElement {

	public static void click(WebElement element, WebDriver driver, ExtentTest extentLogger, ExtentReports extent,
			String step, String className) throws IOException {

		try {
			if (!MiscUtils.isElementPresent(element, driver, extentLogger, extent)) {
				ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			} else {
				element.click();
				ExtentSucessLogger.elementFoundSuccess(extentLogger, extent, className, step);
			}
		} catch (Exception e) {
			ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
		}
	}

	public static void submit(String step, WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent, String className) throws IOException {

		try {
			if (!MiscUtils.isElementPresent(element, driver, extentLogger, extent)) {
				ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);

			} else {
				element.submit();
				ExtentSucessLogger.elementFoundSuccess(extentLogger, extent, className, step);
			}
		} catch (Exception e) {

			ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);

		}

	}

	public static void sendKeys(String step, WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent, String keysToSend, String className) throws IOException {
		try {
			if (!MiscUtils.isElementPresent(element, driver, extentLogger, extent)) {
				ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);

			} else {
				element.sendKeys(keysToSend);
				ExtentSucessLogger.elementFoundSuccess(extentLogger, extent, className, step);
			}
		} catch (Exception e) {

			ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);

		}
	}

	public static void clear(String step, WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent, String className) throws IOException {
		if (!MiscUtils.isElementPresent(element, driver, extentLogger, extent)) {
			ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);

		} else {
			element.clear();
			ExtentSucessLogger.elementFoundSuccess(extentLogger, extent, className, step);
		}

	}

	public static String getTagName(String step, WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent, String className) throws IOException {
		try {
			if (!MiscUtils.isElementPresent(element, driver, extentLogger, extent)) {
				ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			} else {
				ExtentSucessLogger.elementFoundSuccess(extentLogger, extent, className, step);
				return element.getTagName();

			}
		} catch (Exception e) {

			ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			return null;

		}
		return null;
	}

	public static String getAttribute(String step, WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent, String name, String className) throws IOException {
		try {
			if (!MiscUtils.isElementPresent(element, driver, extentLogger, extent)) {
				ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			} else {
				ExtentSucessLogger.elementFoundSuccess(extentLogger, extent, className, step);
				return element.getAttribute(name);
			}
		} catch (Exception e) {

			ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			return null;
		}
		return null;
	}

	public boolean isSelected(String step, WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent, String className) throws IOException {
		try {
			if (!MiscUtils.isElementPresent(element, driver, extentLogger, extent)) {
				ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			} else {
				ExtentSucessLogger.elementFoundSuccess(extentLogger, extent, className, step);
				return element.isSelected();
			}
		} catch (Exception e) {

			ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			return false;
		}
		return false;
	}

	public boolean isEnabled(String step, WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent, String className) throws IOException {
		try {
			if (!MiscUtils.isElementPresent(element, driver, extentLogger, extent)) {
				ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			} else {
				ExtentSucessLogger.elementFoundSuccess(extentLogger, extent, className, step);
				return element.isEnabled();
			}
		} catch (Exception e) {

			ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			return false;
		}
		return false;
	}

	public static String getText(String step, WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent, String className) throws IOException {
		try {
			if (!MiscUtils.isElementPresent(element, driver, extentLogger, extent)) {
				ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			} else {
				ExtentSucessLogger.elementFoundSuccess(extentLogger, extent, className, step);
				return element.getText();
			}
		} catch (Exception e) {

			ExtentErrorHandler.extentHandlerForElement(driver, extent, extentLogger, element, step, className);
			return null;
		}
		return null;
	}

}
