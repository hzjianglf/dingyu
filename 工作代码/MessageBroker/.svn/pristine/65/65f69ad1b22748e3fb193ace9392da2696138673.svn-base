package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.hibernate.MonitorMain;
import com.ibm.hibernate.MonitorMainDAO;
import com.opensymphony.xwork2.ActionSupport;

public class SearchFailureAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	MonitorMainDAO dao = new MonitorMainDAO();	
    List<MonitorMain> pageFailureList = new ArrayList<MonitorMain>();  
    
    private int rowsPerPage = 20;// 每页显示几条  
    private int page = 1; // 默认当前页  
    private int totalPage;// 总共多少页  
    private int planNum;// 总过多少条 
	
	public List<MonitorMain> getPageFailureList() {
		return pageFailureList;
	}

	public void setPageFailureList(List<MonitorMain> pageFailureList) {
		this.pageFailureList = pageFailureList;
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
        totalPage = dao.getFailureTotalPage(rowsPerPage);  
        planNum = dao.getFailureNum();  
        
        if (page >= totalPage) {
        	pageFailureList = dao.findByResult("1", totalPage, rowsPerPage);  
        	page = totalPage;
        } else {
        	pageFailureList = dao.findByResult("1", page, rowsPerPage);
        }
;        
		return SUCCESS;
	}
}