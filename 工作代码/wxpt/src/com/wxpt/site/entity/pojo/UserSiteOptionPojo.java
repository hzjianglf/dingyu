package com.wxpt.site.entity.pojo;

public class UserSiteOptionPojo {
	private int optionId;
	private String optionName;
	private String optionValue;
	private String optionNameStr;
	private String url;
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public String getOptionNameStr() {
		return optionNameStr;
	}
	public void setOptionNameStr(String optionNameStr) {
		this.optionNameStr = optionNameStr;
	}
	public UserSiteOptionPojo(int optionId, String optionName,
			String optionValue, String optionNameStr) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
		this.optionValue = optionValue;
		this.optionNameStr = optionNameStr;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
