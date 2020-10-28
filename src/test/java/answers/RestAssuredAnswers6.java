package answers;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import dataentities.Car;
import dataentities.Photo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class RestAssuredAnswers6 {

	private static RequestSpecification requestSpec;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().port(9876));

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
			setBaseUri("http://localhost").
			setPort(9876).
			setContentType(ContentType.JSON).
			build();
	}

	@Test
	public void fromUserId_findPhotoTitle_expectPariaturSuntEveniet() {

		/*******************************************************
		 * Perform a GET to /users and extract the user id
		 * that corresponds to the user with username 'Karianne'
		 *
		 * Hint: use extract().path() and a 'find' filter to do this.
		 *
		 * Store the user id in a variable of type int
		 ******************************************************/

		int userId = given().
				spec(requestSpec).
			when().
				get("/users").
			then().
				extract().
				path("find{it.username=='Karianne'}.id");

		/*******************************************************
		 * Use a JUnit assertEquals to verify that the userId
		 * is equal to 4
		 ******************************************************/

		Assert.assertEquals(4, userId);

		/*******************************************************
		 * Perform a GET to /albums and extract all albums that
		 * are associated with the previously retrieved user id.
		 *
		 * Hint: use extract().path() and a 'findAll' to do this.
		 *
		 * Store these in a variable of type List<Integer>.
		 ******************************************************/

		List<Integer> albumIds = given().
				spec(requestSpec).
			when().
				get("/albums").
			then().
				extract().
				path(String.format("findAll{it.userId==%d}.id", userId));

		/*******************************************************
		 * Use a JUnit assertEquals to verify that the list has
		 * exactly 10 items (hint: use the size() method)
		 ******************************************************/

		Assert.assertEquals(10, albumIds.size());

		/*******************************************************
		 * Perform a GET to /albums/XYZ/photos, where XYZ is the
		 * id of the fifth album in the previously extracted list
		 * of album IDs (hint: use get(index) on the list).
		 *
		 * Deserialize the list of photos returned into a variable
		 * of type List<Photo>.
		 *
		 * Hint: see
		 * https://stackoverflow.com/questions/21725093/rest-assured-deserialize-response-json-as-listpojo
		 * (the accepted answer should help you solve this one).
		 ******************************************************/

		List<Photo> photos = Arrays.asList(given().
				spec(requestSpec).
				pathParam("albumId", albumIds.get(4)).
			when().
				get("/albums/{albumId}/photos").as(Photo[].class));

		/*******************************************************
		 * Use a JUnit assertEquals to verify that the title of
		 * the 32nd photo in the list equals 'pariatur sunt eveniet'
		 *
		 * Hint: use the get() method to retrieve an object with a
		 * specific index from a List
		 ******************************************************/

		Assert.assertEquals("pariatur sunt eveniet", photos.get(31).getTitle());
	}
}