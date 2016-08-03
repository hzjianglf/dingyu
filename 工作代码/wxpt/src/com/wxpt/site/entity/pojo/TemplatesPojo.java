package com.wxpt.site.entity.pojo;

public class TemplatesPojo {
	private int templateId;
	private String templateTypeStr;
	private int templateType;
	private String templatePrice;
	private String thumbnail;
	private String url;
	private String folder;
	private String templateAddTime;
	private String templateRemark;
	private String templateUpdateTime;
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public String getTemplateTypeStr() {
		return templateTypeStr;
	}
	public void setTemplateTypeStr(String templateTypeStr) {
		this.templateTypeStr = templateTypeStr;
	}
	public int getTemplateType() {
		return templateType;
	}
	public void setTemplateType(int templateType) {
		this.templateType = templateType;
	}
	public String getTemplatePrice() {
		return templatePrice;
	}
	public void setTemplatePrice(String templatePrice) {
		this.templatePrice = templatePrice;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getTemplateAddTime() {
		return templateAddTime;
	}
	public void setTemplateAddTime(String templateAddTime) {
		this.templateAddTime = templateAddTime;
	}
	public String getTemplateRemark() {
		return templateRemark;
	}
	public void setTemplateRemark(String templateRemark) {
		this.templateRemark = templateRemark;
	}
	public String getTemplateUpdateTime() {
		return templateUpdateTime;
	}
	public void setTemplateUpdateTime(String templateUpdateTime) {
		this.templateUpdateTime = templateUpdateTime;
	}
	public TemplatesPojo(int templateId, String templateTypeStr,
			int templateType, String templatePrice, String thumbnail,
			String url, String folder, String templateAddTime,
			String templateRemark, String templateUpdateTime) {
		super();
		this.templateId = templateId;
		this.templateTypeStr = templateTypeStr;
		this.templateType = templateType;
		this.templatePrice = templatePrice;
		this.thumbnail = thumbnail;
		this.url = url;
		this.folder = folder;
		this.templateAddTime = templateAddTime;
		this.templateRemark = templateRemark;
		this.templateUpdateTime = templateUpdateTime;
	}
	
}
