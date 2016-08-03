package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.Reply;



public interface ReplyDao {
	public List<Reply> getReplyList();
	public List<Reply> getReplyList(int enterId,int page,int rows);
	public int getReplyCount(int enterId);
	public Reply getReplyByID(int enterId,int replyId);
	public int getReplyByType(int enterId,int type);
	public void deleteById(int replyId);
	public int getReplyKwordId(String sql);
	public String  getKeywordcontent(String sql);
	
	
}
