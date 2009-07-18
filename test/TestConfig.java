import com.alexbleasdale.bean.Credentials;

public class TestConfig {

	Credentials c;

	public TestConfig() {
		c = new Credentials();

	}

	public Credentials getExistDbCredentials() {
		return c;
	}
}
