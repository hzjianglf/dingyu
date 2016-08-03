package com.ibm.hibernate;

/**
 * Monitormain entity. @author MyEclipse Persistence Tools
 */

public class MonitorMain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String localtransactionid;
	private String brokername;
	private String execgroupname;
	private String flowname;
	private String flowtype;
	private String starttime;
	private String endtime;
	private Integer totalflowtime;
	private String returncode = "0";

	// Constructors

	/** default constructor */
	public MonitorMain() {
	}

	/** minimal constructor */
	public MonitorMain(String localtransactionid) {
		this.localtransactionid = localtransactionid;
	}

	/** full constructor */
	public MonitorMain(String localtransactionid, String brokername,
			String execgroupname, String flowname, String flowtype,
			String starttime, String endtime, Integer totalflowtime,
			String returncode) {
		this.localtransactionid = localtransactionid;
		this.brokername = brokername;
		this.execgroupname = execgroupname;
		this.flowname = flowname;
		this.flowtype = flowtype;
		this.starttime = starttime;
		this.endtime = endtime;
		this.totalflowtime = totalflowtime;
		this.returncode = returncode;
	}

	// Property accessors

	public String getLocaltransactionid() {
		return this.localtransactionid;
	}

	public void setLocaltransactionid(String localtransactionid) {
		this.localtransactionid = localtransactionid;
	}

	public String getBrokername() {
		return this.brokername;
	}

	public void setBrokername(String brokername) {
		this.brokername = brokername;
	}

	public String getExecgroupname() {
		return this.execgroupname;
	}

	public void setExecgroupname(String execgroupname) {
		this.execgroupname = execgroupname;
	}

	public String getFlowname() {
		return this.flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	public String getFlowtype() {
		return this.flowtype;
	}

	public void setFlowtype(String flowtype) {
		this.flowtype = flowtype;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Integer getTotalflowtime() {
		return this.totalflowtime;
	}

	public void setTotalflowtime(Integer totalflowtime) {
		this.totalflowtime = totalflowtime;
	}

	public String getReturncode() {
		return this.returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

}