package com.liferay.gs.testFramework;
import org.openqa.selenium.WebDriver;

public class UtilsKeys {

	public static final WebDriver DRIVER = Selenium.getDriver();

	private static String TIME_OUT = null;

	private static String PLATFORM_NAME = null;
	private static String URL = null;
	private static String URL_LOGOUT = null;
	private static String SELENIUM_GRID_IP = null;
	private static String PHANTOMJS_PATH = null;
	private static String GECKODRIVER_PATH = null;
	private static String ATTACH_FILE_PATH = null;
	private static String DEFAULT_PROPERTIES_FILE_PATH = null;

	public static int getTimeOut() {
		TIME_OUT = ReadProperties.getConfigigurationOfPropertiesFile("time-out");
		int timeOut = Integer.parseInt(TIME_OUT);
		return timeOut;
	}

	public static String getAttachFilePath() {
		ATTACH_FILE_PATH = ReadProperties.getConfigigurationOfPropertiesFile("PathWithAttachFiles");
		return ATTACH_FILE_PATH;
	}

	public static String getPlatformName() {
		PLATFORM_NAME = ReadProperties.getConfigigurationOfPropertiesFile("browser");
		return PLATFORM_NAME;
	}

	public static String getUrlToHome() {
		URL = ReadProperties.getConfigigurationOfPropertiesFile("environment");
		return URL;
	}

	public static String getLinkToLogOut() {
		URL_LOGOUT = ReadProperties.getConfigigurationOfPropertiesFile("linkToLogOut");
		return URL_LOGOUT;
	}

	public static String getSeleniumGridMachine() {
		SELENIUM_GRID_IP = ReadProperties.getConfigigurationOfPropertiesFile("SeleniumGridMachine");
		return SELENIUM_GRID_IP;
	}

	public static String getPhantomJSPath() {
		PHANTOMJS_PATH = Configuration.getString("PhantomJS_Path");
		return PHANTOMJS_PATH;
	}

	public static String getGeckoDriverPath() {
		GECKODRIVER_PATH = Configuration.getString("GeckoDriver_Path");
		return GECKODRIVER_PATH;
	}

	public static String getDefaultPropertiesFilePath() {
		DEFAULT_PROPERTIES_FILE_PATH = Configuration.getString("defaultPropertiesFile");
		return DEFAULT_PROPERTIES_FILE_PATH;
	}

}
