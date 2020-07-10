package com.selenium.commons.testFramework.utils;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.commons.testFramework.core.SeleniumReadPropertyKeys;

/**
 * @author Miguel Angelo
 * @author Italo Laino
 * @author Manoel Cyreno
 */
public class SeleniumWaitMethods {

	@SafeVarargs
	public static <R> WebElement findElementWithFluentWait(WebDriver webDriver, By elementLocator,
			Function<By, ExpectedCondition<R>>... expectedConditions) {

		_applyWaits(elementLocator, getFluentWait(webDriver), expectedConditions);

		WebElement element = webDriver.findElement(elementLocator);

		return element;
	}

	@SafeVarargs
	public static <R> WebElement findElementWithWaitDriver(WebDriver webDriver, By elementLocator,
			Function<By, ExpectedCondition<R>>... expectedConditions) {

		WebDriverWait waitDriver = new WebDriverWait(webDriver, Duration.ofSeconds(SeleniumReadPropertyKeys.getTimeOut()));

		_applyWaits(elementLocator, waitDriver, expectedConditions);

		WebElement element = webDriver.findElement(elementLocator);

		return element;
	}

	public static Wait<WebDriver> getFluentWait(WebDriver webDriver) {
		return new FluentWait<>(webDriver).withTimeout(Duration.ofSeconds(SeleniumReadPropertyKeys.getTimeOut()))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
	}

	public static WebDriverWait getWaitDriver(WebDriver webDriver) {
		return new WebDriverWait(webDriver, Duration.ofSeconds(SeleniumReadPropertyKeys.getTimeOut()));
	}

	public static void waitLongTime() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			// Do nothing

		}
	}

	public static void waitMediumTime() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			// Do nothing

		}
	}

	public static void waitShortTime() {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {

			// Do nothing

		}
	}

	public static void waitTime(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException ie) {

			// Do nothing

		}
	}

	public static void waitVeryLongTime() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {

			// Do nothing

		}
	}

	private static <R> void _applyWaits(By elementLocator, Wait<WebDriver> wait,
			Function<By, ExpectedCondition<R>>[] expectedConditions) {

		for (Function<By, ExpectedCondition<R>> condition : expectedConditions) {
			wait.until(condition.apply(elementLocator));
		}
	}

}