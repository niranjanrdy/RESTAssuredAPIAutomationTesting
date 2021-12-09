package com.demo.localAPI;

import static com.demo.utils.JSONFormatter.jsonPathConverter;
import static com.resources.LocalAPIPayLoad.createDataForCricketer;
import static com.resources.LocalAPIPayLoad.updateDataForCricketer;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LocalAPITest {
	@BeforeTest
	public void setUp() {
		baseURI="http://localhost:3000";
	}
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
	
	@Test(priority=1)
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
	
	@Test(priority=2)
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
	@Test(priority=4)
	public void deleteACricketer() {
		Response response = given()
				.when()
				.delete("cricketer/10")
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
