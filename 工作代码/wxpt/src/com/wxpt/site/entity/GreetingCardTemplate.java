package com.wxpt.site.entity;

/**
 * GreetingCardTemplate entity. @author MyEclipse Persistence Tools
 */

public class GreetingCardTemplate implements java.io.Serializable {

	// Fields

	private Integer cardTemplateId;
	private String cardTemplateName;
	private Integer cardTemplateState;
	private String cardTemplatePath;

	// Constructors

	/** default constructor */
	public GreetingCardTemplate() {
	}

	/** full constructor */
	public GreetingCardTemplate(String cardTemplateName,
			Integer cardTemplateState, String cardTemplatePath) {
		this.cardTemplateName = cardTemplateName;
		this.cardTemplateState = cardTemplateState;
		this.cardTemplatePath = cardTemplatePath;
	}

	// Property accessors

	public Integer getCardTemplateId() {
		return this.cardTemplateId;
	}

	public void setCardTemplateId(Integer cardTemplateId) {
		this.cardTemplateId = cardTemplateId;
	}

	public String getCardTemplateName() {
		return this.cardTemplateName;
	}

	public void setCardTemplateName(String cardTemplateName) {
		this.cardTemplateName = cardTemplateName;
	}

	public Integer getCardTemplateState() {
		return this.cardTemplateState;
	}

	public void setCardTemplateState(Integer cardTemplateState) {
		this.cardTemplateState = cardTemplateState;
	}

	public String getCardTemplatePath() {
		return this.cardTemplatePath;
	}

	public void setCardTemplatePath(String cardTemplatePath) {
		this.cardTemplatePath = cardTemplatePath;
	}

}