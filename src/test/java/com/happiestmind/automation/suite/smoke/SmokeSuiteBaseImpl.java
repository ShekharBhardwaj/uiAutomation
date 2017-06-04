package com.happiestmind.automation.suite.smoke;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.happiestmind.automation.exception.ParameterReaderCustomException;
import com.happiestmind.automation.exception.SheetLoaderCustomException;
import com.happiestmind.automation.loaders.XlsSheetsLoader;
import com.happiestmind.automation.util.MiscUtils;
import com.happiestmind.automation.util.TestUtil;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SmokeSuiteBaseImpl extends XlsSheetsLoader {

	static ExtentReports extent;
	ExtentTest extentLogger;

	@BeforeSuite
	public void checkSuiteSkip() throws ParameterReaderCustomException, SheetLoaderCustomException, IOException {
		initialize();
		if (!TestUtil.isSuiteRunnable(parentSuite, "SmokeTests")) {
			throw new SkipException("RUnmode of SmokeTests Suite set to N. So Skipping all tests in SmokeTests Suite");
		}
		extent = MiscUtils.getExtentObject(extent, "SmokeSuite");

	}

	@AfterSuite
	public void closeConnections() throws Exception {
		extent.flush();
		extent.close();
	}

}
