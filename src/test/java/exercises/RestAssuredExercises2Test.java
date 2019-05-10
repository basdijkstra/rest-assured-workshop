package exercises;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tngtech.java.junit.dataprovider.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.*;
import org.junit.runner.RunWith;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class RestAssuredExercises2Test {

	private static RequestSpecification requestSpec;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().port(9876));

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
			setBaseUri("http://localhost").
			setPort(9876).
			build();
	}

	/*******************************************************
	 * Create a DataProvider with three test data rows:
	 * country code - zip code - state
	 * us           - 90210    - California
	 * us           - 12345    - New York
	 * ca           - Y1A      - Yukon
	 ******************************************************/



	/*******************************************************
	 * Request zip code data for given country / zip
	 * combinations by sending a GET to /<countryCode>/<zipCode>.
	 * Use the test data collection created
	 * above. Check that the state returned by the API
	 * matches the expected value. The
	 ******************************************************/

	@Test
	public void checkStateForCountryCodeAndZipCode() {

		given().
			spec(requestSpec).
		when().
		then();
	}
}