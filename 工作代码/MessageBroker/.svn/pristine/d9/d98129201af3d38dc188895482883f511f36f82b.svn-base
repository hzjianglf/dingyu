package com.ibm.paser;

import com.ibm.mq.jms.*;
import javax.jms.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.ibm.msg.client.wmq.WMQConstants;

public class JmsConsumer implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(JmsConsumer.class);
	BrokerInfo brokerInfo = new BrokerInfo();
	BrokerInfoDAO dao = new BrokerInfoDAO();

	public JmsConsumer(BrokerInfo brokerInfo, BrokerInfoDAO dao) {
		this.brokerInfo = brokerInfo;
		this.dao = dao;
	}
	
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public void run() {
		// Parse the arguments
		//parseArgs(args);
		 QueueConnectionFactory factory = null; 
	     Queue localQueue = null;
	     QueueSession session = null;
	     QueueConnection conn = null;
	     QueueReceiver receiver = null;
	     int i = 0;
		// Variables
		try {
		       	factory = new MQQueueConnectionFactory();
		       		((MQQueueConnectionFactory)factory).setTransportType(WMQConstants.WMQ_CM_CLIENT);
		       		((MQQueueConnectionFactory)factory).setQueueManager(brokerInfo.getQmgrname());
		       		((MQQueueConnectionFactory)factory).setHostName(brokerInfo.getHostname());
		       		((MQQueueConnectionFactory)factory).setChannel(brokerInfo.getSvrconn());
		       		((MQQueueConnectionFactory)factory).setPort(new Integer(brokerInfo.getPort()).intValue());
		       		((MQQueueConnectionFactory)factory).setCCSID(1381);

		       conn = factory.createQueueConnection();
		       session = conn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		       localQueue = session.createQueue("MBMONITORDATAQ");
		       conn.start();
		       receiver = session.createReceiver(localQueue);
		       
		       System.out.println(Thread.currentThread().getId());
			
		       						
		       PaserMonitorMsg pmm = new PaserMonitorMsg(brokerInfo);
		       log.info("代理 " + brokerInfo.getBname() + " 监控程序启动成功。");
		       
		       // And, receive the message
		       while(true) {
		    	   /***************************************************/
		    	   BytesMessage message = (BytesMessage)receiver.receive(10000);
		    	   /***************************************************/
		    	   if ( message != null) {
		    		   if (message.getStringProperty("Close") == null) {
		    			   try {
		    				   
		    				   byte[] body = new byte[new Long(message.getBodyLength()).intValue()];
		    				   message.readBytes(body);
		    				   pmm.paser(new String(body, "UTF-8"));						
		    			   } catch (Exception e) {
		    				   log.error(e.getMessage()+"---error1");
		    				   e.printStackTrace();
		    				   continue;
		    			   }
		    		   } else if (message.getStringProperty("Close").equalsIgnoreCase(brokerInfo.getQmgrname())) {						
		    			   log.info("代理 " + brokerInfo.getBname() + " 监控程序已经停止。");
		    			   System.out.println("代理 " + brokerInfo.getBname() + "线程（"+Thread.currentThread().getId()+") 监控程序已经停止。");
		    			   break;
		    		   }
											
				}
		       }
			} catch (Exception e) {
			log.error("代理 " + brokerInfo.getBname() + " 监控程序出现异常。");
			log.error(e.getMessage()+"---error2");
		} finally {
			if (receiver != null) {
				try {
					receiver.close();
				} catch (Exception ex) {
					log.error("Receiver could not be closed.");
					log.error(ex.getMessage()+"---error3");
				}
			}

			if (session != null) {
				try {
					session.close();
				} catch (Exception ex) {
					log.error("Session could not be closed.");
					log.error(ex.getMessage()+"---error4");
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
					log.error("Connection could not be closed.");
					log.error(ex.getMessage()+"---error5");
				}
			}
//			brokerInfo.setStatus("0");
//			dao.update(brokerInfo);
		}
	} // end main()
} 
