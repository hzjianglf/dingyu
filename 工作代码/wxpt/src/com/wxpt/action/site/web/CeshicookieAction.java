package com.wxpt.action.site.web;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
@Results({
	@Result(name = "ceshi", location = "/siteManage/ceshi.jsp")
	})
public class CeshicookieAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Cookie memberCookie = new Cookie("memberCookie","1");
		memberCookie.setMaxAge(-1);
		memberCookie.setPath("/");
		ServletActionContext.getResponse().addCookie(memberCookie);
		
		return super.execute();
	}

}
