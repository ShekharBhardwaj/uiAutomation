package com.happiestmind.automation.errorhandler;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.happiestmind.automation.exception.AutomationDataProviderCustomException;

import com.happiestmind.automation.util.MiscUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentErrorHandler {

	/***
	 * 
	 * @param e
	 * @param extent
	 * @param extentLogger
	 * @param TESTCASE
	 * @throws IOException 
	 */
	public static void dataProviderErrHandler(AutomationDataProviderCustomException e, ExtentReports extent,
			ExtentTest extentLogger, String TESTCASE, String suiteName) throws IOException {
		extent = MiscUtils.getExtentObject(extent, suiteName);
		extentLogger = extent.startTest(TESTCASE);
		extentLogger.log(LogStatus.FATAL, "Getting Data from DataProvider", e.getCause());
		extent.endTest(extentLogger);
		extent.flush();
		extent.close();
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
	public static boolean assertionExceptionHandler(Object actual, Object expected, ExtentTest extentLogger,
			String step, WebDriver driver, String className, Throwable t) throws IOException {

		extentLogger.log(LogStatus.FAIL, "Step: " + step + ".", "Comparison between " + actual + " and " + expected);
		extentLogger.log(LogStatus.INFO, "Snapshot for failed step is given below [Click it for full view]: "
				+ extentLogger.addScreenCapture(MiscUtils.selfie(driver, className)));
		ErrorHandler.setVerificationFailure(t);
		return true;
	}
	
	/***
	 * 
	 * @param driver
	 * @param extentLogger
	 * @param extent
	 * @param className
	 * @param step
	 * @return
	 * @throws IOException
	 */
/*	public static boolean elementNotFoundErrorHandler(WebDriver driver, ExtentTest extentLogger, ExtentReports extent,
			String className, String step) throws IOException {
		MiscUtils.selfie(driver, className);
		extentLogger.log(LogStatus.FATAL, "Step:" + step + ".",
				"Test step failed due to WebElement related exception.");
		extentLogger.log(LogStatus.INFO, "Snapshot for failed step is given below [Click it for full view]: "
				+ extentLogger.addScreenCapture(MiscUtils.selfie(driver, className)));
		driver.quit();
		extent.endTest(extentLogger);
		return false;

	}*/

	/***
	 * 
	 * @param extent
	 * @param extentLogger
	 * @param element
	 * @param step
	 * @throws IOException 
	 */
	public static void extentHandlerForElement(WebDriver driver,ExtentReports extent, ExtentTest extentLogger, WebElement element,
			String step, String className) throws IOException {
		MiscUtils.selfie(driver, className);
		extentLogger.log(LogStatus.FATAL, "Step: " + step + ".",
				"Required WebElement: " + element.getText() + "  Not found");
		extentLogger.log(LogStatus.INFO, "Snapshot for failed step is given below [Click it for full view]: "
				+ extentLogger.addScreenCapture(MiscUtils.selfie(driver, className)));
		driver.quit();
		extent.endTest(extentLogger);
		extent.endTest(extentLogger);
	}

}
