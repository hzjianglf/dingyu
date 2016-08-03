package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.Fans;
import com.wxpt.site.entity.FansImage;
import com.wxpt.site.entity.FansImageVisit;
import com.wxpt.site.entity.FansUser;


public interface FansDao extends ParentDao{
	public Fans getFansByName(int enterId,String fansName);
	public Fans getFansByNameValue(String sql);
	public void deleteFans(int enterId, Fans fans);
	public void addFans(int enterId, Fans fans);
	public void addFansUser(int enterId, FansUser fanuser);
	public void addFansImage(int enterId, FansImage fansImage);

	
	public void updateFans(int enterId, Fans fans);
	public Fans getFansByName(String fansName);
	public int getFansImageCount(String hql);
	public int getupdateFansImage(int enterId,String sql);
	public FansUser getFansUserByName(int enterId,String fromUsername);
	public int getCountBySql(String sql);
	public List<FansImage> getList(String hql,int page, int rows);
	//新添加
	public List<FansImage> getList2(String hql);
	public void updateFansUser(int enterId, FansUser fanuser);
	public void addFansImageVisit(int enterId, FansImageVisit fansImageVisit);
	public int getFansImageVisit(int enterId,String hql);
	
	public List<FansUser> getFansUserList(String hql);
	public List<FansUser> getFansUserList2(String hql,int page,int pageSize);

	public List<FansImageVisit> getFansImageVisitList(String hql);
	
	public List<FansUser> getAllFans(String sql,int page,int pageSize);
	
	public List<FansUser> getFansCount(String sql);
	
	//添加
	public void addFans(String sql);
	
	//更新
	public void updateFans(int enterId,String sql);
	
	//删除
	public void deleFans(String sql);

	
}
