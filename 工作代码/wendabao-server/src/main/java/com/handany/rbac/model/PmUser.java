package com.handany.rbac.model;

import java.io.Serializable;

import com.handany.base.model.LoginInfo;

public class PmUser implements Serializable {

	private String id;
	
	private String gender;
	
	private String mobile;

	private String loginName;
	
	private String name;
	
	private String briefName;
	
	private String password;
	
	private String status;
	
	private String lastModified;
	
	private String groupId;
	
	private String picUrl;

	private LoginInfo loginInfo;
	
	/**
	 * 用户类型
	 * 1. 普通用户
	 * 2. 教师
	 * 3. 代理商
	 * 4. 管理员
	 */
	private String userType;
	
	

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBriefName() {
		return briefName;
	}

	public void setBriefName(String briefName) {
		this.briefName = briefName;
	}

	public String getPassword() {
		return password;
	}

	

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


}