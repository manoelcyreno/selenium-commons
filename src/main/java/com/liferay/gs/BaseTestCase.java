package com.liferay.gs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

public class BaseTestCase {

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();

	@BeforeClass
	public static void beforeClass() {
	};

	@AfterClass
	public static void afterClass() {
		Selenium.quit();
	};

}
