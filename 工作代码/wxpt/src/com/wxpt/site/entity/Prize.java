package com.wxpt.site.entity;

/**
 * Prize entity. @author MyEclipse Persistence Tools
 */

public class Prize implements java.io.Serializable {

	// Fields

	private Integer prizeId;
	private Integer prizegrade;
	private String prizenum;
	private String probability;
	private String prizeuser;
	private String prizeTime;
	private Integer state;
	private Integer isstate;

	// Constructors

	/** default constructor */
	public Prize() {
	}

	/** full constructor */
	public Prize(Integer prizegrade, String prizenum, String probability,
			String prizeuser, String prizeTime, Integer state, Integer isstate) {
		this.prizegrade = prizegrade;
		this.prizenum = prizenum;
		this.probability = probability;
		this.prizeuser = prizeuser;
		this.prizeTime = prizeTime;
		this.state = state;
		this.isstate = isstate;
	}

	// Property accessors

	public Integer getPrizeId() {
		return this.prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public Integer getPrizegrade() {
		return this.prizegrade;
	}

	public void setPrizegrade(Integer prizegrade) {
		this.prizegrade = prizegrade;
	}

	public String getPrizenum() {
		return this.prizenum;
	}

	public void setPrizenum(String prizenum) {
		this.prizenum = prizenum;
	}

	public String getProbability() {
		return this.probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getPrizeuser() {
		return this.prizeuser;
	}

	public void setPrizeuser(String prizeuser) {
		this.prizeuser = prizeuser;
	}

	public String getPrizeTime() {
		return this.prizeTime;
	}

	public void setPrizeTime(String prizeTime) {
		this.prizeTime = prizeTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsstate() {
		return this.isstate;
	}

	public void setIsstate(Integer isstate) {
		this.isstate = isstate;
	}

}