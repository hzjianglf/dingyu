package com.ibm.hibernate;

/**
 * AlertInfo entity. @author MyEclipse Persistence Tools
 */

public class AlertInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String alertuuid;
	private String createtime;
	private String flowname;
	private String conditionitem;
	private String numberitem;
	private String curcount;
	private String description;

	// Constructors

	/** default constructor */
	public AlertInfo() {
	}

	/** minimal constructor */
	public AlertInfo(String alertuuid) {
		this.alertuuid = alertuuid;
	}

	/** full constructor */
	public AlertInfo(String alertuuid, String createtime, String conditionitem,
			String numberitem, String curcount, String description) {
		this.alertuuid = alertuuid;
		this.createtime = createtime;
		this.conditionitem = conditionitem;
		this.numberitem = numberitem;
		this.curcount = curcount;
		this.description = description;
	}

	// Property accessors

	public String getAlertuuid() {
		return this.alertuuid;
	}

	public void setAlertuuid(String alertuuid) {
		this.alertuuid = alertuuid;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getFlowname() {
		return flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	public String getConditionitem() {
		return this.conditionitem;
	}

	public void setConditionitem(String conditionitem) {
		this.conditionitem = conditionitem;
	}

	public String getNumberitem() {
		return this.numberitem;
	}

	public void setNumberitem(String numberitem) {
		this.numberitem = numberitem;
	}

	public String getCurcount() {
		return this.curcount;
	}

	public void setCurcount(String curcount) {
		this.curcount = curcount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}