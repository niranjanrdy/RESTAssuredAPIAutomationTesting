package com.resources;

import java.util.HashMap;

public class PayLoad {
	public static HashMap<String, String> createUserPayLoad(String name, String job){
		HashMap<String, String> createUserPayLoad= new HashMap<>();
		createUserPayLoad.put("name",name);
		createUserPayLoad.put("job", job);
		return createUserPayLoad;
	}
	public static HashMap<String, String> updateUserPayLoad(String name, String job){
		HashMap<String, String> updateUserPayLoad= new HashMap<>();
		updateUserPayLoad.put("name", name);
		updateUserPayLoad.put("job", job);
		return updateUserPayLoad;
		
	}
}