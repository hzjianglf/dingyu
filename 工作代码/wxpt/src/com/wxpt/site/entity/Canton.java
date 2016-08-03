package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Canton entity. @author MyEclipse Persistence Tools
 */

public class Canton implements java.io.Serializable {

	// Fields

	private Integer cantonId;
	private String cantonName;
	private String addTime;
	private String updateTime;
	private Integer userId;
	private Integer updateUser;
	private String cantonNo;
	private Set customerses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Canton() {
	}

	/** minimal constructor */
	public Canton(String cantonName, String addTime, String updateTime,
			Integer userId, String cantonNo) {
		this.cantonName = cantonName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.userId = userId;
		this.cantonNo = cantonNo;
	}

	/** full constructor */
	public Canton(String cantonName, String addTime, String updateTime,
			Integer userId, Integer updateUser, String cantonNo, Set customerses) {
		this.cantonName = cantonName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.userId = userId;
		this.updateUser = updateUser;
		this.cantonNo = cantonNo;
		this.customerses = customerses;
	}

	// Property accessors

	public Integer getCantonId() {
		return this.cantonId;
	}

	public void setCantonId(Integer cantonId) {
		this.cantonId = cantonId;
	}

	public String getCantonName() {
		return this.cantonName;
	}

	public void setCantonName(String cantonName) {
		this.cantonName = cantonName;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public String getCantonNo() {
		return this.cantonNo;
	}

	public void setCantonNo(String cantonNo) {
		this.cantonNo = cantonNo;
	}

	public Set getCustomerses() {
		return this.customerses;
	}

	public void setCustomerses(Set customerses) {
		this.customerses = customerses;
	}

}