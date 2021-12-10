package com.demo.localAPI;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DeleteCricketer extends BaseTest {

	@Test(priority=4)
	public void deleteACricketer() {
		Response response = given()
				.when()
				.delete("cricketer/10")
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
