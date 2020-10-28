package answers;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import dataentities.Car;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

public class RestAssuredAnswers5 {

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
	 * by passing these values to the POJO constructor
	 *
	 * POST this object to /car/postcar
	 *
	 * Verify that the response HTTP status code is equal to 200
	 * (note that this will only work if you use these exact values!)
	 ******************************************************/
	
	@Test
	public void postCarObject_checkResponseHttpStatusCode_expect200() {

		Car myCar = new Car("Ford", "Focus", 2012);

		given().
			spec(requestSpec).
		and().
			body(myCar).
		when().
			post("/car/postcar").
		then().
			assertThat().
			statusCode(200);
	}

	/*******************************************************
	 * Perform a GET to /car/getcar/alfaromeogiulia
	 *
	 * Store the response in a Car object using deserialization
	 *
	 * Verify, using that object, that the model year = 2016
	 *
	 * Use the standard Assert.assertEquals(expected,actual)
	 * as provided by JUnit for the assertion, and the
	 * getModelYear() method to retrieve the actual model year
	 ******************************************************/

	@Test
	public void getCarObject_checkModelYear_expect2016() {

		Car myCar = given().
			spec(requestSpec).
			when().
			get("/car/getcar/alfaromeogiulia").
			as(Car.class);

		Assert.assertEquals(2016, myCar.getModelYear());
	}
}