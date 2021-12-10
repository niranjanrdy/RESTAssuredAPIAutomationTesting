package com.resources;


import java.util.HashMap;
import java.util.Map;

public class LocalAPIPayLoad {
	
	public static Map<String,Object> createDataForCricketer(String firstName, String lastName, int categoryId ) {
		Map<String,Object> data = new HashMap<>();
		data.put("firstName",firstName);
		data.put("lastName", lastName);
		data.put("categoryId", categoryId);
		
		return data;
	}
	
	
	public static Map<String,Object> updateDataForCricketer(String firstName, String lastName, int categoryId) {
		Map<String,Object> updateData = new HashMap<>();
		updateData.put("firstName",firstName);
		updateData.put("lastName", lastName);
		updateData.put("categoryId", categoryId);
		
		return updateData;
	}
	
	public static Map<String,Object> createDataForCategory(String name) {
		Map<String,Object> catData = new HashMap<>();
		catData.put("name",name);
		return catData;
	}
	
	public static Map<String,Object> updateDataForcategory(String name) {
		Map<String,Object> catUpdateData = new HashMap<>();
		catUpdateData.put("name",name);
		return catUpdateData;
	}
	
}