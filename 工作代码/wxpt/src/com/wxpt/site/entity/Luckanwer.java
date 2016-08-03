package com.wxpt.site.entity;

/**
 * Luckanwer entity. @author MyEclipse Persistence Tools
 */

public class Luckanwer implements java.io.Serializable {

	// Fields

	private Integer luckanswerid;
	private AnswerRecords answerRecords;
	private String addTime;
	private Integer state;
	private String updateTime;

	// Constructors

	/** default constructor */
	public Luckanwer() {
	}

	/** minimal constructor */
	public Luckanwer(AnswerRecords answerRecords, String addTime, Integer state) {
		this.answerRecords = answerRecords;
		this.addTime = addTime;
		this.state = state;
	}

	/** full constructor */
	public Luckanwer(AnswerRecords answerRecords, String addTime,
			Integer state, String updateTime) {
		this.answerRecords = answerRecords;
		this.addTime = addTime;
		this.state = state;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Integer getLuckanswerid() {
		return this.luckanswerid;
	}

	public void setLuckanswerid(Integer luckanswerid) {
		this.luckanswerid = luckanswerid;
	}

	public AnswerRecords getAnswerRecords() {
		return this.answerRecords;
	}

	public void setAnswerRecords(AnswerRecords answerRecords) {
		this.answerRecords = answerRecords;
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

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}