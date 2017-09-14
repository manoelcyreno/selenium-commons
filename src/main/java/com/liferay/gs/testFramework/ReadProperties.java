package com.liferay.gs.testFramework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ReadProperties {

	public static String getConfigigurationOfPropertiesFile(String parameterName) {
		Properties prop = getConfigurationProperties();
		String text = prop.getProperty(parameterName);
		return text;
	}

	public static String getConfigigurationOfPropertiesFile(String parameterName, String defaultValue) {
		Properties prop = getConfigurationProperties();
		String text = prop.getProperty(parameterName, defaultValue);
		return text;
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
		String defaultProperties = SeleniumReadPropertyKeys.getDefaultPropertiesFilePath();
		return defaultProperties;
	}
}
