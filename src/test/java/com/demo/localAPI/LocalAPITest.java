package com.demo.localAPI;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class LocalAPITest {
	@BeforeTest
	public void setUp() {
		baseURI="http://localhost:3000";
	}
	@Test(priority=0)
	public  void getCricketer() {
		given().when().get("/cricketer").then().statusCode(200).log().all();
		}
	@Test(priority=1)
	public void addCricketerTest() {

		JSONObject req=new JSONObject();
		req.put("firstName","Joe");
		req.put("lastName","Root");
		req.put("subjectId",1);
		System.out.println(req.toJSONString());
		given().contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		post("/cricketer").
		then().
		statusCode(201).log().all();
	}
	@Test(priority=2)
	public void updateCricketerByPutTest() {

		JSONObject req=new JSONObject();
		req.put("firstName","Bhuvneshwar");
		req.put("lastName","Kumar");
		req.put("subjectId",2);
		System.out.println(req.toJSONString());
		given().contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		put("/cricketer/5").
		then().
		statusCode(200).log().all();

	}
	@Test(priority=3)
	public void updateCricketerByPatchTest() {
		JSONObject req=new JSONObject();
		req.put("firstName","Kane");
		req.put("lastName","Williamson");
		req.put("subjectId",3);
		System.out.println(req.toJSONString());
		given().contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		patch("/cricketer/2").
		then().
		statusCode(200).log().all();
	}
	@Test(priority=4)
	public void deleteACricketer() {
		when().
		delete("/cricketer/8").
		then().
		statusCode(200).log().all();
	}
	@Test(priority=5)
	public void createNewCategory() {
		JSONObject req=new JSONObject();
		req.put("name","Coach");
		System.out.println(req.toJSONString());
		given().contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		post("/category").
		then().
		statusCode(201).log().all();
	}

}
