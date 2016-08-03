package com.handany.base.common;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationConfig {

	private static Properties config = new Properties();
	
	private static Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
	
	
	public static void init() throws Exception{
		config.load(ApplicationConfig.class.getResourceAsStream("/application.properties"));
	}
	
	public static String getConfig(String name){
		return config.getProperty(name);
	}
	
	public static int getConfigInt(String name){
		
		String value = config.getProperty(name);
		
		int num = 0;
		
		try{
			num = Integer.parseInt(value);
		}catch(Exception ex){
			logger.error("Property["+name+"] in application.properties is not an integer",ex);
			throw new RuntimeException("Property["+name+"] in application.properties is not an integer");
		}
		return num;
		
	}
	
}
