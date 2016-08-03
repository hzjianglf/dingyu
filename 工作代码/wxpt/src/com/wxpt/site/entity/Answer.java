package com.wxpt.site.entity;

/**
 * Answer entity. @author MyEclipse Persistence Tools
 */

public class Answer implements java.io.Serializable {

	// Fields

	private Integer answerid;
	private Question question;
	private String sendUser;
	private String answertime;
	private String sendAnswer;
	private Integer state;

	// Constructors

	/** default constructor */
	public Answer() {
	}

	/** full constructor */
	public Answer(Question question, String sendUser, String answertime,
			String sendAnswer, Integer state) {
		this.question = question;
		this.sendUser = sendUser;
		this.answertime = answertime;
		this.sendAnswer = sendAnswer;
		this.state = state;
	}

	// Property accessors

	public Integer getAnswerid() {
		return this.answerid;
	}

	public void setAnswerid(Integer answerid) {
		this.answerid = answerid;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getSendUser() {
		return this.sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getAnswertime() {
		return this.answertime;
	}

	public void setAnswertime(String answertime) {
		this.answertime = answertime;
	}

	public String getSendAnswer() {
		return this.sendAnswer;
	}

	public void setSendAnswer(String sendAnswer) {
		this.sendAnswer = sendAnswer;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}