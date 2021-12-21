package answers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@WireMockTest(httpPort = 9876)
public class RestAssuredAnswers3Test {

    private static RequestSpecification requestSpec;

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

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                expectBody("country", equalTo("United States")).
                build();
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
            get("/us/90210").
        then().
            spec(responseSpec).
        and().
            body("'country abbreviation'",equalTo("US"));
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

        String actualCountry =

                given().
                    spec(requestSpec).
                when().
                    get("/us/90210").
                then().
                    extract().
                    path("country");

        assertEquals("United States", actualCountry);
    }
}