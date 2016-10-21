package com.liferay.gs.testFramework;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

public class UtilsMethods {

	private static String imagePath = null;

	/*
	 * If the environment was localhost, the images will be generate in the
	 * localhost environment Else, the environment will be the Selenium Grid
	 * environment, in this case the image exist in another directory.
	 */
	public static void setPathToAttachFile(String imageExtension) {

		Date data = new Date();

		if (UtilsKeys.getPlatformName().equalsIgnoreCase("default")) {
			try {
				Thread.sleep(1000);
				new File("reports/screenshots/").mkdirs();
				imagePath = "Screenshot_" + data.getTime() + "-screenshot." + imageExtension;
				BufferedImage image = new Robot()
						.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				ImageIO.write(image, imageExtension, new File("reports/screenshots/" + imagePath));
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			Random numberGenerator = new Random();
			String pathToSaveTheFile = UtilsKeys.getAttachFilePath();
			int number = numberGenerator.nextInt(5) + 1;
			imagePath = pathToSaveTheFile + "0" + number + "." + imageExtension;
		}
	}

	public static String getPathOfImageFile() {
		if (UtilsKeys.getPlatformName().equalsIgnoreCase("default")) {
			Path whereIam = Paths.get(Configuration.class.getResource("/").getFile());
			Path rootRepository = whereIam.getParent();
			return rootRepository + "/reports/screenshots/" + imagePath;
		} else {
			return imagePath;
		}
	}

	/*
	 * If. the environment was localhost, the images will be removed in the
	 * localhost environment Else, the environment will be the Selenium Grid
	 * environment, in this case the image exist in another directory.
	 */
	public static void removeScreenshots() {

		if (UtilsKeys.getPlatformName().equalsIgnoreCase("default")) {
			Path whereIam = Paths.get(Configuration.class.getResource("/").getFile());
			Path rootRepository = whereIam.getParent();
			imagePath = rootRepository + "/reports/screenshots/";
			File folder = new File(imagePath);
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.getName().startsWith("Screenshot_")) {
					file.delete();
				}
			}
		} else {
			// do nothing
		}
	}
}