package com.liferay.gs.testFramework;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumCommonMethods {

	private static String imagePath = null;

	private static Actions actionBuilder = new Actions(SeleniumReadPropertyKeys.DRIVER);

	private static final By addButtonLocator = By.xpath(
			".//*[@id='_com_liferay_product_navigation_control_menu_web_portlet_ProductNavigationControlMenuPortlet_addToggleId']");
	private static final By applicationHeadingLocator = By.xpath(
			".//*[@id='_com_liferay_product_navigation_control_menu_web_portlet_ProductNavigationControlMenuPortlet_addApplicationHeading']");
	private static final By searchApplicationLocator = By.xpath(
			".//*[@id='_com_liferay_product_navigation_control_menu_web_portlet_ProductNavigationControlMenuPortlet_searchApplication']");

	/*
	 * If the environment was localhost, the images will be generate in the
	 * localhost environment Else, the environment will be the Selenium Grid
	 * environment, in this case the image exist in another directory.
	 */
	public static void setPathToAttachFile(String imageExtension) {

		Date data = new Date();

		if (SeleniumReadPropertyKeys.getPlatformName().contains("default")) {
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
			String pathToSaveTheFile = SeleniumReadPropertyKeys.getAttachFilePath();
			int number = numberGenerator.nextInt(5) + 1;
			imagePath = pathToSaveTheFile + "0" + number + "." + imageExtension;
		}
	}

	public static String getPathOfImageFile() {
		if (SeleniumReadPropertyKeys.getPlatformName().contains("default")) {
			Path whereIam = Paths.get(ConfigurationOS.class.getResource("/").getFile());
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

		if (SeleniumReadPropertyKeys.getPlatformName().contains("default")) {
			Path whereIam = Paths.get(ConfigurationOS.class.getResource("/").getFile());
			Path rootRepository = whereIam.getParent();
			imagePath = rootRepository + "/reports/screenshots/";
			File folder = new File(imagePath);
			if (folder.exists() == true && folder.listFiles().length > 0) {
				File[] files = folder.listFiles();
				for (File file : files) {
					if (file.getName().startsWith("Screenshot_")) {
						file.delete();
					}
				}
			} else {
				// do nothing
			}
		} else {
			// do nothing
		}
	}

	/*
	 * Add the portlet on Screen, but to do this, the user should inform the
	 * portlet name and the column that the portlet will appear
	 */
	public static void addPortletOnScreen(String portletName, String column) {
		clickOnAddButton();
		clickOnApplicationCategory();
		searchForPortletByName(portletName);
		dragAndDropPortletToColumn(portletName, column);
	}

	/*
	 * Remove all portlets from the current screen.
	 */
	public static void removeAllPorlets() {
		List<WebElement> portlets = SeleniumReadPropertyKeys.DRIVER.findElements(By.cssSelector(".portlet-layout .portlet"));
		for (WebElement portlet : portlets) {
			openPortletActionDropDown(portlet);
			clickOnPortletConfigurationMenu("Remove");
			acceptBrowserDialog();
		}
	}

	/*
	 * Remove all specific portlets from the current screen, but to do this the
	 * user should inform any part of the portlet ID, that all portlets with
	 * this part of ID to be removed.
	 */
	public static void removeAllSpecificPorlets(String partOfPortletID) {
		List<WebElement> portlets = SeleniumReadPropertyKeys.DRIVER
				.findElements(By.xpath(".//*[contains(@id,'" + partOfPortletID + "')]"));
		for (WebElement portlet : portlets) {
			openPortletActionDropDown(portlet);
			clickOnPortletConfigurationMenu("Remove");
			acceptBrowserDialog();
		}
	}

	private static void dragAndDropPortletToColumn(String portletName, String column) {
		SeleniumWaitMethods.waitMediumTime();

		By searchApplicationResultLocator = By
				.xpath(".//*[@id='_com_liferay_product_navigation_control_menu_web_portlet_ProductNavigationControlMenuPortlet_portletCategory0']//*[contains (text(), '"
						+ portletName + "')]");

		By columnLocator = By.xpath(".//*[@id='" + column + "']");

		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(searchApplicationResultLocator));
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.elementToBeClickable(searchApplicationResultLocator));
		WebElement element = SeleniumReadPropertyKeys.DRIVER.findElement(searchApplicationResultLocator);
		WebElement target = SeleniumReadPropertyKeys.DRIVER.findElement(columnLocator);
		(new Actions(SeleniumReadPropertyKeys.DRIVER)).dragAndDrop(element, target).perform();
		SeleniumWaitMethods.waitMediumTime();
	}

	private static void searchForPortletByName(String portletName) {
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(searchApplicationLocator));
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.elementToBeClickable(searchApplicationLocator));
		SeleniumReadPropertyKeys.DRIVER.findElement(searchApplicationLocator).sendKeys(portletName);
	}

	private static void clickOnAddButton() {
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(addButtonLocator));
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.elementToBeClickable(addButtonLocator));
		SeleniumReadPropertyKeys.DRIVER.findElement(addButtonLocator).click();
	}

	private static void clickOnApplicationCategory() {
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(applicationHeadingLocator));
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.elementToBeClickable(applicationHeadingLocator));

		By portletContentCategories = By.cssSelector(".add-content-menu .lfr-content-category");
		boolean isApplicationCategoriesDisplayed = SeleniumReadPropertyKeys.DRIVER.findElement(portletContentCategories).isDisplayed();

		if (!isApplicationCategoriesDisplayed) {
			SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(applicationHeadingLocator));
			SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.elementToBeClickable(applicationHeadingLocator));
			SeleniumReadPropertyKeys.DRIVER.findElement(applicationHeadingLocator).click();
		}
	}

	private static void openPortletActionDropDown(WebElement portletElement) {
		WebElement configButton = portletElement.findElement(By.cssSelector(".lexicon-icon-ellipsis-v"));
		actionBuilder.moveToElement(configButton).pause(Duration.ofMillis(200)).perform();
		configButton.click();
	}

	private static void clickOnPortletConfigurationMenu(String title) {
		By dropDownMenu = By.cssSelector(".dropdown-menu");
		SeleniumWaitMethods.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(dropDownMenu));

		WebElement dropDownMenuElement = SeleniumReadPropertyKeys.DRIVER.findElement(dropDownMenu);

		List<WebElement> elements = dropDownMenuElement.findElements(By.cssSelector("li a.lfr-icon-item"));
		WebElement element = elements.stream().filter(el -> el.getText().trim().equals(title)).findFirst().get();
		element.click();
	}

	private static void acceptBrowserDialog() {
		Alert alert = SeleniumReadPropertyKeys.DRIVER.switchTo().alert();
		alert.accept();
	}
}