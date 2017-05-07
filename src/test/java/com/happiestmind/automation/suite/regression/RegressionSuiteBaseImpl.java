package com.happiestmind.automation.suite.regression;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.happiestmind.automation.base.AutomationSuiteBase;
import com.happiestmind.automation.loaders.XlsSheetsLoader;
import com.happiestmind.automation.util.TestUtil;

public class RegressionSuiteBaseImpl extends XlsSheetsLoader implements AutomationSuiteBase {
	
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		if(!TestUtil.isSuiteRunnable(parentSuite, "Regression Suite")){
			throw new SkipException("Runmode of Suite A set to no. So Skipping all tests in Suite A");
		}
		
	}

}
