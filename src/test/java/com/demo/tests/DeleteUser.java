package com.demo.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DeleteUser {
	@Test(priority=5)
	public void deleteAUser() {
		BaseTest.createTest("delete user test", "Regression");
		Response response = given()
							.when().delete("users/2")
							.then().extract().response();
		
		Assert.assertEquals(response.statusCode(), 204);	
	}
}
