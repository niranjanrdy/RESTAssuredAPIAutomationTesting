package com.demo.localAPI;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeTest;

public class BaseTest {
	@BeforeTest
	public void setUp() {
		baseURI="http://localhost:3000";
	}
}
