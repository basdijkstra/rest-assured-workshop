package examples;

import dataentities.GraphQLQuery;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredExamplesGraphQLTest {

    @Test
    public void useHardCodedValuesInQuery_checkTheWeather() {

        String queryString = """
                {
                  getCityByName(name: "Amsterdam") {
                    weather {
                      summary {
                        title
                      }
                    }
                  }
                }
                """;

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery(queryString);

        given().
            contentType(ContentType.JSON).
            body(query).
        when().
            post("https://graphql-weather-api.herokuapp.com/").
        then().
            assertThat().
            statusCode(200).
        and().
            body("data.getCityByName.weather.summary.title", equalTo("Clear"));
    }

    private String queryString = "";

    @ParameterizedTest
    @CsvSource({
            "Amsterdam, Clouds",
            "Berlin, Clear",
            "Rome, Clear"
    })
    public void useJSONObjectInQuery_checkTheWeather(String cityName, String expectedWeather) {

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery(queryString);

        JSONObject variables = new JSONObject();
        variables.put("name", cityName);

        query.setVariables(variables.toString());

        given().
            contentType(ContentType.JSON).
            body(query).
        when().
            post("https://graphql-weather-api.herokuapp.com/").
        then().
            assertThat().
            statusCode(200).
        and().
            body("data.getCityByName.weather.summary.title", equalTo(expectedWeather));
    }
}