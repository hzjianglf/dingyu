package com.ticket.master.entity;

import java.sql.Timestamp;

/**
 * OperateLog entity. @author MyEclipse Persistence Tools
 */

public class OperateLog implements java.io.Serializable {

	// Fields

	private Integer operateLogId;
	private String operateIp;
	private String userName;
	private String operatingData;
	private Timestamp operatingTime;
	private Integer logType;
	private Integer userParentId;

	// Constructors

	/** default constructor */
	public OperateLog() {
	}

	/** minimal constructor */
	public OperateLog(String userName, Integer logType, Integer userParentId) {
		this.userName = userName;
		this.logType = logType;
		this.userParentId = userParentId;
	}

	/** full constructor */
	public OperateLog(String operateIp, String userName, String operatingData,
			Timestamp operatingTime, Integer logType, Integer userParentId) {
		this.operateIp = operateIp;
		this.userName = userName;
		this.operatingData = operatingData;
		this.operatingTime = operatingTime;
		this.logType = logType;
		this.userParentId = userParentId;
	}

	// Property accessors

	public Integer getOperateLogId() {
		return this.operateLogId;
	}

	public void setOperateLogId(Integer operateLogId) {
		this.operateLogId = operateLogId;
	}

	public String getOperateIp() {
		return this.operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperatingData() {
		return this.operatingData;
	}

	public void setOperatingData(String operatingData) {
		this.operatingData = operatingData;
	}

	public Timestamp getOperatingTime() {
		return this.operatingTime;
	}

	public void setOperatingTime(Timestamp operatingTime) {
		this.operatingTime = operatingTime;
	}

	public Integer getLogType() {
		return this.logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Integer getUserParentId() {
		return this.userParentId;
	}

	public void setUserParentId(Integer userParentId) {
		this.userParentId = userParentId;
	}

}