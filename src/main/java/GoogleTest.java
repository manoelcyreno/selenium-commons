import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.gs.BaseTestCase;
import com.liferay.gs.Selenium;
import com.liferay.gs.UtilsKeys;
import com.liferay.gs.WaitUtils;

public class GoogleTest extends BaseTestCase {

	private static By searchLocator = By.name("q");

	@Before
	public void setUp() {
		UtilsKeys.DRIVER.get(UtilsKeys.getUrlToHome());
		UtilsKeys.DRIVER.manage().timeouts().implicitlyWait(UtilsKeys.getTimeOut(), TimeUnit.SECONDS);
	}

	@After
	public void after() {
		Selenium.quit();
	}

	@Test
	public void goToGooglePageAndAccessTheFirstResult() {
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
