package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private Logistics logistics;
	private Member member;
	private String orderProductId;
	private String orderNum;
	private String orderTime;
	private Double payMoney;
	private String payTime;
	private Integer payType;
	private String receivePerson;
	private String receiveAddress;
	private String receivePhone;
	private Integer sendType;
	private String orderLeave;
	private Integer orderType;
	private String orderRemark;
	private Integer takeType;
	private String sendTime;
	private Set buyProducts = new HashSet(0);

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Member member, String orderProductId, String orderNum,
			String orderTime, Double payMoney, Integer payType,
			Integer sendType, String orderLeave) {
		this.member = member;
		this.orderProductId = orderProductId;
		this.orderNum = orderNum;
		this.orderTime = orderTime;
		this.payMoney = payMoney;
		this.payType = payType;
		this.sendType = sendType;
		this.orderLeave = orderLeave;
	}

	/** full constructor */
	public Order(Logistics logistics, Member member, String orderProductId,
			String orderNum, String orderTime, Double payMoney, String payTime,
			Integer payType, String receivePerson, String receiveAddress,
			String receivePhone, Integer sendType, String orderLeave,
			Integer orderType, String orderRemark, Integer takeType,
			String sendTime, Set buyProducts) {
		this.logistics = logistics;
		this.member = member;
		this.orderProductId = orderProductId;
		this.orderNum = orderNum;
		this.orderTime = orderTime;
		this.payMoney = payMoney;
		this.payTime = payTime;
		this.payType = payType;
		this.receivePerson = receivePerson;
		this.receiveAddress = receiveAddress;
		this.receivePhone = receivePhone;
		this.sendType = sendType;
		this.orderLeave = orderLeave;
		this.orderType = orderType;
		this.orderRemark = orderRemark;
		this.takeType = takeType;
		this.sendTime = sendTime;
		this.buyProducts = buyProducts;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Logistics getLogistics() {
		return this.logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getOrderProductId() {
		return this.orderProductId;
	}

	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Double getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public String getPayTime() {
		return this.payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getReceivePerson() {
		return this.receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getReceiveAddress() {
		return this.receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceivePhone() {
		return this.receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public Integer getSendType() {
		return this.sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public String getOrderLeave() {
		return this.orderLeave;
	}

	public void setOrderLeave(String orderLeave) {
		this.orderLeave = orderLeave;
	}

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getOrderRemark() {
		return this.orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Integer getTakeType() {
		return this.takeType;
	}

	public void setTakeType(Integer takeType) {
		this.takeType = takeType;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public Set getBuyProducts() {
		return this.buyProducts;
	}

	public void setBuyProducts(Set buyProducts) {
		this.buyProducts = buyProducts;
	}

}