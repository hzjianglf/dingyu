package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AnswerRecords entity. @author MyEclipse Persistence Tools
 */

public class AnswerRecords implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private String answerUser;
	private String answerTime;
	private Integer type;
	private Set luckanwers = new HashSet(0);

	// Constructors

	/** default constructor */
	public AnswerRecords() {
	}

	/** minimal constructor */
	public AnswerRecords(String answerUser, String answerTime, Integer type) {
		this.answerUser = answerUser;
		this.answerTime = answerTime;
		this.type = type;
	}

	/** full constructor */
	public AnswerRecords(String answerUser, String answerTime, Integer type,
			Set luckanwers) {
		this.answerUser = answerUser;
		this.answerTime = answerTime;
		this.type = type;
		this.luckanwers = luckanwers;
	}

	// Property accessors

	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getAnswerUser() {
		return this.answerUser;
	}

	public void setAnswerUser(String answerUser) {
		this.answerUser = answerUser;
	}

	public String getAnswerTime() {
		return this.answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Set getLuckanwers() {
		return this.luckanwers;
	}

	public void setLuckanwers(Set luckanwers) {
		this.luckanwers = luckanwers;
	}

}