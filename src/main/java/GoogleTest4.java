import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.liferay.gs.testFramework.FunctionalTest;
import com.liferay.gs.testFramework.core.SeleniumReadPropertyKeys;
import com.liferay.gs.testFramework.utils.SeleniumWaitMethods;

public class GoogleTest4 extends FunctionalTest {

	private static By searchLocator = By.name("q");

	@Before
	public void setUp() {
		// SeleniumReadPropertyKeys.DRIVER.get(SeleniumReadPropertyKeys.getUrlToHome());
		getWebDriver().get("http://www.google.com");
		getWebDriver().manage().timeouts().implicitlyWait(SeleniumReadPropertyKeys.getTimeOut(), TimeUnit.SECONDS);
	}

	/*
	 * this test should fail purposely
	 */
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithWrongTitle() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq2", getWebDriver().getTitle());
	}

	/*
	 * this test should pass
	 */
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle1() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle2() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}

	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle3() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle4() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle5() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle6() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle7() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle8() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle9() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator, ExpectedConditions::visibilityOfElementLocated,
				ExpectedConditions::elementToBeClickable);
		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
}