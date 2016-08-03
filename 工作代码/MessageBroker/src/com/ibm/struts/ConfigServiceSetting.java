package com.ibm.struts;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.broker.config.proxy.BrokerConnectionParameters;
import com.ibm.broker.config.proxy.BrokerProxy;
import com.ibm.broker.config.proxy.ConfigManagerProxyLoggedException;
import com.ibm.broker.config.proxy.ConfigManagerProxyPropertyNotInitializedException;
import com.ibm.broker.config.proxy.ConfigurableService;
import com.ibm.broker.config.proxy.MQBrokerConnectionParameters;
import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigServiceSetting extends ActionSupport {

	private static final Logger log = LoggerFactory.getLogger(ConfigServiceSetting.class);
	
	private static final long serialVersionUID = 1L;
	private int MAX_WAIT_TIME = 120;		
	private String qmgrname;
	private String brokername;
	private String selectedQmgr;
	private String selectedConfigServiceType;
	private String selectedConfigServiceItems;	
	private String comment1;	
	private String title;	
	private String viewdescription;	
	private String operation;		
	private int paramNum = 0;	
	private String selectedItemIds;	
	private String changeValues;	
	Map<String, String> qmgrMap = new LinkedHashMap<String, String>();	
	Map<String, String> configServiceMap = new LinkedHashMap<String, String>();	
	Map<String, String> configServiceItemsMap = new LinkedHashMap<String, String>();	
	Map<String, String> servicePropertyMap = new LinkedHashMap<String, String>();	
	BrokerInfoDAO dao = new BrokerInfoDAO();
	
	public String getQmgrname() {
		return qmgrname;
	}

	public void setQmgrname(String qmgrname) {
		this.qmgrname = qmgrname;
	}

	public String getBrokername() {
		return brokername;
	}

	public void setBrokername(String brokername) {
		this.brokername = brokername;
	}

	public String getSelectedQmgr() {
		return selectedQmgr;
	}

	public void setSelectedQmgr(String selectedQmgr) {
		this.selectedQmgr = selectedQmgr;
	}

	public String getSelectedConfigServiceType() {
		return selectedConfigServiceType;
	}

	public void setSelectedConfigServiceType(String selectedConfigServiceType) {
		this.selectedConfigServiceType = selectedConfigServiceType;
	}

	public String getSelectedConfigServiceItems() {
		return selectedConfigServiceItems;
	}

	public void setSelectedConfigServiceItems(String selectedConfigServiceItems) {
		this.selectedConfigServiceItems = selectedConfigServiceItems;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
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

	public int getParamNum() {
		return paramNum;
	}

	public void setParamNum(int paramNum) {
		this.paramNum = paramNum;
	}

	public String getSelectedItemIds() {
		return selectedItemIds;
	}

	public void setSelectedItemIds(String selectedItemIds) {
		this.selectedItemIds = selectedItemIds;
	}

	public String getChangeValues() {
		return changeValues;
	}

	public void setChangeValues(String changeValues) {
		this.changeValues = changeValues;
	}

	public Map<String, String> getQmgrMap() {
		return qmgrMap;
	}

	public void setQmgrMap(Map<String, String> qmgrMap) {
		this.qmgrMap = qmgrMap;
	}

	public Map<String, String> getConfigServiceMap() {
		return configServiceMap;
	}

	public void setConfigServiceMap(Map<String, String> configServiceMap) {
		this.configServiceMap = configServiceMap;
	}

	public Map<String, String> getConfigServiceItemsMap() {
		return configServiceItemsMap;
	}

	public void setConfigServiceItemsMap(Map<String, String> configServiceItemsMap) {
		this.configServiceItemsMap = configServiceItemsMap;
	}

	public Map<String, String> getServicePropertyMap() {
		return servicePropertyMap;
	}

	public void setServicePropertyMap(Map<String, String> servicePropertyMap) {
		this.servicePropertyMap = servicePropertyMap;
	}

	public String queryAllQmgr() {		
		List<BrokerInfo> list = dao.findAll();

		for (Iterator<BrokerInfo> i = list.iterator(); i.hasNext();) {
			BrokerInfo brokerInfo = i.next();
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
		title = "选择代理";
		viewdescription = "在该页面用户可以查看配置服务信息。";
		operation = "selectforconfigservice";
		String flag = SUCCESS;
		BrokerInfo brokerInfo = new BrokerInfo();
		
		if (!selectedQmgr.equalsIgnoreCase("select")) {

			brokerInfo = (BrokerInfo) dao.findByQmgrname(selectedQmgr).iterator().next();
			brokername = brokerInfo.getBname(); // Get			
			qmgrname = selectedQmgr;
			
			BrokerProxy b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
			if (b!= null) {
				try {
					String[] configServiceTypes = b.getConfigurableServiceTypes();
					for(int i=0; i < configServiceTypes.length; i++) {
						configServiceMap.put(configServiceTypes[i], configServiceTypes[i]);					
					}
					selectedConfigServiceType = "JMSProviders";
					
					ConfigurableService[] configServiceItems = b.getConfigurableServices(selectedConfigServiceType);
					for(int i=0; i < configServiceItems.length; i++) {
						ConfigurableService service = configServiceItems[i];
						configServiceItemsMap.put(service.getName(), service.getName());
					}
				} catch (ConfigManagerProxyPropertyNotInitializedException e) {				
					//e.printStackTrace();
					log.error(e.getMessage());
				} catch (IllegalArgumentException e) {
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
			brokerInfo = new BrokerInfo();
			brokerInfo = i.next();
			qmgrMap.put(brokerInfo.getQmgrname(), brokerInfo.getQmgrname());
		}		
				
		return flag;
	}
	
	public String lookupConfigServiceItem() {
		String flag = SUCCESS;
		title = "选择代理";
		viewdescription = "在该页面用户可以查看配置服务信息。";
		operation = "selectforconfigservice";
		BrokerInfo brokerInfo = new BrokerInfo();
		
		if (!selectedQmgr.equalsIgnoreCase("select")) {

			brokerInfo = (BrokerInfo) dao.findByQmgrname(selectedQmgr).iterator().next();
			brokername = brokerInfo.getBname(); // Get			
			qmgrname = selectedQmgr;
			
			BrokerProxy b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
			if (b!= null) {
				try {
					String[] configServiceTypes = b.getConfigurableServiceTypes();
					for(int i=0; i < configServiceTypes.length; i++) {
						configServiceMap.put(configServiceTypes[i], configServiceTypes[i]);					
					}
					
					ConfigurableService[] configServiceItems = b.getConfigurableServices(selectedConfigServiceType);
					for(int i=0; i < configServiceItems.length; i++) {
						ConfigurableService service = configServiceItems[i];
						configServiceItemsMap.put(service.getName(), service.getName());
					}
					
					flag = SUCCESS;
				} catch (ConfigManagerProxyPropertyNotInitializedException e) {				
					//e.printStackTrace();		
					log.error(e.getMessage());
				} catch (IllegalArgumentException e) {
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
			brokerInfo = new BrokerInfo();
			brokerInfo = i.next();
			qmgrMap.put(brokerInfo.getQmgrname(), brokerInfo.getQmgrname());
		}
		
		return flag;
	}

	@SuppressWarnings("unchecked")
	public String viewConfigServiceProperty() {
		String flag = SUCCESS;
		title = "查看配置服务参数";
		viewdescription = "在该页面用户可以查看配置服务信息。";
		operation = "viewconfigservice";
		BrokerInfo brokerInfo = new BrokerInfo();
		
		if (!selectedQmgr.equalsIgnoreCase("select")) {

			brokerInfo = (BrokerInfo) dao.findByQmgrname(selectedQmgr).iterator().next();
			brokername = brokerInfo.getBname(); // Get			
			qmgrname = selectedQmgr;
			
			BrokerProxy b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
			if (b!= null) {
				try {
					ConfigurableService service = b.getConfigurableService(selectedConfigServiceType, selectedConfigServiceItems);
					Properties p = service.getProperties();
					Enumeration e = p.keys();
					paramNum = 0;
					while(e.hasMoreElements()){
						paramNum++;
						String key = (String)e.nextElement();						
						servicePropertyMap.put(key, p.getProperty(key));
					}
					
					flag = SUCCESS;
				} catch (ConfigManagerProxyPropertyNotInitializedException e) {				
					//e.printStackTrace();
					comment1 = "类型为 " + selectedConfigServiceType + " 的配置服务 " + selectedConfigServiceItems + " 参数查看失败。";
					log.error(comment1);
					log.error(e.getMessage());
					flag = ERROR;
				} catch (IllegalArgumentException e) {
					//e.printStackTrace();
					comment1 = "配置服务参数不能为空。";
					log.error(comment1);
					log.error(e.getMessage());
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
		} else {
			qmgrname = "";
			brokername = "";
			flag = SUCCESS;
		}			
		
		return flag;
	}
	
	public String submitConfigServiceItem() {
		String flag = SUCCESS;
		title = "查看配置服务参数";
		viewdescription = "在该页面用户可以查看配置服务信息修改结果。";
		operation = "configservice";
		
		if (selectedItemIds != null ) {
			String objectNames[] = selectedItemIds.split(", ");
			String values[] = changeValues.split(", ");
			BrokerInfo brokerInfo = new BrokerInfo();			
			brokerInfo = (BrokerInfo)dao.findByQmgrname(qmgrname).get(0);							
			BrokerProxy b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
			
			try {									
				
				if (b != null) {
					b.setSynchronous(MAX_WAIT_TIME * 1000);
					for(int i=0; i < objectNames.length; i++) {
						String propertyItems[] = objectNames[i].split("&");
						int index = new Integer(propertyItems[1]).intValue();
												
						String key = selectedConfigServiceType + "/" + selectedConfigServiceItems + "/" + propertyItems[0];						
						b.setConfigurableServiceProperty(key, values[index]);						
					}	
					
					comment1 = "类型为 " + selectedConfigServiceType + " 的配置服务 " + selectedConfigServiceItems + " 参数修改成功。";
					log.info(comment1);
					flag = SUCCESS; 					
				} else {
					comment1 = "连接代理 " + brokername + " 失败。";	
					log.error(comment1);
					flag = ERROR;
				}
				
			} catch (ConfigManagerProxyLoggedException e) {
				//e.printStackTrace();
				comment1 = "类型为 " + selectedConfigServiceType + " 的配置服务 " + selectedConfigServiceItems + " 参数修改失败。";
				log.error(comment1);
				log.error(e.getMessage());
				flag = ERROR;
			} catch (IllegalArgumentException e) {
				//e.printStackTrace();
				comment1 = "配置服务参数不能为空。";
				log.error(comment1);
				log.error(e.getMessage());
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
	
	@SuppressWarnings("unchecked")
	public String returnConfigServiceProperty() {
		String flag = SUCCESS;
		title = "查看配置服务参数";
		viewdescription = "在该页面用户可以查看配置服务参数。";
		operation = "configservice";
		
		BrokerInfo brokerInfo = new BrokerInfo();
		BrokerProxy b = null;						
		brokerInfo = (BrokerInfo)dao.findByQmgrname(qmgrname).get(0);
		
		if (b == null) {
			b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
		} 
			
		if (b!= null) {
			try {
				ConfigurableService service = b.getConfigurableService(selectedConfigServiceType, selectedConfigServiceItems);
				Properties p = service.getProperties();
				Enumeration e = p.keys();
				paramNum = 0;
				while(e.hasMoreElements()){
					paramNum++;
					String key = (String)e.nextElement();						
					servicePropertyMap.put(key, p.getProperty(key));
				}
				
				flag = SUCCESS;
			} catch (ConfigManagerProxyPropertyNotInitializedException e) {				
				//e.printStackTrace();
				comment1 = "查看类型为 " + selectedConfigServiceType + " 配置服务 " + selectedConfigServiceItems + " 参数失败。";
				log.error(comment1);
				log.error(e.getMessage());
				flag = ERROR;
			} catch (IllegalArgumentException e) {
				//e.printStackTrace();
				comment1 = "配置服务 " + selectedConfigServiceType + " 参数不能为空。";
				log.error(comment1);
				log.error(e.getMessage());
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
	
	public String submitToCreateConfigService() {
		String flag = SUCCESS;
		title = "创建配置服务";
		viewdescription = "在该页面用户可以创建配置服务。";
		operation = "createconfigservice";
		BrokerInfo brokerInfo = new BrokerInfo();
		
		if (!selectedQmgr.equalsIgnoreCase("select")) {

			brokerInfo = (BrokerInfo) dao.findByQmgrname(selectedQmgr).iterator().next();
			brokername = brokerInfo.getBname(); // Get			
			qmgrname = selectedQmgr;
			
			BrokerProxy b = connect(brokerInfo.getHostname(), new Integer(brokerInfo.getPort()).intValue(),brokerInfo.getQmgrname());
			if (b!= null) {
				try {
					b.setSynchronous(MAX_WAIT_TIME * 1000);
					b.createConfigurableService(selectedConfigServiceType, selectedConfigServiceItems);
					
					comment1 = "类型为 " + selectedConfigServiceType + " 的配置服务 " + selectedConfigServiceItems + " 创建成功。";
					log.info(comment1);
					flag = SUCCESS;
				} catch (IllegalArgumentException e) {
					//e.printStackTrace();
					comment1 = "配置服务相关名称和参数不能为空。";
					log.error(comment1);
					log.error(e.getMessage());
					flag = ERROR;
				} catch (ConfigManagerProxyLoggedException e) {
					//e.printStackTrace();
					comment1 = "类型为 " + selectedConfigServiceType + " 的配置服务 " + selectedConfigServiceItems + " 创建失败。";
					log.error(comment1);
					log.error(e.getMessage());
					flag = ERROR;										
				} finally {
					if (b != null) {					
						b.disconnect();
					}						
				}
				
			}
		} else {
			comment1 = "配置服务相关名称和参数不能为空。";
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
}