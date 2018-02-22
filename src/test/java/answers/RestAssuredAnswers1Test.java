package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.*;
import org.junit.*;

public class RestAssuredAnswers1Test {

	private static RequestSpecification requestSpec;
	
	@BeforeClass
	public static void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder().
			setBaseUri("http://localhost").
			setPort(9876).
			setBasePath("/api/f1").
			build();
	}
	
	/*******************************************************
	 * Send a GET request to /2016/drivers.json
	 * and check that the response has HTTP status code 200
	 ******************************************************/
	
	@Test
	public void checkResponseCodeForCorrectRequest() {
				
		given().
			spec(requestSpec).
		when().
			get("/2016/drivers.json").
		then().
			assertThat().
			statusCode(200);
	}
	
	/*******************************************************
	 * Send a GET request to /incorrect.json
	 * and check that the answer has HTTP status code 404
	 ******************************************************/
	
	@Test
	public void checkResponseCodeForIncorrectRequest() {
				
		given().
			spec(requestSpec).
		when().
			get("/incorrect.json").
		then().
			assertThat().
			statusCode(404);
	}
	
	/*******************************************************
	 * Send a GET request to /2016/drivers.json
	 * and check that the response is in JSON format 
	 ******************************************************/
	
	@Test
	public void checkResponseContentTypeJson() {
				
		given().
			spec(requestSpec).
		when().
			get("/2016/drivers.json").
		then().
			assertThat().
			contentType("application/json");
	}
	
	/***********************************************
	 * Retrieve circuit information for the first race
	 * of the 2014 season and check the circuitId equals
	 * albert_park
	 * Use /2014/1/circuits.json
	 **********************************************/
	
	@Test
	public void checkTheFirstRaceOf2014WasAtAlbertPark() {
				
		given().
			spec(requestSpec).
		when().
			get("/2014/1/circuits.json").
		then().
			assertThat().
			body("MRData.CircuitTable.Circuits.circuitId[0]", equalTo("albert_park"));
	}
	
	/***********************************************
	 * Retrieve the list of circuits for the 2014
	 * season and check that it contains silverstone
	 * Use /2014/circuits.json
	 **********************************************/
	
	@Test
	public void checkThereWasARaceAtSilverstoneIn2014() {
		
		given().
			spec(requestSpec).
		when().
			get("/2014/circuits.json").
		then().
			assertThat().
			body("MRData.CircuitTable.Circuits.circuitId", hasItem("silverstone"));
	}
	
	/***********************************************
	 * Retrieve the list of circuits for the 2014
	 * season and check that it does not contain
	 * nurburgring
	 * USe /2014/circuits.json
	 **********************************************/
	
	@Test
	public void checkThereWasNoRaceAtNurburgringIn2014() {
		
		given().
			spec(requestSpec).
		when().
			get("/2014/circuits.json").
		then().
			assertThat().
			body("MRData.CircuitTable.Circuits.circuitId", not(hasItem("nurburgring")));
	}
}