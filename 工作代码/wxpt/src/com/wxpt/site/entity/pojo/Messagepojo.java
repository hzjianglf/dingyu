package com.wxpt.site.entity.pojo;

import java.util.List;

import com.wxpt.site.entity.Message;

public class Messagepojo {

	private String messageContents;
	List<Message> listMessage;
	
	public List<Message> getListMessage() {
		return listMessage;
	}

	public void setListMessage(List<Message> listMessage) {
		this.listMessage = listMessage;
	}

	public String getMessageContents() {
		return messageContents;
	}

	public void setMessageContents(String messageContents) {
		this.messageContents = messageContents;
	}
	
}
