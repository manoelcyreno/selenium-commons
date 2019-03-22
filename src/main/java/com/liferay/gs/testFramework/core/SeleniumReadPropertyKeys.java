package com.liferay.gs.testFramework.core;

import java.io.File;

/**
 * @author Manoel Cyreno
 * @author Italo Laino
 */
public class SeleniumReadPropertyKeys {

	public static int getNumberOfBrowserInParallel() {
		return Integer.parseInt(ReadProperties.getConfigurationOfPropertiesFile("number-of-browser-in-parallel", "4"));
	}
	
	public static int getNumberOfFailedTestsRetryTestExecution() {
		return Integer.parseInt(ReadProperties.getConfigurationOfPropertiesFile("number-of-failed-tests-retry-test-execution", "3"));
	}

	public static int getTimeOut() {
		return Integer.parseInt(ReadProperties.getConfigurationOfPropertiesFile("time-out", "10"));
	}

	public static String getDefaultPlatformName() {
		return ReadProperties.getConfigurationOfPropertiesFile("browser", "defaultGCHeadless");
	}

	public static String getUrlToHome() {
		return ReadProperties.getConfigurationOfPropertiesFile("environment", "http://localhost:8080");
	}

	public static String getDefaultUsername() {
		return ReadProperties.getConfigurationOfPropertiesFile("username", "test@liferay.com");
	}

	public static String getDefaultPassword() {
		return ReadProperties.getConfigurationOfPropertiesFile("password", "test");
	}

	public static String getLinkToLogOut() {
		return ReadProperties.getConfigurationOfPropertiesFile("link-to-logout", "/c/portal/logout");
	}

	public static String getDownloadSaveFilePath() {
		return ReadProperties.getConfigurationOfPropertiesFile("download-save-file-path", "default");
	}

	public static String getGeckoDriverPath() {
		String localPropertiesFile;
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			localPropertiesFile = "/SeleniumProperties/geckodriver.exe";
		} else {
			localPropertiesFile = "/SeleniumProperties/geckodriver";
		}

		File localFile = new File(getProjectPath() + localPropertiesFile);

		String GECKODRIVER_PATH;

		if (localFile.exists()) {
			GECKODRIVER_PATH = getProjectPath() + localPropertiesFile;
		} else {
			GECKODRIVER_PATH = ConfigurationOS.getString("GeckoDriver_Path");
		}

		return GECKODRIVER_PATH;
	}

	public static String getChromeDriverPath() {
		String localPropertiesFile;
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			localPropertiesFile = "/SeleniumProperties/chromedriver.exe";
		} else {
			localPropertiesFile = "/SeleniumProperties/chromedriver";
		}

		File localFile = new File(getProjectPath() + localPropertiesFile);

		String CHROMEDRIVER_PATH;

		if (localFile.exists()) {
			CHROMEDRIVER_PATH = getProjectPath() + localPropertiesFile;
		} else {
			CHROMEDRIVER_PATH = ConfigurationOS.getString("ChromeDriver_Path");
		}

		return CHROMEDRIVER_PATH;
	}

	public static String getSeleniumPropertyKeysFilePath() {
		String localPropertiesFile = "/SeleniumProperties/SeleniumPropertyKeys.properties";
		File localFile = new File(getProjectPath() + localPropertiesFile);

		String DEFAULT_PROPERTIES_FILE_PATH;

		if (localFile.exists()) {
			DEFAULT_PROPERTIES_FILE_PATH = getProjectPath() + localPropertiesFile;
		} else {
			DEFAULT_PROPERTIES_FILE_PATH = ConfigurationOS.getString("SeleniumPropertyKeys");
		}

		return DEFAULT_PROPERTIES_FILE_PATH;
	}

	private static String getProjectPath() {
		return System.getProperty("user.dir");
	}

}
