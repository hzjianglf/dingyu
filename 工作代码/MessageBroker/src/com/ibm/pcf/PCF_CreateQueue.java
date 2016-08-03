package com.ibm.pcf;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;

public class PCF_CreateQueue {
  public static String queueManager;
  public static String queueName;
  public static void main(String[] args) {
//	if (args.length !=2){
//		System.out.println("Usage: PCF_CreateQueue QManager QName");
//	}
//    queueManager = args[0];
//    queueName = args[1];
	  
	
    String queueName = "TEST1";
    String host = "localhost";
    int port = 2414;
    String channel = "CHANNEL1";
    try {
    	  //PCFMessageAgent agent = new PCFMessageAgent(queueManager);
    	  PCFMessageAgent agent = new PCFMessageAgent(host, port, channel);
    	  
    	  PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_CREATE_Q);
    	  pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);
    	  pcfCmd.addParameter(MQConstants.MQIA_Q_TYPE, MQConstants.MQQT_LOCAL);
    	  pcfCmd.addParameter(MQConstants.MQIA_MAX_Q_DEPTH, 10);
    	  agent.send(pcfCmd);
    	  System.out.println("Create the queue ok!");
    	  agent.disconnect();
    }
    catch (Exception e) {
    	System.out.println(e);
    }
    return;
  }  
  
  public void deleteQueue(String queueName, String host, int port, String channel) throws Exception
  {
	  //PCFMessageAgent agent = new PCFMessageAgent(queueManager);
	  PCFMessageAgent agent = new PCFMessageAgent(host, port, channel);
	  
	    // Create the PCF message type for the delete queue.
	    PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_DELETE_Q);

	    // Queue name - This must be the first parameter!
	    pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);

	    // Execute the command. The returned object is an array of PCF messages.
	    /* PCFMessage[] pcfResponse = */// We ignore the returned result
	    agent.send(pcfCmd);	  
  }
}
