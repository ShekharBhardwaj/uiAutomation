package com.happiestmind.automation.errorhandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ErrorHandler {
	
	
	final static Logger LOG = Logger.getLogger(ErrorHandler.class);
	private static Map<ITestResult, List> verificationFailuresMap = new HashMap<ITestResult, List>();

	public static void setVerificationFailure(Throwable e) {
		LOG.info("*************addVerificationFailure******************");
		List verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
		verificationFailures.add(e);
	}

	public static List getVerificationFailures() {
		LOG.info("*************getVerificationFailures******************");
		List verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList() : verificationFailures;
	}

}
