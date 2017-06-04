package com.happiestmind.automation.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
import com.happiestmind.automation.errorhandler.ExtentErrorHandler;
import com.happiestmind.automation.exception.ComparisonFailureCustomException;
import com.happiestmind.automation.exception.ParameterReaderCustomException;
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

	public static void pauseForElementToBePresent(Long millis) throws InterruptedException {
		LOG.debug("Pausing for element to be present :" + millis);
		Thread.sleep(millis);
	}

	/**
	 * 
	 * @param element
	 * @param driver
	 * @throws Exception
	 * @throws InterruptedException
	 */
	public static boolean isElementPresent(WebElement element, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent) {
		int counter = 0;
		int countProp = 0;
		boolean isElementPresent = true;

		try {
			while (!element.isEnabled()) {
				driverSync(driver);
				LOG.info("Polling for the element" + element + "| retry no." + counter);
				counter++;

				if (PropertiesLoader.loadConfigurations().getProperty("max.retries") == null) {
					countProp = defaultWaitCounter();

				} else {
					countProp = Integer.parseInt(PropertiesLoader.loadConfigurations().getProperty("max.retries"));
				}

				if (counter == countProp) {
					isElementPresent = false;
					driver.quit();
					break;
				}

			}
		} catch (Exception e) {
			LOG.fatal("FATAL Error closing the quitting browser, ending Test");
			LOG.error("Caused By:" + e.getMessage());
			driver.quit();
			isElementPresent = false;

		}
		return isElementPresent;
	}

	/**
	 * 
	 * @param element
	 * @param driver
	 * @throws Exception
	 * @throws InterruptedException
	 */
	public static boolean isElementPresent(List<WebElement> elements, WebDriver driver, ExtentTest extentLogger,
			ExtentReports extent) {
		int counter = 0;
		int countProp = 0;
		boolean isElementPresent = true;

		try {
			for (WebElement element : elements) {
				while (!element.isDisplayed()) {
					driverSync(driver);
					LOG.info("Polling for the element" + element + "| retry no." + counter);
					counter++;
					if (PropertiesLoader.loadConfigurations().getProperty("max.retries") == null) {
						countProp = defaultWaitCounter();

					} else {
						countProp = Integer.parseInt(PropertiesLoader.loadConfigurations().getProperty("max.retries"));
					}

					if (counter == countProp) {
						isElementPresent = false;
						driver.quit();
						break;
					}
				}
			}
		} catch (Exception e) {
			LOG.fatal("FATAL Error closing the quitting browser, ending Test");
			LOG.error("Caused By:" + e.getMessage());
			driver.quit();
			isElementPresent = false;

		}
		return isElementPresent;
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
				+ testCaseName + "_" + getCurrentDateAndTime()+"_"+UUID.randomUUID() + AutomationConstants.PNG;
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
			ta.getExtentLogger().log(LogStatus.FAIL, "Test Case Failed: ", ta.getResult().getName());
			ta.getExtentLogger().log(LogStatus.FAIL, "Failure Cause: " + ta.getResult().getThrowable());
		} else if (ta.getResult().getStatus() == ITestResult.SKIP) {
			ta.getExtentLogger().log(LogStatus.SKIP, "Test Case Skipped is " + ta.getResult().getName());
		}

		return isTestPass;

	}

	/***
	 * 
	 * @param AfterTestAssessmentProperties
	 *            ta
	 * @throws ParameterReaderCustomException
	 */
	public static void afterTestAssessment(AfterTestAssessmentProperties ta) throws ParameterReaderCustomException {

		if (ta.isTestPass()) {
			TestUtil.reportDataSetResult(ta.getSuiteObject(), "Test Cases",
					TestUtil.getRowNum(ta.getSuiteObject(), ta.getClassName()), AutomationConstants.PASS);
		} else {
			TestUtil.reportDataSetResult(ta.getSuiteObject(), "Test Cases",
					TestUtil.getRowNum(ta.getSuiteObject(), ta.getClassName()), AutomationConstants.FAIL);
		}

		//ta.getExtent().flush();

	}

	/***
	 * 
	 * @param actual
	 * @param expected
	 * @param ExtentTest
	 *            extentLogger
	 * @param step
	 * @throws ComparisonFailureCustomException
	 */
	public static void assertion(WebDriver driver, Object actual, Object expected, ExtentTest extentLogger,
			String className, String step) throws ComparisonFailureCustomException {
		try {
			Assert.assertEquals(actual, expected);
			ExtentSucessLogger.assertionSuccess(actual, expected, extentLogger, step);
//			extentLogger.log(LogStatus.PASS, "Step:" + step + ".", "Comparison between " + actual + " and " + expected);
		} catch (Throwable e) {

			try {
				ExtentErrorHandler.assertionExceptionHandler(actual, expected, extentLogger, step, driver, className, e);
			} catch (IOException e1) {
				throw new ComparisonFailureCustomException(e1.getCause());

			}

		}
	}

	

	/***
	 * 
	 * @param suiteObject
	 * @param className
	 * @throws ParameterReaderCustomException
	 */
	public static void beforeTestRunmodeCheckUp(ParameterReader suiteObject, String className)
			throws ParameterReaderCustomException {
		if (!TestUtil.isTestCaseRunnable(suiteObject, className)) {
			throw new SkipException("Skipping Test Case" + className + " as runmode set to NO");
		}
	}

	/***
	 * 
	 * @param ExtentReports
	 *            extent
	 * @return ExtentReports
	 * @throws IOException 
	 */
	public static ExtentReports getExtentObject(ExtentReports extent, String suiteName) throws IOException {
		//FileUtils.forceMkdir(new File(AutomationConstants.extentReportFile+File.pathSeparator+UUID.randomUUID()));
		extent = new ExtentReports(AutomationConstants.extentReportFile+suiteName+"_"+MiscUtils.getCurrentDateAndTime()+"_"+UUID.randomUUID()+"/"+suiteName+"TestReport_"
				+ MiscUtils.getCurrentDateAndTime() +"_"+".html", true);
		extent.addSystemInfo(getSystemInfo());
		extent.loadConfig(new File(AutomationConstants.configXmlLocation));
		return extent;
	}

	

	private static int defaultWaitCounter() {
		return 30;
	}

	
}
