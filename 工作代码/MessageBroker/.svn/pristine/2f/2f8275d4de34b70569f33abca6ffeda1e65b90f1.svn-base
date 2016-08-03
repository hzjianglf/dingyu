package com.ibm.throughput;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.PartofthroughoutControl;
import com.ibm.hibernate.PartofthroughoutControlDAO;

public class ThroughputStarter implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(ThroughputStarter.class);
	
	PartofthroughoutControl control = new PartofthroughoutControl();
	PartofthroughoutControlDAO controlDao = new PartofthroughoutControlDAO();


	public ThroughputStarter(PartofthroughoutControl control){
		this.control = control;
	}
	
	public void run() {
		String flowName = control.getFlowname();

		
		log.info("流量控制'" + flowName + "' 已经启动。");
						
			try {
				Thread.sleep((long)(Integer.parseInt(control.getPartofinterval()) * java.lang.Math.pow(60, Integer.parseInt(control.getPartofunit()) - 1 ) * 1000));				
			} catch (InterruptedException e) {			
				log.error(e.getMessage());
			}

			Calendar day = Calendar.getInstance();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");			
			String date = sdf.format(day.getTime());			
			
			Thread run = new Thread(new CreateThroughput(date, control));
			run.start();
			log.info("流量控制 '" + flowName +"' "+Integer.parseInt(control.getPartofinterval())*60  + " 秒轮询一次。");

	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
}
