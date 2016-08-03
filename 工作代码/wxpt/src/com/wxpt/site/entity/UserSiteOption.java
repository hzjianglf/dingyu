package com.wxpt.site.entity;

/**
 * UserSiteOption entity. @author MyEclipse Persistence Tools
 */

public class UserSiteOption implements java.io.Serializable {

	// Fields

	private Integer optionId;
	private String optionName;
	private String optionValue;
	private String url;

	// Constructors

	/** default constructor */
	public UserSiteOption() {
	}

	/** full constructor */
	public UserSiteOption(String optionName, String optionValue, String url) {
		this.optionName = optionName;
		this.optionValue = optionValue;
		this.url = url;
	}

	// Property accessors

	public Integer getOptionId() {
		return this.optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return this.optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionValue() {
		return this.optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}