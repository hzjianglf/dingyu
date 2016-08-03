package com.ibm.paser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.AlertCondition;
import com.ibm.hibernate.AlertConditionDAO;
import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;

public class InitStatus extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(InitStatus.class);
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public InitStatus() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here				
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPut method of the servlet. <br>
	 *
	 * This method is called when a HTTP put request is received.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		BrokerInfoDAO dao = new BrokerInfoDAO();
		List<BrokerInfo> list = new ArrayList<BrokerInfo>();
		
		list = dao.findByStatus("1");
		for(Iterator<BrokerInfo> i = list.iterator(); i.hasNext();) {	
			BrokerInfo brokerInfo = new BrokerInfo();
			brokerInfo = (BrokerInfo)i.next();			
			if (brokerInfo.getStatus().equalsIgnoreCase("1")) {
				brokerInfo.setStatus("0");
				dao.update(brokerInfo);
			}
		}
		
		//Æô¶¯WMB¼à¿Ø
//		BrokerInfoDAO dao = new BrokerInfoDAO();
//		List<BrokerInfo> list = new ArrayList<BrokerInfo>();
//		
//		list = dao.findAll();
//		for(Iterator<BrokerInfo> i = list.iterator(); i.hasNext();) {	
//			BrokerInfo brokerInfo = new BrokerInfo();
//			brokerInfo = (BrokerInfo)i.next();			
//			Thread run = new Thread(new JmsConsumer(brokerInfo, dao));
//			run.start();
//		}
		
		AlertConditionDAO alertDao = new AlertConditionDAO();
		List<AlertCondition> alertList = new ArrayList<AlertCondition>();
		alertList = alertDao.findAlertConditionByStatus("1");
		for(Iterator<AlertCondition> i = alertList.iterator(); i.hasNext();) {	
			AlertCondition condition = new AlertCondition();
			condition = (AlertCondition)i.next();						
			condition.setStatus("0");
			alertDao.update(condition);
		}		
		
	}
}
