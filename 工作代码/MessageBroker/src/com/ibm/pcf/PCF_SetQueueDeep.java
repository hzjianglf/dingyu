package com.ibm.pcf;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;

public class PCF_SetQueueDeep {
  public static String queueManager;
  public static String queueName;
  public static void main(String[] args) {
//	if (args.length !=3){
//		System.out.println("Usage: PCF_SetQueueDeep QManager QName Number");
//	}
//    queueManager = args[0];
//    queueName = args[1];
//    Number = new Integer(args[2]).intValue();
	  
	    String queueName = "TEST1";
	    String host = "localhost";
	    int port = 2414;
	    String channel = "CHANNEL1";
	    Integer number = 10;
	    
    try {
    	  //PCFMessageAgent agent = new PCFMessageAgent(queueManager);
    	  PCFMessageAgent agent = new PCFMessageAgent(host, port, channel);
    	  PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_CHANGE_Q);
    	  pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);
    	  pcfCmd.addParameter(MQConstants.MQIA_Q_TYPE, MQConstants.MQQT_LOCAL);
    	  pcfCmd.addParameter(MQConstants.MQIA_MAX_Q_DEPTH, number);
    	  agent.send(pcfCmd);
    	  System.out.println("Change the queue ok!");
    	  agent.disconnect();
    }
    catch (Exception e) {
    	System.out.println(e);
    }
    return;
  }  
}
