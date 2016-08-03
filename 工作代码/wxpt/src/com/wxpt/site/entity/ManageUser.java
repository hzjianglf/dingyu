package com.wxpt.site.entity;

/**
 * ManageUser entity. @author MyEclipse Persistence Tools
 */

public class ManageUser implements java.io.Serializable {

	// Fields

	private Integer manageUserId;
	private String manageUserName;
	private String passwrod;
	private Integer userType;
	private String registerTime;
	private Integer userParentId;
	private String roleList;
	private Integer status;
	private Integer enterid;

	// Constructors

	/** default constructor */
	public ManageUser() {
	}

	/** full constructor */
	public ManageUser(String manageUserName, String passwrod, Integer userType,
			String registerTime, Integer userParentId, String roleList,
			Integer status, Integer enterid) {
		this.manageUserName = manageUserName;
		this.passwrod = passwrod;
		this.userType = userType;
		this.registerTime = registerTime;
		this.userParentId = userParentId;
		this.roleList = roleList;
		this.status = status;
		this.enterid = enterid;
	}

	// Property accessors

	public Integer getManageUserId() {
		return this.manageUserId;
	}

	public void setManageUserId(Integer manageUserId) {
		this.manageUserId = manageUserId;
	}

	public String getManageUserName() {
		return this.manageUserName;
	}

	public void setManageUserName(String manageUserName) {
		this.manageUserName = manageUserName;
	}

	public String getPasswrod() {
		return this.passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getUserParentId() {
		return this.userParentId;
	}

	public void setUserParentId(Integer userParentId) {
		this.userParentId = userParentId;
	}

	public String getRoleList() {
		return this.roleList;
	}

	public void setRoleList(String roleList) {
		this.roleList = roleList;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getEnterid() {
		return this.enterid;
	}

	public void setEnterid(Integer enterid) {
		this.enterid = enterid;
	}

}