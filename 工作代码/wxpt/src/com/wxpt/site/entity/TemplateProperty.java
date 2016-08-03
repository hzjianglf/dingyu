package com.wxpt.site.entity;

/**
 * TemplateProperty entity. @author MyEclipse Persistence Tools
 */

public class TemplateProperty implements java.io.Serializable {

	// Fields

	private Integer templatePropertyId;
	private Templates templates;
	private Integer bannerProperty;
	private Integer menuNum;
	private Integer advertisementProperty;
	private Integer advertisingNum;
	private Integer recomMenuNum;
	private Integer logoProperty;
	private Integer bannerNumber;
	private Integer menuImageProterty;

	// Constructors

	/** default constructor */
	public TemplateProperty() {
	}

	/** minimal constructor */
	public TemplateProperty(Templates templates, Integer bannerNumber,
			Integer menuImageProterty) {
		this.templates = templates;
		this.bannerNumber = bannerNumber;
		this.menuImageProterty = menuImageProterty;
	}

	/** full constructor */
	public TemplateProperty(Templates templates, Integer bannerProperty,
			Integer menuNum, Integer advertisementProperty,
			Integer advertisingNum, Integer recomMenuNum, Integer logoProperty,
			Integer bannerNumber, Integer menuImageProterty) {
		this.templates = templates;
		this.bannerProperty = bannerProperty;
		this.menuNum = menuNum;
		this.advertisementProperty = advertisementProperty;
		this.advertisingNum = advertisingNum;
		this.recomMenuNum = recomMenuNum;
		this.logoProperty = logoProperty;
		this.bannerNumber = bannerNumber;
		this.menuImageProterty = menuImageProterty;
	}

	// Property accessors

	public Integer getTemplatePropertyId() {
		return this.templatePropertyId;
	}

	public void setTemplatePropertyId(Integer templatePropertyId) {
		this.templatePropertyId = templatePropertyId;
	}

	public Templates getTemplates() {
		return this.templates;
	}

	public void setTemplates(Templates templates) {
		this.templates = templates;
	}

	public Integer getBannerProperty() {
		return this.bannerProperty;
	}

	public void setBannerProperty(Integer bannerProperty) {
		this.bannerProperty = bannerProperty;
	}

	public Integer getMenuNum() {
		return this.menuNum;
	}

	public void setMenuNum(Integer menuNum) {
		this.menuNum = menuNum;
	}

	public Integer getAdvertisementProperty() {
		return this.advertisementProperty;
	}

	public void setAdvertisementProperty(Integer advertisementProperty) {
		this.advertisementProperty = advertisementProperty;
	}

	public Integer getAdvertisingNum() {
		return this.advertisingNum;
	}

	public void setAdvertisingNum(Integer advertisingNum) {
		this.advertisingNum = advertisingNum;
	}

	public Integer getRecomMenuNum() {
		return this.recomMenuNum;
	}

	public void setRecomMenuNum(Integer recomMenuNum) {
		this.recomMenuNum = recomMenuNum;
	}

	public Integer getLogoProperty() {
		return this.logoProperty;
	}

	public void setLogoProperty(Integer logoProperty) {
		this.logoProperty = logoProperty;
	}

	public Integer getBannerNumber() {
		return this.bannerNumber;
	}

	public void setBannerNumber(Integer bannerNumber) {
		this.bannerNumber = bannerNumber;
	}

	public Integer getMenuImageProterty() {
		return this.menuImageProterty;
	}

	public void setMenuImageProterty(Integer menuImageProterty) {
		this.menuImageProterty = menuImageProterty;
	}

}