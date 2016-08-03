package com.wxpt.site.entity.pojo;

public class GreetingCardTypePojo {
	private Integer cardTypeId;
	private String cardTypeName;
	private Integer cardTypeState;
	private String stateStr;
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
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public GreetingCardTypePojo() {
		// TODO Auto-generated constructor stub
	}
	public GreetingCardTypePojo(Integer cardTypeId, String cardTypeName,
			Integer cardTypeState, String stateStr) {
		super();
		this.cardTypeId = cardTypeId;
		this.cardTypeName = cardTypeName;
		this.cardTypeState = cardTypeState;
		this.stateStr = stateStr;
	}
	
}
