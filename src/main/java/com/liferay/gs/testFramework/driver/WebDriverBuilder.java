package com.liferay.gs.testFramework.driver;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.liferay.gs.testFramework.core.SeleniumReadPropertyKeys;

/**
 * @author Italo Laino
 * @author Manoel Cyreno
 */
public final class WebDriverBuilder {

	protected static WebDriver build() {
		if (_defaultPropertiesFilePathWasConfigured()) {
			WebDriver webDriver = _build();

			_configure(webDriver);

			return webDriver;
		} else {
			System.out.println(_configurationErrorMessage);

			return null;
		}
	}

	private static WebDriver _build() {
		switch (SeleniumReadPropertyKeys.getDefaultPlatformName()) {
			case "defaultFF":
				return _configureDefaultFF();
			case "defaultGC":
				return _configureDefaultGC();
			case "defaultGCHeadless":
				return _configureDefaultGCHeadless();
			default:
				return null;
		}
	}

	private static boolean _chromeDriverWasConfigured() {
		File chromeDriverFile = new File(_chromeDriver_Path);

		if (chromeDriverFile.exists() && chromeDriverFile.canExecute()) {
			_configurationErrorMessage = "The chromedriver was configured correctly";

			return true;
		} else {
			_configurationErrorMessage = "The chromedriver should be configured in '" + _chromeDriver_Path +
				"' according the project wiki.";

			return false;
		}
	}

	private static void _configure(WebDriver webDriver) {
		WebDriver.Options manage = webDriver.manage();

		manage.window().setSize(new Dimension(1024, 945));
		manage.timeouts().implicitlyWait(SeleniumReadPropertyKeys.getTimeOut(), TimeUnit.SECONDS);
	}

	private static FirefoxDriver _configureDefaultFF() {
		if (_geckoDriverWasConfigured()) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			System.setProperty("webdriver.gecko.driver", _geckoDriver_Path);
			cap.setCapability("marionette", true);

			FirefoxOptions options = new FirefoxOptions();

			options.addCapabilities(cap);

			return new FirefoxDriver(options);
		} else {
			System.out.println(_configurationErrorMessage);
		}

		return null;
	}

	private static ChromeDriver _configureDefaultGC() {
		if (_chromeDriverWasConfigured()) {
			System.setProperty("webdriver.chrome.driver", _chromeDriver_Path);

			if (Objects.equals(_downloadSaveFilePath, "default")) {
				return new ChromeDriver();
			} else {
				HashMap<String, Object> chromePrefs = new HashMap<>();
				chromePrefs.put("download.default_directory", _downloadSaveFilePath);
				chromePrefs.put("profile.default_content_settings.popups", 0);
				ChromeOptions options = new ChromeOptions();

				options.setExperimentalOption("prefs", chromePrefs);

				DesiredCapabilities cap = DesiredCapabilities.chrome();

				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap.setCapability(ChromeOptions.CAPABILITY, options);

				return new ChromeDriver(cap);
			}
		} else {
			System.out.println(_configurationErrorMessage);
		}

		return null;
	}

	private static ChromeDriver _configureDefaultGCHeadless() {
		if (_chromeDriverWasConfigured()) {
			ChromeOptions chromeOptions = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", _chromeDriver_Path);
			chromeOptions.addArguments("--headless");

			return new ChromeDriver(chromeOptions);
		} else {
			System.out.println(_configurationErrorMessage);
		}

		return null;
	}

	private static boolean _defaultPropertiesFilePathWasConfigured() {
		File defaultPropertiesFile = new File(SeleniumReadPropertyKeys.getSeleniumPropertyKeysFilePath());

		if (defaultPropertiesFile.exists() && defaultPropertiesFile.canRead()) {
			_configurationErrorMessage = "The defaultProperties.properties file was configured correctly";

			return true;
		} else {
			_configurationErrorMessage = "The defaultProperties.properties should be configured in '" +
				SeleniumReadPropertyKeys.getSeleniumPropertyKeysFilePath() + "' according the project wiki.";

			return false;
		}
	}

	private static boolean _geckoDriverWasConfigured() {
		File genckoDriverFile = new File(_geckoDriver_Path);

		if (genckoDriverFile.exists() && genckoDriverFile.canExecute()) {
			_configurationErrorMessage = "The geckodriver was configured correctly";

			return true;
		} else {
			_configurationErrorMessage = "The geckodriver should be configured in '" + _geckoDriver_Path +
				"' according the project wiki.";

			return false;
		}
	}

	private static String _chromeDriver_Path = SeleniumReadPropertyKeys.getChromeDriverPath();
	private static String _configurationErrorMessage = null;
	private static String _downloadSaveFilePath = SeleniumReadPropertyKeys.getDownloadSaveFilePath();
	private static String _geckoDriver_Path = SeleniumReadPropertyKeys.getGeckoDriverPath();

}