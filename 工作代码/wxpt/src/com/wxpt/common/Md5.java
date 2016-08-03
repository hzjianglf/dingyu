package com.wxpt.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Md5 m = new Md5();
		System.out.println(Md5.GetMd5("admin"));
/*		System.out.println(Md5.GetMd5("http://www.uniqyw.com/herostage/site/wei-xin-hero!valid"));
		http://www.uniqyw.com/cityin/site/wei-xin-handler!valid
			System.out.println(Md5.GetMd5("herostage"));*/
	}

	public static String GetMd5(String plainText) {
		plainText = plainText.trim();
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
}
