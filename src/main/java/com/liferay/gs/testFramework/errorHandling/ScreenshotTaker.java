package com.liferay.gs.testFramework.errorHandling;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author Italo Laino
 */
public class ScreenshotTaker extends AttachmentUploader {

	public void take(WebDriver webDriver) {
		if (webDriver instanceof TakesScreenshot) {
			byte[] data = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES);

			setData(data);
		}
	}

}