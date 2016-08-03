package com.wxpt.site.entity;

/**
 * FandsGreetingCard entity. @author MyEclipse Persistence Tools
 */

public class FandsGreetingCard implements java.io.Serializable {

	// Fields

	private Integer fandsCardId;
	private GreetingCard greetingCard;
	private String fromUser;
	private String cardValue;

	// Constructors

	/** default constructor */
	public FandsGreetingCard() {
	}

	/** full constructor */
	public FandsGreetingCard(GreetingCard greetingCard, String fromUser,
			String cardValue) {
		this.greetingCard = greetingCard;
		this.fromUser = fromUser;
		this.cardValue = cardValue;
	}

	// Property accessors

	public Integer getFandsCardId() {
		return this.fandsCardId;
	}

	public void setFandsCardId(Integer fandsCardId) {
		this.fandsCardId = fandsCardId;
	}

	public GreetingCard getGreetingCard() {
		return this.greetingCard;
	}

	public void setGreetingCard(GreetingCard greetingCard) {
		this.greetingCard = greetingCard;
	}

	public String getFromUser() {
		return this.fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getCardValue() {
		return this.cardValue;
	}

	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}

}