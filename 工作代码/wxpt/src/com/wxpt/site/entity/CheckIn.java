package com.wxpt.site.entity;

/**
 * CheckIn entity. @author MyEclipse Persistence Tools
 */

public class CheckIn implements java.io.Serializable {

	// Fields

	private Integer checkId;
	private String checkuser;
	private String checTime;
	private String checkNum;
	private String score;
	private Integer count;

	// Constructors

	/** default constructor */
	public CheckIn() {
	}

	/** full constructor */
	public CheckIn(String checkuser, String checTime, String checkNum,
			String score, Integer count) {
		this.checkuser = checkuser;
		this.checTime = checTime;
		this.checkNum = checkNum;
		this.score = score;
		this.count = count;
	}

	// Property accessors

	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public String getCheckuser() {
		return this.checkuser;
	}

	public void setCheckuser(String checkuser) {
		this.checkuser = checkuser;
	}

	public String getChecTime() {
		return this.checTime;
	}

	public void setChecTime(String checTime) {
		this.checTime = checTime;
	}

	public String getCheckNum() {
		return this.checkNum;
	}

	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}