package com.ibm.paser;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class JmsConsumer_ok implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(JmsConsumer_ok.class);
	private static String destinationName = "topic://$SYS/Broker/+/Monitoring/#";
	BrokerInfo brokerInfo = new BrokerInfo();
	BrokerInfoDAO dao = new BrokerInfoDAO();
	
	public JmsConsumer_ok(BrokerInfo brokerInfo, BrokerInfoDAO dao) {
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

		// Variables
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageConsumer consumer = null;

		try {
			// Create a connection factory
			JmsFactoryFactory ff = JmsFactoryFactory
					.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();

			// Set the properties
			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, brokerInfo.getHostname());
			cf.setIntProperty(WMQConstants.WMQ_PORT, new Integer(brokerInfo.getPort()).intValue());
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, brokerInfo.getSvrconn());
			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE,
					WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER,
					brokerInfo.getQmgrname());

			// Create JMS objects
			connection = cf.createConnection();
			connection.setClientID("brokermonitor");
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic(destinationName);
			
			TopicSubscriber subscriber = session.createDurableSubscriber((Topic)destination, "sub-broker-monitor");
			//***************************************************/			
			consumer = session.createConsumer(destination);
			/**************************************************/
			
			brokerInfo.setStatus("1");
			dao.update(brokerInfo);						
			PaserMonitorMsg pmm = new PaserMonitorMsg();
			log.info("代理 " + brokerInfo.getBname() + " 监控程序启动成功。");
			
			// And, receive the message
			while(true) {
				/***************************************************/
				//BytesMessage message = (BytesMessage)consumer.receive();
				/***************************************************/
				
				BytesMessage message = (BytesMessage)subscriber.receive();
				if ( message != null) {
					if (message.getStringProperty("Close") == null) {
						try {
							byte[] body = new byte[new Long(message.getBodyLength()).intValue()];
							message.readBytes(body);
							pmm.paser(new String(body, "UTF-8"));						
							
						} catch (Exception e) {
							log.error(e.getMessage());
							continue;
						}
					} else if (message.getStringProperty("Close").equalsIgnoreCase(brokerInfo.getQmgrname())) {						
						log.info("代理 " + brokerInfo.getBname() + " 监控程序已经停止。");
						break;
					}
											
				}
			}	
			
		} catch (Exception jmsex) {
			log.error("代理 " + brokerInfo.getBname() + " 监控程序出现异常。");
			log.error(jmsex.getMessage());
		} finally {
			if (consumer != null) {
				try {
					consumer.close();
				} catch (JMSException jmsex) {
					log.error("Consumer could not be closed.");
					log.error(jmsex.getMessage());
				}
			}

			if (session != null) {
				try {
					session.close();
				} catch (JMSException jmsex) {
					log.error("Session could not be closed.");
					log.error(jmsex.getMessage());
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException jmsex) {
					log.error("Connection could not be closed.");
					log.error(jmsex.getMessage());
				}
			}
			
			brokerInfo.setStatus("0");
			dao.update(brokerInfo);
		}
	} // end main()

	/**
	 * Process a JMSException and any associated inner exceptions.
	 * 
	 * @param jmsex
	 */
//	private static void processJMSException(JMSException jmsex) {
//		System.out.println(jmsex);
//		Throwable innerException = jmsex.getLinkedException();
//		if (innerException != null) {
//			System.out.println("Inner exception(s):");
//		}
//		while (innerException != null) {
//			System.out.println(innerException);
//			innerException = innerException.getCause();
//		}
//		return;
//	}


	/**
	 * Record this run as failure.
	 * 
	 * @param ex
	 */
//	private static void recordFailure(Exception ex) {
//		if (ex != null) {
//			if (ex instanceof JMSException) {
//				processJMSException((JMSException) ex);
//			} else {
//				System.out.println(ex);
//			}
//		}
//		System.out.println("FAILURE");
//		
//		return;
//	}

} 
