package com.whcyz.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author  Tumi
 * 日期：2012-10-9
 */
public class MD5{

	public static String MD5Encode(String strSrc, String key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(strSrc.getBytes("UTF8"));
	
			byte[] temp;
			temp = md5.digest(key.getBytes("UTF8"));
			
			return byte2hex(temp);
	
		} catch (NoSuchAlgorithmException e) {
	
			e.printStackTrace();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 二行制转字符串
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b!=null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}


	private static byte[] hex2byte(byte[] b) {
	    if((b.length%2)!=0)
	        throw new IllegalArgumentException();
		byte[] b2 = new byte[b.length/2];
		for (int n = 0; n < b.length; n+=2) {
		    String item = new String(b,n,2);
		    b2[n/2] = (byte)Integer.parseInt(item,16);
		}
	    return b2;
	}

	public static void main(String[] args) {
		DebugInfo.println(MD5Encode("123","whcyz"));
	}

}