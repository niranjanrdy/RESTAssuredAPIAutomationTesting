package com.demo.tests;

import static com.demo.utils.JSONFormatter.jsonPathConverter;
import static com.resources.PayLoad.createUserPayLoad;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUser {
	@Test(priority=2)
	public void createUserTest() {
		String name ="Chetan";
		String job ="Tech Manager";
		
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
							.body(createUserPayLoad(name,job))
				            .when().post("/users")
				            .then().extract().response();
	    JsonPath jsonPath =	jsonPathConverter(response);
		Assert.assertEquals(response.statusCode(),201);
		Assert.assertEquals(jsonPath.getString("name"), name);
		Assert.assertEquals(jsonPath.getString("job"), job);
		Assert.assertNotNull(response.body());
	}
}
