package com.happiestmind.automation.suite.regression;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.happiestmind.automation.base.AutomationSuiteBase;
import com.happiestmind.automation.exception.ParameterReaderCustomException;
import com.happiestmind.automation.exception.SheetLoaderCustomException;
import com.happiestmind.automation.loaders.XlsSheetsLoader;
import com.happiestmind.automation.util.MiscUtils;
import com.happiestmind.automation.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class RegressionSuiteBaseImpl extends XlsSheetsLoader implements AutomationSuiteBase {
	
	ExtentReports extent;
	ExtentTest extentLogger;

	@BeforeSuite
	public void checkSuiteSkip() throws ParameterReaderCustomException, SheetLoaderCustomException, IOException  {
		initialize();
		if (!TestUtil.isSuiteRunnable(parentSuite, "RegressionTests")) {
			throw new SkipException("Runmode of Regression Suite set to N. So Skipping all tests in RegressionTest Suite");
		} else {
			extent = MiscUtils.getExtentObject(extent, "RegressionSuite");
		}

	}

	@AfterSuite
	public void closeConnections() throws Exception {
		extent.close();
	}


}
