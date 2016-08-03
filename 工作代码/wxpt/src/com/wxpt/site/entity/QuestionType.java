package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * QuestionType entity. @author MyEclipse Persistence Tools
 */

public class QuestionType implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;
	private Integer typeNum;
	private Set questions = new HashSet(0);

	// Constructors

	/** default constructor */
	public QuestionType() {
	}

	/** minimal constructor */
	public QuestionType(String typeName, Integer typeNum) {
		this.typeName = typeName;
		this.typeNum = typeNum;
	}

	/** full constructor */
	public QuestionType(String typeName, Integer typeNum, Set questions) {
		this.typeName = typeName;
		this.typeNum = typeNum;
		this.questions = questions;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeNum() {
		return this.typeNum;
	}

	public void setTypeNum(Integer typeNum) {
		this.typeNum = typeNum;
	}

	public Set getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set questions) {
		this.questions = questions;
	}

}