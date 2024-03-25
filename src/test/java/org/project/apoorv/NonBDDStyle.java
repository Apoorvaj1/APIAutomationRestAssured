package org.project.apoorv;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDStyle {
    @Test
    public void positveScenario() {

        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/2112").log().all();
        r.when().get();
        r.then().log().all().statusCode(200);

    }
}
