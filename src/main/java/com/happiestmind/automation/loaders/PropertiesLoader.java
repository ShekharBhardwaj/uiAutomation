package com.happiestmind.automation.loaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesLoader {
	
	final static Logger LOG = Logger.getLogger(PropertiesLoader.class);
	public static boolean isInitialized = false;
	private static Properties prop = new Properties();
	private static InputStream input = null;

	public static Properties loadConfigurations() {

		if (!isInitialized) {
			prop = new Properties();
			
			try {
				LOG.info("Loading properties from : "+ System.getProperty("user.dir") + "/src/main/resources/config.properties");
				input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");

				// load a properties file
				prop.load(input);

			} catch (IOException ex) {
				LOG.error("Error during loading the properties.");
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						LOG.error("Error during closing the input stream the properties. could be a memory leak.");
						e.printStackTrace();
					}
				}
			}
			isInitialized = true;
		}

		return prop;

	}

}
