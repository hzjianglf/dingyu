package com.wxpt.site.entity;

/**
 * Idiom entity. @author MyEclipse Persistence Tools
 */

public class Idiom implements java.io.Serializable {

	// Fields

	private Integer idiomId;
	private String idiomName;
	private String paraphrase;

	// Constructors

	/** default constructor */
	public Idiom() {
	}

	/** full constructor */
	public Idiom(String idiomName, String paraphrase) {
		this.idiomName = idiomName;
		this.paraphrase = paraphrase;
	}

	// Property accessors

	public Integer getIdiomId() {
		return this.idiomId;
	}

	public void setIdiomId(Integer idiomId) {
		this.idiomId = idiomId;
	}

	public String getIdiomName() {
		return this.idiomName;
	}

	public void setIdiomName(String idiomName) {
		this.idiomName = idiomName;
	}

	public String getParaphrase() {
		return this.paraphrase;
	}

	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}

}