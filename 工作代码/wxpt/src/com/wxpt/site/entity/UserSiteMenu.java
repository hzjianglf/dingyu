package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * UserSiteMenu entity. @author MyEclipse Persistence Tools
 */

public class UserSiteMenu implements java.io.Serializable {

	// Fields

	private Integer menuId;
	private String menuName;
	private Integer menuParent;
	private Integer menuGroup;
	private String imageValue;
	private Set userSitePages = new HashSet(0);

	// Constructors

	/** default constructor */
	public UserSiteMenu() {
	}

	/** full constructor */
	public UserSiteMenu(String menuName, Integer menuParent, Integer menuGroup,
			String imageValue, Set userSitePages) {
		this.menuName = menuName;
		this.menuParent = menuParent;
		this.menuGroup = menuGroup;
		this.imageValue = imageValue;
		this.userSitePages = userSitePages;
	}

	// Property accessors

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuParent() {
		return this.menuParent;
	}

	public void setMenuParent(Integer menuParent) {
		this.menuParent = menuParent;
	}

	public Integer getMenuGroup() {
		return this.menuGroup;
	}

	public void setMenuGroup(Integer menuGroup) {
		this.menuGroup = menuGroup;
	}

	public String getImageValue() {
		return this.imageValue;
	}

	public void setImageValue(String imageValue) {
		this.imageValue = imageValue;
	}

	public Set getUserSitePages() {
		return this.userSitePages;
	}

	public void setUserSitePages(Set userSitePages) {
		this.userSitePages = userSitePages;
	}

}