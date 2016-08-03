package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Appraise;


public interface AppraiseService {
	//订单查询
		public List<Appraise> getAll(String sql,int page, int rows);
		//订单总数
		public int getCount(String sql);
		public boolean delete(String sql);
		//查询所有
		public List<Appraise> getAll2(String sql);
		//更新  回复内容
		public  void update(String sql);
		

		/**
		 * 刘百冰
		 * 获取评价
		 * @param hql
		 * @return
		 */
		public List<Appraise> getAppraise(String hql);
		/**
		 * 刘百冰 
		 * 分页获取评价
		 * @param hql
		 * @param startIndex 开始索引
		 * @param pageSize 每页个数
		 * @return
		 */
		public List<Appraise> getAppraiseByPage(String hql,int startIndex, int pageSize);

		public int getNumber(String hql);
		
		public int save(String sql);

	
}
