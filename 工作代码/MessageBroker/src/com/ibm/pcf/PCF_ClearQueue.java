package com.ibm.pcf;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;


public class PCF_ClearQueue {
  public static String queueManager;
  public static String queueName;
  public static void main(String[] args) {
	if (args.length !=2){
		System.out.println("Usage: PCF_ClearQueue QManager QName");
	}
    queueManager = args[0];
    queueName = args[1];
    
    try {
    	  PCFMessageAgent agent = new PCFMessageAgent(queueManager);
    	  PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_CLEAR_Q);
    	  pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);
    	  agent.send(pcfCmd);
    	  System.out.println("Clear the queue ok!");
    	  agent.disconnect();
    }
    catch (Exception e) {
    	System.out.println(e);
    }
    return;
  }  
}
