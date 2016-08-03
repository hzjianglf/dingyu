package com.wxpt.site.entity.pojo;

import java.sql.Timestamp;



public class Pageabout {
	private Integer pageId;
	private String pageTitle;
	private Integer pageMenu;
	private Integer pageGroup;
	private Timestamp pageTime;
	private String meatKey;
	private String pageCTime;
	private String meatKey1;
	private String metaDetail;
	private String meatKey2;
	private String metaValue;
	private String menuName;
	private String metaImageValue;
	private String address;
	private String hotline;//热线电话
	private String telephone;//手机
	private String landline;//座机
	private String localtion;//地图中心坐标
	private String email;//emial
	private String contacts;
	private String childMenuName;
	private int childMenuID;
	private String msgContent;
	private String msgContacts;
	private String msgWay;
	private String pageUrl;
	
	
	
	
	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgContacts() {
		return msgContacts;
	}

	public void setMsgContacts(String msgContacts) {
		this.msgContacts = msgContacts;
	}

	public String getMsgWay() {
		return msgWay;
	}

	public void setMsgWay(String msgWay) {
		this.msgWay = msgWay;
	}

	public String getChildMenuName() {
		return childMenuName;
	}

	public void setChildMenuName(String childMenuName) {
		this.childMenuName = childMenuName;
	}

	public int getChildMenuID() {
		return childMenuID;
	}

	public void setChildMenuID(int childMenuID) {
		this.childMenuID = childMenuID;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getMetaImageValue() {
		return metaImageValue;
	}

	public void setMetaImageValue(String metaImageValue) {
		this.metaImageValue = metaImageValue;
	}

	public Pageabout() {
		// TODO Auto-generated constructor stub
	}
	
	public Pageabout(Integer pageId, String pageTitle,
			Integer pageMenu, Integer pageGroup, Timestamp pageTime,
			String meatKey, String pageCTime, String meatKey1,
			String metaDetail, String meatKey2, String metaValue,
			String menuName) {
		super();
		this.pageId = pageId;
		this.pageTitle = pageTitle;
		this.pageMenu = pageMenu;
		this.pageGroup = pageGroup;
		this.pageTime = pageTime;
		this.meatKey = meatKey;
		this.pageCTime = pageCTime;
		this.meatKey1 = meatKey1;
		this.metaDetail = metaDetail;
		this.meatKey2 = meatKey2;
		this.metaValue = metaValue;
		this.menuName = menuName;
	}
	public String getPageCTime() {
		return pageCTime;
	}
	public void setPageCTime(String pageCTime) {
		this.pageCTime = pageCTime;
	}
	public String getMeatKey1() {
		return meatKey1;
	}
	public void setMeatKey1(String meatKey1) {
		this.meatKey1 = meatKey1;
	}
	public String getMetaDetail() {
		return metaDetail;
	}
	public void setMetaDetail(String metaDetail) {
		this.metaDetail = metaDetail;
	}
	public String getMeatKey2() {
		return meatKey2;
	}
	public void setMeatKey2(String meatKey2) {
		this.meatKey2 = meatKey2;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public Integer getPageMenu() {
		return pageMenu;
	}
	public void setPageMenu(Integer pageMenu) {
		this.pageMenu = pageMenu;
	}
	public Integer getPageGroup() {
		return pageGroup;
	}
	public void setPageGroup(Integer pageGroup) {
		this.pageGroup = pageGroup;
	}
	public Timestamp getPageTime() {
		return pageTime;
	}
	public void setPageTime(Timestamp pageTime) {
		this.pageTime = pageTime;
	}
	public String getMeatKey() {
		return meatKey;
	}
	public void setMeatKey(String meatKey) {
		this.meatKey = meatKey;
	}
	public String getMetaValue() {
		return metaValue;
	}
	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getLocaltion() {
		return localtion;
	}

	public void setLocaltion(String localtion) {
		this.localtion = localtion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
