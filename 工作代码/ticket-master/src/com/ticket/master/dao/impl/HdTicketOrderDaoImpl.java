package com.ticket.master.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.master.entity.HdTicketOrder;
import com.ticket.master.entity.HibernateSessionFactory;
import com.ticket.master.entity.Res;
import com.ticket.master.dao.HdTicketOrderDao;

public class HdTicketOrderDaoImpl implements HdTicketOrderDao{

	public HdTicketOrder findByOrderId(String hql) {
		try {
			Session session = HibernateSessionFactory.getSession(); 
			 Transaction tx = session.beginTransaction();
			 tx=session.beginTransaction();
			List<HdTicketOrder> resList=new ArrayList<HdTicketOrder>();
			resList = 	session.createQuery(hql).list();
			 tx.commit();
			 return resList.get(0);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	public void update(HdTicketOrder ho) {
		try {
			Session session = HibernateSessionFactory.getSession(); 
			 Transaction tx = session.beginTransaction();
			 tx=session.beginTransaction();
			 session.update(ho);
			 tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
