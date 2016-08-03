package com.wxpt.site.entity;

/**
 * ShoppingAds entity. @author MyEclipse Persistence Tools
 */

public class ShoppingAds implements java.io.Serializable {

	// Fields

	private Integer adId;
	private String adTitle;
	private String adConent;
	private String adName;
	private Integer type;

	// Constructors

	/** default constructor */
	public ShoppingAds() {
	}

	/** minimal constructor */
	public ShoppingAds(Integer type) {
		this.type = type;
	}

	/** full constructor */
	public ShoppingAds(String adTitle, String adConent, String adName,
			Integer type) {
		this.adTitle = adTitle;
		this.adConent = adConent;
		this.adName = adName;
		this.type = type;
	}

	// Property accessors

	public Integer getAdId() {
		return this.adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public String getAdTitle() {
		return this.adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public String getAdConent() {
		return this.adConent;
	}

	public void setAdConent(String adConent) {
		this.adConent = adConent;
	}

	public String getAdName() {
		return this.adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}