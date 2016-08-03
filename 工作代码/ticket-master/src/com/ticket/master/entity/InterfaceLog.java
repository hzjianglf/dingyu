package com.ticket.master.entity;

import java.sql.Timestamp;

/**
 * InterfaceLog entity. @author MyEclipse Persistence Tools
 */

public class InterfaceLog implements java.io.Serializable {

	// Fields

	private Integer interfaceLogId;
	private Interfacs interfacs;
	private User user;
	private Timestamp invokingTime;
	private String requestContent;
	private String returnContent;
	private String disposeReturnXml;

	// Constructors

	/** default constructor */
	public InterfaceLog() {
	}

	/** minimal constructor */
	public InterfaceLog(Interfacs interfacs, Timestamp invokingTime) {
		this.interfacs = interfacs;
		this.invokingTime = invokingTime;
	}

	/** full constructor */
	public InterfaceLog(Interfacs interfacs, User user, Timestamp invokingTime,
			String requestContent, String returnContent, String disposeReturnXml) {
		this.interfacs = interfacs;
		this.user = user;
		this.invokingTime = invokingTime;
		this.requestContent = requestContent;
		this.returnContent = returnContent;
		this.disposeReturnXml = disposeReturnXml;
	}

	// Property accessors

	public Integer getInterfaceLogId() {
		return this.interfaceLogId;
	}

	public void setInterfaceLogId(Integer interfaceLogId) {
		this.interfaceLogId = interfaceLogId;
	}

	public Interfacs getInterfacs() {
		return this.interfacs;
	}

	public void setInterfacs(Interfacs interfacs) {
		this.interfacs = interfacs;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getInvokingTime() {
		return this.invokingTime;
	}

	public void setInvokingTime(Timestamp invokingTime) {
		this.invokingTime = invokingTime;
	}

	public String getRequestContent() {
		return this.requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getReturnContent() {
		return this.returnContent;
	}

	public void setReturnContent(String returnContent) {
		this.returnContent = returnContent;
	}

	public String getDisposeReturnXml() {
		return this.disposeReturnXml;
	}

	public void setDisposeReturnXml(String disposeReturnXml) {
		this.disposeReturnXml = disposeReturnXml;
	}

}