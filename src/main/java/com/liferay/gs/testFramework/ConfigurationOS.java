package com.liferay.gs.testFramework;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigurationOS {
	private static final String BUNDLE_NAME_MAC = "com.liferay.gs.testFramework.configuration"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_WIN = "com.liferay.gs.testFramework.configurationWin"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE_MAC = ResourceBundle.getBundle(BUNDLE_NAME_MAC);
	private static final ResourceBundle RESOURCE_BUNDLE_WIN = ResourceBundle.getBundle(BUNDLE_NAME_WIN);

	private ConfigurationOS() {
	}

	public static String getString(String key) {
		try {
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				return RESOURCE_BUNDLE_WIN.getString(key);
			} else {
				return RESOURCE_BUNDLE_MAC.getString(key);
			}
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
