package com.wxpt.site.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.entity.CheckIn;
import com.wxpt.site.entity.Imageroll;
import com.wxpt.site.entity.Prize;


public interface ImageService {
	
	public List<Imageroll> getImageList(int enterId);
	public List<Imageroll> getImageList1(int enterId);
	public List<Imageroll> getImageList2(int enterId);
	public void addImage(int enterId, Imageroll image);
	public Imageroll getImagerollById(int imageId);
	public void updateImage(int enterId, Imageroll image);
	
	//签到
	public void addCheck(int enterId, CheckIn checkin);
	//根据发送者维信号获取签到集合
	public List<CheckIn> queryAllChenck(int enterId,String username);
	public List<CheckIn> queryAllChenckin(int enterid);
	public List<CheckIn> queryAllChenck1(int enterId,String username);
	public List<CheckIn> queryAllChenckByName(int enterId,String username);
	public List<CheckIn> queryAllChenckByName(String username,int enterid );
	public List<CheckIn> queryAllChenckin(int enterid, int start, int number);
	public List<CheckIn> queryAllChenckinByName(int enterid,String username, int start, int number);
	public CheckIn queryByCheckinId(int enterid, int id);
	public void deleteCheckin(int enterid, int id);
	
	public List<CheckIn> queryAllCheckByNameAndTime(int enterId,String username,String year,String month);
	public List<CheckIn> queryAllCheckByNameAndTime2(int enterId,String username,String year,String month);
	public List<CheckIn> queryAllCheckByNameAndTime3(int enterId,String username,String year,String month);

	public void addPrize(int enterId,Prize prize);
	//抽奖
	public List<Prize> queryByName(int enterId,String username);
	public List<Prize> queryByNamePage(int enterId,int start,int number);
	public List<Prize> queryPrizeAll(int enterId);
	public Prize queryByPrizeId(int enterid, int id);
	public void updatePrizeisState(int enterId,int prizeId ,int state);
	public void deletePrize(int enterid,  int id);
	public List<Prize> queryByNameOrder(int enterId,String username);
	public List<Prize> queryByNameByTime(int enterId,String year,String month);
	
	public int queryByNameByTimeCount(int enterId,int i, String year, String month);
	
	public boolean queryCheck(String checktime,String prizeTime,String username ,int count );


}
