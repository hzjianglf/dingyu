package com.wxpt.site.entity.pojo;

import java.util.List;

import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.Message;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message2 implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private Member member;
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	private String messageTitle;
	private String messageContent;
	private Integer messageType;
	private String time;
	private Integer messageParentid;


	public List<Message> getListhui() {
		return listhui;
	}

	public void setListhui(List<Message> listhui) {
		this.listhui = listhui;
	}

	private List<Message> listhui;
	// Constructors


	/** default constructor */
	public Message2() {
	}

	/** full constructor */

	// Property accessors

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
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