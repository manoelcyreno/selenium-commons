import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.selenium.commons.testFramework.FunctionalTest;
import com.selenium.commons.testFramework.core.SeleniumReadPropertyKeys;
import com.selenium.commons.testFramework.utils.SeleniumCommonMethods;

/* 
 * Running tests in parallel and with the fail retries is not possible by the bug
 * (https://github.com/artsok/rerunner-jupiter/issues/27)
 * 
 * To use the fail retries mode, change the CONCURRENT to SAME_THREAD,
 * and change the tests annotation to @RepeatedIfExceptionsTest(repeats = 2)
 */

@RunWith(JUnitPlatform.class)
@Execution(ExecutionMode.CONCURRENT)
public class GoogleTest extends FunctionalTest {

	private static By searchLocator = By.name("q");

	private SeleniumCommonMethods scm = new SeleniumCommonMethods();

	@BeforeEach
	public void setUp() {
		getWebDriver().get(SeleniumReadPropertyKeys.getUrlToHome());
		getWebDriver().manage().timeouts().implicitlyWait(SeleniumReadPropertyKeys.getTimeOut(), TimeUnit.SECONDS);
	}

	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitleClass1() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);

		assertTrue(getWebDriver().getTitle().contains("seleniumhq") && getWebDriver().getTitle().contains("Google"));
	}

	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitleClass2() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq22222");
		scm.writeOnElement(searchLocator, Keys.ENTER);

		assertTrue(getWebDriver().getTitle().contains("seleniumhq") && getWebDriver().getTitle().contains("Google"));
	}
}