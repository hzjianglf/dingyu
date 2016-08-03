package com.handany.bm.dao;

import java.util.List;
import java.util.Map;

import com.handany.bm.model.BmPicture;

public interface BmPictureMapper {
	//查询数量
	Map count(Map map);	
	
	//根据主键删除
	int deleteByPrimaryKey(String id);	
	//根据关联关系查找图片
	List<BmPicture> selectPicList(Map <String,String> map);
	//修改图片
	int updateByPrimaryKeySelective(BmPicture pic);
	// 添加图片
	int batchInsert(List<BmPicture> list);
	//修改图片顺序
	int batchUpdate(List<BmPicture> list);

	//主键查询
	BmPicture selectById(String id);
	
	
	/**
	 * 根据shopProductId查询商铺封面的图片
	 * @param shopProductId
	 * @return
	 */
	BmPicture queryByShopProductId(String shopProductId);
	
	/**
	 * 删除图片 根据关联id和type
	 * @param map   type relateId
	 * @return
	 */
	int deletePic(Map map);
	
	/**
	 * 通过shopId查询商铺认证图片
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	public List<BmPicture> queryShopIdentifyPic(String shopId);
	
	/**
	 * 清空封面标识
	 * @param picId
	 */
	public void clearHeader(Map<String,String> map);
	
	/**
	 * 设置为封面
	 * @param picId
	 */
	public void setHeader(String picId);
}