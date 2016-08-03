package com.wxpt.site.entity;

/**
 * PageStatistics entity. @author MyEclipse Persistence Tools
 */

public class PageStatistics implements java.io.Serializable {

	// Fields

	private Integer pageStatisticsId;
	private Integer pageId;
	private String forwardUserId;
	private String forwardTime;
	private Integer forwardType;
	private String pageTitle;
	private String forwardUrl;

	// Constructors

	/** default constructor */
	public PageStatistics() {
	}

	/** full constructor */
	public PageStatistics(Integer pageId, String forwardUserId,
			String forwardTime, Integer forwardType, String pageTitle,
			String forwardUrl) {
		this.pageId = pageId;
		this.forwardUserId = forwardUserId;
		this.forwardTime = forwardTime;
		this.forwardType = forwardType;
		this.pageTitle = pageTitle;
		this.forwardUrl = forwardUrl;
	}

	// Property accessors

	public Integer getPageStatisticsId() {
		return this.pageStatisticsId;
	}

	public void setPageStatisticsId(Integer pageStatisticsId) {
		this.pageStatisticsId = pageStatisticsId;
	}

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getForwardUserId() {
		return this.forwardUserId;
	}

	public void setForwardUserId(String forwardUserId) {
		this.forwardUserId = forwardUserId;
	}

	public String getForwardTime() {
		return this.forwardTime;
	}

	public void setForwardTime(String forwardTime) {
		this.forwardTime = forwardTime;
	}

	public Integer getForwardType() {
		return this.forwardType;
	}

	public void setForwardType(Integer forwardType) {
		this.forwardType = forwardType;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getForwardUrl() {
		return this.forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

}