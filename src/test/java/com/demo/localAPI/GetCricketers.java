package com.demo.localAPI;

import static com.demo.utils.JSONFormatter.jsonPathConverter;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetCricketers extends BaseTest{
	@Test(priority=0)
	public  void getCricketer() {
		Response response = given().header("Content-Type","Application.json")
				.contentType(ContentType.JSON).accept(ContentType.JSON)
				.when().get("cricketer")
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id[0]"), 1);
		Assert.assertEquals(response.jsonPath().getString("firstName[0]"), "Virat");
		Assert.assertEquals(response.jsonPath().getInt("categoryId[0]"), 1);
	}
	@Test(priority =1)
	public void getCricketerById() {
		int id=2;
		Response response = given().accept(ContentType.JSON).queryParam("id", id)
				.when().get("cricketer/{id}",id)
				.then().extract().response();
		JsonPath jsonPath = jsonPathConverter(response);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(jsonPath.getInt("id"), 2);
		Assert.assertEquals(jsonPath.getString("lastName"), "Williamson");
		Assert.assertEquals(jsonPath.getInt("subjectId"), 3);
	}

}
