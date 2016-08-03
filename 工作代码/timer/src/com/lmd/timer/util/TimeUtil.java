package com.lmd.timer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(getTime());
		System.out.println(getTimeDiff(dfs.parse("2016-03-25 08:40:41"), dfs.parse(getTime())));
	}
	
	public static String getTime(){
		Date date=new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	
	public static long getTimeDiff(Date begin,Date end){
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        long between = 0;
        try {
//            java.util.Date begin = dfs.parse("2009-07-10 10:22:21.214");
//            java.util.Date end = dfs.parse("2009-07-20 11:24:49.145");
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);
        System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒" + ms
                + "毫秒");
		return between;
	}
}
