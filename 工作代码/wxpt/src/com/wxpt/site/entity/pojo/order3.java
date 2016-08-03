package com.wxpt.site.entity.pojo;

import java.util.List;

import com.wxpt.site.entity.BuyProduct;
import com.wxpt.site.entity.Order;


public class order3 {
	private Order order;
	private List<BuyProduct> lbp;
	private List<BuyProduct2> lbp2;
	public List<BuyProduct2> getLbp2() {
		return lbp2;
	}
	public void setLbp2(List<BuyProduct2> lbp2) {
		this.lbp2 = lbp2;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<BuyProduct> getLbp() {
		return lbp;
	}
	public void setLbp(List<BuyProduct> lbp) {
		this.lbp = lbp;
	}
}
