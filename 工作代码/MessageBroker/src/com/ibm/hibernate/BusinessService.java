package com.ibm.hibernate;

/**
 * BusinessService entity. @author MyEclipse Persistence Tools
 */

public class BusinessService implements java.io.Serializable {

	// Fields

	private String servicename;
	private String flowname;
	private String description;

	// Constructors

	/** default constructor */
	public BusinessService() {
	}

	/** minimal constructor */
	public BusinessService(String servicename) {
		this.servicename = servicename;
	}

	/** full constructor */
	public BusinessService(String servicename, String flowname,
			String description) {
		this.servicename = servicename;
		this.flowname = flowname;
		this.description = description;
	}

	// Property accessors

	public String getServicename() {
		return this.servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getFlowname() {
		return flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}