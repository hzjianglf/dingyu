package com.wxpt.site.entity.pojo;

public class TongjiStaticsPojo {
	private int pageCount;//总图文数
	private int forwardUserCount;//共享总人数
	private int sendUserCount;//转发个人总数
	private int sendFriendCount;//转发朋友圈总人数
	private int sendWeiboCount;//转发微博总数
	private String pageTitle;
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getForwardUserCount() {
		return forwardUserCount;
	}
	public void setForwardUserCount(int forwardUserCount) {
		this.forwardUserCount = forwardUserCount;
	}
	public int getSendUserCount() {
		return sendUserCount;
	}
	public void setSendUserCount(int sendUserCount) {
		this.sendUserCount = sendUserCount;
	}
	public int getSendFriendCount() {
		return sendFriendCount;
	}
	public void setSendFriendCount(int sendFriendCount) {
		this.sendFriendCount = sendFriendCount;
	}
	public int getSendWeiboCount() {
		return sendWeiboCount;
	}
	public void setSendWeiboCount(int sendWeiboCount) {
		this.sendWeiboCount = sendWeiboCount;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public TongjiStaticsPojo() {
		// TODO Auto-generated constructor stub
	}
	public TongjiStaticsPojo(int pageCount, int forwardUserCount,
			int sendUserCount, int sendFriendCount, int sendWeiboCount,
			String pageTitle) {
		super();
		this.pageCount = pageCount;
		this.forwardUserCount = forwardUserCount;
		this.sendUserCount = sendUserCount;
		this.sendFriendCount = sendFriendCount;
		this.sendWeiboCount = sendWeiboCount;
		this.pageTitle = pageTitle;
	}
	
	
}
