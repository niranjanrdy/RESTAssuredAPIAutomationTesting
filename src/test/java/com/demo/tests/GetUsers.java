package com.demo.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUsers extends BaseTest {	
	@Test(priority=0)
	public void getAllUsersTest() {
		int pageNumber =1;
		Response response =  given().accept(ContentType.JSON).queryParam("page",pageNumber)
				.when().get("users")
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("page"), 1);
		Assert.assertEquals(response.jsonPath().getInt("per_page"), 6);
		Assert.assertEquals(response.jsonPath().getInt("total"), 12);
		Assert.assertEquals(response.jsonPath().getInt("total_pages"),2);
		Assert.assertTrue(response.jsonPath().getString("data.email").contains("@rees.in"));
		Assert.assertTrue(response.jsonPath().getString("support.url").contains("reqres.in") );
	}
	@Test(priority=1)
	public void getUserByIDTest() {
		int id =1;
		Response response = given().accept(ContentType.JSON)
				.when().get("users/"+id)
				.then().extract().response();
		Assert.assertEquals(response.statusCode(),200);
		Assert.assertEquals(response.jsonPath().getInt("data.id"),1);
		Assert.assertEquals(response.jsonPath().getString("data.email"),"george.bluth@reqres.in");
		Assert.assertEquals(response.jsonPath().getString("data.first_name"), "George");
		Assert.assertEquals(response.jsonPath().getString("data.last_name"), "Bluth");
	}
	

}
