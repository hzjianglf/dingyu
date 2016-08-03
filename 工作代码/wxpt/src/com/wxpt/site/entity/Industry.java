package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Industry entity. @author MyEclipse Persistence Tools
 */

public class Industry implements java.io.Serializable {

	// Fields

	private Integer industryId;
	private String industryName;
	private String addTime;
	private String updateTime;
	private Integer userId;
	private Integer updateUser;
	private String industryNo;
	private Set customerses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Industry() {
	}

	/** minimal constructor */
	public Industry(String industryName, String addTime, String updateTime,
			Integer userId, String industryNo) {
		this.industryName = industryName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.userId = userId;
		this.industryNo = industryNo;
	}

	/** full constructor */
	public Industry(String industryName, String addTime, String updateTime,
			Integer userId, Integer updateUser, String industryNo,
			Set customerses) {
		this.industryName = industryName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.userId = userId;
		this.updateUser = updateUser;
		this.industryNo = industryNo;
		this.customerses = customerses;
	}

	// Property accessors

	public Integer getIndustryId() {
		return this.industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getIndustryName() {
		return this.industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
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

	public String getIndustryNo() {
		return this.industryNo;
	}

	public void setIndustryNo(String industryNo) {
		this.industryNo = industryNo;
	}

	public Set getCustomerses() {
		return this.customerses;
	}

	public void setCustomerses(Set customerses) {
		this.customerses = customerses;
	}

}