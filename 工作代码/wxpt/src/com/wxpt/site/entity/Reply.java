package com.wxpt.site.entity;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private Integer replyId;
	private Keywords keywords;
	private Integer replyType;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(Integer replyType) {
		this.replyType = replyType;
	}

	/** full constructor */
	public Reply(Keywords keywords, Integer replyType) {
		this.keywords = keywords;
		this.replyType = replyType;
	}

	// Property accessors

	public Integer getReplyId() {
		return this.replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Keywords getKeywords() {
		return this.keywords;
	}

	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	public Integer getReplyType() {
		return this.replyType;
	}

	public void setReplyType(Integer replyType) {
		this.replyType = replyType;
	}

}