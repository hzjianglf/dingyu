package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.CardRecords;

public interface CardReordsDao {
public List<CardRecords> findByCardId(int cardId);

public int getBycardUserName(String cardUserName, int enterId);

public void saveCardRecords(CardRecords cardRecords, int enterId);

public List<CardRecords> findAllQuery(int page, int rows, int enterId);

public void update(CardRecords cardRecords, int enterId);

public CardRecords findByRecordsCardId(int cardRecordsId, int enterId);

public void delete(int cardRecordsId, int enterId);

public int getByCardType(int cardType, int enterId);

public int getCount(int enterId);

public int getBycardUserName(String fromUsername, int enterId,
		String moveStartTime);

public int getByCardType(int i, int enterId, String moveStartTime);
}
