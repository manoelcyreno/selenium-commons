import com.liferay.gs.UtilsMethods;

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
		UtilsMethods.setPathToAttachFile("png");
		System.out.println(UtilsMethods.getPathOfImageFile());
		UtilsMethods.setPathToAttachFile("jpeg");
		System.out.println(UtilsMethods.getPathOfImageFile());
		UtilsMethods.setPathToAttachFile("jpg");
		System.out.println(UtilsMethods.getPathOfImageFile());
		UtilsMethods.setPathToAttachFile("gif");
		System.out.println(UtilsMethods.getPathOfImageFile());
	}

	private static void removeScreenshot() {
		UtilsMethods.removeScreenshots();
	}

}
