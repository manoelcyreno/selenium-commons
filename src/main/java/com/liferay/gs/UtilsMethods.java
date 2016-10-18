package com.liferay.gs;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jna.platform.FileUtils;

public class UtilsMethods {

	private static String imagePath = null;

	/*
	 * If the environment was localhost, the images will be generate in the
	 * localhost environment Else, the environment will be the Selenium Grid
	 * environment, in this case the image exist in another directory.
	 */
	// public static void takeScreenshot() {
	//
	// Date data = new Date();
	//
	// if (UtilsKeys.getPlatformName().equalsIgnoreCase("default")) {
	// File scrFile = ((TakesScreenshot)
	// Selenium.getDriver()).getScreenshotAs(OutputType.FILE);
	// Path whereIam = Paths.get(Property.class.getResource("/").getFile());
	// Path rootRepository =
	// whereIam.getParent().getParent().getParent().getParent().getParent();
	// Path pathToSaveTheFile = rootRepository.getParent();
	// try {
	// imagePath = pathToSaveTheFile + "/screenshots/Screenshot_" +
	// data.getTime() + ".png";
	// FileUtils.copyFile(scrFile, new File(imagePath), true);
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	// } else {
	// Random numberGenerator = new Random();
	// String pathToSaveTheFile = "C:\\\\screenshots\\";
	// int number = numberGenerator.nextInt(5) + 1;
	// imagePath = pathToSaveTheFile + "0" + number + ".jpg";
	// }
	// }
	//
	// public static String getImagePath() {
	//
	// return imagePath;
	// }
	//
	// /*
	// * If. the environment was localhost, the images will be removed in the
	// * localhost environment Else, the environment will be the Selenium Grid
	// * environment, in this case the image exist in another directory.
	// */
	// public static void removeScreenshots() {
	//
	// if (UtilsKeys.getPlatformName().equalsIgnoreCase("default")) {
	// Path whereIam = Paths.get(Property.class.getResource("/").getFile());
	// Path rootRepository =
	// whereIam.getParent().getParent().getParent().getParent().getParent();
	// Path pathToSaveTheFile = rootRepository.getParent();
	// imagePath = pathToSaveTheFile + "/screenshots/";
	// File folder = new File(imagePath);
	// File[] files = folder.listFiles();
	// for (File file : files) {
	// if (file.getName().startsWith("SIGEX_Screenshot_")) {
	// file.delete();
	// }
	// }
	// } else {
	// // do nothing
	// }
	// }

	public static String getMarionetteDriverPath() {
		Path whereIam = Paths.get(Configuration.class.getResource("/").getFile());
		Path rootRepository = whereIam.getParent();
		String marionetteDriverPath = rootRepository
				+ "/src/main/executables/geckodriver";
		return marionetteDriverPath;
	}
}