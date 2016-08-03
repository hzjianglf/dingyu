package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Card entity. @author MyEclipse Persistence Tools
 */

public class Card implements java.io.Serializable {

	// Fields

	private Integer cardId;
	private String cardName;
	private String cardImage;
	private Integer cardCount;
	private Integer cardType;
	private Set cardRecordses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Card() {
	}

	/** full constructor */
	public Card(String cardName, String cardImage, Integer cardCount,
			Integer cardType, Set cardRecordses) {
		this.cardName = cardName;
		this.cardImage = cardImage;
		this.cardCount = cardCount;
		this.cardType = cardType;
		this.cardRecordses = cardRecordses;
	}

	// Property accessors

	public Integer getCardId() {
		return this.cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardImage() {
		return this.cardImage;
	}

	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}

	public Integer getCardCount() {
		return this.cardCount;
	}

	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}

	public Integer getCardType() {
		return this.cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Set getCardRecordses() {
		return this.cardRecordses;
	}

	public void setCardRecordses(Set cardRecordses) {
		this.cardRecordses = cardRecordses;
	}

}