package com.wxpt.site.entity;

/**
 * ShoppingAddress entity. @author MyEclipse Persistence Tools
 */

public class ShoppingAddress implements java.io.Serializable {

	// Fields

	private Integer shoppingAddressId;
	private Member member;
	private String name;
	private String address;
	private String phone;

	// Constructors

	/** default constructor */
	public ShoppingAddress() {
	}

	/** full constructor */
	public ShoppingAddress(Member member, String name, String address,
			String phone) {
		this.member = member;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	// Property accessors

	public Integer getShoppingAddressId() {
		return this.shoppingAddressId;
	}

	public void setShoppingAddressId(Integer shoppingAddressId) {
		this.shoppingAddressId = shoppingAddressId;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

}