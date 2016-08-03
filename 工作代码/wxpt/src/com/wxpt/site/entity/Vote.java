package com.wxpt.site.entity;

/**
 * Vote entity. @author MyEclipse Persistence Tools
 */

public class Vote implements java.io.Serializable {

	// Fields

	private Integer voteId;
	private String voteStartTime;
	private String voteEndTime;
	private Integer state;
	private String voteUser;

	// Constructors

	/** default constructor */
	public Vote() {
	}

	/** full constructor */
	public Vote(String voteStartTime, String voteEndTime, Integer state,
			String voteUser) {
		this.voteStartTime = voteStartTime;
		this.voteEndTime = voteEndTime;
		this.state = state;
		this.voteUser = voteUser;
	}

	// Property accessors

	public Integer getVoteId() {
		return this.voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public String getVoteStartTime() {
		return this.voteStartTime;
	}

	public void setVoteStartTime(String voteStartTime) {
		this.voteStartTime = voteStartTime;
	}

	public String getVoteEndTime() {
		return this.voteEndTime;
	}

	public void setVoteEndTime(String voteEndTime) {
		this.voteEndTime = voteEndTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getVoteUser() {
		return this.voteUser;
	}

	public void setVoteUser(String voteUser) {
		this.voteUser = voteUser;
	}

}