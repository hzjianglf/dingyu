package com.wxpt.site.entity;

/**
 * Appraise entity. @author MyEclipse Persistence Tools
 */

public class Appraise implements java.io.Serializable {

	// Fields

	private Integer appraiseId;
	private Member member;
	private Product product;
	private String appraiseContent;
	private String appraiseTime;
	private String appraiseAdd;
	private String appraiseAddTime;
	private String appraiseHuifu;
	private String appraiseHuifuTime;

	// Constructors

	/** default constructor */
	public Appraise() {
	}

	/** minimal constructor */
	public Appraise(Member member, Product product, String appraiseContent,
			String appraiseTime, String appraiseAdd, String appraiseAddTime) {
		this.member = member;
		this.product = product;
		this.appraiseContent = appraiseContent;
		this.appraiseTime = appraiseTime;
		this.appraiseAdd = appraiseAdd;
		this.appraiseAddTime = appraiseAddTime;
	}

	/** full constructor */
	public Appraise(Member member, Product product, String appraiseContent,
			String appraiseTime, String appraiseAdd, String appraiseAddTime,
			String appraiseHuifu, String appraiseHuifuTime) {
		this.member = member;
		this.product = product;
		this.appraiseContent = appraiseContent;
		this.appraiseTime = appraiseTime;
		this.appraiseAdd = appraiseAdd;
		this.appraiseAddTime = appraiseAddTime;
		this.appraiseHuifu = appraiseHuifu;
		this.appraiseHuifuTime = appraiseHuifuTime;
	}

	// Property accessors

	public Integer getAppraiseId() {
		return this.appraiseId;
	}

	public void setAppraiseId(Integer appraiseId) {
		this.appraiseId = appraiseId;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getAppraiseContent() {
		return this.appraiseContent;
	}

	public void setAppraiseContent(String appraiseContent) {
		this.appraiseContent = appraiseContent;
	}

	public String getAppraiseTime() {
		return this.appraiseTime;
	}

	public void setAppraiseTime(String appraiseTime) {
		this.appraiseTime = appraiseTime;
	}

	public String getAppraiseAdd() {
		return this.appraiseAdd;
	}

	public void setAppraiseAdd(String appraiseAdd) {
		this.appraiseAdd = appraiseAdd;
	}

	public String getAppraiseAddTime() {
		return this.appraiseAddTime;
	}

	public void setAppraiseAddTime(String appraiseAddTime) {
		this.appraiseAddTime = appraiseAddTime;
	}

	public String getAppraiseHuifu() {
		return this.appraiseHuifu;
	}

	public void setAppraiseHuifu(String appraiseHuifu) {
		this.appraiseHuifu = appraiseHuifu;
	}

	public String getAppraiseHuifuTime() {
		return this.appraiseHuifuTime;
	}

	public void setAppraiseHuifuTime(String appraiseHuifuTime) {
		this.appraiseHuifuTime = appraiseHuifuTime;
	}

}