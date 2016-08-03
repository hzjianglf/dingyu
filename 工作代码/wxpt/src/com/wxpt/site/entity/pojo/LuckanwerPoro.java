package com.wxpt.site.entity.pojo;

import com.wxpt.site.entity.AnswerRecords;

public class LuckanwerPoro {
	private Integer luckanswerid;
	private Integer recordId;
	private String answerUser;
	private String answerTime;
	private Integer type;
	private String addTime;
	private Integer state;
	private String updateTime;
	private String typeStr;
	private String stateStr;
	
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public LuckanwerPoro() {
		// TODO Auto-generated constructor stub
	}
	public Integer getLuckanswerid() {
		return luckanswerid;
	}

	public void setLuckanswerid(Integer luckanswerid) {
		this.luckanswerid = luckanswerid;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getAnswerUser() {
		return answerUser;
	}

	public void setAnswerUser(String answerUser) {
		this.answerUser = answerUser;
	}

	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public LuckanwerPoro(Integer luckanswerid, Integer recordId,
			String answerUser, String answerTime, Integer type, String addTime,
			Integer state, String updateTime) {
		super();
		this.luckanswerid = luckanswerid;
		this.recordId = recordId;
		this.answerUser = answerUser;
		this.answerTime = answerTime;
		this.type = type;
		this.addTime = addTime;
		this.state = state;
		this.updateTime = updateTime;
	}
	
}
