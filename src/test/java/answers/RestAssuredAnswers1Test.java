package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@WireMockTest(httpPort = 9876)
public class RestAssuredAnswers1Test {

	private RequestSpecification requestSpec;

	@BeforeEach
	public void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder().
			setBaseUri("http://localhost").
			setPort(9876).
			build();
	}
	
	/*******************************************************
	 * Send a GET request to /customer/12212
	 * and check that the response has HTTP status code 200
	 ******************************************************/
	
	@Test
	public void requestDataForCustomer12212_checkResponseCode_expect200() {
				
		given().
			spec(requestSpec).
		when().
			get("/customer/12212").
		then().
			assertThat().
			statusCode(200);
	}
	
	/*******************************************************
	 * Send a GET request to /customer/99999
	 * and check that the answer has HTTP status code 404
	 ******************************************************/
	
	@Test
	public void requestDataForCustomer99999_checkResponseCode_expect404() {
				
		given().
			spec(requestSpec).
		when().
			get("/customer/99999").
		then().
			assertThat().
			statusCode(404);
	}
	
	/*******************************************************
	 * Send a GET request to /customer/12212
	 * and check that the response is in JSON format 
	 ******************************************************/
	
	@Test
	public void requestDataForCustomer12212_checkContentType_expectApplicationJson() {
				
		given().
			spec(requestSpec).
		when().
			get("/customer/12212").
		then().
			assertThat().
			contentType(equalTo("application/json"));
	}
	
	/***********************************************
	 * Send a GET request to /customer/12212 and check
	 * that the first name of the person associated with
	 * this customer ID is 'John'.
	 *
	 * Use the GPath expression "firstName" to
	 * extract the required response body element
	 **********************************************/
	
	@Test
	public void requestDataForCustomer12212_checkFirstName_expectJohn() {
				
		given().
			spec(requestSpec).
		when().
			get("/customer/12212").
		then().
			assertThat().
			body("firstName", equalTo("John"));
	}

	/***********************************************
	 * Send a GET request to /customer/12212 and check
	 * that the city where the person associated with
	 * this customer ID is living is 'Beverly Hills'.
	 *
	 * Use the GPath expression "address.city" to
	 * extract the required response body element
	 **********************************************/

	@Test
	public void requestDataForCustomer12212_checkAddressCity_expectBeverlyHills() {

		given().
		    spec(requestSpec).
		when().
			get("/customer/12212").
		then().
			assertThat().
			body("address.city", equalTo("Beverly Hills"));
	}
	
	/***********************************************
	 * Send a GET request to /customer/12212/accounts
	 * and check that the list of accounts returned
	 * includes an account with ID 12345
	 *
	 * Use the GPath expression "accounts.id" to
	 * extract the required response body elements
	 **********************************************/
	
	@Test
	public void requestAccountsForCustomer12212_checkListOfAccountsIDs_expectContains12345() {
		
		given().
			spec(requestSpec).
		when().
			get("/customer/12212/accounts").
		then().
			assertThat().
			body("accounts.id", hasItem(12345));
	}

	/***********************************************
	 * Send a GET request to /customer/12212/accounts
	 * and check that the list of accounts returned
	 * does not include an account with ID 99999
	 *
	 * Use the GPath expression "accounts.id" to
	 * extract the required response body elements
	 **********************************************/

	@Test
	public void requestAccountsForCustomer12212_checkListOfAccountsIDs_expectDoesNotContain99999() {

		given().
			spec(requestSpec).
		when().
			get("/customer/12212/accounts").
		then().
			assertThat().
			body("accounts.id", not(hasItem(99999)));
	}

	/***********************************************
	 * Send a GET request to /customer/12212/accounts
	 * and check that the list of account IDs returned
	 * is a collection of size 3
	 * 
	 * Use the GPath expression "accounts.id" to
	 * extract the required response body elements
	 **********************************************/

	@Test
	public void requestAccountsForCustomer12212_checkListOfAccountsIDs_expectSize3() {

		given().
			spec(requestSpec).
		when().
			get("/customer/12212/accounts").
		then().
			assertThat().
			body("accounts.id", hasSize(3));
	}
}