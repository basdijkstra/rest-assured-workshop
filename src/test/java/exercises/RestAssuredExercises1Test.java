package exercises;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@WireMockTest(httpPort = 9876)
public class RestAssuredExercises1Test {

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
		then();
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
		then();
	}

	/*******************************************************
	 * Send a GET request to /customer/12212
	 * and check that the Content-Type header of the response
	 * has value 'application/json'
	 ******************************************************/

	@Test
	public void requestDataForCustomer12212_checkContentType_expectApplicationJson() {

		given().
			spec(requestSpec).
		when().
		then();
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
		then();
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
		then();
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
		then();
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
		then();
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
		then();
	}
}