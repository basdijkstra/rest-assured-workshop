package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.junit.*;

public class RestAssuredAnswers3Test {

	private static RequestSpecification requestSpec;

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
			setBaseUri("http://localhost").
			setPort(9876).
			build();
	}
		
	/*******************************************************
	 * Create a static ResponseSpecification that checks whether:
	 * - the response has statusCode 200
	 * - the response contentType is JSON
	 * - the value of 'country' in the response body
	 *   is equal to 'United States'
	 ******************************************************/
	
	private static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void createResponseSpecification() {
		
		responseSpec = new ResponseSpecBuilder().
				expectStatusCode(200).
				expectContentType(ContentType.JSON).
				expectBody("country", equalTo("United States")).
				build();		
	}

	/*******************************************************
	 * Perform a GET request to /us/90210
	 * Use the previously created ResponseSpecification to
	 * execute the specified checks
	 * Additionally, check that 'country abbreviation' is
	 * equal to 'US'
	 ******************************************************/
	
	@Test
	public void useResponseSpecification() {
		
		given().
			spec(requestSpec).
		when().
			get("/us/90210").
		then().
			spec(responseSpec).
		and().
			body("'country abbreviation'",equalTo("US"));
	}

	/*******************************************************
	 * Perform a GET request to /us/90210
	 * Extract the value of the 'country' element in the
	 * response into a String variable actualCountry
	 * Use the given JUnit assertion to check on its length
	 ******************************************************/

	@Test
	public void extractCountryFromResponse() {

		String actualCountry =

		given().
			spec(requestSpec).
		when().
			get("/us/90210").
		then().
			extract().
			path("country");

		Assert.assertEquals("United States", actualCountry);
	}
}