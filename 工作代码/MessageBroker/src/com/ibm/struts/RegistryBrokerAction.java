package com.ibm.struts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.broker.config.proxy.BrokerConnectionParameters;
import com.ibm.broker.config.proxy.BrokerProxy;
import com.ibm.broker.config.proxy.ConfigManagerProxyLoggedException;
import com.ibm.broker.config.proxy.ConfigManagerProxyPropertyNotInitializedException;
import com.ibm.broker.config.proxy.MQBrokerConnectionParameters;
import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class RegistryBrokerAction extends ActionSupport {

	private static final Logger log = LoggerFactory.getLogger(RegistryBrokerAction.class);
	
	private static final long serialVersionUID = 1L;
	private String qmgrname;
	private String hostname;
	private String port;
	private String svrconn;
	private String bname;
	private String title;
	private String message;
	private String comment1;
	private String comment2;	
	private String description;	
	private String registrytime;
	private String pid;
	private String status;
	
	List<BrokerInfo> brokerList = new ArrayList<BrokerInfo>();
		
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BrokerInfo> getBrokerList() {
		return brokerList;
	}

	public void setBrokerList(List<BrokerInfo> brokerList) {
		this.brokerList = brokerList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegistrytime() {
		return registrytime;
	}

	public void setRegistrytime(String registrytime) {
		this.registrytime = registrytime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getComment2() {
		return comment2;
	}

	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getQmgrname() {
		return qmgrname;
	}


	public void setQmgrname(String qmgrname) {
		this.qmgrname = qmgrname;
	}


	public String getHostname() {
		return hostname;
	}


	public void setHostname(String hostname) {
		this.hostname = hostname;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	public String getSvrconn() {
		return svrconn;
	}


	public void setSvrconn(String svrconn) {
		this.svrconn = svrconn;
	}

	/**
	 * @return
	 */
	public String create() {
		if (!qmgrname.equalsIgnoreCase("") && !hostname.equalsIgnoreCase("") && !port.equalsIgnoreCase("")) {
			bname = getBroker();
		
			if (bname != null) {
				BrokerInfoDAO dao = new BrokerInfoDAO();
				BrokerInfo bk = new BrokerInfo();
				bk.setQmgrname(qmgrname);
				bk.setHostname(hostname);
				bk.setPort(port);
				bk.setSvrconn(svrconn);
				bk.setDescription(description);
				bk.setPid("0");
				bk.setStatus("0");
				
				Calendar day = Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");			
				bk.setRegistrytime(sdf.format(day.getTime()));
				
				bk.setBname(bname);
				if (dao.save(bk)) {
					title = "注册代理";
					message = "注册成功";
					comment1 ="代理 " + bname + " 已经成功注册。";
					log.info(comment1);
					brokerList.add(bk);
					
					return SUCCESS;
				} else {
					title = "注册代理";
					message = "注册失败";
					comment1 = "代理 " + bname + " 信息可能已经存在。";
					log.error(comment1);
					return ERROR;
				}
								
			} else {
				title = "注册代理";
				message = "注册失败";
				comment1 = "1.请确认输入参数是否正确；";
				comment2 = "2.请确认代理 " + bname + " 是否正在运行。";
				log.error("注册失败  " + comment1);
				log.error("注册失败  " + comment2);
				return ERROR;
			}
			
		} else {
			setTitle("注册代理");
			setMessage("注册失败");
			setComment1("队列管理器、主机名(IP地址)和端口不能为空。");
			return ERROR;
		} 
	}
	
	public String getBroker() {
		String brokerName = null;
		
		BrokerProxy b = null;
        BrokerConnectionParameters bcp;
        bcp = new MQBrokerConnectionParameters(hostname, new Integer(port).intValue(), qmgrname);
        try {
        	b = BrokerProxy.getInstance(bcp);
            boolean brokerIsResponding = b.hasBeenPopulatedByBroker(true);
            
            if (brokerIsResponding) {
            	if (brokerIsResponding) {
                    brokerName = b.getName();
                } else {                                
                	brokerName = null;
                }
            }
        } catch (ConfigManagerProxyPropertyNotInitializedException e1) {
        	brokerName = null;
            log.error(e1.getMessage());
        } catch (ConfigManagerProxyLoggedException e2) {
        	brokerName = null;
        	log.error(e2.getMessage());
        } catch (Exception e3) {
        	brokerName = null;
        	log.error(e3.getMessage());
        } finally {        
        	if (b != null) {
        		b.disconnect();
        	}
        }                
        
		return brokerName; 
	}
	
	public String displayregistry(){
		
		return SUCCESS;
	}
}