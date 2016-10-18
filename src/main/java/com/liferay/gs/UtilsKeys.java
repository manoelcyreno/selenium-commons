package com.liferay.gs;
import org.openqa.selenium.WebDriver;

public class UtilsKeys {

	public static final WebDriver DRIVER = Selenium.getDriver();

	private static String TIME_OUT = Configuration.getString("time-out");

	private static String VALID_UP_TO_FIREFOX_VERSION_KEY = Configuration.getString("valid-up-to-firefox-version");
	private static String PLATFORM_NAME = Configuration.getString("browser");
	private static String URL = Configuration.getString("environment");
	private static String URL_LOGOUT = Configuration.getString("linkToLogOut");
	private static String SELENIUM_GRID_IP = Configuration.getString("SeleniumGridMachine");
	private static String PHANTOMJS_PATH = Configuration.getString("PhantomJS_Path");

	public static int getTimeOut() {
		int timeOut = Integer.parseInt(TIME_OUT);
		return timeOut;
	}

	public static String getUpToFirefoxVersion() {
		return VALID_UP_TO_FIREFOX_VERSION_KEY;
	}

	public static String getPlatformName() {
		System.out.println("getPlatformName(): " + PLATFORM_NAME);
		return PLATFORM_NAME;
	}

	public static String getUrlToHome() {
		return URL;
	}

	public static String getLinkToLogOut() {
		return URL_LOGOUT;
	}

	public static String getSeleniumGridMachine() {
		return SELENIUM_GRID_IP;
	}

	public static String getPhantomJSPath() {
		return PHANTOMJS_PATH;
	}

}
