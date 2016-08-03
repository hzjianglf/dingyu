package com.ticket.master.entity;

/**
 * UserInterface entity. @author MyEclipse Persistence Tools
 */

public class UserInterface implements java.io.Serializable {

	// Fields

	private Integer userInterfaceId;
	private Interfacs interfacs;
	private User user;
	private Integer interfaceAccount;
	private Integer interfaceState;

	// Constructors

	/** default constructor */
	public UserInterface() {
	}

	/** minimal constructor */
	public UserInterface(Interfacs interfacs, User user,
			Integer interfaceAccount) {
		this.interfacs = interfacs;
		this.user = user;
		this.interfaceAccount = interfaceAccount;
	}

	/** full constructor */
	public UserInterface(Interfacs interfacs, User user,
			Integer interfaceAccount, Integer interfaceState) {
		this.interfacs = interfacs;
		this.user = user;
		this.interfaceAccount = interfaceAccount;
		this.interfaceState = interfaceState;
	}

	// Property accessors

	public Integer getUserInterfaceId() {
		return this.userInterfaceId;
	}

	public void setUserInterfaceId(Integer userInterfaceId) {
		this.userInterfaceId = userInterfaceId;
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

	public Integer getInterfaceAccount() {
		return this.interfaceAccount;
	}

	public void setInterfaceAccount(Integer interfaceAccount) {
		this.interfaceAccount = interfaceAccount;
	}

	public Integer getInterfaceState() {
		return this.interfaceState;
	}

	public void setInterfaceState(Integer interfaceState) {
		this.interfaceState = interfaceState;
	}

}