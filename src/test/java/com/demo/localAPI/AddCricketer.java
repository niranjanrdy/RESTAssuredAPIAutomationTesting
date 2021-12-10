package com.demo.localAPI;

import static com.demo.utils.JSONFormatter.jsonPathConverter;
import static com.resources.LocalAPIPayLoad.createDataForCricketer;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddCricketer extends BaseTest{
	@Test(priority=2)
	public void addCricketerTest() {
		String firstName ="Rohit";
		String lastName= "Sharma";
		int categoryId =1;

		Response response = given().header("Content-Type","Application.json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(createDataForCricketer(firstName,lastName,categoryId))
				.when().post("cricketer")
				.then().extract().response();
		JsonPath jsonPath = jsonPathConverter(response);
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertNotNull(response.body());
		Assert.assertEquals(jsonPath.getString("firstName"), firstName);
		Assert.assertEquals(jsonPath.getInt("categoryId"), categoryId);
		Assert.assertEquals(jsonPath.getString("lastName"), lastName);
	}
}
