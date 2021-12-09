package com.demo.tests;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeTest;

public class MainURL {
	@BeforeTest
	public void setUp() {
		baseURI = "https://reqres.in/api";
	}
}