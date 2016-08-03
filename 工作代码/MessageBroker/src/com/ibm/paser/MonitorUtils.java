package com.ibm.paser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class MonitorUtils {

	public static String readValue(String filePath, String key) {

		Properties props = new Properties();

		try {
			InputStream ips = new BufferedInputStream(new FileInputStream(filePath));
			props.load(ips);
			String value = props.getProperty(key);

			return value;
		} catch (FileNotFoundException e1) {			
			e1.printStackTrace();
			return null;

		} catch (IOException e2) {
			e2.printStackTrace();
			return null;
			
		}
	}
	
	public static String getLocalTime(String strTime) {
		if(strTime.length() < 23)
		{
			strTime = strTime.substring(0, 19) + ".000Z";
		}
		
		strTime = strTime.substring(0, 23).replace('T', ' ');		
		//System.out.println(strTime);
		
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = null;
		try {
			date = sfd.parse(strTime);
			   
		    now.setTime(date);   
		    now.set(Calendar.HOUR, now.get(Calendar.HOUR) + 8);
		    
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}	
	    return sfd.format(now.getTime());   
		
	}
	
	public static Long getFlowProcessedTime(String startTime, String endTime) {		
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date start = null;
		Date end = null;
		
		try {
			start = sfd.parse(startTime);
			end = sfd.parse(endTime);
			
			return (Long)(end.getTime() - start.getTime());
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return null;
		}
		
		
	}
}
