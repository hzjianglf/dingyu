package com.wxpt.site.entity;

import java.util.Date;

/**
 * Integrals entity. @author MyEclipse Persistence Tools
 */

public class Integrals implements java.io.Serializable {

	// Fields

	private Integer integralsId;
	private Member member;
	private Integer integralsOne;
	private Date integralsTime;
	private String integralsBusiness;
	private String integralsRemark;

	// Constructors

	/** default constructor */
	public Integrals() {
	}

	/** minimal constructor */
	public Integrals(Member member) {
		this.member = member;
	}

	/** full constructor */
	public Integrals(Member member, Integer integralsOne, Date integralsTime,
			String integralsBusiness, String integralsRemark) {
		this.member = member;
		this.integralsOne = integralsOne;
		this.integralsTime = integralsTime;
		this.integralsBusiness = integralsBusiness;
		this.integralsRemark = integralsRemark;
	}

	// Property accessors

	public Integer getIntegralsId() {
		return this.integralsId;
	}

	public void setIntegralsId(Integer integralsId) {
		this.integralsId = integralsId;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getIntegralsOne() {
		return this.integralsOne;
	}

	public void setIntegralsOne(Integer integralsOne) {
		this.integralsOne = integralsOne;
	}

	public Date getIntegralsTime() {
		return this.integralsTime;
	}

	public void setIntegralsTime(Date integralsTime) {
		this.integralsTime = integralsTime;
	}

	public String getIntegralsBusiness() {
		return this.integralsBusiness;
	}

	public void setIntegralsBusiness(String integralsBusiness) {
		this.integralsBusiness = integralsBusiness;
	}

	public String getIntegralsRemark() {
		return this.integralsRemark;
	}

	public void setIntegralsRemark(String integralsRemark) {
		this.integralsRemark = integralsRemark;
	}

}