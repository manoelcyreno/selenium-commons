package com.liferay.gs.testFramework;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class ScreenshotRule extends TestWatcher {

	private static String screenshotPicture = null;
	private static boolean testFail = false;

	public static boolean isTestFail() {
		return testFail;
	}

	@Override
	protected void failed(Throwable e, Description description) {
		testFail = true;
		takeScreenshot(description.getTestClass().getSimpleName(), description.getMethodName());
	}

	public void takeScreenshot(String className, String testName) {
		try {
			Thread.sleep(1000);
			new File("reports/screenshots/").mkdirs();
			screenshotPicture = "screenshots/" + className + "-" + testName + "-screenshot.jpg";
	        BufferedImage image = new Robot().createScreenCapture(new Rectangle(
	                Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "jpg", new File("reports/" + screenshotPicture));
			testFail = false;
		} catch (Exception e) {
			System.out.println("Failure to try take screenshot: " + e);
		}
	}
}