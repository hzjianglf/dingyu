package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Card;

public interface CardService {
	public List<Card> findByCardId(int cardId);

	public int findByCardImage(String cardImage, int enterId);

	public void save(Card card, int enterId);

	public void updateCard(Card card, int enterId);

	public List<Card> findQuery(int enterId);

	public List<Card> findAllQuery(int page, int rows, int enterId);

	public int getCardCount();

	public int getByMaxType(int enterId);

	public Card findByCardImageEntity(String cardName, int enterId);

	public Card findByCardId(int cardId, int enterId);
	

}
