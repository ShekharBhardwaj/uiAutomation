package com.happiestmind.automation.loaders;

import org.apache.log4j.Logger;

import com.happiestmind.automation.util.ParameterReader;

public class XlsSheetsLoader {
	
	final static Logger LOG = Logger.getLogger(XlsSheetsLoader.class);
	private static boolean isInitalized = false;
	public static ParameterReader parentSuite = null;
	public static ParameterReader smokeSuite= null;
	public static ParameterReader regressionSuite= null;

	

	// initializing the Tests
	public static void initialize() throws Exception {
		if (!isInitalized) {

			// xls file
			LOG.info("Initializing data xlsx files for data provider.");
			smokeSuite = new ParameterReader(
					System.getProperty("user.dir") + "/src/test/resources/com/happiestmind/data/"+PropertiesLoader.loadConfigurations().getProperty("smoke.suite.xls"));
			parentSuite = new ParameterReader(
					System.getProperty("user.dir") + "/src/test/resources/com/happiestmind/data/"+PropertiesLoader.loadConfigurations().getProperty("parent.suite.xls"));
			
			isInitalized = true;
		}
	}
	
}