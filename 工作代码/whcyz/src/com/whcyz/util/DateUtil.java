package com.whcyz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String YMD = "yyyy-MM-dd";
    public static final String YMDHMS = "yyyy-MM-dd HH-mm-ss";

    public static SimpleDateFormat sdf = new SimpleDateFormat();

    public static String format(Date date , String pattern) {
    	if(date==null){
    		return "";
    	}
    	sdf.applyPattern(pattern);
    	return sdf.format(date);
    	
    }
    public static String format(Date date) {
        if(date==null){
            return "";
        }
        sdf.applyPattern(YMDHMS);
        return sdf.format(date);
        
    }
}
