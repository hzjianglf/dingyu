package com.wxpt.site.entity.pojo;

public class UserSiteMenuPojo {
	private int menuId;
	private String menuName;
	private int menuParent;
	private int menuGroup;
	private String menuNameStr;
	private String menuNameID;
	private int menuImageState;
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuParent() {
		return menuParent;
	}
	public void setMenuParent(int menuParent) {
		this.menuParent = menuParent;
	}
	public int getMenuGroup() {
		return menuGroup;
	}
	public void setMenuGroup(int menuGroup) {
		this.menuGroup = menuGroup;
	}
	public String getMenuNameStr() {
		return menuNameStr;
	}
	public void setMenuNameStr(String menuNameStr) {
		this.menuNameStr = menuNameStr;
	}
	
	public String getMenuNameID() {
		return menuNameID;
	}
	public void setMenuNameID(String menuNameID) {
		this.menuNameID = menuNameID;
	}
	public UserSiteMenuPojo(int menuId, String menuName, int menuParent,
			int menuGroup, String menuNameStr,String menuNameID) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuParent = menuParent;
		this.menuGroup = menuGroup;
		this.menuNameStr = menuNameStr;
		this.menuNameID = menuNameID;
	}
	public int getMenuImageState() {
		return menuImageState;
	}
	public void setMenuImageState(int menuImageState) {
		this.menuImageState = menuImageState;
	}
	
}
