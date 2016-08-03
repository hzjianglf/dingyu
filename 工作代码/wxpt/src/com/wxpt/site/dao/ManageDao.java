package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ProductType;
import com.wxpt.site.entity.ShoppingAds;


public interface ManageDao {

	//商品添加
	public int addProduct(Product product,int enterId);
	//商品查看
	public List<Product> getProductList(int enterId,String hql,int start,int number);
	//商品记录数
	public int getCount(int enterId,String hql);
	//商品修改
	public void updateProduct(Product product,int id,int enterId);
	//商品删除
	public void delProduct(int productId,int enterId);
	//以Id查出商品
	public Product getByIdProduct(int procutId,int enterId);
	//以typeId查询
	public ProductType getByIdType(int id,int enterId);
	//商品类别
	public List<ProductType> getProductType(int parentId,int enterId);
	//parentId!=0的
	public List<ProductType> getProductType(int enterId);
	//登录验证
	public ManageUser checkLogin(int enterId,String uname, String getMd5);
	//密码修改
	public ManageUser getManageUserByName(int enterId,String userName);
	public void update(int enterId,String sql);
	//广告显示
	public List<ShoppingAds> showShoppAds(int enterId,String hql,int start,int number);
	//广告记录数
	public int getCountAd(int enterId,String hql);
	//banner
	public int saveBanner(String name,int enterId);
	public int updateBanner(String name,int id);
	public int check(int id);
	
}
