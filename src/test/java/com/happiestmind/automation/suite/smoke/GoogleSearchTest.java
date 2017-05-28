package com.happiestmind.automation.suite.smoke;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.happiestmind.automation.browsers.BrowserFactory;
import com.happiestmind.automation.dataprovider.AutomationDataProvider;
import com.happiestmind.automation.domain.AfterMethodAssessmentProperties;
import com.happiestmind.automation.domain.AfterTestAssessmentProperties;
import com.happiestmind.automation.errorhandler.ErrorHandler;
import com.happiestmind.automation.errorhandler.ExtentErrorHandler;
import com.happiestmind.automation.exception.AutomationDataProviderCustomException;
import com.happiestmind.automation.exception.ParameterReaderCustomException;
import com.happiestmind.automation.pagefactory.GoogleSearchPage;
import com.happiestmind.automation.util.MiscUtils;
import com.happiestmind.automation.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class GoogleSearchTest extends SmokeSuiteBaseImpl {

	final static Logger LOG = Logger.getLogger(GoogleSearchTest.class);
	
	private final static String TESTCASE="Starting Smoke Google Search Test";
	private final static String STEP1="Searching the word";
	private final static String STEP2="Clicking search icon";
	private final static String STEP3="Compairing page title";


	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	String className = this.getClass().getSimpleName();

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() {
		try {
			MiscUtils.beforeTestRunmodeCheckUp(smokeSuite, className);
			runmodes = TestUtil.getDataSetRunmodes(smokeSuite, className);
		} catch (ParameterReaderCustomException e) {
			e.printStackTrace();
		}

	}

	@Test(dataProvider = "getTestData")
	public void googleSearchTest(String browser, String uri, String searchWord, String title) throws Exception {

		extentLogger = extent.startTest(TESTCASE);
		// test the runmode of current dataset
		String pagetitle = null;
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			extentLogger.log(LogStatus.SKIP, "This test case is skipped");
			throw new SkipException("Runmode for test set data set to no " + count);
		}

		LOG.info(" Executing : " + className);
		LOG.info(browser + " -- " + uri + " -- " + searchWord);

		WebDriver driver = null;

		try {

			driver = BrowserFactory.startBrowser(browser, uri);
			GoogleSearchPage googleSearchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
			
			//Step 1
			googleSearchPage.searchTextEnter(searchWord, extentLogger, extent, STEP1, className);
			
			//Step 2
			googleSearchPage.clickSearchIcon(extentLogger, extent, STEP2, className);
			
			//Step 3
			pagetitle = driver.getTitle();
			MiscUtils.assertion(driver, pagetitle, title, extentLogger, className, STEP3);
		} catch (Throwable t) {
			ErrorHandler.setVerificationFailure(t);
			fail = true;
			extent.endTest(extentLogger);
			driver.quit();
			return;
		}

		driver.quit();
		extent.endTest(extentLogger);

	}

	@AfterMethod
	public void reportDataSetResult(ITestResult result) {

		AfterMethodAssessmentProperties methodAssessment = new AfterMethodAssessmentProperties();
		methodAssessment.setSuiteObject(smokeSuite);
		methodAssessment.setClassName(className);
		methodAssessment.setFail(fail);
		methodAssessment.setSkip(skip);
		methodAssessment.setTestPass(isTestPass);
		methodAssessment.setExtentLogger(extentLogger);
		methodAssessment.setResult(result);

		isTestPass = MiscUtils.afterMethodAssessment(methodAssessment);
	}

	@AfterTest
	public void reportTestResult() {
		AfterTestAssessmentProperties testAssessment = new AfterTestAssessmentProperties();
		testAssessment.setTestPass(isTestPass);
		testAssessment.setClassName(className);
		testAssessment.setExtent(extent);
		testAssessment.setSuiteObject(smokeSuite);
		try {
			MiscUtils.afterTestAssessment(testAssessment);
		} catch (ParameterReaderCustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] getTestData() throws IOException {
		try {
			return AutomationDataProvider.getData(smokeSuite, className);
		} catch (AutomationDataProviderCustomException e) {
			ExtentErrorHandler.dataProviderErrHandler(e, extent, extentLogger, TESTCASE, "SmokeSuite");
		}
		return null;
	}

}
