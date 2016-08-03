package com.wxpt.site.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.Statement;
import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.LuckDao;
import com.wxpt.site.entity.AnswerRecords;
import com.wxpt.site.entity.LuckUser;
import com.wxpt.site.entity.Luckanwer;

public class LuckDaoImpl implements LuckDao {

	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public void addLuckUser(int enterId,LuckUser luckUser) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".luckUser( `send_user`, `add_time`, `state`, `send_time`) " +
				"VALUES ('"+luckUser.getSendUser()+"','"+luckUser.getAddTime()+"',"+luckUser.getState()+",'"+luckUser.getSendTime()+"')";
		System.out.println(sql);
		/*this.sessionFactory.getCurrentSession().save(luckUser);*/
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


	public LuckUser getMaxLuckUser(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		String hql ="select * from wxpt"+enterId+".luckUser where luckId = (select max(luckId) from wxpt"+enterId+".luckUser where send_user ='"+sendUser+"')";
		try {
			LuckUser u = (LuckUser) this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(LuckUser.class).list().get(0);
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}


	@Override
	public List<LuckUser> getUserList(int enterId,int page,int rows) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".luckUser ";
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(LuckUser.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}


	@Override
	public void updateLkUser(int enterId,int luckid, int state) {
		// TODO Auto-generated method stub
		String sql = "update wxpt"+enterId+".luckUser set state = "+state+" where luckId = "+luckid;
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			/*pstmt.setString(1, "userName");
			pstmt.setString(2, "userNum");
			pstmt.setInt(3, 5);*/
			//执行
			pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void deleteLkUser(int enterId,int luckid) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM wxpt"+enterId+".luckUser where luckid = "+luckid;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@SuppressWarnings("static-access")
	public void addAnswerRecords(int MaxId,int enterId,String time,String forUser,int type){
		con = jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement("insert into answer_records values(?,?,?,?)");
			pstmt.setInt(1, MaxId*1+1);
			pstmt.setString(2, forUser);
			pstmt.setString(3, time);
			pstmt.setInt(4, type);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*String sql="INSERT INTO wxpt"+enterId+".answer_records( `answer_user`, `answer_time`, `type`) " +
				"VALUES ('"+answerRecord.getAnswerUser()+"','"+answerRecord.getAnswerTime()+"',"+answerRecord.getType()+")";
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
		}*/

		
		/*this.sessionFactory.getCurrentSession().save(answerRecord);*/
	}


	@Override
	public List<AnswerRecords> getAnswerRecords(int enterId,int type) {
		
			// TODO Auto-generated method stub
			String hql = "select * from wxpt"+enterId+".answer_records where type = "
					+ type
					+ " and datediff(now(),str_to_date( answer_time,'%Y/%m/%d %H:%i:%s') )>=1 and datediff(now(),str_to_date( answer_time,'%Y/%m/%d %H:%i:%s') ) <= 30 order by recordId desc";
			List<AnswerRecords> l = this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(AnswerRecords.class).list();
			return l;
		
	}


	@Override
	public void addLuckanwer(int enterId,Luckanwer luckAnswer) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".`luckanwer`( `recordId`, `add_time`, `state`, `update_time`) " +
				"VALUES ("+luckAnswer.getAnswerRecords().getRecordId()+",'"+luckAnswer.getAddTime()+"',"+luckAnswer.getState()+",'"+luckAnswer.getUpdateTime()+"')";
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
		
		/*this.sessionFactory.getCurrentSession().save(luckAnswer);*/
	}


	@Override
	public Luckanwer getLuckanwer(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM wxpt"+enterId+".answer_records WHERE `answer_user`='"+sendUser+"'";
		AnswerRecords an=(AnswerRecords) this.sessionFactory.getCurrentSession()
			.createSQLQuery(sql).addEntity(AnswerRecords.class).list().get(0);
		
		String hql = "select * from wxpt"+enterId+".luckanwer where recordId = "+an.getRecordId() +" order by luckanswerid desc";
		try {
			Luckanwer a = (Luckanwer) this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(Luckanwer.class).list().get(0);
			return a;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<Luckanwer> getLuckanwer(int enterId,int page,int rows) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".luckanwer";
		Query query= this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Luckanwer.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}


	@Override
	public void updateLuckanwer(int enterId,int luckanwerid, int state) {
		// TODO Auto-generated method stub
		String sql = "update wxpt"+enterId+".luckanwer set state = "+state+" where 	luckanswerid = "+luckanwerid;
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


	@Override
	public void deleteLuckanwer(int enterId,int luckanwerid) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM wxpt"+enterId+".luckanwer where luckanswerid = "+luckanwerid;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}


	@Override
	public int byluchAnwerId(int enterId, int id) {
		// TODO Auto-generated method stub
		String sql="select recordId from wxpt"+enterId+".luckanwer where luckanswerid="+id;
		int recordId=(Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		return recordId;
	}


	@Override
	public AnswerRecords byRecordId(int enterId, int recordId) {
		// TODO Auto-generated method stub
		String sql="select * from wxpt"+enterId+".answer_records where recordId="+recordId;
		return (AnswerRecords) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(AnswerRecords.class).list().get(0);
	}


	@Override
	public int getluckUserCount(String sql) {
		// TODO Auto-generated method stub
		try {
			List<LuckUser>list=this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(LuckUser.class).list();
			int count = list.size();

			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	
	}


	@Override
	public int getluckAnwerCount(String sql) {
		// TODO Auto-generated method stub
		try {
			List<Luckanwer>list=this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Luckanwer.class).list();
			int count = list.size();

			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public AnswerRecords getMaxId(String sql) {
		// TODO Auto-generated method stub
		return (AnswerRecords) this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(AnswerRecords.class).list().get(0);
	}
	
}
