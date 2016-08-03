package com.wxpt.site.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.ProductDao;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ShoppingCar;
import com.wxpt.site.service.ProductService;


public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao productDao;
	
	/**
	 * 刘百冰 2013年10月12日10:14:54
	 * 获取商品
	 * @param hql
	 * @return
	 */
	@Transactional
	public List<Product> getProduct(String hql){
		return productDao.getProduct(hql);
	}
	/**
	 * 刘百冰 2013年10月12日10:14:54
	 * 分页获取商品
	 * @param hql
	 * @param startIndex 开始索引
	 * @param pageSize 每页个数
	 * @return
	 */
	@Transactional
	public List<Product> getProductByPage(String hql,int startIndex, int pageSize){
		return productDao.getProductByPage(hql, startIndex, pageSize);
	}
	/**
	 * 刘百冰 
	 * 获取商品数量
	 * @param hql
	 * @return
	 */
	@Transactional
	public int getProductNum(String hql){
		return productDao.getProductNum(hql);
	}
	/**
	 * 加入购物车
	 * @param shopCar
	 * @return
	 */
	@Transactional
	public int saveShopCar(ShoppingCar shopCar,int enterId){
		return productDao.saveShopCar(shopCar,enterId);
	}
	
	/**
	 * 根据商品id和会员id查询是否有数据
	 * @param hql
	 * @return
	 */
	@Transactional
	public int getShopCarById(String hql){
		return productDao.getShopCarById(hql);
	}

}
