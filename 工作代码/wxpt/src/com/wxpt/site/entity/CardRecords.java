package com.wxpt.site.entity;

/**
 * CardRecords entity. @author MyEclipse Persistence Tools
 */

public class CardRecords implements java.io.Serializable {

	// Fields

	private Integer cardRecordsId;
	private Card card;
	private String cardGetTime;
	private String exchangeAwdTime;
	private String cardUserName;
	private String cardPrizeDesc;

	// Constructors

	/** default constructor */
	public CardRecords() {
	}

	/** full constructor */
	public CardRecords(Card card, String cardGetTime, String exchangeAwdTime,
			String cardUserName, String cardPrizeDesc) {
		this.card = card;
		this.cardGetTime = cardGetTime;
		this.exchangeAwdTime = exchangeAwdTime;
		this.cardUserName = cardUserName;
		this.cardPrizeDesc = cardPrizeDesc;
	}

	// Property accessors

	public Integer getCardRecordsId() {
		return this.cardRecordsId;
	}

	public void setCardRecordsId(Integer cardRecordsId) {
		this.cardRecordsId = cardRecordsId;
	}

	public Card getCard() {
		return this.card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getCardGetTime() {
		return this.cardGetTime;
	}

	public void setCardGetTime(String cardGetTime) {
		this.cardGetTime = cardGetTime;
	}

	public String getExchangeAwdTime() {
		return this.exchangeAwdTime;
	}

	public void setExchangeAwdTime(String exchangeAwdTime) {
		this.exchangeAwdTime = exchangeAwdTime;
	}

	public String getCardUserName() {
		return this.cardUserName;
	}

	public void setCardUserName(String cardUserName) {
		this.cardUserName = cardUserName;
	}

	public String getCardPrizeDesc() {
		return this.cardPrizeDesc;
	}

	public void setCardPrizeDesc(String cardPrizeDesc) {
		this.cardPrizeDesc = cardPrizeDesc;
	}

}