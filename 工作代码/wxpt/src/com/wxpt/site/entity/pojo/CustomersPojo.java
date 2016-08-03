package com.wxpt.site.entity.pojo;



public class CustomersPojo {
	private Integer customersId;
	private String areaName;
	private String industryName;
	private String cantonName;
	private int areaId;
	private int industryId;
	private int cantonId;
	private String customersName;
	private String customersAddress;
	private String model;
	private String count;
	private String introduce;
	private String locationX;
	private String locationY;
	private String addTime;
	private Integer state;
	private String fromUsername;
	private String customersNo;
	private String customersImage;
	
	public CustomersPojo(Integer customersId, String areaName,
			String industryName, String cantonName, String customersName,
			String customersAddress, String model, String count,
			String introduce, String locationX, String locationY,
			String addTime, Integer state, String fromUsername) {
		super();
		this.customersId = customersId;
		this.areaName = areaName;
		this.industryName = industryName;
		this.cantonName = cantonName;
		
		this.customersName = customersName;
		this.customersAddress = customersAddress;
		this.model = model;
		this.count = count;
		this.introduce = introduce;
		this.locationX = locationX;
		this.locationY = locationY;
		this.addTime = addTime;
		this.state = state;
		this.fromUsername = fromUsername;
	}
	public Integer getCustomersId() {
		return customersId;
	}
	public void setCustomersId(Integer customersId) {
		this.customersId = customersId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getCantonName() {
		return cantonName;
	}
	public void setCantonName(String cantonName) {
		this.cantonName = cantonName;
	}
	public String getCustomersName() {
		return customersName;
	}
	public void setCustomersName(String customersName) {
		this.customersName = customersName;
	}
	public String getCustomersAddress() {
		return customersAddress;
	}
	public void setCustomersAddress(String customersAddress) {
		this.customersAddress = customersAddress;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getLocationX() {
		return locationX;
	}
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	public String getLocationY() {
		return locationY;
	}
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getFromUsername() {
		return fromUsername;
	}
	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}
	public String getCustomersNo() {
		return customersNo;
	}
	public void setCustomersNo(String customersNo) {
		this.customersNo = customersNo;
	}
	public String getCustomersImage() {
		return customersImage;
	}
	public void setCustomersImage(String customersImage) {
		this.customersImage = customersImage;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getIndustryId() {
		return industryId;
	}
	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}
	public int getCantonId() {
		return cantonId;
	}
	public void setCantonId(int cantonId) {
		this.cantonId = cantonId;
	}

}
