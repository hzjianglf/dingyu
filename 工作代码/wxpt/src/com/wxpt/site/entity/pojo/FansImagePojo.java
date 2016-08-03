package com.wxpt.site.entity.pojo;


public class FansImagePojo {
	private Integer fansImageId;
	private int fansUserId;
	private String fansImageValue;
	private String imageUpdateTime;
	private String checkState;
	private int state;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Integer getFansImageId() {
		return fansImageId;
	}
	public void setFansImageId(Integer fansImageId) {
		this.fansImageId = fansImageId;
	}
	public int getFansUserId() {
		return fansUserId;
	}
	public void setFansUserId(int fansUserId) {
		this.fansUserId = fansUserId;
	}
	public String getFansImageValue() {
		return fansImageValue;
	}
	public void setFansImageValue(String fansImageValue) {
		this.fansImageValue = fansImageValue;
	}
	public String getImageUpdateTime() {
		return imageUpdateTime;
	}
	public void setImageUpdateTime(String imageUpdateTime) {
		this.imageUpdateTime = imageUpdateTime;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	public FansImagePojo() {
		// TODO Auto-generated constructor stub
	}
	public FansImagePojo(Integer fansImageId, int fansUserId,
			String fansImageValue, String imageUpdateTime, String checkState) {
		super();
		this.fansImageId = fansImageId;
		this.fansUserId = fansUserId;
		this.fansImageValue = fansImageValue;
		this.imageUpdateTime = imageUpdateTime;
		this.checkState = checkState;
	}
	
}
