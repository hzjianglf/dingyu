package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.AppraiseDao;
import com.wxpt.site.entity.Appraise;
import com.wxpt.site.service.AppraiseService;


public class AppraiseServiceImpl implements AppraiseService {
	AppraiseDao appraiseDao;

	public AppraiseDao getAppraiseDao() {
		return appraiseDao;
	}
	public void setAppraiseDao(AppraiseDao appraiseDao) {
		this.appraiseDao = appraiseDao;
	}
	@Transactional
	@Override
	public List<Appraise> getAll(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return appraiseDao.getAll(sql, page, rows);
	}
	@Transactional
	@Override
	public int getCount(String sql) {
		// TODO Auto-generated method stub
		return appraiseDao.getCount(sql);
	}
	@Transactional
	@Override
	public boolean delete(String sql) {
		// TODO Auto-generated method stub
		return appraiseDao.delete(sql);
	}
	@Transactional
	@Override
	public List<Appraise> getAll2(String sql) {
		// TODO Auto-generated method stub
		return appraiseDao.getAll2(sql);
	}
	@Transactional
	@Override
	public  void update (String sql) {
		// TODO Auto-generated method stub
		 appraiseDao.update(sql);
	}

		/**
		 * 刘百冰
		 * 获取评价
		 * @param hql
		 * @return
		 */
		@Transactional
		public List<Appraise> getAppraise(String hql){
			return appraiseDao.getAppraise(hql);
		}
		/**
		 * 刘百冰 
		 * 分页获取评价
		 * @param hql
		 * @param startIndex 开始索引
		 * @param pageSize 每页个数
		 * @return
		 */
		@Transactional
		public List<Appraise> getAppraiseByPage(String hql,int startIndex, int pageSize){
			return appraiseDao.getAppraiseByPage(hql, startIndex, pageSize);
		}
		@Transactional
		public int getNumber(String hql){
			return appraiseDao.getNumber(hql);
		}
		@Transactional
		public int save(String sql){
			return appraiseDao.save(sql);
		}
	

}
