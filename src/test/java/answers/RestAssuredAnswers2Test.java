package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@WireMockTest(httpPort = 9876)
public class RestAssuredAnswers2Test {

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

    @ParameterizedTest
    @CsvSource({
            "12212, John, Smith",
            "12323, Susan, Holmes",
            "14545, Anna, Grant"
    })
    public void checkFirstNameAndLastNameForCustomerIDs(
            int customerId, String expectedFirstName, String expectedLastName
    ) {

        given().
            spec(requestSpec).
        and().
            pathParam("customerId", customerId).
        when().
            get("/customer/{customerId}").
        then().
            assertThat().
            body("firstName", equalTo(expectedFirstName)).
            body("lastName", equalTo(expectedLastName));
    }
}