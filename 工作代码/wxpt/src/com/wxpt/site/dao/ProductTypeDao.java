package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.common.PageBean;
import com.wxpt.site.entity.ProductType;


public interface ProductTypeDao {
	public int addProductType(ProductType productType);
	public PageBean productTypeList(int enterId,int curPage, int pageSize);
	public  ProductType productTypeObject(String sql);
	public void productTypeObjectOperator(String sql);
	public void productTypeObjectOperatorTwo(String sql);
	public List<ProductType> productTypeList(String sql);
	/**
	 * 刘百冰 
	 * 分页获取商品类别
	 * @param hql
	 * @param startIndex 开始索引
	 * @param pageSize 每页个数
	 * @return
	 */
	
	public List<ProductType> getProductTypeByPage(String hql,int startIndex, int pageSize);
	/**
	 * 刘百冰 
	 * 获取商品类别数量
	 * @param hql
	 * @return
	 */
	public int getTypeNum(String hql);
}
