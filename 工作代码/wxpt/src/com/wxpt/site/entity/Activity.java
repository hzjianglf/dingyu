package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Activity entity. @author MyEclipse Persistence Tools
 */

public class Activity implements java.io.Serializable {

	// Fields

	private Integer activityId;
	private String activityTitle;
	private String activityContent;
	private String activityStarttime;
	private String activityEndtime;
	private String imageUrl;
	private Set couponses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Activity() {
	}

	/** full constructor */
	public Activity(String activityTitle, String activityContent,
			String activityStarttime, String activityEndtime, String imageUrl,
			Set couponses) {
		this.activityTitle = activityTitle;
		this.activityContent = activityContent;
		this.activityStarttime = activityStarttime;
		this.activityEndtime = activityEndtime;
		this.imageUrl = imageUrl;
		this.couponses = couponses;
	}

	// Property accessors

	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityTitle() {
		return this.activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityContent() {
		return this.activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public String getActivityStarttime() {
		return this.activityStarttime;
	}

	public void setActivityStarttime(String activityStarttime) {
		this.activityStarttime = activityStarttime;
	}

	public String getActivityEndtime() {
		return this.activityEndtime;
	}

	public void setActivityEndtime(String activityEndtime) {
		this.activityEndtime = activityEndtime;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set getCouponses() {
		return this.couponses;
	}

	public void setCouponses(Set couponses) {
		this.couponses = couponses;
	}

}