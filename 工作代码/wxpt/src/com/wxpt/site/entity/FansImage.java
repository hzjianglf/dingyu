package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * FansImage entity. @author MyEclipse Persistence Tools
 */

public class FansImage implements java.io.Serializable {

	// Fields

	private Integer fansImageId;
	private FansUser fansUser;
	private String fansImageValue;
	private String imageUpdateTime;
	private Integer checkState;
	private Set fansImageVisits = new HashSet(0);

	// Constructors

	/** default constructor */
	public FansImage() {
	}

	/** minimal constructor */
	public FansImage(FansUser fansUser, String fansImageValue,
			String imageUpdateTime, Integer checkState) {
		this.fansUser = fansUser;
		this.fansImageValue = fansImageValue;
		this.imageUpdateTime = imageUpdateTime;
		this.checkState = checkState;
	}

	/** full constructor */
	public FansImage(FansUser fansUser, String fansImageValue,
			String imageUpdateTime, Integer checkState, Set fansImageVisits) {
		this.fansUser = fansUser;
		this.fansImageValue = fansImageValue;
		this.imageUpdateTime = imageUpdateTime;
		this.checkState = checkState;
		this.fansImageVisits = fansImageVisits;
	}

	// Property accessors

	public Integer getFansImageId() {
		return this.fansImageId;
	}

	public void setFansImageId(Integer fansImageId) {
		this.fansImageId = fansImageId;
	}

	public FansUser getFansUser() {
		return this.fansUser;
	}

	public void setFansUser(FansUser fansUser) {
		this.fansUser = fansUser;
	}

	public String getFansImageValue() {
		return this.fansImageValue;
	}

	public void setFansImageValue(String fansImageValue) {
		this.fansImageValue = fansImageValue;
	}

	public String getImageUpdateTime() {
		return this.imageUpdateTime;
	}

	public void setImageUpdateTime(String imageUpdateTime) {
		this.imageUpdateTime = imageUpdateTime;
	}

	public Integer getCheckState() {
		return this.checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public Set getFansImageVisits() {
		return this.fansImageVisits;
	}

	public void setFansImageVisits(Set fansImageVisits) {
		this.fansImageVisits = fansImageVisits;
	}

}