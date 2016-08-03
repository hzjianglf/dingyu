package com.ibm.paser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.ibm.hibernate.MonitorMain;
import com.ibm.hibernate.MonitorMainDAO;
import com.ibm.hibernate.MonitorMessageFlow;
import com.ibm.hibernate.MonitorMessageFlowDAO;
import com.ibm.hibernate.MonitorNodeItems;
import com.ibm.hibernate.MonitorNodeItemsDAO;

public class PaserMonitorMsg {
	
	private static final Logger log = LoggerFactory.getLogger(PaserMonitorMsg.class);
	
	MonitorMainDAO monitorMainDAO = new MonitorMainDAO();
	
	MonitorMessageFlowDAO mmFlowDao = new MonitorMessageFlowDAO();
	
	MonitorNodeItemsDAO mmItemDao = new MonitorNodeItemsDAO();

	
	
	MonitorMessageFlow mmFlow = new MonitorMessageFlow();
	
	MonitorNodeItems mmItem = new MonitorNodeItems();
	
	BrokerInfo brokerinfo = new BrokerInfo();
	
	
	public PaserMonitorMsg(BrokerInfo brokerinfo) {
		this.brokerinfo = brokerinfo;
	}

	public void paser(String xmlMsg) {
		MonitorMain monitorMain = new MonitorMain();

		String eventSourceAddress = null;
		String eventName = null;
		String creationTime = null;
		String counter = null;
		String localTransactionId = null;
		String brokerName = null;
		String executionGroupName = null;
		String messageFlowName = null;
		String nodeLabel = null;
		String nodeType = null;
		String terminal = null;
		String elementName = null;
		String xmlStr = null;
		String flowType = null;
		String serviceLabel = null;
		Document document = null;
		
		
		
		try {
			// document = saxReader.read(new File("2.xml"));
			document = DocumentHelper.parseText(xmlMsg);

			Element eventElt = document.getRootElement();

			/* eventPointData */
			Element eventPointDataElt = eventElt.element("eventPointData");
			Element eventDataElt = eventPointDataElt.element("eventData");
			// eventSourceAddress
			Attribute eventSourceAddressAttr = eventDataElt
					.attribute("eventSourceAddress");
			eventSourceAddress = eventSourceAddressAttr.getText();

			Element eventIdentityElt = eventDataElt.element("eventIdentity");
			// eventName
			Attribute eventNameAttr = eventIdentityElt.attribute("eventName");
			eventName = eventNameAttr.getText();

			Element eventSequenceElt = eventDataElt.element("eventSequence");
			// creationTime
			Attribute creationTimeAttr = eventSequenceElt
					.attribute("creationTime");
			creationTime = MonitorUtils.getLocalTime(creationTimeAttr.getText());
			// counter
			Attribute counterAttr = eventSequenceElt.attribute("counter");
			counter = counterAttr.getText();

			Element eventCorrelationElt = eventDataElt
					.element("eventCorrelation");
			// localTransactionId
			Attribute localTransactionIdAttr = eventCorrelationElt
					.attribute("localTransactionId");
			localTransactionId = localTransactionIdAttr.getText();

			Element messageFlowDataElt = eventPointDataElt
					.element("messageFlowData");
			Element brokerElt = messageFlowDataElt.element("broker");
			// brokerName
			Attribute brokerNameAttr = brokerElt.attribute("name");
			brokerName = brokerNameAttr.getText();

			Element executionGroupElt = messageFlowDataElt
					.element("executionGroup");
			// executionGroupName
			Attribute executionGroupNameAttr = executionGroupElt
					.attribute("name");
			executionGroupName = executionGroupNameAttr.getText();

			Element messageFlowElt = messageFlowDataElt.element("messageFlow");
			// messageFlowName
			Attribute messageFlowNameAttr = messageFlowElt.attribute("name");
			messageFlowName = messageFlowNameAttr.getText();

			Element nodeElt = messageFlowDataElt.element("node");
			// nodeLabel
			Attribute nodeLabelAttr = nodeElt.attribute("nodeLabel");
			nodeLabel = nodeLabelAttr.getText();
			// nodeType
			Attribute nodeTypeAttr = nodeElt.attribute("nodeType");
			nodeType = nodeTypeAttr.getText();
				
			// terminal
			Attribute terminalAttr = nodeElt.attribute("terminal");
			if (terminalAttr != null) {
				terminal = terminalAttr.getText();
			}

			/* applicationData */
			Element applicationDataElt = eventElt.element("applicationData");
			if (applicationDataElt != null) {
				Element simpleContentElt = applicationDataElt.element("simpleContent");
				if (simpleContentElt != null) {
					//Attribute simpleNameAttr = simpleContentElt.attribute("name");					
					Attribute simpleValueAttr = simpleContentElt.attribute("value");
					serviceLabel = simpleValueAttr.getText();
					
				}
				
				Element complexContentElt = applicationDataElt
						.element("complexContent");
				if (complexContentElt != null) {
					// complexContent
					Attribute elementNameAttr = complexContentElt
							.attribute("elementName");
					elementName = elementNameAttr.getText();
	
					Element xmlAttr = complexContentElt.element(elementName);
					xmlStr = xmlAttr.asXML();
				}
				
				
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}		
		
		if (nodeType.contains("WS") || nodeType.contains("SOAP")) {
			flowType = "WS";
		} else if (nodeType.contains("MQ")) {
			flowType = "MQ";
		} else if(nodeType.contains("JMS")) {
			flowType = "JMS";
		} else if(nodeType.contains("TCPIP")) {
			flowType = "Socket";
		} else if (nodeType.contains("File") || nodeType.contains("FTE")){
			flowType = "File";
		}
		
		//Insert Monitor_Main Table
		monitorMain.setLocaltransactionid(localTransactionId);
		monitorMain.setBrokername(brokerName);
		monitorMain.setExecgroupname(executionGroupName);
		monitorMain.setFlowname(messageFlowName);
		monitorMain.setFlowtype(flowType);

		if (eventSourceAddress.contains("transaction.Start")) {
			
			monitorMain.setStarttime(creationTime);	
			
			synchronized(brokerinfo){
			if(isMonitorMainItemEmpty(localTransactionId))
			{
				monitorMainDAO.save(monitorMain);
			}
			else
			{
				String etime = new String();
				if(!monitorMainDAO.findEndTime(localTransactionId).isEmpty())
				{
					etime = (String)monitorMainDAO.findEndTime(localTransactionId).get(0);			
				}
				monitorMain.setTotalflowtime(new Integer(MonitorUtils.getFlowProcessedTime(creationTime, etime).intValue()));
				monitorMain.setEndtime(etime);
				String returncode = (String)monitorMainDAO.findReturnCode(localTransactionId).get(0);
				monitorMain.setReturncode(returncode);
				monitorMainDAO.update(monitorMain);
			}
			}
			//Insert Monitor_Message_Flow Table
			mmFlow.setFlowname(messageFlowName);
			mmFlow.setEventsourceaddress(eventSourceAddress);
			mmFlow.setEventname(eventName);
			mmFlow.setCreationtime(creationTime);
			mmFlow.setCounter(new Integer(counter));
			mmFlow.setLocaltransactionid(localTransactionId);			
			mmFlow.setNodelabel(nodeLabel);
			mmFlow.setNodetype(nodeType);
			mmFlow.setAppdatatype("");
			mmFlow.setAppdatafilepath("");
			synchronized(brokerinfo){
			mmFlowDao.save(mmFlow);
			}
			
		} else if (eventSourceAddress.contains("terminal.failure") || eventSourceAddress.contains("terminal.catch") 
				|| eventSourceAddress.contains("terminal.error") || eventSourceAddress.contains("terminal.fault")) {
			//log.info("-------------------terminal.failure ----------------------");
			
			//Update Monitor_Main Table
			String stime = new String();
			//monitorMain.setLocaltransactionid(localTransactionId);
			monitorMain.setEndtime(creationTime);
			monitorMain.setReturncode("1");
			synchronized(brokerinfo){
			if(isMonitorMainItemEmpty(localTransactionId))
			{						
				monitorMainDAO.save(monitorMain);
			}
			else
			{
				if(monitorMainDAO.findStartTime(localTransactionId).get(0)!=null)
				{
					stime = (String)monitorMainDAO.findStartTime(localTransactionId).get(0);			
					monitorMain.setStarttime(stime);
					monitorMain.setTotalflowtime(new Integer(MonitorUtils.getFlowProcessedTime(stime, creationTime).intValue()));
				}
				monitorMainDAO.update(monitorMain);
			}
			}
			//Insert Monitor_Message_Flow Table
			mmFlow.setFlowname(messageFlowName);
			mmFlow.setEventsourceaddress(eventSourceAddress);
			mmFlow.setEventname(eventName);
			mmFlow.setCreationtime(creationTime);
			mmFlow.setCounter(new Integer(counter));
			mmFlow.setLocaltransactionid(localTransactionId);	
			mmFlow.setNodelabel(nodeLabel);
			mmFlow.setNodetype(nodeType);
			mmFlow.setTerminal(terminal);
			if (elementName != null) {
				Calendar day = Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");			
				String dateStr = sdf.format(day.getTime());
				
				String datapath = System.getProperty("WMB_MONITOR_DATA") + "/" + dateStr + "/";	
				String context = System.getProperty("WMB_MONITOR_CONTEXTPATH");
				File dFile = new File(datapath);
				if (!dFile.exists()) {
					dFile.mkdirs();
				}
				
				mmFlow.setAppdatatype(elementName);
				String filename = localTransactionId + "-" + counter + ".xml";				
				writeXml(xmlStr, datapath + filename);
				mmFlow.setAppdatafilepath(context + "/data/" + dateStr + "/" + filename);
				
			} else {
				mmFlow.setAppdatatype("");
				mmFlow.setAppdatafilepath("");
			}
			synchronized(brokerinfo){
			mmFlowDao.save(mmFlow);		
			}
			if (nodeType.contains("SOAPRequest") || nodeType.contains("WSRequest")) {
				stime = (String)mmItemDao.findStartTime(localTransactionId + nodeLabel).get(0);
				mmItem.setLocaltransactionid(localTransactionId + nodeLabel);
				mmItem.setServicelabel(nodeLabel);
				mmItem.setServicetype("WebService");
				mmItem.setEndtime(creationTime);
				mmItem.setTotalnodetime(new Integer(MonitorUtils.getFlowProcessedTime(stime, creationTime).intValue()));
				mmItem.setReturncode("1");
				synchronized(brokerinfo){
				mmItemDao.update(mmItem);
				}
			}
			
		} else if (eventSourceAddress.contains("transaction.End")) {			
			//Update Monitor_Main Table			
			String stime = new String();
			
			//monitorMain.setLocaltransactionid(localTransactionId);
			monitorMain.setEndtime(creationTime);
			synchronized(brokerinfo){
			if(isMonitorMainItemEmpty(localTransactionId))
			{						
				monitorMainDAO.save(monitorMain);
			}
			else
			{
				if(monitorMainDAO.findStartTime(localTransactionId).get(0)!=null)
				{
					stime = (String)monitorMainDAO.findStartTime(localTransactionId).get(0);			
					monitorMain.setStarttime(stime);
					monitorMain.setTotalflowtime(new Integer(MonitorUtils.getFlowProcessedTime(stime, creationTime).intValue()));	
				}
				String returncode = (String)monitorMainDAO.findReturnCode(localTransactionId).get(0);
				monitorMain.setReturncode(returncode);
				monitorMainDAO.update(monitorMain);
			}
			}
			//Insert Monitor_Message_Flow Table
			mmFlow.setFlowname(messageFlowName);
			mmFlow.setEventsourceaddress(eventSourceAddress);
			mmFlow.setEventname(eventName);
			mmFlow.setCreationtime(creationTime);
			mmFlow.setCounter(new Integer(counter));
			mmFlow.setLocaltransactionid(localTransactionId);	
			mmFlow.setNodelabel(nodeLabel);
			mmFlow.setNodetype(nodeType);
			mmFlow.setTerminal(terminal);
			mmFlow.setAppdatatype("");
			mmFlow.setAppdatafilepath("");
			synchronized(brokerinfo){
			mmFlowDao.save(mmFlow);			
			}
		} else {
			//log.info("-------------------Other Nodes----------------------");
			String stime = new String();
			synchronized(brokerinfo){
				if(isMonitorMainItemEmpty(localTransactionId))
				{
					if(creationTime != null)
					{
						monitorMain.setStarttime(creationTime);
					}
					monitorMainDAO.save(monitorMain);
				}
				else
				{
					if(monitorMainDAO.findStartTime(localTransactionId).get(0)!=null)
					{
						stime = (String)monitorMainDAO.findStartTime(localTransactionId).get(0);			
						monitorMain.setStarttime(stime);
						monitorMain.setTotalflowtime(new Integer(MonitorUtils.getFlowProcessedTime(stime, creationTime).intValue()));	
					}
					String returncode = (String)monitorMainDAO.findReturnCode(localTransactionId).get(0);
					monitorMain.setReturncode(returncode);
					monitorMainDAO.update(monitorMain);
				}
			}
			//Insert Monitor_Message_Flow Table
			mmFlow.setFlowname(messageFlowName);
			mmFlow.setEventsourceaddress(eventSourceAddress);
			mmFlow.setEventname(eventName);
			mmFlow.setCreationtime(creationTime);
			mmFlow.setCounter(new Integer(counter));
			mmFlow.setLocaltransactionid(localTransactionId);	
			mmFlow.setNodelabel(nodeLabel);
			mmFlow.setNodetype(nodeType);
			mmFlow.setTerminal(terminal);
			if (elementName != null) {
				Calendar day = Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");			
				String dateStr = sdf.format(day.getTime());
																		
				String datapath = System.getProperty("WMB_MONITOR_DATA") + "/" + dateStr + "/";	
				String context = System.getProperty("WMB_MONITOR_CONTEXTPATH");
				File dFile = new File(datapath);
				if (!dFile.exists()) {
					dFile.mkdirs();
				}
				
				mmFlow.setAppdatatype(elementName);
				String filename = localTransactionId + "-" + counter + ".xml";				
				writeXml(xmlStr, datapath + filename);
				mmFlow.setAppdatafilepath(context + "/data/" + dateStr + "/" + filename);

			} else {
				mmFlow.setAppdatatype("");
				mmFlow.setAppdatafilepath("");
			}
			synchronized(brokerinfo){
			mmFlowDao.save(mmFlow);
			}
			if (nodeType.contains("SOAPRequest") || nodeType.contains("WSRequest")) {
				if (terminal.equalsIgnoreCase("in")) {
					mmItem.setLocaltransactionid(localTransactionId + nodeLabel);
					mmItem.setBrokername(brokerName);
					mmItem.setExecgroupname(executionGroupName);
					mmItem.setFlowname(messageFlowName);
					mmItem.setServicelabel(nodeLabel);
					mmItem.setServicetype("WebService");
					mmItem.setStarttime(creationTime);
					synchronized(brokerinfo){
					mmItemDao.save(mmItem);
					}
				} else {
					stime = (String)mmItemDao.findStartTime(localTransactionId + nodeLabel).get(0);
					mmItem.setLocaltransactionid(localTransactionId + nodeLabel);
					mmItem.setServicelabel(nodeLabel);
					mmItem.setServicetype("WebService");
					mmItem.setEndtime(creationTime);
					mmItem.setTotalnodetime(new Integer(MonitorUtils.getFlowProcessedTime(stime, creationTime).intValue()));
					mmItem.setReturncode("0");
					synchronized(brokerinfo){
					mmItemDao.update(mmItem);
					}
				}
				
			}
		}
	}
	
	public PaserMonitorMsg() {
		super();
	}

	public void writeXml(String xmlData, String filename) {
		FileOutputStream fos;
		BufferedWriter fw;
		
		try {
			fos = new FileOutputStream(new File(filename));
			
			fw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
			
			fw.write(xmlData);
			fw.flush();
			fw.close();
			fos.close(); 
		} catch (FileNotFoundException e1) {			
			log.error(e1.getMessage());
		} catch (UnsupportedEncodingException e2) {
			log.error(e2.getMessage());
		} catch (IOException e3) {
			log.error(e3.getMessage());
		} 		
	}
	
	public String readXml(String xmlPath) {

		// InputStream in=
		// this.getClass().getClassLoader().getResourceAsStream(xmlPath);
		FileInputStream fis = null;
		StringBuffer sb = null;

		// 统一用UTF-8的编码格式
		BufferedReader br;
		try {
			fis = new FileInputStream(xmlPath);

			br = new BufferedReader(new InputStreamReader(fis, "utf-8"));

			String line = null;
			sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}

		return sb.toString();
	}
	
	public Boolean isMonitorMainItemEmpty(String localTransactionId)
	{
		return monitorMainDAO.findByProperty("localtransactionid",localTransactionId).isEmpty();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MonitorMessageFlow mmFlow = new MonitorMessageFlow();
		MonitorMessageFlowDAO mmFlowDao = new MonitorMessageFlowDAO();
		
		mmFlow.setFlowname("ddd");
		mmFlow.setEventsourceaddress("dd");
		mmFlow.setEventname("dd");
		mmFlow.setCreationtime("dd");
		mmFlow.setCounter(new Integer(111));
		mmFlow.setLocaltransactionid("111111111111111");
		mmFlow.setNodelabel("dd");
		mmFlow.setNodetype("dd");
		mmFlow.setAppdatatype("dd");
		mmFlow.setAppdatafilepath("ddd");
		mmFlowDao.save(mmFlow);			
	}

}
