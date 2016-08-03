package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Surquestion entity. @author MyEclipse Persistence Tools
 */

public class Surquestion implements java.io.Serializable {

	// Fields

	private Integer surquestionId;
	private String surquestionNumber;
	private String surquestionContent;
	private Integer surquestionType;
	private String surquestionAddtime;
	private String surquestionUpdatetime;
	private Set suroptions = new HashSet(0);
	private Set surrecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public Surquestion() {
	}

	/** minimal constructor */
	public Surquestion(String surquestionNumber, String surquestionContent,
			Integer surquestionType) {
		this.surquestionNumber = surquestionNumber;
		this.surquestionContent = surquestionContent;
		this.surquestionType = surquestionType;
	}

	/** full constructor */
	public Surquestion(String surquestionNumber, String surquestionContent,
			Integer surquestionType, String surquestionAddtime,
			String surquestionUpdatetime, Set suroptions, Set surrecords) {
		this.surquestionNumber = surquestionNumber;
		this.surquestionContent = surquestionContent;
		this.surquestionType = surquestionType;
		this.surquestionAddtime = surquestionAddtime;
		this.surquestionUpdatetime = surquestionUpdatetime;
		this.suroptions = suroptions;
		this.surrecords = surrecords;
	}

	// Property accessors

	public Integer getSurquestionId() {
		return this.surquestionId;
	}

	public void setSurquestionId(Integer surquestionId) {
		this.surquestionId = surquestionId;
	}

	public String getSurquestionNumber() {
		return this.surquestionNumber;
	}

	public void setSurquestionNumber(String surquestionNumber) {
		this.surquestionNumber = surquestionNumber;
	}

	public String getSurquestionContent() {
		return this.surquestionContent;
	}

	public void setSurquestionContent(String surquestionContent) {
		this.surquestionContent = surquestionContent;
	}

	public Integer getSurquestionType() {
		return this.surquestionType;
	}

	public void setSurquestionType(Integer surquestionType) {
		this.surquestionType = surquestionType;
	}

	public String getSurquestionAddtime() {
		return this.surquestionAddtime;
	}

	public void setSurquestionAddtime(String surquestionAddtime) {
		this.surquestionAddtime = surquestionAddtime;
	}

	public String getSurquestionUpdatetime() {
		return this.surquestionUpdatetime;
	}

	public void setSurquestionUpdatetime(String surquestionUpdatetime) {
		this.surquestionUpdatetime = surquestionUpdatetime;
	}

	public Set getSuroptions() {
		return this.suroptions;
	}

	public void setSuroptions(Set suroptions) {
		this.suroptions = suroptions;
	}

	public Set getSurrecords() {
		return this.surrecords;
	}

	public void setSurrecords(Set surrecords) {
		this.surrecords = surrecords;
	}

}