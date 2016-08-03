package com.ticket.master.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Res entity. @author MyEclipse Persistence Tools
 */

public class Res implements java.io.Serializable {

	// Fields

	private Integer resId;
	private String departCity;
	private String arrivalCity;
	private Date departDate;
	private String airways;
	private String passengerType;
	private String cabinType;
	private String sortType;
	private Integer userId;
	private Set flightDatas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Res() {
	}

	/** minimal constructor */
	public Res(String departCity, String arrivalCity, Date departDate,
			String airways, String passengerType, String cabinType,
			String sortType, Integer userId) {
		this.departCity = departCity;
		this.arrivalCity = arrivalCity;
		this.departDate = departDate;
		this.airways = airways;
		this.passengerType = passengerType;
		this.cabinType = cabinType;
		this.sortType = sortType;
		this.userId = userId;
	}

	/** full constructor */
	public Res(String departCity, String arrivalCity, Date departDate,
			String airways, String passengerType, String cabinType,
			String sortType, Integer userId, Set flightDatas) {
		this.departCity = departCity;
		this.arrivalCity = arrivalCity;
		this.departDate = departDate;
		this.airways = airways;
		this.passengerType = passengerType;
		this.cabinType = cabinType;
		this.sortType = sortType;
		this.userId = userId;
		this.flightDatas = flightDatas;
	}

	// Property accessors

	public Integer getResId() {
		return this.resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getDepartCity() {
		return this.departCity;
	}

	public void setDepartCity(String departCity) {
		this.departCity = departCity;
	}

	public String getArrivalCity() {
		return this.arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public Date getDepartDate() {
		return this.departDate;
	}

	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}

	public String getAirways() {
		return this.airways;
	}

	public void setAirways(String airways) {
		this.airways = airways;
	}

	public String getPassengerType() {
		return this.passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public String getCabinType() {
		return this.cabinType;
	}

	public void setCabinType(String cabinType) {
		this.cabinType = cabinType;
	}

	public String getSortType() {
		return this.sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Set getFlightDatas() {
		return this.flightDatas;
	}

	public void setFlightDatas(Set flightDatas) {
		this.flightDatas = flightDatas;
	}

}