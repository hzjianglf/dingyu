package com.handany.base.common;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

	public static SimpleDateFormat Common_Date_Format = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SimpleDateFormat Common_Time_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public static SimpleDateFormat Common_DateMinute_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static DecimalFormat moneyFormat = new DecimalFormat("##,###.00");
	
	
	/**
	 * 获取时间戳：yyyy-MM-dd HH:mm
	 * @return
	 */
	public static String getDateMinuteStr(){
		return Common_DateMinute_Format.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 获得当天日期 yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDateStr(){
		return Common_Date_Format.format(Calendar.getInstance().getTime());
	}
	
	
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获得当前时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentTimeStr(){
		return Common_Time_Format.format(Calendar.getInstance().getTime());
	}
	
	public static String formatMoney(BigDecimal decimal){
		if(decimal == null){
			return "0.00";
		}
		 return moneyFormat.format(decimal.doubleValue());
	}
	
	public static void main(String[] args){
		//System.out.println(formatMoney(new BigDecimal("11123131.34")));
		System.out.println(getCurrentTimeStr());
	}
	
	//指定对象的那些属性判断不能为空
	public static Map isNull(Object model, String... strs){		
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i = 0; i < strs.length; i ++){
			try{
				Method me = model.getClass().getMethod("get" + strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1));
				if (me.invoke(model) == null || "".equals(me.invoke(model))) {
					map.put("param", strs[i]);
					map.put("flag", false);
					return map;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		map.put("flag", true);
		return map;
	}
	
	/**
	 * 转为整型
	 * @param number
	 * @return
	 */
	public static int toInt(String number){
		int num = 0;
		try{
			num = Integer.parseInt(number);
		}catch(Exception ex){
			
		}
		
		return num;
	}
	
	public static BigDecimal toBigDecimal(String number){
		
		double num = 0.0;
		
		try{
			num = Double.parseDouble(number); 
		}catch(Exception ex){
			
		}
		
		BigDecimal decimal = new BigDecimal(num);
		
		return decimal;
	}
	
	/**
	 * 将参数追加到url
	 * @param url
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	public static String appendToUrl(String url,String paramName,String paramValue){
		String tmp = null;
		if(url.indexOf("?")>-1){
			tmp = url+"&"+paramName+"="+paramValue;
		}else{
			tmp = url+"&"+paramName+"="+paramValue;
		}
		return tmp;
	}
	
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
}
