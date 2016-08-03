package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.hibernate.RouteInfo;
import com.ibm.hibernate.RouteInfoDAO;
import com.ibm.hibernate.TrustedIpaddress;
import com.ibm.hibernate.TrustedIpaddressDAO;
import com.opensymphony.xwork2.ActionSupport;

public class PermitAddress extends ActionSupport {
	
	private int rowsPerPage = 20;// 每页显示几条  	  
    private int page = 1; // 默认当前页    
    private int totalPage;// 总共多少页    
    private int planNum;// 总过多少条    
    
    private String tid;
    private String startip;
    private String endip;
    private String description;
    
    private String result;
    private String selectedItemIds;
    
	TrustedIpaddressDAO tiDao = new TrustedIpaddressDAO();
	List<TrustedIpaddress> pageTrustedList = new ArrayList<TrustedIpaddress>();  
	
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

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getStartip() {
		return startip;
	}

	public void setStartip(String startip) {
		this.startip = startip;
	}

	public String getEndip() {
		return endip;
	}

	public void setEndip(String endip) {
		this.endip = endip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSelectedItemIds() {
		return selectedItemIds;
	}

	public void setSelectedItemIds(String selectedItemIds) {
		this.selectedItemIds = selectedItemIds;
	}

	public List<TrustedIpaddress> getPageTrustedList() {
		return pageTrustedList;
	}

	public void setPageTrustedList(List<TrustedIpaddress> pageTrustedList) {
		this.pageTrustedList = pageTrustedList;
	}

	public String displayTrusted() {
		pageTrustedList = tiDao.findAllByPage(page, rowsPerPage); 
        totalPage = tiDao.getTotalPage(rowsPerPage);  
        planNum = tiDao.getTotalNum();		
		
		return SUCCESS;
	}
	
	public String createTrusted() {
				
		return SUCCESS;
	}

	public String saveTrusted() {		
		if (startip.equalsIgnoreCase("") && endip.equalsIgnoreCase("")) {
			result = "起始IP地址和结束IP地址不能为空！";
			
			return ERROR;
		} else {
			TrustedIpaddress tip = new TrustedIpaddress();
			tip.setStartip(startip);
			tip.setEndip(endip);
			tip.setDescription(description);
			
			if (tiDao.save(tip)) {
				result = "创建成功。";
				
				pageTrustedList = tiDao.findAllByPage(page, rowsPerPage); 
		        totalPage = tiDao.getTotalPage(rowsPerPage);  
		        planNum = tiDao.getTotalNum();	
		        
				return SUCCESS;
			} else {
				result = "创建失败，请重新创建！";
				
				return ERROR;			
			}
		}	
	}
	
	public String deleteTrusted() {
		String flag = SUCCESS;
		
		String selectedNames[] = selectedItemIds.split(", ");
		for (int i=0; i< selectedNames.length;i++) {
			TrustedIpaddress tip = new TrustedIpaddress();
			tip.setTid(selectedNames[i]);
			
			if (tiDao.delete(tip)) {				
				result = "删除成功。";
				
				pageTrustedList = tiDao.findAllByPage(page, rowsPerPage); 
		        totalPage = tiDao.getTotalPage(rowsPerPage);  
		        planNum = tiDao.getTotalNum();		        
		        
			} else {				
				result = "删除失败！";
				flag = ERROR;
			}							
		}
					
		return flag;		
	}
	
	public String modifyTrusted() {
		if (selectedItemIds != null) {
			String tid[] = selectedItemIds.split(", ");
			for(int i=0; i < tid.length; i++) {
				TrustedIpaddress tip = tiDao.findByTid(tid[i]).iterator().next();
				pageTrustedList.add(tip);
			}
			
		} else {
			result = "没有选中任何项目！";
			
			pageTrustedList = tiDao.findAllByPage(page, rowsPerPage); 
	        totalPage = tiDao.getTotalPage(rowsPerPage);  
	        planNum = tiDao.getTotalNum();
	        
			return ERROR;
		}
		
		return SUCCESS;		
	}
	
	public String saveModifyTrusted() {
		String tidItems[] = selectedItemIds.split(", ");
		String startipItems[] = startip.split(", ");
		String endipItems[] = endip.split(", ");
		String descriptionItems[] = description.split(", ");		
		
		for(int i=0; i < tidItems.length; i++) {
			TrustedIpaddress tip = new TrustedIpaddress();
			tip.setTid(tidItems[i]);
			tip.setStartip(startipItems[i]);
			tip.setEndip(endipItems[i]);
			tip.setDescription(descriptionItems[i]);
			tiDao.update(tip);
		}
		
		pageTrustedList = tiDao.findAllByPage(page, rowsPerPage); 
        totalPage = tiDao.getTotalPage(rowsPerPage);  
        planNum = tiDao.getTotalNum();
        
		return SUCCESS;		
	}	
}
