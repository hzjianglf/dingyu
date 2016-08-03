package com.ticket.master.entity;

/**
 * FlightDiscount entity. @author MyEclipse Persistence Tools
 */

public class FlightDiscount implements java.io.Serializable {

	// Fields

	private Integer flightDiscountId;
	private String startTime;
	private String endTime;
	private String startCity;
	private String airway;
	private String cabin;
	private String discount;
	private String endCity;

	// Constructors

	/** default constructor */
	public FlightDiscount() {
	}

	/** full constructor */
	public FlightDiscount(String startTime, String endTime, String startCity,
			String airway, String cabin, String discount, String endCity) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.startCity = startCity;
		this.airway = airway;
		this.cabin = cabin;
		this.discount = discount;
		this.endCity = endCity;
	}

	// Property accessors

	public Integer getFlightDiscountId() {
		return this.flightDiscountId;
	}

	public void setFlightDiscountId(Integer flightDiscountId) {
		this.flightDiscountId = flightDiscountId;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartCity() {
		return this.startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getAirway() {
		return this.airway;
	}

	public void setAirway(String airway) {
		this.airway = airway;
	}

	public String getCabin() {
		return this.cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getEndCity() {
		return this.endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

}