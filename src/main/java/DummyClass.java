import com.liferay.gs.testFramework.SeleniumCommonMethods;

public class DummyClass {

	public static void main(String[] args) {
		texts();
		takeScreenshot();
		removeScreenshot();
	}

	private static void texts() {
		System.out.println("I'm here !");
	}

	private static void takeScreenshot() {
		SeleniumCommonMethods.setPathToAttachFile("png");
		System.out.println(SeleniumCommonMethods.getPathOfImageFile());
		SeleniumCommonMethods.setPathToAttachFile("jpeg");
		System.out.println(SeleniumCommonMethods.getPathOfImageFile());
		SeleniumCommonMethods.setPathToAttachFile("jpg");
		System.out.println(SeleniumCommonMethods.getPathOfImageFile());
		SeleniumCommonMethods.setPathToAttachFile("gif");
		System.out.println(SeleniumCommonMethods.getPathOfImageFile());
	}

	private static void removeScreenshot() {
		SeleniumCommonMethods.removeScreenshots();
	}

}
