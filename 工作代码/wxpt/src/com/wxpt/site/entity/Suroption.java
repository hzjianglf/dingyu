package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Suroption entity. @author MyEclipse Persistence Tools
 */

public class Suroption implements java.io.Serializable {

	// Fields

	private Integer optionId;
	private Surquestion surquestion;
	private String optionNumber;
	private String optionContent;
	private Integer optionCode;
	private String optionAddtime;
	private String optionUpdatetime;
	private Set surrecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public Suroption() {
	}

	/** minimal constructor */
	public Suroption(Surquestion surquestion, String optionNumber,
			String optionContent, Integer optionCode) {
		this.surquestion = surquestion;
		this.optionNumber = optionNumber;
		this.optionContent = optionContent;
		this.optionCode = optionCode;
	}

	/** full constructor */
	public Suroption(Surquestion surquestion, String optionNumber,
			String optionContent, Integer optionCode, String optionAddtime,
			String optionUpdatetime, Set surrecords) {
		this.surquestion = surquestion;
		this.optionNumber = optionNumber;
		this.optionContent = optionContent;
		this.optionCode = optionCode;
		this.optionAddtime = optionAddtime;
		this.optionUpdatetime = optionUpdatetime;
		this.surrecords = surrecords;
	}

	// Property accessors

	public Integer getOptionId() {
		return this.optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Surquestion getSurquestion() {
		return this.surquestion;
	}

	public void setSurquestion(Surquestion surquestion) {
		this.surquestion = surquestion;
	}

	public String getOptionNumber() {
		return this.optionNumber;
	}

	public void setOptionNumber(String optionNumber) {
		this.optionNumber = optionNumber;
	}

	public String getOptionContent() {
		return this.optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public Integer getOptionCode() {
		return this.optionCode;
	}

	public void setOptionCode(Integer optionCode) {
		this.optionCode = optionCode;
	}

	public String getOptionAddtime() {
		return this.optionAddtime;
	}

	public void setOptionAddtime(String optionAddtime) {
		this.optionAddtime = optionAddtime;
	}

	public String getOptionUpdatetime() {
		return this.optionUpdatetime;
	}

	public void setOptionUpdatetime(String optionUpdatetime) {
		this.optionUpdatetime = optionUpdatetime;
	}

	public Set getSurrecords() {
		return this.surrecords;
	}

	public void setSurrecords(Set surrecords) {
		this.surrecords = surrecords;
	}

}