package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.CheckIn;
import com.wxpt.site.entity.Imageroll;
import com.wxpt.site.entity.Prize;


public interface ImageDao {
	public List<Imageroll> getImageList(int enterId);
	public List<Imageroll> getImageList1(int enterId);
	public List<Imageroll> getImageList2(int enterId);
	public void addImage( int enterId,Imageroll image);
	public Imageroll getImagerollById(int imageId);
	public void updateImage(int enterId, Imageroll image);	
	//签到
	public void addCheck(int enterId, CheckIn checkin);
	public List<CheckIn> queryAllChenck(int enterId,String username);
	public List<CheckIn> queryAllChenck1(int enterId,String username);
	public List<CheckIn> queryAllChenckByName(int enterId,String username);
	public List<CheckIn> queryAllChenckByName(String username,int enterid );
	public List<CheckIn> queryAllChenckin(int enterid);
	public List<CheckIn> queryAllChenckin(int enterid, int start, int number);
	public List<CheckIn> queryAllChenckinByName(int enterid,String username, int start, int number);
	public List<CheckIn> queryAllCheckByNameAndTime(int enterId,String username,String year,String month);
	public List<CheckIn> queryAllCheckByNameAndTime2(int enterId,String username,String year,String month);
	public List<CheckIn> queryAllCheckByNameAndTime3(int enterId,String username,String year,String month);
	public void deleteCheckin(int enterid, int id);
	public CheckIn queryByCheckinId(int enterid, int id);
	//抽奖
	public void addPrize(int enterId,Prize prize);
	public List<Prize> queryByName(int enterId,String username);
	public List<Prize> queryPrizeAll(int enterId);
	public Prize queryByPrizeId(int enterid, int id);
	public void deletePrize(int enterid,  int id);
	public void updatePrizeisState(int enterId,int luckanwerid ,int state);
	public List<Prize> queryByNamePage(int enterId,int start,int number);
	public List<Prize> queryByNameByTime(int enterId,String year,String month);
	public List<Prize> queryByNameOrder(int enterId,String username);
	public boolean queryCheck(String checktime,String prizeTime,String username,int count );
	public int queryByNameByTimeCount(int enterId,int i, String year, String month);
}
