package com.wxpt.site.entity.pojo;

public class TongjiPojo {
	private int pageStatisticsId;
	private int pageId;
	private String forwardUserId;
	private String forwardTime;
	private Integer forwardType;
	private String pageTitle;
	private String forwardUrl;
	private String typeStr;
	private String forwardUserIdStr;
	private String forwardUserName;
	private String partName;
	
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public int getPageStatisticsId() {
		return pageStatisticsId;
	}
	public void setPageStatisticsId(int pageStatisticsId) {
		this.pageStatisticsId = pageStatisticsId;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public String getForwardUserId() {
		return forwardUserId;
	}
	public void setForwardUserId(String forwardUserId) {
		this.forwardUserId = forwardUserId;
	}
	public String getForwardTime() {
		return forwardTime;
	}
	public void setForwardTime(String forwardTime) {
		this.forwardTime = forwardTime;
	}
	public Integer getForwardType() {
		return forwardType;
	}
	public void setForwardType(Integer forwardType) {
		this.forwardType = forwardType;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getForwardUserIdStr() {
		return forwardUserIdStr;
	}
	public void setForwardUserIdStr(String forwardUserIdStr) {
		this.forwardUserIdStr = forwardUserIdStr;
	}
	public String getForwardUserName() {
		return forwardUserName;
	}
	public void setForwardUserName(String forwardUserName) {
		this.forwardUserName = forwardUserName;
	}
	public TongjiPojo(int pageStatisticsId, int pageId, String forwardUserId,
			String forwardTime, Integer forwardType, String pageTitle,
			String forwardUrl, String typeStr, String forwardUserIdStr,
			String forwardUserName) {
		super();
		this.pageStatisticsId = pageStatisticsId;
		this.pageId = pageId;
		this.forwardUserId = forwardUserId;
		this.forwardTime = forwardTime;
		this.forwardType = forwardType;
		this.pageTitle = pageTitle;
		this.forwardUrl = forwardUrl;
		this.typeStr = typeStr;
		this.forwardUserIdStr = forwardUserIdStr;
		this.forwardUserName = forwardUserName;
	}
	public TongjiPojo() {
		// TODO Auto-generated constructor stub
	}
}
