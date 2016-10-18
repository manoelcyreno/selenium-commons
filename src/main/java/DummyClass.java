import com.liferay.gs.Configuration;
import com.liferay.gs.UtilsKeys;

public class DummyClass {

	public static void main(String[] args) {
		System.out.println("I'm here !");
		System.out.println(Configuration.getString("environment"));
	}
}
