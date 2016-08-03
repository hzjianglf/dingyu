package com.wxpt.site.entity.pojo;

public class LuckUserPoro {
	private Integer luckId;
	private String sendUser;
	private String addTime;
	private Integer state;
	private String sendTime;
	private String stateStr;
	public Integer getLuckId() {
		return luckId;
	}
	public void setLuckId(Integer luckId) {
		this.luckId = luckId;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
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
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public LuckUserPoro(Integer luckId, String sendUser, String addTime,
			Integer state, String sendTime) {
		super();
		this.luckId = luckId;
		this.sendUser = sendUser;
		this.addTime = addTime;
		this.state = state;
		this.sendTime = sendTime;
	}
	public LuckUserPoro() {
		// TODO Auto-generated constructor stub
	}
}
