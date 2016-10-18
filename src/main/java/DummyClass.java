import com.liferay.gs.Configuration;

public class DummyClass {

	public static void main(String[] args) {
		System.out.println("I'm here !");
		System.out.println(Configuration.getString("environment"));
		System.out.println(Configuration.getString("browser"));
	}
}
