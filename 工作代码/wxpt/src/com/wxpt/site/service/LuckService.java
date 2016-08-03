package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.AnswerRecords;
import com.wxpt.site.entity.LuckUser;
import com.wxpt.site.entity.Luckanwer;

public interface LuckService {
	public void addLuckUser(int enterId,LuckUser luckUser);
	public LuckUser getMaxLuckUser(int enterId,String sendUser);
	public List<LuckUser> getUserList(int enterId,int page, int rows);
	public void updateLkUser(int enterId,int luckid,int state);
	public void deleteLkUser(int enterId,int luckid);
	public void addAnswerRecords(int MaxId,int enterId,String time,String forUser,int type);
	public List<AnswerRecords> getAnswerRecords(int enterId,int type);
	public void addLuckanwer(int enterId,Luckanwer luckAnswer);
	public Luckanwer getLuckanwer(int enterId,String sendUser);
	public List<Luckanwer> getLuckanwer(int enterId,int page, int rows);
	public void updateLuckanwer(int enterId,int luckanwerid ,int state);
	public void deleteLuckanwer(int enterId,int luckanwerid);
	//
	public int byluchAnwerId(int enterId,int id);
	public AnswerRecords byRecordId(int enterId,int recordId);
	public int getluckUserCount(String sql);
	public int getluckAnwerCount(String sql);
	
	//maxId
	public AnswerRecords getMaxId(String sql);
}
