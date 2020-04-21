import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.gs.testFramework.FunctionalTest;
import com.liferay.gs.testFramework.core.SeleniumReadPropertyKeys;
import com.liferay.gs.testFramework.utils.SeleniumWaitMethods;

public class GoogleTest2 extends FunctionalTest {

	private static By searchLocator = By.name("q");

	@Before
	public void setUp() {
		getWebDriver().get(SeleniumReadPropertyKeys.getUrlToHome());
		getWebDriver().manage().timeouts().implicitlyWait(SeleniumReadPropertyKeys.getTimeOut(), TimeUnit.SECONDS);
	}

	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitleClass2() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator,
				ExpectedConditions::visibilityOfElementLocated, ExpectedConditions::elementToBeClickable);

		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);

		assertTrue(getWebDriver().getTitle().contains("seleniumhq") && getWebDriver().getTitle().contains("Google"));
	}
}