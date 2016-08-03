package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TemplateMenuType entity. @author MyEclipse Persistence Tools
 */

public class TemplateMenuType implements java.io.Serializable {

	// Fields

	private Integer templateMenuTypeId;
	private Templates templates;
	private Integer menuTypeId;
	private Integer templateTypeMenuId;
	private Integer menuRecom;
	private Integer parseNum;
	private Integer advertisingNum;
	private Integer littleImage;
	private Integer menuLocation;
	private Set templatePageProperties = new HashSet(0);

	// Constructors

	/** default constructor */
	public TemplateMenuType() {
	}

	/** minimal constructor */
	public TemplateMenuType(Integer templateMenuTypeId, Templates templates,
			Integer menuLocation) {
		this.templateMenuTypeId = templateMenuTypeId;
		this.templates = templates;
		this.menuLocation = menuLocation;
	}

	/** full constructor */
	public TemplateMenuType(Integer templateMenuTypeId, Templates templates,
			Integer menuTypeId, Integer templateTypeMenuId, Integer menuRecom,
			Integer parseNum, Integer advertisingNum, Integer littleImage,
			Integer menuLocation, Set templatePageProperties) {
		this.templateMenuTypeId = templateMenuTypeId;
		this.templates = templates;
		this.menuTypeId = menuTypeId;
		this.templateTypeMenuId = templateTypeMenuId;
		this.menuRecom = menuRecom;
		this.parseNum = parseNum;
		this.advertisingNum = advertisingNum;
		this.littleImage = littleImage;
		this.menuLocation = menuLocation;
		this.templatePageProperties = templatePageProperties;
	}

	// Property accessors

	public Integer getTemplateMenuTypeId() {
		return this.templateMenuTypeId;
	}

	public void setTemplateMenuTypeId(Integer templateMenuTypeId) {
		this.templateMenuTypeId = templateMenuTypeId;
	}

	public Templates getTemplates() {
		return this.templates;
	}

	public void setTemplates(Templates templates) {
		this.templates = templates;
	}

	public Integer getMenuTypeId() {
		return this.menuTypeId;
	}

	public void setMenuTypeId(Integer menuTypeId) {
		this.menuTypeId = menuTypeId;
	}

	public Integer getTemplateTypeMenuId() {
		return this.templateTypeMenuId;
	}

	public void setTemplateTypeMenuId(Integer templateTypeMenuId) {
		this.templateTypeMenuId = templateTypeMenuId;
	}

	public Integer getMenuRecom() {
		return this.menuRecom;
	}

	public void setMenuRecom(Integer menuRecom) {
		this.menuRecom = menuRecom;
	}

	public Integer getParseNum() {
		return this.parseNum;
	}

	public void setParseNum(Integer parseNum) {
		this.parseNum = parseNum;
	}

	public Integer getAdvertisingNum() {
		return this.advertisingNum;
	}

	public void setAdvertisingNum(Integer advertisingNum) {
		this.advertisingNum = advertisingNum;
	}

	public Integer getLittleImage() {
		return this.littleImage;
	}

	public void setLittleImage(Integer littleImage) {
		this.littleImage = littleImage;
	}

	public Integer getMenuLocation() {
		return this.menuLocation;
	}

	public void setMenuLocation(Integer menuLocation) {
		this.menuLocation = menuLocation;
	}

	public Set getTemplatePageProperties() {
		return this.templatePageProperties;
	}

	public void setTemplatePageProperties(Set templatePageProperties) {
		this.templatePageProperties = templatePageProperties;
	}

}