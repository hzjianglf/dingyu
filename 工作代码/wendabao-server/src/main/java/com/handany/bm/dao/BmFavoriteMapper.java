package com.handany.bm.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.handany.bm.model.BmFavorite;

public interface BmFavoriteMapper {
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
	List<BmFavorite> queryFavorites(String studentId);
	
	/**
	 * 删除收藏
	 * @param studentId
	 * @param teacherId
	 * @return
	 */
	int deleteFavorite(@Param("studentId") String studentId, @Param("teacherId") String teacherId);
	
	/**
	 * 查询教师是否已收藏
	 * @param teacherId
	 * @return
	 */
	int isInFavorites(@Param("studentId") String studentId, @Param("teacherId") String teacherId);
}