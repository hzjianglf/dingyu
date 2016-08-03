package com.wxpt.site.entity;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields

	private Integer menuId;
	private String menuName;
	private String menuType;
	private Integer menuParentId;
	private String menuUrl;
	private String menuKey;

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(String menuName) {
		this.menuName = menuName;
	}

	/** full constructor */
	public Menu(String menuName, String menuType, Integer menuParentId,
			String menuUrl, String menuKey) {
		this.menuName = menuName;
		this.menuType = menuType;
		this.menuParentId = menuParentId;
		this.menuUrl = menuUrl;
		this.menuKey = menuKey;
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

	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuParentId() {
		return this.menuParentId;
	}

	public void setMenuParentId(Integer menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuKey() {
		return this.menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

}