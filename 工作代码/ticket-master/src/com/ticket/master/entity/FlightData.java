package com.ticket.master.entity;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FlightData entity. @author MyEclipse Persistence Tools
 */

public class FlightData implements java.io.Serializable {

	// Fields

	private Integer flightDataId;
	private Res res;
	private String departCity;
	private Date departDate;
	private Time departTime;
	private String meal;
	private String carrierFlightNo;
	private String arrivalCity;
	private Time arrivalTime;
	private String ETicket;
	private String flightModel;
	private Time flyTime;
	private String flightNo;
	private String airways;
	private String departTerminal;
	private String arrivalTerminal;
	private String airConstructionFee;
	private String stopOver;
	private String otherCabin;
	private String fuelTax;
	private String protocolNum;
	private String standPrice;
	private Set cabinDatas = new HashSet(0);

	// Constructors

	/** default constructor */
	public FlightData() {
	}

	/** minimal constructor */
	public FlightData(Res res, String departCity, Date departDate,
			Time departTime, String arrivalCity, Time arrivalTime,
			String flightNo, String airways, String airConstructionFee,
			String fuelTax, String standPrice) {
		this.res = res;
		this.departCity = departCity;
		this.departDate = departDate;
		this.departTime = departTime;
		this.arrivalCity = arrivalCity;
		this.arrivalTime = arrivalTime;
		this.flightNo = flightNo;
		this.airways = airways;
		this.airConstructionFee = airConstructionFee;
		this.fuelTax = fuelTax;
		this.standPrice = standPrice;
	}

	/** full constructor */
	public FlightData(Res res, String departCity, Date departDate,
			Time departTime, String meal, String carrierFlightNo,
			String arrivalCity, Time arrivalTime, String ETicket,
			String flightModel, Time flyTime, String flightNo, String airways,
			String departTerminal, String arrivalTerminal,
			String airConstructionFee, String stopOver, String otherCabin,
			String fuelTax, String protocolNum, String standPrice,
			Set cabinDatas) {
		this.res = res;
		this.departCity = departCity;
		this.departDate = departDate;
		this.departTime = departTime;
		this.meal = meal;
		this.carrierFlightNo = carrierFlightNo;
		this.arrivalCity = arrivalCity;
		this.arrivalTime = arrivalTime;
		this.ETicket = ETicket;
		this.flightModel = flightModel;
		this.flyTime = flyTime;
		this.flightNo = flightNo;
		this.airways = airways;
		this.departTerminal = departTerminal;
		this.arrivalTerminal = arrivalTerminal;
		this.airConstructionFee = airConstructionFee;
		this.stopOver = stopOver;
		this.otherCabin = otherCabin;
		this.fuelTax = fuelTax;
		this.protocolNum = protocolNum;
		this.standPrice = standPrice;
		this.cabinDatas = cabinDatas;
	}

	// Property accessors

	public Integer getFlightDataId() {
		return this.flightDataId;
	}

	public void setFlightDataId(Integer flightDataId) {
		this.flightDataId = flightDataId;
	}

	public Res getRes() {
		return this.res;
	}

	public void setRes(Res res) {
		this.res = res;
	}

	public String getDepartCity() {
		return this.departCity;
	}

	public void setDepartCity(String departCity) {
		this.departCity = departCity;
	}

	public Date getDepartDate() {
		return this.departDate;
	}

	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}

	public Time getDepartTime() {
		return this.departTime;
	}

	public void setDepartTime(Time departTime) {
		this.departTime = departTime;
	}

	public String getMeal() {
		return this.meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getCarrierFlightNo() {
		return this.carrierFlightNo;
	}

	public void setCarrierFlightNo(String carrierFlightNo) {
		this.carrierFlightNo = carrierFlightNo;
	}

	public String getArrivalCity() {
		return this.arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public Time getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getETicket() {
		return this.ETicket;
	}

	public void setETicket(String ETicket) {
		this.ETicket = ETicket;
	}

	public String getFlightModel() {
		return this.flightModel;
	}

	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}

	public Time getFlyTime() {
		return this.flyTime;
	}

	public void setFlyTime(Time flyTime) {
		this.flyTime = flyTime;
	}

	public String getFlightNo() {
		return this.flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getAirways() {
		return this.airways;
	}

	public void setAirways(String airways) {
		this.airways = airways;
	}

	public String getDepartTerminal() {
		return this.departTerminal;
	}

	public void setDepartTerminal(String departTerminal) {
		this.departTerminal = departTerminal;
	}

	public String getArrivalTerminal() {
		return this.arrivalTerminal;
	}

	public void setArrivalTerminal(String arrivalTerminal) {
		this.arrivalTerminal = arrivalTerminal;
	}

	public String getAirConstructionFee() {
		return this.airConstructionFee;
	}

	public void setAirConstructionFee(String airConstructionFee) {
		this.airConstructionFee = airConstructionFee;
	}

	public String getStopOver() {
		return this.stopOver;
	}

	public void setStopOver(String stopOver) {
		this.stopOver = stopOver;
	}

	public String getOtherCabin() {
		return this.otherCabin;
	}

	public void setOtherCabin(String otherCabin) {
		this.otherCabin = otherCabin;
	}

	public String getFuelTax() {
		return this.fuelTax;
	}

	public void setFuelTax(String fuelTax) {
		this.fuelTax = fuelTax;
	}

	public String getProtocolNum() {
		return this.protocolNum;
	}

	public void setProtocolNum(String protocolNum) {
		this.protocolNum = protocolNum;
	}

	public String getStandPrice() {
		return this.standPrice;
	}

	public void setStandPrice(String standPrice) {
		this.standPrice = standPrice;
	}

	public Set getCabinDatas() {
		return this.cabinDatas;
	}

	public void setCabinDatas(Set cabinDatas) {
		this.cabinDatas = cabinDatas;
	}

}