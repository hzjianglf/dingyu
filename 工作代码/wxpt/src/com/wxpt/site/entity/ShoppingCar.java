package com.wxpt.site.entity;

/**
 * ShoppingCar entity. @author MyEclipse Persistence Tools
 */

public class ShoppingCar implements java.io.Serializable {

	// Fields

	private Integer shoppingId;
	private Member member;
	private Product product;
	private Integer productSum;

	// Constructors

	/** default constructor */
	public ShoppingCar() {
	}

	/** full constructor */
	public ShoppingCar(Member member, Product product, Integer productSum) {
		this.member = member;
		this.product = product;
		this.productSum = productSum;
	}

	// Property accessors

	public Integer getShoppingId() {
		return this.shoppingId;
	}

	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getProductSum() {
		return this.productSum;
	}

	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}

}