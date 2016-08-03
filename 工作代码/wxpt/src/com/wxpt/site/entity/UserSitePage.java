package com.wxpt.site.entity;

import java.sql.Timestamp;

/**
 * UserSitePage entity. @author MyEclipse Persistence Tools
 */

public class UserSitePage implements java.io.Serializable {

	// Fields

	private Integer pageId;
	private UserSiteMenu userSiteMenu;
	private String pageTitle;
	private Integer pageGroup;
	private Timestamp pageTime;
	private String pageUrl;

	// Constructors

	/** default constructor */
	public UserSitePage() {
	}

	/** minimal constructor */
	public UserSitePage(UserSiteMenu userSiteMenu) {
		this.userSiteMenu = userSiteMenu;
	}

	/** full constructor */
	public UserSitePage(UserSiteMenu userSiteMenu, String pageTitle,
			Integer pageGroup, Timestamp pageTime, String pageUrl) {
		this.userSiteMenu = userSiteMenu;
		this.pageTitle = pageTitle;
		this.pageGroup = pageGroup;
		this.pageTime = pageTime;
		this.pageUrl = pageUrl;
	}

	// Property accessors

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public UserSiteMenu getUserSiteMenu() {
		return this.userSiteMenu;
	}

	public void setUserSiteMenu(UserSiteMenu userSiteMenu) {
		this.userSiteMenu = userSiteMenu;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public Integer getPageGroup() {
		return this.pageGroup;
	}

	public void setPageGroup(Integer pageGroup) {
		this.pageGroup = pageGroup;
	}

	public Timestamp getPageTime() {
		return this.pageTime;
	}

	public void setPageTime(Timestamp pageTime) {
		this.pageTime = pageTime;
	}

	public String getPageUrl() {
		return this.pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

}