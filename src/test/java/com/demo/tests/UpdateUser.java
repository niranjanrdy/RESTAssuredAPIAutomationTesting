package com.demo.tests;

import static com.demo.utils.JSONFormatter.jsonPathConverter;
import static com.resources.PayLoad.updateUserPayLoad;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateUser {
	@Test(priority=3)
	public void updateUserTestByPutMethod() {
		String name = "Otis Milburn";
		String job = "Therapist";
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(updateUserPayLoad(name,job))
				.when().put("/users/2")
				.then().extract().response();
		
		Assert.assertEquals(response.statusCode(), 200);
	    JsonPath jsonPath =jsonPathConverter(response);
		Assert.assertNotNull(response.body());
		Assert.assertEquals(jsonPath.getString("job"), job);
		Assert.assertEquals(jsonPath.getString("name"), name);
	}
	
	@Test(priority=4)
	public void updateUserTestByPatchMethod() {
		String name = "Kane";
		String job = "Batter";
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(updateUserPayLoad(name,job))
				.when().put("/users/1")
				.then().extract().response();
		
		Assert.assertEquals(response.statusCode(), 200);
		JsonPath jsonPath =jsonPathConverter(response);
		Assert.assertEquals(jsonPath.getString("name"), name);
		Assert.assertEquals(jsonPath.getString("job"), job);
		Assert.assertNotNull(response.body());
	}
}
