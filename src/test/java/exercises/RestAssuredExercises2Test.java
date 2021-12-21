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

    private static RequestSpecification requestSpec;

    @BeforeEach
    public void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(9876).
                build();
    }

    /*******************************************************
     * Transform this Test into a ParameterizedTest, using
     * a CsvSource data source with three test data rows:
     * ------------------------------------
     * country code | zip code | state
     * ------------------------------------
     * us           | 90210    | California
     * us           | 12345    | New York
     * ca           | Y1A      | Yukon
     *
     * Request zip code data for the given country / zip
     * combinations by sending a GET to /<countryCode>/<zipCode>.
     *
     * Use the test data collection created
     * above. Check that the state returned by the API
     * matches the expected value.
     *
     * Use the GPath expression "places[0].state" to
     * extract the required response body element
     ******************************************************/

    @Test
    public void checkStateForCountryCodeAndZipCode() {

        given().
            spec(requestSpec).
        when().
        then();
    }
}