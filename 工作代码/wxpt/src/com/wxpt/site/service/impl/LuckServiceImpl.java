package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.LuckDao;
import com.wxpt.site.entity.AnswerRecords;
import com.wxpt.site.entity.LuckUser;
import com.wxpt.site.entity.Luckanwer;
import com.wxpt.site.service.LuckService;

public class LuckServiceImpl implements LuckService {
	@Autowired
	LuckDao luckDao;

	@Transactional
	@Override
	public void addLuckUser(int enterId,LuckUser luckUser) {
		// TODO Auto-generated method stub
		luckDao.addLuckUser(enterId,luckUser);
	}

	@Transactional
	@Override
	public LuckUser getMaxLuckUser(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		return luckDao.getMaxLuckUser(enterId,sendUser);
	}

	@Transactional
	@Override
	public List<LuckUser> getUserList(int enterId,int page,int rows) {
		// TODO Auto-generated method stub
		return luckDao.getUserList(enterId,page,rows);
	}

	@Transactional
	@Override
	public void updateLkUser(int enterId,int luckid, int state) {
		// TODO Auto-generated method stub
		luckDao.updateLkUser(enterId,luckid, state);
	}

	@Transactional
	@Override
	public void deleteLkUser(int enterId,int luckid) {
		// TODO Auto-generated method stub
		luckDao.deleteLkUser(enterId,luckid);
	}

	@Transactional
	@Override
	public void addAnswerRecords(int MaxId,int enterId,String time,String forUser,int type) {
		// TODO Auto-generated method stub
		luckDao.addAnswerRecords(MaxId,enterId,time,forUser,type);
	}

	@Transactional
	@Override
	public List<AnswerRecords> getAnswerRecords(int enterId,int type) {
		// TODO Auto-generated method stub
		return luckDao.getAnswerRecords(enterId,type);
	}

	@Transactional
	@Override
	public void addLuckanwer(int enterId,Luckanwer luckAnswer) {
		// TODO Auto-generated method stub
		luckDao.addLuckanwer(enterId,luckAnswer);
	}

	@Transactional
	@Override
	public Luckanwer getLuckanwer(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		return luckDao.getLuckanwer(enterId,sendUser);
	}

	@Transactional
	@Override
	public List<Luckanwer> getLuckanwer(int enterId,int page,int rows) {
		// TODO Auto-generated method stub
		return luckDao.getLuckanwer(enterId,page,rows);
	}

	@Transactional
	@Override
	public void updateLuckanwer(int enterId,int luckanwerid, int state) {
		// TODO Auto-generated method stub
		luckDao.updateLuckanwer(enterId,luckanwerid, state);
	}
	@Transactional
	@Override
	public void deleteLuckanwer(int enterId,int luckanwerid) {
		// TODO Auto-generated method stub
		luckDao.deleteLuckanwer(enterId,luckanwerid);
	}

	@Override
	public int byluchAnwerId(int enterId,int id) {
		// TODO Auto-generated method stub
		return luckDao.byluchAnwerId(enterId, id);
	}

	@Override
	public AnswerRecords byRecordId(int enterId, int recordId) {
		// TODO Auto-generated method stub
		return luckDao.byRecordId(enterId, recordId);
	}
	@Transactional
	@Override
	public int getluckUserCount(String sql) {
		// TODO Auto-generated method stub
		return luckDao.getluckUserCount(sql);
	}
	@Transactional
	@Override
	public int getluckAnwerCount(String sql) {
		// TODO Auto-generated method stub
		return luckDao.getluckAnwerCount(sql);
	}
	@Transactional
	@Override
	public AnswerRecords getMaxId(String sql) {
		// TODO Auto-generated method stub
		return luckDao.getMaxId(sql);
	}

}
