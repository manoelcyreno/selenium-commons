import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.liferay.gs.testFramework.FunctionalTest;
import com.liferay.gs.testFramework.core.SeleniumReadPropertyKeys;
import com.liferay.gs.testFramework.utils.SeleniumCommonMethods;

public class GoogleTest extends FunctionalTest {

	private static By searchLocator = By.name("q");

	private SeleniumCommonMethods scm = new SeleniumCommonMethods(getWebDriver());

	@Before
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
}