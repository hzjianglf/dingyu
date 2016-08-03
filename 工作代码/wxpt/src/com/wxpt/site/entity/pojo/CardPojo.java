package com.wxpt.site.entity.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Card entity. @author MyEclipse Persistence Tools
 */

public class CardPojo implements java.io.Serializable {

	// Fields

	private Integer cardId;
	private String cardName;
	private String cardImage;
	private Integer cardCount;
	private Integer cardType;
	private String cardTypeDesc;
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardImage() {
		return cardImage;
	}
	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}
	public Integer getCardCount() {
		return cardCount;
	}
	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getCardTypeDesc() {
		return cardTypeDesc;
	}
	public void setCardTypeDesc(String cardTypeDesc) {
		this.cardTypeDesc = cardTypeDesc;
	}

	

}