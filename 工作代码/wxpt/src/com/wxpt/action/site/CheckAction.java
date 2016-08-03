package com.wxpt.action.site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.Md5;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.User;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.UserService;


@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
public class CheckAction extends ActionSupport implements SessionAware {
	/**
	 * author:张莹 date:13.3.12 ajax局部刷新验证码的验证
	 */
	private String yzm;// 输入的验证码
	private String result;
	@Autowired
	UserService userService;
	EnterService enterService;
	EnterInfor enter;
	private String uname;
	private String pwd;
	private String jsonid;
	private int enterId;
	private User user;
	private String cookie;// 验证浏览器是否支持cookie或浏览器是否把cookie以关闭的问题
	private Map<String, Object> session;
	private Map<String, String> cookies;
	private ManageUser manageUser;
	List<EnterInfor> enterList=new ArrayList<EnterInfor>();
	public void out() {
		Cookie wxpts = new Cookie("wxpts", "");
		wxpts.setMaxAge(-1);
		wxpts.setPath("/");
		ServletActionContext.getResponse().addCookie(wxpts);
		System.out.println(wxpts.getValue());
		try {
			ServletActionContext.getResponse().sendRedirect("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Action(value = "/register/check", results = { @Result(name = "success", type = "json", params = {
			"root", "result" }) })
	public String checkYan() {
		try {
			// 获取刷新的验证码
			String rand = (String) ServletActionContext.getRequest()
					.getSession().getAttribute("rand");
			if (yzm.equals(rand)) {
				try {
					manageUser = userService.checkLogin(enterId,uname, Md5.GetMd5(pwd));
					if (manageUser != null) {
						if (cookie.equals("true")) {
							if (manageUser.getStatus()==0) {
								Cookie wxpts = new Cookie("wxpts",
										manageUser.getManageUserName() + ":"
												+ manageUser.getEnterid()+":"+enterId);
								wxpts.setMaxAge(60*60*24*365);
								//wxpts.setMaxAge(Integer.MAX_VALUE);
								wxpts.setPath("/");
								ServletActionContext.getResponse().addCookie(
										wxpts);
								session.remove("rand");
								enter = enterService.getById(enterId);
								String qyId=enter.getChilendQyId();
								if(qyId==null||qyId.equals("")||qyId.equals("null")){
									result = "1";
								}else{
									result = "4";
								}
								
							} else{
								result="tiyan";
							}
						}else{
							result="cookie";
						}

					} else{
						result = "login_error";
					}
					
				} catch (Exception e) {
					result = "login_error";
				}
			} else {
				result = "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
public String  getChile(){
	enter = enterService.getById(enterId);
	String qyIds=enter.getChilendQyId();
	if(qyIds==null||qyIds.equals("")||qyIds.equals("null")){
		
	}else{
		String[] qy = qyIds.split(";");
		for (int i = 0; i < qy.length; i++) {
			enter = enterService.getById2(Integer.parseInt(qy[i]));
			enterList.add(enter);
		}
		enter = enterService.getById(enterId);
		enterList.add(enter);
	}
	return "chile";
}
	
	
	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public String getJsonid() {
		return jsonid;
	}

	public void setJsonid(String jsonid) {
		this.jsonid = jsonid;
	}

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

	public List<EnterInfor> getEnterList() {
		return enterList;
	}

	public void setEnterList(List<EnterInfor> enterList) {
		this.enterList = enterList;
	}

	public EnterService getEnterService() {
		return enterService;
	}

	public void setEnterService(EnterService enterService) {
		this.enterService = enterService;
	}

	public EnterInfor getEnter() {
		return enter;
	}

	public void setEnter(EnterInfor enter) {
		this.enter = enter;
	}

}
