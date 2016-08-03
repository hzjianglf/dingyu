package com.ibm.struts;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.hibernate.MonitorMessageFlow;
import com.ibm.hibernate.MonitorMessageFlowDAO;
import com.opensymphony.xwork2.ActionSupport;

public class SearchDetailsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	MonitorMessageFlowDAO dao = new MonitorMessageFlowDAO();	
    List<MonitorMessageFlow> pageDetailsList = new ArrayList<MonitorMessageFlow>();      
    private String appdatafilepath;    
    private String appdata;
        
    public String getAppdata() {
		return appdata;
	}

	public void setAppdata(String appdata) {
		this.appdata = appdata;
	}

	public String getAppdatafilepath() {
		return appdatafilepath;
	}

	public void setAppdatafilepath(String appdatafilepath) {
		this.appdatafilepath = appdatafilepath;
	}

	private String localtransactionid = null;
    
	public List<MonitorMessageFlow> getPageDetailsList() {
		return pageDetailsList;
	}

	public void setPageDetailsList(List<MonitorMessageFlow> pageDetailsList) {
		this.pageDetailsList = pageDetailsList;
	}

	public String getLocaltransactionid() {
		return localtransactionid;
	}

	public void setLocaltransactionid(String localtransactionid) {
		this.localtransactionid = localtransactionid;
	}

	/**
	 * @return
	 */
	public String execute() {
		pageDetailsList = dao.findByLocaltransactionid(localtransactionid);		

		return SUCCESS;
	}
	
	public String searchcontent(){
		appdata = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + readXml(appdatafilepath);
		
		//setAppdatafilepath(appdatafilepath);
		return SUCCESS;
	}
	
	public String readXml(String xmlPath) {

		// InputStream in=
		// this.getClass().getClassLoader().getResourceAsStream(xmlPath);
		FileInputStream fis = null;
		StringBuffer sb = null;

		// 统一用UTF-8的编码格式
		BufferedReader br;
		try {
			fis = new FileInputStream(xmlPath);

			br = new BufferedReader(new InputStreamReader(fis, "utf-8"));

			String line = null;
			sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}
}