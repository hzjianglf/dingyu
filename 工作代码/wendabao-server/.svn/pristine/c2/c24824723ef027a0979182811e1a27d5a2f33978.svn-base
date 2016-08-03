package com.handany.base.common;

public class WriteJsonContainer {

	
	private static ThreadLocal<JsonObject> container = new ThreadLocal<JsonObject>();
	
	

	public static JsonObject get(){
		return container.get();
	}

	public static void set(JsonObject jsonObject){
		 container.set(jsonObject);
	}
	
	public static void release(){
		container.remove();
	}
}
