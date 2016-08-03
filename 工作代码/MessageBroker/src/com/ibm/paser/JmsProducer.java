package com.ibm.paser;

import java.io.UnsupportedEncodingException;

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

public class JmsProducer implements Runnable {

	private static String stopMonitorTopic = "topic://$SYS/Broker/+/Monitoring/#";  //flag 1
	
	BrokerInfo brokerInfo = new BrokerInfo();

	public JmsProducer(BrokerInfo brokerInfo) {
		this.brokerInfo = brokerInfo;
	}
	
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public void run() {
		// Parse the arguments
		// parseArgs(args);

		// Variables
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageProducer producer = null;
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
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
							
			destination = session.createTopic(stopMonitorTopic);			
			producer = session.createProducer(destination);
			BytesMessage message = session.createBytesMessage();			
			message.setStringProperty("Close", brokerInfo.getQmgrname());
			message.writeBytes("".getBytes("utf-8"));

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
