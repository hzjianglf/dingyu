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
import com.wxpt.site.dao.CardReordsDao;
import com.wxpt.site.entity.CardRecords;

public class CardReordsDaoImpl implements CardReordsDao{
@Autowired
SessionFactory sessionFactory;
PreparedStatement ps=null;
Connection con=null;
JDBC_test jdbc =new JDBC_test();
protected Session session = null;
	@Override
	public List<CardRecords> findByCardId(int cardId) {
		String hql="from CardReords where cardId="+cardId;
		return this.sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	@Override
	public int getBycardUserName(String cardUserName,int enterId) {
		List<CardRecords> list;
		try {
			//String hql="from CardRecords where cardUserName='"+cardUserName+"'";
			String sql="select * from wxpt"+enterId+".card_records where card_userName='"+cardUserName+"'";
			list = new ArrayList<CardRecords>();
			
			list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return list.size();
	}
	@Override
	public void saveCardRecords(CardRecords cardRecords,int enterId) {
		String sql="INSERT INTO wxpt"+enterId+".card_records(`card_id`, " +
				"`card_get_time`, `exchange_awd_time`, `card_userName`, `card-prize-desc`) " +
				"VALUES ("+cardRecords.getCard().getCardId()+",'"+cardRecords.getCardGetTime()+"', " +
				"'"+cardRecords.getExchangeAwdTime()+"','"+cardRecords.getCardUserName()+"','"+cardRecords.getCardPrizeDesc()+"')";
				System.out.println(sql);
		try { 
			session=(Session)this.sessionFactory.getCurrentSession();
			session.beginTransaction();
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
	public List<CardRecords> findAllQuery(int page, int rows,int enterId) {
		//String hql = "from CardRecords where card.cardType!=0 and card.cardType!=-1";
		String sql="select * from wxpt"+enterId+".card_records crs where crs.card_id in " +
				"(select c.card_id from wxpt"+enterId+".card c where c.card_type > 0 and c.card_id=crs.card_id)";
//				"(select c.card_id from wxpt"+enterId+".card c where c.card_id=crs.card_id)";
	System.out.println(sql);
		Query query= this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(CardRecords.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		List<CardRecords> list=new ArrayList<CardRecords>();
		list=query.list();
		return list;
	}
	@Override
	public void update(CardRecords cardRecords,int enterId) {
		String sql="UPDATE wxpt"+enterId+".card_records SET `card_id`="+cardRecords.getCard().getCardId()+",`card_get_time`='"+cardRecords.getCardGetTime()+"',`exchange_awd_time`='"+cardRecords.getExchangeAwdTime()+"',`card_userName`='"+cardRecords.getCardUserName()+"',`card-prize-desc`='"+cardRecords.getCardPrizeDesc()+"' WHERE  `card_records_id`="+cardRecords.getCardRecordsId();
		try {
			con = jdbc.getConnection(enterId);
			ps= con.prepareStatement(sql);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public CardRecords findByRecordsCardId(int cardRecordsId,int enterId) {
		String hql="from CardRecords where cardRecordsId="+cardRecordsId;
		String sql="select * from wxpt"+enterId+".card_records where card_records_id="+cardRecordsId;
		return (CardRecords) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(CardRecords.class).list().get(0);
	}
	@Override
	public void delete(int cardRecordsId,int enterId) {
		String sql="delete from wxpt"+enterId+".card_records where card_records_id="+cardRecordsId;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		
	}
	@Override
	public int getByCardType(int cardType,int enterId) {
		//String hql="from CardRecords where card.cardType="+cardType;
		String sql="select * from wxpt"+enterId+".card_records cr where cr.card_id in(select" +
				" card_id from wxpt"+enterId+".card where card_type="+cardType+")";
		System.out.println(sql);
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().size();
	}
	@Override
	public int getCount(int enterId) {
		List<CardRecords> list =new ArrayList<CardRecords>();
		
		String sql="select * from wxpt"+enterId+".card_records crs where crs.card_id in " +
				"(select c.card_id from wxpt"+enterId+".card c where c.card_type > 0 and c.card_id=crs.card_id)";
		try {
			
			list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(CardRecords.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		return list.size();
	}
	@Override
	public int getBycardUserName(String fromUsername, int enterId,
			String moveStartTime) {
		List<CardRecords> list;
		try {
			String hql="SELECT * FROM wxpt"+enterId+".card_records WHERE datediff('"+moveStartTime+"', str_to_date(`card_get_time`,'%Y/%m/%d %H:%i:%s'))<=0 and `card_userName`='"+fromUsername+"'";
			list = new ArrayList<CardRecords>();
			list=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(CardRecords.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return list.size();
	}
	@Override
	public int getByCardType(int i, int enterId, String moveStartTime) {
		try {
			String sql = "SELECT * FROM wxpt"+enterId+" .card_records WHERE datediff('"+moveStartTime+"', str_to_date(`card_get_time`,'%Y/%m/%d %H:%i:%s'))<=0 and" +
					"`card_id`  in ("+i+")";
			
			return this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(CardRecords.class).list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
