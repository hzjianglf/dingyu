package com.wxpt.site.entity;

/**
 * UserCount entity. @author MyEclipse Persistence Tools
 */

public class UserCount implements java.io.Serializable {

	// Fields

	private Integer countId;
	private User user;
	private String sendUser;
	private String sendTime;

	// Constructors

	/** default constructor */
	public UserCount() {
	}

	/** full constructor */
	public UserCount(User user, String sendUser, String sendTime) {
		this.user = user;
		this.sendUser = sendUser;
		this.sendTime = sendTime;
	}

	// Property accessors

	public Integer getCountId() {
		return this.countId;
	}

	public void setCountId(Integer countId) {
		this.countId = countId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSendUser() {
		return this.sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

}