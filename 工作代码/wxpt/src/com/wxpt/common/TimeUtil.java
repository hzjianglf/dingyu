package com.wxpt.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	public static String getTime(){
		Date date=new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String currTime=dateFormat.format(date);
		return currTime;
	}
	public static String getPayTime(){
		Date date=new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMMddhhmmss");
		String currTime=dateFormat.format(date);
		return currTime;
	}
	public static String getTimes(){
		Date date=new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
		String currTime=dateFormat.format(date);
		return currTime;
	}
	public static void main(String[] args) {
		String i ="2,3,";
		System.out.println(i.substring(i.length()-1,i.length()).equals(","));
		System.out.println();
	}
	public static String getImagesTime(){
		Date date=new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMMddHHmmss");
		String currTime=dateFormat.format(date);
		return currTime;
	}
	public static String getWeixinTime(){
		Date date=new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("MM月dd日");
		String currTime=dateFormat.format(date);
		return currTime;
	}
	public static String getDay(){
		Date date=new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("dd");
		String currTime=dateFormat.format(date);
		return currTime;
	}
	public static String getYesterdayTime(){
		Date date=new Date();
		date=new Date(date.getTime()-24*60*60*1000);
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd");
		String currTime=dateFormat.format(date);
		return currTime;
	}
	public static String getDayTime(){
		Date date=new Date();
		date=new Date(date.getTime());
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd");
		String currTime=dateFormat.format(date);
		return currTime;
	}
	
	public static String getHours(){
		Date date=new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("HH");
		String currTime=dateFormat.format(date);
		return currTime;
	}
}
