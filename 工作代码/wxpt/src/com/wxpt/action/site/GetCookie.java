package com.wxpt.action.site;

import javax.servlet.http.Cookie;

@SuppressWarnings("serial")
public class GetCookie extends ParentAction{
	Cookie c = null;
	
	public int getCookie(){
		Cookie[] cookies = request.getCookies();
		for(int i=0;i<cookies.length;i++){
			c = cookies[i];
			if (c.getName().equals("wxpts")) {
				String[] cString = c.getValue().split(":");
				String name=cString[0];
				String enter=cString[2];
				int enterId=Integer.parseInt(enter);
				return enterId;
			}
			
		}
		return 0;
		
	}
	
	public String getCookieName(){
		Cookie[] cookies = request.getCookies();
		String name="";
		for(int i=0;i<cookies.length;i++){
			c = cookies[i];
			if (c.getName().equals("wxpts")) {
				String[] cString = c.getValue().split(":");
				 name=cString[0];							 
				return name;
			}
						
		}
		return name;
	}
	
	public String getCookieValue(String name){
		Cookie[] cookies = request.getCookies();
		for(int i=0;i<cookies.length;i++){
			c = cookies[i];
			if (c.getName().equals(name)) {
				return c.getValue();
			}
						
		}
		return null;
	}
}
