package com.handany.bm.service;

import com.github.pagehelper.PageInfo;
import com.handany.bm.model.BmFavorite;


/**
 * 
 * @author Administrator
 *
 */
public interface BmFavoriteService {	
	/**
	 * 保存收藏
	 * @param favorite
	 * @return
	 */
	int saveFavorite(BmFavorite favorite);
	
	/**
	 * 查询学生的所有收藏
	 * @param studentId
	 * @return
	 */
	PageInfo<BmFavorite> queryFavorites(String studentId);
	
	/**
	 * 删除收藏
	 * @param studentId
	 * @param teacherId
	 * @return
	 */
	int deleteFavorite(String studentId, String teacherId);
	
	/**
	 * 查询教师是否已收藏
	 * @param studentId
	 * @param teacherId
	 * @return
	 */
	int isInFavorites(String studentId, String teacherId);
}
