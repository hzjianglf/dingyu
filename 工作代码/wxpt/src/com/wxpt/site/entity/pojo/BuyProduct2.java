package com.wxpt.site.entity.pojo;

import com.wxpt.site.entity.Order;
import com.wxpt.site.entity.Product;


public class BuyProduct2 {
	private Integer buyProductId;
	private Product product;
	private Order order;
	private Integer buyProductSum;
	private Integer type;
	public Integer getBuyProductId() {
		return buyProductId;
	}
	public void setBuyProductId(Integer buyProductId) {
		this.buyProductId = buyProductId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Integer getBuyProductSum() {
		return buyProductSum;
	}
	public void setBuyProductSum(Integer buyProductSum) {
		this.buyProductSum = buyProductSum;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
