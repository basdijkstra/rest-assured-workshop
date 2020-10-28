package exercises;

import com.tngtech.java.junit.dataprovider.*;
import dataentities.Address;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.*;
import io.restassured.http.*;
import io.restassured.specification.*;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class RestAssuredExamples {

    @Test
    public void usePreviouslyStoredAuthToken() {

        given().
            auth().
            oauth2("myAuthenticationToken").
        when().
            get("https://my.very.secure/api").
        then().
            assertThat().
            statusCode(200);
    }

    @DataProvider
    public static Object[][] userData() {
        return new Object[][] {
                { 1, "Leanne Graham" },
                { 2, "Ervin Howell" },
                { 3, "Clementine Bauch"}
        };
    }

    @Test
    public void getUserData_verifyName_shouldBeLeanneGraham() {

        given().
        when().
            get("http://jsonplaceholder.typicode.com/users/1").  // Do a GET call to the specified resource
        then().
            assertThat().                                           // Assert that the value of the element 'name'
            body("name", equalTo("Leanne Graham"));       // in the response body equals 'Leanne Graham'
    }

    @Test
    public void logAllRequestData() {

        given().
            log().all().
        when().
            get("http://jsonplaceholder.typicode.com/users/1").
        then().
            assertThat().
            body("name", equalTo("Leanne Graham"));
    }

    @Test
    public void logAllResponseData() {

        given().
        when().
            get("http://jsonplaceholder.typicode.com/users/1").
        then().
            log().all().
        and().
            assertThat().
            body("name", equalTo("Leanne Graham"));
    }


    @Test
    public void getUserData_verifyStatusCodeAndContentType() {

        given().
        when().
            get("http://jsonplaceholder.typicode.com/users/1").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void useQueryParameter() {

        given().
            queryParam("text", "testcase").
        when().
            get("http://md5.jsontest.com").
        then().
            assertThat().
            body("md5", equalTo("7489a25fc99976f06fecb807991c61cf"));
    }

    @Test
    public void usePathParameter() {

        given().
            pathParam("userId",1).
        when().
            get("http://jsonplaceholder.typicode.com/users/{userId}").
        then().
            assertThat().
            body("name", equalTo("Leanne Graham"));
    }

    @Test
    @UseDataProvider("userData")
    public void checkNameForUser
        (int userId, String expectedUserName) {

        given().
            pathParam("userId", userId).
        when().
            get("http://jsonplaceholder.typicode.com/users/{userId}").
        then().
            assertThat().
            body("name",equalTo(expectedUserName));
    }

    @Test
    public void useBasicAuthentication() {

        given().
            auth().
            preemptive().
            basic("username", "password").
        when().
            get("https://my.secure/api").
        then().
            assertThat().
            statusCode(200);
    }

    @Test
    public void useOAuthAuthentication() {

        given().
            auth().
            oauth2("myAuthenticationToken").
        when().
            get("https://my.very.secure/api").
        then().
            assertThat().
            statusCode(200);
    }

    @Test
    public void captureAndReuseUserId() {

        String userId =

            given().
            when().
                post("http://my.user.api/user").
            then().
                extract().
                path("id");

        given().
            pathParam("userId", userId).
        when().
            get("http://my.user.api/user/{userId}").
        then().
            assertThat().
            statusCode(200);
    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpec() {

        responseSpec =
            new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void useResponseSpec() {

        given().
        when().
            get("http://jsonplaceholder.typicode.com/users/1").
        then().
            spec(responseSpec).
        and().
            body("name", equalTo("Leanne Graham"));
    }

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpec() {

        requestSpec =
            new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                setPort(9876).
                build();
    }

    @Test
    public void useRequestSpec() {

        given().
            spec(requestSpec).
        when().
            get("/us/90210.json").
        then().
            assertThat().
            statusCode(200);
    }

    @Test
    public void serializeAddressToJson() {

        Address myAddress = new Address("My street", 1, 1234, "Amsterdam");

        given().
            body(myAddress).
        when().
            post("http://localhost:9876/address").
        then().
            assertThat().
            statusCode(200);
    }

    @Test
    public void deserializeJsonToAddress() {

        Address myAddress =

            given().
            when().
                get("http://localhost:9876/address").
                as(Address.class);

        Assert.assertEquals("Amsterdam", myAddress.getCity());
    }
}