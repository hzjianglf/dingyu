package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userNum;
	private String userName;
	private Set userCounts = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userNum, String userName) {
		this.userNum = userNum;
		this.userName = userName;
	}

	/** full constructor */
	public User(String userNum, String userName, Set userCounts) {
		this.userNum = userNum;
		this.userName = userName;
		this.userCounts = userCounts;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set getUserCounts() {
		return this.userCounts;
	}

	public void setUserCounts(Set userCounts) {
		this.userCounts = userCounts;
	}

}