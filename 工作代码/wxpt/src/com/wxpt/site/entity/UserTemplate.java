package com.wxpt.site.entity;

/**
 * UserTemplate entity. @author MyEclipse Persistence Tools
 */

public class UserTemplate implements java.io.Serializable {

	// Fields

	private Integer userTemplateId;
	private EnterInfor enterInfor;
	private Templates templates;
	private String userTemplateAddTime;

	// Constructors

	/** default constructor */
	public UserTemplate() {
	}

	/** full constructor */
	public UserTemplate(EnterInfor enterInfor, Templates templates,
			String userTemplateAddTime) {
		this.enterInfor = enterInfor;
		this.templates = templates;
		this.userTemplateAddTime = userTemplateAddTime;
	}

	// Property accessors

	public Integer getUserTemplateId() {
		return this.userTemplateId;
	}

	public void setUserTemplateId(Integer userTemplateId) {
		this.userTemplateId = userTemplateId;
	}

	public EnterInfor getEnterInfor() {
		return this.enterInfor;
	}

	public void setEnterInfor(EnterInfor enterInfor) {
		this.enterInfor = enterInfor;
	}

	public Templates getTemplates() {
		return this.templates;
	}

	public void setTemplates(Templates templates) {
		this.templates = templates;
	}

	public String getUserTemplateAddTime() {
		return this.userTemplateAddTime;
	}

	public void setUserTemplateAddTime(String userTemplateAddTime) {
		this.userTemplateAddTime = userTemplateAddTime;
	}

}