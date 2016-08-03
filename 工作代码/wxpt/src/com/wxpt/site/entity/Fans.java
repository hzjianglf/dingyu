package com.wxpt.site.entity;

/**
 * Fans entity. @author MyEclipse Persistence Tools
 */

public class Fans implements java.io.Serializable {

	// Fields

	private Integer fansId;
	private String fansName;
	private String fansValue;

	// Constructors

	/** default constructor */
	public Fans() {
	}

	/** full constructor */
	public Fans(String fansName, String fansValue) {
		this.fansName = fansName;
		this.fansValue = fansValue;
	}

	// Property accessors

	public Integer getFansId() {
		return this.fansId;
	}

	public void setFansId(Integer fansId) {
		this.fansId = fansId;
	}

	public String getFansName() {
		return this.fansName;
	}

	public void setFansName(String fansName) {
		this.fansName = fansName;
	}

	public String getFansValue() {
		return this.fansValue;
	}

	public void setFansValue(String fansValue) {
		this.fansValue = fansValue;
	}

}