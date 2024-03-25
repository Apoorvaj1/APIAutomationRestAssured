package org.project.apoorv.Project;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class RestfulBooker {
    RequestSpecification request_spec;
    ValidatableResponse vr;
    Integer id;
    String token;
    @BeforeTest
    public void createToken(){
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
        String token_1 = vr.extract().path("token");
        System.out.println("Using vr -> token1 value is "+token_1);
        response.prettyPrint();
        response.then().statusCode(200);
        token = response.then().extract().path("token");
        System.out.println("Token is "+token);

    }
    @Test
    public void getBookingID(){
        System.out.println("----------------------Get Booking ID-------------------");
        request_spec = RestAssured.given();
        String payload = "{\n" +
                "    \"firstname\" : \"Apoorv\",\n" +
                "    \"lastname\" : \"Jain\",\n" +
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
        request_spec.body(payload);
        Response response = request_spec.when().post();
        vr = response.then().log().all();
        vr.body("booking.firstname", Matchers.equalTo("Apoorv"));
        String checkin = vr.extract().path("booking.bookingdates.checkin");
        System.out.println("Using vr -> checkin value is "+checkin);
        response.prettyPrint();
        response.then().statusCode(200);
        String contentType =response.getHeader("Content-Type");
        System.out.println("Content type value is "+contentType);
        String name =response.then().extract().path("booking.firstname");
        System.out.println("Firstname value is "+name);

        System.out.println("--------------------ID----------------------");
        id = response.then().extract().path("bookingid");
        System.out.println("Id is "+id);
    }
    @Test
    public void getDetailsbyID(){
        request_spec = RestAssured.given();
        /*String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";*/
        request_spec.cookie("token",token);
        request_spec.baseUri("https://restful-booker.herokuapp.com");
        request_spec.basePath("/booking/"+id);
        //request_spec.body(payload);
        request_spec.accept("application/xml");
        Response response = request_spec.when().get();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200,"Please check again");

    }
    @Test
    public void partialUpdate(){
        request_spec = RestAssured.given();
        String payload = "{\n" +
                "    \"firstname\" : \"Honey\",\n" +
                "    \"lastname\" : \"Singh\"\n" +
                "}";
        request_spec.baseUri("https://restful-booker.herokuapp.com/");
        request_spec.basePath("booking/"+id);
        request_spec.contentType(ContentType.JSON);
        request_spec.accept("application/json");
        request_spec.cookie("token",token);

        request_spec.body(payload);
        Response response = request_spec.when().patch();
        response.prettyPrint();
        JsonPath extractor = response.jsonPath();
        String JSON_name = extractor.get("firstname");
        System.out.println("USING JSONPATH value of firstname is "+JSON_name);

        String name = response.then().extract().path("firstname");
        System.out.println("First name value is "+name);
        String lastname = response.then().extract().path("lastname");
        System.out.println("Lastname value is "+lastname);
        assertThat(lastname).isEqualTo("Singh");

        vr=response.then().log().all();
        vr.statusCode(200);
        vr.body("depositpaid",Matchers.equalTo(true));
        String last_name=vr.extract().path("lastname");
        System.out.println("Value of lastname is "+last_name);

        System.out.println("Time is "+response.getTime());
        System.out.println("Status is "+response.getStatusCode());
        System.out.println("Status 2 is "+response.statusCode());
    }
    @AfterTest
    public void deleteBooking(){
        request_spec = RestAssured.given();
        request_spec.baseUri("https://restful-booker.herokuapp.com");
        request_spec.basePath("/booking/"+id);
        request_spec.contentType(ContentType.JSON);
        request_spec.cookie("token",token);
        Response response = request_spec.when().delete();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),201,"Please check again");
    }

}