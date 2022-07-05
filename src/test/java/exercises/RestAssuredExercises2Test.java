package exercises;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@WireMockTest(httpPort = 9876)
public class RestAssuredExercises2Test {

    private RequestSpecification requestSpec;

    @BeforeEach
    public void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(9876).
                build();
    }

    /*******************************************************
     * Transform these tests into a single ParameterizedTest,
     * using a CsvSource data source with three test data rows:
     * ------------------------------------
     * customer ID | first name | last name
     * ------------------------------------
     * 12212       | John       | Smith
     * 12323       | Susan      | Holmes
     * 14545       | Anna       | Grant
     *
     * Request user data for the given user IDs by sending
     * an HTTP GET to /customer/<customerID>.
     *
     * Use the test data collection created
     * above. Check that both the first name and the last name
     * for each of the customer IDs matches the expected values
     * listed in the table above
     *
     * Use the GPath expressions "firstName" and "lastName",
     * respectively, to extract the required response body elements
     ******************************************************/

    @Test
    public void requestDataForCustomer12212_checkNames_expectJohnSmith() {

        given().
            spec(requestSpec).
        when().
            get("/customer/12212").
        then().
            assertThat().
            body("firstName", equalTo("John")).
            body("lastName", equalTo("Smith"));
    }

    @Test
    public void requestDataForCustomer12323_checkNames_expectSusanHolmes() {

        given().
            spec(requestSpec).
        when().
            get("/customer/12323").
        then().
            assertThat().
            body("firstName", equalTo("Susan")).
            body("lastName", equalTo("Holmes"));
    }

    @Test
    public void requestDataForCustomer14545_checkNames_expectAnnaGrant() {

        given().
            spec(requestSpec).
        when().
            get("/customer/14545").
        then().
            assertThat().
            body("firstName", equalTo("Anna")).
            body("lastName", equalTo("Grant"));
    }
}