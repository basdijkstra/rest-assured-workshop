package exercises;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import dataentities.GraphQLQuery;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@WireMockTest(httpPort = 9876)
public class RestAssuredExercises6Test {

    /*******************************************************
     * Create a new GraphQL query as a String with value
     * { company { name ceo coo } }
     *
     * POST this object to https://api.spacex.land/graphql/
     *
     * Assert that the name of the CEO is Elon Musk
     *
     * Use "data.company.ceo" as the GPath
     * expression to extract the required value from the response
     ******************************************************/

    @Test
    public void getCompanyData_checkCeo_shouldBeElonMusk() {

        given().
            contentType(ContentType.JSON).
        when().
        then();
    }

    /*******************************************************
     * Transform this Test into a ParameterizedTest, using
     * a CsvSource data source with three test data rows:
     * ------------------------------------
     * rocket id   | rocket name  | country
     * ------------------------------------
     * falcon1     | Falcon 1     | Republic of the Marshall Islands
     * falconheavy | Falcon Heavy | United States
     * starship    | Starship     | United States
     *
     * Parameterize the test
     *
     * Create a new GraphQL query from the given query string
     * Pass in the rocket id as a variable value
     *
     * POST this object to https://api.spacex.land/graphql/
     *
     * Assert that the HTTP response status code is 200
     *
     * Assert that the name of the rocket is equal to the value in the data source
     * Use "data.rocket.name" as the GPath
     * expression to extract the required value from the response
     *
     * Also, assert that the country of the rocket is equal to the value in the data source
     * Use "data.rocket.country" as the GPath
     * expression to extract the required value from the response
     ******************************************************/

    @Test
    public void getRocketDataById_checkNameAndCountry() {

        String queryString = """
                query getRocketData($id: ID!)
                {
                  rocket(id: $id) {
                    name
                    country
                  }
                }
                """;

        given().
            contentType(ContentType.JSON).
        when().
        then();
    }
}