package com.wxpt.site.entity;

/**
 * Surrecord entity. @author MyEclipse Persistence Tools
 */

public class Surrecord implements java.io.Serializable {

	// Fields

	private Integer surrecordId;
	private Survey survey;
	private Suroption suroption;
	private Surquestion surquestion;

	// Constructors

	/** default constructor */
	public Surrecord() {
	}

	/** full constructor */
	public Surrecord(Survey survey, Suroption suroption, Surquestion surquestion) {
		this.survey = survey;
		this.suroption = suroption;
		this.surquestion = surquestion;
	}

	// Property accessors

	public Integer getSurrecordId() {
		return this.surrecordId;
	}

	public void setSurrecordId(Integer surrecordId) {
		this.surrecordId = surrecordId;
	}

	public Survey getSurvey() {
		return this.survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Suroption getSuroption() {
		return this.suroption;
	}

	public void setSuroption(Suroption suroption) {
		this.suroption = suroption;
	}

	public Surquestion getSurquestion() {
		return this.surquestion;
	}

	public void setSurquestion(Surquestion surquestion) {
		this.surquestion = surquestion;
	}

}