package com.wxpt.site.entity;

/**
 * FansImageVisit entity. @author MyEclipse Persistence Tools
 */

public class FansImageVisit implements java.io.Serializable {

	// Fields

	private Integer fansImageVisitId;
	private FansUser fansUser;
	private FansImage fansImage;
	private String fansVisitKey;
	private String fansUserName;
	private String fansVisitValue;

	// Constructors

	/** default constructor */
	public FansImageVisit() {
	}

	/** full constructor */
	public FansImageVisit(FansUser fansUser, FansImage fansImage,
			String fansVisitKey, String fansUserName, String fansVisitValue) {
		this.fansUser = fansUser;
		this.fansImage = fansImage;
		this.fansVisitKey = fansVisitKey;
		this.fansUserName = fansUserName;
		this.fansVisitValue = fansVisitValue;
	}

	// Property accessors

	public Integer getFansImageVisitId() {
		return this.fansImageVisitId;
	}

	public void setFansImageVisitId(Integer fansImageVisitId) {
		this.fansImageVisitId = fansImageVisitId;
	}

	public FansUser getFansUser() {
		return this.fansUser;
	}

	public void setFansUser(FansUser fansUser) {
		this.fansUser = fansUser;
	}

	public FansImage getFansImage() {
		return this.fansImage;
	}

	public void setFansImage(FansImage fansImage) {
		this.fansImage = fansImage;
	}

	public String getFansVisitKey() {
		return this.fansVisitKey;
	}

	public void setFansVisitKey(String fansVisitKey) {
		this.fansVisitKey = fansVisitKey;
	}

	public String getFansUserName() {
		return this.fansUserName;
	}

	public void setFansUserName(String fansUserName) {
		this.fansUserName = fansUserName;
	}

	public String getFansVisitValue() {
		return this.fansVisitValue;
	}

	public void setFansVisitValue(String fansVisitValue) {
		this.fansVisitValue = fansVisitValue;
	}

}