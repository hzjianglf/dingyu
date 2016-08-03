package com.ibm.throughput;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

//import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.alert.AlertMessageProducer;
import com.ibm.alert.MailTo;
import com.ibm.hibernate.AlertCondition;
import com.ibm.hibernate.AlertInfo;
import com.ibm.hibernate.AlertInfoDAO;
import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.ibm.hibernate.MonitorMainDAO;
import com.ibm.hibernate.MonitorNodeItemsDAO;
import com.ibm.hibernate.PartofthroughoutControl;
import com.ibm.hibernate.PartofthroughoutControlDAO;
import com.ibm.pcf.PCF_QueueHelper;

public class CreateThroughput implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(CreateThroughput.class);
	
	public String currentTime;
	public String lastCheckTime;
	
	

	PartofthroughoutControl control = new PartofthroughoutControl();
	PartofthroughoutControlDAO controlDao = new PartofthroughoutControlDAO();
		
	public CreateThroughput(String currentTime, PartofthroughoutControl control) {
		this.currentTime = currentTime;
		this.control = control;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");   
 
		Date currentDate;
		try {
			currentDate = sdf.parse(currentTime);			
			Calendar g = Calendar.getInstance(); 
			g.setTime(currentDate);
			
			g.add(Calendar.MINUTE, 0 - Integer.parseInt(control.getPartofinterval()));              
			Date lastCheckDate = g.getTime();
			lastCheckTime = sdf.format(lastCheckDate); 
			
		} catch (ParseException e) {
			//e.printStackTrace();
		}   
		
	}
	
	public synchronized boolean getRunStatus()
	{
		control = controlDao.findByFlowName(control.getFlowname()).iterator().next();
		return (control == null || control.getStatus() == null || control.getStatus().equalsIgnoreCase("0"))? false: true;
	}
	
	public void run() {
					 			
		String flowname = control.getFlowname();
		log.info("流量控制'" + flowname + "' 已经启动。");
		
		while(getRunStatus()) {
			
			try {
				Thread.sleep((long)(Integer.parseInt(control.getPartofinterval()) * java.lang.Math.pow(60, Integer.parseInt(control.getPartofunit()) - 1 ) * 1000));				
			} catch (InterruptedException e) {			
				log.error(e.getMessage());
			}			
			
			
			try {
				BrokerInfo brokerInfo = new BrokerInfo();
	        	BrokerInfoDAO dao = new BrokerInfoDAO();
	    		brokerInfo = (BrokerInfo)dao.findAll().get(0);
				PCF_QueueHelper.clearQueue(brokerInfo.getQmgrname(), flowname, brokerInfo.getHostname(), Integer.parseInt(brokerInfo.getPort()), brokerInfo.getSvrconn(), Integer.parseInt(control.getPartofthreshold()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(new java.util.Date() + "流量控制 '" + flowname + "' "+ Integer.parseInt(control.getPartofinterval()) * 60 + " 秒轮询一次。");
			
		}

		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {


	}

}
