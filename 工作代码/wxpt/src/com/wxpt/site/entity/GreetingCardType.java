package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * GreetingCardType entity. @author MyEclipse Persistence Tools
 */

public class GreetingCardType implements java.io.Serializable {

	// Fields

	private Integer cardTypeId;
	private String cardTypeName;
	private Integer cardTypeState;
	private Set greetingCards = new HashSet(0);

	// Constructors

	/** default constructor */
	public GreetingCardType() {
	}

	/** minimal constructor */
	public GreetingCardType(String cardTypeName, Integer cardTypeState) {
		this.cardTypeName = cardTypeName;
		this.cardTypeState = cardTypeState;
	}

	/** full constructor */
	public GreetingCardType(String cardTypeName, Integer cardTypeState,
			Set greetingCards) {
		this.cardTypeName = cardTypeName;
		this.cardTypeState = cardTypeState;
		this.greetingCards = greetingCards;
	}

	// Property accessors

	public Integer getCardTypeId() {
		return this.cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getCardTypeName() {
		return this.cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public Integer getCardTypeState() {
		return this.cardTypeState;
	}

	public void setCardTypeState(Integer cardTypeState) {
		this.cardTypeState = cardTypeState;
	}

	public Set getGreetingCards() {
		return this.greetingCards;
	}

	public void setGreetingCards(Set greetingCards) {
		this.greetingCards = greetingCards;
	}

}