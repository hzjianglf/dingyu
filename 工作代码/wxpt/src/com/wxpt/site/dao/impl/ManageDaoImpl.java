package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.ManageDao;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ProductType;
import com.wxpt.site.entity.ShoppingAds;


public class ManageDaoImpl implements ManageDao {

	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc = new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;

	// 商品添加
	@Override
	public int addProduct(Product product, int enterId) {
		// TODO Auto-generated method stub
		int i = 0;
		String sql = "INSERT INTO wxpt"+enterId+".product ( `product_num`, `product_name`, `product_brand`,`inventory_num`, `price`, `cheap_price`,`sell_num`,`product_details`,`product_overview`,`product_color`,`product_ximage`,`product_dimage`,`product_addtime`,`product_type_id`,`product_recomme`,`product_update_time`) "
				+ "VALUES ('"+product.getProductNum()+"','"+product.getProductName()+"','"+product.getProductBrand()+"',"+product.getInventoryNum()+","+product.getPrice()+","+product.getCheapPrice()+","+product.getSellNum()+",'"+product.getProductDetails()+"','"+product.getProductOverview()+"'," +
						  "'"+product.getProductColor()+"','"+product.getProductXimage()+"','"+product.getProductDimage()+"','"+product.getProductAddtime()+"',"+product.getProductType().getProductTypeId()+",'"+product.getProductRecomme()+"','"+product.getProductUpdateTime()+"')";
				
		System.out.println(sql);
		try {
			session = (Session) this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			i = session.connection().createStatement().executeUpdate(sql);
			System.out.println(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			i = 0;
		}

		return i;
	}

	// 商品查看
	@Override
	public List<Product> getProductList(int enterId, String hql, int start,
			int number) {
		// TODO Auto-generated method stub
		List<Product> list = new ArrayList<Product>();;
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(Product.class);
		query.setFirstResult(start);
		query.setMaxResults(number);
		list = query.list();
		return list;
	}

	// 商品修改
	@Override
	public void updateProduct(Product product, int id, int enterId) {

   String sql="UPDATE `product` SET " +
   		"`product_num`='"+product.getProductNum()+"',`product_name`='"+product.getProductName()+"'," +
   		"`product_brand`='"+product.getProductBrand()+"',`inventory_num`="+product.getInventoryNum()+"," +
   		"`price`="+product.getPrice()+",cheap_price="+product.getCheapPrice()+"," +
   		"`sell_num`="+product.getSellNum()+",`product_details`='"+product.getProductDetails()+"'," +
   		"`product_overview`='"+product.getProductOverview()+"',`product_color`='"+product.getProductColor()+"'," +
   		"`product_ximage`='"+product.getProductXimage()+"',`product_dimage`='"+product.getProductDimage()+"'," +
   		"`product_addtime`='"+product.getProductAddtime()+"',`product_type_id`="+product.getProductType().getProductTypeId()+"," +
   		"`product_recomme`='"+product.getProductRecomme()+"',`product_update_time`='"+product.getProductUpdateTime()+"' WHERE product_id="+id+"";
		System.out.println(sql);
/*=======

		String sql = "update wxpt"+enterId+".product set product_update_time='"+product.getProductUpdateTime()+"', product_num='"
				+ product.getProductNum() + "',product_name='"
				+ product.getProductName() + "'," + "product_brand ='"
				+ product.getProductBrand() + "',inventory_num="
				+ product.getInventoryNum() + "," + "price="
				+ product.getPrice() + ",cheap_price="
				+ product.getCheapPrice() + ",sell_num=" + product.getSellNum()
				+ "," + "product_details='" + product.getProductDetails()
				+ "'," + "product_overview='" + product.getProductOverview()
				+ "',product_color='" + product.getProductColor() + "',"
				+ "product_ximage='" + product.getProductXimage()
				+ "',product_dimage='" + product.getProductDimage() + "',"
				+ "product_addtime='" + product.getProductAddtime()
				+ "',product_type_id="
				+ product.getProductType().getProductTypeId()
				+ ",product_recomme='" + product.getProductRecomme() + "'"
				+ " where product_id=" + id;
>>>>>>> .r8052*/
		try {
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 商品删除
	@Override
	public void delProduct(int productId, int enterId) {
		// TODO Auto-generated method stub
		String hql = "delete from wxpt"+enterId+".product where product_id = '"
				+ productId + "'";
		this.sessionFactory.getCurrentSession().createSQLQuery(hql)
				.executeUpdate();
	}

	// 商品记录数
	@Override
	public int getCount(int enterId, String hql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(Product.class).list().size();
	}

	// 以Id查出商品
	@Override
	public Product getByIdProduct(int procutId, int enterId) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".product where product_id ="
				+ procutId;
		return (Product) this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(Product.class).list().get(0);
	}

	// 商品类别
	@Override
	public List<ProductType> getProductType(int praentId, int enterId) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".product_type where parent_id = "
				+ praentId + "";
		return this.sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(ProductType.class).list();
	}

	// 以typeId查询
	@Override
	public ProductType getByIdType(int id, int enterId) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".product_type where product_type_id = "
				+ id + "";
		return (ProductType) this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(ProductType.class).list().get(0);
	}

	// parentId!=0的
	@Override
	public List<ProductType> getProductType(int enterId) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createQuery("from ProductType where parentId != 0").list();
	}

	// 登录验证
	@Override
	public ManageUser checkLogin(int enterId, String uname, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from wxpt"+enterId+".manage_user where manage_user_name = '"
				+ uname + "' and passwrod = '" + password + "'";

		System.out.println(sql);
		/*
		 * String hql = "from ManageUser where manageUserName = '" + uname +
		 * "' and passwrod = '" + password + "'";
		 */
		try {
			ManageUser user = (ManageUser) this.sessionFactory
					.getCurrentSession().createSQLQuery(sql)
					.addEntity(ManageUser.class).list().get(0);
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	// 修改密码
	public ManageUser getManageUserByName(int enterId, String userName) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			return (ManageUser) this.sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select * from wxpt"+enterId+".manage_user where manage_user_name = '"
									+ userName + "'")
					.addEntity(ManageUser.class).list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public void update(int enterId,String sql) {
		// TODO Auto-generated method stub
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
		
			
	}
	//广告显示
	@Override
	public List<ShoppingAds> showShoppAds(int enterId, String hql, int start,
			int number) {
		// TODO Auto-generated method stub
		List<ShoppingAds> list;
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(ShoppingAds.class);
		query.setFirstResult(start);
		query.setMaxResults(number);
		list = query.list();
		return list;
	}

	@Override
	public int getCountAd(int enterId, String hql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(hql)
				.addEntity(ShoppingAds.class).list().size();
	}

	public int saveBanner(String name,int enterId) {
		int i = 0;
		String sql = "INSERT INTO wxpt"+enterId+".shopping_ads ( `ad_name`)VALUES ('"+name+"')";
		System.out.println(sql);
		try {
			session = (Session) this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			i = session.connection().createStatement().executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	//====================================================================================================================================
	@Override
	public int updateBanner(String name,int id) {
		int i = 0;
		String sql = "update shopping_ads set ad_name='"+name+"' where type=" + id;
				
		try {
			con = jdbc.getConnection2();
			pstmt = con.prepareStatement(sql);
			i = pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int check(int id) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery("select * from shopping_ads where type = 1").addEntity(ShoppingAds.class).list().size();
	}
}
