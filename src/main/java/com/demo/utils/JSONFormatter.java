package com.demo.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JSONFormatter {

	public static JsonPath jsonPathConverter(Response response) {
		return new JsonPath(response.asString());
	}
}
