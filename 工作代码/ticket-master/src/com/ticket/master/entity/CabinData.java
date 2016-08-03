package com.ticket.master.entity;

/**
 * CabinData entity. @author MyEclipse Persistence Tools
 */

public class CabinData implements java.io.Serializable {

	// Fields

	private Integer cabinDataId;
	private FlightData flightData;
	private String cabin;
	private String foreignRemark;
	private String cabinType;
	private String cabinName;
	private String rewardRates;
	private String hkForeignRemark;
	private String hkRewardRates;
	private String hkPrice;
	private String hkCashBack;
	private String hkPolicyId;
	private String hkDiscount;
	private String hlForeignRemark;
	private String hlRewardRates;
	private String hlPrice;
	private String hlCashBack;
	private String hlLastSeatNum;
	private String hlNote;
	private String hlPolicyId;
	private String hlDiscount;
	private String isPublishTariff;
	private Double jsPrice;
	private Double price;
	private String isSpecialPolicy;
	private String platStayMoney;
	private String platPolicyType;
	private String platPolicyId;
	private String platPolicyRemark;
	private String platPolicyRewardRates;
	private String platOth;
	private String cashBack;
	private String teamForeignRemark;
	private String teamRewardRates;
	private Double teamPrice;
	private String teamPnr;
	private String teamCashBack;
	private String teamNote;
	private String teamPolicyId;
	private String teamDiscount;
	private String teamLastSeatNum;
	private String teamTotalSeatNum;
	private String note;
	private String policyId;
	private String discount;
	private String seatNum;

	// Constructors

	/** default constructor */
	public CabinData() {
	}

	/** minimal constructor */
	public CabinData(FlightData flightData, String cabin, String cabinType,
			String rewardRates, String isPublishTariff, Double jsPrice,
			Double price, String isSpecialPolicy, String cashBack, String note,
			String seatNum) {
		this.flightData = flightData;
		this.cabin = cabin;
		this.cabinType = cabinType;
		this.rewardRates = rewardRates;
		this.isPublishTariff = isPublishTariff;
		this.jsPrice = jsPrice;
		this.price = price;
		this.isSpecialPolicy = isSpecialPolicy;
		this.cashBack = cashBack;
		this.note = note;
		this.seatNum = seatNum;
	}

	/** full constructor */
	public CabinData(FlightData flightData, String cabin, String foreignRemark,
			String cabinType, String cabinName, String rewardRates,
			String hkForeignRemark, String hkRewardRates, String hkPrice,
			String hkCashBack, String hkPolicyId, String hkDiscount,
			String hlForeignRemark, String hlRewardRates, String hlPrice,
			String hlCashBack, String hlLastSeatNum, String hlNote,
			String hlPolicyId, String hlDiscount, String isPublishTariff,
			Double jsPrice, Double price, String isSpecialPolicy,
			String platStayMoney, String platPolicyType, String platPolicyId,
			String platPolicyRemark, String platPolicyRewardRates,
			String platOth, String cashBack, String teamForeignRemark,
			String teamRewardRates, Double teamPrice, String teamPnr,
			String teamCashBack, String teamNote, String teamPolicyId,
			String teamDiscount, String teamLastSeatNum,
			String teamTotalSeatNum, String note, String policyId,
			String discount, String seatNum) {
		this.flightData = flightData;
		this.cabin = cabin;
		this.foreignRemark = foreignRemark;
		this.cabinType = cabinType;
		this.cabinName = cabinName;
		this.rewardRates = rewardRates;
		this.hkForeignRemark = hkForeignRemark;
		this.hkRewardRates = hkRewardRates;
		this.hkPrice = hkPrice;
		this.hkCashBack = hkCashBack;
		this.hkPolicyId = hkPolicyId;
		this.hkDiscount = hkDiscount;
		this.hlForeignRemark = hlForeignRemark;
		this.hlRewardRates = hlRewardRates;
		this.hlPrice = hlPrice;
		this.hlCashBack = hlCashBack;
		this.hlLastSeatNum = hlLastSeatNum;
		this.hlNote = hlNote;
		this.hlPolicyId = hlPolicyId;
		this.hlDiscount = hlDiscount;
		this.isPublishTariff = isPublishTariff;
		this.jsPrice = jsPrice;
		this.price = price;
		this.isSpecialPolicy = isSpecialPolicy;
		this.platStayMoney = platStayMoney;
		this.platPolicyType = platPolicyType;
		this.platPolicyId = platPolicyId;
		this.platPolicyRemark = platPolicyRemark;
		this.platPolicyRewardRates = platPolicyRewardRates;
		this.platOth = platOth;
		this.cashBack = cashBack;
		this.teamForeignRemark = teamForeignRemark;
		this.teamRewardRates = teamRewardRates;
		this.teamPrice = teamPrice;
		this.teamPnr = teamPnr;
		this.teamCashBack = teamCashBack;
		this.teamNote = teamNote;
		this.teamPolicyId = teamPolicyId;
		this.teamDiscount = teamDiscount;
		this.teamLastSeatNum = teamLastSeatNum;
		this.teamTotalSeatNum = teamTotalSeatNum;
		this.note = note;
		this.policyId = policyId;
		this.discount = discount;
		this.seatNum = seatNum;
	}

	// Property accessors

	public Integer getCabinDataId() {
		return this.cabinDataId;
	}

	public void setCabinDataId(Integer cabinDataId) {
		this.cabinDataId = cabinDataId;
	}

	public FlightData getFlightData() {
		return this.flightData;
	}

	public void setFlightData(FlightData flightData) {
		this.flightData = flightData;
	}

	public String getCabin() {
		return this.cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getForeignRemark() {
		return this.foreignRemark;
	}

	public void setForeignRemark(String foreignRemark) {
		this.foreignRemark = foreignRemark;
	}

	public String getCabinType() {
		return this.cabinType;
	}

	public void setCabinType(String cabinType) {
		this.cabinType = cabinType;
	}

	public String getCabinName() {
		return this.cabinName;
	}

	public void setCabinName(String cabinName) {
		this.cabinName = cabinName;
	}

	public String getRewardRates() {
		return this.rewardRates;
	}

	public void setRewardRates(String rewardRates) {
		this.rewardRates = rewardRates;
	}

	public String getHkForeignRemark() {
		return this.hkForeignRemark;
	}

	public void setHkForeignRemark(String hkForeignRemark) {
		this.hkForeignRemark = hkForeignRemark;
	}

	public String getHkRewardRates() {
		return this.hkRewardRates;
	}

	public void setHkRewardRates(String hkRewardRates) {
		this.hkRewardRates = hkRewardRates;
	}

	public String getHkPrice() {
		return this.hkPrice;
	}

	public void setHkPrice(String hkPrice) {
		this.hkPrice = hkPrice;
	}

	public String getHkCashBack() {
		return this.hkCashBack;
	}

	public void setHkCashBack(String hkCashBack) {
		this.hkCashBack = hkCashBack;
	}

	public String getHkPolicyId() {
		return this.hkPolicyId;
	}

	public void setHkPolicyId(String hkPolicyId) {
		this.hkPolicyId = hkPolicyId;
	}

	public String getHkDiscount() {
		return this.hkDiscount;
	}

	public void setHkDiscount(String hkDiscount) {
		this.hkDiscount = hkDiscount;
	}

	public String getHlForeignRemark() {
		return this.hlForeignRemark;
	}

	public void setHlForeignRemark(String hlForeignRemark) {
		this.hlForeignRemark = hlForeignRemark;
	}

	public String getHlRewardRates() {
		return this.hlRewardRates;
	}

	public void setHlRewardRates(String hlRewardRates) {
		this.hlRewardRates = hlRewardRates;
	}

	public String getHlPrice() {
		return this.hlPrice;
	}

	public void setHlPrice(String hlPrice) {
		this.hlPrice = hlPrice;
	}

	public String getHlCashBack() {
		return this.hlCashBack;
	}

	public void setHlCashBack(String hlCashBack) {
		this.hlCashBack = hlCashBack;
	}

	public String getHlLastSeatNum() {
		return this.hlLastSeatNum;
	}

	public void setHlLastSeatNum(String hlLastSeatNum) {
		this.hlLastSeatNum = hlLastSeatNum;
	}

	public String getHlNote() {
		return this.hlNote;
	}

	public void setHlNote(String hlNote) {
		this.hlNote = hlNote;
	}

	public String getHlPolicyId() {
		return this.hlPolicyId;
	}

	public void setHlPolicyId(String hlPolicyId) {
		this.hlPolicyId = hlPolicyId;
	}

	public String getHlDiscount() {
		return this.hlDiscount;
	}

	public void setHlDiscount(String hlDiscount) {
		this.hlDiscount = hlDiscount;
	}

	public String getIsPublishTariff() {
		return this.isPublishTariff;
	}

	public void setIsPublishTariff(String isPublishTariff) {
		this.isPublishTariff = isPublishTariff;
	}

	public Double getJsPrice() {
		return this.jsPrice;
	}

	public void setJsPrice(Double jsPrice) {
		this.jsPrice = jsPrice;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIsSpecialPolicy() {
		return this.isSpecialPolicy;
	}

	public void setIsSpecialPolicy(String isSpecialPolicy) {
		this.isSpecialPolicy = isSpecialPolicy;
	}

	public String getPlatStayMoney() {
		return this.platStayMoney;
	}

	public void setPlatStayMoney(String platStayMoney) {
		this.platStayMoney = platStayMoney;
	}

	public String getPlatPolicyType() {
		return this.platPolicyType;
	}

	public void setPlatPolicyType(String platPolicyType) {
		this.platPolicyType = platPolicyType;
	}

	public String getPlatPolicyId() {
		return this.platPolicyId;
	}

	public void setPlatPolicyId(String platPolicyId) {
		this.platPolicyId = platPolicyId;
	}

	public String getPlatPolicyRemark() {
		return this.platPolicyRemark;
	}

	public void setPlatPolicyRemark(String platPolicyRemark) {
		this.platPolicyRemark = platPolicyRemark;
	}

	public String getPlatPolicyRewardRates() {
		return this.platPolicyRewardRates;
	}

	public void setPlatPolicyRewardRates(String platPolicyRewardRates) {
		this.platPolicyRewardRates = platPolicyRewardRates;
	}

	public String getPlatOth() {
		return this.platOth;
	}

	public void setPlatOth(String platOth) {
		this.platOth = platOth;
	}

	public String getCashBack() {
		return this.cashBack;
	}

	public void setCashBack(String cashBack) {
		this.cashBack = cashBack;
	}

	public String getTeamForeignRemark() {
		return this.teamForeignRemark;
	}

	public void setTeamForeignRemark(String teamForeignRemark) {
		this.teamForeignRemark = teamForeignRemark;
	}

	public String getTeamRewardRates() {
		return this.teamRewardRates;
	}

	public void setTeamRewardRates(String teamRewardRates) {
		this.teamRewardRates = teamRewardRates;
	}

	public Double getTeamPrice() {
		return this.teamPrice;
	}

	public void setTeamPrice(Double teamPrice) {
		this.teamPrice = teamPrice;
	}

	public String getTeamPnr() {
		return this.teamPnr;
	}

	public void setTeamPnr(String teamPnr) {
		this.teamPnr = teamPnr;
	}

	public String getTeamCashBack() {
		return this.teamCashBack;
	}

	public void setTeamCashBack(String teamCashBack) {
		this.teamCashBack = teamCashBack;
	}

	public String getTeamNote() {
		return this.teamNote;
	}

	public void setTeamNote(String teamNote) {
		this.teamNote = teamNote;
	}

	public String getTeamPolicyId() {
		return this.teamPolicyId;
	}

	public void setTeamPolicyId(String teamPolicyId) {
		this.teamPolicyId = teamPolicyId;
	}

	public String getTeamDiscount() {
		return this.teamDiscount;
	}

	public void setTeamDiscount(String teamDiscount) {
		this.teamDiscount = teamDiscount;
	}

	public String getTeamLastSeatNum() {
		return this.teamLastSeatNum;
	}

	public void setTeamLastSeatNum(String teamLastSeatNum) {
		this.teamLastSeatNum = teamLastSeatNum;
	}

	public String getTeamTotalSeatNum() {
		return this.teamTotalSeatNum;
	}

	public void setTeamTotalSeatNum(String teamTotalSeatNum) {
		this.teamTotalSeatNum = teamTotalSeatNum;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPolicyId() {
		return this.policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getSeatNum() {
		return this.seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

}