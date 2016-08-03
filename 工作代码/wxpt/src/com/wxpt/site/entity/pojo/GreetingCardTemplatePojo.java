package com.wxpt.site.entity.pojo;

public class GreetingCardTemplatePojo {
	private Integer cardTemplateId;
	private String cardTemplateName;
	private Integer cardTemplateState;
	private String stateStr;
	private String cardTemplatePath;
	public Integer getCardTemplateId() {
		return cardTemplateId;
	}
	public void setCardTemplateId(Integer cardTemplateId) {
		this.cardTemplateId = cardTemplateId;
	}
	public String getCardTemplateName() {
		return cardTemplateName;
	}
	public void setCardTemplateName(String cardTemplateName) {
		this.cardTemplateName = cardTemplateName;
	}
	public Integer getCardTemplateState() {
		return cardTemplateState;
	}
	public void setCardTemplateState(Integer cardTemplateState) {
		this.cardTemplateState = cardTemplateState;
	}
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public String getCardTemplatePath() {
		return cardTemplatePath;
	}
	public void setCardTemplatePath(String cardTemplatePath) {
		this.cardTemplatePath = cardTemplatePath;
	}
	public GreetingCardTemplatePojo(Integer cardTemplateId,
			String cardTemplateName, Integer cardTemplateState,
			String stateStr, String cardTemplatePath) {
		super();
		this.cardTemplateId = cardTemplateId;
		this.cardTemplateName = cardTemplateName;
		this.cardTemplateState = cardTemplateState;
		this.stateStr = stateStr;
		this.cardTemplatePath = cardTemplatePath;
	}
	public GreetingCardTemplatePojo() {
		// TODO Auto-generated constructor stub
	}
}
