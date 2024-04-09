package org.project.apoorv.Project;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Postmethod {
    String payload ="{\n" +
            "    \"age\":28,\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\",\n" +
            "    \"hobbies\":[\"Cricket\",\"Vollyball\"],\n" +
            "    \"Fastfood\":{\n" +
            "        \"food\":\"vadapav\"\n" +
            "    }\n" +
            "}";

    RequestSpecification reqSpec;
    ValidatableResponse vr;
    String id;
    Response response;
    @BeforeTest
    public void createUser(){
        reqSpec = RestAssured.given();
        reqSpec.baseUri("https://reqres.in");
        reqSpec.basePath("/api/users");
        reqSpec.body(payload).log().all();
        response = reqSpec.when().post();
        String abc = response.asString();
        System.out.println("Full response is ");
        System.out.println(abc);

        response.getBody().prettyPrint();

        id = response.then().extract().path("id");
        System.out.println("Id is "+id);

        String contentLength = response.getHeader("Content-Length");
        System.out.println(contentLength);
        String contentType = response.getHeader("Content-Type");
        System.out.println("Content Type is "+contentType);
        Assert.assertEquals(response.statusCode(),201,"Please verify status code");

    }
    @Test
    public void getUserbyID(){
        //System.out.println("Id is "+id);
        reqSpec = RestAssured.given();
        reqSpec.baseUri("https://reqres.in");
        reqSpec.basePath("/api/users/"+id);
        response = reqSpec.when().get();
        response.getBody().prettyPrint();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
        /*int reponse1 = response.getStatusCode();
        System.out.println(reponse1);
        String respons = response.asString();
        System.out.println(respons);*/
    }


}
