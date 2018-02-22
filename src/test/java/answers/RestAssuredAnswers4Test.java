package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

public class RestAssuredAnswers4Test {

	private static RequestSpecification requestSpec;

	private static String accessToken;

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
			setBaseUri("http://localhost").
			setPort(9876).
			setBasePath("/api/f1").
			build();
	}
		
	/*******************************************************
	 * Request an authentication token through the API
	 * and extract the value of the access_token field in
	 * the response to a String variable.
	 * Use preemptive Basic authentication:
	 * username = oauth
	 * password = gimmeatoken
	 * Use /oauth2/token
	 ******************************************************/

	@BeforeClass
	public static void retrieveOAuthToken() {

		accessToken =
		
		given().
			spec(requestSpec).
		and().
			auth().
			preemptive().
			basic("oauth","gimmeatoken").
		when().
			get("/oauth2/token").
		then().
			extract().
			path("access_token");
	}
	
	/*******************************************************
	 * Request a list of payments for this account and check
	 * that the number of payments made equals 4.
	 * Use OAuth2 authentication with the previously retrieved
	 * authentication token.
	 * Use /payments
	 * Value to be retrieved is in the paymentsCount field
	 ******************************************************/
	
	@Test
	public void checkNumberOfPayments() {
		
		given().
			spec(requestSpec).
		and().
			auth().
			oauth2(accessToken).
		when().
			get("/payments").
		then().
			assertThat().
			body("paymentsCount",equalTo(4));
	}
	
	/*******************************************************
	 * Request the list of all circuits that hosted a
	 * Formula 1 race in 2014 and check that this request is
	 * answered within 100 ms
	 * Use /2014/circuits.json
	 ******************************************************/
	
	@Test
	public void checkResponseTimeFor2014CircuitList() {
		
		given().
			spec(requestSpec).
		when().
			get("/2014/circuits.json").
		then().
			assertThat().
			time(lessThan(100L),TimeUnit.MILLISECONDS);
	}
}
