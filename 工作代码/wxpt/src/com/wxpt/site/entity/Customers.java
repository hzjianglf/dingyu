package com.wxpt.site.entity;

/**
 * Customers entity. @author MyEclipse Persistence Tools
 */

public class Customers implements java.io.Serializable {

	// Fields

	private Integer customersId;
	private Area area;
	private Industry industry;
	private Canton canton;
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

	// Constructors

	/** default constructor */
	public Customers() {
	}

	/** minimal constructor */
	public Customers(Area area, Industry industry, String customersName,
			Integer state, String customersNo) {
		this.area = area;
		this.industry = industry;
		this.customersName = customersName;
		this.state = state;
		this.customersNo = customersNo;
	}

	/** full constructor */
	public Customers(Area area, Industry industry, Canton canton,
			String customersName, String customersAddress, String model,
			String count, String introduce, String locationX, String locationY,
			String addTime, Integer state, String fromUsername,
			String customersNo, String customersImage) {
		this.area = area;
		this.industry = industry;
		this.canton = canton;
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
		this.customersNo = customersNo;
		this.customersImage = customersImage;
	}

	// Property accessors

	public Integer getCustomersId() {
		return this.customersId;
	}

	public void setCustomersId(Integer customersId) {
		this.customersId = customersId;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Industry getIndustry() {
		return this.industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public String getCustomersName() {
		return this.customersName;
	}

	public void setCustomersName(String customersName) {
		this.customersName = customersName;
	}

	public String getCustomersAddress() {
		return this.customersAddress;
	}

	public void setCustomersAddress(String customersAddress) {
		this.customersAddress = customersAddress;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getLocationX() {
		return this.locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return this.locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFromUsername() {
		return this.fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getCustomersNo() {
		return this.customersNo;
	}

	public void setCustomersNo(String customersNo) {
		this.customersNo = customersNo;
	}

	public String getCustomersImage() {
		return this.customersImage;
	}

	public void setCustomersImage(String customersImage) {
		this.customersImage = customersImage;
	}

}