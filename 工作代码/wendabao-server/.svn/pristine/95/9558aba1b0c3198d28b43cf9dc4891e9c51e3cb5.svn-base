package com.handany.bm.service;

import java.util.List;
import java.util.Map;

import com.handany.bm.model.BmPicture;


public interface BmPictureService {
	//批量更新数据
	int batchUpdate(List<BmPicture> list);

	/**
	 * 根据主键删除图片
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id, String type, String relateId);
	
	/**
	 * 添加图片
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> batchInsert() throws Exception;
	
	public List<Map<String,String>> batchInsert(String type,String relateId) throws Exception;

	/**
	 * 根据类型和关联id查询图片列表
	 * @param type
	 * @param relateId
	 * @return
	 * @throws Exception
	 */
	public List<BmPicture> selectPicList(String type,String relateId)throws Exception;
	
	/**
	 * 根据主键修改图片信息
	 * @param map
	 * @return
	 */
	int updateByPrimaryKeySelective(BmPicture pic);
	
	/**
	 * 预生成id
	 * @return
	 */
	public Map<String, String> getNextId();

	/**
	 * 根据id查询图片
	 * @param id
	 * @return
	 */
	BmPicture selectById(String id);
	
	/**
	 * 删除图片
	 * @param map   type relateId
	 * @return
	 */
	int deletePic(String type, String relateId) throws Exception;

	void setHeader(String picId,String type,String relateId) throws Exception;
	
}












