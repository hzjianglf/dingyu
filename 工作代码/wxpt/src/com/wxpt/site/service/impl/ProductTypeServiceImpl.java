package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.common.PageBean;
import com.wxpt.site.dao.ProductTypeDao;
import com.wxpt.site.entity.ProductType;
import com.wxpt.site.service.ProductTypeService;


public class ProductTypeServiceImpl implements ProductTypeService{
	
	ProductTypeDao productTypeDao;

	@Transactional
	public int addProductType(ProductType productType) {
		// TODO Auto-generated method stub
		return productTypeDao.addProductType(productType);
	}

	@Transactional
	public PageBean productTypeList(int enterId,int curPage, int pageSize) {
		// TODO Auto-generated method stub
		return productTypeDao.productTypeList(enterId,curPage, pageSize);
	}

	@Transactional
	public ProductType productTypeObject(String sql) {
		// TODO Auto-generated method stub
		return productTypeDao.productTypeObject(sql);
	}

	@Transactional
	public void productTypeObjectOperator(String sql) {
		 productTypeDao.productTypeObjectOperator(sql);
	}

	@Transactional
	public List<ProductType> productTypeList(String sql) {
		// TODO Auto-generated method stub
		return productTypeDao.productTypeList(sql);
	}

	/**
	 * 刘百冰 
	 * 分页获取商品类别
	 * @param hql
	 * @param startIndex 开始索引
	 * @param pageSize 每页个数
	 * @return
	 */
	@Transactional
	public List<ProductType> getProductTypeByPage(String hql,int startIndex, int pageSize){
		return productTypeDao.getProductTypeByPage(hql, startIndex, pageSize);
	}
	/**
	 * 刘百冰 
	 * 获取商品类别数量
	 * @param hql
	 * @return
	 */
	@Transactional
	public int getTypeNum(String hql){
		return productTypeDao.getTypeNum(hql);
	}

	public ProductTypeDao getProductTypeDao() {
		return productTypeDao;
	}

	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
	}

	@Transactional
	@Override
	public void productTypeObjectOperatorTwo(String sql) {
		// TODO Auto-generated method stub
		productTypeDao.productTypeObjectOperatorTwo(sql);
	}

	
	
	
}
