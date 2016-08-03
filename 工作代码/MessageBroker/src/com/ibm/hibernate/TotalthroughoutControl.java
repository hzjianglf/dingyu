package com.ibm.hibernate;

/**
 * TotalthroughoutControl entity. @author MyEclipse Persistence Tools
 */

public class TotalthroughoutControl implements java.io.Serializable {

	// Fields

	private String tid;
	private String totalinterval;
	private String totalunit;
	private String totalthreshold;

	// Constructors

	/** default constructor */
	public TotalthroughoutControl() {
	}

	/** minimal constructor */
	public TotalthroughoutControl(String tid) {
		this.tid = tid;
	}

	/** full constructor */
	public TotalthroughoutControl(String tid, String totalinterval,
			String totalunit, String totalthreshold) {
		this.tid = tid;
		this.totalinterval = totalinterval;
		this.totalunit = totalunit;
		this.totalthreshold = totalthreshold;
	}

	// Property accessors

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTotalinterval() {
		return this.totalinterval;
	}

	public void setTotalinterval(String totalinterval) {
		this.totalinterval = totalinterval;
	}

	public String getTotalunit() {
		return this.totalunit;
	}

	public void setTotalunit(String totalunit) {
		this.totalunit = totalunit;
	}

	public String getTotalthreshold() {
		return this.totalthreshold;
	}

	public void setTotalthreshold(String totalthreshold) {
		this.totalthreshold = totalthreshold;
	}

}