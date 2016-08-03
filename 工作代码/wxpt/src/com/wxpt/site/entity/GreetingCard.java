package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * GreetingCard entity. @author MyEclipse Persistence Tools
 */

public class GreetingCard implements java.io.Serializable {

	// Fields

	private Integer cardId;
	private Integer cardTemplateId;
	private String cardValue;
	private String cardBgImage;
	private String cardTxtImage;
	private String cardTitleImage;
	private String cardName;
	private Set fandsGreetingCards = new HashSet(0);

	// Constructors

	/** default constructor */
	public GreetingCard() {
	}

	/** minimal constructor */
	public GreetingCard(Integer cardTemplateId, String cardValue,
			String cardBgImage, String cardTxtImage, String cardName) {
		this.cardTemplateId = cardTemplateId;
		this.cardValue = cardValue;
		this.cardBgImage = cardBgImage;
		this.cardTxtImage = cardTxtImage;
		this.cardName = cardName;
	}

	/** full constructor */
	public GreetingCard(Integer cardTemplateId, String cardValue,
			String cardBgImage, String cardTxtImage, String cardTitleImage,
			String cardName, Set fandsGreetingCards) {
		this.cardTemplateId = cardTemplateId;
		this.cardValue = cardValue;
		this.cardBgImage = cardBgImage;
		this.cardTxtImage = cardTxtImage;
		this.cardTitleImage = cardTitleImage;
		this.cardName = cardName;
		this.fandsGreetingCards = fandsGreetingCards;
	}

	// Property accessors

	public Integer getCardId() {
		return this.cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public Integer getCardTemplateId() {
		return this.cardTemplateId;
	}

	public void setCardTemplateId(Integer cardTemplateId) {
		this.cardTemplateId = cardTemplateId;
	}

	public String getCardValue() {
		return this.cardValue;
	}

	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}

	public String getCardBgImage() {
		return this.cardBgImage;
	}

	public void setCardBgImage(String cardBgImage) {
		this.cardBgImage = cardBgImage;
	}

	public String getCardTxtImage() {
		return this.cardTxtImage;
	}

	public void setCardTxtImage(String cardTxtImage) {
		this.cardTxtImage = cardTxtImage;
	}

	public String getCardTitleImage() {
		return this.cardTitleImage;
	}

	public void setCardTitleImage(String cardTitleImage) {
		this.cardTitleImage = cardTitleImage;
	}

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Set getFandsGreetingCards() {
		return this.fandsGreetingCards;
	}

	public void setFandsGreetingCards(Set fandsGreetingCards) {
		this.fandsGreetingCards = fandsGreetingCards;
	}

}