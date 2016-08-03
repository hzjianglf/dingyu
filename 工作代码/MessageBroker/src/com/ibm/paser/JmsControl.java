package com.ibm.paser;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.BrokerInfo;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class JmsControl {

	private static final Logger log = LoggerFactory.getLogger(JmsControl.class);
	
	private static String toAgentTopic = "topic://Broker/Monitoring/CmdToAgent";
	
	private static String fromAgentTopic = "topic://Broker/Monitoring/CmdFromAgent";
	
	BrokerInfo brokerInfo = new BrokerInfo();
	
	private String cmdMsg;
	
	private String flag;
	
	public JmsControl(BrokerInfo brokerInfo, String cmdMsg, String flag) {
		this.brokerInfo = brokerInfo;
		this.cmdMsg = cmdMsg;
		this.flag = flag;
	}
	
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public String run() {
		// Parse the arguments
		//parseArgs(args);

		// Variables
		Connection connection = null;
		Session session = null;
		Destination toAgentDest = null;
		Destination fromAgentDest = null;
		MessageConsumer consumer = null;
		MessageProducer producer = null;
		String result = null;
		
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
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			toAgentDest = session.createTopic(toAgentTopic);
			producer = session.createProducer(toAgentDest);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			fromAgentDest = session.createTopic(fromAgentTopic);
			consumer = session.createConsumer(fromAgentDest);
			
			BytesMessage sdrMsg = session.createBytesMessage();
			sdrMsg.writeBytes(cmdMsg.getBytes("utf-8"));
			
			// Start the connection
			connection.start();
			producer.send(sdrMsg);						
			if (flag.equalsIgnoreCase("1")) {
				log.info("BIP0000I: 启动代理 " + brokerInfo.getBname() + " 命令发布成功。");
			} else if (flag.equalsIgnoreCase("2")) {
				log.info("BIP0000I: 停止代理 " + brokerInfo.getBname() + " 命令发布成功。");
			}
			
			BytesMessage rcvMsg = (BytesMessage)consumer.receive();
			if ( rcvMsg != null) {
				byte[] body = new byte[new Long(rcvMsg.getBodyLength()).intValue()];
				rcvMsg.readBytes(body);
				result = new String(body, "utf-8");															
			}		
			
		} catch (Exception jmsex) {
			jmsex.printStackTrace();
			recordFailure(jmsex);
		} finally {
			if (consumer != null) {
				try {
					consumer.close();
				} catch (JMSException jmsex) {
					System.out.println("Consumer could not be closed.");
					recordFailure(jmsex);
				}
			}

			if (producer != null) {
				try {
					producer.close();
				} catch (JMSException jmsex) {
					System.out.println("Consumer could not be closed.");
					recordFailure(jmsex);
				}
			}
			
			if (session != null) {
				try {
					session.close();
				} catch (JMSException jmsex) {
					System.out.println("Session could not be closed.");
					recordFailure(jmsex);
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException jmsex) {
					System.out.println("Connection could not be closed.");
					recordFailure(jmsex);
				}
			}
		}
		
		return result;
	} // end main()

	/**
	 * Process a JMSException and any associated inner exceptions.
	 * 
	 * @param jmsex
	 */
	private static void processJMSException(JMSException jmsex) {
		System.out.println(jmsex);
		Throwable innerException = jmsex.getLinkedException();
		if (innerException != null) {
			System.out.println("Inner exception(s):");
		}
		while (innerException != null) {
			System.out.println(innerException);
			innerException = innerException.getCause();
		}
		return;
	}


	/**
	 * Record this run as failure.
	 * 
	 * @param ex
	 */
	private static void recordFailure(Exception ex) {
		if (ex != null) {
			if (ex instanceof JMSException) {
				processJMSException((JMSException) ex);
			} else {
				System.out.println(ex);
			}
		}
		System.out.println("FAILURE");
		
		return;
	}
	
	public static void main(String[] args) {
		
		//System.out.println("end");
	}
} // end class
