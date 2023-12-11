package examples;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@Disabled
public class RestAssuredExamplesXmlTest {

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
            body("cars.car[-1].modelYear", equalTo("2012"));
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
            body("cars.car.findAll{it.country=='Japan'}", hasSize(1));
    }

    @Test
    public void checkTheListContainsTwoCarsWhoseMakeStartsWithAnA() {

        given().
        when().
            get("http://path.to/cars/xml").
        then().
            assertThat().
            body("cars.car.@make.grep(~/A.*/)", hasSize(2));
    }
}

