package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.junit.*;

public class RestAssuredAnswers1Test {

	private static RequestSpecification requestSpec;
	
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
			get("/us/90210").
		then().
			assertThat().
			statusCode(200);
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
			get("/us/99999").
		then().
			assertThat().
			statusCode(404);
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
			get("/us/90210").
		then().
			assertThat().
			contentType(ContentType.JSON);
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
			get("/us/90210").
		then().
			assertThat().
			body("places[0].state", equalTo("California"));
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
			get("/de/24848").
		then().
			assertThat().
			body("places.'place name'", hasItem("Kropp"));
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
			get("/de/24848").
		then().
			assertThat().
			body("places.'place name'", not(hasItem("Frankfurt")));
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
			get("/de/24848").
		then().
			assertThat().
			body("places.'place name'", hasSize(4));
	}
}