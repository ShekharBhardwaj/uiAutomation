package com.happiestmind.automation.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.happiestmind.automation.domain.AfterMethodAssessmentProperties;
import com.happiestmind.automation.domain.AfterTestAssessmentProperties;
import com.happiestmind.automation.loaders.PropertiesLoader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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
	 * @throws Exception
	 * @throws InterruptedException
	 */
	public static void isElementPresent(WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent,String step) throws Exception {
		int counter = 0;

		try {
			while (!element.isDisplayed() && !element.isEnabled()) {
				driverSync(driver);
				LOG.info("Polling for the element" + element + "| retry no." + counter);
				counter++;
				if (counter == Integer.parseInt(PropertiesLoader.loadConfigurations().getProperty("max.retries"))) {
					extentLogger.log(LogStatus.FATAL, "Required WebElement: " + element.getText() + "  Not found");
					driver.quit();
					extent.endTest(extentLogger);
					break;
				}

			}
			extentLogger.log(LogStatus.PASS,step);
		} catch (Exception e) {
			LOG.fatal("FATAL Error closing the quitting browser, ending Test");
			LOG.error("Caused By:" + e.getMessage());
			extentLogger.log(LogStatus.FATAL, "Required WebElement: " + element.getText() + "  Not found");
			driver.quit();
			extent.endTest(extentLogger);
			throw e;

		}
	}

	/***
	 * 
	 * @param driver
	 * @param testCaseName
	 * @return String
	 * @throws IOException
	 */
	public static String selfie(WebDriver driver, String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty(AutomationConstants.USER_DIR) + AutomationConstants.SCREENSHOT_FOLDER
				+ testCaseName + "_" + getCurrentDateAndTime() + AutomationConstants.PNG;
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}

	/***
	 * 
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> getSystemInfo() {
		HashMap<String, String> systemInfo = new HashMap<String, String>();
		systemInfo.put(AutomationConstants.COMPANY, PropertiesLoader.loadConfigurations().getProperty("company.name"));
		systemInfo.put(AutomationConstants.ENVIRONMENT,
				PropertiesLoader.loadConfigurations().getProperty("test.environment"));
		systemInfo.put(AutomationConstants.AUTOMATION_DEVELOPER,
				PropertiesLoader.loadConfigurations().getProperty("automation.developer"));
		return systemInfo;

	}

	/***
	 * 
	 * @return String
	 */
	public static String getCurrentDateAndTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(AutomationConstants.YYYY_MM_DD_HH_MM_SS);
		Date now = new Date();
		return formatter.format(now);
	}

	/***
	 * 
	 * @param AfterMethodAssessmentProperties
	 *            ta
	 * @return
	 */
	public static boolean afterMethodAssessment(AfterMethodAssessmentProperties ta) {
		boolean isTestPass = true;
		if (ta.isSkip())
			TestUtil.reportDataSetResult(ta.getSuiteObject(), ta.getClassName(), ta.getCount() + 2,
					AutomationConstants.SKIP);
		else if (ta.isFail()) {
			isTestPass = ta.isTestPass();
			TestUtil.reportDataSetResult(ta.getSuiteObject(), ta.getClassName(), ta.getCount() + 2,
					AutomationConstants.FAIL);
		} else
			TestUtil.reportDataSetResult(ta.getSuiteObject(), ta.getClassName(), ta.getCount() + 2,
					AutomationConstants.PASS);

		if (ta.getResult().getStatus() == ITestResult.FAILURE) {
			ta.getExtentLogger().log(LogStatus.INFO, "Test Case Failed is " + ta.getResult().getName());
			ta.getExtentLogger().log(LogStatus.INFO, "Test Case Failed is " + ta.getResult().getThrowable());
		} else if (ta.getResult().getStatus() == ITestResult.SKIP) {
			ta.getExtentLogger().log(LogStatus.SKIP, "Test Case Skipped is " + ta.getResult().getName());
		}

		return isTestPass;

	}

	/***
	 * 
	 * @param AfterTestAssessmentProperties
	 *            ta
	 */
	public static void afterTestAssessment(AfterTestAssessmentProperties ta) {

		if (ta.isTestPass()) {
			TestUtil.reportDataSetResult(ta.getSuiteObject(), "Test Cases",
					TestUtil.getRowNum(ta.getSuiteObject(), ta.getClassName()), AutomationConstants.PASS);
		} else {
			TestUtil.reportDataSetResult(ta.getSuiteObject(), "Test Cases",
					TestUtil.getRowNum(ta.getSuiteObject(), ta.getClassName()), AutomationConstants.FAIL);
		}

		ta.getExtent().flush();

	}

	/***
	 * 
	 * @param actual
	 * @param expected
	 * @param ExtentTest
	 *            extentLogger
	 * @param step
	 */
	public static void assertion(Object actual, Object expected, ExtentTest extentLogger, String step) {

		Assert.assertEquals(actual, expected);
		extentLogger.log(LogStatus.PASS, "Step:" + step + ".", "Comparison between " + actual + " and " + expected);

	}

	/***
	 * 
	 * @param actual
	 * @param expected
	 * @param ExtentTest
	 *            extentLogger
	 * @param step
	 * @param WebDriver
	 *            driver
	 * @param className
	 * @param Throwable
	 *            t
	 * @throws IOException
	 */
	public static void assertionExceptionHandler(Object actual, Object expected, ExtentTest extentLogger, String step,
			WebDriver driver, String className, Throwable t) throws IOException {

		extentLogger.log(LogStatus.FAIL, "Step: " + step + ".", "Comparison between " + actual + " and " + expected);
		extentLogger.log(LogStatus.INFO, "Snapshot for failed step is given below [Click it for full view]: "
				+ extentLogger.addScreenCapture(MiscUtils.selfie(driver, className)));
		ErrorHandler.setVerificationFailure(t);
	}

	/***
	 * 
	 * @param suiteObject
	 * @param className
	 */
	public static void beforeTestRunmodeCheckUp(ParameterReader suiteObject, String className) {
		if (!TestUtil.isTestCaseRunnable(suiteObject, className)) {
			throw new SkipException("Skipping Test Case" + className + " as runmode set to NO");
		}
	}

	/***
	 * 
	 * @param ExtentReports
	 *            extent
	 * @return ExtentReports
	 */
	public static ExtentReports getExtentObject(ExtentReports extent) {
		extent = new ExtentReports(AutomationConstants.extentReportFile, true);
		extent.addSystemInfo(getSystemInfo());
		extent.loadConfig(new File(AutomationConstants.configXmlLocation));
		return extent;
	}

	public static boolean elementNotFoundErrorHandler(WebDriver driver, ExtentTest extentLogger, ExtentReports extent,
			String className, String step) throws IOException {
		selfie(driver, className);
		extentLogger.log(LogStatus.FATAL, "Step:"+step+".", "Test step failed due to WebElement related exception.");
		driver.quit();
		extent.endTest(extentLogger);
		return false;

	}

}
