package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.AlertCondition;
import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.ibm.hibernate.PartofthroughoutControl;
import com.ibm.hibernate.PartofthroughoutControlDAO;
import com.ibm.hibernate.TotalthroughoutControl;
import com.ibm.hibernate.TotalthroughoutControlDAO;
import com.ibm.pcf.PCF_QueueHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.ibm.throughput.*;

public class SetTrafficControl extends ActionSupport {
	
	private static final Logger log = LoggerFactory.getLogger(SetTrafficControl.class);
	
	private static final long serialVersionUID = 1L;
	PartofthroughoutControlDAO ptDao = new PartofthroughoutControlDAO();
	List<PartofthroughoutControl> pageControlList = new ArrayList<PartofthroughoutControl>(); 
	TotalthroughoutControlDAO ttDao = new TotalthroughoutControlDAO();
	
	private int rowsPerPage = 20;// 每页显示几条  	  
    private int page = 1; // 默认当前页    
    private int totalPage;// 总共多少页    
    private int planNum;// 总过多少条     
    
	private String allinterval;
	private String allunit;
	private String allthreshold;	
	
	private String flowname;
	private String partofinterval;
	private String partofunit;
	private String partofthreshold;
	private String status;
	
	private String selectedItemIds;
	private String result;	
	
	public List<PartofthroughoutControl> getPageControlList() {
		return pageControlList;
	}

	public void setPageControlList(List<PartofthroughoutControl> pageControlList) {
		this.pageControlList = pageControlList;
	}

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

	public String getAllinterval() {
		return allinterval;
	}

	public void setAllinterval(String allinterval) {
		this.allinterval = allinterval;
	}

	public String getAllunit() {
		return allunit;
	}

	public void setAllunit(String allunit) {
		this.allunit = allunit;
	}

	public String getAllthreshold() {
		return allthreshold;
	}

	public void setAllthreshold(String allthreshold) {
		this.allthreshold = allthreshold;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFlowname() {
		return flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	public String getPartofinterval() {
		return partofinterval;
	}

	public void setPartofinterval(String partofinterval) {
		this.partofinterval = partofinterval;
	}

	public String getPartofunit() {
		return partofunit;
	}

	public void setPartofunit(String partofunit) {
		this.partofunit = partofunit;
	}

	public String getPartofthreshold() {
		return partofthreshold;
	}

	public void setPartofthreshold(String partofthreshold) {
		this.partofthreshold = partofthreshold;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSelectedItemIds() {
		return selectedItemIds;
	}

	public void setSelectedItemIds(String selectedItemIds) {
		this.selectedItemIds = selectedItemIds;
	}

	/**
	 * set the whole throughout control
	 * 
	 */
	public String setallcontrol(){
//		String filePath = ServletActionContext.getServletContext().getRealPath("/config") + "/TrafficControl.properties";
//		allinterval = CommonUtils.readValue(filePath, "allinterval");		
//		allunit = CommonUtils.readValue(filePath, "allunit");
//		allthreshold = CommonUtils.readValue(filePath, "allthreshold");	
		TotalthroughoutControl ttControl = new TotalthroughoutControl();
		ttControl = ttDao.findById("esb");
		if (ttControl != null) {
			allinterval = ttControl.getTotalinterval();
			allunit = ttControl.getTotalunit();
			allthreshold = ttControl.getTotalthreshold();
		}
		
		return SUCCESS;
	}
	
	public String saveallcontrol() {
//		String filePath = ServletActionContext.getServletContext().getRealPath("/config") + "/TrafficControl.properties";
//		CommonUtils.writeProperties(filePath, "allinterval", allinterval);
//		CommonUtils.writeProperties(filePath, "allunit", allunit);
//		CommonUtils.writeProperties(filePath, "allthreshold", allthreshold);
		TotalthroughoutControl ttControl = new TotalthroughoutControl();
		ttControl.setTid("esb");
		ttControl.setTotalinterval(allinterval);
		ttControl.setTotalunit(allunit);
		ttControl.setTotalthreshold(allthreshold);
		ttDao.save(ttControl);
		
		result = "保存成功！";		
		return SUCCESS;
	}

	public String listpart() {
		
		pageControlList = ptDao.findAllByPage(page, rowsPerPage); 
        totalPage = ptDao.getTotalPage(rowsPerPage);  
        planNum = ptDao.getTotalNum();		
		
		return SUCCESS;
	}
	
	/**
	 * set the part of throughout control
	 * 
	 */	
	public String addpartofcontrol() {
		
		return SUCCESS;
	}
	
	public String savepartofcontrol() {
		PartofthroughoutControl pot = new PartofthroughoutControl();
		pot.setFlowname(flowname);
		pot.setPartofinterval(partofinterval);
		pot.setPartofunit(partofunit);
		pot.setPartofthreshold(partofthreshold);
		pot.setStatus("0");
		
		if (ptDao.save(pot)) {
			pageControlList = ptDao.findAllByPage(page, rowsPerPage); 		
	        totalPage = ptDao.getTotalPage(rowsPerPage);  
	        planNum = ptDao.getTotalNum();
	           
	        try{
	        	BrokerInfo brokerInfo = new BrokerInfo();
	        	BrokerInfoDAO dao = new BrokerInfoDAO();
	    		brokerInfo = (BrokerInfo)dao.findAll().get(0);
	    		PCF_QueueHelper.createQueue(flowname, brokerInfo.getHostname(), Integer.parseInt(brokerInfo.getPort()), brokerInfo.getSvrconn(), Integer.parseInt(partofthreshold));
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = "消息流'" + flowname + "'分量控制创建成功！";
			return SUCCESS;
		} else {
			result = "消息流'" + flowname + "'分量控制创建失败，请重试！";
			return ERROR;			
		}
	}

	public String preUpdatePartOfControl(){
		String flag = SUCCESS;
		
		if(selectedItemIds == null)
		{
			result = "您选择了零个或者多个分量 ，请选择一个分量进行修改！";
			pageControlList = ptDao.findAllByPage(page, rowsPerPage); 
	        totalPage = ptDao.getTotalPage(rowsPerPage);  
	        planNum = ptDao.getTotalNum();	
	        
			return ERROR;	
		}
		String selectedNames[] = selectedItemIds.split(", ");	
		if(selectedNames.length != 1 )
		{
			result = "您选择了零个或者多个分量 ，请选择一个分量进行修改！";
			pageControlList = ptDao.findAllByPage(page, rowsPerPage); 
	        totalPage = ptDao.getTotalPage(rowsPerPage);  
	        planNum = ptDao.getTotalNum();	
	        
			return ERROR;	
		}
		else{
			
			PartofthroughoutControl pot = new PartofthroughoutControl();
			pot.setFlowname(selectedNames[0]);
			
			try {
				BrokerInfo brokerInfo = new BrokerInfo();
	        	BrokerInfoDAO dao = new BrokerInfoDAO();
	    		brokerInfo = (BrokerInfo)dao.findAll().get(0);
				pot = ptDao.findByFlowName(selectedNames[0].trim()).iterator().next();
				if(pot != null)
				{
					flowname = pot.getFlowname();
					partofinterval = pot.getPartofinterval();
					partofunit = pot.getPartofunit();
					partofthreshold = pot.getPartofthreshold();
					status = pot.getStatus();
				}
				return SUCCESS;	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return SUCCESS;	

	}
	
	public String updatePartOfControl() {
		PartofthroughoutControl pot = new PartofthroughoutControl();
		pot.setFlowname(flowname);
		
		try {
			BrokerInfo brokerInfo = new BrokerInfo();
        	BrokerInfoDAO dao = new BrokerInfoDAO();
    		brokerInfo = (BrokerInfo)dao.findAll().get(0);
			pot = ptDao.findByFlowName(flowname.trim()).iterator().next();				
			pot.setPartofthreshold(partofthreshold);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		if (ptDao.update(pot)) {
			pageControlList = ptDao.findAllByPage(page, rowsPerPage); 		
	        totalPage = ptDao.getTotalPage(rowsPerPage);  
	        planNum = ptDao.getTotalNum();
	           
	        try{
	        	BrokerInfo brokerInfo = new BrokerInfo();
	        	BrokerInfoDAO dao = new BrokerInfoDAO();
	    		brokerInfo = (BrokerInfo)dao.findAll().get(0);
	    		PCF_QueueHelper.updateQueue(flowname.trim(), brokerInfo.getHostname(), Integer.parseInt(brokerInfo.getPort()), brokerInfo.getSvrconn(), Integer.parseInt(partofthreshold));
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = "消息流'" + flowname + "'流量控制修改阀值成功！";
			return SUCCESS;
		} else {
			result = "消息流'" + flowname + "'流量控制修改阀值失败，请重试！";
			return ERROR;			
		}

	}	
	
	public String delpartofcontrol() {
		String flag = SUCCESS;
		
		String selectedNames[] = selectedItemIds.split(", ");
		for (int i=0; i< selectedNames.length;i++) {
			PartofthroughoutControl pot = new PartofthroughoutControl();
			pot.setFlowname(selectedNames[i]);
			
			try {
				BrokerInfo brokerInfo = new BrokerInfo();
	        	BrokerInfoDAO dao = new BrokerInfoDAO();
	    		brokerInfo = (BrokerInfo)dao.findAll().get(0);
				pot = ptDao.findByFlowName(selectedNames[i].trim()).iterator().next();				
				
				PCF_QueueHelper.deleteQueue(selectedNames[i].trim(), brokerInfo.getHostname(), Integer.parseInt(brokerInfo.getPort()), brokerInfo.getSvrconn());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (ptDao.delete(pot)) {				
				result = "删除成功。";
				
				pageControlList = ptDao.findAllByPage(page, rowsPerPage); 		
		        totalPage = ptDao.getTotalPage(rowsPerPage);  
		        planNum = ptDao.getTotalNum();		        
		        
			} else {				
				result = "删除失败！";
				flag = ERROR;
			}							
		}
					
		return flag;
	}
	
	public String startPartOfControl() {
		String flag = SUCCESS;
		
		String selectedNames[] = selectedItemIds.split(", ");
		for (int i=0; i< selectedNames.length;i++) {
			PartofthroughoutControl control = new PartofthroughoutControl();
		
			try {
				
				control = ptDao.findByFlowName(selectedNames[i].trim()).iterator().next();				
				//PCF_QueueHelper.createQueue(selectedNames[i].trim(), "localhost", 2414, "CHANNEL1", Integer.parseInt(control.getPartofthreshold()));
				
				Thread run = new Thread(new ThroughputStarter(control));
				run.start();
				control.setStatus("1");
				ptDao.update(control);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
			
		pageControlList = ptDao.findAllByPage(page, rowsPerPage); 		
        totalPage = ptDao.getTotalPage(rowsPerPage);  
        planNum = ptDao.getTotalNum();	
		return flag;
	}	
	
	public String stopPartOfControl() {
		
		String objectName[] = selectedItemIds.split(", ");
		for(int i=0; i < objectName.length; i++) {	
			PartofthroughoutControl control = new PartofthroughoutControl();
			control = (PartofthroughoutControl) ptDao.findByFlowName(objectName[i].trim()).iterator().next();
			
			control.setStatus("0");
			ptDao.update(control);
		}		
		
		pageControlList = ptDao.findAllByPage(page, rowsPerPage);  
        totalPage = ptDao.getTotalPage(rowsPerPage);  
        planNum = ptDao.getTotalNum(); 
        		
		return SUCCESS;
	}	
}
