package com.ibm.sigar;

import java.util.ArrayList;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.BrokerInfo;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SubInfoFromBrokerTop {
	
	private static final Logger log = LoggerFactory.getLogger(SubInfoFromBrokerTop.class);
	
	private String host = "localhost";
	private int port = 1414;
	private String channel = "SYSTEM.DEF.SVRCONN";
	private String queueManagerName = "CTQM";
	private String brokerDest = "topic://Broker/Monitoring/Processes";	
	private String systemDest = "topic://Broker/Monitoring/System";
	private String brokername;
	
	public SubInfoFromBrokerTop(BrokerInfo broker) {
		this.queueManagerName = broker.getQmgrname();
		this.host = broker.getHostname();
		this.port = new Integer(broker.getPort()).intValue();	
		this.brokername = broker.getBname();
	}
	
	/**
	 * Main method
	 * 
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public List<BrokerProcInfo> receiveBroker() {
		// Parse the arguments
		//parseArgs(args);

		// Variables
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageConsumer consumer = null;
		
		String xmlRecord = null;
		List<BrokerProcInfo> list = new ArrayList<BrokerProcInfo>();
		try {
			// Create a connection factory
			JmsFactoryFactory ff = JmsFactoryFactory
					.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();

			// Set the properties
			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, host);
			cf.setIntProperty(WMQConstants.WMQ_PORT, port);
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, channel);
			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE,
					WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER,
					queueManagerName);

			// Create JMS objects
			connection = cf.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic(brokerDest);
			consumer = session.createConsumer(destination);
			// Start the connection
			connection.start();
			log.info("代理 " + brokername + " 监控信息订阅成功。");
			
			// And, receive the message		
			BytesMessage message = (BytesMessage)consumer.receive(3*1000);
			if ( message != null) {			
				byte[] body = new byte[new Long(message.getBodyLength()).intValue()];
				message.readBytes(body);
				xmlRecord = new String(body);												
			}
			
            XStream xstream = new XStream(new DomDriver()); 
            xstream.alias("BrokerProc", List.class); 
            xstream.alias("item", BrokerProcInfo.class);
            list = (List<BrokerProcInfo>)xstream.fromXML(xmlRecord);
            
		} catch (Exception jmsex) {
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
		
		return list;
	} // end main()

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public SystemCPUInfo receiveSystem() {
		
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageConsumer consumer = null;
		
		String xmlRecord = null;
		SystemCPUInfo sysInfo = new SystemCPUInfo();
		try {
			// Create a connection factory
			JmsFactoryFactory ff = JmsFactoryFactory
					.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();

			// Set the properties
			cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, host);
			cf.setIntProperty(WMQConstants.WMQ_PORT, port);
			cf.setStringProperty(WMQConstants.WMQ_CHANNEL, channel);
			cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE,
					WMQConstants.WMQ_CM_CLIENT);
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER,
					queueManagerName);

			// Create JMS objects
			connection = cf.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic(systemDest);
			consumer = session.createConsumer(destination);
			// Start the connection
			connection.start();
			log.info("系统(" + host + ")监控信息订阅成功。");
			
			// And, receive the message		
			BytesMessage message = (BytesMessage)consumer.receive(3*1000);
			if ( message != null) {			
				byte[] body = new byte[new Long(message.getBodyLength()).intValue()];
				message.readBytes(body);
				xmlRecord = new String(body);												
			}
			
            XStream xstream = new XStream(new DomDriver()); 
            xstream.alias("CPU", SystemCPUInfo.class);
    		xstream.alias("mem", SystemMemInfo.class);
    		sysInfo = (SystemCPUInfo)xstream.fromXML(xmlRecord);
            
		} catch (Exception jmsex) {
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
		}
		
		return sysInfo;
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
//		Thread run = new Thread(new JmsConsumer());
//		run.start();
		
		//System.out.println("end");
	}
} // end class
