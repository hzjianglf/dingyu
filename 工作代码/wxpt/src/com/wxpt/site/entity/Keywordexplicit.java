package com.wxpt.site.entity;

/**
 * Keywordexplicit entity. @author MyEclipse Persistence Tools
 */

public class Keywordexplicit implements java.io.Serializable {

	// Fields

	private Integer explicitId;
	private Keywords keywords;
	private String econtent;
	private String ekey;
	private String addTime;
	private String einstruction;
	private String title;
	private String url;
	private Integer type;

	// Constructors

	/** default constructor */
	public Keywordexplicit() {
	}

	/** minimal constructor */
	public Keywordexplicit(Keywords keywords, String econtent, String ekey,
			String addTime, Integer type) {
		this.keywords = keywords;
		this.econtent = econtent;
		this.ekey = ekey;
		this.addTime = addTime;
		this.type = type;
	}

	/** full constructor */
	public Keywordexplicit(Keywords keywords, String econtent, String ekey,
			String addTime, String einstruction, String title, String url,
			Integer type) {
		this.keywords = keywords;
		this.econtent = econtent;
		this.ekey = ekey;
		this.addTime = addTime;
		this.einstruction = einstruction;
		this.title = title;
		this.url = url;
		this.type = type;
	}

	// Property accessors

	public Integer getExplicitId() {
		return this.explicitId;
	}

	public void setExplicitId(Integer explicitId) {
		this.explicitId = explicitId;
	}

	public Keywords getKeywords() {
		return this.keywords;
	}

	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	public String getEcontent() {
		return this.econtent;
	}

	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}

	public String getEkey() {
		return this.ekey;
	}

	public void setEkey(String ekey) {
		this.ekey = ekey;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getEinstruction() {
		return this.einstruction;
	}

	public void setEinstruction(String einstruction) {
		this.einstruction = einstruction;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}