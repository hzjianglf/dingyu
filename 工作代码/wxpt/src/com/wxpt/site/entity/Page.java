package com.wxpt.site.entity;

/**
 * Page entity. @author MyEclipse Persistence Tools
 */

public class Page implements java.io.Serializable {

	// Fields

	private Integer pageId;
	private String pageTitle;
	private String pageAuthor;
	private String pageNoName;
	private String pageTime;
	private String pageImage;
	private String pageMetaValue;
	private String pageContent;
	private String pageUrl;
	private String pageJumpUrl;
	private String weixinNo;

	// Constructors

	/** default constructor */
	public Page() {
	}

	/** minimal constructor */
	public Page(String pageTitle, String pageNoName, String pageTime,
			String pageImage, String pageMetaValue, String pageContent,
			String pageJumpUrl, String weixinNo) {
		this.pageTitle = pageTitle;
		this.pageNoName = pageNoName;
		this.pageTime = pageTime;
		this.pageImage = pageImage;
		this.pageMetaValue = pageMetaValue;
		this.pageContent = pageContent;
		this.pageJumpUrl = pageJumpUrl;
		this.weixinNo = weixinNo;
	}

	/** full constructor */
	public Page(String pageTitle, String pageAuthor, String pageNoName,
			String pageTime, String pageImage, String pageMetaValue,
			String pageContent, String pageUrl, String pageJumpUrl,
			String weixinNo) {
		this.pageTitle = pageTitle;
		this.pageAuthor = pageAuthor;
		this.pageNoName = pageNoName;
		this.pageTime = pageTime;
		this.pageImage = pageImage;
		this.pageMetaValue = pageMetaValue;
		this.pageContent = pageContent;
		this.pageUrl = pageUrl;
		this.pageJumpUrl = pageJumpUrl;
		this.weixinNo = weixinNo;
	}

	// Property accessors

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageAuthor() {
		return this.pageAuthor;
	}

	public void setPageAuthor(String pageAuthor) {
		this.pageAuthor = pageAuthor;
	}

	public String getPageNoName() {
		return this.pageNoName;
	}

	public void setPageNoName(String pageNoName) {
		this.pageNoName = pageNoName;
	}

	public String getPageTime() {
		return this.pageTime;
	}

	public void setPageTime(String pageTime) {
		this.pageTime = pageTime;
	}

	public String getPageImage() {
		return this.pageImage;
	}

	public void setPageImage(String pageImage) {
		this.pageImage = pageImage;
	}

	public String getPageMetaValue() {
		return this.pageMetaValue;
	}

	public void setPageMetaValue(String pageMetaValue) {
		this.pageMetaValue = pageMetaValue;
	}

	public String getPageContent() {
		return this.pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getPageUrl() {
		return this.pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getPageJumpUrl() {
		return this.pageJumpUrl;
	}

	public void setPageJumpUrl(String pageJumpUrl) {
		this.pageJumpUrl = pageJumpUrl;
	}

	public String getWeixinNo() {
		return this.weixinNo;
	}

	public void setWeixinNo(String weixinNo) {
		this.weixinNo = weixinNo;
	}

}