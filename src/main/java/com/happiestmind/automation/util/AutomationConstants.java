package com.happiestmind.automation.util;

public class AutomationConstants {

	public static final String extentReportFile = System.getProperty("user.dir") + "/extentreports/extentTestReport_"
			+ MiscUtils.getCurrentDateAndTime() + ".html";
	public static final String extentReportImage = System.getProperty("user.dir")
			+ "/extentreports/extentReportImage.png";
	public static final String configXmlLocation = System.getProperty("user.dir")
			+ "/src/main/resources/com/happiestmind/config/extent-config.xml";
	public static final String PASS = "PASS";
	public static final String FAIL = "FAIL";
	public static final String SKIP = "SKIP";
	public static final String YYYY_MM_DD_HH_MM_SS="yyyy_MM_dd_HH_mm_ss";
	public static final String PNG=".png";
	public static final String SCREENSHOT_FOLDER="/screenshots/";
	public static final String USER_DIR="user.dir";
	public static final String COMPANY="Company";
	public static final String ENVIRONMENT="Environment";
	public static final String AUTOMATION_DEVELOPER="AutomationDeveloper";

}
