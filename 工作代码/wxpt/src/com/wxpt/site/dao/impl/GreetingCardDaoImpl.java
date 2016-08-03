package com.wxpt.site.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.wxpt.action.site.ParentAction;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.site.dao.GreetingCardDao;
import com.wxpt.site.entity.FandsGreetingCard;
import com.wxpt.site.entity.GreetingCard;
import com.wxpt.site.entity.GreetingCardProterty;
import com.wxpt.site.entity.GreetingCardTemplate;
import com.wxpt.site.entity.GreetingCardType;

@SuppressWarnings({ "unchecked" })
public class GreetingCardDaoImpl extends ParentDaoImpl implements
		GreetingCardDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<GreetingCardType> getCardType(String sql) {
		// TODO Auto-generated method stub
		List<GreetingCardType> greetingCardType = this.sessionFactory
				.getCurrentSession().createSQLQuery(sql)
				.addEntity(GreetingCardType.class).list();
		return greetingCardType;
	}

	@Override
	public List<GreetingCard> getCard(String sql) {
		// TODO Auto-generated method stub
		List<GreetingCard> greetingCard = this.sessionFactory
				.getCurrentSession().createSQLQuery(sql)
				.addEntity(GreetingCard.class).list();
		return greetingCard;
	}

	@Override
	public List<GreetingCard> getCard(String sql, int page, int rows) {

		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(GreetingCard.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<GreetingCard> cardList = query.list();
		return cardList;

	}

	@Override
	public List<GreetingCardType> getCardType(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(GreetingCardType.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<GreetingCardType> cardTypeList = query.list();
		return cardTypeList;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int executeSql(String sql) {
		try {
			Session session = (Session) this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			int result = session.connection().createStatement()
					.executeUpdate(sql);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	public Serializable executeSql(String sql,int enterId) {
		Serializable ret = 0;
		try {
			// TODO Auto-generated method stub
			Connection conn = (Connection) jdbc.getConnection(enterId);
			  PreparedStatement  state=  (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);     
		        ResultSet rs=null;
		            state.executeUpdate();
		            rs = (ResultSet) state.getGeneratedKeys();
		            if (rs.next()) {
		                ret = (Serializable) rs.getObject(1);
		            }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
	}
	
	@Override
	public FandsGreetingCard getFansCard(String sql) {
		// TODO Auto-generated method stub
		return (FandsGreetingCard) this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(FandsGreetingCard.class).list()
				.get(0);
	}

	@Override
	public List<GreetingCardTemplate> getCardTemplate(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(GreetingCardTemplate.class).list();
	}

	@Override
	public List<GreetingCardTemplate> getCardTemplate(String sql, int page,
			int rows) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(GreetingCardTemplate.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<GreetingCardTemplate> cardTypeList = query.list();
		return cardTypeList;
	}

	@Override
	public List<GreetingCardProterty> getCardProterty(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(GreetingCardProterty.class).list();
	}

}
