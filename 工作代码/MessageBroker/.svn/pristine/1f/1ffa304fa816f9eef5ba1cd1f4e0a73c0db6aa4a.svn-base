package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.hibernate.MonitorMainDAO;
import com.ibm.hibernate.WebServicesInvoke;
import com.opensymphony.xwork2.ActionSupport;

public class SearchWSProvideAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	MonitorMainDAO dao = new MonitorMainDAO();
	List<WebServicesInvoke> pageWSProvideList = new ArrayList<WebServicesInvoke>(); 
	List<WebServicesInvoke> totalWSProvideList = new ArrayList<WebServicesInvoke>();
	
	private int rowsPerPage = 20;// 每页显示几条  
    private int page = 1; // 默认当前页  
    private int totalPage;// 总共多少页  
    private int planNum;// 总过多少条     

    private String startdate;
    private String starttime;
    private String enddate;
    private String endtime;
    
	public List<WebServicesInvoke> getPageWSProvideList() {
		return pageWSProvideList;
	}


	public void setPageWSProvideList(List<WebServicesInvoke> pageWSProvideList) {
		this.pageWSProvideList = pageWSProvideList;
	}

	public List<WebServicesInvoke> getTotalWSProvideList() {
		return totalWSProvideList;
	}

	public void setTotalWSProvideList(List<WebServicesInvoke> totalWSProvideList) {
		this.totalWSProvideList = totalWSProvideList;
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

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return
	 */
	public String execute() {
		String startpoint = startdate + " " + starttime.substring(1);		
		String endpoint = enddate + " " + endtime.substring(1);
		
		
		pageWSProvideList = dao.getWSProvide(startpoint, endpoint, page, rowsPerPage);
		
        totalPage = dao.getWSProvideTotalPage(startpoint, endpoint, rowsPerPage); 
        
        planNum = dao.getWSProvideNum(startpoint, endpoint);  
        
        totalWSProvideList = dao.getWSTotalProvide(startpoint, endpoint);		
		
		return SUCCESS;
	}
	
	public String display() {
		
		
		return SUCCESS;
	}
}