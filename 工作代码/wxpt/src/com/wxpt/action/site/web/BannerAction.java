package com.wxpt.action.site.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.service.BannerService;

@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
@SuppressWarnings("serial")
public class BannerAction extends ActionSupport{
	@Autowired
	BannerService bannerService;
	JSONArray jsonls;
	List<EnterInfor> enterList;
	EnterInfor enter = new EnterInfor();
	String sql;
	protected PrintWriter out = null;
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	private int enterId=GetCurrentUser.getEnterId();
	public String getBanner(){
		sql = "SELECT * FROM webchat.enter_infor WHERE `enter_infor_id`="+enterId;
		enterList = new ArrayList<EnterInfor>();
		enter = new EnterInfor();
		try {
			enter =  bannerService.getBanner(sql).get(0);
		} catch (Exception e1) {
			enter = null;
		}
		try {
			out = response.getWriter();
		} catch (IOException e) {

		}
		JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.setExcludes(new String[] { "userTemplates" });
		jsonls = JSONArray.fromObject(enter,jsonConfig);
		System.out.println(jsonls);
		out.print(jsonls);
		out.flush();
		out.close();
	return "success";
	}
}
