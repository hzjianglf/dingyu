package com.handany.bm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import com.handany.base.common.PageUtil;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmFavoriteMapper;
import com.handany.bm.model.BmFavorite;
import com.handany.bm.service.BmFavoriteService;

@Service
public class BmFavoriteServiceImpl implements BmFavoriteService {
    @Autowired
    private BmFavoriteMapper bmFavoriteMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

	@Override
	public int saveFavorite(BmFavorite favorite) {
		favorite.setLastModified(CommonUtils.getCurrentDate());
		return bmFavoriteMapper.saveFavorite(favorite);
	}

	@Override
	public PageInfo<BmFavorite> queryFavorites(String studentId) {
		PageUtil.startPage();
		List<BmFavorite> list = bmFavoriteMapper.queryFavorites(studentId);
		PageInfo<BmFavorite> page = new PageInfo<BmFavorite>(list);
		
		return page;
	}

	@Override
	public int deleteFavorite(String studentId, String teacherId) {
		return bmFavoriteMapper.deleteFavorite(studentId, teacherId);
	}

	@Override
	public int isInFavorites(String studentId, String teacherId) {
		return bmFavoriteMapper.isInFavorites(studentId, teacherId);
	}
}
