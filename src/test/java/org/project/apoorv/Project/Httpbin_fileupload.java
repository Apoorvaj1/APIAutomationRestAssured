package org.project.apoorv.Project;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

public class Httpbin_fileupload {
    RequestSpecification requestSpecification;
    @Test
    public void FileUpload(){
        File file = new File("C:\\Users\\apoor\\OneDrive\\Desktop\\abc1.txt");
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://httpbin.org/post");

        requestSpecification.multiPart("abc",file);
        Response response = requestSpecification.when().post();
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
