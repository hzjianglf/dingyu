package com.wxpt.site.service;

import java.util.List;

import com.wxpt.common.PageBean;
import com.wxpt.site.entity.Order;
import com.wxpt.site.entity.Product;


public interface OrderManageService {
	public PageBean spiltOrderManager(int curPage, int pageSize,String product_num,String username,int enterId) ;
	public Product productObject(String sql);
	public void operateOrder(String sql,int enterId);
	public Order orderObject(String sql);
	public List<Product> productshowPicture(String sql);
	public void getOrder(String hql);
}
