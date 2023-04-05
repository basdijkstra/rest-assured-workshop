package answers;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@WireMockTest(httpPort = 9876)
public class RestAssuredAnswers6Test {

    /*******************************************************
     * Create a new payload for a GraphQL query using a
     * HashMap and the specified query (with hardcoded ID)
     *
     * POST this object to https://fruits-api.netlify.app/graphql
     *
     * Assert that the name of the fruit is 'Manzana'
     *
     * Use "data.fruit.fruit_name" as the GPath
     * expression to extract the required value from the response
     ******************************************************/

    @Test
    public void getFruitData_checkFruitName_shouldBeManzana() {

        String queryString = """
                {
                    fruit(id: 1) {
                        id
                        fruit_name
                        tree_name
                    }
                }
                """;

        HashMap<String, Object> graphQlQuery = new HashMap<>();
        graphQlQuery.put("query", queryString);

        given().
            contentType(ContentType.JSON).
            body(graphQlQuery).
        when().
            post("https://fruits-api.netlify.app/graphql").
        then().
            assertThat().
            statusCode(200).
        and().
            body("data.fruit.fruit_name", equalTo("Manzana"));
    }

    /*******************************************************
     * Transform this Test into a ParameterizedTest, using
     * a CsvSource data source with three test data rows:
     * ---------------------------------
     * fruit id | fruit name | tree name
     * ---------------------------------
     *        1 |    Manzana |   Manzano
     *        2 |       Pera |     Peral
     *        3 |     Banana |   Platano
     *
     * Parameterize the test
     *
     * Create a new GraphQL query from the given query string
     * Pass in the fruit id as a variable value
     *
     * POST this object to https://fruits-api.netlify.app/graphql
     *
     * Assert that the HTTP response status code is 200
     *
     * Assert that the name of the fruit is equal to the value in the data source
     * Use "data.fruit.fruit_name" as the GPath
     * expression to extract the required value from the response
     *
     * Also, assert that the tree name is equal to the value in the data source
     * Use "data.fruit.tree_name" as the GPath
     * expression to extract the required value from the response
     ******************************************************/

    @ParameterizedTest
    @CsvSource({
            "1, Manzana, Manzano",
            "2, Pera, Peral",
            "3, Banana, Platano"
    })
    public void getFruitDataById_checkFruitNameAndTreeName(int fruitId, String expectedFruitName, String expectedTreeName) {

        String queryString = """
                query GetFruit($id: ID!)
                {
                    fruit(id: $id) {
                        id
                        fruit_name
                        tree_name
                    }
                }
                """;

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("id", fruitId);

        HashMap<String, Object> graphqlQuery = new HashMap<>();
        graphqlQuery.put("query", queryString);
        graphqlQuery.put("variables", variables);

        given().
            contentType(ContentType.JSON).
            body(graphqlQuery).
        when().
            post("https://fruits-api.netlify.app/graphql").
        then().
            assertThat().
            statusCode(200).
        and().
            body("data.fruit.fruit_name", equalTo(expectedFruitName)).
        and().
            body("data.fruit.tree_name", equalTo(expectedTreeName));
    }
}