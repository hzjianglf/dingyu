package com.wxpt.site.entity;

/**
 * Radius entity. @author MyEclipse Persistence Tools
 */

public class Radius implements java.io.Serializable {

	// Fields

	private Integer radiusId;
	private Integer radiusContent;
	private String centerName;

	// Constructors

	/** default constructor */
	public Radius() {
	}

	/** minimal constructor */
	public Radius(Integer radiusContent) {
		this.radiusContent = radiusContent;
	}

	/** full constructor */
	public Radius(Integer radiusContent, String centerName) {
		this.radiusContent = radiusContent;
		this.centerName = centerName;
	}

	// Property accessors

	public Integer getRadiusId() {
		return this.radiusId;
	}

	public void setRadiusId(Integer radiusId) {
		this.radiusId = radiusId;
	}

	public Integer getRadiusContent() {
		return this.radiusContent;
	}

	public void setRadiusContent(Integer radiusContent) {
		this.radiusContent = radiusContent;
	}

	public String getCenterName() {
		return this.centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

}