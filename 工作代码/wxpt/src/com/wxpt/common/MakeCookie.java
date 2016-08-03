package com.wxpt.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;


public class MakeCookie {
	public static void saveCookie(String name,String value){
		Cookie c = new Cookie(name,value);
		c.setMaxAge(-1);
		ServletActionContext.getResponse().addCookie(c);
	}
	public static String getCookie(String name){
		String value = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		/*for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			if(c.getName().equals(name)){
				value = c.getValue();
			}
		}*/
		return cookies.length+"";
	}
	
	
}
