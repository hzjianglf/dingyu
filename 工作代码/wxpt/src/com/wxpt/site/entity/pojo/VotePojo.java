package com.wxpt.site.entity.pojo;

public class VotePojo {
	private int voteId;
	private String voteStartTime;
	private String voteEndTime;
	private String state;
	private String voteUser;
	public String getVoteStartTime() {
		return voteStartTime;
	}
	public void setVoteStartTime(String voteStartTime) {
		this.voteStartTime = voteStartTime;
	}
	public String getVoteEndTime() {
		return voteEndTime;
	}
	public void setVoteEndTime(String voteEndTime) {
		this.voteEndTime = voteEndTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getVoteUser() {
		return voteUser;
	}
	public void setVoteUser(String voteUser) {
		this.voteUser = voteUser;
	}
	public int getVoteId() {
		return voteId;
	}
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
}
