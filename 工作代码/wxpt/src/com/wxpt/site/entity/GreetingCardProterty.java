package com.wxpt.site.entity;

/**
 * GreetingCardProterty entity. @author MyEclipse Persistence Tools
 */

public class GreetingCardProterty implements java.io.Serializable {

	// Fields

	private Integer cardProtertyId;
	private String guideUrl;
	private Integer cardId;

	// Constructors

	/** default constructor */
	public GreetingCardProterty() {
	}

	/** minimal constructor */
	public GreetingCardProterty(Integer cardId) {
		this.cardId = cardId;
	}

	/** full constructor */
	public GreetingCardProterty(String guideUrl, Integer cardId) {
		this.guideUrl = guideUrl;
		this.cardId = cardId;
	}

	// Property accessors

	public Integer getCardProtertyId() {
		return this.cardProtertyId;
	}

	public void setCardProtertyId(Integer cardProtertyId) {
		this.cardProtertyId = cardProtertyId;
	}

	public String getGuideUrl() {
		return this.guideUrl;
	}

	public void setGuideUrl(String guideUrl) {
		this.guideUrl = guideUrl;
	}

	public Integer getCardId() {
		return this.cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

}