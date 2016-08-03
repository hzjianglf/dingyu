package com.wxpt.site.entity;

/**
 * Imageroll entity. @author MyEclipse Persistence Tools
 */

public class Imageroll implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private String uploadImage;
	private String uploadTime;
	private Integer templateCount;

	// Constructors

	/** default constructor */
	public Imageroll() {
	}

	/** full constructor */
	public Imageroll(String uploadImage, String uploadTime,
			Integer templateCount) {
		this.uploadImage = uploadImage;
		this.uploadTime = uploadTime;
		this.templateCount = templateCount;
	}

	// Property accessors

	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getUploadImage() {
		return this.uploadImage;
	}

	public void setUploadImage(String uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getTemplateCount() {
		return this.templateCount;
	}

	public void setTemplateCount(Integer templateCount) {
		this.templateCount = templateCount;
	}

}