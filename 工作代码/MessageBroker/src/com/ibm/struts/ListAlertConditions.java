package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.alert.StartAlert;
import com.ibm.hibernate.AlertCondition;
import com.ibm.hibernate.AlertConditionDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ListAlertConditions extends ActionSupport {

	private static final Logger log = LoggerFactory.getLogger(ListAlertConditions.class);
	
	private static final long serialVersionUID = 1L;
	AlertConditionDAO dao = new AlertConditionDAO();	
	List<AlertCondition> pageAlertConditionList = new ArrayList<AlertCondition>();  	
	private int rowsPerPage = 20;// ÿҳ��ʾ����  	  
    private int page = 1; // Ĭ�ϵ�ǰҳ    
    private int totalPage;// �ܹ�����ҳ    
    private int planNum;// �ܹ�������     
    private String alertname; 
    private String flowname;
    private String conditionitem;    
    private int numberitem;
    private int interval;
    private String notice;
    private String email;
    private String mqtopic;
    private String description; 
    private String result;
    private String status;   
    private String selectedItemIds;
    
	public String getSelectedItemIds() {
		return selectedItemIds;
	}

	public void setSelectedItemIds(String selectedItemIds) {
		this.selectedItemIds = selectedItemIds;
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

	public int getNumberitem() {
		return numberitem;
	}

	public void setNumberitem(int numberitem) {
		this.numberitem = numberitem;
	}

	public String getAlertname() {
		return alertname;
	}

	public void setAlertname(String alertname) {
		this.alertname = alertname;
	}

	public String getConditionitem() {
		return conditionitem;
	}

	public void setConditionitem(String conditionitem) {
		this.conditionitem = conditionitem;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMqtopic() {
		return mqtopic;
	}

	public void setMqtopic(String mqtopic) {
		this.mqtopic = mqtopic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<AlertCondition> getPageAlertConditionList() {
		return pageAlertConditionList;
	}

	public void setPageAlertConditionList(
			List<AlertCondition> pageAlertConditionList) {
		this.pageAlertConditionList = pageAlertConditionList;
	}

	/**
	 * @return
	 */
	public String list() {
		pageAlertConditionList = dao.findAlertConditionByPage(page, rowsPerPage);  
        totalPage = dao.getAlertConditionTotalPage(rowsPerPage);  
        planNum = dao.getAlertConditionNum();
		
		return SUCCESS;
	}
	
	public String createalert() {
		
		return SUCCESS;
	}
	
	public String savealert() {
		
		AlertCondition alertCondition = new AlertCondition();
		alertCondition.setAlertname(alertname);
		alertCondition.setFlowname(flowname);
		alertCondition.setConditionitem(conditionitem);
		alertCondition.setNumberitem(new Integer(numberitem));
		alertCondition.setNotice(notice);
		alertCondition.setEmail(email);
		alertCondition.setMqtopic(mqtopic);
		alertCondition.setInterval(interval);
		alertCondition.setDescription(description);
		alertCondition.setStatus("0");
		
		if (dao.save(alertCondition)) {
			pageAlertConditionList = dao.findAlertConditionByPage(page, rowsPerPage);  
	        totalPage = dao.getAlertConditionTotalPage(rowsPerPage);  
	        planNum = dao.getAlertConditionNum();
	        
	        result = "�澯���� " + alertname + " �����ɹ���";
	        log.info(result);
			
			return SUCCESS;
		} else {
			result = "����ʧ�ܣ��澯�������� " + alertname + " �ظ���";
			log.error(result);
			
			return ERROR;
		}
					
	}	
	
	public String delete() {	
		String flag = SUCCESS;
		
		String alertNames[] = selectedItemIds.split(", ");
		for (int i=0; i< alertNames.length;i++) {
			AlertCondition alertCondition = new AlertCondition();
			alertCondition = (AlertCondition) dao.findAlertConditionByName(alertNames[i]).iterator().next();
			if (alertCondition.getStatus().equalsIgnoreCase("1")) {
				result = "�澯  '" + alertNames[i] + "' �Ѿ����������ܱ�ɾ����";
		        log.info(result);
		        
		        flag = ERROR;
			} else {
				dao.delete(alertCondition);
				
				result = "�澯 " + alertNames[i] + " ������ɾ���ɹ���";
		        log.info(result);
			}							
		}
		
		pageAlertConditionList = dao.findAlertConditionByPage(page, rowsPerPage);  
        totalPage = dao.getAlertConditionTotalPage(rowsPerPage);  
        planNum = dao.getAlertConditionNum(); 
        
		return flag;
	}
	
	public String startalert() {
		String filePath = ServletActionContext.getServletContext().getRealPath("/config") + "/Alert.properties";
		
		String objectName[] = selectedItemIds.split(", ");
		for(int i=0; i < objectName.length; i++) {	
			AlertCondition condition = new AlertCondition();
			condition = (AlertCondition) dao.findAlertConditionByName(objectName[i]).iterator().next();
			
			Thread run = new Thread(new StartAlert(condition, filePath));
			run.start();
			
			condition.setStatus("1");
			dao.update(condition);
		}
		
		pageAlertConditionList = dao.findAlertConditionByPage(page, rowsPerPage);  
        totalPage = dao.getAlertConditionTotalPage(rowsPerPage);  
        planNum = dao.getAlertConditionNum(); 
        
		return SUCCESS;
	}
	
	public String stopalert() {
		
		String objectName[] = selectedItemIds.split(", ");
		for(int i=0; i < objectName.length; i++) {	
			AlertCondition condition = new AlertCondition();
			condition = (AlertCondition) dao.findAlertConditionByName(objectName[i]).iterator().next();
			
			condition.setStatus("0");
			dao.update(condition);
		}		
		
		pageAlertConditionList = dao.findAlertConditionByPage(page, rowsPerPage);  
        totalPage = dao.getAlertConditionTotalPage(rowsPerPage);  
        planNum = dao.getAlertConditionNum(); 
        		
		return SUCCESS;
	}
}