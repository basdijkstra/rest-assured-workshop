package exercises;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredExercises1Test {

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
		then();
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
		then();
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
		then();
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
		then();
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
		then();
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

		then();
	}
}