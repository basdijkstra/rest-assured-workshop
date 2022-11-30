package exercises;

import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import dataentities.Account;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredExercises5Test {

    private RequestSpecification requestSpec;

    @RegisterExtension
    static WireMockExtension wiremock = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(9876)
                    .extensions(new ResponseTemplateTransformer(true)))
            .build();

    @BeforeEach
    public void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(9876).
                setContentType(ContentType.JSON).
                build();
    }

    /*******************************************************
     * Create a new Account object with 'savings' as the account
     * type
     *
     * POST this object to /customer/12212/accounts
     *
     * Verify that the response HTTP status code is equal to 201
     ******************************************************/

    @Test
    public void postAccountObject_checkResponseHttpStatusCode_expect201() {

        given().
            spec(requestSpec).
        when().
        then();
    }

    /*******************************************************
     * Perform an HTTP GET to /customer/12212/accounts and
     * deserialize the response into an object of type
     * AccountResponse
     *
     * Using a JUnit assertEquals() method, verify that the
     * number of account in the response (in other words,
     * the size() of the accounts property) is equal to 3
     ******************************************************/

    @Test
    public void getAccountsForCustomer12212_deserializeIntoList_checkListSize_shouldEqual3() {

        given().
            spec(requestSpec).
        when();
    }

    /*******************************************************
     * Create a new Customer object by using the constructor
     * that takes a first name and last name as its parameters
     *
     * Use a first name and a last name of your own choosing
     *
     * POST this object to /customer
     *
     * Deserialize the response into another object of type
     * Customer and use JUnit assertEquals() assertions to
     * check that the first name and last name returned by
     * the API are the same as those you passed into the
     * constructor of the Customer method you POSTed
     ******************************************************/

    @Test
    public void postCustomerObject_checkReturnedFirstAndLastName_expectSuppliedValues() {

        given().
            spec(requestSpec).
        when();
    }
}