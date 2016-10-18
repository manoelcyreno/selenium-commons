package com.liferay.gs;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class Property {

	private Property() {
	}

	private static Properties getPropConfiguration() {
		Properties properties = new Properties();
		String fileName = Configuration.getString("key"); //$NON-NLS-1$

		InputStream is = Property.class.getClassLoader().getResourceAsStream(fileName);
		try {
			properties.load(is);
		} catch (IOException e) {
			System.out.println(e);
		}

		return properties;
	}

	public static String getConfiguration(String parameterName) {
		Properties prop = getPropConfiguration();
		return prop.getProperty(parameterName);
	}
}
