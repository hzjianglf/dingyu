package com.ibm.alert;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.ibm.hibernate.BrokerInfo;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class AlertMessageProducer implements Runnable {

	private String alertTopic = null;
	private String filePath = null;
	private String context = null;
	
	public AlertMessageProducer(String filePath, String topic, String context) {
		this.alertTopic = topic;
		this.filePath = filePath;
		this.context = context;
	}
	
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public void run() {
		String qmgrname = readValue(filePath, "qmgrname");
		String connectionmode = readValue(filePath, "connectionmode");
		
		String mqhostname = null;
		String mqport = null;
		String mqchannel = null;
		
		if (connectionmode.equalsIgnoreCase("2")) {
			mqhostname = readValue(filePath, "mqhostname");
			mqport = readValue(filePath, "mqport");
			mqchannel = readValue(filePath, "mqchannel");
		}	
		
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageProducer producer = null;
		MessageConsumer consumer = null;
		
		try {
			// Create a connection factory
			JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
			JmsConnectionFactory cf = ff.createConnectionFactory();
						
			// Set the properties
			cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, qmgrname);
			if (connectionmode.equalsIgnoreCase("2")) {
				cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, mqhostname);
				cf.setIntProperty(WMQConstants.WMQ_PORT, new Integer(mqport).intValue());
				cf.setStringProperty(WMQConstants.WMQ_CHANNEL, mqchannel);
				cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE,
						WMQConstants.WMQ_CM_CLIENT);
			} else {
				cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE,
						WMQConstants.WMQ_CM_BINDINGS);
			}
			

			// Create JMS objects
			connection = cf.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
							
			destination = session.createTopic(alertTopic);			
			producer = session.createProducer(destination);
			BytesMessage message = session.createBytesMessage();			
			message.writeBytes(context.getBytes("utf-8"));

			// Start the connection
			connection.start();		
			producer.send(message);				 

		} catch (JMSException jmsex) {
			recordFailure(jmsex);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (producer != null) {
				try {
					producer.close();
				} catch (JMSException jmsex) {
					System.out.println("Producer could not be closed.");
					recordFailure(jmsex);
				}
			}
			
			if (consumer != null) {
				try {
					consumer.close();
				} catch (JMSException jmsex) {
					System.out.println("Producer could not be closed.");
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

		return;
	} // end main()

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

} // end class
