package com.happiestmind.automation.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	public static boolean isInitialized = false;
	private static Properties prop = new Properties();
	private static InputStream input = null;

	public static Properties loadConfigurations() {

		if (!isInitialized) {
			prop = new Properties();

			try {

				input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");

				// load a properties file
				prop.load(input);

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			isInitialized = true;
		}

		return prop;

	}

}
