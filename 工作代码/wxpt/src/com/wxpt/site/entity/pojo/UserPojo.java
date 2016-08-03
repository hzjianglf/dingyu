package com.wxpt.site.entity.pojo;

public class UserPojo {
	private Integer userId;
	private String userNum;
	private String userName;
	private Integer count;
	public UserPojo() {
		// TODO Auto-generated constructor stub
	}
	
	public UserPojo(Integer userId, String userNum, String userName,
			Integer count) {
		super();
		this.userId = userId;
		this.userNum = userNum;
		this.userName = userName;
		this.count = count;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
