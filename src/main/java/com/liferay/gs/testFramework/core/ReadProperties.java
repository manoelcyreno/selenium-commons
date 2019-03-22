package com.liferay.gs.testFramework.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author Manoel Cyreno
 * @author Italo Laino
 */
public class ReadProperties {

	public static String getConfigurationOfPropertiesFile(String parameterName) {
		return getConfigurationProperties().getProperty(parameterName);
	}
	
	public static String getConfigurationOfPropertiesFile(String parameterName, String defaultValue) {
		return getConfigurationProperties().getProperty(parameterName, defaultValue);
	}

	private static Properties getConfigurationProperties() {

		Properties properties = new Properties();
		FileInputStream is = null;
		InputStreamReader isr = null;
		try {
			is = new FileInputStream(findConfigurationPropertiesPath());
			isr = new InputStreamReader(is, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			properties.load(isr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	private static String findConfigurationPropertiesPath() {
		return SeleniumReadPropertyKeys.getSeleniumPropertyKeysFilePath();
	}
}
