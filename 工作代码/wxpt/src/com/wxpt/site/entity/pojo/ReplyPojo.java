package com.wxpt.site.entity.pojo;

public class ReplyPojo {
	private int replyId;
	private String replyContent;
	private String replyType;
	private int keyId;
	
	
	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	public ReplyPojo (int replyId,String replyContent,String replyType){
		
		this.replyId = replyId;
		this.replyContent = replyContent;
		this.replyType = replyType;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyType() {
		return replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}
	
	
}
