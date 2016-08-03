package com.wxpt.site.entity;

/**
 * LuckUser entity. @author MyEclipse Persistence Tools
 */

public class LuckUser implements java.io.Serializable {

	// Fields

	private Integer luckId;
	private String sendUser;
	private String addTime;
	private Integer state;
	private String sendTime;

	// Constructors

	/** default constructor */
	public LuckUser() {
	}

	/** full constructor */
	public LuckUser(String sendUser, String addTime, Integer state,
			String sendTime) {
		this.sendUser = sendUser;
		this.addTime = addTime;
		this.state = state;
		this.sendTime = sendTime;
	}

	// Property accessors

	public Integer getLuckId() {
		return this.luckId;
	}

	public void setLuckId(Integer luckId) {
		this.luckId = luckId;
	}

	public String getSendUser() {
		return this.sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

}