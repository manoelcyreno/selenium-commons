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
import com.liferay.gs.testFramework.UtilsKeys;
import com.liferay.gs.testFramework.WaitUtils;

public class GoogleTest extends BaseTestCase {

	private static By searchLocator = By.name("q");

	@Before
	public void setUp() {
		// UtilsKeys.DRIVER.get(UtilsKeys.getUrlToHome());
		UtilsKeys.DRIVER.get("http://www.google.com");
		UtilsKeys.DRIVER.manage().timeouts().implicitlyWait(UtilsKeys.getTimeOut(), TimeUnit.SECONDS);
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
		WaitUtils.findElementWithWaitDriver(searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		UtilsKeys.DRIVER.findElement(searchLocator).clear();
		UtilsKeys.DRIVER.findElement(searchLocator).sendKeys("seleniumhq");
		UtilsKeys.DRIVER.findElement(searchLocator).sendKeys(Keys.ENTER);
		WaitUtils.findElementWithWaitDriver(By.linkText("Selenium - Web Browser Automation"),
				ExpectedConditions::visibilityOfElementLocated, ExpectedConditions::elementToBeClickable);
		UtilsKeys.DRIVER.findElement(By.linkText("Selenium - Web Browser Automation")).click();
		WaitUtils.getWaitDriver().until(ExpectedConditions.titleContains("Automation"));
		assertEquals("Selenium - Web Browser Automation2", UtilsKeys.DRIVER.getTitle());
	}

	/*
	 * this test should pass
	 */
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle() {
		WaitUtils.findElementWithWaitDriver(searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		UtilsKeys.DRIVER.findElement(searchLocator).clear();
		UtilsKeys.DRIVER.findElement(searchLocator).sendKeys("seleniumhq");
		UtilsKeys.DRIVER.findElement(searchLocator).sendKeys(Keys.ENTER);
		WaitUtils.findElementWithWaitDriver(By.linkText("Selenium - Web Browser Automation"),
				ExpectedConditions::visibilityOfElementLocated, ExpectedConditions::elementToBeClickable);
		UtilsKeys.DRIVER.findElement(By.linkText("Selenium - Web Browser Automation")).click();
		WaitUtils.getWaitDriver().until(ExpectedConditions.titleContains("Automation"));
		assertEquals("Selenium - Web Browser Automation", UtilsKeys.DRIVER.getTitle());
	}
}