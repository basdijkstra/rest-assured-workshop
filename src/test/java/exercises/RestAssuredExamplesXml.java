package exercises;

import org.junit.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Ignore
public class RestAssuredExamplesXml {

    @Test
    public void checkCountryForFirstCar() {

        given().
        when().
            get("http://path.to/cars/xml").
        then().
            assertThat().
            body("cars.car[0].country", equalTo("Italy"));
    }

    @Test
    public void checkYearForLastCar() {

        given().
        when().
            get("http://path.to/cars/xml").
        then().
            assertThat().
            body("cars.car[-1].year", equalTo("2012"));
    }

    @Test
    public void checkModelForSecondCar() {

        given().
        when().
            get("http://path.to/cars/xml").
        then().
            assertThat().
            body("cars.car[1].@model", equalTo("DB11"));
    }

    @Test
    public void checkTheListContainsOneJapaneseCar() {

        given().
        when().
            get("http://path.to/cars/xml").
        then().
            assertThat().
            body("cars.car.findAll{it.country=='Japan'}.size()", equalTo(1));
    }

    @Test
    public void checkTheListContainsTwoCarsWhoseMakeStartsWithAnA() {

        given().
        when().
            get("http://path.to/cars/xml").
        then().
            assertThat().
            body("cars.car.@make.grep(~/A.*/).size()", equalTo(2));
    }
}

