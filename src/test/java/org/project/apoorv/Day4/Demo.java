package org.project.apoorv.Day4;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.assertj.core.api.Assertions.*;

public class Demo {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String Token;

    @BeforeTest

    public void getToken() {

        String payload = "{\n"

                + " \"username\": \"admin\",\n"

                + " \"password\": \"password123\"\n"

                + "}";

        requestSpecification = RestAssured.given();

        requestSpecification.baseUri("https://restful-booker.herokuapp.com");

        requestSpecification.basePath("/auth");

        requestSpecification.contentType(ContentType.JSON);

        requestSpecification.body(payload);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();

        validatableResponse.body("token", Matchers.notNullValue()); // Rest Assured Assertions

        Token = response.then().extract().path("token");

        Assert.assertNotNull(Token); //TestNG Asssertions

        assertThat(Token).isNotNull().isNotEmpty().isNotBlank();

        System.out.println(Token);

    }

    @Test
    public void nonBDDStylePut() {

        String payload1 = "{\n"

                + " \"firstname\" : \"Apoorv\",\n"

                + " \"lastname\" : \"Sharma\",\n"

                + " \"totalprice\" : 1220,\n"

                + " \"depositpaid\" : true,\n"

                + " \"bookingdates\" : {\n"

                + " \"checkin\" : \"2024-03-16\",\n"

                + " \"checkout\" : \"2024-03-24\"\n"

                + " },\n"

                + " \"additionalneeds\" : \"Lunch\"\n"

                + "}";

        requestSpecification = RestAssured.given();

        requestSpecification.baseUri("https://restful-booker.herokuapp.com");

        requestSpecification.basePath("/booking/5526");

        requestSpecification.contentType(ContentType.JSON);

        requestSpecification.cookie("token", Token);

        requestSpecification.body(payload1).log().all();

//Calling Put methods

        response = requestSpecification.when().put(); // when().put or directly put can use

//Validate Response

        validatableResponse = response.then().log().all();
        System.out.println("--------------------------------------");
        String response123 = response.getBody().asString();
        System.out.println("Response is "+response123);

        System.out.println("--------------------------------------");

        response.getBody().prettyPrint();

        validatableResponse.statusCode(200);
        String name = validatableResponse.extract().path("firstname");
        System.out.println("Firstname value is "+name);


    }


}

