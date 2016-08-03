package com.wxpt.site.entity;

/**
 * Storerecord entity. @author MyEclipse Persistence Tools
 */

public class Storerecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Member member;
	private Double money;
	private String recordtime;
	private String businessName;
	private Double presentMoney;

	// Constructors

	/** default constructor */
	public Storerecord() {
	}

	/** full constructor */
	public Storerecord(Member member, Double money, String recordtime,
			String businessName, Double presentMoney) {
		this.member = member;
		this.money = money;
		this.recordtime = recordtime;
		this.businessName = businessName;
		this.presentMoney = presentMoney;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getRecordtime() {
		return this.recordtime;
	}

	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Double getPresentMoney() {
		return this.presentMoney;
	}

	public void setPresentMoney(Double presentMoney) {
		this.presentMoney = presentMoney;
	}

}