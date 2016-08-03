package com.ibm.alert;

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

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.AlertCondition;
import com.ibm.hibernate.AlertInfo;
import com.ibm.hibernate.AlertInfoDAO;
import com.ibm.hibernate.MonitorMainDAO;
import com.ibm.hibernate.MonitorNodeItemsDAO;

public class CreateAlert implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(CreateAlert.class);
	
	public String currentTime;
	public String lastCheckTime;
	public String flag;
	public String filePath;
	
	AlertCondition condition = new AlertCondition();		
	MonitorMainDAO mmDao = new MonitorMainDAO();
	MonitorNodeItemsDAO mmItemDao = new MonitorNodeItemsDAO();
	AlertInfoDAO aInfoDAO = new AlertInfoDAO();	
		
	public CreateAlert(String currentTime, AlertCondition condition, String flag, String filePath) {
		this.currentTime = currentTime;
		this.condition = condition;
		this.flag = flag;
		this.filePath = filePath;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");   
 
		Date currentDate;
		try {
			currentDate = sdf.parse(currentTime);			
			Calendar g = Calendar.getInstance(); 
			g.setTime(currentDate);
			
			g.add(Calendar.MINUTE, 0 - condition.getInterval().intValue());              
			Date lastCheckDate = g.getTime();   
			lastCheckTime = sdf.format(lastCheckDate); 
			
		} catch (ParseException e) {
			//e.printStackTrace();
		}   
		
	}
	
	public void run() {
					 			
		String flowname = condition.getFlowname();
		String conditionitem = condition.getConditionitem();
		int numberitem = condition.getNumberitem().intValue();				
		String description = condition.getDescription();
		
		if (conditionitem.equalsIgnoreCase("1")) {					
			int totalNumber = mmDao.getAlertTotalNumber(flowname, lastCheckTime, currentTime);
			if (totalNumber > numberitem) {
				UUID uuid = UUID.randomUUID();
				AlertInfo info = new AlertInfo();
				info.setAlertuuid(uuid.toString());
				info.setCreatetime(currentTime);
				info.setFlowname(flowname);
				info.setConditionitem(conditionitem);
				info.setNumberitem(new Integer(numberitem).toString());
				info.setCurcount(new Integer(totalNumber).toString());
				info.setDescription(description);
				aInfoDAO.save(info);
				
				String context = "北京时间 " + currentTime + "，ESB产生告警信息：在最近的" + condition.getInterval() + "分钟内，实际处理的消息总数为" + totalNumber + "，大于告警条件设定的阈值" + numberitem;
				if (flag.equalsIgnoreCase("2")) {										
					sendmail(filePath, context);
				} else if (flag.equalsIgnoreCase("3")){
					Thread run = new Thread(new AlertMessageProducer(filePath, condition.getMqtopic(), context));					
					run.start();
				}
			}
		} else if (conditionitem.equalsIgnoreCase("2")) {
			int totalNumber = mmDao.getAlertErrorNumber(flowname, lastCheckTime, currentTime);
			if (totalNumber > numberitem) {
				UUID uuid = UUID.randomUUID();
				AlertInfo info = new AlertInfo();
				info.setAlertuuid(uuid.toString());
				info.setCreatetime(currentTime);
				info.setFlowname(flowname);
				info.setConditionitem(conditionitem);
				info.setNumberitem(new Integer(numberitem).toString());
				info.setCurcount(new Integer(totalNumber).toString());
				info.setDescription(description);
				aInfoDAO.save(info);
				
				if (flag.equalsIgnoreCase("2")) {
					String context = "北京时间 " + currentTime + "，ESB产生告警信息：在最近的" + condition.getInterval() + "分钟内，实际产生的错误消息总数为" + totalNumber + "，大于告警条件设定的阈值" + numberitem;
					
					sendmail(filePath, context);
				}
			}
		} else if (conditionitem.equalsIgnoreCase("3")) {
			double avgresptime = mmItemDao.getAlertWsReqAvg(flowname, lastCheckTime, currentTime);					
			if (avgresptime > numberitem) {
				UUID uuid = UUID.randomUUID();
				AlertInfo info = new AlertInfo();
				info.setAlertuuid(uuid.toString());
				info.setCreatetime(currentTime);
				info.setFlowname(flowname);
				info.setConditionitem(conditionitem);
				info.setNumberitem(new Integer(numberitem).toString());
				info.setCurcount(new Double(avgresptime).toString());
				info.setDescription(description);
				aInfoDAO.save(info);
				
				if (flag.equalsIgnoreCase("2")) {
					String context = "北京时间 " + currentTime + "，ESB产生告警信息：在最近的" + condition.getInterval() + "分钟内，实际服务请求平均响应时间为" + avgresptime + "ms，大于告警条件设定的阈值" + numberitem + "ms";
					
					sendmail(filePath, context);
				}
			}
		} else if (conditionitem.equalsIgnoreCase("4")) {
			double avgresptime = mmDao.getAlertWsProvideAvg(flowname, lastCheckTime, currentTime);					
			if (avgresptime > numberitem) {
				UUID uuid = UUID.randomUUID();
				AlertInfo info = new AlertInfo();
				info.setAlertuuid(uuid.toString());
				info.setCreatetime(currentTime);
				info.setFlowname(flowname);
				info.setConditionitem(conditionitem);
				info.setNumberitem(new Integer(numberitem).toString());
				info.setCurcount(new Double(avgresptime).toString());
				info.setDescription(description);
				aInfoDAO.save(info);
				
				if (flag.equalsIgnoreCase("2")) {
					String context = "北京时间 " + currentTime + "，ESB产生告警信息：在最近的" + condition.getInterval() + "分钟内，实际服务提供平均响应时间为" + avgresptime + "ms，大于告警条件设定的阈值" + numberitem + "ms";
					
					sendmail(filePath, context);
				}
			}
		} else if (conditionitem.equalsIgnoreCase("5")) {
			int failureNumber = mmItemDao.getAlertWsReqFailure(flowname, lastCheckTime, currentTime);
			if (failureNumber > numberitem) {
				UUID uuid = UUID.randomUUID();
				AlertInfo info = new AlertInfo();
				info.setAlertuuid(uuid.toString());
				info.setCreatetime(currentTime);
				info.setFlowname(flowname);
				info.setConditionitem(conditionitem);						
				info.setCurcount(new Integer(failureNumber).toString());
				info.setNumberitem(new Integer(numberitem).toString());
				info.setDescription(description);
				aInfoDAO.save(info);
				
				if (flag.equalsIgnoreCase("2")) {
					String context = "北京时间 " + currentTime + "，ESB产生告警信息：在最近的" + condition.getInterval() + "分钟内，外部服务不可用";
					
					sendmail(filePath, context);
				}
			}
		}
				
	}
	
	public void sendmail(String filePath, String context) {
		MailTo sm = new MailTo();
		
		sm.setSMTPHost(readValue(filePath,"smtpserver"));
		sm.setMailAccount(readValue(filePath,"emailid"));
		sm.setMailPassword(readValue(filePath,"emailpwd"));
		sm.setMailFrom(readValue(filePath,"emailid"));
		sm.setMailTo(condition.getEmail());		
		sm.setMsgContent(context);
		sm.setSubject("ESB SLA告警提示");
		
		try {
			sm.sendMail();
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (MessagingException e) {
			log.error(e.getMessage());			
		}
		
	}
	
	public String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			InputStream ips = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(ips);
			String value = props.getProperty(key);
			
			return value;
		} catch (FileNotFoundException e) {
			
			return "";
		} catch (IOException e) {
			
			return "";
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {


	}

}
