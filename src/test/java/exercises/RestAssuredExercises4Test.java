package exercises;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredExercises4Test {

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
	 * Perform a GET request to /xml/de/24848 to get the
	 * list of places associated with German zip code 24848
	 * in XML format.
	 *
	 * Assert that the third place in the list is Kropp
	 *
	 * Use "response.places.place[2].placeName" as the GPath
	 * expression to extract the required value from the response
	 ******************************************************/

	@Test
	public void getDeZipCode24848_checkThirdPlaceInList_expectKropp() {

		given().
			spec(requestSpec).
		when().
		then();
	}

	/*******************************************************
	 * Perform a GET request to /xml/de/24848 to get the
	 * list of places associated with German zip code 24848
	 * in XML format.
	 *
	 * Assert that the latitude for the second
	 * place in the list is equal to 54.45
	 *
	 * Can you create the correct GPath expression yourself,
	 * using the examples as shown in the slides?
	 ******************************************************/

	@Test
	public void getDeZipCode24848_checkLatitudeForSecondPlaceInList_expect5445() {

		given().
			spec(requestSpec).
		when().
		then();
	}

	/*******************************************************
	 * Perform a GET request to /xml/de/24848 to get the
	 * list of places associated with German zip code 24848
	 * in XML format.
	 *
	 * Assert that there are 4 places that
	 * have a stateAbbreviation that equals 'SH'
	 *
	 * Can you create the correct GPath expression yourself,
	 * using the examples as shown in the slides?
	 ******************************************************/

	@Test
	public void getDeZipCode24848_checkNumberOfPlacesInSH_expect4() {

		given().
			spec(requestSpec).
		when().
		then();
	}


	/*******************************************************
	 * Perform a GET request to /xml/de/24848 to get the
	 * list of places associated with German zip code 24848
	 * in XML format.
	 *
	 * Assert that there are 2 places that have a name that
	 * starts with 'Klein'
	 *
	 * Can you create the correct GPath expression yourself,
	 * using the examples as shown in the slides?
	 ******************************************************/

	@Test
	public void getDeZipCode24848_checkNumberOfPlacesStartingWithKlein_expect2() {

		given().
			spec(requestSpec).
		when().
		then();
	}
}