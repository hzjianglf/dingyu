package com.wxpt.site.entity;

/**
 * UserSitePagemeta entity. @author MyEclipse Persistence Tools
 */

public class UserSitePagemeta implements java.io.Serializable {

	// Fields

	private Integer metaId;
	private UserSitePage userSitePage;
	private String metaKey;
	private String metaValue;

	// Constructors

	/** default constructor */
	public UserSitePagemeta() {
	}

	/** minimal constructor */
	public UserSitePagemeta(UserSitePage userSitePage) {
		this.userSitePage = userSitePage;
	}

	/** full constructor */
	public UserSitePagemeta(UserSitePage userSitePage, String metaKey,
			String metaValue) {
		this.userSitePage = userSitePage;
		this.metaKey = metaKey;
		this.metaValue = metaValue;
	}

	// Property accessors

	public Integer getMetaId() {
		return this.metaId;
	}

	public void setMetaId(Integer metaId) {
		this.metaId = metaId;
	}

	public UserSitePage getUserSitePage() {
		return this.userSitePage;
	}

	public void setUserSitePage(UserSitePage userSitePage) {
		this.userSitePage = userSitePage;
	}

	public String getMetaKey() {
		return this.metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	public String getMetaValue() {
		return this.metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

}