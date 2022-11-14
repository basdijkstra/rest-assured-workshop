package answers;

import static io.restassured.RestAssured.*;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.builder.*;
import io.restassured.specification.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@WireMockTest(httpPort = 9876)
public class RestAssuredAnswers3Test {

    private RequestSpecification requestSpec;

    @BeforeEach
    public void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(9876).
                build();
    }

    /*******************************************************
     * Perform a GET request to /token and pass in basic
     * authentication details with username 'john' and
     * password 'demo'.
     *
     * Extract the value of the 'token' element in the
     * response into a String variable.
     *
     * Use the token to authenticate using OAuth2 when sending
     * a GET request to /secure/customer/12212
     *
     * Verify that the status code of this response is equal to HTTP 200
     ******************************************************/

    @Test
    public void getTokenUsingBasicAuth_extractFromResponse_thenReuseAsOAuthToken() {

        String token =

            given().
                spec(requestSpec).
                auth().
                preemptive().
                basic("john", "demo").
            when().
                get("/token").
            then().
                extract().
                path("token");

        given().
            spec(requestSpec).
            auth().
            oauth2(token).
        when().
            get("/secure/customer/12212").
        then().
            assertThat().
            statusCode(200);
    }
}