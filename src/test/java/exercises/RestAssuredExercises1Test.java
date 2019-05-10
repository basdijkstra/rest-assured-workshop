package exercises;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredExercises1Test {

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
	 * Send a GET request to /us/90210
	 * and check that the response has HTTP status code 200
	 ******************************************************/

	@Test
	public void requestUsZipCode90210_checkResponseCode_expect200() {

		given().
			spec(requestSpec).
			when().
			then();
	}

	/*******************************************************
	 * Send a GET request to /us/99999
	 * and check that the answer has HTTP status code 404
	 ******************************************************/

	@Test
	public void requestUsZipCode99999_checkResponseCode_expect404() {

		given().
			spec(requestSpec).
			when().
			then();
	}

	/*******************************************************
	 * Send a GET request to /us/90210
	 * and check that the response is in JSON format
	 ******************************************************/

	@Test
	public void requestUsZipCode90210_checkContentType_expectApplicationJson() {

		given().
			spec(requestSpec).
			when().
			then();
	}

	/***********************************************
	 * Send a GET request to /us/90210 and check
	 * that the state associated with the first place
	 * in the list returned is equal to 'California'
	 **********************************************/

	@Test
	public void requestUsZipCode90210_checkStateForFirstPlace_expectCalifornia() {

		given().
			spec(requestSpec).
			when().
			then();
	}

	/***********************************************
	 * Send a GET request to /de/24848 and check that
	 * the list of place names returned contains the
	 * value 'Kropp'
	 **********************************************/

	@Test
	public void requestDeZipCode24848_checkListOfPlaceNames_expectContainsKropp() {

		given().
			spec(requestSpec).
			when().
			then();
	}

	/***********************************************
	 * Send a GET request to /de/24848 and check that
	 * the list of place names returned does not
	 * contain the value 'Frankfurt'
	 **********************************************/

	@Test
	public void requestDeZipCode24848_checkListOfPlaceNames_expectDoesNotContainFrankfurt() {

		given().
			spec(requestSpec).
			when().
			then();
	}

	/***********************************************
	 * Send a GET request to /de/24848 and check that
	 * the list of place names returned is a
	 * collection of size 4
	 **********************************************/

	@Test
	public void requestDeZipCode24848_checkNumberOfPlaceNames_expect4() {

		given().
			spec(requestSpec).
			when().
			then();
	}
}