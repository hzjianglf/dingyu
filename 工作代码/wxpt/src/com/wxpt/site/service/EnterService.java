package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Shiyong;

public interface EnterService {
	//查询所有企业
		public List<EnterInfor> getAll(String sql,int page, int rows);
		public int enterCount(String sql);
		//删除
		public void deleEnter(String sql);
		//更新
		public void updateEnter(String sql);
		//查询
		public EnterInfor getById(int enterId);
		
		public List<EnterInfor> getByEnterInfor(String sql);
		
		public EnterInfor getById2(int enterId);
		
		public List<EnterInfor> getAllEnter();
		
		public void updateVshopBanenr(String sql);
		
		//添加子企业
		public void addQyPeiZhi(String sql);
		
		//总数
		public List<EnterInfor>getCount(String sql);
		
		//查询 实用功能
		public List<Shiyong> getall(String sql);
		
		//更新实用功能
		public void  updateAll(String sql, int enterId);
		
}
