package com.wxpt.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


public class GetCurrentUser {
	public static String getUserName() {
		String userName="";
		String cookie = GetCurrentUser.getCookieValue("wxpts");
		try {
			String values[] = cookie.split(":");
			userName = values[1];
		} catch (Exception e) {
			
		}
		return userName;
	}
	public static int getUserID() {
		int userId=0;
		String cookie = GetCurrentUser.getCookieValue("wxpts");
		try {
			String values[] = cookie.split(":");
			System.out.println(values[0]);
			String id=values[1];
			userId = Integer.parseInt(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;
	}
	public static int getEnterId(){

		int enterId =0;
		String cookie = GetCurrentUser.getCookieValue("wxpts");
		try {
			String values[] = cookie.split(":");
			enterId = Integer.parseInt(values[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enterId;
	
	}
	
	/*public static String getMemberName() {
		String userName="admin";
		String cookie = GetCurrentUser.getCookieValue("memberCookie");
		try {
			String values[] = cookie.split(":");
			userName = values[1];
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(userName);
		return userName;
	}
	public static int getMemberID() {
		int memberId=70;
		String cookie;
		try {
			cookie = GetCurrentUser.getCookieValue("memberCookie");
			String values[] = cookie.split(":");
			memberId = Integer.parseInt(values[0]);
			memberId=100;
		} catch (Exception e) {
			cookie = "0";
		}
		System.out.println(memberId);
		return memberId;
	}*/
	public static String getCookieValue(String cookieName) {
		String cookievalue = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookieValue = request.getCookies();
		if(cookieValue != null){
			
			Cookie c = null;
			for (int i = 0; i < cookieValue.length; i++) {
				c = cookieValue[i];
				System.out.println(c.getName());
				if (c.getName().equals(cookieName)) {
					
					cookievalue = c.getValue();
				}
			}
		}else{
			return null;
		}
		return cookievalue;
	}
}
