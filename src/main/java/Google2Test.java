import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.selenium.commons.testFramework.FunctionalTest;
import com.selenium.commons.testFramework.core.SeleniumReadPropertyKeys;
import com.selenium.commons.testFramework.utils.SeleniumWaitMethods;

/* 
 * Running tests in parallel and with the fail retries is not possible by the bug
 * (https://github.com/artsok/rerunner-jupiter/issues/27)
 * 
 * To use the fail retries mode, change the CONCURRENT to SAME_THREAD,
 * and change the tests annotation to @RepeatedIfExceptionsTest(repeats = 2)
 */

@Execution(ExecutionMode.CONCURRENT)
public class Google2Test extends FunctionalTest {

	private static By searchLocator = By.name("q");

	@BeforeEach
	public void setUp() {
		getWebDriver().get(SeleniumReadPropertyKeys.getUrlToHome());
		getWebDriver().manage().timeouts().implicitlyWait(SeleniumReadPropertyKeys.getTimeOut(), TimeUnit.SECONDS);
	}
	
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitleClass2_1() {
		SeleniumWaitMethods.findElementWithWaitDriver(getWebDriver(), searchLocator,
				ExpectedConditions::visibilityOfElementLocated, ExpectedConditions::elementToBeClickable);

		getWebDriver().findElement(searchLocator).clear();
		getWebDriver().findElement(searchLocator).sendKeys("seleniumhq");
		getWebDriver().findElement(searchLocator).sendKeys(Keys.ENTER);

		assertTrue(getWebDriver().getTitle().contains("seleniumhq") && getWebDriver().getTitle().contains("Google"));
	}
}