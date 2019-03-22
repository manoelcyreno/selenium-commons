package com.liferay.gs.testFramework;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

import com.liferay.gs.testFramework.core.SeleniumReadPropertyKeys;
import com.liferay.gs.testFramework.driver.WebDriverManager;
import com.liferay.gs.testFramework.errorHandling.RetryTestExecution;
import com.liferay.gs.testFramework.errorHandling.ScreenshotTaker;

/**
 * @author Italo Laino
 * @author Manoel Cyreno
 */
public abstract class FunctionalTest {

	public FunctionalTest() {
		_webDriver = _webDriverManager.get();

		retry = new RetryTestExecution(_webDriver, SeleniumReadPropertyKeys.getNumberOfFailedTestsRetryTestExecution());
	}

	@After
	public void after() {
		_screenshotTaker.take(_webDriver);
	}

	@Before
	public void before() throws IOException {
	}

	public WebDriver getWebDriver() {
		return _webDriver;
	}

	public void openURL(String url) {
		getWebDriver().get(url);
	}

	@Rule
	public Timeout globalTimeout = Timeout.seconds(5 * 60);

	@Rule
	public RetryTestExecution retry;

	@Rule
	public TestWatcher writeScreenshotOnFailure = new TestWatcher() {

		protected void failed(Throwable e, Description description) {
			String fileName = description.getClassName() + "." + description.getMethodName() + ".png";

			_screenshotTaker.save(fileName);
		}
	};

	private final ScreenshotTaker _screenshotTaker = new ScreenshotTaker();
	private final WebDriver _webDriver;
	private final WebDriverManager _webDriverManager = new WebDriverManager();
}