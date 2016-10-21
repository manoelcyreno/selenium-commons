import com.liferay.gs.UtilsMethods;

public class DummyClass {

	public static void main(String[] args) {
		texts();
		takeScreenshot();
		System.out.println(UtilsMethods.getPathOfFileJPG());
		removeScreenshot();
	}

	private static void texts() {
		System.out.println("I'm here !");
	}

	private static void takeScreenshot() {
		UtilsMethods.setPathToAttachFileJPG();
	}

	private static void removeScreenshot() {
		UtilsMethods.removeScreenshots();
	}

}
