package com.handany.base.util;

import java.util.Random;

public class VerifyCode {

	private static Random random = new Random();
	
	private static String CODE_FORMAT = "999999";
	
	/**
	 * 返回6位随机验证码
	 * @return
	 */
	public static String nextRandom(){
		
		String code=  random.nextInt(1000000)+CODE_FORMAT;
		return code.substring(0, 6);
		
	}
	
}
