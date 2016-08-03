package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Logistics entity. @author MyEclipse Persistence Tools
 */

public class Logistics implements java.io.Serializable {

	// Fields

	private Integer logisticsId;
	private String logisticsWay;
	private Double logisticsPrice;
	private Set orders = new HashSet(0);

	// Constructors

	/** default constructor */
	public Logistics() {
	}

	/** minimal constructor */
	public Logistics(String logisticsWay, Double logisticsPrice) {
		this.logisticsWay = logisticsWay;
		this.logisticsPrice = logisticsPrice;
	}

	/** full constructor */
	public Logistics(String logisticsWay, Double logisticsPrice, Set orders) {
		this.logisticsWay = logisticsWay;
		this.logisticsPrice = logisticsPrice;
		this.orders = orders;
	}

	// Property accessors

	public Integer getLogisticsId() {
		return this.logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getLogisticsWay() {
		return this.logisticsWay;
	}

	public void setLogisticsWay(String logisticsWay) {
		this.logisticsWay = logisticsWay;
	}

	public Double getLogisticsPrice() {
		return this.logisticsPrice;
	}

	public void setLogisticsPrice(Double logisticsPrice) {
		this.logisticsPrice = logisticsPrice;
	}

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

}