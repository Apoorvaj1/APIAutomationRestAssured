package org.project.apoorv.DDT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Demo {

        RequestSpecification requestSpecification;
        ValidatableResponse validatableResponse;
        String token;
        @Test
        public void CreateToken() {

            requestSpecification = RestAssured.given();

            String payload = "{\n" +

                    " \"username\" : \"admin\",\n" +

                    " \"password\" : \"password123\"\n" +

                    "}";


            requestSpecification.baseUri("https://restful-booker.herokuapp.com/");

            requestSpecification.basePath("auth");

            requestSpecification.contentType(ContentType.JSON);

            requestSpecification.body(payload).log().all();

            Response response = requestSpecification.when().post();

            validatableResponse = response.then().log().all();



//Matchers RestAssured(Hamcrest)

            validatableResponse.body("token", Matchers.notNullValue());


//TESTNG ASSERTION

            token= response.then().log().all().extract().path("token");

            Assert.assertNotNull(token);



//ASSERJ

            assertThat(token).isNotNull().isNotBlank().isNotEmpty();

            System.out.println("Token is "+token);

        }

}
