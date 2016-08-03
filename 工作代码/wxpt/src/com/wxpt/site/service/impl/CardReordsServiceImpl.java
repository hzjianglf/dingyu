package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.CardReordsDao;
import com.wxpt.site.entity.CardRecords;
import com.wxpt.site.service.CardReordsService;

public class CardReordsServiceImpl implements CardReordsService{
@Autowired
CardReordsDao cardRecordDao;
	@Transactional
	public List<CardRecords> findByCardId(int cardId) {
		// TODO Auto-generated method stub
		return cardRecordDao.findByCardId(cardId);
	}
	@Transactional
	public int getBycardUserName(String cardUserName,int enterId) {
		// TODO Auto-generated method stub
		return cardRecordDao.getBycardUserName(cardUserName,enterId);
	}
	@Transactional
	public void saveCardRecords(CardRecords cardRecords,int enterId) {
		cardRecordDao.saveCardRecords(cardRecords,enterId);
		
	}
	@Transactional
	public List<CardRecords> findAllQuery(int page, int rows,int enterId) {
		// TODO Auto-generated method stub
		return cardRecordDao.findAllQuery(page,rows,enterId);
	}
	@Transactional
	public void update(CardRecords cardRecords,int enterId) {
		cardRecordDao.update(cardRecords,enterId);
		
	}
	@Transactional
	public CardRecords findByRecordsCardId(int cardRecordsId,int enterId) {
		// TODO Auto-generated method stub
		return cardRecordDao.findByRecordsCardId(cardRecordsId,enterId);
	}
	@Transactional
	public void delete(int cardRecordsId,int enterId) {
		cardRecordDao.delete(cardRecordsId,enterId);
		
	}
	@Transactional
	public int getByCardType(int cardType,int enterId) {
		// TODO Auto-generated method stub
		return cardRecordDao.getByCardType(cardType,enterId);
	}
	@Transactional
	public int getCount(int enterId) {
		// TODO Auto-generated method stub
		return cardRecordDao.getCount(enterId);
	}
	@Transactional
	public int getBycardUserName(String fromUsername, int enterId,
			String moveStartTime) {
		// TODO Auto-generated method stub
		return cardRecordDao.getBycardUserName(fromUsername, enterId,
				 moveStartTime);
	}
	@Transactional
	public int getByCardType(int i, int enterId, String moveStartTime) {
		// TODO Auto-generated method stub
		return cardRecordDao.getByCardType(i, enterId, moveStartTime);
	}

}
