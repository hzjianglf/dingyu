package com.ticket.master.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String password;
	private String companyName;
	private String companyAbbreviation;
	private String businessLicense;
	private String adress;
	private String responsiblePerson;
	private String idNo;
	private String responsiblePhone;
	private String qq;
	private String emergencyContact;
	private String contactAddress;
	private Integer userType;
	private String companyTel;
	private Integer roleId;
	private Integer userParentId;
	private String addTime;
	private String updateTime;
	private Integer userState;
	private String discount;
	private Set roles = new HashSet(0);
	private Set userInterfaces = new HashSet(0);
	private Set interfaceLogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, Integer userType,
			String discount) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.discount = discount;
	}

	/** full constructor */
	public User(String username, String password, String companyName,
			String companyAbbreviation, String businessLicense, String adress,
			String responsiblePerson, String idNo, String responsiblePhone,
			String qq, String emergencyContact, String contactAddress,
			Integer userType, String companyTel, Integer roleId,
			Integer userParentId, String addTime, String updateTime,
			Integer userState, String discount, Set roles, Set userInterfaces,
			Set interfaceLogs) {
		this.username = username;
		this.password = password;
		this.companyName = companyName;
		this.companyAbbreviation = companyAbbreviation;
		this.businessLicense = businessLicense;
		this.adress = adress;
		this.responsiblePerson = responsiblePerson;
		this.idNo = idNo;
		this.responsiblePhone = responsiblePhone;
		this.qq = qq;
		this.emergencyContact = emergencyContact;
		this.contactAddress = contactAddress;
		this.userType = userType;
		this.companyTel = companyTel;
		this.roleId = roleId;
		this.userParentId = userParentId;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.userState = userState;
		this.discount = discount;
		this.roles = roles;
		this.userInterfaces = userInterfaces;
		this.interfaceLogs = interfaceLogs;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAbbreviation() {
		return this.companyAbbreviation;
	}

	public void setCompanyAbbreviation(String companyAbbreviation) {
		this.companyAbbreviation = companyAbbreviation;
	}

	public String getBusinessLicense() {
		return this.businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getResponsiblePerson() {
		return this.responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getResponsiblePhone() {
		return this.responsiblePhone;
	}

	public void setResponsiblePhone(String responsiblePhone) {
		this.responsiblePhone = responsiblePhone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmergencyContact() {
		return this.emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getCompanyTel() {
		return this.companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserParentId() {
		return this.userParentId;
	}

	public void setUserParentId(Integer userParentId) {
		this.userParentId = userParentId;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserState() {
		return this.userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Set getRoles() {
		return this.roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public Set getUserInterfaces() {
		return this.userInterfaces;
	}

	public void setUserInterfaces(Set userInterfaces) {
		this.userInterfaces = userInterfaces;
	}

	public Set getInterfaceLogs() {
		return this.interfaceLogs;
	}

	public void setInterfaceLogs(Set interfaceLogs) {
		this.interfaceLogs = interfaceLogs;
	}

}