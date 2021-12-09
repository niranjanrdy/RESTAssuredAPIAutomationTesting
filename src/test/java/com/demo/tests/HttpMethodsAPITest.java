package com.demo.tests;

import static com.demo.utils.JSONFormatter.jsonPathConverter;
import static com.resources.PayLoad.createUserPayLoad;
import static com.resources.PayLoad.updateUserPayLoad;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HttpMethodsAPITest extends MainURL {
	@Test(priority=2)
	public void createUserTest() {
		String name ="Chetan";
		String job ="Tech Manager";
		
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(createUserPayLoad(name,job))
				            .when().post("/users")
				            .then().log().all().extract().response();
	    JsonPath jsonPath =	jsonPathConverter(response);
		Assert.assertEquals(response.statusCode(),201);
		Assert.assertEquals(jsonPath.getString("name"), name);
		Assert.assertEquals(jsonPath.getString("job"), job);
		Assert.assertNotNull(response.body());
	}
	@Test(priority=0)
	public void getAllUsersTest() {
		int pageNumber =1;
		Response response =  given().accept(ContentType.JSON).queryParam("page",pageNumber)
				.when().get("users")
				.then().log().all().extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("page"), 1);
		Assert.assertEquals(response.jsonPath().getInt("per_page"), 6);
		Assert.assertEquals(response.jsonPath().getInt("total"), 12);
		Assert.assertEquals(response.jsonPath().getInt("total_pages"),2);
		Assert.assertTrue(response.jsonPath().getString("data.email").contains("@reqres.in"));
		Assert.assertTrue(response.jsonPath().getString("support.url").contains("reqres.in") );
	}
	@Test(priority=1)
	public void getUserByIDTest() {
		int id =1;
		Response response = given().accept(ContentType.JSON)
				.when().get("users/"+id)
				.then().log().all().extract().response();
	 
		Assert.assertEquals(response.statusCode(),200);
		Assert.assertEquals(response.jsonPath().getInt("data.id"),1);
		Assert.assertEquals(response.jsonPath().getString("data.email"),"george.bluth@reqres.in");
		Assert.assertEquals(response.jsonPath().getString("data.first_name"), "George");
		Assert.assertEquals(response.jsonPath().getString("data.last_name"), "Bluth");
	}
	@Test(priority=3)
	public void updateUserTestByPutMethod() {
		String name = "Otis Milburn";
		String job = "Therapist";
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(updateUserPayLoad(name,job))
				.when().put("/users/2")
				.then().log().all().extract().response();
		
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
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(updateUserPayLoad(name,job))
				.when().put("/users/1")
				.then().log().all().extract().response();
		
		Assert.assertEquals(response.statusCode(), 200);
		JsonPath jsonPath =jsonPathConverter(response);
		Assert.assertEquals(jsonPath.getString("name"), name);
		Assert.assertEquals(jsonPath.getString("job"), job);
		Assert.assertNotNull(response.body());
	}
	@Test(priority=5)
	public void deleteAUser() {
		
		Response response = given()
							.when().delete("users/2")
							.then().log().all().extract().response();
		
		Assert.assertEquals(response.statusCode(), 204);
		
	}

}
