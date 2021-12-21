package exercises;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpPort = 9876)
public class RestAssuredExercises3Test {

    private RequestSpecification requestSpec;

    @BeforeEach
    public void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(9876).
                build();
    }

    /*******************************************************
     * Create a static ResponseSpecification that checks whether:
     * - the response has statusCode 200
     * - the response contentType is JSON
     * - the value of 'country' in the response body
     *   is equal to 'United States' (use the GPath expression
     *   "country" to extract the required element)
     ******************************************************/

    private static ResponseSpecification responseSpec;

    @BeforeEach
    public void createResponseSpecification() {

    }

    /*******************************************************
     * Perform a GET request to /us/90210
     *
     * Use the previously created ResponseSpecification to
     * execute the specified checks
     *
     * Additionally, check that 'country abbreviation' is
     * equal to 'US' (use the GPath expression
     * "'country abbreviation'" to extract the required element)
     ******************************************************/

    @Test
    public void useResponseSpecification() {

        given().
            spec(requestSpec).
        when().
        then();
    }

    /*******************************************************
     * Perform a GET request to /us/90210
     *
     * Extract the value of the 'country' element in the
     * response into a String variable actualCountry
     *
     * Use the given JUnit assertion to check on its value
     ******************************************************/

    @Test
    public void extractCountryFromResponse() {

        String actualCountry = "";

                given().
                    spec(requestSpec).
                when().
                then();

        assertEquals("United States", actualCountry);
    }
}