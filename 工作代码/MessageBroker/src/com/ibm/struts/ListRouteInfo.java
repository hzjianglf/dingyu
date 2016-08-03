package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.hibernate.InterfaceService;
import com.ibm.hibernate.RouteInfo;
import com.ibm.hibernate.RouteInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ListRouteInfo extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	RouteInfoDAO riDao = new RouteInfoDAO();
	List<RouteInfo> pageRouteList = new ArrayList<RouteInfo>(); 
	
	private int rowsPerPage = 20;// 每页显示几条  	  
    private int page = 1; // 默认当前页    
    private int totalPage;// 总共多少页    
    private int planNum;// 总过多少条    
	
    private String source;
    private String destination;
    private String description;
    
    private String result;
    private String selectedItemIds;
    
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

    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RouteInfo> getPageRouteList() {
		return pageRouteList;
	}

	public void setPageRouteList(List<RouteInfo> pageRouteList) {
		this.pageRouteList = pageRouteList;
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

	/**
	 * manage the route information
	 * 
	 */
	public String list() {
    	
		pageRouteList = riDao.findAllByPage(page, rowsPerPage); 
        totalPage = riDao.getTotalPage(rowsPerPage);  
        planNum = riDao.getTotalNum();
        
    	return SUCCESS;
    }
	
	public String add() {
		
		return SUCCESS;
	}
	
	public String save() {
		RouteInfo info = new RouteInfo();
		info.setSource(source);
		info.setDestination(destination);
		info.setDescription(description);

		if (riDao.save(info)) {			
			pageRouteList = riDao.findAllByPage(page, rowsPerPage); 
	        totalPage = riDao.getTotalPage(rowsPerPage);  
	        planNum = riDao.getTotalNum();
	        
			result = "路由信息创建成功。";
			return SUCCESS;
		} else {
			result = "路由信息创建失败！";
			return ERROR;			
		}
	}
	
	public String modify() {
		if (selectedItemIds != null) {
			String source[] = selectedItemIds.split(", ");
			for(int i=0; i < source.length; i++) {
				RouteInfo info = riDao.findBySource(source[i]).iterator().next();
				pageRouteList.add(info);
			}
			
		} else {
			result = "没有选中任何项目！";
			
			pageRouteList = riDao.findAllByPage(page, rowsPerPage);  
	        totalPage = riDao.getTotalPage(rowsPerPage);  
	        planNum = riDao.getTotalNum();
	        
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String delete() {
		String flag = SUCCESS;
		
		String selectedNames[] = selectedItemIds.split(", ");
		for (int i=0; i< selectedNames.length;i++) {
			RouteInfo info = new RouteInfo();
			info.setSource(selectedNames[i]);
			
			if (riDao.delete(info)) {				
				result = "删除成功。";
				
				pageRouteList = riDao.findAllByPage(page, rowsPerPage); 		
		        totalPage = riDao.getTotalPage(rowsPerPage);  
		        planNum = riDao.getTotalNum();		        
		        
			} else {				
				result = "删除失败！";
				flag = ERROR;
			}							
		}
					
		return flag;
	}
	
	public String savemodify() {
		String sourceItems[] = selectedItemIds.split(", ");
		String destinationItems[] = destination.split(", ");		
		String descriptionItems[] = description.split(", ");		
		
		for(int i=0; i < sourceItems.length; i++) {
			RouteInfo info = new RouteInfo();
			info.setSource(sourceItems[i]);
			info.setDestination(destinationItems[i]);
			info.setDescription(descriptionItems[i]);

			riDao.update(info);
		}
	
		pageRouteList = riDao.findAllByPage(page, rowsPerPage); 		
        totalPage = riDao.getTotalPage(rowsPerPage);  
        planNum = riDao.getTotalNum();
        
		return SUCCESS;		
	}
}
