package com.ibm.alert;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.AlertCondition;
import com.ibm.hibernate.AlertConditionDAO;

public class StartAlert implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(StartAlert.class);
	
	AlertCondition condition = new AlertCondition();
	AlertConditionDAO alertDao = new AlertConditionDAO();
	
	private String filePath = null;
	private String flag = null;

	public StartAlert(AlertCondition condition, String filePath){
		this.condition = condition;
		this.filePath = filePath;
	}
	
	public void run() {
		String alertname = condition.getAlertname();
		if (condition.getNotice().equalsIgnoreCase("1")) {
			flag = "1";			
		} else if (condition.getNotice().equalsIgnoreCase("2")) {
			flag = "2";			
		} else {
			flag = "3";
		}
		
		log.info("告警监控 '" + alertname + "' 已经启动。");
				
		while(true) {			
			try {
				Thread.sleep(condition.getInterval().intValue() * 60 * 1000);				
			} catch (InterruptedException e) {			
				log.error(e.getMessage());
			}
			AlertCondition check = (AlertCondition)alertDao.findAlertConditionByName(alertname).iterator().next();
			if (check.getStatus().equalsIgnoreCase("0")) {
				log.info("告警监控 '" + condition.getAlertname() + "' 已经停止。");
				break;
			}
			
			Calendar day = Calendar.getInstance();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");			
			String date = sdf.format(day.getTime());			
			
			Thread run = new Thread(new CreateAlert(date, condition, flag, filePath));
			run.start();
			log.info("告警条件检查已经完成，告警信息 " + condition.getInterval().intValue() * 60 + " 秒轮询一次。");
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
