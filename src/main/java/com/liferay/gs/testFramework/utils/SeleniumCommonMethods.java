package com.liferay.gs.testFramework.utils;

import static com.liferay.gs.testFramework.utils.SeleniumWaitMethods.getWaitDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Manoel Cyreno
 */
public class SeleniumCommonMethods {

	public SeleniumCommonMethods(WebDriver webDriver) {
		_webDriver = webDriver;
	}

	private WebDriver _webDriver;

	public void acceptBrowserDialog() {
		Alert alert = _webDriver.switchTo().alert();
		alert.accept();
	}

	public void waitElementBeVisible(By locator) {
		getWaitDriver(_webDriver).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitElementBePresent(By locator) {
		getWaitDriver(_webDriver).until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitElementBeClickable(By locator) {
		getWaitDriver(_webDriver).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickOnElement(By locator) {
		waitElementBePresent(locator);
		waitElementBeVisible(locator);
		waitElementBeClickable(locator);
		_webDriver.findElement(locator).click();

	}

	public void clearAndWriteOnElement(By locator, CharSequence... keysToSend) {
		waitElementBePresent(locator);
		waitElementBeVisible(locator);
		_webDriver.findElement(locator).clear();
		_webDriver.findElement(locator).sendKeys(keysToSend);
	}
	
	public void writeOnElement(By locator, CharSequence... keysToSend) {
		waitElementBePresent(locator);
		waitElementBeVisible(locator);
		_webDriver.findElement(locator).sendKeys(keysToSend);
	}

}