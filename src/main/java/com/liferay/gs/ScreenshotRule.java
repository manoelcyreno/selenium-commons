package com.liferay.gs;
import java.io.File;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotRule extends TestWatcher {

	public static String screenshotPicture = null;

	// @Override
	// protected void failed(Throwable e, Description description) {
	// takeScreenshot(description.getTestClass().getSimpleName(),
	// description.getMethodName());
	// }
	//
	// public void takeScreenshot(String className, String testName) {
	// try {
	// new File("reports/screenshots/").mkdirs();
	// File screenshot = ((TakesScreenshot)
	// Selenium.getDriver()).getScreenshotAs(OutputType.FILE);
	// screenshotPicture = "screenshots/" + className + "-" + testName +
	// "-screenshot.png";
	// FileUtils.copyFile(screenshot, new File("reports/" + screenshotPicture));
	// } catch (Exception e) {
	// System.out.println("Failure to try take screenshot: " + e);
	// }
	// }
}