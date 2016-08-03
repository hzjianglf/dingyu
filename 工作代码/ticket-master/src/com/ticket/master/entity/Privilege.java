package com.ticket.master.entity;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege implements java.io.Serializable {

	// Fields

	private Integer privilegeId;
	private String privilegeName;
	private String privilegeStatement;
	private Integer privilegeParentId;

	// Constructors

	/** default constructor */
	public Privilege() {
	}

	/** full constructor */
	public Privilege(String privilegeName, String privilegeStatement,
			Integer privilegeParentId) {
		this.privilegeName = privilegeName;
		this.privilegeStatement = privilegeStatement;
		this.privilegeParentId = privilegeParentId;
	}

	// Property accessors

	public Integer getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeName() {
		return this.privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getPrivilegeStatement() {
		return this.privilegeStatement;
	}

	public void setPrivilegeStatement(String privilegeStatement) {
		this.privilegeStatement = privilegeStatement;
	}

	public Integer getPrivilegeParentId() {
		return this.privilegeParentId;
	}

	public void setPrivilegeParentId(Integer privilegeParentId) {
		this.privilegeParentId = privilegeParentId;
	}

}