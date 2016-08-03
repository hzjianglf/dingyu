package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.hibernate.MonitorMain;
import com.ibm.hibernate.MonitorMainDAO;
import com.opensymphony.xwork2.ActionSupport;

public class SearchHistoryAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	MonitorMainDAO dao = new MonitorMainDAO();	
	List<MonitorMain> pageMonitorMainList = new ArrayList<MonitorMain>();  
    
    private int rowsPerPage = 20;// 每页显示几条    
    private int page = 1; // 默认当前页    
    private int totalPage;// 总共多少页    
    private int planNum;// 总过多少条 
    
    public List<MonitorMain> getPageMonitorMainList() {
		return pageMonitorMainList;
	}

	public void setPageMonitorMainList(List<MonitorMain> pageMonitorMainList) {
		this.pageMonitorMainList = pageMonitorMainList;
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
    
	/**
	 * @return
	 */
	public String execute() {				        
        totalPage = dao.getMonitorMainTotalPage(rowsPerPage);  
        planNum = dao.getMonitorMainNum();
        
        if (page >= totalPage) {
        	pageMonitorMainList = dao.findMonitorMainByPage(totalPage, rowsPerPage);
        	page = totalPage;
        } else {
        	pageMonitorMainList = dao.findMonitorMainByPage(page, rowsPerPage);
        }
        
		return SUCCESS;
	}
}