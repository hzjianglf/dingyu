package com.handany.bm.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handany.base.common.CommonUtils;
import com.handany.base.common.FileUploader;
import com.handany.base.common.FileUploader.FileItem;
import com.handany.base.common.HttpUtil;
import com.handany.base.container.CacheContainer;
import com.handany.base.db.TransactionManager;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.common.CacheConstants;
import com.handany.bm.dao.BmPictureMapper;
import com.handany.bm.model.BmPicture;
import com.handany.bm.service.BmPictureService;

@Service
public class BmPictureServiceImpl implements BmPictureService {

	@Autowired
	private BmPictureMapper pictureMapper;
	
	@Autowired
	private SerialNumberManager serialNumberManager;
	
	/** 
	 * 批量修改图片顺序
	 */
	@Override
	public int batchUpdate(List<BmPicture> list) {
		// TODO Auto-generated method stub
		int i = pictureMapper.batchUpdate(list);
		if(i > 0){
			CacheContainer.remove(CacheConstants.CACHE_PICTURE + list.get(0).getType() + ":" + list.get(0).getRelateId());
		}
		return i;
	}

	/**
	 * 根据主键删除图片
	 */
	@Override
	public int deleteByPrimaryKey(String id, String type, String relateId) {
		int i = pictureMapper.deleteByPrimaryKey(id);
		if(i > 0){
			CacheContainer.remove(CacheConstants.CACHE_PICTURE + type + ":"  + relateId);
		}
		return i;
	}
	
	/**
	 * 添加图片
	 */
	public List<Map<String,String>> batchInsert() throws Exception{
		
		String relateId = HttpUtil.getParameter("relateId");
		String type = HttpUtil.getParameter("type");
		
		return batchInsert(type,relateId);
	}
	
	
	
	/**
	 * 添加图片
	 */
	public List<Map<String,String>> batchInsert(String type,String relateId) throws Exception{
		List<FileItem> list = FileUploader.getFileItemList();
		List<Map<String,String>> rtnList = new ArrayList<Map<String,String>>();
		List<BmPicture> picList = new ArrayList<BmPicture>();
		for (int i = 0; i < list.size(); i++) {
			BmPicture bp = new BmPicture();
			bp.setRelateId(relateId);
			bp.setUrl(list.get(i).smallPath.replaceAll("\\\\", "/"));
			bp.setRealUrl(list.get(i).filePath);
			bp.setType(type);
			bp.setStatus("1");
			bp.setId( serialNumberManager.nextSeqNo("bm_picture"));
			bp.setLastModified(CommonUtils.getCurrentDateStr());
			picList.add(bp);
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("id",bp.getId());
			map.put("url",bp.getUrl());
			map.put("realUrl",bp.getRealUrl());
			map.put("relateId", bp.getRelateId());
			rtnList.add(map);
		}
		int i = pictureMapper.batchInsert(picList);
		if(i > 0){
			CacheContainer.remove(CacheConstants.CACHE_PICTURE + type + ":"  + relateId);
		}
		
		return rtnList;
	}
	

	/**
	 * 查询图片
	 */
	@Override
	public List<BmPicture> selectPicList(String type,String relateId) throws Exception {
		
		List<BmPicture> list =  CacheContainer.get(CacheConstants.CACHE_PICTURE + type + ":"  + relateId, List.class);
		if(null == list || list.size()==0){
			Map<String,String> map = new HashMap<String,String>();
			map.put("type", type);
			map.put("relateId", relateId);
			list = pictureMapper.selectPicList(map);
			if(list != null && list.size()>0){
				CacheContainer.put(CacheConstants.CACHE_PICTURE + type + ":"  + relateId, list, com.handany.base.common.Constants.SECONDS_DAY);
			}
		}
		return list;
	}

	/**
	 * 修改单个图片
	 */
	@Override
	public int updateByPrimaryKeySelective(BmPicture pic) {
		
		int i = pictureMapper.updateByPrimaryKeySelective(pic);
		if(i > 0){
			CacheContainer.remove(CacheConstants.CACHE_PICTURE + pic.getType() + ":"  + pic.getRelateId());
		}		
		return i;
	}
	


	
	/**
	 * 预生成id
	 * @return
	 */
	@Override
	public Map<String, String> getNextId() {
		Map<String, String> map = new HashMap<String, String>();
		try{			
			String pictureId = serialNumberManager.nextSeqNo("bm_picture");			
			map.put("pictureId", pictureId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 根据图片主键查询图片信息
	 */
	@Override
	public BmPicture selectById(String id) {
		return pictureMapper.selectById(id);
	}

	
	/**
	 * 删除图片
	 * @param map   type relateId
	 * @return
	 */
	@Override
	public int deletePic(String type, String relateId) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", type);
		map.put("relateId", relateId);
		int i = pictureMapper.deletePic(map);
		if(i > 0){
			CacheContainer.remove(CacheConstants.CACHE_PICTURE + type + ":"  + relateId);
		}
		return i;
	}

	@Override
	public void setHeader(String picId,String type,String relateId) throws Exception {
		
		try{
			TransactionManager.begin();
			Map<String,String> map = new HashMap<String,String>();
			map.put("type", type);
			map.put("relateId", relateId);
			pictureMapper.clearHeader(map);
			pictureMapper.setHeader(picId);
			CacheContainer.remove(CacheConstants.CACHE_PICTURE + type + ":"  + relateId);
			
			TransactionManager.commit();
		}catch(Exception ex){
			TransactionManager.rollback();
			throw ex;
		}
	}

}
