package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.common.PageBean;
import com.wxpt.site.dao.OrderManageDao;
import com.wxpt.site.entity.Order;
import com.wxpt.site.entity.Product;
import com.wxpt.site.service.OrderManageService;


public class OrderManageServiceImpl implements OrderManageService {
	@Autowired
	OrderManageDao orderManageDao;



	@Transactional
	public Product productObject(String sql) {
		// TODO Auto-generated method stub
		return orderManageDao.productObject(sql);
	}



	@Transactional
	public PageBean spiltOrderManager(int curPage, int pageSize,
			String product_num, String username,int enterId) {
		return orderManageDao.spiltOrderManager(curPage, pageSize, product_num, username,enterId);
	}



	@Transactional
	public void operateOrder(String sql,int enterId) {
		// TODO Auto-generated method stub
		 orderManageDao.operateOrder(sql,enterId);
	}



	@Transactional
	public Order orderObject(String sql) {
		// TODO Auto-generated method stub
		return orderManageDao.orderObject(sql)
				;
	}



	@Override
	public List<Product> productshowPicture(String sql) {
		// TODO Auto-generated method stub
		return orderManageDao.productshowPicture(sql);
	}



	@Transactional
	public void getOrder(String hql) {
		// TODO Auto-generated method stub
		orderManageDao.getOrder(hql);
	}
	
	
	

}
