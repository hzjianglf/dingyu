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

import com.wxpt.common.JDBC_test;
import com.wxpt.common.PageBean;
import com.wxpt.site.dao.ProductTypeDao;
import com.wxpt.site.entity.ProductType;


public class ProductTypeDaoImpl implements ProductTypeDao {
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	public int addProductType(ProductType productType )
	{
		int a=(Integer) this.sessionFactory.getCurrentSession().save(productType);
		return a;
	}
	
	
	private int total;// 总数据条数
	private int firstPage;// 分页开始数
	private List list = new ArrayList();
	
	public PageBean productTypeList(int enterId,int curPage, int pageSize){
		String sql="select * from  wxpt"+enterId+".product_type";
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(ProductType.class);
		
		total = query.list().size();
		firstPage = (curPage - 1) * pageSize;
		query.setFirstResult(firstPage);
		query.setMaxResults(pageSize);
		list = query.list();
		return new PageBean(list, total, pageSize, curPage);
	}
	
	public  ProductType productTypeObject(String sql){
		ProductType productType=(ProductType) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(ProductType.class).list().get(0);
		return productType;
	}
	
	@SuppressWarnings("deprecation")
	public void productTypeObjectOperator(String sql) {
		// TODO Auto-generated method stub
		session=(Session)this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		 try {
			session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductType> productTypeList(String sql){
		List<ProductType> list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(ProductType.class).list();
		return list;
	}
	

	@Override	
	public List<ProductType> getProductTypeByPage(String sql,int startIndex, int pageSize){
		List <ProductType> productType = null;
		try {
			Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(ProductType.class);
           
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			productType = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			productType = null;
			System.out.println("获取商品类别报异常！");
			return productType;
		}
		return productType;
	}
	/**
	 * 刘百冰 
	 * 获取商品类别数量
	 * @param hql
	 * @return
	 */
	public int getTypeNum(String hql){
		int num = 0;
		try {
			num  = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(ProductType.class).list().size();
			//num  = this.sessionFactory.getCurrentSession().createQuery(hql).list().size();

		} catch (HibernateException e) {
			num = 0;
			System.out.println("获取商品类别报异常！");
		}
		return num;
	}


	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return session;
	}


	public void productTypeObjectOperatorTwo(String sql) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(ProductType.class);

	}

	
}
