package com.happiestmind.automation.loaders;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

public class AutomationDriverLoader {
	
	final static Logger LOG = Logger.getLogger(AutomationDriverLoader.class);

	public static String loadGeckoDriver() throws IOException, URISyntaxException {
		String uri;
		LOG.info("Loading gecko driver...");
		LOG.info("OS | "+System.getProperty("os.name"));
		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			uri = System.getProperty("user.dir") + PropertiesLoader.loadConfigurations().getProperty("gecko.driver.mac");
		} else {
			uri = System.getProperty("user.dir") +  PropertiesLoader.loadConfigurations().getProperty("gecko.driver.win");
		}
		return uri;
	}
	
	
	public static String loadChromeDriver() throws IOException, URISyntaxException {
		String uri;
		LOG.info("Loading chrome driver...");
		LOG.info("OS | "+System.getProperty("os.name"));
		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			uri = System.getProperty("user.dir") +  PropertiesLoader.loadConfigurations().getProperty("chrome.driver.mac");
		} else {
			uri = System.getProperty("user.dir") +  PropertiesLoader.loadConfigurations().getProperty("chrome.driver.win");
		}
		return uri;
	}

}
