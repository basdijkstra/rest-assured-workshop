package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.*;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.*;

@RunWith(DataProviderRunner.class)
public class RestAssuredAnswers2Test {

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
	 * Create a DataProvider that specifies in which country
	 * a specific circuit can be found (specify that Monza 
	 * is in Italy, for example) 
	 ******************************************************/
	
	@DataProvider
	public static Object[][] circuitData() {
		return new Object[][] {
				{ "monza", "Italy" },
				{ "spa", "Belgium" },
				{ "sepang", "Malaysia" }
		};
	}
	
	/*******************************************************
	 * Create a DataProvider that specifies for all races
	 * (adding the first four suffices) in 2015 how many  
	 * pit stops Max Verstappen made
	 * (race 1 = 1 pitstop, 2 = 3, 3 = 2, 4 = 2)
	 ******************************************************/
	
	@DataProvider
	public static Object[][] pitstopData() {
		return new Object[][] {
				{ "1", 1 },
				{ "2", 3 },
				{ "3", 2 },
				{ "4", 2 }
		};
	}
	
	/*******************************************************
	 * Request data for a specific circuit (for Monza this 
	 * is /circuits/monza.json)
	 * and check the country this circuit can be found in
	 ******************************************************/
	
	@Test
	@UseDataProvider("circuitData")
	public void checkCountryForCircuit(String circuitName, String circuitCountry) {
		
		given().
			spec(requestSpec).
		and().
			pathParam("circuitName", circuitName).
		when().
			get("/circuits/{circuitName}.json").
		then().
			assertThat().
			body("MRData.CircuitTable.Circuits.Location[0].country",equalTo(circuitCountry));
	}
	
	/*******************************************************
	 * Request the pitstop data for the first four races in
	 * 2015 for Max Verstappen (for race 1 this is
	 * /2015/1/drivers/max_verstappen/pitstops.json)
	 * and verify the number of pit stops made
	 ******************************************************/
	
	@Test
	@UseDataProvider("pitstopData")
	public void checkNumberOfPitstopsForMaxVerstappenIn2015(String raceNumber, int numberOfPitstops) {
		
		given().
			spec(requestSpec).
		and().
			pathParam("raceNumber", raceNumber).
		when().
			get("/2015/{raceNumber}/drivers/max_verstappen/pitstops.json").
		then().
			assertThat().
			body("MRData.RaceTable.Races[0].PitStops",hasSize(numberOfPitstops));
	}
}