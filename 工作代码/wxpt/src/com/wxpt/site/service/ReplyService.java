package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Reply;



public interface ReplyService extends ParentService {
	public List<Reply> getReplyList();

	public List<Reply> getReplyList(int enterId,int page, int rows);

	public int getReplyCount(int enterId);

	public Reply getReplyByID(int enterId, int replyId);

	public void deleteById(int replyId);

	public int getReplyByType(int enterId,int type);
	public int getReplyKwordId(String sql);
	public String  getKeywordcontent(String sql);


	

}
