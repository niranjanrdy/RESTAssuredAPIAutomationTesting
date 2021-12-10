package com.demo.localAPI;

import static com.demo.utils.JSONFormatter.jsonPathConverter;
import static com.resources.LocalAPIPayLoad.updateDataForCricketer;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateCricketer extends BaseTest{
	@Test(priority=3)
	public void updateCricketerByPutTest() {
		String firstName ="Rohit Gurunath";
		String lastName= "Sharma";
		int categoryId =1;

		Response response = given().header("Content-Type","Application.json").contentType(ContentType.JSON).accept(ContentType.JSON).
				body(updateDataForCricketer(firstName,lastName,categoryId))
				.when().put("cricketer/9")
				.then().extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonPath = jsonPathConverter(response);		
		Assert.assertEquals(jsonPath.getString("firstName"), firstName);
		Assert.assertEquals(jsonPath.getInt("categoryId"), categoryId);
		Assert.assertEquals(jsonPath.getString("lastName"), lastName);

	}
}
