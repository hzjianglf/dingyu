package com.wxpt.site.entity;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private Member member;
	private String messageTitle;
	private String messageContent;
	private Integer messageType;
	private String time;
	private Integer messageParentid;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(Member member, String messageTitle, String messageContent,
			Integer messageType, String time, Integer messageParentid) {
		this.member = member;
		this.messageTitle = messageTitle;
		this.messageContent = messageContent;
		this.messageType = messageType;
		this.time = time;
		this.messageParentid = messageParentid;
	}

	// Property accessors

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getMessageTitle() {
		return this.messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Integer getMessageType() {
		return this.messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getMessageParentid() {
		return this.messageParentid;
	}

	public void setMessageParentid(Integer messageParentid) {
		this.messageParentid = messageParentid;
	}

}