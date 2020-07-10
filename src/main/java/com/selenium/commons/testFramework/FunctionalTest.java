package com.selenium.commons.testFramework;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import com.selenium.commons.testFramework.driver.WebDriverManager;

/**
 * @author Italo Laino
 * @author Manoel Cyreno
 */
public abstract class FunctionalTest {

	public FunctionalTest() {
		_webDriver = _webDriverManager.get();
	}

	@AfterEach
	public void after() {
	}

	@BeforeEach
	public void before() throws IOException {
	}

	public WebDriver getWebDriver() {
		return _webDriver;
	}

	public void openURL(String url) {
		getWebDriver().get(url);
	}

	private final WebDriver _webDriver;
	private final WebDriverManager _webDriverManager = new WebDriverManager();
}