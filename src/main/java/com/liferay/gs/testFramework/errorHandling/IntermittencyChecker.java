package com.liferay.gs.testFramework.errorHandling;

import static com.liferay.gs.testFramework.errorHandling.AttachmentUploader.ATTACHMENTS_DIR;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.runner.Description;

import org.openqa.selenium.WebDriver;

/**
 * @author Italo Laino
 */
public class IntermittencyChecker {

	public static final String ARTIFACT_FILE_NAME_FORMAT = "Intermittency.#%s.%s.%s.%s";

	public void registerTestFailure(int attempts, Description description) {
		_cleanArtifacts(attempts, description);
	}

	public void registerTestSuccess(int attempts, Description description) {
		if (attempts > 1) {
			System.err.println(_getWarning(description));
		}
	}

	public void registerThrowable(WebDriver webDriver, int attempt, Description description, Throwable t) {
		_writeArtifact(webDriver, attempt, t, description);
	}

	private void _cleanArtifacts(int attempts, Description description) {
		for (int attempt = 1; attempt <= attempts; attempt++) {
			String txtFileName = _getTxtFileName(attempt, description);

			String screenshotFileName = _getScreenshotFileName(attempt, description);

			File txtFile = new File(ATTACHMENTS_DIR, txtFileName);

			txtFile.delete();

			File screenshotFile = new File(ATTACHMENTS_DIR, screenshotFileName);

			screenshotFile.delete();
		}
	}

	private String _getScreenshotFileName(int attempt, Description description) {
		return String.format(ARTIFACT_FILE_NAME_FORMAT, attempt, description.getClassName(), description.getMethodName(), "png");
	}

	private String _getTxtFileName(int attempt, Description description) {
		return String.format(ARTIFACT_FILE_NAME_FORMAT, attempt, description.getClassName(), description.getMethodName(), "txt");
	}

	private String _getWarning(Description description) {
		return description.getDisplayName() + ": INTERMITTENCY WARNING - Test has just passed but failed on previous tries!";
	}

	private void _writeArtifact(WebDriver webDriver, int attempt, Throwable t, Description description) {
		StringWriter sw = new StringWriter();

		t.printStackTrace(new PrintWriter(sw));

		String stackTrace = sw.toString();

		String content = _getWarning(description) + "\nStacktrace:\n" + stackTrace;

		String txtFileName = _getTxtFileName(attempt, description);

		_attachmentUploader.setData(content);
		_attachmentUploader.save(txtFileName);

		String screenshotFileName = _getScreenshotFileName(attempt, description);

		_screenshotTaker.take(webDriver);
		_screenshotTaker.save(screenshotFileName);
	}

	private AttachmentUploader _attachmentUploader = new AttachmentUploader();
	private ScreenshotTaker _screenshotTaker = new ScreenshotTaker();

}