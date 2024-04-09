package org.project.apoorv.Day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PracticeDP {
    RequestSpecification request_spec;
    ValidatableResponse vr;
    Integer id;
    String token;

    @BeforeTest
    public void createToken() {
        System.out.println("----------------Get token------------------");
        request_spec = RestAssured.given();
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        request_spec.baseUri("https://restful-booker.herokuapp.com");
        request_spec.basePath("/auth");
        request_spec.contentType(ContentType.JSON);
        request_spec.body(payload);
        Response response = request_spec.when().post();
        vr = response.then().log().all();
        vr.body("token", Matchers.notNullValue());
        System.out.println("-----------++++++++++++++++++++++-----------------");
        response.getBody().prettyPrint();
        System.out.println("----------+++++++++++++++++++++++------------------");
        response.then().statusCode(200);
        token = response.then().extract().path("token");
        System.out.println("Token is " + token);
    }

    @Test (dataProvider = "getData")
    public void createBooking(String f_name,String l_name) {
        request_spec = RestAssured.given();
        String payload = "{\n" +
                "    \"firstname\" : \"" + f_name + "\",\n" +
                "    \"lastname\" : \"" + l_name + "\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        request_spec.baseUri("https://restful-booker.herokuapp.com");
        request_spec.basePath("/booking");
        request_spec.contentType(ContentType.JSON);
        //request_spec.headers("Content-Type","application/json; charset=utf-8");
        request_spec.accept("application/json");
        request_spec.cookie("token",token);
        request_spec.body(payload);
        Response response = request_spec.when().post();
        vr = response.then().log().all();
        if(f_name.equals("Rahul")) {
            vr.body("booking.firstname", Matchers.equalTo("Rahul"));
        }
        if(f_name.equals("Sahil")){
            vr.body("booking.firstname", Matchers.equalTo("Sahil"));
        }
        if(f_name.equals("Piyush")){
            vr.body("booking.firstname", Matchers.equalTo("Piyush"));
        }
        String checkin = vr.extract().path("booking.bookingdates.checkin");
        System.out.println("Using vr -> checkin value is "+checkin);
        response.prettyPrint();
        response.then().statusCode(200);
        String contentType =response.getHeader("Content-Type");
        System.out.println("Content type value is "+contentType);
        String name =response.then().extract().path("booking.firstname");
        System.out.println("Firstname value is "+name);
        if(f_name.equals("Rahul")){
            id = response.then().extract().path("bookingid");
        }
        if(f_name.equals("Sahil")){
            id = response.then().extract().path("bookingid");
        }
        if(f_name.equals("Piyush")){
            id = response.then().extract().path("bookingid");
        }
        System.out.println("ID of "+f_name+" is: "+id);

    }
    @DataProvider(name="getData")
    public Object[][] useData() {
        return new Object[][]{
                {"Rahul","Jain"},
                {"Sahil","Jain"},
                {"Piyush","Jain"}
        };
    }

}

