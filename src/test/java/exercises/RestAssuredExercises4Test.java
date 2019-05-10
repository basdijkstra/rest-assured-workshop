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
	 * in XML format. Assert that the third place in the list
	 * is Kropp
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
	 * in XML format. Assert that the latitude for the third
	 * place in the list equal to 54.45
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
	 * in XML format. Assert that there are 4 places that
	 * have a stateAbbreviation that equals 'SH'
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
	 * in XML format. Assert that there are 3 places that
	 * have a name that starts with 'Klein'
	 ******************************************************/

	@Test
	public void getDeZipCode24848_checkNumberOfPlacesStartingWithKlein_expect3() {

		given().
			spec(requestSpec).
		when().
		then();
	}
}