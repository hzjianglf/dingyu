package com.wxpt.site.entity;

/**
 * Shiyong entity. @author MyEclipse Persistence Tools
 */

public class Shiyong implements java.io.Serializable {

	// Fields

	private Integer shiyongId;
	private String shiyongName;
	private String shiyongValue;

	// Constructors

	/** default constructor */
	public Shiyong() {
	}

	/** minimal constructor */
	public Shiyong(String shiyongValue) {
		this.shiyongValue = shiyongValue;
	}

	/** full constructor */
	public Shiyong(String shiyongName, String shiyongValue) {
		this.shiyongName = shiyongName;
		this.shiyongValue = shiyongValue;
	}

	// Property accessors

	public Integer getShiyongId() {
		return this.shiyongId;
	}

	public void setShiyongId(Integer shiyongId) {
		this.shiyongId = shiyongId;
	}

	public String getShiyongName() {
		return this.shiyongName;
	}

	public void setShiyongName(String shiyongName) {
		this.shiyongName = shiyongName;
	}

	public String getShiyongValue() {
		return this.shiyongValue;
	}

	public void setShiyongValue(String shiyongValue) {
		this.shiyongValue = shiyongValue;
	}

}