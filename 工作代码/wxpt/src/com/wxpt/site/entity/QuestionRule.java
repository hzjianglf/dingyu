package com.wxpt.site.entity;

/**
 * QuestionRule entity. @author MyEclipse Persistence Tools
 */

public class QuestionRule implements java.io.Serializable {

	// Fields

	private Integer questionRuleId;
	private Integer questionNum;
	private String questionTishi;
	private String awards;
	private String fangqi;

	// Constructors

	/** default constructor */
	public QuestionRule() {
	}

	/** minimal constructor */
	public QuestionRule(String awards) {
		this.awards = awards;
	}

	/** full constructor */
	public QuestionRule(Integer questionNum, String questionTishi,
			String awards, String fangqi) {
		this.questionNum = questionNum;
		this.questionTishi = questionTishi;
		this.awards = awards;
		this.fangqi = fangqi;
	}

	// Property accessors

	public Integer getQuestionRuleId() {
		return this.questionRuleId;
	}

	public void setQuestionRuleId(Integer questionRuleId) {
		this.questionRuleId = questionRuleId;
	}

	public Integer getQuestionNum() {
		return this.questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public String getQuestionTishi() {
		return this.questionTishi;
	}

	public void setQuestionTishi(String questionTishi) {
		this.questionTishi = questionTishi;
	}

	public String getAwards() {
		return this.awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getFangqi() {
		return this.fangqi;
	}

	public void setFangqi(String fangqi) {
		this.fangqi = fangqi;
	}

}