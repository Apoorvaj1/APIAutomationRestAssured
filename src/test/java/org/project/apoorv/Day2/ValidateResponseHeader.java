package org.project.apoorv.Day2;

import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ValidateResponseHeader {
//https://reqres.in/api/users/2

	Integer bookingID;

	@Test
	public void GetSingleUser()
	{
		//Get Request Specification
		RequestSpecification requestSpec = RestAssured.given();
		
		//specify base uri
		requestSpec.baseUri("https://reqres.in");
		requestSpec.basePath("/api/users/2");
		
		//create get request
		Response response = requestSpec.get();
		
		//validate response header
		String contentType = response.getHeader("Content-Type");
		System.out.println("Header value is "+contentType);


		System.out.println("-------------------ABC-----------------");
		ValidatableResponse validatableResponse =  response.then();
		validatableResponse.statusCode(200);
		validatableResponse.body("data.first_name", Matchers.equalTo("Janet"));
		String first_name = response.then().extract().path("data.first_name");
		System.out.println("Particular body value is "+first_name);
		System.out.println("---------------------------------------");
		//System.out.println("Value of connection:" + connection);
		
		//read all the response header attributes/keys and print their values
		Headers headersList = response.getHeaders();
		
		//iterate over header list 
		for (Header header:headersList)
		{
			System.out.println(header.getName() + "::::" + header.getValue());
		}
		
		
		//validate header content-type , expected value : application/json; charset=utf-8
		Assert.assertEquals(contentType, "application/json; charset=utf-8","Header content type mismatch.");
		
	}
	
}
