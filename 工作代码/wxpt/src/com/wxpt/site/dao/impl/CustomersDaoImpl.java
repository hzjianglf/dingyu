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
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.common.JDBC_test;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Canton;
import com.wxpt.site.entity.Customers;
import com.wxpt.site.entity.Industry;
import com.wxpt.site.entity.Move;
import com.wxpt.site.entity.Radius;
import com.wxpt.site.entity.Reply;
import com.wxpt.site.dao.CustomersDao;

public class CustomersDaoImpl extends ParentDaoImpl implements CustomersDao {
   @Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	
	@Override
	@Transactional
	public List<Customers> getCustomersByInId(int enterId,int industryId) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from wxpt"+enterId+".customers where industry_id = "+industryId;
			
			List<Customers> list=new ArrayList<Customers>();
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class);	
			return list = query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	@Transactional
	public List<Customers> getCustomersByAreaId(int enterId,int areaId) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from wxpt"+enterId+".customers where area_id = "+areaId;		
			List<Customers> list=new ArrayList<Customers>();
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class);
			return list = query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Transactional
	public List<Customers> getCustomersByCantonId(int enterId,int cantonId) {
		// TODO Auto-generated method stub
		String sql = "from wxpt"+enterId+".customers where canton_id = "+cantonId;
		List<Customers> list=new ArrayList<Customers>();	
		try{		
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class);	
			list = query.list();
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	@Override
	@Transactional
	public List<Customers> getCustomersList(int enterId) {
		// TODO Auto-generated method stub		
		String sql = "from wxpt"+enterId+".customers";
		List<Customers> list=new ArrayList<Customers>();		
		try{
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class);		
			list = query.list();	
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	@Override
	@Transactional
	public List<Customers> getCustomersList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		
		String sql = "from wxpt"+enterId+".customers";
		List<Customers> list=new ArrayList<Customers>();		
		try{
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class);
			query.setFirstResult((page-1)*rows);
			query.setMaxResults(rows);
			list = query.list();
			
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
		
		
	}
	@Override
	@Transactional
	public int getCustomersCount(int enterId) {
		// TODO Auto-generated method stub
		String sql = "from wxpt"+enterId+".customers ";
		List<Customers> list=new ArrayList<Customers>();	
		try{	
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class);			
			list = query.list();
			return list.size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list.size();
		}
	}
	@Override
	@Transactional
	public Customers getCustomersByID(int enterId,int customersId) {
		Customers customers=null;
		try {
			// TODO Auto-generated method stub
			String hql = " select * from wxpt"+enterId+".customers where customers_id = " + customersId;
			List<Customers> list=new ArrayList<Customers>();	
			
			try{		
				
				/*String sql="SELECT * FROM webchat.enter_infor";*/
				System.out.println(hql);
				Query query=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Customers.class);	
				list = query.list();
				if(list.size()!=0){
					customers=list.get(0);
				}
				
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return customers;
			}
			return customers;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	@Override
	@Transactional
	public void deleteById(int enterId,int customersId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM wxpt"+enterId+".customers WHERE customers_id ="+customersId;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	@Override
	@Transactional
	public List<Customers> getCustomersList(int enterId,String sql, int page, int rows) {
		// TODO Auto-generated method stub
		List<Customers> list=new ArrayList<Customers>();		
		try{
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class);
			query.setFirstResult((page-1)*rows);
			query.setMaxResults(rows);
			list = query.list();	
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	@Override
	@Transactional
	public int getCustomersCount(int enterId,String hql) {
		// TODO Auto-generated method stub
		List<Customers> list=new ArrayList<Customers>();	
		try{	
			
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Customers.class);			
			list = query.list();
			return list.size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list.size();
		}
	}
	@Transactional
	public List<Customers> getCustomersList(int enterId,String sql) {
		// TODO Auto-generated method stub
		List<Customers> list=new ArrayList<Customers>();		
		try{
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class);
			list = query.list();	
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	@Override
	@Transactional
	public Customers getCustomersByNo(int enterId,String no) {
		// TODO Auto-generated method stub
		Customers customers=null;
		try {
			
			String sql="select * from wxpt"+enterId+".customers where customers_no = '"+no+"' ";
			customers = (Customers) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Customers.class).list().get(0);
			return customers;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Override
	@Transactional
	public List<Customers> getCustomersListByXY(int enterId) {
		// TODO Auto-generated method stub
		//return this.sessionFactory.getCurrentSession().createQuery("from Customers where locationX != '' and locationY != ''").list();
		List<Customers> list=new ArrayList<Customers>();
		try {
			String sql="select * from wxpt"+enterId+".customers where location_x !='' and location_y !=''";
			list = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("static-access")
	@Override

	public void updateCustomer(int enterId,String Location_X,String Location_y,int customers_id) {
		//String sql="SELECT * FROM wxpt"+enterId+".canton WHERE `canton_id`="+cantonId;
		//Customers Canton=(Customers) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Customers.class).list().get(0);
		String sql="UPDATE wxpt"+enterId+".customers SET `location_x`='"+Location_X+"',`location_y`='"+Location_y+"' WHERE `customers_id`="+customers_id;
		
		/*if(Canton==null){
			sql="UPDATE `customers` SET `location_x`='"+Location_X+"',`location_y`='"+Location_y+"' WHERE `customers_id`="+customers_id;
			//sql ="UPDATE wxpt"+enterId+".customers SET customers_name='"+customers.getCustomersName()+"',industry_id="+customers.getIndustry().getIndustryId()+",area_id="+customers.getArea().getAreaId()+",customers_address='"+customers.getCustomersAddress()+"',model='"+customers.getModel()+"',count="+customers.getCount()+",introduce='"+customers.getIntroduce()+"',location_x='"+customers.getLocationX()+"',location_y='"+customers.getLocationY()+"',add_time='"+customers.getAddTime()+"',state="+customers.getState()+",from_username='"+customers.getFromUsername()+"',customers_no='"+customers.getCustomersNo()+"',customers_image='"+customers.getCustomersImage()+"'  WHERE customers_id="+customers.getCustomersId()+"";
		}else{
			 sql ="UPDATE  wxpt"+enterId+".customers SET customers_name='"+Canton.getCustomersName()+"',industry_id="+customers.getIndustry().getIndustryId()+",area_id="+customers.getArea().getAreaId()+",canton_id="+customers.getCanton().getCantonId()+",customers_address='"+customers.getCustomersAddress()+"',model='"+customers.getModel()+"',count="+customers.getCount()+",introduce='"+customers.getIntroduce()+"',location_x='"+customers.getLocationX()+"',location_y='"+customers.getLocationY()+"',add_time='"+customers.getAddTime()+"',state="+customers.getState()+",`from_username`='"+customers.getFromUsername()+"',customers_no='"+customers.getCustomersNo()+"',customers_image='"+customers.getCustomersImage()+"'  WHERE customers_id="+customers.getCustomersId()+"";
		}*/
		
		System.out.println(sql);
		con=jdbc.getConnection(enterId);
		try {
			ps = con.prepareStatement(sql);
			//执行
			ps.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public void addCustomers(int enterId, Customers customers) {
		// TODO Auto-generated method stub
		String sql;
		if(customers.getCanton()==null){
			 sql="INSERT INTO wxpt"+enterId+".customers(`customers_id`, `customers_name`, `industry_id`, `area_id`,  `customers_address`, `model`, `count`, `introduce`, `location_x`, `location_y`, `add_time`, `state`, `from_username`, `customers_no`, `customers_image`) VALUES ("+customers.getCustomersId()+",'"+customers.getCustomersName()+"',"+customers.getIndustry().getIndustryId()+","+customers.getArea().getAreaId()+",'"+customers.getCustomersAddress()+"','"+customers.getModel()+"','"+customers.getCount()+"','"+customers.getIntroduce()+"','"+customers.getLocationX()+"','"+customers.getLocationY()+"','"+TimeUtil.getTime()+"',"+customers.getState()+",'"+customers.getFromUsername()+"','"+customers.getCustomersNo()+"','"+customers.getCustomersImage()+"')";
		}else{
			 sql="INSERT INTO wxpt"+enterId+".customers(`customers_id`, `customers_name`, `industry_id`, `area_id`, `canton_id`, `customers_address`, `model`, `count`, `introduce`, `location_x`, `location_y`, `add_time`, `state`, `from_username`, `customers_no`, `customers_image`) VALUES ("+customers.getCustomersId()+",'"+customers.getCustomersName()+"',"+customers.getIndustry().getIndustryId()+","+customers.getArea().getAreaId()+","+customers.getCanton().getCantonId()+",'"+customers.getCustomersAddress()+"','"+customers.getModel()+"','"+customers.getCount()+"','"+customers.getIntroduce()+"','"+customers.getLocationX()+"','"+customers.getLocationY()+"','"+TimeUtil.getTime()+"',"+customers.getState()+",'"+customers.getFromUsername()+"','"+customers.getCustomersNo()+"','"+customers.getCustomersImage()+"')";
		}
		 
	        System.out.println(sql);
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
	@Override
	public Canton getbyId(String sql) {
		// TODO Auto-generated method stub
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Canton.class);
		Canton caton=(Canton) query.list().get(0);
		return caton;
	}
	@Override
	public Area getById(String sql) {
		// TODO Auto-generated method stub
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Area.class);
		Area caton=(Area) query.list().get(0);
		return caton;
	}
	@Override
	public Industry getIndustryById(String sql2) {
		// TODO Auto-generated method stub
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql2).addEntity(Industry.class);
		Industry caton=(Industry) query.list().get(0);
		return caton;
	}
	@SuppressWarnings("static-access")
	@Override
	public void update(int enterId, String sql) {
		// TODO Auto-generated method stub
		con=jdbc.getConnection(enterId);
		try {
			ps = con.prepareStatement(sql);
			//执行
			ps.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
