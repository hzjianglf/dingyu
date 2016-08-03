package com.wxpt.site.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ShoppingCar;


public interface ProductService {
	/**
	 * 刘百冰 2013年10月12日10:14:54
	 * 获取商品
	 * @param hql
	 * @return
	 */
	public List<Product> getProduct(String hql);
	/**
	 * 刘百冰 2013年10月12日10:14:54
	 * 分页获取商品
	 * @param hql
	 * @param startIndex 开始索引
	 * @param pageSize 每页个数
	 * @return
	 */
	public List<Product> getProductByPage(String hql,int startIndex, int pageSize);
	/**
	 * 刘百冰 
	 * 获取商品数量
	 * @param hql
	 * @return
	 */
	public int getProductNum(String hql);
	/**
	 * 加入购物车
	 * @param shopCar
	 * @return
	 */
	public int saveShopCar(ShoppingCar shopCar,int enterId);
	/**
	 * 根据商品id和会员id查询是否有数据
	 * @param hql
	 * @return
	 */
	public int getShopCarById(String hql);
}