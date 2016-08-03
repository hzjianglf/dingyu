package com.wxpt.site.service.impl;

import java.util.List;

import javax.persistence.TableGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.ReplyDao;
import com.wxpt.site.entity.Reply;
import com.wxpt.site.service.ReplyService;

public class ReplyServiceImpl extends ParentServieImpl implements ReplyService {
	@Autowired
	ReplyDao replyDao;

	@Transactional
	@Override
	public List<Reply> getReplyList() {
		// TODO Auto-generated method stub
		return replyDao.getReplyList();
	}

	@Transactional
	@Override
	public List<Reply> getReplyList(int enterId, int page, int rows) {
		// TODO Auto-generated method stub
		return replyDao.getReplyList(enterId, page, rows);
	}

	@Transactional
	@Override
	public int getReplyCount(int enterId) {
		// TODO Auto-generated method stub
		return replyDao.getReplyCount(enterId);
	}

	@Transactional
	@Override
	public Reply getReplyByID(int enterId, int replyId) {
		// TODO Auto-generated method stub
		return replyDao.getReplyByID(enterId, replyId);
	}

	@Override
	public void deleteById(int replyId) {
		// TODO Auto-generated method stub
		replyDao.deleteById(replyId);
	}

	@Override
	public int getReplyByType(int enterId, int type) {
		// TODO Auto-generated method stub
		return replyDao.getReplyByType(enterId, type);
	}

	@Transactional
	@Override
	public int getReplyKwordId(String sql) {
		// TODO Auto-generated method stub
		return replyDao.getReplyKwordId(sql);
	}

	@Transactional
	@Override
	public String getKeywordcontent(String sql) {
		// TODO Auto-generated method stub
		return replyDao.getKeywordcontent(sql);
	}

}
