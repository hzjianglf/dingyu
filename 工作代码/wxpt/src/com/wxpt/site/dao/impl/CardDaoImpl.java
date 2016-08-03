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
import com.wxpt.site.dao.CardDao;
import com.wxpt.site.entity.Card;

public class CardDaoImpl implements CardDao{
@Autowired
SessionFactory sessionFactory;
protected Session session=null;
JDBC_test jdbc=new JDBC_test();
Connection con = null;
PreparedStatement pstmt = null;
	@Override
	public List<Card> findByCardId(int cardId) {
		String hql="from Card where cardId="+cardId;
		return this.sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	@Override
	public int findByCardImage(String cardImage,int enterId) {
		//String hql="from Card where cardImage='"+cardImage+"'";
		String sql="select card_id from wxpt"+enterId+".card where card_image='"+cardImage+"'";
		int cardId;
		try {
			cardId = (Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return cardId;
	}
	@Override
	public void updateCard(Card card,int enterId) {

		String sql="UPDATE wxpt"+enterId+".card SET `card_name`='"+card.getCardName()+"',`card_image`='"+card.getCardImage()+"',`card_count`="+card.getCardCount()+",`card_type`="+card.getCardType()+" WHERE `card_id`="+card.getCardId()+"";
		con=jdbc.getConnection(enterId);
		try {
			pstmt=pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//执行
			
		//this.sessionFactory.getCurrentSession().update(card);
		
	}
	@Override
	public void save(Card card,int enterId) {
		String sql="insert into wxpt"+enterId+".card(card_id,card_name,card_image,card_count " +
				",card_type)values('"+card.getCardId()+"','"+card.getCardImage()+"','"+card.getCardImage()+"',"+card.getCardCount()+","+card.getCardType()+")";
		
		try {
			session=(Session) this.sessionFactory.getCurrentSession();
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
	public List<Card> findQuery(int enterId) {
		String sql="select * from wxpt"+enterId+".card where card_type>0";
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Card.class).list();
	}
	@Override
	public List<Card> findAllQuery(int page, int rows,int enterId) {
		String hql = "select * from wxpt"+enterId+".card ";
		Query query= this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Card.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}
	@Override
	public int getCardCount() {
		String hql="from Card where cardType >0";
		return this.sessionFactory.getCurrentSession().createQuery(hql).list().size();
	}
	@Override
	public int getByMaxType(int enterId) {
		String sql="select max(card_type) from wxpt"+enterId+".card";
		List list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		if(list.get(0)==null){
			return 0;
		}else{
			return (Integer)list.get(0);
		}
		//return (Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
	}
	@Override
	public Card findByCardImageEntity(String cardName, int enterId) {
		String sql="select * from wxpt"+enterId+".card where card_name='"+cardName+"'";
		return (Card) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Card.class).list().get(0);
	}
	@Override
	public Card findByCardId(int cardId, int enterId) {
		String sql="select * from wxpt"+enterId+".card where card_id="+cardId+"";
		return (Card) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Card.class).list().get(0);
	}

}
