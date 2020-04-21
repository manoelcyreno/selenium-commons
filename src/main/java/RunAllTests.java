import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.liferay.gs.testFramework.core.ConcurrentSuite;
import com.liferay.gs.testFramework.driver.WebDriverManager;

@RunWith(ConcurrentSuite.class)
@Suite.SuiteClasses({ GoogleTest.class, GoogleTest2.class })
public class RunAllTests {

	@AfterClass
	public static void afterClass() {
		_webDriverManager.quitAll();
	}

	private static WebDriverManager _webDriverManager = new WebDriverManager();

}
