package com.ibm.struts;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
	private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);
	
	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			InputStream ips = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(ips);
			String value = props.getProperty(key);
			
			return value;
		} catch (FileNotFoundException e) {
			
			return "";
		} catch (IOException e) {
			
			return "";
		}
	}
	
	public static void writeProperties(String filePath, String parameterName, String parameterValue) {
		
		Properties prop = new Properties();
		try {
			InputStream fis = new FileInputStream(filePath);

			prop.load(fis);
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(parameterName, parameterValue);
			prop.store(fos, "Update '" + parameterName + "' value");

		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}	
}
