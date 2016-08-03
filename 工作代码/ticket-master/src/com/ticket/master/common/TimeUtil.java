package com.ticket.master.common;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	// 转换成Date形式
	public static Date getTime(String Time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(Time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	// 转换成datetime形式
	public static Timestamp getOperateTime(String operatingTime) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(operatingTime);
			System.out.println(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	// datetime转换成string形式

	public static String getDateTime(Timestamp operatingTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = "";
		try {
			// 方法一
			time = sdf.format(operatingTime);
			System.out.println(time);
			/*
			 * //方法二 time = ts.toString(); System.out.println(time);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public static String getDateTime2(Timestamp operatingTime) {

		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		String time = "";
		try {
			// 方法一
			time = sdf.format(operatingTime);
			System.out.println(time);
			/*
			 * //方法二 time = ts.toString(); System.out.println(time);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 获取系统时间转换成datetime类型
	 * 
	 * @return
	 */
	public static Timestamp getSysTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currTime = dateFormat.format(date);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(currTime);
			System.out.println(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * 获取系统时间转换成datetime类型
	 * 
	 * @return
	 */
	public static Time getSysTimes() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String currTime = dateFormat.format(date);
		Time ts = new Time(System.currentTimeMillis());
		try {
			ts = Time.valueOf(currTime);
			System.out.println(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	public static Time getSysTimes(String time) {
		Time ts = new Time(System.currentTimeMillis());
		try {
			ts = Time.valueOf(time);
			System.out.println(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * 获取系统时间转换成date类型
	 * 
	 * @return
	 */
	public static Date getSysDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currTime = sdf.format(date);
		try {
			date = sdf.parse(currTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String getYear() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String getOrderTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String getjifenSystime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String verifybirthday() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String getPayTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String getPayTime2() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String getPayTime2s() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String getTimes(Date time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currTime = dateFormat.format(time);
		return currTime;
	}

	/*
	 * public static void main(String[] args) { String i ="2,3,";
	 * System.out.println(i.substring(i.length()-1,i.length()).equals(","));
	 * System.out.println(); }
	 */
	public static String getImagesTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String getWeixinTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static String getDay() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		String currTime = dateFormat.format(date);
		return currTime;
	}

	public static boolean getResult(String dateTime, String startTime,
			String endTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt = df.parse(dateTime);
			Date dtStart = df.parse(startTime);
			Date dtEnd = df.parse(endTime);
			if (dt.getTime() >= dtStart.getTime() && dt.getTime()<=dtEnd.getTime()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return false;
	}
}
