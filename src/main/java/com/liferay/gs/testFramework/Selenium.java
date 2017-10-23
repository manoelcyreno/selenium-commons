package com.liferay.gs.testFramework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class Selenium {

	private static WebDriver driver = null;

	private static String SeleniumGridMachine = SeleniumReadPropertyKeys.getSeleniumGridMachine();
	private static String SeleniumGridDocker = SeleniumReadPropertyKeys.getSeleniumGridDocker();
	private static String PhantomJS_Path = SeleniumReadPropertyKeys.getPhantomJSPath();
	private static String GeckoDriver_Path = SeleniumReadPropertyKeys.getGeckoDriverPath();
	private static String ChromeDriver_Path = SeleniumReadPropertyKeys.getChromeDriverPath();
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

			switch (SeleniumReadPropertyKeys.getPlatformName()) {
			case "ie":
				configureIE(capabilities);
				break;

			case "chrome":
				configureChrome();
				break;

			case "defaultFF":
				configureDefaultFF();
				break;

			case "defaultGC":
				configureDefaultGC();
				break;

			case "firefox":
				configureFirefox(capabilities);
				break;

			case "phantomjs":
				configurePhantomJS(capabilities);
				break;

			case "defaultGCHeadless":
				configureDefaultGCHeadless();
				break;

			case "dockerGC":
				configuraSeleniumGridWithDocker();
				break;

			default:
				break;
			}
		} catch (MalformedURLException e) {
			System.out.println(e);
		}
	}

	private static void configureDefaultFF() {
		if (geckoDriverWasConfigured() == true) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			System.setProperty("webdriver.gecko.driver", GeckoDriver_Path);
			cap.setCapability("marionette", true);
			driver = new FirefoxDriver(cap);
		} else {
			System.out.println(configurationErrorMessage);
		}
	}

	private static void configureDefaultGC() {
		if (chromeDriverWasConfigured() == true) {
			System.setProperty("webdriver.chrome.driver", ChromeDriver_Path);
			driver = new ChromeDriver();
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

	private static void configuraSeleniumGridWithDocker() throws MalformedURLException {
		Capabilities chromeCapabilities = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL(SeleniumGridDocker), chromeCapabilities);
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

	private static void configureDefaultGCHeadless() {
		if (chromeDriverWasConfigured() == true) {
			ChromeOptions chromeOptions = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", ChromeDriver_Path);
			chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
		} else {
			System.out.println(configurationErrorMessage);
		}
	}

	public static void quit() {
		driver.quit();
		driver = null;
	}

	public static void forceInitDriver() {
		initDriver();
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

	private static boolean chromeDriverWasConfigured() {
		File chromeDriverFile = new File(ChromeDriver_Path);
		if (chromeDriverFile.exists() == true && chromeDriverFile.canExecute() == true) {
			configurationErrorMessage = "The chromedriver was configured correctly";
			return true;
		} else {
			configurationErrorMessage = "The chromedriver should be configured in '" + ChromeDriver_Path
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
		File defaultPropertiesFile = new File(SeleniumReadPropertyKeys.getSeleniumPropertyKeysFilePath());
		if (defaultPropertiesFile.exists() == true && defaultPropertiesFile.canRead() == true) {
			configurationErrorMessage = "The defaultProperties.properties file was configured correctly";
			return true;
		} else {
			configurationErrorMessage = "The defaultProperties.properties should be configured in '"
					+ SeleniumReadPropertyKeys.getSeleniumPropertyKeysFilePath() + "' according the project wiki.";
			return false;
		}
	}

}