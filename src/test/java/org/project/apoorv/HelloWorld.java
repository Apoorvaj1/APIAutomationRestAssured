package org.project.apoorv;

import io.restassured.RestAssured;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World");

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us").basePath("/IN/560037")

                .when().log().all()
                .get()

                .then().log().all().statusCode(200);
    }
}
