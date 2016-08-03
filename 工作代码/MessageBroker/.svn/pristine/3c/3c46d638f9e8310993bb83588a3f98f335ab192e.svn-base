package com.ibm.struts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.ibm.paser.JmsConsumer;
import com.ibm.paser.JmsControl;
import com.ibm.paser.JmsProducer;
import com.opensymphony.xwork2.ActionSupport;

public class ListBrokersAction extends ActionSupport {

	private static final Logger log = LoggerFactory.getLogger(ListBrokersAction.class);
	
	private static final long serialVersionUID = 1L;
	BrokerInfoDAO dao = new BrokerInfoDAO();	  
	private String qmgrname;	
	private String hostname;	
	private String port;	
	private String svrconn;	
	private String bname;	
	private String description;	
	private String registrytime;	
	private String pid;	
	private String status;	
	private String selectedItemIds;	
	private String alert;	
	private String comment1;	
	private String title;	
	private String viewdescription;	
	private String operation;		
	private String flag;
	private String changeValues;
	List<BrokerInfo> pageBrokerInfoList = new ArrayList<BrokerInfo>();    
	Map<String, String> commentMap = new LinkedHashMap<String, String>();
	
	public ListBrokersAction() {
		String datapath = ServletActionContext.getServletContext().getRealPath("/data");				
		System.getProperties().setProperty("WMB_MONITOR_DATA", datapath);
		String context = ServletActionContext.getServletContext().getContextPath();
		System.getProperties().setProperty("WMB_MONITOR_CONTEXTPATH", context);			
	}
	
	public String getChangeValues() {
		return changeValues;
	}

	public void setChangeValues(String changeValues) {
		this.changeValues = changeValues;
	}

	public Map<String, String> getCommentMap() {
		return commentMap;
	}

	public void setCommentMap(Map<String, String> commentMap) {
		this.commentMap = commentMap;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getSelectedItemIds() {
		return selectedItemIds;
	}

	public void setSelectedItemIds(String selectedItemIds) {
		this.selectedItemIds = selectedItemIds;
	}

	public List<BrokerInfo> getPageBrokerInfoList() {
		return pageBrokerInfoList;
	}

	public void setPageBrokerInfoList(List<BrokerInfo> pageBrokerInfoList) {
		this.pageBrokerInfoList = pageBrokerInfoList;
	}
	
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

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	private int rowsPerPage = 20;// 每页显示几条  
  
    private int page = 1; // 默认当前页  
  
    private int totalPage;// 总共多少页  
  
    private int planNum;// 总过多少条 
        

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPlanNum() {
		return planNum;
	}

	public void setPlanNum(int planNum) {
		this.planNum = planNum;
	}

	/**
	 * @return
	 */
	public String search() {
		dao = new BrokerInfoDAO();
		pageBrokerInfoList = dao.findBrokerInfoByPage(page, rowsPerPage);  
        totalPage = dao.getBrokerInfoTotalPage(rowsPerPage);  
        planNum = dao.getBrokerInfoNum();  
        
		return SUCCESS;
	}			
	
	public String delete() {	

		String qMgrName[] = selectedItemIds.split(", ");
		for (int i=0; i< qMgrName.length;i++) {
			String info[] = qMgrName[i].split("&");						
			BrokerInfo broker = new BrokerInfo();
			BrokerInfoDAO dao = new BrokerInfoDAO();
			broker = (BrokerInfo)dao.findByQmgrname(info[0]).get(0);
			if (broker.getStatus().equalsIgnoreCase("0")) {
				dao.delete(broker);
				comment1 = "删除注册代理 " + broker.getBname() + "成功。";
				log.info(comment1);
			} else {
				alert = "代理 " + broker.getBname() +  " 监控程序正在运行，删除失败!";
				log.error(alert);
				break;
			}
		}
		
        pageBrokerInfoList = dao.findBrokerInfoByPage(page, rowsPerPage);  
        totalPage = dao.getBrokerInfoTotalPage(rowsPerPage);  
        planNum = dao.getBrokerInfoNum();  
        
		return SUCCESS;
	}
	
	public String startBroker() {
		title = "启动代理";
		viewdescription = "在该页面用户可以查看启动代理结果。";
		operation = "controlbroker";
		
		if (selectedItemIds != null) {
			//System.out.println("stop broker");
			String qMgrName[] = selectedItemIds.split(", ");
			for (int i=0; i< qMgrName.length;i++) {
				String info[] = qMgrName[i].split("&");
				BrokerInfo brokerInfo = new BrokerInfo();							
				brokerInfo = (BrokerInfo)dao.findByQmgrname(info[0]).get(0);
				String message = "<Monitor><command>mqsistart</command><brokername>" +
								brokerInfo.getBname() + "</brokername></Monitor>";
				
				JmsControl clt = new JmsControl(brokerInfo, message, "1");
				comment1 = clt.run();				
				
				String[] comment = comment1.split("BIP");
				for(int j=1; j < comment.length; j++) {
					log.info(comment[j]);
					commentMap.put(new Integer(j).toString(), "BIP" + comment[j]);
				}
			}			

		} else {
			comment1 = "没有选择要启动的代理。";
		}
		
		return SUCCESS;
	}

	public String stopBroker() {
		title = "停止代理";
		viewdescription = "在该页面用户可以查看停止代理结果。";
		operation = "controlbroker";
		
		if (selectedItemIds != null) {
			//System.out.println("stop broker");
			String qMgrName[] = selectedItemIds.split(", ");
			for (int i=0; i< qMgrName.length;i++) {
				String info[] = qMgrName[i].split("&");
				BrokerInfo brokerInfo = new BrokerInfo();							
				brokerInfo = (BrokerInfo)dao.findByQmgrname(info[0]).get(0);
				String message = null;
				if (flag.equalsIgnoreCase("0")) {
					message = "<Monitor><command>mqsistop</command><brokername>" +
								brokerInfo.getBname() + "</brokername></Monitor>";					
				} else {
					message = "<Monitor><command>mqsistop</command><control>-i</control><brokername>" +
					brokerInfo.getBname() + "</brokername></Monitor>";
				}
				
				JmsControl clt = new JmsControl(brokerInfo, message, "2");
				comment1 = clt.run();
				
				String[] comment = comment1.split("BIP");
				for(int j=1; j < comment.length; j++) {
					log.info(comment[j]);
					commentMap.put(new Integer(j).toString(), "BIP" + comment[j]);
				}				
			}			

		} else {
			comment1 = "没有选择要启动的代理。";
		}
		
		return SUCCESS;
	}
	
	public String startMonitor() {
		if (selectedItemIds != null) {
			//System.out.println("start broker");
			String qMgrName[] = selectedItemIds.split(", ");
			String threadnum[] = changeValues.split(", ");
			for (int i=0; i< qMgrName.length;i++) {
				String info[] = qMgrName[i].split("&");
				String index = info[1];
				BrokerInfo brokerInfo = new BrokerInfo();							
				brokerInfo = (BrokerInfo)dao.findByQmgrname(info[0]).get(0);
				brokerInfo.setInstances(Integer.parseInt(threadnum[Integer.parseInt(index)]));
				if (brokerInfo.getStatus().equalsIgnoreCase("0")) {
					brokerInfo.setStatus("1"); 
					dao.update(brokerInfo);
					for(int j = 1; j <= Integer.parseInt(threadnum[Integer.parseInt(index)]) ;j++)
					{
						Thread run = new Thread(new JmsConsumer(brokerInfo, dao));
						run.start();
					}
					
				}	
			}			

		}
        pageBrokerInfoList = dao.findBrokerInfoByPage(page, rowsPerPage);  
        totalPage = dao.getBrokerInfoTotalPage(rowsPerPage);  
        planNum = dao.getBrokerInfoNum(); 
        
		return SUCCESS;
	}
	
	public String stopMonitor() {
		if (selectedItemIds != null) {
			//System.out.println("stop broker");
			String qMgrName[] = selectedItemIds.split(", ");
			String threadnum[] = changeValues.split(", ");
			for (int i=0; i< qMgrName.length;i++) {
				String info[] = qMgrName[i].split("&");
				BrokerInfo brokerInfo = new BrokerInfo();
				String index = info[1];
				brokerInfo = (BrokerInfo)dao.findByQmgrname(info[0]).get(0);
				brokerInfo.setStatus("0"); 
				dao.update(brokerInfo);
				for(int j = 1; j <= Integer.parseInt(threadnum[Integer.parseInt(index)]) ;j++)
				{
					Thread run = new Thread(new JmsProducer(brokerInfo));
					run.start();				
				}
			}			

		}
		
        pageBrokerInfoList = dao.findBrokerInfoByPage(page, rowsPerPage);  
        totalPage = dao.getBrokerInfoTotalPage(rowsPerPage);  
        planNum = dao.getBrokerInfoNum(); 
        
		return SUCCESS;
	}
	
}