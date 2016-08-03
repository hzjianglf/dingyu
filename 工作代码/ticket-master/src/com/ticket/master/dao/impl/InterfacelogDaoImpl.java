package com.ticket.master.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.crypto.Data;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import com.ticket.master.common.TimeUtil;
import com.ticket.master.dao.InterfaceLogDao;
import com.ticket.master.entity.HibernateSessionFactory;
import com.ticket.master.entity.InterfaceLog;
import com.ticket.master.entity.UserInterface;

@SuppressWarnings("unchecked")
public class InterfacelogDaoImpl implements InterfaceLogDao {

	public List<InterfaceLog> getInterfaceLog(String sql) {
		// TODO Auto-generated method stub
		List<InterfaceLog> interFaceLogList = null;
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction tx = session.beginTransaction();
			tx = session.beginTransaction();
			interFaceLogList = session.createSQLQuery(sql)
					.addEntity(InterfaceLog.class).list();
			tx.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			interFaceLogList = null;
		}finally{
			session.clear();
			session.close();
		}
		return interFaceLogList;
	}

	public List<String> getOrderIDList(List<InterfaceLog> interFaceLogList,
			String xml) {
		List<String> orderList = new ArrayList<String>();
		String start = null;
		String count = null;
		if (interFaceLogList == null) {
			return null;
		} else {
			try {
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();
				String orderID = root.elementText("OrderID");
				String departureCity = root.elementText("DepartureCity");
				String arrivalCity = root.elementText("ArrivalCity");
				String flightNo = root.elementText("FlightNo");
				start = root.elementText("Start");
				count = root.elementText("Count");
				String dateType = root.elementText("DateType");
				String startDate = root.elementText("StartDate");
				String endDate = root.elementText("EndDate");
				for (InterfaceLog interfaceLog : interFaceLogList) {
					String reponseXml = interfaceLog.getRequestContent();
					document = DocumentHelper.parseText(reponseXml);
					root = document.getRootElement();
					String responseDataType = root
							.elementText("ResponseDataType");
					String responseDepartureCity = root
							.elementText("DepartureCity");
					String responseArrivalCity = root
							.elementText("ArrivalCity");
					String responseFlightNo = null;
					String depDate = null;
					List<Element> FlightList = root.selectNodes("//Legs/Leg");
					for (Element element : FlightList) {
						responseFlightNo = element.elementText("FlightNo");
						depDate = element.elementText("DepDate");
					}

					if (dateType.equals("2")) {
						if (!TimeUtil.getResult(depDate, startDate, endDate)) {
							continue;
						}
					}

					if (departureCity != null
							&& departureCity.equals("") == false
							&& responseDepartureCity.equals(departureCity) == false) {
						continue;
					}

					if (arrivalCity != null && arrivalCity.equals("") == false
							&& responseArrivalCity.equals(arrivalCity) == false) {
						continue;
					}

					if (flightNo != null && flightNo.equals("") == false
							&& responseFlightNo.equals(flightNo) == false) {
						continue;
					}

					if (responseDataType.equals("1")) {
						document = DocumentHelper.parseText(interfaceLog
								.getReturnContent());
						root = document.getRootElement();
						String status = root.elementText("Status");
						if (status.equals("0")) {
							String orderno = root.elementText("OrderId");
							if (orderID.equals("")) {
								orderList.add(orderno);
							} else {
								if (orderID.equals(orderno)) {
									orderList.add(orderno);
								}
							}

						}
					} else {
						JSONObject jsob = new JSONObject(
								interfaceLog.getReturnContent());
						String str = jsob.getString("Res");
						JSONObject jsob2 = new JSONObject(str);
						String status = jsob2.getString("Status");
						if (status.equals("0")) {
							String orderno = jsob2.getString("OrderId");
							if (orderID.equals("")) {
								orderList.add(orderno);
							} else {
								if (orderID.equals(orderno)) {
									orderList.add(orderno);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (start != null && count != null && start.equals("") == false
				&& count.equals("") == false) {
			if (Integer.parseInt(start) * Integer.parseInt(count) > orderList
					.size()) {
				if (Integer.parseInt(start) > orderList.size()) {
					orderList.subList(orderList.size() - 1,
							orderList.size() - 1);
				} else {
					orderList.subList(Integer.parseInt(start),
							orderList.size() - 1);
				}

			} else {
				if (Integer.parseInt(start) > orderList.size()) {
					orderList.subList(orderList.size() - 1,
							Integer.parseInt(start) * Integer.parseInt(count)
									- 1);
				} else {
					orderList.subList(Integer.parseInt(start) - 1,
							Integer.parseInt(start) * Integer.parseInt(count)
									- 1);
				}

			}
		}
		return orderList;
	}

	public UserInterface getInterfaceByInterfaceName(String userName,
			int interfaceId) {
		Session session = HibernateSessionFactory.getSession();
		try {
			// TODO Auto-generated method stub
			String hql = "from UserInterface where interfacs.interfaceId = "
					+ interfaceId + " and user.username = '" + userName + "'";
			
			Transaction tx = session.beginTransaction();
			tx = session.beginTransaction();
			UserInterface userInterFace = (UserInterface) session
					.createQuery(hql).list().get(0);
			tx.commit();

			return userInterFace;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally{
			session.clear();
			session.close();
		}
	}

	public int getInterfaceCountByInterfaceName(String userName, int interfaceId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from InterfaceLog where interfacs.interfaceId = "
				+ interfaceId + " and user.username = '" + userName + "'";
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			int count = ((Number)query.uniqueResult()).intValue(); 
			tx.commit();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}finally{
			session.clear();
			session.close();
		}
	}
}
