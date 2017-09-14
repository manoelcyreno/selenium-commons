import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.gs.testFramework.BaseTestCase;
import com.liferay.gs.testFramework.Selenium;
import com.liferay.gs.testFramework.SeleniumReadPropertyKeys;
import com.liferay.gs.testFramework.SeleniumWaitMethods;

public class GoogleTest extends BaseTestCase {

	private static By searchLocator = By.name("q");

	@Before
	public void setUp() {
		// UtilsKeys.DRIVER.get(UtilsKeys.getUrlToHome());
		SeleniumReadPropertyKeys.DRIVER.get("http://www.google.com");
		SeleniumReadPropertyKeys.DRIVER.manage().timeouts().implicitlyWait(SeleniumReadPropertyKeys.getTimeOut(), TimeUnit.SECONDS);
	}

	@After
	public void after() {
	}

	@AfterClass
	public static void tearDown() {
		Selenium.quit();
	}

	/*
	 * this test should fail purposely
	 */
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithWrongTitle() {
		SeleniumWaitMethods.findElementWithWaitDriver(searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		SeleniumReadPropertyKeys.DRIVER.findElement(searchLocator).clear();
		SeleniumReadPropertyKeys.DRIVER.findElement(searchLocator).sendKeys("seleniumhq");
		SeleniumReadPropertyKeys.DRIVER.findElement(searchLocator).sendKeys(Keys.ENTER);
		SeleniumWaitMethods.findElementWithWaitDriver(By.linkText("Selenium - Web Browser Automation"),
				ExpectedConditions::visibilityOfElementLocated, ExpectedConditions::elementToBeClickable);
		SeleniumReadPropertyKeys.DRIVER.findElement(By.linkText("Selenium - Web Browser Automation")).click();
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.titleContains("Automation"));
		assertEquals("Selenium - Web Browser Automation2", SeleniumReadPropertyKeys.DRIVER.getTitle());
	}

	/*
	 * this test should pass
	 */
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle() {
		SeleniumWaitMethods.findElementWithWaitDriver(searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		SeleniumReadPropertyKeys.DRIVER.findElement(searchLocator).clear();
		SeleniumReadPropertyKeys.DRIVER.findElement(searchLocator).sendKeys("seleniumhq");
		SeleniumReadPropertyKeys.DRIVER.findElement(searchLocator).sendKeys(Keys.ENTER);
		SeleniumWaitMethods.findElementWithWaitDriver(By.linkText("Selenium - Web Browser Automation"),
				ExpectedConditions::visibilityOfElementLocated, ExpectedConditions::elementToBeClickable);
		SeleniumReadPropertyKeys.DRIVER.findElement(By.linkText("Selenium - Web Browser Automation")).click();
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.titleContains("Automation"));
		assertEquals("Selenium - Web Browser Automation", SeleniumReadPropertyKeys.DRIVER.getTitle());
	}
}