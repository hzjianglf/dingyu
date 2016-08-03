package com.ibm.paser;

import java.io.EOFException;
import java.io.IOException;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

public class MQGetMonitorMsg {
	
	private String qMgrName = null;
	private String qGetName = null;
	private MQQueueManager qMgr;
	private MQQueue queueGet;
	
	public MQGetMonitorMsg() {
		this.qMgrName = MonitorUtils.readValue("wmq.properties", "QmgrName");
		this.qGetName = MonitorUtils.readValue("wmq.properties", "MonitorQueue");
		
		MQEnvironment.hostname = MonitorUtils.readValue("wmq.properties", "MQHostName");
		MQEnvironment.channel = MonitorUtils.readValue("wmq.properties", "SvrConnChannel");
		MQEnvironment.port = new Integer(MonitorUtils.readValue("wmq.properties", "MQPort")).intValue();
		MQEnvironment.CCSID = new Integer(MonitorUtils.readValue("wmq.properties", "MQCCSID")).intValue();
				
	}

	public void startMQ() throws MQException {		
			qMgr = new MQQueueManager(qMgrName);
			int openOptions = MQConstants.MQOO_INPUT_AS_Q_DEF | MQConstants.MQOO_FAIL_IF_QUIESCING;
			queueGet = qMgr.accessQueue(qGetName, openOptions);
	}

	public void stopMQ() throws MQException{
			queueGet.close();
			qMgr.disconnect();		
	}

	public String getMsg() throws MQException {
		String xmlInfo = null;

		MQGetMessageOptions gmo = new MQGetMessageOptions();
		gmo.options = gmo.options + MQConstants.MQGMO_FAIL_IF_QUIESCING;
		//gmo.options = gmo.options + MQConstants.MQGMO_ALL_MSGS_AVAILABLE;
		gmo.options = gmo.options + MQConstants.MQGMO_WAIT;
		gmo.waitInterval = -1;		
		MQMessage getMsg = new MQMessage();				
		
		queueGet.get(getMsg, gmo);	
		try {			
			xmlInfo = getMsg.readStringOfByteLength(getMsg.getDataLength());
			xmlInfo = new String(xmlInfo.getBytes(),"utf-8");
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}								
									
		return xmlInfo;
	}
	
	public static void main(String[] args) {
		MQGetMonitorMsg get = new MQGetMonitorMsg();
		try {
			get.startMQ();
			
			String xmlInfo = get.getMsg();
			System.out.println(xmlInfo);
			
			get.stopMQ();
		} catch (MQException e) {
			e.printStackTrace();
		}		
	}
}
