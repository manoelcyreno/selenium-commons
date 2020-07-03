package com.selenium.commons.testFramework.utils;

import static com.selenium.commons.testFramework.utils.SeleniumWaitMethods.getWaitDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.selenium.commons.testFramework.FunctionalTest;

/**
 * @author Manoel Cyreno
 */
public class SeleniumCommonMethods extends FunctionalTest{

	public void acceptBrowserDialog() {
		Alert alert = getWebDriver().switchTo().alert();
		alert.accept();
	}

	public void waitElementBeVisible(By locator) {
		getWaitDriver(getWebDriver()).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitElementBePresent(By locator) {
		getWaitDriver(getWebDriver()).until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitElementBeClickable(By locator) {
		getWaitDriver(getWebDriver()).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickOnElement(By locator) {
		waitElementBePresent(locator);
		waitElementBeVisible(locator);
		waitElementBeClickable(locator);
		getWebDriver().findElement(locator).click();

	}

	public void clearAndWriteOnElement(By locator, CharSequence... keysToSend) {
		waitElementBePresent(locator);
		waitElementBeVisible(locator);
		getWebDriver().findElement(locator).clear();
		getWebDriver().findElement(locator).sendKeys(keysToSend);
	}
	
	public void writeOnElement(By locator, CharSequence... keysToSend) {
		waitElementBePresent(locator);
		waitElementBeVisible(locator);
		getWebDriver().findElement(locator).sendKeys(keysToSend);
	}

}