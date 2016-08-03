package com.wxpt.common;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

public class SavaCookie {
	public static void savaCookie(int memberId,String memberName,int enterId){
		Cookie memberCookie = new Cookie("wxpts",memberId+":"+memberName+":"+enterId);
		//memberCookie.setMaxAge(-1);
		memberCookie.setMaxAge(60*60*24*365);
		memberCookie.setPath("/");
		ServletActionContext.getResponse().addCookie(memberCookie);
	} 
}
