package com.wxpt.site.entity.pojo;

public class GreetingCardPojo {
	private Integer cardId;
	private Integer cardTypeId;
	private String cardTypeName;
	private Integer cardTypeState;
	private String cardValue;
	private String cardBgImage;
	private String cardTxtImage;
	private String cardTitleImage;
	private String cardTypeStateStr;
	private String cardName;
	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public Integer getCardTypeState() {
		return cardTypeState;
	}

	public void setCardTypeState(Integer cardTypeState) {
		this.cardTypeState = cardTypeState;
	}

	public String getCardValue() {
		return cardValue;
	}

	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}

	public String getCardBgImage() {
		return cardBgImage;
	}

	public void setCardBgImage(String cardBgImage) {
		this.cardBgImage = cardBgImage;
	}

	public String getCardTxtImage() {
		return cardTxtImage;
	}

	public void setCardTxtImage(String cardTxtImage) {
		this.cardTxtImage = cardTxtImage;
	}

	public String getCardTypeStateStr() {
		return cardTypeStateStr;
	}

	public void setCardTypeStateStr(String cardTypeStateStr) {
		this.cardTypeStateStr = cardTypeStateStr;
	}

	public GreetingCardPojo() {
		// TODO Auto-generated constructor stub
	}

	public GreetingCardPojo(Integer cardId, Integer cardTypeId,
			String cardTypeName, Integer cardTypeState, String cardValue,
			String cardBgImage, String cardTxtImage, String cardTypeStateStr) {
		super();
		this.cardId = cardId;
		this.cardTypeId = cardTypeId;
		this.cardTypeName = cardTypeName;
		this.cardTypeState = cardTypeState;
		this.cardValue = cardValue;
		this.cardBgImage = cardBgImage;
		this.cardTxtImage = cardTxtImage;
		this.cardTypeStateStr = cardTypeStateStr;
	}

	public String getCardTitleImage() {
		return cardTitleImage;
	}

	public void setCardTitleImage(String cardTitleImage) {
		this.cardTitleImage = cardTitleImage;
	}

}
