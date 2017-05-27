package com.happiestmind.automation.suite.smoke;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.happiestmind.automation.loaders.XlsSheetsLoader;
import com.happiestmind.automation.util.TestUtil;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SmokeSuiteBaseImpl extends XlsSheetsLoader {

	ExtentReports extent;
	ExtentTest extentLogger;

	@BeforeSuite
	public void checkSuiteSkip() throws Exception {
		initialize();
		if (!TestUtil.isSuiteRunnable(parentSuite, "SmokeTests")) {
			throw new SkipException("RUnmode of SmokeTests Suite set to N. So Skipping all tests in SmokeTests Suite");
		}

	}

	@AfterSuite
	public void closeConnections() throws Exception {
		extent.close();
	}

}
