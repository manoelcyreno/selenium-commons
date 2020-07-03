package com.selenium.commons.testFramework.driver;

import static com.selenium.commons.testFramework.utils.SeleniumWaitMethods.waitMediumTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;

/**
 * @author Italo Laino
 */
public class WebDriverManager {

	public synchronized WebDriver get() {
		if (_driver.get() == null) {
			WebDriver webDriver = WebDriverBuilder.build();

			_drivers.add(webDriver);

			_driver.set(webDriver);
		}

		return _driver.get();
	}

	public synchronized void quitAll() {
		_drivers.forEach(webDriver -> {
			webDriver.close();

			waitMediumTime();
			waitMediumTime();
			waitMediumTime();

			webDriver.quit();
		});
	}

	private static ThreadLocal<WebDriver> _driver = new ThreadLocal<>();
	private static List<WebDriver> _drivers = Collections.synchronizedList(new ArrayList<>());

}