package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.CheckIn;
import com.wxpt.site.service.ImageService;
@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
public class CheckInAction extends ActionSupport {
	private String fromusername;
	private String tempString;
	private CheckIn checkin ;
	private ImageService imageservice;
	List<CheckIn> checklistTime= null;
	List<CheckIn> checklist;
	List<CheckIn> checklistTime2= null;
	List<CheckIn> checklistTime3= null;
	
	
	private  int enterId;
public String start(){
		
		System.out.println(enterId);
		checklist=imageservice.queryAllChenckByName(enterId,fromusername);
		
		System.out.println(checklist.size());
		if(checklist.size()!=0){
			checkin=checklist.get(0);
		}
		return "check";
	}
	
	public List<CheckIn> getChecklist() {
		return checklist;
	}




	public void setChecklist(List<CheckIn> checklist) {
		this.checklist = checklist;
	}




	public void getJson(){
		HttpServletResponse hsp =ServletActionContext.getResponse();
		PrintWriter out=null;
		try {
			 out= hsp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hsp.setContentType("text/html;charset=utf-8");
		

		//List<CheckIn> list= imageservice.queryAllChenckByName("oIMbDjhOnycSf4RJ8L91Zn6_Ic6A");
		List<CheckIn> list= imageservice.queryAllChenckByName(enterId,fromusername);		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<list.size();i++){
			sb.append("");
			sb.append(list.get(i).getChecTime().substring(0,4));
			sb.append(list.get(i).getChecTime().substring(5,7));
			sb.append(list.get(i).getChecTime().substring(8,10));
		
			if(i!=(list.size()-1)){
				sb.append(",");
			}
				
		}
	
		tempString = sb.toString();
		System.out.println(tempString);
		out.write(tempString);
	}


	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public ImageService getImageservice() {
		return imageservice;
	}

	public void setImageservice(ImageService imageservice) {
		this.imageservice = imageservice;
	}

	public CheckIn getCheckin() {
		return checkin;
	}

	public void setCheckin(CheckIn checkin) {
		this.checkin = checkin;
	}

	public String getTempString() {
		return tempString;
	}

	public void setTempString(String tempString) {
		this.tempString = tempString;
	}

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}
	
	
}
