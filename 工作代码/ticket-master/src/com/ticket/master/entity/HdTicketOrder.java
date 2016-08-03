package com.ticket.master.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * HdTicketOrder entity. @author MyEclipse Persistence Tools
 */

public class HdTicketOrder implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private String orderNo;
	private String airline;
	private Integer legType;
	private String depCity;
	private String arrCity;
	private String transferCity;
	private Date depDate;
	private Date bacDate;
	private Date transferDate;
	private Time depTime;
	private Time arrTime;
	private String distrRemark;
	private String term;
	private String flightNo;
	private String cabin;
	private Integer cabinNum;
	private String discount;
	private Double price;
	private String flightMod;
	private String contactMobile;
	private String contactName;
	private String contactPhone;
	private Integer passengerType;
	private String name;
	private String sex;
	private String mobileNum;
	private Date birthday;
	private String docType;
	private String docName;
	private Date docValid;
	private String insuranceCode;
	private Integer insurance;
	private String nationality;
	private String eatPrefe;
	private String seatPrefe;
	private String orderType;
	private String pnrno;
	private Integer status;
	private Timestamp payTime;
	private String warnMsg;
	private String warnError;
	private Integer delStatus;
	private Timestamp addTime;
	private String cancelTicketState;
	private String cancelTicketError;
	private String passengerId;
	private String deliveryType;
	private String addressee;
	private String postalCode;
	private String mailAddress;
	private String deliveryCity;
	private String deliveryAddress;
	private String deliveryTime;
	private String deliveryRemar;
	private Integer userId;

	// Constructors

	/** default constructor */
	public HdTicketOrder() {
	}

	/** full constructor */
	public HdTicketOrder(String orderNo, String airline, Integer legType,
			String depCity, String arrCity, String transferCity, Date depDate,
			Date bacDate, Date transferDate, Time depTime, Time arrTime,
			String distrRemark, String term, String flightNo, String cabin,
			Integer cabinNum, String discount, Double price, String flightMod,
			String contactMobile, String contactName, String contactPhone,
			Integer passengerType, String name, String sex, String mobileNum,
			Date birthday, String docType, String docName, Date docValid,
			String insuranceCode, Integer insurance, String nationality,
			String eatPrefe, String seatPrefe, String orderType, String pnrno,
			Integer status, Timestamp payTime, String warnMsg,
			String warnError, Integer delStatus, Timestamp addTime,
			String cancelTicketState, String cancelTicketError,
			String passengerId, String deliveryType, String addressee,
			String postalCode, String mailAddress, String deliveryCity,
			String deliveryAddress, String deliveryTime, String deliveryRemar,
			Integer userId) {
		this.orderNo = orderNo;
		this.airline = airline;
		this.legType = legType;
		this.depCity = depCity;
		this.arrCity = arrCity;
		this.transferCity = transferCity;
		this.depDate = depDate;
		this.bacDate = bacDate;
		this.transferDate = transferDate;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.distrRemark = distrRemark;
		this.term = term;
		this.flightNo = flightNo;
		this.cabin = cabin;
		this.cabinNum = cabinNum;
		this.discount = discount;
		this.price = price;
		this.flightMod = flightMod;
		this.contactMobile = contactMobile;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.passengerType = passengerType;
		this.name = name;
		this.sex = sex;
		this.mobileNum = mobileNum;
		this.birthday = birthday;
		this.docType = docType;
		this.docName = docName;
		this.docValid = docValid;
		this.insuranceCode = insuranceCode;
		this.insurance = insurance;
		this.nationality = nationality;
		this.eatPrefe = eatPrefe;
		this.seatPrefe = seatPrefe;
		this.orderType = orderType;
		this.pnrno = pnrno;
		this.status = status;
		this.payTime = payTime;
		this.warnMsg = warnMsg;
		this.warnError = warnError;
		this.delStatus = delStatus;
		this.addTime = addTime;
		this.cancelTicketState = cancelTicketState;
		this.cancelTicketError = cancelTicketError;
		this.passengerId = passengerId;
		this.deliveryType = deliveryType;
		this.addressee = addressee;
		this.postalCode = postalCode;
		this.mailAddress = mailAddress;
		this.deliveryCity = deliveryCity;
		this.deliveryAddress = deliveryAddress;
		this.deliveryTime = deliveryTime;
		this.deliveryRemar = deliveryRemar;
		this.userId = userId;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAirline() {
		return this.airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Integer getLegType() {
		return this.legType;
	}

	public void setLegType(Integer legType) {
		this.legType = legType;
	}

	public String getDepCity() {
		return this.depCity;
	}

	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}

	public String getArrCity() {
		return this.arrCity;
	}

	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}

	public String getTransferCity() {
		return this.transferCity;
	}

	public void setTransferCity(String transferCity) {
		this.transferCity = transferCity;
	}

	public Date getDepDate() {
		return this.depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public Date getBacDate() {
		return this.bacDate;
	}

	public void setBacDate(Date bacDate) {
		this.bacDate = bacDate;
	}

	public Date getTransferDate() {
		return this.transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Time getDepTime() {
		return this.depTime;
	}

	public void setDepTime(Time depTime) {
		this.depTime = depTime;
	}

	public Time getArrTime() {
		return this.arrTime;
	}

	public void setArrTime(Time arrTime) {
		this.arrTime = arrTime;
	}

	public String getDistrRemark() {
		return this.distrRemark;
	}

	public void setDistrRemark(String distrRemark) {
		this.distrRemark = distrRemark;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getFlightNo() {
		return this.flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getCabin() {
		return this.cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public Integer getCabinNum() {
		return this.cabinNum;
	}

	public void setCabinNum(Integer cabinNum) {
		this.cabinNum = cabinNum;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getFlightMod() {
		return this.flightMod;
	}

	public void setFlightMod(String flightMod) {
		this.flightMod = flightMod;
	}

	public String getContactMobile() {
		return this.contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Integer getPassengerType() {
		return this.passengerType;
	}

	public void setPassengerType(Integer passengerType) {
		this.passengerType = passengerType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobileNum() {
		return this.mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDocType() {
		return this.docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Date getDocValid() {
		return this.docValid;
	}

	public void setDocValid(Date docValid) {
		this.docValid = docValid;
	}

	public String getInsuranceCode() {
		return this.insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public Integer getInsurance() {
		return this.insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEatPrefe() {
		return this.eatPrefe;
	}

	public void setEatPrefe(String eatPrefe) {
		this.eatPrefe = eatPrefe;
	}

	public String getSeatPrefe() {
		return this.seatPrefe;
	}

	public void setSeatPrefe(String seatPrefe) {
		this.seatPrefe = seatPrefe;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPnrno() {
		return this.pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public String getWarnMsg() {
		return this.warnMsg;
	}

	public void setWarnMsg(String warnMsg) {
		this.warnMsg = warnMsg;
	}

	public String getWarnError() {
		return this.warnError;
	}

	public void setWarnError(String warnError) {
		this.warnError = warnError;
	}

	public Integer getDelStatus() {
		return this.delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getCancelTicketState() {
		return this.cancelTicketState;
	}

	public void setCancelTicketState(String cancelTicketState) {
		this.cancelTicketState = cancelTicketState;
	}

	public String getCancelTicketError() {
		return this.cancelTicketError;
	}

	public void setCancelTicketError(String cancelTicketError) {
		this.cancelTicketError = cancelTicketError;
	}

	public String getPassengerId() {
		return this.passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getAddressee() {
		return this.addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getDeliveryCity() {
		return this.deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryRemar() {
		return this.deliveryRemar;
	}

	public void setDeliveryRemar(String deliveryRemar) {
		this.deliveryRemar = deliveryRemar;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}