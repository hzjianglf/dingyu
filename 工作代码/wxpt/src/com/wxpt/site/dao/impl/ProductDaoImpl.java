package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.ProductDao;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ShoppingCar;


public class ProductDaoImpl implements ProductDao{
	@Autowired
    SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	
	/**
	 * 刘百冰 
	 * 获取商品
	 * @param hql
	 * @return
	 */
	public List<Product> getProduct(String hql){
		List <Product> product = new ArrayList<Product>();
		try {
			product = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Product.class).list();
		} catch (HibernateException e) {
			product = null;
			System.out.println("获取商品报异常！");
		}
		return product;
	}
	/**
	 * 刘百冰 
	 * 获取商品数量
	 * @param hql
	 * @return
	 */
	public int getProductNum(String hql){
		int num = 0;
		try {
			num  = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Product.class).list().size();
		} catch (HibernateException e) {
			num = 0;
			System.out.println("获取商品报异常！");
		}
		return num;
	}
	/**
	 * 刘百冰 
	 * 分页获取商品
	 * @param hql
	 * @param startIndex 开始索引
	 * @param pageSize 每页个数
	 * @return
	 */
	public List<Product> getProductByPage(String sql,int startIndex, int pageSize){
		List <Product> product = new ArrayList<Product>();
		try {
			Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			product = query.list();
		} catch (HibernateException e) {
			product = null;
			System.out.println("获取商品报异常！");
		}
		return product;
	}
	/**
	 * 加入购物车
	 * @param shopCar
	 * @return
	 */
	public int saveShopCar(ShoppingCar shopCar,int enterId){
		int i= 0;
		String sql="INSERT INTO wxpt"+enterId+".shopping_car( `member_id`, `product_id`, `product_sum`) VALUES ("+shopCar.getMember().getMemberId()+","+shopCar.getProduct().getProductId()+","+shopCar.getProductSum()+")";
		session=(Session)this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		 try {
			 i =session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			i = (Integer) sessionFactory.getCurrentSession().save(shopCar);
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("保存购物车异常！！！");
		}*/
		return i;
	}
	/**
	 * 根据商品id和会员id查询是否有数据
	 * @param hql
	 * @return
	 */
	public int getShopCarById(String hql){
		int i= 0;
		try {
			i = (Integer) sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(ShoppingCar.class).list().size();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("保存购物车异常！！！");
		}
		return i;
	}
}
