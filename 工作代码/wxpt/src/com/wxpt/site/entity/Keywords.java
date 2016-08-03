package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Keywords entity. @author MyEclipse Persistence Tools
 */

public class Keywords implements java.io.Serializable {

	// Fields

	private Integer keywordId;
	private String keywordcontent;
	private String wordCount;
	private String fileCount;
	private String imagesCount;
	private String rule;
	private Set keywordexplicits = new HashSet(0);
	private Set replies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Keywords() {
	}

	/** minimal constructor */
	public Keywords(String keywordcontent, String rule) {
		this.keywordcontent = keywordcontent;
		this.rule = rule;
	}

	/** full constructor */
	public Keywords(String keywordcontent, String wordCount, String fileCount,
			String imagesCount, String rule, Set keywordexplicits, Set replies) {
		this.keywordcontent = keywordcontent;
		this.wordCount = wordCount;
		this.fileCount = fileCount;
		this.imagesCount = imagesCount;
		this.rule = rule;
		this.keywordexplicits = keywordexplicits;
		this.replies = replies;
	}

	// Property accessors

	public Integer getKeywordId() {
		return this.keywordId;
	}

	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}

	public String getKeywordcontent() {
		return this.keywordcontent;
	}

	public void setKeywordcontent(String keywordcontent) {
		this.keywordcontent = keywordcontent;
	}

	public String getWordCount() {
		return this.wordCount;
	}

	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}

	public String getFileCount() {
		return this.fileCount;
	}

	public void setFileCount(String fileCount) {
		this.fileCount = fileCount;
	}

	public String getImagesCount() {
		return this.imagesCount;
	}

	public void setImagesCount(String imagesCount) {
		this.imagesCount = imagesCount;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Set getKeywordexplicits() {
		return this.keywordexplicits;
	}

	public void setKeywordexplicits(Set keywordexplicits) {
		this.keywordexplicits = keywordexplicits;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

}