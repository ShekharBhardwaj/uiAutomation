package com.happiestmind.automation.util;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentSucessLogger {

	public static boolean assertionSuccess(Object actual, Object expected, ExtentTest extentLogger,
			String step) {
		extentLogger.log(LogStatus.PASS, "Step: " + step + ".", "Comparison between " + actual + " and " + expected);
		return true;
	}
	

	public static boolean elementFoundSuccess(ExtentTest extentLogger, ExtentReports extent,
			String className, String step)  {
		extentLogger.log(LogStatus.PASS, "Step: " + step + ".",
				"Required Element found.");
		return true;

	}

}
