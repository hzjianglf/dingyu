package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.ManageDao;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ProductType;
import com.wxpt.site.entity.ShoppingAds;
import com.wxpt.site.service.ManageService;



public class ManageServiceImpl implements ManageService{
	@Autowired
	ManageDao manageDao;
	
	//商品添加
	@Transactional
		@Override
		public int addProduct(Product product, int enterId) {
			// TODO Auto-generated method stub
			return manageDao.addProduct(product, enterId);
		}
		//商品查看
		@Transactional
		@Override
		public List<Product> getProductList(int enterId,String hql,int start,int number) {
			// TODO Auto-generated method stub
			return manageDao.getProductList(enterId,hql,start,number);
		}
		//商品修改
		@Transactional
		@Override
		public void updateProduct(Product product,int id, int enterId) {
			// TODO Auto-generated method stub
			manageDao.updateProduct(product,id, enterId);
		}
		//商品删除
		@Transactional
		@Override
		public void delProduct(int productId, int enterId) {
			// TODO Auto-generated method stub
			manageDao.delProduct(productId, enterId);
		}
		//商品记录数
		@Transactional
		@Override
		public int getCount(int enterId, String hql) {
			// TODO Auto-generated method stub
			return manageDao.getCount(enterId, hql);
		}
		
		//以Id查出商品
		@Transactional
		public Product getByIdProduct(int procutId,int enterId){
			return manageDao.getByIdProduct(procutId,enterId);
		}
		//商品类别
		@Transactional
		@Override
		public List<ProductType> getProductType(int praentId,int enterId) {
			// TODO Auto-generated method stub
			return manageDao.getProductType(praentId,enterId);
		}
		//以typeId查询
		@Transactional
		@Override
		public ProductType getByIdType(int id,int enterId) {
			// TODO Auto-generated method stub
			return manageDao.getByIdType(id,enterId);
		}
		//parentId!=0的
		@Transactional
		@Override
		public List<ProductType> getProductType(int enterId) {
			// TODO Auto-generated method stub
			return manageDao.getProductType(enterId);
		}
		//登录验证
		@Transactional
		@Override
		public ManageUser checkLogin(int enterId,String uname, String password) {
			// TODO Auto-generated method stub
			return manageDao.checkLogin(enterId,uname, password);
		}
		//密码修改
		@Transactional
		@Override
		public ManageUser getManageUserByName(int enterId, String userName) {
			// TODO Auto-generated method stub
			return manageDao.getManageUserByName(enterId, userName);
		}
		@Transactional
		@Override
		public void update(int enterId, String sql) {
			// TODO Auto-generated method stub
			manageDao.update(enterId, sql);
		}
		@Transactional
		@Override
		public List<ShoppingAds> showShoppAds(int enterId, String hql,
				int start, int number) {
			// TODO Auto-generated method stub
			return manageDao.showShoppAds(enterId, hql, start, number);
		}
		@Transactional
		@Override
		public int getCountAd(int enterId, String hql) {
			// TODO Auto-generated method stub
			return manageDao.getCountAd(enterId, hql);
		}
		@Transactional
		@Override
		public int saveBanner(String name,int enterId) {
			// TODO Auto-generated method stub
			return manageDao.saveBanner(name,enterId);
		}
		@Transactional
		@Override
		public int updateBanner(String name, int id) {
			// TODO Auto-generated method stub
			return manageDao.updateBanner(name,id);
		}
		@Transactional
		@Override
		public int check(int id) {
			// TODO Auto-generated method stub
			return  manageDao.check(id);
		}
}
