package com.wxpt.action;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@Result(name = "success", type = "redirectAction",
		params = {
			"actionName", "manage",
			"namespace", "/site",
			"method", "manage" })
public class IndexAction extends ActionSupport {
	
	public String execute() {
		
		return SUCCESS;
	}
	

}
