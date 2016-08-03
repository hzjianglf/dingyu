package com.ticket.master.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Interfacs entity. @author MyEclipse Persistence Tools
 */

public class Interfacs implements java.io.Serializable {

	// Fields

	private Integer interfaceId;
	private String interfaceName;
	private String interfaceNameMarke;
	private Integer interfaceGroup;
	private String interfaceGroupName;
	private Integer interfaceCount;
	private Set userInterfaces = new HashSet(0);
	private Set interfaceLogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Interfacs() {
	}

	/** minimal constructor */
	public Interfacs(String interfaceName, String interfaceNameMarke,
			Integer interfaceGroup, String interfaceGroupName,
			Integer interfaceCount) {
		this.interfaceName = interfaceName;
		this.interfaceNameMarke = interfaceNameMarke;
		this.interfaceGroup = interfaceGroup;
		this.interfaceGroupName = interfaceGroupName;
		this.interfaceCount = interfaceCount;
	}

	/** full constructor */
	public Interfacs(String interfaceName, String interfaceNameMarke,
			Integer interfaceGroup, String interfaceGroupName,
			Integer interfaceCount, Set userInterfaces, Set interfaceLogs) {
		this.interfaceName = interfaceName;
		this.interfaceNameMarke = interfaceNameMarke;
		this.interfaceGroup = interfaceGroup;
		this.interfaceGroupName = interfaceGroupName;
		this.interfaceCount = interfaceCount;
		this.userInterfaces = userInterfaces;
		this.interfaceLogs = interfaceLogs;
	}

	// Property accessors

	public Integer getInterfaceId() {
		return this.interfaceId;
	}

	public void setInterfaceId(Integer interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getInterfaceName() {
		return this.interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceNameMarke() {
		return this.interfaceNameMarke;
	}

	public void setInterfaceNameMarke(String interfaceNameMarke) {
		this.interfaceNameMarke = interfaceNameMarke;
	}

	public Integer getInterfaceGroup() {
		return this.interfaceGroup;
	}

	public void setInterfaceGroup(Integer interfaceGroup) {
		this.interfaceGroup = interfaceGroup;
	}

	public String getInterfaceGroupName() {
		return this.interfaceGroupName;
	}

	public void setInterfaceGroupName(String interfaceGroupName) {
		this.interfaceGroupName = interfaceGroupName;
	}

	public Integer getInterfaceCount() {
		return this.interfaceCount;
	}

	public void setInterfaceCount(Integer interfaceCount) {
		this.interfaceCount = interfaceCount;
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