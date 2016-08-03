package com.wxpt.site.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.GreetingCardDao;
import com.wxpt.site.entity.FandsGreetingCard;
import com.wxpt.site.entity.GreetingCard;
import com.wxpt.site.entity.GreetingCardProterty;
import com.wxpt.site.entity.GreetingCardTemplate;
import com.wxpt.site.entity.GreetingCardType;
import com.wxpt.site.service.GreetingCardService;

public class GreetingCardServiceImpl extends ParentServieImpl implements GreetingCardService {
	@Autowired
	GreetingCardDao gCardDao;
	
	@Override
	public List<GreetingCardType> getCardType(String sql) {
		// TODO Auto-generated method stub
		return gCardDao.getCardType(sql);
	}

	@Override
	public List<GreetingCard> getCard(String sql) {
		// TODO Auto-generated method stub
		return gCardDao.getCard(sql);
	}

	@Override
	public List<GreetingCard> getCard(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return gCardDao.getCard(sql,page,rows);
	}

	@Override
	public List<GreetingCardType> getCardType(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return gCardDao.getCardType(sql,page,rows);
	}
	@Transactional
	@Override
	public int executeSql(String sql) {
		// TODO Auto-generated method stub
		return gCardDao.executeSql(sql);
	}

	@Override
	public FandsGreetingCard getFansCard(String string) {
		// TODO Auto-generated method stub
		return gCardDao.getFansCard(string);
	}

	@Override
	public List<GreetingCardTemplate> getCardTemplate(String sql) {
		// TODO Auto-generated method stub
		return gCardDao.getCardTemplate(sql);
	}

	@Override
	public List<GreetingCardTemplate> getCardTemplate(String sql, int page,
			int rows) {
		// TODO Auto-generated method stub
		return gCardDao.getCardTemplate(sql,page,rows);
	}

	@Override
	public List<GreetingCardProterty> getCardProterty(String sql) {
		// TODO Auto-generated method stub
		return gCardDao.getCardProterty(sql);
	}

	@Override
	public Serializable executeSql(String sql, int enterId) {
		// TODO Auto-generated method stub
		return gCardDao.executeSql(sql, enterId);
	}


}
