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
import com.wxpt.common.PageBean;
import com.wxpt.site.dao.OrderManageDao;
import com.wxpt.site.entity.Order;
import com.wxpt.site.entity.Product;

public class OrderManageDaoImpl implements OrderManageDao {
	@Autowired
	SessionFactory sessionfactory;
	protected Session session = null;
	private int total;// 总数据条数
	private int firstPage;// 分页开始数
	private List list = new ArrayList();
	private List<Product> plist = new ArrayList<Product>();
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	public PageBean spiltOrderManager(int curPage, int pageSize,
			String product_num, String username, int enterId) {
		String sql = "select * from  wxpt" + enterId + ".order where 1=1";
		if (product_num != null && !product_num.equals("")) {
			sql += " and order_num='" + product_num + "'";
		}
		if (username != null && !username.equals("")) {
			sql += " and member_id in (select member_id from  wxpt" + enterId
					+ ".member where username like '%" + username + "%')";
		}
		sql += " order by order_time desc";

		System.out.println(sql);
		Query query = this.sessionfactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Order.class);
		total = query.list().size();
		firstPage = (curPage - 1) * pageSize;
		query.setFirstResult(firstPage);
		query.setMaxResults(pageSize);
		list = query.list();

		return new PageBean(list, total, pageSize, curPage);
	}

	@SuppressWarnings("unchecked")
	public Product productObject(String sql) {
		plist = this.sessionfactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Product.class).list();
		if (plist.size() ==0) {
			return null;
		} else {
			return plist.get(0);
		}

	}

	@SuppressWarnings("static-access")
	public void operateOrder(String sql,int enterId) {
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			//执行
			pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		this.sessionfactory.getCurrentSession().createSQLQuery(sql)
				.executeUpdate();
	}

	public Order orderObject(String sql) {
		Order order = (Order) this.sessionfactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Order.class).list().get(0);
		return order;
	}

	public List<Product> productshowPicture(String sql) {
		List<Product> list2 = this.sessionfactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Product.class).list();
		return list2;
	}

	@Override
	public void getOrder(String sql) {
		// TODO Auto-generated method stub
		try {
			session=(Session) this.sessionfactory.getCurrentSession();
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
