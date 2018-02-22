package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

public class RestAssuredAnswers5Test {

	private static RequestSpecification requestSpec;

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
			setBaseUri("http://localhost").
			setPort(9876).
			build();
	}
		
	/*******************************************************
	 * Get the list of speed records set by street legal cars
	 * use /xml/speedrecords
	 * Check that the third speed record in the list was set
	 * in 1955
	 ******************************************************/
	
	@Test
	public void checkThirdSpeedRecordWasSetIn1955() {
		
		given().
			spec(requestSpec).
		when().
			get("/xml/speedrecords").
		then().
			assertThat().
			body("speedRecords.car[2].year", equalTo("1955"));
	}
	
	/*******************************************************
	 * Get the list of speed records set by street legal cars
	 * use /xml/speedrecords
	 * Check that the fourth speed record in the list was set
	 * by an Aston Martin
	 ******************************************************/
	
	@Test
	public void checkFourthSpeedRecordWasSetbyAnAstonMartin() {
		
		given().
			spec(requestSpec).
		when().
			get("/xml/speedrecords").
		then().
			assertThat().
			body("speedRecords.car[3].@make", equalTo("Aston Martin"));
	}
	
	/*******************************************************
	 * Get the list of speed records set by street legal cars
	 * use /xml/speedrecords
	 * Check that three speed records have been set by cars
	 * from the UK
	 ******************************************************/
	
	@Test
	public void checkThreeRecordsHaveBeenSetByCarsFromTheUK() {
		
		given().
			spec(requestSpec).
		when().
			get("/xml/speedrecords").
		then().
			assertThat().
			body("speedRecords.car.findAll{it.@country=='UK'}.size()", equalTo(3));
	}
	
	/*******************************************************
	 * Get the list of speed records set by street legal cars
	 * use /xml/speedrecords
	 * Check that four speed records have been set by cars
	 * from either Italy or Germany
	 ******************************************************/
	
	@Test
	public void checkFourRecordsHaveBeenSetByCarsFromEitherItalyOrGermany() {
		
		given().
			spec(requestSpec).
		when().
			get("/xml/speedrecords").
		then().
			assertThat().
			body("speedRecords.car.findAll{it.@country in ['Italy','Germany']}.size()", equalTo(4));
	}
	
	/*******************************************************
	 * Get the list of speed records set by street legal cars
	 * use /xml/speedrecords
	 * Check that two speed records have been set by cars
	 * whose make ends on 'Benz'
	 ******************************************************/
	
	@Test
	public void checkTwoRecordsHaveBeenSetByCarsWhoseMakeEndOnBenz() {
		
		given().
			spec(requestSpec).
		when().
			get("/xml/speedrecords").
		then().
			assertThat().
			body("speedRecords.car.@make.grep(~/.*Benz/).size()", equalTo(2));
	}
}