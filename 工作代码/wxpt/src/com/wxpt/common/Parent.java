package com.wxpt.common;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.MoveService;
import com.wxpt.site.service.UserService;
import com.wxpt.site.service.WebSiteService;
public class Parent extends TagSupport{

	public static MoveService getMoveService(){
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		MoveService moveService=(MoveService) ctx.getBean("moveService");
		return moveService;
	} 
	
	public static EnterService getEnterService(){
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		EnterService enterService=(EnterService) ctx.getBean("enterService");
		return enterService;
	}
	
	public static WebSiteService getWebSiteService(){
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		WebSiteService webSiteService=(WebSiteService) ctx.getBean("webSiteService");
		return webSiteService;
	}
	public static UserService getUserService(){
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) ctx.getBean("userService");
		return userService;
	} 
}
