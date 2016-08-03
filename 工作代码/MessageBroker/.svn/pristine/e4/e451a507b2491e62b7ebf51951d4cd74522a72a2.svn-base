package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.hibernate.AlertInfo;
import com.ibm.hibernate.AlertInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ListAlertInfo extends ActionSupport {

	private static final long serialVersionUID = 1L;

	AlertInfoDAO dao = new AlertInfoDAO();
	
	List<AlertInfo> pageAlertInfoList = new ArrayList<AlertInfo>();  
	
	private int rowsPerPage = 20;// 每页显示几条  
	  
    private int page = 1; // 默认当前页  
  
    private int totalPage;// 总共多少页  
  
    private int planNum;// 总过多少条 
        
	public List<AlertInfo> getPageAlertInfoList() {
		return pageAlertInfoList;
	}

	public void setPageAlertInfoList(List<AlertInfo> pageAlertInfoList) {
		this.pageAlertInfoList = pageAlertInfoList;
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
	public String list() {
		pageAlertInfoList = dao.findAlertInfoByPage(page, rowsPerPage);  
        totalPage = dao.getAlertInfoTotalPage(rowsPerPage);  
        planNum = dao.getAlertInfoNum();				
		
		return SUCCESS;
	}
}