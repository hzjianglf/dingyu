package com.ticket.master.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ticket.master.dao.FlightDatasDao;
import com.ticket.master.entity.CabinData;
import com.ticket.master.entity.FlightData;
import com.ticket.master.entity.HibernateSessionFactory;
import com.ticket.master.entity.Res;
import com.ticket.master.entity.User;

public class FlightDatasDaoImpl implements FlightDatasDao{

	public void saveFlightDatas(FlightData fd) {
		try {
			Session session = HibernateSessionFactory.getSession(); 
			 Transaction tx = session.beginTransaction();
			 tx=session.beginTransaction();
			 session.save(fd);
			 tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
	}

	public Res findById(int resId) {
		try {
			Session session = HibernateSessionFactory.getSession(); 
			 Transaction tx = session.beginTransaction();
			 tx=session.beginTransaction();
			List<Res> resList=new ArrayList<Res>();
			resList = 	session.createQuery("from Res where resId="+resId).list();
			 tx.commit();
			 return resList.get(0);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	public void saveCabinData(CabinData cd) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession(); 
			 Transaction tx = session.beginTransaction();
			 tx=session.beginTransaction();
			 session.save(cd);
			 tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  
}
