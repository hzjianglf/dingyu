package com.wxpt.site.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.FansDao;
import com.wxpt.site.entity.Fans;
import com.wxpt.site.entity.FansImage;
import com.wxpt.site.entity.FansImageVisit;
import com.wxpt.site.entity.FansUser;
import com.wxpt.site.entity.Role;

public class FansDaoImpl extends ParentDaoImpl implements FansDao {
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Fans getFansByName(int enterId,String fansName) {
		String sql="select * from wxpt"+enterId+".fans where fans_name='"+fansName+"'";
		try {
			List<Fans> list = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Fans.class).list();
			Fans fans = list.get(0);
			return fans;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public void deleteFans(int enterId, Fans fans) {
		String sql = "DELETE FROM wxpt"+enterId+".fans where fans_id = "+fans.getFansId();
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		
	}

	@Override
	public void addFans(int enterId, Fans fans) {
		String sql="INSERT INTO wxpt"+enterId+".fans( `fans_name`, `fans_value`)" +
				" VALUES ('"+fans.getFansName()+"','"+fans.getFansValue()+"')";
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

	@SuppressWarnings("static-access")
	@Override
	public void updateFans(int enterId, Fans fans) {
		String sql ="UPDATE wxpt"+enterId+".fans SET `fans_value`='"+fans.getFansValue()+"' WHERE `fans_id`="+fans.getFansId();
		System.out.println(sql);
		con=jdbc.getConnection(enterId);
		try {
			ps = con.prepareStatement(sql);
			/*pstmt.setString(1, "userName");
			pstmt.setString(2, "userNum");
			pstmt.setInt(3, 5);*/
			//执行
			ps.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

	public Fans getFansByName(String fansName) {
		// TODO Auto-generated method stub
		String hql = "from Fans where fansName = '" + fansName + "'";
		try {
			Fans fans = (Fans) this.sessionFactory.getCurrentSession()
					.createQuery(hql).list().get(0);
			return fans;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	
	public FansUser getFansUserByName(int enrterId,String fromUsername) {
		// TODO Auto-generated method stub
		String sql = "select * from wxpt"+enrterId+".fans_user where fans_user_name = '" + fromUsername + "'";
		System.out.println(sql);
		FansUser fansUser=null;
		try {
			
			 Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FansUser.class);		
				
			List<FansUser> fanlist=query.list();
			if(fanlist.size()>0){
				fansUser=fanlist.get(0);
			}		
			return fansUser;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<FansImage> getList(String hql,int page, int rows) {
		// TODO Auto-generated method stub
		List<FansImage> FansImagelist=null;
		// TODO Auto-generated method stub
	try {
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(FansImage.class);	
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		FansImagelist=query.list();
			return FansImagelist;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}

	@Override
	public List<FansUser> getFansUserList(String hql) {
		List<FansUser> fansuserlist=null;
		// TODO Auto-generated method stub
	try {
		Query query=	 this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(FansUser.class);
		
		fansuserlist=query.list();
			return fansuserlist;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
	}
	@Override
	public List<FansUser> getFansUserList2(String hql,int page,int pageSize) {
		// TODO Auto-generated method stub
		try {
			Query query= this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(FansUser.class);
			query.setFirstResult((page-1)*pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<FansImageVisit> getFansImageVisitList(String hql) {
		// TODO Auto-generated method stub
		List<FansImageVisit> list=null;
		try {
		
			Query query  =this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(FansImageVisit.class);
			list=query.list();
			if(list.size()==0){
				return list;
			}else{
				return list;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<FansUser> getAllFans(String sql,int page,int pageSize) {
		// TODO Auto-generated method stub
		List<FansUser>fansList=null;
		try {
			Query query= this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FansUser.class);
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);
			fansList=query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fansList.size()==0){
			return fansList;
		}
		return fansList;
	}

	@Override
	public List<FansUser> getFansCount(String sql) {
		List<FansUser>fansList=null;
		// TODO Auto-generated method stub
		try {
			
			Query query= this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FansUser.class);
			fansList=query.list();
			return fansList;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fansList;
	}



	@Override
	public int getFansImageCount(String hql) {
		// TODO Auto-generated method stub
		List<FansUser> fansuserlist=null;
		// TODO Auto-generated method stub
	try {
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(FansImage.class);
		
		fansuserlist=query.list();
			return fansuserlist.size();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}

  }

	@Override
	public int getupdateFansImage(int enterId,String sql) {
		int row=0;
		// TODO Auto-generated method stub
		//String sql ="UPDATE wxpt"+enterId+".fans SET `fans_value`='"+fanuser.getFansValue()+"' WHERE `fans_id`="+fans.getFansId();
				//String sql="UPDATE "+enterId+".fans_user SET fans_user_name='"+fanuser.getFansUserName()+"',`update_time`='"+TimeUtil.getTime()+"',`signature`='"+fanuser.getSignature()+"',`nickname`='"+fanuser.getNickname()+"',`touxiang`='"+fanuser.getTouxiang()+"' WHERE fans_user_id="+fanuser.getFansUserId()+"";
				System.out.println(sql);
				con=jdbc.getConnection(enterId);
				try {
					ps = con.prepareStatement(sql);
					/*pstmt.setString(1, "userName");
					pstmt.setString(2, "userNum");
					pstmt.setInt(3, 5);*/
					//执行
					 row=ps.executeUpdate();
					con.close();	//关闭数据库连接
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return row;
		
	}

	@Override
	public void addFansUser(int enterId, FansUser fanuser) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".fans_user( `fans_user_name`, `update_time`, " +
				" `touxiang`) VALUES ('"+fanuser.getFansUserName()+"','"+TimeUtil.getTime()+"','"+fanuser.getTouxiang()+"')";
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
	public void addFansImage(int enterId, FansImage fansImage) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt wxpt"+enterId+".`fans_image`( `fans_image_value`, " +
				"`image_update_time`, `fans_user_id`, `check_state`) " +
				"VALUES ('"+fansImage.getFansImageValue()+"','"+TimeUtil.getTime()+"',"+fansImage.getFansUser().getFansUserId()+","+0+")";
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
	public void updateFansUser(int enterId, FansUser fanuser) {
		// TODO Auto-generated method stub
		String sql="UPDATE wxpt"+enterId+".fans_user SET fans_user_name='"+fanuser.getFansUserName()+"',`update_time`='"+TimeUtil.getTime()+"',`signature`='"+fanuser.getSignature()+"',`nickname`='"+fanuser.getNickname()+"',`touxiang`='"+fanuser.getTouxiang()+"' WHERE fans_user_id="+fanuser.getFansUserId()+"";
		System.out.println(sql);
		con=jdbc.getConnection(enterId);
		try {
			ps = con.prepareStatement(sql);
			/*pstmt.setString(1, "userName");
			pstmt.setString(2, "userNum");
			pstmt.setInt(3, 5);*/
			//执行
			ps.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addFans(String sql) {
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

	@SuppressWarnings("static-access")
	@Override
	public void updateFans(int enterId, String sql) {
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

	@Override
	public void deleFans(String sql) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public List<FansImage> getList2(String hql) {
		// TODO Auto-generated method stub
		List<FansImage> FansImagelist=null;
		// TODO Auto-generated method stub
	try {
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(FansImage.class);	
		
		FansImagelist=query.list();
			return FansImagelist;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}

	@Override
	public void addFansImageVisit(int enterId, FansImageVisit fansImageVisit) {
		System.out.println(fansImageVisit);
		String sql="INSERT INTO wxpt"+enterId+".`fans_image_visit`(`fans_visit_key`, " +
				"`fans_user_name`, `fans_visit_value`, `fans_image_id`, `fans_user_id`) VALUES " +
				"('"+fansImageVisit.getFansVisitKey()+"','"+fansImageVisit.getFansUserName()+"','"+fansImageVisit.getFansVisitValue()+"',"+fansImageVisit.getFansImage().getFansImageId()+","+fansImageVisit.getFansUser().getFansUserId()+")";
		System.out.println(sql);
		session=(Session)this.sessionFactory.getCurrentSession();
	
		session.beginTransaction();
		try {
			session.connection().createStatement().executeUpdate(sql);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public int getFansImageVisit(int enterId, String sql) {
		int row=0;
		// TODO Auto-generated method stub
				System.out.println(sql);
				con=jdbc.getConnection(enterId);
				try {
					ps = con.prepareStatement(sql);
					//执行
					 row=ps.executeUpdate();
					con.close();	//关闭数据库连接
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return row;	}

	@SuppressWarnings("unchecked")
	@Override
	public Fans getFansByNameValue(String sql) {
		// TODO Auto-generated method stub
		try {
			List<Fans> list = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Fans.class).list();
			Fans fans = list.get(0);
			return fans;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
