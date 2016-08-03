package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.ImageDao;
import com.wxpt.site.entity.CheckIn;
import com.wxpt.site.entity.Imageroll;
import com.wxpt.site.entity.Prize;
import com.wxpt.site.service.ImageService;

public class ImageServiceImpl implements ImageService{
	@Autowired
	ImageDao imageDao;
	
	@Transactional
	@Override
	public List<Imageroll> getImageList(int enterId) {
		// TODO Auto-generated method stub
		return imageDao.getImageList(enterId);
	}

	@Override
	@Transactional
	public void addImage(int enterId,Imageroll image) {
		imageDao.addImage(enterId,image);
		
	}

	public ImageDao getImageDao() {
		return imageDao;
	}

	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

	@Override
	public Imageroll getImagerollById(int imageId) {
		// TODO Auto-generated method stub
		return imageDao.getImagerollById(imageId);
	}

	@Override
	public void updateImage(int enterId,Imageroll image) {
		// TODO Auto-generated method stub
		imageDao.updateImage(enterId,image);
	}

	@Override
	@Transactional
	public void addCheck(int enterId,CheckIn checkin) {
		// TODO Auto-generated method stub
		imageDao.addCheck(enterId,checkin);
	}

	@Override
	@Transactional
	public List<CheckIn> queryAllChenck(int enterId,String username) {
		// TODO Auto-generated method stub
		return imageDao.queryAllChenck(enterId,username);
	}

	@Override
	@Transactional
	public List<CheckIn> queryAllChenckByName(int enterId,String username) {
		// TODO Auto-generated method stub
		return imageDao.queryAllChenckByName(enterId,username);
	}

	@Override
	@Transactional
	public List<CheckIn> queryAllChenck1(int enterId,String username) {
		// TODO Auto-generated method stub
		return imageDao.queryAllChenck1(enterId,username);
	}


	@Override
	@Transactional
	public List<CheckIn> queryAllCheckByNameAndTime(int enterId,String username,
			String year, String month) {
		// TODO Auto-generated method stub
		return imageDao.queryAllCheckByNameAndTime(enterId,username, year, month);
	}

	@Override
	@Transactional
	public List<CheckIn> queryAllCheckByNameAndTime2(int enterId,String username,
			String year, String month) {
		// TODO Auto-generated method stub
		return imageDao.queryAllCheckByNameAndTime2(enterId,username, year, month);
	}

	@Override
	@Transactional
	public List<CheckIn> queryAllCheckByNameAndTime3(int enterId,String username,
			String year, String month) {
		// TODO Auto-generated method stub
		return imageDao.queryAllCheckByNameAndTime3(enterId,username, year, month);
	}


	@Override
   @Transactional
	public void addPrize(int enterId,Prize prize) {
		// TODO Auto-generated method stub
		imageDao.addPrize(enterId,prize);
	}

	@Override
	  @Transactional
	public List<Prize> queryByName(int enterId,String username) {
		// TODO Auto-generated method stub
		return imageDao.queryByName(enterId,username);
	}


	@Override
	@Transactional
	public List<Prize> queryByNameOrder(int enterId,String username) {
		// TODO Auto-generated method stub
		return imageDao.queryByNameOrder(enterId,username);
	}

	@Override
	@Transactional
	public boolean queryCheck(String checktime, String prizeTime,
			String username,int count) {
		// TODO Auto-generated method stub
		return imageDao.queryCheck(checktime, prizeTime, username,count);
	}


	@Override
	@Transactional
	public List<Imageroll> getImageList1(int enterId) {
		// TODO Auto-generated method stub
		return imageDao.getImageList1(enterId);
	}

	@Override
	@Transactional
	public List<Imageroll> getImageList2(int enterId) {
		// TODO Auto-generated method stub
		return imageDao.getImageList2(enterId);
	}

	@Override
	@Transactional
	public List<Prize> queryByNameByTime(int enterId,String year, String month) {
		// TODO Auto-generated method stub
		return imageDao.queryByNameByTime(enterId,year, month);
	}

	@Override
	@Transactional
	public List<CheckIn> queryAllChenckin(int enterid) {
		// TODO Auto-generated method stub
		return imageDao.queryAllChenckin( enterid);
	}

	@Override
	@Transactional
	public List<CheckIn> queryAllChenckin(int enterid, int start, int number) {
		// TODO Auto-generated method stub
		return imageDao.queryAllChenckin(enterid, start, number);
	}

	@Override
	@Transactional
	public void deleteCheckin(int enterid,  int id) {
		// TODO Auto-generated method stub
		imageDao.deleteCheckin(enterid, id);
	}

	@Override
	@Transactional
	public CheckIn queryByCheckinId(int enterid, int id) {
		// TODO Auto-generated method stub
		return imageDao.queryByCheckinId(enterid, id);
	}

	@Override
	@Transactional
	public List<CheckIn> queryAllChenckinByName(int enterid, String username,
			int start, int number) {
		// TODO Auto-generated method stub
		return imageDao.queryAllChenckinByName(enterid, username, start, number)
				;
	}

	@Override
	@Transactional
	public List<CheckIn> queryAllChenckByName(String username, int enterid) {
		// TODO Auto-generated method stub
		return imageDao.queryAllChenckByName(username, enterid)
				;
	}

	@Override
	@Transactional
	public List<Prize> queryByNamePage(int enterId, int start,
			int number) {
		// TODO Auto-generated method stub
		return imageDao.queryByNamePage(enterId, start, number);
	}


	@Override
	@Transactional
	public List<Prize> queryPrizeAll(int enterId) {
		// TODO Auto-generated method stub
		return imageDao.queryPrizeAll(enterId);
	}

	@Override
	@Transactional
	public Prize queryByPrizeId(int enterid, int id) {
		// TODO Auto-generated method stub
		return imageDao.queryByPrizeId(enterid, id);
	}

	@Override
	@Transactional
	public void deletePrize(int enterid, int id) {
		// TODO Auto-generated method stub
		imageDao.deletePrize(enterid, id);
	}

	@Override
	@Transactional
	public void updatePrizeisState(int enterId, int prizeId, int state) {
		// TODO Auto-generated method stub
		imageDao.updatePrizeisState(enterId, prizeId, state);
	}

	@Override
	@Transactional
	public int queryByNameByTimeCount(int enterId,int i, String year, String month) {
		// TODO Auto-generated method stub
		return imageDao.queryByNameByTimeCount(enterId,i, year, month);
	}


	
}
