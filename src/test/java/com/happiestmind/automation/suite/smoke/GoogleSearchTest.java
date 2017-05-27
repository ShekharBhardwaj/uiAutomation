package com.happiestmind.automation.suite.smoke;

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
import com.happiestmind.automation.pagefactory.GoogleSearchPage;
import com.happiestmind.automation.util.MiscUtils;
import com.happiestmind.automation.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class GoogleSearchTest extends SmokeSuiteBaseImpl {

	final static Logger LOG = Logger.getLogger(GoogleSearchTest.class);

	String runmodes[] = null;
	static int count = -1;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;
	String className = this.getClass().getSimpleName();

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() {

		MiscUtils.beforeTestRunmodeCheckUp(smokeSuite, className);
		// load the runmodes off the tests
		runmodes = TestUtil.getDataSetRunmodes(smokeSuite, className);
		extent = MiscUtils.getExtentObject(extent);
		
	}

	@Test(dataProvider = "getTestData")
	public void googleSerahcTest(String browser, String uri, String searchWord, String title)
			throws Exception {

		extentLogger = extent.startTest("Starting Google Search Test");
		// test the runmode of current dataset
		String pagetitle;
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			extentLogger.log(LogStatus.SKIP, "This test case is skipped");
			throw new SkipException("Runmode for test set data set to no " + count);
		}

		LOG.info(" Executing : " + className);
		LOG.info(browser + " -- " + uri + " -- " + searchWord);

		WebDriver driver = BrowserFactory.startBrowser(browser, uri);

		GoogleSearchPage googleSearchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
		
		try {
		googleSearchPage.search(searchWord, extentLogger, extent, "Searching the word");
		} catch (Exception e){
			isTestPass = MiscUtils.elementNotFoundErrorHandler(driver,extentLogger,extent,className, "Searching the word");
			throw e;
		}

		pagetitle = driver.getTitle();

		try {
			MiscUtils.assertion(pagetitle, title, extentLogger, "Compairing Page titles");
		} catch (Throwable t) {
			MiscUtils.assertionExceptionHandler(pagetitle, title, extentLogger, "Compairing Page titles", driver,
					className, t);
			// report the error in xls files
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
		MiscUtils.afterTestAssessment(testAssessment);
	}

	@DataProvider
	public Object[][] getTestData() {
		return AutomationDataProvider.getData(smokeSuite, className);
	}

}
