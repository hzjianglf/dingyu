package com.ibm.struts;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.broker.config.proxy.BrokerConnectionParameters;
import com.ibm.broker.config.proxy.BrokerProxy;
import com.ibm.broker.config.proxy.ConfigManagerProxyException;
import com.ibm.broker.config.proxy.ConfigManagerProxyLoggedException;
import com.ibm.broker.config.proxy.ConfigManagerProxyPropertyNotInitializedException;
import com.ibm.broker.config.proxy.ExecutionGroupProxy;
import com.ibm.broker.config.proxy.MQBrokerConnectionParameters;
import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class RuntimeParametersSetting extends ActionSupport {

	private static final Logger log = LoggerFactory.getLogger(RuntimeParametersSetting.class);

	private static final long serialVersionUID = 1L;
	private int MAX_WAIT_TIME = 120;
	private String qmgrname;
	private String brokername;
	private String egname;
	private String getFilePath;
	private String selectedQmgr;
	private String selectedExecutionGroup;
	private String comment1;	
	private String title;	
	private String viewdescription;	
	private String operation;		
	private int paramNum = 0;	
	private String brokerRuntimeParam;	
	private String egRuntimeParam;	
	private String selectedItemIds;	
	private String changeValues;
	
	Map<String, String> qmgrMap = new LinkedHashMap<String, String>();
	Map<String, String> egMap = new LinkedHashMap<String, String>();
	Map<String, String> httpPropertyMap = new LinkedHashMap<String, String>();
	BrokerInfoDAO dao = new BrokerInfoDAO();
	
	public String getEgname() {
		return egname;
	}

	public void setEgname(String egname) {
		this.egname = egname;
	}
	
	public String getChangeValues() {
		return changeValues;
	}

	public void setChangeValues(String changeValues) {
		this.changeValues = changeValues;
	}

	public String getSelectedItemIds() {
		return selectedItemIds;
	}

	public void setSelectedItemIds(String selectedItemIds) {
		this.selectedItemIds = selectedItemIds;
	}

	public String getEgRuntimeParam() {
		return egRuntimeParam;
	}

	public void setEgRuntimeParam(String egRuntimeParam) {
		this.egRuntimeParam = egRuntimeParam;
	}

	public String getBrokerRuntimeParam() {
		return brokerRuntimeParam;
	}

	public void setBrokerRuntimeParam(String brokerRuntimeParam) {
		this.brokerRuntimeParam = brokerRuntimeParam;
	}

	public int getParamNum() {
		return paramNum;
	}

	public void setParamNum(int paramNum) {
		this.paramNum = paramNum;
	}
	
	public Map<String, String> getHttpPropertyMap() {
		return httpPropertyMap;
	}

	public void setHttpPropertyMap(Map<String, String> httpPropertyMap) {
		this.httpPropertyMap = httpPropertyMap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getViewdescription() {
		return viewdescription;
	}

	public void setViewdescription(String viewdescription) {
		this.viewdescription = viewdescription;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getGetFilePath() {
		return getFilePath;
	}

	public void setGetFilePath(String getFilePath) {
		this.getFilePath = getFilePath;
	}

	public String getSelectedExecutionGroup() {
		return selectedExecutionGroup;
	}

	public void setSelectedExecutionGroup(String selectedExecutionGroup) {
		this.selectedExecutionGroup = selectedExecutionGroup;
	}

	public String getSelectedQmgr() {
		return selectedQmgr;
	}

	public void setSelectedQmgr(String selectedQmgr) {
		this.selectedQmgr = selectedQmgr;
	}

	public String getBrokername() {
		return brokername;
	}

	public void setBrokername(String brokername) {
		this.brokername = brokername;
	}

	public Map<String, String> getEgMap() {
		return egMap;
	}

	public void setEgMap(Map<String, String> egMap) {
		this.egMap = egMap;
	}

	public String getQmgrname() {
		return qmgrname;
	}

	public void setQmgrname(String qmgrname) {
		this.qmgrname = qmgrname;
	}

	public Map<String, String> getQmgrMap() {
		return qmgrMap;
	}

	public void setQmgrMap(Map<String, String> qmgrMap) {
		this.qmgrMap = qmgrMap;
	}

	public String queryAllQmgr() {		
		List<BrokerInfo> list = dao.findAll();

		for (Iterator<BrokerInfo> i = list.iterator(); i.hasNext();) {
			BrokerInfo brokerInfo = (BrokerInfo) i.next();
			qmgrMap.put(brokerInfo.getQmgrname(), brokerInfo.getQmgrname());
		}
		qmgrname = "";
		brokername = "";
		
		return SUCCESS;
	}
	
	/**
	 * @return
	 */
	public String queryBrokerByQmgr() {
		title = "选择代理和执行组";
		viewdescription = "在该页面用户可以查看操作信息。";
		operation = "selectforruntimebroker";
		String flag = SUCCESS;;
					
		if (!qmgrname.equalsIgnoreCase("select")) {
			BrokerInfo broker = new BrokerInfo();
			broker = (BrokerInfo) dao.findByQmgrname(qmgrname).iterator().next();
			brokername = broker.getBname(); // Get			

			BrokerProxy b = connect(broker.getHostname(), new Integer(
					broker.getPort()).intValue(), broker.getQmgrname());
			if (b != null) {
				try {
					egMap = getExecutionGroup(b);
					flag = SUCCESS;					
				} catch (ConfigManagerProxyException e) {
					//e.printStackTrace();
					log.error(e.getMessage());
				} finally {
					if (b != null) {
						b.disconnect();
					}									
				}
			}
		} else {
			qmgrname = "";
			brokername = "";
			flag = SUCCESS;
		}
		
		List<BrokerInfo> list = dao.findAll();
		qmgrMap = new LinkedHashMap<String, String>();
	
		for (Iterator<BrokerInfo> i = list.iterator(); i.hasNext();) {
			BrokerInfo brokerInfo = (BrokerInfo) i.next();
			qmgrMap.put(brokerInfo.getQmgrname(), brokerInfo.getQmgrname());
		}		
		
		return flag;
	}
	
	public String viewRuntimeBrokerProperty() {
		title = "运行环境参数";
		viewdescription = "在该页面用户可以查看当前代理的运行环境参数。";
		operation = "bkparametersetting";
		
		String flag = SUCCESS;
		BrokerInfo brokerInfo = new BrokerInfo();
		BrokerProxy b = null;
		
		if (!selectedQmgr.equalsIgnoreCase("select")) {			
			brokerInfo = (BrokerInfo)dao.findByQmgrname(selectedQmgr).get(0);
			
			if (b == null) {
				b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
			}
			
			if (b != null) {
				try {
					String[] httpProperty = b.getHTTPListenerPropertyNames();
					paramNum = 0;
					for(int i=0; i < httpProperty.length; i++) {
						if (httpProperty[i].contains(brokerRuntimeParam)) {
							paramNum++;
							httpPropertyMap.put(httpProperty[i], b.getHTTPListenerProperty(httpProperty[i]));
						}	
					}
					
					flag = SUCCESS;
				} catch (ConfigManagerProxyPropertyNotInitializedException e) {					
					//e.printStackTrace();
					comment1 = "查看代理 " + brokername + " 参数失败。";
					log.error(comment1);
					flag = ERROR;						
				} catch (IllegalArgumentException e) {					
					//e.printStackTrace();
					comment1 = "代理 " + brokername + " 参数不能为空。";
					log.error(comment1);
					flag = ERROR;						
				} finally {
					if (b != null) {
						b.disconnect();
					}					
				}
			} else {
				comment1 = "连接代理 " + brokername + " 失败。";		
				log.error(comment1);
				flag = ERROR;				
			}
			
			qmgrname = selectedQmgr;
		} else {
			comment1 = "代理相关参数不能为空 。";
			
			flag = ERROR;			
		}
		
		return flag;
	}
	
	public String viewRuntimeEGProperty() {
		title = "运行环境参数";
		viewdescription = "在该页面用户可以查看所选执行组的运行环境参数。";
		operation = "egparametersetting";
		
		String flag = SUCCESS;
		BrokerInfo brokerInfo = new BrokerInfo();
		BrokerProxy b = null;
		
		if (!selectedQmgr.equalsIgnoreCase("select")) {			
			brokerInfo = (BrokerInfo)dao.findByQmgrname(selectedQmgr).get(0);
			
			if (b == null) {
				b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
			}
			if (b != null) {
				try {
					ExecutionGroupProxy e = b.getExecutionGroupByName(selectedExecutionGroup);
					String[] runtimeProperty = e.getRuntimePropertyNames();
					paramNum = 0;
					for(int i=0; i < runtimeProperty.length; i++) {
						if (runtimeProperty[i].contains(egRuntimeParam)) {
							paramNum++;
							httpPropertyMap.put(runtimeProperty[i], e.getRuntimeProperty(runtimeProperty[i]));
						}	
					}
					
					flag = SUCCESS;
				} catch (ConfigManagerProxyPropertyNotInitializedException e) {					
					//e.printStackTrace();
					comment1 = "查看代理 " + brokername + " 上的执行组 " + selectedExecutionGroup + " 参数失败。";
					log.error(comment1);
					flag = ERROR;						
				} catch (IllegalArgumentException e) {					
					//e.printStackTrace();
					comment1 = "代理 " + brokername + " 上的执行组 " + selectedExecutionGroup + " 参数不能为空。";
					log.error(comment1);
					flag = ERROR;						
				} finally {
					if (b != null) {
						b.disconnect();
					}					
				}
			} else {
				comment1 = "连接代理 " + brokername + " 上的执行组 " + selectedExecutionGroup + " 失败。";
				log.error(comment1);
				flag = ERROR;				
			}
			
			qmgrname = selectedQmgr;
			egname = selectedExecutionGroup;
		} else {
			comment1 = "代理相关参数不能为空 。";			
			flag = ERROR;			
		}
		
		return flag;
	}
	
	public String submitRuntimeBrokerProperty() {
		title = "运行环境参数";
		viewdescription = "在该页面用户可以查看当前代理的参数修改结果。";
		operation = "bkparametersetting";
		String flag = null;
		
		if (selectedItemIds != null ) {
			String objectNames[] = selectedItemIds.split(", ");
			String values[] = changeValues.split(", ");
						
			BrokerProxy b = null;
			BrokerInfo brokerInfo = new BrokerInfo();			
			brokerInfo = (BrokerInfo)dao.findByQmgrname(qmgrname).get(0);
			
			try {
				
				b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
				
				if (b != null) {
					b.setSynchronous(MAX_WAIT_TIME * 1000);
					for(int i=0; i < objectNames.length; i++) {
						String propertyItems[] = objectNames[i].split("&");
						int index = new Integer(propertyItems[1]).intValue();						
						b.setHTTPListenerProperty(propertyItems[0], values[index]);												
					}	
				}
				comment1 = "消息代理 " + brokername + " 参数修改成功。";	
				log.info(comment1);
				flag = SUCCESS; 
			} catch (ConfigManagerProxyLoggedException e) {
				//e.printStackTrace();
				comment1 = "消息代理 " + brokername + " 参数修改失败。";	
				log.error(comment1);
				flag = ERROR;	
			} finally {
				if (b != null) {					
					b.disconnect();
				}					
			}			

		} else {
			comment1 = "没有选择修改参数。";			
			flag = SUCCESS;		
		}
		
		return flag;
	}

	public String submitRuntimeEGProperty() {
		title = "运行环境参数";
		viewdescription = "在该页面用户可以查看代理相关执行组参数修改结果。";
		operation = "egparametersetting";
		String flag = null;
		
		if (selectedItemIds != null ) {
			String objectNames[] = selectedItemIds.split(", ");
			String values[] = changeValues.split(", ");
						
			BrokerProxy b = null;
			BrokerInfo brokerInfo = new BrokerInfo();			
			brokerInfo = (BrokerInfo)dao.findByQmgrname(qmgrname).get(0);
			
			try {
				
				b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
				
				if (b != null) {
					b.setSynchronous(MAX_WAIT_TIME * 1000);
					for(int i=0; i < objectNames.length; i++) {
						String propertyItems[] = objectNames[i].split("&");
						int index = new Integer(propertyItems[1]).intValue();
												
						ExecutionGroupProxy e = b.getExecutionGroupByName(egname);						
						e.setRuntimeProperty(propertyItems[0], values[index]);						
					}	
				}	
				comment1 = "代理 " + brokername + " 上的执行组 " + egname + " 参数修改成功。";
				log.info(comment1);
				flag = SUCCESS; 
			} catch (ConfigManagerProxyLoggedException e) {
				//e.printStackTrace();
				comment1 = "代理 " + brokername + " 上的执行组 " + egname + " 参数修改失败。";
				log.error(comment1);
				flag = ERROR;	
			} catch (ConfigManagerProxyPropertyNotInitializedException e) {				
				//e.printStackTrace();
				comment1 = "代理 " + brokername + " 上的执行组 " + egname + " 参数修改失败。";
				log.error(comment1);
				flag = ERROR;				
			} finally {
				if (b != null) {					
					b.disconnect();
				}					
			}			

		} else {
			comment1 = "没有选择修改参数。";			
			flag = SUCCESS;		
		}
		
		return flag;
	}
	
	public String returnRuntimeBrokerProperty() {
		title = "运行环境参数";
		viewdescription = "在该页面用户可以查看当前代理的运行环境参数。";
		operation = "bkparametersetting";
		
		String flag = SUCCESS;
		BrokerInfo brokerInfo = new BrokerInfo();
		BrokerProxy b = null;						
		brokerInfo = (BrokerInfo)dao.findByQmgrname(qmgrname).get(0);
		
		if (b == null) {
			b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
		}
		
		if (b != null) {
			try {
				String[] httpProperty = b.getHTTPListenerPropertyNames();
				paramNum = 0;
				for(int i=0; i < httpProperty.length; i++) {
					if (httpProperty[i].contains(brokerRuntimeParam)) {
						paramNum++;
						httpPropertyMap.put(httpProperty[i], b.getHTTPListenerProperty(httpProperty[i]));
					}	
				}
				
				flag = SUCCESS;
			} catch (ConfigManagerProxyPropertyNotInitializedException e) {					
				//e.printStackTrace();
				comment1 = "查看代理 " + brokername + " 参数失败。";
				log.error(comment1);
				flag = ERROR;						
			} catch (IllegalArgumentException e) {					
				//e.printStackTrace();
				comment1 = "代理 " + brokername + " 参数不能为空。";	
				log.error(comment1);
				flag = ERROR;						
			} finally {
				if (b != null) {
					b.disconnect();
				}					
			}
		} else {
			comment1 = "连接代理 " + brokername + " 失败。";
			log.error(comment1);
			flag = ERROR;				
		}
		
		return flag;
	}
	
	public String returnRuntimeEGProperty() {
		title = "运行环境参数";
		viewdescription = "在该页面用户可以查看所选执行组的运行环境参数。";
		operation = "egparametersetting";
		
		String flag = SUCCESS;
		BrokerInfo brokerInfo = new BrokerInfo();								
		brokerInfo = (BrokerInfo)dao.findByQmgrname(qmgrname).get(0);
		
		BrokerProxy b = null;
		if (b == null) {
			b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
		}
		if (b != null) {
			try {
				ExecutionGroupProxy e = b.getExecutionGroupByName(egname);
				String[] runtimeProperty = e.getRuntimePropertyNames();
				paramNum = 0;
				for(int i=0; i < runtimeProperty.length; i++) {
					if (runtimeProperty[i].contains(egRuntimeParam)) {
						paramNum++;
						httpPropertyMap.put(runtimeProperty[i], e.getRuntimeProperty(runtimeProperty[i]));
					}	
				}
				
				flag = SUCCESS;
			} catch (ConfigManagerProxyPropertyNotInitializedException e) {					
				//e.printStackTrace();
				comment1 = "查看代理 " + brokername + " 上的执行组 " + egname + " 参数失败。";
				log.error(comment1);
				flag = ERROR;						
			} catch (IllegalArgumentException e) {					
				//e.printStackTrace();
				comment1 = "代理 " + brokername + " 上的执行组 " + egname + " 参数不能为空。";	
				log.error(comment1);
				flag = ERROR;						
			} finally {
				if (b != null) {
					b.disconnect();
				}
			}
		} else {
			comment1 = "连接代理 " + brokername + " 上的执行组 " + egname + " 失败。";
			log.error(comment1);
			flag = ERROR;				
		}				
		
		return flag;
	}	
	
	private BrokerProxy connect(String hostname, int port, String qmgrname) {
		BrokerProxy b = null;
		BrokerConnectionParameters bcp = new MQBrokerConnectionParameters(
				hostname, port, qmgrname);

		try {
			b = BrokerProxy.getInstance(bcp);

			boolean brokerIsResponding = b.hasBeenPopulatedByBroker(true);

			if (brokerIsResponding) {
				// System.out.println("Broker is running");
			} else {
				// System.out.println("Broker is Stopped");
				b.disconnect();
				b = null;
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return b;
	}

	private Map<String, String> getExecutionGroup(BrokerProxy b)
			throws ConfigManagerProxyException {

		Map<String, String> egMap = new LinkedHashMap<String, String>();

		Enumeration<ExecutionGroupProxy> allEGsInThisBroker = b
				.getExecutionGroups(null);
		if (allEGsInThisBroker.hasMoreElements()) {

			while (allEGsInThisBroker.hasMoreElements()) {
				ExecutionGroupProxy thisEG = allEGsInThisBroker.nextElement();

				boolean isEgRunning = thisEG.isRunning();
				if (isEgRunning) {
					String executionGroupName = thisEG.getName();
					egMap.put(executionGroupName, executionGroupName);
				}
			}

		}

		return egMap;
	}
	
}