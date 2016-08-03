package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Templates entity. @author MyEclipse Persistence Tools
 */

public class Templates implements java.io.Serializable {

	// Fields

	private Integer templateId;
	private Integer templateType;
	private String templatePrice;
	private String thumbnail;
	private String url;
	private String folder;
	private String templateAddTime;
	private String templateRemark;
	private String templateUpdateTime;
	private Set templateMenuTypes = new HashSet(0);
	private Set templatePageProperties = new HashSet(0);
	private Set templateProperties = new HashSet(0);
	private Set userTemplates = new HashSet(0);

	// Constructors

	/** default constructor */
	public Templates() {
	}

	/** minimal constructor */
	public Templates(Integer templateType, String templatePrice,
			String thumbnail, String folder, String templateAddTime,
			String templateRemark, String templateUpdateTime) {
		this.templateType = templateType;
		this.templatePrice = templatePrice;
		this.thumbnail = thumbnail;
		this.folder = folder;
		this.templateAddTime = templateAddTime;
		this.templateRemark = templateRemark;
		this.templateUpdateTime = templateUpdateTime;
	}

	/** full constructor */
	public Templates(Integer templateType, String templatePrice,
			String thumbnail, String url, String folder,
			String templateAddTime, String templateRemark,
			String templateUpdateTime, Set templateMenuTypes,
			Set templatePageProperties, Set templateProperties,
			Set userTemplates) {
		this.templateType = templateType;
		this.templatePrice = templatePrice;
		this.thumbnail = thumbnail;
		this.url = url;
		this.folder = folder;
		this.templateAddTime = templateAddTime;
		this.templateRemark = templateRemark;
		this.templateUpdateTime = templateUpdateTime;
		this.templateMenuTypes = templateMenuTypes;
		this.templatePageProperties = templatePageProperties;
		this.templateProperties = templateProperties;
		this.userTemplates = userTemplates;
	}

	// Property accessors

	public Integer getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getTemplateType() {
		return this.templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getTemplatePrice() {
		return this.templatePrice;
	}

	public void setTemplatePrice(String templatePrice) {
		this.templatePrice = templatePrice;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFolder() {
		return this.folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getTemplateAddTime() {
		return this.templateAddTime;
	}

	public void setTemplateAddTime(String templateAddTime) {
		this.templateAddTime = templateAddTime;
	}

	public String getTemplateRemark() {
		return this.templateRemark;
	}

	public void setTemplateRemark(String templateRemark) {
		this.templateRemark = templateRemark;
	}

	public String getTemplateUpdateTime() {
		return this.templateUpdateTime;
	}

	public void setTemplateUpdateTime(String templateUpdateTime) {
		this.templateUpdateTime = templateUpdateTime;
	}

	public Set getTemplateMenuTypes() {
		return this.templateMenuTypes;
	}

	public void setTemplateMenuTypes(Set templateMenuTypes) {
		this.templateMenuTypes = templateMenuTypes;
	}

	public Set getTemplatePageProperties() {
		return this.templatePageProperties;
	}

	public void setTemplatePageProperties(Set templatePageProperties) {
		this.templatePageProperties = templatePageProperties;
	}

	public Set getTemplateProperties() {
		return this.templateProperties;
	}

	public void setTemplateProperties(Set templateProperties) {
		this.templateProperties = templateProperties;
	}

	public Set getUserTemplates() {
		return this.userTemplates;
	}

	public void setUserTemplates(Set userTemplates) {
		this.userTemplates = userTemplates;
	}

}