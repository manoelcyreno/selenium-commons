import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
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
		// SeleniumReadPropertyKeys.DRIVER.get(SeleniumReadPropertyKeys.getUrlToHome());
		getWebDriver().get("http://www.google.com");
		getWebDriver().manage().timeouts().implicitlyWait(SeleniumReadPropertyKeys.getTimeOut(), TimeUnit.SECONDS);
	}

	/*
	 * this test should fail purposely
	 */
	@Ignore
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithWrongTitle() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq2", getWebDriver().getTitle());
	}

	/*
	 * this test should pass
	 */
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle1() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle2() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}

	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle3() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle4() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle5() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle6() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle7() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle8() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
	@Test
	public void goToGooglePageAndAccessTheFirstResultAndCompareTheTitleWithRightTitle9() {
		scm.clearAndWriteOnElement(searchLocator, "seleniumhq");
		scm.writeOnElement(searchLocator, Keys.ENTER);
		assertEquals("seleniumhq - Pesquisa Google", getWebDriver().getTitle());
	}
}