package com.wxpt.site.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Member entity. @author MyEclipse Persistence Tools
 */

public class Member implements java.io.Serializable {

	// Fields

	private Integer memberId;
	private String cardId;
	private String password;
	private String weixinId;
	private String businessId;
	private String username;
	private Integer sex;
	private Integer age;
	private Date addTime;
	private Date endTime;
	private String address;
	private String phone;
	private Integer memberPoints;
	private String saveMoney;
	private Integer memberGrade;
	private Integer memberFreeze;
	private String weixinHao;
	private String bumen;
	private Set couponses = new HashSet(0);
	private Set shoppingAddresses = new HashSet(0);
	private Set storerecords = new HashSet(0);
	private Set shoppingCars = new HashSet(0);
	private Set appraises = new HashSet(0);
	private Set integralses = new HashSet(0);
	private Set orders = new HashSet(0);
	private Set messages = new HashSet(0);

	// Constructors

	/** default constructor */
	public Member() {
	}

	/** full constructor */
	public Member(String cardId, String password, String weixinId,
			String businessId, String username, Integer sex, Integer age,
			Date addTime, Date endTime, String address, String phone,
			Integer memberPoints, String saveMoney, Integer memberGrade,
			Integer memberFreeze, String weixinHao, String bumen,
			Set couponses, Set shoppingAddresses, Set storerecords,
			Set shoppingCars, Set appraises, Set integralses, Set orders,
			Set messages) {
		this.cardId = cardId;
		this.password = password;
		this.weixinId = weixinId;
		this.businessId = businessId;
		this.username = username;
		this.sex = sex;
		this.age = age;
		this.addTime = addTime;
		this.endTime = endTime;
		this.address = address;
		this.phone = phone;
		this.memberPoints = memberPoints;
		this.saveMoney = saveMoney;
		this.memberGrade = memberGrade;
		this.memberFreeze = memberFreeze;
		this.weixinHao = weixinHao;
		this.bumen = bumen;
		this.couponses = couponses;
		this.shoppingAddresses = shoppingAddresses;
		this.storerecords = storerecords;
		this.shoppingCars = shoppingCars;
		this.appraises = appraises;
		this.integralses = integralses;
		this.orders = orders;
		this.messages = messages;
	}

	// Property accessors

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWeixinId() {
		return this.weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getMemberPoints() {
		return this.memberPoints;
	}

	public void setMemberPoints(Integer memberPoints) {
		this.memberPoints = memberPoints;
	}

	public String getSaveMoney() {
		return this.saveMoney;
	}

	public void setSaveMoney(String saveMoney) {
		this.saveMoney = saveMoney;
	}

	public Integer getMemberGrade() {
		return this.memberGrade;
	}

	public void setMemberGrade(Integer memberGrade) {
		this.memberGrade = memberGrade;
	}

	public Integer getMemberFreeze() {
		return this.memberFreeze;
	}

	public void setMemberFreeze(Integer memberFreeze) {
		this.memberFreeze = memberFreeze;
	}

	public String getWeixinHao() {
		return this.weixinHao;
	}

	public void setWeixinHao(String weixinHao) {
		this.weixinHao = weixinHao;
	}

	public String getBumen() {
		return this.bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}

	public Set getCouponses() {
		return this.couponses;
	}

	public void setCouponses(Set couponses) {
		this.couponses = couponses;
	}

	public Set getShoppingAddresses() {
		return this.shoppingAddresses;
	}

	public void setShoppingAddresses(Set shoppingAddresses) {
		this.shoppingAddresses = shoppingAddresses;
	}

	public Set getStorerecords() {
		return this.storerecords;
	}

	public void setStorerecords(Set storerecords) {
		this.storerecords = storerecords;
	}

	public Set getShoppingCars() {
		return this.shoppingCars;
	}

	public void setShoppingCars(Set shoppingCars) {
		this.shoppingCars = shoppingCars;
	}

	public Set getAppraises() {
		return this.appraises;
	}

	public void setAppraises(Set appraises) {
		this.appraises = appraises;
	}

	public Set getIntegralses() {
		return this.integralses;
	}

	public void setIntegralses(Set integralses) {
		this.integralses = integralses;
	}

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

}