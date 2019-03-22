package com.liferay.gs.testFramework.errorHandling;

import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

/**
 * @author Italo Laino
 */
public class AttachmentUploader {

	public static final String ATTACHMENTS_DIR = "build/test-results/functionalTest/attachments/";

	public void save(String fileName) {
		if (_data == null) {
			return;
		}

		try {
			FileUtils.writeByteArrayToFile(new File(ATTACHMENTS_DIR, fileName), _data);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void setData(byte[] data) {
		_data = data;
	}

	public void setData(String data) {
		_data = data.getBytes(StandardCharsets.UTF_8);
	}

	private byte[] _data = null;

}