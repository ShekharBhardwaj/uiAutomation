package com.happiestmind.automation.domain;

import org.testng.ITestResult;

import com.happiestmind.automation.util.ParameterReader;
import com.relevantcodes.extentreports.ExtentTest;

public class AfterMethodAssessmentProperties {

	private boolean skip;
	private boolean fail;
	private boolean isTestPass;
	private ParameterReader suiteObject;
	private String className;
	private int count;
	private ITestResult result;
	private ExtentTest extentLogger;

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isFail() {
		return fail;
	}

	public void setFail(boolean fail) {
		this.fail = fail;
	}

	public boolean isTestPass() {
		return isTestPass;
	}

	public void setTestPass(boolean isTestPass) {
		this.isTestPass = isTestPass;
	}

	public ParameterReader getSuiteObject() {
		return suiteObject;
	}

	public void setSuiteObject(ParameterReader suiteObject) {
		this.suiteObject = suiteObject;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ITestResult getResult() {
		return result;
	}

	public void setResult(ITestResult result) {
		this.result = result;
	}

	public ExtentTest getExtentLogger() {
		return extentLogger;
	}

	public void setExtentLogger(ExtentTest extentLogger) {
		this.extentLogger = extentLogger;
	}

}
