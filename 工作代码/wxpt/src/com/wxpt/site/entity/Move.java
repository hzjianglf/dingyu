package com.wxpt.site.entity;

/**
 * Move entity. @author MyEclipse Persistence Tools
 */

public class Move implements java.io.Serializable {

	// Fields

	private Integer moveId;
	private String moveStateDesc;
	private String moveName;
	private String moveContent;
	private String moveStartTime;
	private String moveEndTime;
	private Integer moveState;
	private String awardTime;
	private String gailv;
	private String probability;

	// Constructors

	/** default constructor */
	public Move() {
	}

	/** full constructor */
	public Move(String moveStateDesc, String moveName, String moveContent,
			String moveStartTime, String moveEndTime, Integer moveState,
			String awardTime, String gailv, String probability) {
		this.moveStateDesc = moveStateDesc;
		this.moveName = moveName;
		this.moveContent = moveContent;
		this.moveStartTime = moveStartTime;
		this.moveEndTime = moveEndTime;
		this.moveState = moveState;
		this.awardTime = awardTime;
		this.gailv = gailv;
		this.probability = probability;
	}

	// Property accessors

	public Integer getMoveId() {
		return this.moveId;
	}

	public void setMoveId(Integer moveId) {
		this.moveId = moveId;
	}

	public String getMoveStateDesc() {
		return this.moveStateDesc;
	}

	public void setMoveStateDesc(String moveStateDesc) {
		this.moveStateDesc = moveStateDesc;
	}

	public String getMoveName() {
		return this.moveName;
	}

	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}

	public String getMoveContent() {
		return this.moveContent;
	}

	public void setMoveContent(String moveContent) {
		this.moveContent = moveContent;
	}

	public String getMoveStartTime() {
		return this.moveStartTime;
	}

	public void setMoveStartTime(String moveStartTime) {
		this.moveStartTime = moveStartTime;
	}

	public String getMoveEndTime() {
		return this.moveEndTime;
	}

	public void setMoveEndTime(String moveEndTime) {
		this.moveEndTime = moveEndTime;
	}

	public Integer getMoveState() {
		return this.moveState;
	}

	public void setMoveState(Integer moveState) {
		this.moveState = moveState;
	}

	public String getAwardTime() {
		return this.awardTime;
	}

	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}

	public String getGailv() {
		return this.gailv;
	}

	public void setGailv(String gailv) {
		this.gailv = gailv;
	}

	public String getProbability() {
		return this.probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

}