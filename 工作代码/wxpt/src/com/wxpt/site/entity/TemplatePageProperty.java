package com.wxpt.site.entity;

/**
 * TemplatePageProperty entity. @author MyEclipse Persistence Tools
 */

public class TemplatePageProperty implements java.io.Serializable {

	// Fields

	private Integer templatePagePropertyId;
	private TemplateMenuType templateMenuType;
	private Templates templates;
	private Integer title;
	private Integer compilerNum;
	private Integer bigImageNum;
	private Integer textboxNum;
	private Integer advertisingNum;
	private Integer templatePageMenuId;
	private Integer littleImageNum;

	// Constructors

	/** default constructor */
	public TemplatePageProperty() {
	}

	/** minimal constructor */
	public TemplatePageProperty(TemplateMenuType templateMenuType,
			Templates templates) {
		this.templateMenuType = templateMenuType;
		this.templates = templates;
	}

	/** full constructor */
	public TemplatePageProperty(TemplateMenuType templateMenuType,
			Templates templates, Integer title, Integer compilerNum,
			Integer bigImageNum, Integer textboxNum, Integer advertisingNum,
			Integer templatePageMenuId, Integer littleImageNum) {
		this.templateMenuType = templateMenuType;
		this.templates = templates;
		this.title = title;
		this.compilerNum = compilerNum;
		this.bigImageNum = bigImageNum;
		this.textboxNum = textboxNum;
		this.advertisingNum = advertisingNum;
		this.templatePageMenuId = templatePageMenuId;
		this.littleImageNum = littleImageNum;
	}

	// Property accessors

	public Integer getTemplatePagePropertyId() {
		return this.templatePagePropertyId;
	}

	public void setTemplatePagePropertyId(Integer templatePagePropertyId) {
		this.templatePagePropertyId = templatePagePropertyId;
	}

	public TemplateMenuType getTemplateMenuType() {
		return this.templateMenuType;
	}

	public void setTemplateMenuType(TemplateMenuType templateMenuType) {
		this.templateMenuType = templateMenuType;
	}

	public Templates getTemplates() {
		return this.templates;
	}

	public void setTemplates(Templates templates) {
		this.templates = templates;
	}

	public Integer getTitle() {
		return this.title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public Integer getCompilerNum() {
		return this.compilerNum;
	}

	public void setCompilerNum(Integer compilerNum) {
		this.compilerNum = compilerNum;
	}

	public Integer getBigImageNum() {
		return this.bigImageNum;
	}

	public void setBigImageNum(Integer bigImageNum) {
		this.bigImageNum = bigImageNum;
	}

	public Integer getTextboxNum() {
		return this.textboxNum;
	}

	public void setTextboxNum(Integer textboxNum) {
		this.textboxNum = textboxNum;
	}

	public Integer getAdvertisingNum() {
		return this.advertisingNum;
	}

	public void setAdvertisingNum(Integer advertisingNum) {
		this.advertisingNum = advertisingNum;
	}

	public Integer getTemplatePageMenuId() {
		return this.templatePageMenuId;
	}

	public void setTemplatePageMenuId(Integer templatePageMenuId) {
		this.templatePageMenuId = templatePageMenuId;
	}

	public Integer getLittleImageNum() {
		return this.littleImageNum;
	}

	public void setLittleImageNum(Integer littleImageNum) {
		this.littleImageNum = littleImageNum;
	}

}