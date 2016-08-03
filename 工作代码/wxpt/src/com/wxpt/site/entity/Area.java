package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */

public class Area implements java.io.Serializable {

	// Fields

	private Integer areaId;
	private String areaName;
	private String addTime;
	private String updateTime;
	private Integer userId;
	private Integer updateUser;
	private String locationX;
	private String locationY;
	private String areaNo;
	private Set customerses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** minimal constructor */
	public Area(String areaName, String addTime, String updateTime,
			Integer userId, Integer updateUser, String areaNo) {
		this.areaName = areaName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.userId = userId;
		this.updateUser = updateUser;
		this.areaNo = areaNo;
	}

	/** full constructor */
	public Area(String areaName, String addTime, String updateTime,
			Integer userId, Integer updateUser, String locationX,
			String locationY, String areaNo, Set customerses) {
		this.areaName = areaName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.userId = userId;
		this.updateUser = updateUser;
		this.locationX = locationX;
		this.locationY = locationY;
		this.areaNo = areaNo;
		this.customerses = customerses;
	}

	// Property accessors

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public String getLocationX() {
		return this.locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return this.locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getAreaNo() {
		return this.areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public Set getCustomerses() {
		return this.customerses;
	}

	public void setCustomerses(Set customerses) {
		this.customerses = customerses;
	}

}