package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.ibm.hibernate.MonitorMain;
import com.ibm.hibernate.MonitorMainDAO;
import com.opensymphony.xwork2.ActionSupport;

public class SearchCurrentAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	MonitorMainDAO dao = new MonitorMainDAO();	
    List<MonitorMain> pageCurrentList = new ArrayList<MonitorMain>();  
    List<MonitorMain> pagePropertyList = new ArrayList<MonitorMain>();
    
	private int rowsPerPage = 20;// ÿҳ��ʾ����    
    private int page = 1; // Ĭ�ϵ�ǰҳ    
    private int totalPage = 0;// �ܹ�����ҳ    
    private int planNum = 0;// �ܹ�������     
    private String brokername;	
    private String flowname;	
    private String startdate;    
    private String starttime;    
    private String enddate;    
    private String endtime;
	private String title;
	private String message;	
	private String comment1;	
	private String comment2;

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

	public List<MonitorMain> getPagePropertyList() {
		return pagePropertyList;
	}

	public void setPagePropertyList(List<MonitorMain> pagePropertyList) {
		this.pagePropertyList = pagePropertyList;
	}
	
	public String getBrokername() {
		return brokername;
	}

	public void setBrokername(String brokername) {
		this.brokername = brokername;
	}

	public String getFlowname() {
		return flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}


	public List<MonitorMain> getPageCurrentList() {
		return pageCurrentList;
	}

	public void setPageCurrentList(List<MonitorMain> pageCurrentList) {
		this.pageCurrentList = pageCurrentList;
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
		
        totalPage = dao.getCurrentTotalPage(rowsPerPage);  
        planNum = dao.getCurrentNum(); 
        
        if (page >= totalPage) {
        	pageCurrentList = dao.findCurrentByPage(totalPage, rowsPerPage);
        	page = totalPage;
        } else {
        	pageCurrentList = dao.findCurrentByPage(page, rowsPerPage);
        }
		        
		return SUCCESS;
	}
	
	/**
	 * @return
	 */
	public String queryByProperty() {
		String startpoint = startdate + " " + starttime.substring(1);		
		String endpoint = enddate + " " + endtime.substring(1);
				
		if (brokername.equalsIgnoreCase("") && flowname.equalsIgnoreCase("") && startpoint.length() < 19 && endpoint.length() < 19) {
			setTitle("��ѯ��������");
			setMessage("��ѯʧ�ܣ�");
			setComment1("��ѯ�������ܶ�Ϊ��");			
			
			return ERROR;
		} else if ((startpoint.length() < 19 && endpoint.length() < 19) || (startpoint.length() == 19 && endpoint.length() == 19)) {
			pagePropertyList = dao.findPropertyByPage(brokername, flowname, startpoint, endpoint, page, rowsPerPage);		
	        totalPage = dao.getPropertyTotalPage(brokername, flowname, startpoint, endpoint, rowsPerPage);  
	        
	        planNum = dao.getPropertyNum(brokername, flowname, startpoint, endpoint);
	        return SUCCESS;					
		} else {	
			setTitle("��ѯ��������");
			setMessage("��ѯʧ�ܣ�");
			setComment1("��ѯ��ʼʱ���ͽ���ʱ��㲻����һ��Ϊ��");			
			
			return ERROR;
		}		
	}	
}