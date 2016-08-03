package com.wxpt.site.entity;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private Integer roleParentId;
	private String roleStatement;
	private String privilegeList;

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String roleName, Integer roleParentId, String roleStatement,
			String privilegeList) {
		this.roleName = roleName;
		this.roleParentId = roleParentId;
		this.roleStatement = roleStatement;
		this.privilegeList = privilegeList;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleParentId() {
		return this.roleParentId;
	}

	public void setRoleParentId(Integer roleParentId) {
		this.roleParentId = roleParentId;
	}

	public String getRoleStatement() {
		return this.roleStatement;
	}

	public void setRoleStatement(String roleStatement) {
		this.roleStatement = roleStatement;
	}

	public String getPrivilegeList() {
		return this.privilegeList;
	}

	public void setPrivilegeList(String privilegeList) {
		this.privilegeList = privilegeList;
	}

}