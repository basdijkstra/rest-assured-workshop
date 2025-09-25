package exercises;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@WireMockTest(httpPort = 9876)
public class RestAssuredExercises4Test {

    private RequestSpecification requestSpec;

    @BeforeEach
    public void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(9876).
                build();
    }

    /*******************************************************
     * Perform a GET request to /xml/customer/12212/accounts
     * to get the list of accounts associated with customer
     * 12212 in XML format
     *
     * Assert that the ID of the first account equals 12345
     * What do you notice about comparing integer element values?
     *
     * Use "accounts.account[0].id" as the GPath
     * expression to extract the required value from the response
     ******************************************************/

    @Test
    public void getAccountsForCustomer12212AsXml_checkIdOfFirstAccount_shouldBe12345() {

        given().
            spec(requestSpec).
        when().
        then();
    }

    /*******************************************************
     * Perform a GET request to /xml/customer/12212/accounts
     * to get the list of accounts associated with customer
     * 12212 in XML format
     *
     * Assert that the balance for the third account in the
     * list is equal to 43.21
     *
     * Can you create the correct GPath expression yourself,
     * using the examples as shown in the slides?
     ******************************************************/

    @Test
    public void getAccountsForCustomer12212AsXml_checkBalanceOfThirdAccount_shouldBe4321() {

        given().
            spec(requestSpec).
        when().
        then();
    }

    /*******************************************************
     * Perform a GET request to /xml/customer/12212/accounts
     * to get the list of accounts associated with customer
     * 12212 in XML format
     *
     * Assert that the list contains 3 accounts of type 'checking'
     *
     * Can you create the correct GPath expression yourself,
     * using the examples as shown in the slides?
     ******************************************************/

    @Test
    public void getAccountsForCustomer12212AsXml_checkNumberOfCheckingAccounts_shouldBe3() {

        given().
            spec(requestSpec).
        when().
        then();
    }


    /*******************************************************
     * Perform a GET request to /xml/customer/12212/accounts
     * to get the list of accounts associated with customer
     * 12212 in XML format
     *
     * Assert that the list contains 2 accounts that have an
     * id starting with a '5'
     *
     * Can you create the correct GPath expression yourself,
     * using the examples as shown in the slides?
     ******************************************************/

    @Test
    public void getAccountsForCustomer12212AsXml_checkNumberOfAccountIdsStartingWith5_shouldBe2() {

        given().
            spec(requestSpec).
        when().
        then();
    }
}