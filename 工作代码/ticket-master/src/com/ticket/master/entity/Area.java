package com.ticket.master.entity;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */

public class Area implements java.io.Serializable {

	// Fields

	private Integer countryAreaId;
	private String areaName;
	private Integer parentId;

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** full constructor */
	public Area(String areaName, Integer parentId) {
		this.areaName = areaName;
		this.parentId = parentId;
	}

	// Property accessors

	public Integer getCountryAreaId() {
		return this.countryAreaId;
	}

	public void setCountryAreaId(Integer countryAreaId) {
		this.countryAreaId = countryAreaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}