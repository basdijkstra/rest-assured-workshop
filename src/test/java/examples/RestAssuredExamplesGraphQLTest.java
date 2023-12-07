package examples;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredExamplesGraphQLTest {

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

    String parameterizedQueryString = """
                query GetWeatherFor($name: String!)
                {
                  getCityByName(name: $name) {
                    weather {
                      summary {
                        title
                      }
                    }
                  }
                }
                """;

    @Test
    public void useHardCodedValuesInQuery_checkTheWeather() {

        HashMap<String, Object> graphQlQuery = new HashMap<>();
        graphQlQuery.put("query", queryString);

        given().
            contentType(ContentType.JSON).
            body(graphQlQuery).
        when().
            post("https://graphql-weather-api.herokuapp.com/").
        then().
            assertThat().
            statusCode(200).
        and().
            body("data.getCityByName.weather.summary.title", equalTo("Clear"));
    }

    @ParameterizedTest
    @CsvSource({
            "Amsterdam, Clouds",
            "Berlin, Clear",
            "Rome, Clear"
    })
    public void useJSONObjectInQuery_checkTheWeather(String cityName, String expectedWeather) {

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("name", cityName);

        HashMap<String, Object> graphQlQuery = new HashMap<>();
        graphQlQuery.put("query", parameterizedQueryString);
        graphQlQuery.put("variables", variables);

        given().
            contentType(ContentType.JSON).
            body(graphQlQuery).
        when().
            post("https://graphql-weather-api.herokuapp.com/").
        then().
            assertThat().
            statusCode(200).
        and().
            body("data.getCityByName.weather.summary.title", equalTo(expectedWeather));
    }
}