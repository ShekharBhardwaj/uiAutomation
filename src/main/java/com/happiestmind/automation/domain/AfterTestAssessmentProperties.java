package com.happiestmind.automation.domain;

import com.happiestmind.automation.util.ParameterReader;
import com.relevantcodes.extentreports.ExtentReports;

public class AfterTestAssessmentProperties {

	private boolean isTestPass;
	private ParameterReader suiteObject;
	private ExtentReports extent;
	private String className;
	
	

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public ExtentReports getExtent() {
		return extent;
	}

	public void setExtent(ExtentReports extent) {
		this.extent = extent;
	}

}
