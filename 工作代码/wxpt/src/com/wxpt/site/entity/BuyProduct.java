package com.wxpt.site.entity;

/**
 * BuyProduct entity. @author MyEclipse Persistence Tools
 */

public class BuyProduct implements java.io.Serializable {

	// Fields

	private Integer buyProductId;
	private Product product;
	private Order order;
	private Integer buyProductSum;

	// Constructors

	/** default constructor */
	public BuyProduct() {
	}

	/** full constructor */
	public BuyProduct(Product product, Order order, Integer buyProductSum) {
		this.product = product;
		this.order = order;
		this.buyProductSum = buyProductSum;
	}

	// Property accessors

	public Integer getBuyProductId() {
		return this.buyProductId;
	}

	public void setBuyProductId(Integer buyProductId) {
		this.buyProductId = buyProductId;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getBuyProductSum() {
		return this.buyProductSum;
	}

	public void setBuyProductSum(Integer buyProductSum) {
		this.buyProductSum = buyProductSum;
	}

}