package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

public class RestAssuredAnswers4Test {

	private static RequestSpecification requestSpec;

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
			get("/xml/de/24848").
		then().
			assertThat().
			body("response.places.place[2].placeName", equalTo("Kropp"));
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
			get("/xml/de/24848").
		then().
			assertThat().
			body("response.places.place[1].@latitude", equalTo("54.45"));
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
			get("/xml/de/24848").
		then().
			assertThat().
			body("response.places.place.findAll{it.stateAbbreviation=='SH'}", hasSize(4));
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
			get("/xml/de/24848").
		then().
			assertThat().
			body("response.places.place.placeName.grep(~/Klein.*/)", hasSize(2));
	}
}