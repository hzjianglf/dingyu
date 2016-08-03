package com.ibm.pcf;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;
import com.ibm.mq.headers.pcf.PCFException;
import com.ibm.msg.client.wmq.v6.base.internal.MQC;

public class PCF_QueueHelper {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	    String queueName = "TEST1";
	    String host = "localhost";
	    int port = 2414;
	    String channel = "CHANNEL1";
	    int deep = 10;
	    
//	    PCF_QueueHelper.createQueue(queueName, host, port, channel, deep);
//	    PCF_QueueHelper.clearQueue(queueName, host, port, channel);
	    PCF_QueueHelper.deleteQueue(queueName, host, port, channel);

	}

	public static void createQueue(String queueName, String host, int port,
			String channel, int deep) throws Exception {
		
		PCFMessageAgent agent = new PCFMessageAgent(host, port, channel);

		PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_CREATE_Q);
		pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);
		pcfCmd.addParameter(MQConstants.MQIA_Q_TYPE, MQConstants.MQQT_LOCAL);
		pcfCmd.addParameter(MQConstants.MQIA_MAX_Q_DEPTH, deep);
		
		
	    try {
	        // Execute the command. The returned object is an array of PCF messages.
	        // If the Queue already exists, then catch the exception, otherwise rethrow.
	        /* PCFMessage[] pcfResponse = */// We ignore the returned result
	    	agent.send(pcfCmd);
	      }
	      catch (PCFException pcfe) {
	        if (pcfe.reasonCode == MQConstants.MQRCCF_OBJECT_ALREADY_EXISTS) {
	          System.out.println("The queue \"" + queueName
	              + "\" already exists on the queue manager.");
	        }
	        else {
	          throw pcfe;
	        }
	      }
		
		
		
		
		System.out.println("Create the queue ok!");
		agent.disconnect();
	}

	public static void updateQueue(String queueName, String host, int port,
			String channel, int deep) throws Exception {
		
		PCFMessageAgent agent = new PCFMessageAgent(host, port, channel);

		PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_CHANGE_Q);
		pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);
		pcfCmd.addParameter(MQConstants.MQIA_Q_TYPE, MQConstants.MQQT_LOCAL);
		pcfCmd.addParameter(MQConstants.MQIA_MAX_Q_DEPTH, deep);
		
		
	    try {
	        // Execute the command. The returned object is an array of PCF messages.
	        // If the Queue already exists, then catch the exception, otherwise rethrow.
	        /* PCFMessage[] pcfResponse = */// We ignore the returned result
	    	agent.send(pcfCmd);
	      }
	      catch (PCFException pcfe) {
	        if (pcfe.reasonCode == MQConstants.MQRCCF_MQSET_FAILED) {
	          System.out.println("The queue \"" + queueName
	              + "\" 修改队列深度失败！");
	        }
	        else {
	          throw pcfe;
	        }
	      }
		
		
		
		
		System.out.println("Update the queue : " + queueName + "  ok!");
		agent.disconnect();
	}
	
	public static void deleteQueue(String queueName, String host, int port,
			String channel) throws Exception {
		PCFMessageAgent agent = new PCFMessageAgent(host, port, channel);
		PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_DELETE_Q);
		pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);
		agent.send(pcfCmd);
		System.out.println("Delete the queue ok!");
		agent.disconnect();
	}

	public static void clearQueue(String qmgrname, String queueName, String host, int port, String channel, int threshold) throws Exception {
		//MQEnvironment.CCSID = 819;
		MQEnvironment.hostname = host; 
        MQEnvironment.port= port;                            
        MQEnvironment.channel  = channel; 
		MQQueueManager qmgr = new MQQueueManager(qmgrname);
		MQQueue rq = qmgr.accessQueue(queueName,MQC.MQOO_INPUT_AS_Q_DEF|MQC.MQOO_OUTPUT);
		
		MQGetMessageOptions gmo = new MQGetMessageOptions();
		gmo.options = MQC.MQGMO_NO_WAIT;
		try{
			for(int i=0; i<threshold;i++) {
				MQMessage theRspMessage    = new MQMessage();
				rq.get(theRspMessage,gmo);
			}
			rq.close();
			//Disconnect from the queue manager
			qmgr.disconnect();	
		}catch (Exception e) {
			rq.close();
			qmgr.disconnect();
		}
			  
	  //PCFMessageAgent agent = new PCFMessageAgent(host, port, channel);
	  //PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_CLEAR_Q);
	  //pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);
	  //agent.send(pcfCmd);
	  //System.out.println("Clear the queue ok!");
	  //agent.disconnect();	
	}
}
