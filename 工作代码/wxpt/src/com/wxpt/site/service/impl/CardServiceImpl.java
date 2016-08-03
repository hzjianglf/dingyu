package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.CardDao;
import com.wxpt.site.entity.Card;
import com.wxpt.site.service.CardService;

public class CardServiceImpl implements CardService{
@Autowired
CardDao cardDao;
	@Transactional
	public List<Card> findByCardId(int cardId) {
		// TODO Auto-generated method stub
		return cardDao.findByCardId(cardId);
	}
	@Transactional
	public int findByCardImage(String cardImage,int enterId) {
		// TODO Auto-generated method stub
		return cardDao.findByCardImage(cardImage,enterId);
	}
	@Transactional
	public void save(Card card, int enterId) {
		cardDao.save(card,enterId);
		
	}
	@Transactional
	public void updateCard(Card card,int enterId) {
		cardDao.updateCard(card,enterId);
		
	}
	@Transactional
	public List<Card> findQuery(int enterId) {
		// TODO Auto-generated method stub
		return cardDao.findQuery(enterId);
	}
	@Transactional
	public List<Card> findAllQuery(int page, int rows,int enterId) {
		// TODO Auto-generated method stub
		return cardDao.findAllQuery(page, rows,enterId);
	}
	@Transactional
	public int getCardCount() {
		// TODO Auto-generated method stub
		return cardDao.getCardCount();
	}
	@Transactional
	public int getByMaxType(int enterId) {
		// TODO Auto-generated method stub
		return cardDao.getByMaxType(enterId);
	}
	@Transactional
	public Card findByCardImageEntity(String cardName, int enterId) {
		// TODO Auto-generated method stub
		return cardDao.findByCardImageEntity(cardName,enterId);
	}
	@Transactional
	public Card findByCardId(int cardId, int enterId) {
		// TODO Auto-generated method stub
		return cardDao.findByCardId(cardId, enterId);
	}
	

}
