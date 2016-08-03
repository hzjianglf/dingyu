package com.wxpt.site.entity.pojo;

import java.util.List;

import com.wxpt.site.entity.Coupons;

public class Coupons2 {

	private int couponsId;
	private String activityTitle;
	private String couponsTitle;
	private String couponsContent;
	private String couponsRemark;
	private String couponsStandby;
	private String activityStarttime;
	private String activityEndtime;
	private List<Coupons> coupons;
	
	
	public List<Coupons> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<Coupons> coupons) {
		this.coupons = coupons;
	}
	public int getCouponsId() {
		return couponsId;
	}
	public void setCouponsId(int couponsId) {
		this.couponsId = couponsId;
	}
	public String getCouponsTitle() {
		return couponsTitle;
	}
	public void setCouponsTitle(String couponsTitle) {
		this.couponsTitle = couponsTitle;
	}
	public String getCouponsContent() {
		return couponsContent;
	}
	public void setCouponsContent(String couponsContent) {
		this.couponsContent = couponsContent;
	}
	public String getCouponsRemark() {
		return couponsRemark;
	}
	public void setCouponsRemark(String couponsRemark) {
		this.couponsRemark = couponsRemark;
	}
	public String getCouponsStandby() {
		return couponsStandby;
	}
	public void setCouponsStandby(String couponsStandby) {
		this.couponsStandby = couponsStandby;
	}
	public String getActivityStarttime() {
		return activityStarttime;
	}
	public void setActivityStarttime(String activityStarttime) {
		this.activityStarttime = activityStarttime;
	}
	public String getActivityEndtime() {
		return activityEndtime;
	}
	public void setActivityEndtime(String activityEndtime) {
		this.activityEndtime = activityEndtime;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
}
