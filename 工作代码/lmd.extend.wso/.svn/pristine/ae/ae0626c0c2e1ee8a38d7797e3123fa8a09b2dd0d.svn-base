package lmd.extend.wso.util;

import java.util.Date;

public class DateUtil {
	
	public static void main(String[] args) {
		Date endDate = new Date();
		Date nowDate = new Date();
		System.out.println(getDatePoor(endDate, nowDate));
	}
	public static String getDatePoor(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long diff = endDate.getTime() - nowDate.getTime();
	    long day = diff / nd;
	    long hour = diff % nd / nh;
	    long min = diff % nd % nh / nm;
	    String timestr=day + "day(s) " + hour + "hour(s) " + min + "min(s)";
	    return timestr;
	}
}
