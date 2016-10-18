package com.liferay.gs;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class Selenium {

	private static WebDriver driver = null;

	private static String SeleniumGridMachine = UtilsKeys.getSeleniumGridMachine();
	private static String PhantomJS_Path = UtilsKeys.getPhantomJSPath();

	public static boolean isValidBrowser() {

		boolean validVersion = true;

		if (getDriver() instanceof FirefoxDriver) {

			Capabilities cap = ((FirefoxDriver) getDriver()).getCapabilities();

			String mainVersion = cap.getVersion().split("\\.")[0];
			System.out.println("FF version: " + mainVersion);

			float currentVersion = Float.valueOf(mainVersion);
			float maxValidVersion = Float.valueOf(UtilsKeys.getUpToFirefoxVersion());

			validVersion = currentVersion <= maxValidVersion;
		}

		System.out.println("validVersion: " + validVersion);
		return validVersion;
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			initDriver();
		}
		return driver;
	}

	private static void initDriver() {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			switch (UtilsKeys.getPlatformName()) {
			case "ie":
				configureIE(capabilities);
				break;

			case "chrome":
				configureChrome();
				break;

			case "default":
				configureDefault();
				break;

			case "firefox":
				configureFirefox(capabilities);
				break;

			case "phantomjs":
				configurePhantomJS(capabilities);
				break;

			default:
				break;
			}
		} catch (MalformedURLException e) {
			System.out.println(e);
		}
	}

	private static void configureDefault() {
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		System.setProperty("webdriver.gecko.driver", UtilsMethods.getMarionetteDriverPath());
		cap.setCapability("marionette", true);
		driver = new FirefoxDriver(cap);
	}

	private static void configureIE(DesiredCapabilities capabilities) throws MalformedURLException {
		capabilities.setBrowserName("internet explorer");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new RemoteWebDriver(new URL(SeleniumGridMachine), capabilities);
	}

	private static void configureChrome() throws MalformedURLException {
		driver = new RemoteWebDriver(new URL(SeleniumGridMachine), DesiredCapabilities.chrome());
	}

	private static void configureFirefox(DesiredCapabilities capabilities) throws MalformedURLException {
		capabilities.setBrowserName("firefox");
		driver = new RemoteWebDriver(new URL(SeleniumGridMachine), capabilities);
	}

	private static void configurePhantomJS(DesiredCapabilities capabilities) throws MalformedURLException {
		capabilities.setBrowserName("PhantomJS");
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PhantomJS_Path);
		driver = new PhantomJSDriver(capabilities);
	}

	public static void quit() {
		driver.quit();
		driver = null;
	}
}