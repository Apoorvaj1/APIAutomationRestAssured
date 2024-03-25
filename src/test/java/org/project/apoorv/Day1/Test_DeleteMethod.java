package org.project.apoorv.Day1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Test_DeleteMethod {

	@Test
	public void test01()
	{
		RestAssured.baseURI="https://reqres.in/api/users/52";
		RestAssured.given().
		when().
			delete().
		then().
			log().all().
			statusCode(204);
	}
			
}
