package com.wxpt.site.entity;

/**
 * QuestionTishi entity. @author MyEclipse Persistence Tools
 */

public class QuestionTishi implements java.io.Serializable {

	// Fields

	private Integer questionTishiId;
	private String meiJiang;
	private String daCuo;
	private String chaoShi;
	private String choujiang;
	private String daguo;
	private Integer type;

	// Constructors

	/** default constructor */
	public QuestionTishi() {
	}

	/** full constructor */
	public QuestionTishi(String meiJiang, String daCuo, String chaoShi,
			String choujiang, String daguo, Integer type) {
		this.meiJiang = meiJiang;
		this.daCuo = daCuo;
		this.chaoShi = chaoShi;
		this.choujiang = choujiang;
		this.daguo = daguo;
		this.type = type;
	}

	// Property accessors

	public Integer getQuestionTishiId() {
		return this.questionTishiId;
	}

	public void setQuestionTishiId(Integer questionTishiId) {
		this.questionTishiId = questionTishiId;
	}

	public String getMeiJiang() {
		return this.meiJiang;
	}

	public void setMeiJiang(String meiJiang) {
		this.meiJiang = meiJiang;
	}

	public String getDaCuo() {
		return this.daCuo;
	}

	public void setDaCuo(String daCuo) {
		this.daCuo = daCuo;
	}

	public String getChaoShi() {
		return this.chaoShi;
	}

	public void setChaoShi(String chaoShi) {
		this.chaoShi = chaoShi;
	}

	public String getChoujiang() {
		return this.choujiang;
	}

	public void setChoujiang(String choujiang) {
		this.choujiang = choujiang;
	}

	public String getDaguo() {
		return this.daguo;
	}

	public void setDaguo(String daguo) {
		this.daguo = daguo;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}