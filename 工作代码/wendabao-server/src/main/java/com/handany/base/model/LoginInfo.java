package com.handany.base.model;

import java.io.Serializable;

/**
 * 用户登录信息
 * @author lhb
 *
 */
public class LoginInfo implements Serializable{

	//省编号
	private String region1;
	//市编号
	private String region2;
	//县区编号
	private String region3;
	
	//登录经度
	private String longitude;
	//登录纬度
	private String latitude;
	//登录地址
	private String address;
	public String getRegion1() {
		return region1;
	}
	public void setRegion1(String region1) {
		this.region1 = region1;
	}
	public String getRegion2() {
		return region2;
	}
	public void setRegion2(String region2) {
		this.region2 = region2;
	}
	public String getRegion3() {
		return region3;
	}
	public void setRegion3(String region3) {
		this.region3 = region3;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
