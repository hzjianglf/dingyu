package com.wxpt.site.entity.pojo;

import java.util.List;
import com.wxpt.site.entity.Logistics;
import com.wxpt.site.entity.Member;

public class order2 {
	private List<product2> lproduct;
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
	public List<product2> getLproduct() {
		return lproduct;
	}
	public void setLproduct(List<product2> lproduct) {
		this.lproduct = lproduct;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Logistics getLogistics() {
		return logistics;
	}
	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getOrderProductId() {
		return orderProductId;
	}
	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getReceivePerson() {
		return receivePerson;
	}
	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getReceivePhone() {
		return receivePhone;
	}
	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}
	public Integer getSendType() {
		return sendType;
	}
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	public String getOrderLeave() {
		return orderLeave;
	}
	public void setOrderLeave(String orderLeave) {
		this.orderLeave = orderLeave;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
}
