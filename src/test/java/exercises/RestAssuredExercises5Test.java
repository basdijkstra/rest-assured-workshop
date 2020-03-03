package exercises;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import dataentities.Car;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

public class RestAssuredExercises5Test {

	private static RequestSpecification requestSpec;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().port(9876));

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
			setBaseUri("http://localhost").
			setPort(9876).
			setContentType(ContentType.JSON).
			build();
	}

	/*******************************************************
	 * Create a new Car object that represents a 2012 Ford Focus
	 * POST this object to /car/postcar
	 * Verify that the response HTTP status code is equal to 200
	 ******************************************************/

	@Test
	public void postCarObject_checkResponseHttpStatusCode_expect200() {

		// Create an instance of the Car object first using


		given().
			spec(requestSpec).
		when().
		then();
	}

	/*******************************************************
	 * Perform a GET to /car/getcar/alfaromeogiulia
	 * Store the response in a Car object
	 * Verify, using that object, that the model year = 2016
	 * Use the standard Assert.assertEquals(expected,actual)
	 * as provided by JUnit for the assertion
	 ******************************************************/

	@Test
	public void getCarObject_checkModelYear_expect2016() {

		// Deserialize the response to a car object first
		// Use Car myCar = given(). ...

		given().
			spec(requestSpec).
			when();

		// Then, write a JUnit assertion to verify the modelYear
		// using Assert.assertEquals(<expected>, <actual>)
	}
}