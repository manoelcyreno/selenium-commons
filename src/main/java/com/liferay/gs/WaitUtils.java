package com.liferay.gs;

import java.util.concurrent.TimeUnit;
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

public class WaitUtils {

	private static WebDriverWait waitDriver = null;
	private static Wait<WebDriver> waitFluent = null;

	public static void waitShortTime() {
		try {
			Thread.sleep(400);
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

	public static void waitLongTime() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// Do nothing
		}
	}

	@SafeVarargs
	public static <R> WebElement findElementWithWaitDriver(By elementLocator,
			Function<By, ExpectedCondition<R>>... expectedConditions) {

		applyWaits(elementLocator, getWaitDriver(), expectedConditions);

		WebElement element = Selenium.getDriver().findElement(elementLocator);

		return element;
	}

	private static <R> void applyWaits(By elementLocator, Wait<WebDriver> wait,
			Function<By, ExpectedCondition<R>>[] expectedConditions) {

		for (Function<By, ExpectedCondition<R>> condition : expectedConditions) {
			wait.until(condition.apply(elementLocator));
		}
	}

	@SafeVarargs
	public static <R> WebElement findElementWithFluentWait(By elementLocator,
			Function<By, ExpectedCondition<R>>... expectedConditions) {

		applyWaits(elementLocator, getFluentWait(), expectedConditions);

		WebElement element = Selenium.getDriver().findElement(elementLocator);

		return element;
	}

	public static WebDriverWait getWaitDriver() {
		if (waitDriver == null) {
			waitDriver = new WebDriverWait(Selenium.getDriver(), UtilsKeys.getTimeOut());
		}
		return waitDriver;
	}

	public static Wait<WebDriver> getFluentWait() {
		if (waitFluent == null) {
			waitFluent = new FluentWait<>(Selenium.getDriver()).withTimeout(UtilsKeys.getTimeOut(), TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class);
		}
		return waitFluent;
	}

}
