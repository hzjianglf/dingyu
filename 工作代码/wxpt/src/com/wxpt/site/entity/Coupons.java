package com.wxpt.site.entity;

/**
 * Coupons entity. @author MyEclipse Persistence Tools
 */

public class Coupons implements java.io.Serializable {

	// Fields

	private Integer couponsId;
	private Member member;
	private Activity activity;
	private String couponsTitle;
	private String couponsContent;
	private String couponsRemark;
	private String couponsStandby;

	// Constructors

	/** default constructor */
	public Coupons() {
	}

	/** full constructor */
	public Coupons(Member member, Activity activity, String couponsTitle,
			String couponsContent, String couponsRemark, String couponsStandby) {
		this.member = member;
		this.activity = activity;
		this.couponsTitle = couponsTitle;
		this.couponsContent = couponsContent;
		this.couponsRemark = couponsRemark;
		this.couponsStandby = couponsStandby;
	}

	// Property accessors

	public Integer getCouponsId() {
		return this.couponsId;
	}

	public void setCouponsId(Integer couponsId) {
		this.couponsId = couponsId;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getCouponsTitle() {
		return this.couponsTitle;
	}

	public void setCouponsTitle(String couponsTitle) {
		this.couponsTitle = couponsTitle;
	}

	public String getCouponsContent() {
		return this.couponsContent;
	}

	public void setCouponsContent(String couponsContent) {
		this.couponsContent = couponsContent;
	}

	public String getCouponsRemark() {
		return this.couponsRemark;
	}

	public void setCouponsRemark(String couponsRemark) {
		this.couponsRemark = couponsRemark;
	}

	public String getCouponsStandby() {
		return this.couponsStandby;
	}

	public void setCouponsStandby(String couponsStandby) {
		this.couponsStandby = couponsStandby;
	}

}