package com.liferay.gs.testFramework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

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
	private static String GeckoDriver_Path = UtilsKeys.getGeckoDriverPath();
	private static String configurationErrorMessage = null;

	public static WebDriver getDriver() {
		if (defaultPropertiesFilePathWasConfigured() == true) {
			if (driver == null) {
				initDriver();
			}
			return driver;
		} else {
			System.out.println(configurationErrorMessage);
			return null;
		}

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
		if (geckoDriverWasConfigured() == true) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			System.setProperty("webdriver.gecko.driver", GeckoDriver_Path);
			cap.setCapability("marionette", true);
			driver = new FirefoxDriver(cap);
		} else {
			System.out.println(configurationErrorMessage);
		}
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
		if (phantomJSWasConfigured() == true) {
			capabilities.setBrowserName("PhantomJS");
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PhantomJS_Path);
			driver = new PhantomJSDriver(capabilities);
		} else {
			System.out.println(configurationErrorMessage);
		}
	}

	public static void quit() {
		driver.quit();
		driver = null;
	}

	private static boolean geckoDriverWasConfigured() {
		File genckoDriverFile = new File(GeckoDriver_Path);
		if (genckoDriverFile.exists() == true && genckoDriverFile.canExecute() == true) {
			configurationErrorMessage = "The geckodriver was configured correctly";
			return true;
		} else {
			configurationErrorMessage = "The geckodriver should be configured in '" + GeckoDriver_Path
					+ "' according the project wiki.";
			return false;
		}
	}

	private static boolean phantomJSWasConfigured() {
		File phantomJSDriverFile = new File(PhantomJS_Path);
		if (phantomJSDriverFile.exists() == true && phantomJSDriverFile.canExecute() == true) {
			configurationErrorMessage = "The phantomJS was configured correctly";
			return true;
		} else {
			configurationErrorMessage = "The phantomJS should be configured in '" + PhantomJS_Path
					+ "' according the project wiki.";
			return false;
		}
	}

	private static boolean defaultPropertiesFilePathWasConfigured() {
		File defaultPropertiesFile = new File(UtilsKeys.getDefaultPropertiesFilePath());
		if (defaultPropertiesFile.exists() == true && defaultPropertiesFile.canRead() == true) {
			configurationErrorMessage = "The defaultProperties.properties file was configured correctly";
			return true;
		} else {
			configurationErrorMessage = "The defaultProperties.properties should be configured in '"
					+ UtilsKeys.getDefaultPropertiesFilePath() + "' according the project wiki.";
			return false;
		}
	}

}