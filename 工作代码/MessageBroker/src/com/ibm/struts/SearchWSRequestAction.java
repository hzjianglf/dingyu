package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.hibernate.BusinessServiceDAO;
import com.ibm.hibernate.MonitorNodeItemsDAO;
import com.ibm.hibernate.WebServicesInvoke;
import com.opensymphony.xwork2.ActionSupport;

public class SearchWSRequestAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	MonitorNodeItemsDAO itemDao = new MonitorNodeItemsDAO();
	BusinessServiceDAO svrDao = new BusinessServiceDAO();
	List<WebServicesInvoke> pageWSRequestList = new ArrayList<WebServicesInvoke>(); 	
	List<WebServicesInvoke> totalWSRequestList = new ArrayList<WebServicesInvoke>();
	
	private int rowsPerPage = 20;// 每页显示几条      
    private int page = 1; // 默认当前页    
    private int totalPage = 0;// 总共多少页    
    private int planNum = 0;// 总过多少条         
    
    List<String> serviceList = new ArrayList<String>();    
    
    private String selectedService;
    private String startdate;
    private String starttime;
    private String enddate;
    private String endtime;
    private String result;
    
    public List<WebServicesInvoke> getTotalWSRequestList() {
		return totalWSRequestList;
	}

	public void setTotalWSRequestList(List<WebServicesInvoke> totalWSRequestList) {
		this.totalWSRequestList = totalWSRequestList;
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

	public List<WebServicesInvoke> getPageWSRequestList() {
		return pageWSRequestList;
	}

	public void setPageWSRequestList(List<WebServicesInvoke> pageWSRequestList) {
		this.pageWSRequestList = pageWSRequestList;
	}

	public List<String> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<String> serviceList) {
		this.serviceList = serviceList;
	}

	public String getSelectedService() {
		return selectedService;
	}

	public void setSelectedService(String selectedService) {
		this.selectedService = selectedService;
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
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return
	 */
	public String execute() {
		
//		if (selectedService == null || selectedService.equalsIgnoreCase("")) {
//			result =  "业务服务名称不能为空，请添加接口映射！";
//		} else {
//			String flowname = svrDao.findMessageFlow(selectedService);
			String startpoint = startdate + " " + starttime.substring(1);		
			String endpoint = enddate + " " + endtime.substring(1);

			System.out.println("Starting 1 getWSProvide");
			pageWSRequestList = itemDao.getWSRequest(startpoint, endpoint, page, rowsPerPage);
			System.out.println("Starting 2 getWSProvideTotalPage");
	        totalPage = itemDao.getWSRequestTotalPage(startpoint, endpoint, rowsPerPage);
	        System.out.println("Starting 3 getWSProvideNum");
	        planNum = itemDao.getWSRequestNum(startpoint, endpoint);  
	        System.out.println("Starting 4 getWSTotalProvide");
	        totalWSRequestList = itemDao.getWSTotalRequest(startpoint, endpoint);
//		}
		
		return SUCCESS;
	}
	
	public String display() {
//		serviceList = svrDao.findServices();		
//		if (serviceList.size() == 0) {
//			result =  "业务服务名称不能为空，请添加接口映射！";
//			return ERROR;
//		}
		
		return SUCCESS;
	}
	
	
}