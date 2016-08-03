package com.ibm.struts;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.BusinessService;
import com.ibm.hibernate.BusinessServiceDAO;
import com.ibm.hibernate.InterfaceService;
import com.ibm.hibernate.InterfaceServiceDAO;
import com.ibm.paser.PaserWSDL;
import com.opensymphony.xwork2.ActionSupport;

public class MappingInterfaces extends ActionSupport {
	
	private static final Logger log = LoggerFactory.getLogger(MappingInterfaces.class);
	
	private static final long serialVersionUID = 1L;
	
	private int rowsPerPage = 20;// 每页显示几条  
    private int page = 1; // 默认当前页  
    private int totalPage;// 总共多少页  
    private int planNum;// 总过多少条 
    

	private String selectedItemIds;	
	private String result;	

	private String servicename;
	private String operation;
	private String mappingname;
	private String description;
	

	private String namespace;
	private String endpoint;
	private String flag;
	private String available;
	private String onLine;	
	
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

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getOnLine() {
		return onLine;
	}

	public void setOnLine(String onLine) {
		this.onLine = onLine;
	}


		
	List<InterfaceService> pageServiceList = new ArrayList<InterfaceService>();	
//	BusinessServiceDAO bDao = new BusinessServiceDAO();
	InterfaceServiceDAO iDao = new InterfaceServiceDAO();
	
//	/*****************文件上传*********************/
//	JakartaMultiPartRequest f;
//	private File imgFile;
//	private String imgFileFileName;
//	private String caption;		
//
//	public File getImgFile() {
//		return imgFile;
//	}
//
//	public void setImgFile(File imgFile) {
//		this.imgFile = imgFile;
//	}
//
//	public String getImgFileFileName() {
//		return imgFileFileName;
//	}
//
//	public void setImgFileFileName(String imgFileFileName) {
//		this.imgFileFileName = imgFileFileName;
//	}
//
//	public String getCaption() {
//		return caption;
//	}
//
//	public void setCaption(String caption) {
//		this.caption = caption;
//	}

	/*********************************************/
	
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<InterfaceService> getPageServiceList() {
		return pageServiceList;
	}

	public void setPageServiceList(List<InterfaceService> pageServiceList) {
		this.pageServiceList = pageServiceList;
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

	/**
	 * @return
	 */
	public String list() {
        pageServiceList = iDao.findServiceByPage(page, rowsPerPage);  
        totalPage = iDao.getServiceTotalPage(rowsPerPage);  
        planNum = iDao.getServiceNum();  		
		
		return SUCCESS;
	}
	
	public String createservice() {
		
		return SUCCESS;
	}
	
	public String submitservice() {
		
		if (servicename.equalsIgnoreCase("")) {
			result = "业务服务名不能为空";
			
			return ERROR;
		} 
		if (endpoint.equalsIgnoreCase("")) {
			result = "服务端点URL地址不能为空";
			
			return ERROR;
		} else {
			
			byte []snbytes = servicename.getBytes();  
			int i = snbytes.length; //i为字节长度  
			int j = servicename.length(); //j为字符长度  
			
			if (i == j) {
				InterfaceService service = new InterfaceService();
				
				service.setServicename(servicename);
				service.setAvailable(available);
				service.setFlag(flag);
				service.setMappingname(mappingname);
				service.setNamespace(namespace);
				service.setOnLine(onLine);
				service.setOperation(operation);
				service.setEndpoint(endpoint);
				service.setDescription(description);		
				iDao.save(service);
		
		        pageServiceList = iDao.findServiceByPage(page, rowsPerPage);  
		        totalPage = iDao.getServiceTotalPage(rowsPerPage);  
		        planNum = iDao.getServiceNum();
		        
		        result = "服务创建成功。";
			} else {
				
				result = "请用英文定义服务名！";
				return ERROR;
			}
		}
		
		return SUCCESS;
	}
	
	public String deleteservice() {
		if (selectedItemIds != null ) {			
			String objectName[] = selectedItemIds.split(", ");
			for(int i=0; i < objectName.length; i++) {	
				iDao.deleteByUUID(objectName[i]);			
				
//				InterfaceService item = new InterfaceService();
//				item.setUuid(objectName[i]);
//
//				iDao.delete(item);
			}	
			
	        pageServiceList = iDao.findServiceByPage(page, rowsPerPage);  
	        totalPage = iDao.getServiceTotalPage(rowsPerPage);  
	        planNum = iDao.getServiceNum(); 
	        
	        result = "删除成功！";
		} else {
			result = "没有任何项目选中！";
			
	        pageServiceList = iDao.findServiceByPage(page, rowsPerPage);  
	        totalPage = iDao.getServiceTotalPage(rowsPerPage);  
	        planNum = iDao.getServiceNum(); 			
		}
			
		return SUCCESS;
	}
	
	public String startService() {
		if (selectedItemIds != null ) {			
			String objectName[] = selectedItemIds.split(", ");
			for(int i=0; i < objectName.length; i++) {	
				InterfaceService service = new InterfaceService();
				
				service = iDao.findById(objectName[i]);			
				
				service.setOnLine("1");

				iDao.update(service);
			}	
			
	        pageServiceList = iDao.findServiceByPage(page, rowsPerPage);  
	        totalPage = iDao.getServiceTotalPage(rowsPerPage);  
	        planNum = iDao.getServiceNum(); 
	        
	        result = "服务上线成功！";
		} else {
			result = "没有任何项目选中！";
			
	        pageServiceList = iDao.findServiceByPage(page, rowsPerPage);  
	        totalPage = iDao.getServiceTotalPage(rowsPerPage);  
	        planNum = iDao.getServiceNum(); 			
		}
			
		return SUCCESS;
	}	
	
	public String stopService() {
		if (selectedItemIds != null ) {			
			String objectName[] = selectedItemIds.split(", ");
			for(int i=0; i < objectName.length; i++) {	
				InterfaceService service = new InterfaceService();
				
				service = iDao.findById(objectName[i]);			
				
				service.setOnLine("0");

				iDao.update(service);
			}	
			
	        pageServiceList = iDao.findServiceByPage(page, rowsPerPage);  
	        totalPage = iDao.getServiceTotalPage(rowsPerPage);  
	        planNum = iDao.getServiceNum(); 
	        
	        result = "服务下线成功！";
		} else {
			result = "没有任何项目选中！";
			
	        pageServiceList = iDao.findServiceByPage(page, rowsPerPage);  
	        totalPage = iDao.getServiceTotalPage(rowsPerPage);  
	        planNum = iDao.getServiceNum(); 			
		}
			
		return SUCCESS;
	}		

//	public String uploadwsdlfile() {
//		// 服务名不能为空
//		if (servicename.equalsIgnoreCase("") || servicename == null) {
//			result = "服务名称不能为空！";			
//			return ERROR;			
//		}
//		// 消息流不能为空
//		if (flowname.equalsIgnoreCase("") || flowname == null) {
//			result = "消息流名称不能为空！";
//			
//			return ERROR;		
//		}		
//
//		byte []snbytes = servicename.getBytes();  
//		int i = snbytes.length; //i为字节长度  
//		int j = servicename.length(); //j为字符长度  
//		
//		if ( i > j) {			
//			result = "请用英文定义服务名！";
//			return ERROR;
//		}
//		
//		BusinessService service = new BusinessService();
//		service.setServicename(servicename);
//		service.setFlowname(flowname);
//		service.setDescription(description);
//		try {
//			bDao.save(service);
//		} catch(Exception e) {
//			result = "创建服务失败，请重新提交！";
//			return ERROR;
//		}
//		
//		// WSDL路径不能为空
//		if (this.imgFileFileName != null ) {
//			//int byteLen = this.imgFileFileName.getBytes().length;			
//			
//			if (this.imgFileFileName.contains(".wsdl")) {
//				
//				File imageFile = new File(ServletActionContext.getServletContext()
//						.getRealPath("/upload") + "/" + this.imgFileFileName);		
//				
//				copy(this.imgFile, imageFile, imgFile.length());	
//				PaserWSDL paserWsdl = new PaserWSDL();
//				result = paserWsdl.paser(imageFile, servicename);				
//				if (!result.equalsIgnoreCase("Success")) {
//					bDao.delete(service);					
//					return ERROR;
//				}
//			} 
//			
//		} else {
//			result = "WSDL文件路径不能为空!";
//			return ERROR;			
//		}
//		
//        pageServiceList = bDao.findServiceByPage(page, rowsPerPage);  
//        totalPage = bDao.getServiceTotalPage(rowsPerPage);  
//        planNum = bDao.getServiceNum(); 
//        result = "";
//        
//		return SUCCESS;
//	}
	
//	/*****************文件上传*************************************************************************/
//	private void copy(File src, File dst, long length) {
//		try  {
//            InputStream in = null ;
//            OutputStream out = null ;
//             try  {                
//                in = new BufferedInputStream( new FileInputStream(src), new Long(length).intValue());
//                out = new BufferedOutputStream( new FileOutputStream(dst), new Long(length).intValue());
//                 byte [] buffer = new byte [new Long(length).intValue()];
//                 while (in.read(buffer) > 0 )  {
//                    out.write(buffer);
//                } 
//             } finally  {
//                 if ( null != in)  {
//                    in.close();
//                } 
//                  if ( null != out)  {
//                    out.close();
//                } 
//            } 
//         } catch (Exception e)  {
//            log.error(e.getMessage());
//        } 
//
//	}	
}