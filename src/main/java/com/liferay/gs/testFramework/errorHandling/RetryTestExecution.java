package com.liferay.gs.testFramework.errorHandling;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import org.openqa.selenium.WebDriver;

/**
 * @author Joao Louro
 * @author Italo Laino
 */
public class RetryTestExecution implements TestRule {

	public RetryTestExecution(WebDriver webDriver, int retryCount) {
		_webDriver = webDriver;
		_retryCount = retryCount;
	}

	public Statement apply(Statement base, Description description) {
		return _statement(base, description);
	}

	private Statement _statement(final Statement base, final Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				Throwable caughtThrowable = null;

				for (int attempt = 1; attempt <= _retryCount; attempt++) {
					try {
						base.evaluate();

						_intermittencyChecker.registerTestSuccess(attempt, description);

						return;
					}
					catch (Throwable t) {
						_intermittencyChecker.registerThrowable(_webDriver, attempt, description, t);

						caughtThrowable = t;

						System.err.println(description.getDisplayName() + ": run " + attempt + " failed");
						System.err.println(description.getDisplayName() + ": stacktrace");

						caughtThrowable.printStackTrace();
					}
				}

				_intermittencyChecker.registerTestFailure(_retryCount, description);

				System.err.println(description.getDisplayName() + ": giving up after " + _retryCount + " failures");

				throw caughtThrowable;
			}

		};
	}

	private final IntermittencyChecker _intermittencyChecker = new IntermittencyChecker();
	private final int _retryCount;
	private final WebDriver _webDriver;

}