package com.wxpt.site.entity.pojo;

import java.util.Date;

import com.wxpt.site.entity.Product;


public class DST {
	//会员表
	private Integer memberId;
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
	//商品表
	private Integer productId;
	private String productNum;
	private String productName;
	private Double price;
	private Integer sellNum;
	private String productDetails;
	private String productColor;
	private String productXimage;
	private String productDimage;
	//商品分类表
	private Integer productTypeId;
	private Integer parentId;
	private String typeName;
	//收藏表
	private Integer shoppingId;
	private Integer productSum;
	//订单表
	private Integer orderId;
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
	//物流方式
	private Integer logisticsId;
	private String logisticsWay;
	private Double logisticsPrice;
	//评论表
	private Integer appraiseId;
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWeixinId() {
		return weixinId;
	}
	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getMemberPoints() {
		return memberPoints;
	}
	public void setMemberPoints(Integer memberPoints) {
		this.memberPoints = memberPoints;
	}
	public String getSaveMoney() {
		return saveMoney;
	}
	public void setSaveMoney(String saveMoney) {
		this.saveMoney = saveMoney;
	}
	public Integer getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(Integer memberGrade) {
		this.memberGrade = memberGrade;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getSellNum() {
		return sellNum;
	}
	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}
	public String getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}
	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	public String getProductXimage() {
		return productXimage;
	}
	public void setProductXimage(String productXimage) {
		this.productXimage = productXimage;
	}
	public String getProductDimage() {
		return productDimage;
	}
	public void setProductDimage(String productDimage) {
		this.productDimage = productDimage;
	}
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getShoppingId() {
		return shoppingId;
	}
	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}
	public Integer getProductSum() {
		return productSum;
	}
	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public Integer getLogisticsId() {
		return logisticsId;
	}
	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}
	public String getLogisticsWay() {
		return logisticsWay;
	}
	public void setLogisticsWay(String logisticsWay) {
		this.logisticsWay = logisticsWay;
	}
	public Double getLogisticsPrice() {
		return logisticsPrice;
	}
	public void setLogisticsPrice(Double logisticsPrice) {
		this.logisticsPrice = logisticsPrice;
	}
	public Integer getAppraiseId() {
		return appraiseId;
	}
	public void setAppraiseId(Integer appraiseId) {
		this.appraiseId = appraiseId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getAppraiseContent() {
		return appraiseContent;
	}
	public void setAppraiseContent(String appraiseContent) {
		this.appraiseContent = appraiseContent;
	}
	public String getAppraiseTime() {
		return appraiseTime;
	}
	public void setAppraiseTime(String appraiseTime) {
		this.appraiseTime = appraiseTime;
	}
	public String getAppraiseAdd() {
		return appraiseAdd;
	}
	public void setAppraiseAdd(String appraiseAdd) {
		this.appraiseAdd = appraiseAdd;
	}
	public String getAppraiseAddTime() {
		return appraiseAddTime;
	}
	public void setAppraiseAddTime(String appraiseAddTime) {
		this.appraiseAddTime = appraiseAddTime;
	}
	private Product product;
	private String appraiseContent;
	private String appraiseTime;
	private String appraiseAdd;
	private String appraiseAddTime;
}
