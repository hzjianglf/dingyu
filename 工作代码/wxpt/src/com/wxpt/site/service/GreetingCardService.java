package com.wxpt.site.service;
import java.io.Serializable;
import java.util.List;

import com.wxpt.site.entity.FandsGreetingCard;
import com.wxpt.site.entity.GreetingCard;
import com.wxpt.site.entity.GreetingCardProterty;
import com.wxpt.site.entity.GreetingCardTemplate;
import com.wxpt.site.entity.GreetingCardType;


public interface GreetingCardService extends ParentService{
	public List<GreetingCardType> getCardType(String sql);

	public List<GreetingCard> getCard(String sql);

	public List<GreetingCard> getCard(String sql, int page, int rows);

	public List<GreetingCardType> getCardType(String sql, int page, int rows);
	
	public int executeSql(String sql);

	public FandsGreetingCard getFansCard(String string);
	public List<GreetingCardTemplate> getCardTemplate(String sql);

	public List<GreetingCardTemplate> getCardTemplate(String sql, int page, int rows);

	public List<GreetingCardProterty> getCardProterty(String sql);
	public Serializable executeSql(String sql,int enterId);
}
