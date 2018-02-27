package exercises;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

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

