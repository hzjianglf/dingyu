	package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ibm.hibernate.BusinessService;
import com.ibm.hibernate.InterfaceService;
import com.ibm.hibernate.InterfaceServiceDAO;
import com.opensymphony.xwork2.ActionSupport;

public class MappingOperations extends ActionSupport {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int rowsPerPage = 20;// 每页显示几条  
    private int page = 1; // 默认当前页  
    private int totalPage;// 总共多少页  
    private int planNum;// 总过多少条 

	private String servicename;
	private String flowname;
	private String operation;
	private String mappingname;
	private String description;
	private String selectedItemIds;	
	private String result;	
	
	List<InterfaceService> pageOperationList = new ArrayList<InterfaceService>();	
	InterfaceServiceDAO dao = new InterfaceServiceDAO();

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

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	
	public String getFlowname() {
		return flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMappingname() {
		return mappingname;
	}

	public void setMappingname(String mappingname) {
		this.mappingname = mappingname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public List<InterfaceService> getPageOperationList() {
		return pageOperationList;
	}

	public void setPageOperationList(List<InterfaceService> pageOperationList) {
		this.pageOperationList = pageOperationList;
	}

	/**
	 * @return
	 */
	public String list() {
				
        pageOperationList = dao.findOperationByPage(servicename, page, rowsPerPage);  
        totalPage = dao.getOperationTotalPage(servicename, rowsPerPage);  
        planNum = dao.getOperationNum(servicename);  		
		
		return SUCCESS;
	}
	
	public String createoperation() {		  		
		
		return SUCCESS;
	}

	public String submitoperation() {
		InterfaceService service = new InterfaceService();
		UUID uuid = UUID.randomUUID();
		service.setUuid(uuid.toString());
		service.setServicename(servicename);
		service.setOperation(operation);
		service.setMappingname(mappingname);
		service.setDescription(description);		
		dao.save(service);		

        pageOperationList = dao.findOperationByPage(servicename, page, rowsPerPage);  
        totalPage = dao.getOperationTotalPage(servicename, rowsPerPage);  
        planNum = dao.getOperationNum(servicename);  	
        
		return SUCCESS;
	}
	
	public String deleteoperation() {		
		if (selectedItemIds != null ) {			
			String objectName[] = selectedItemIds.split(", ");
			for(int i=0; i < objectName.length; i++) {				
				InterfaceService service = new InterfaceService();
				service.setUuid(objectName[i]);
				dao.delete(service);
			}	
			
	        pageOperationList = dao.findOperationByPage(servicename, page, rowsPerPage);  
	        totalPage = dao.getOperationTotalPage(servicename, rowsPerPage);  
	        planNum = dao.getOperationNum(servicename);  
	        
	        result = "删除成功！";
		} else {
			result = "没有任何项目选中！";
		}			
		
		return SUCCESS;
	}
	
	public String modifyoperation() {
		
		if (selectedItemIds != null) {
			String uuid[] = selectedItemIds.split(", ");
			for(int i=0; i < uuid.length; i++) {
				InterfaceService opeItem = dao.findById(uuid[i]);
				pageOperationList.add(opeItem);
			}
			
		} else {
			result = "没有选中任何项目！";
			
	        pageOperationList = dao.findOperationByPage(servicename, page, rowsPerPage);  
	        totalPage = dao.getOperationTotalPage(servicename, rowsPerPage);  
	        planNum = dao.getOperationNum(servicename); 
	        
			return ERROR;
		}
        
		return SUCCESS;
	}

	public String submitmodifyoperation() {
		String uuidItems[] = selectedItemIds.split(", ");
		String operationItems[] = operation.split(", ");
		String mappingnameItems[] = mappingname.split(", ");
		String descriptionItems[] = description.split(", ");		
		
		for(int i=0; i < uuidItems.length; i++) {
			InterfaceService ope = new InterfaceService();
			ope.setServicename(servicename);
			ope.setUuid(uuidItems[i]);
			ope.setOperation(operationItems[i]);
			ope.setMappingname(mappingnameItems[i]);
			try {
				ope.setDescription(descriptionItems[i]);
			} catch (Exception e) {
				ope.setDescription("");
			}
			dao.update(ope);
		}
	
        pageOperationList = dao.findOperationByPage(servicename, page, rowsPerPage);  
        totalPage = dao.getOperationTotalPage(servicename, rowsPerPage);  
        planNum = dao.getOperationNum(servicename);
        
		return SUCCESS;
	}
	
	public String cancelmodifyoperation() {
        pageOperationList = dao.findOperationByPage(servicename, page, rowsPerPage);  
        totalPage = dao.getOperationTotalPage(servicename, rowsPerPage);  
        planNum = dao.getOperationNum(servicename); 
        
        return SUCCESS;
	}
	
}