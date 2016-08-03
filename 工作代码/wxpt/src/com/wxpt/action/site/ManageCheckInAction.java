package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


import com.wxpt.site.entity.CheckIn;
import com.wxpt.site.entity.Imageroll;
import com.wxpt.site.entity.Prize;
import com.wxpt.site.entity.Question;
import com.wxpt.site.service.ImageService;

@SuppressWarnings("serial")
@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
public class ManageCheckInAction extends ParentAction{
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	GetCookie cookies=new GetCookie();
	int enterId=cookies.getCookie();
	Imageroll image;
	ImageService imageservice;
	private List<CheckIn> checkList;
	private List<Prize> prizeList=null;
	String ids;
	int result=0;
	String username;
	public String prizeId;
	
	
	private String rows;//每页显示的记录数  
  	private String page;//当前第几页  
  	private int listCount;	//总数据条数
  	private int pageCount;//总页数
 	private int currentpage=1;//当前页
 	
	
	public String  queryAll(){
		 response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;		
			
			try {
				out = response.getWriter();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			//分页	
			int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); //当前第几页 //每页显示条数  
	        int number = Integer.parseInt((rows == null || rows == "0") ? "15":rows);//每页显示条数
	        listCount=imageservice.queryAllChenckin(enterId).size();
	        if(listCount<((intPage-1)*number)){
	        	intPage=1;
	        }
	         int   start=(intPage-1)*number;
		
	      
	checkList=imageservice.queryAllChenckin(enterId, start, number);
	//把集合转化为json
	if(checkList==null||checkList.size()==0){
		/*super.out.print("");*/
		super.out.print("{\"total\":"+0+",\"rows\":" + "" + "}");
	}else{
		
            JsonConfig jsonConfig=new JsonConfig(); 			
			JSONArray jsonArrayFromList = JSONArray.fromObject(checkList,jsonConfig); 	
			System.out.println(jsonArrayFromList.toString());
			super.out.print("{\"total\":"+listCount+",\"rows\":" + jsonArrayFromList.toString() + "}");
				 
	}
	super. out.flush();
	super.out.close();
		return SUCCESS; 
	}
	
	
	
	
	
	//查看
	
	public String queryAllByName(){
		 response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;		
			
			try {
				out = response.getWriter();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			//分页	
			int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); //当前第几页 //每页显示条数  
	        int number = Integer.parseInt((rows == null || rows == "0") ? "15":rows);//每页显示条数
	        listCount=imageservice.queryAllChenckByName(username, enterId).size();
	    	System.out.println(listCount+":::::::::::::");
	        if(listCount<((intPage-1)*number)){
	        	intPage=1;
	        }
	         int   start=(intPage-1)*number;
	
	      
	checkList=imageservice.queryAllChenckinByName(enterId, username, start, number);
	//把集合转化为json
	if(checkList==null||checkList.size()==0){
		out.print("");
	}else{
		
         JsonConfig jsonConfig=new JsonConfig(); 			
			JSONArray jsonArrayFromList = JSONArray.fromObject(checkList,jsonConfig); 	
			System.out.println(jsonArrayFromList.toString());
			super.out.print("{\"total\":"+listCount+",\"rows\":" + jsonArrayFromList.toString() + "}");
			super. out.flush();
			super.out.close();	 
	}
		
		
		return SUCCESS;
	}
	
	//shanchu
	 public String deleteCheckin(){
		 
		 ids=ids.substring(0, ids.lastIndexOf(","));
		 //执行删除操作
		 System.out.println(ids);
		 
		 try {
				String quetionIDs[] = ids.split(",");
				for (int i = 0; i < quetionIDs.length; i++) {
					CheckIn checkIn = imageservice.queryByCheckinId(enterId,Integer
							.parseInt(quetionIDs[i]));
					 imageservice.deleteCheckin(enterId, checkIn.getCheckId());
					
				}
				HttpServletResponse hs=ServletActionContext.getResponse();
	    		  hs.setContentType(
	 				     "text/html;charset=utf-8");
	    		 PrintWriter out = hs.getWriter();
	    		result=1;
				out.print(result);
			} catch (Exception e) {
				// TODO: handle exception
				out.print(result);
			}
		 out.print(result);
			return SUCCESS;
	 }
	
	
	 
	 //转盘抽奖幸运粉丝
	 public String getPrizeLucker(){
		 response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;		
			
			try {
				out = response.getWriter();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			//分页	
			int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); //当前第几页 //每页显示条数  
	        int number = Integer.parseInt((rows == null || rows == "0") ? "15":rows);//每页显示条数
	        listCount=imageservice.queryPrizeAll(enterId).size();
	        if(listCount<((intPage-1)*number)){
	        	intPage=1;
	        }
	         int   start=(intPage-1)*number;
		
	      
	      prizeList=imageservice.queryByNamePage(enterId, start, number);
	//把集合转化为json
	if(prizeList==null||prizeList.size()==0){
		/*super.out.print("");*/
		super.out.print("{\"total\":"+0+",\"rows\":" + "" + "}");
	}else{
		
         JsonConfig jsonConfig=new JsonConfig(); 			
			JSONArray jsonArrayFromList = JSONArray.fromObject(prizeList,jsonConfig); 
			String str =jsonArrayFromList.toString();	
			//System.out.println(str.indexOf(",\"productState\":2"));
			str=str.replaceAll(",\"isstate\":0", ",\"isstate\":\"未领取\"");
			str=str.replaceAll(",\"isstate\":1", ",\"isstate\":\"已领取\"");
			
			
			str=str.replaceAll(",\"prizegrade\":-1", ",\"prizegrade\":\"未中奖\"");
			str=str.replaceAll(",\"prizegrade\":1", ",\"prizegrade\":\"一等奖\"");
			str=str.replaceAll(",\"prizegrade\":2", ",\"prizegrade\":\"二等奖\"");
			str=str.replaceAll(",\"prizegrade\":3", ",\"prizegrade\":\"三等奖\"");
			str=str.replaceAll(",\"prizegrade\":4", ",\"prizegrade\":\"四等奖\"");
			str=str.replaceAll(",\"prizegrade\":5", ",\"prizegrade\":\"五等奖\"");
			str=str.replaceAll(",\"prizegrade\":6", ",\"prizegrade\":\"六等奖\"");
			str=str.replaceAll(",\"prizegrade\":7", ",\"prizegrade\":\"七等奖\"");
			str=str.replaceAll(",\"prizegrade\":8", ",\"prizegrade\":\"八等奖\"");	
			str=str.replaceAll(",\"prizegrade\":9", ",\"prizegrade\":\"九等奖\"");
			//str=str.replaceAll(",\"prizegrade\":10", ",\"prizegrade\":\"十等奖\"");
			

			System.out.println(str);
			super.out.print("{\"total\":"+listCount+",\"rows\":" + str.toString() + "}");
				 
	}
			super. out.flush();
			super.out.close();
				return SUCCESS; 
	 }
	 
	 
	 
	 public String DelPrizeLucker(){
		 ids=ids.substring(0, ids.lastIndexOf(","));
		 //执行删除操作
		 System.out.println(ids);
		 
		 try {
				String quetionIDs[] = ids.split(",");
				for (int i = 0; i < quetionIDs.length; i++) {
					Prize prize = imageservice.queryByPrizeId(enterId,Integer
							.parseInt(quetionIDs[i]));
					 imageservice.deletePrize(enterId,prize.getPrizeId())	;	
				}
				HttpServletResponse hs=ServletActionContext.getResponse();
	    		  hs.setContentType(
	 				     "text/html;charset=utf-8");
	    		 PrintWriter out = hs.getWriter();
	    		result=1;
				out.print(result);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.print(result);
			}
		   out.print(result);
		   return SUCCESS; 
	 }
	 
	 
	 //修改设置已领取
public void updatePrizeLucker(){
	System.out.println(prizeId);
	try {
		int prizeid=Integer.parseInt(prizeId);
		imageservice.updatePrizeisState(enterId, prizeid, 1);
		HttpServletResponse hs=ServletActionContext.getResponse();
		  hs.setContentType(
			     "text/html;charset=utf-8");
		 PrintWriter out;	
		out = hs.getWriter();
		result=1;
		out.print(result);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		result=1;
		out.print(result);
	}
	
	
}
	 
	


	public List<Prize> getPrizeList() {
		return prizeList;
	}





	public void setPrizeList(List<Prize> prizeList) {
		this.prizeList = prizeList;
	}





	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public List<CheckIn> getCheckList() {
		return checkList;
	}




	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}


	public void setCheckList(List<CheckIn> checkList) {
		this.checkList = checkList;
	}




	public String getRows() {
		return rows;
	}




	public void setRows(String rows) {
		this.rows = rows;
	}




	public String getPage() {
		return page;
	}




	public void setPage(String page) {
		this.page = page;
	}




	public int getListCount() {
		return listCount;
	}




	public void setListCount(int listCount) {
		this.listCount = listCount;
	}




	public int getPageCount() {
		return pageCount;
	}




	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}




	public int getCurrentpage() {
		return currentpage;
	}




	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}




	public String getPrizeId() {
		return prizeId;
	}





	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}





	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public Imageroll getImage() {
		return image;
	}
	public void setImage(Imageroll image) {
		this.image = image;
	}
	public ImageService getImageservice() {
		return imageservice;
	}
	public void setImageservice(ImageService imageservice) {
		this.imageservice = imageservice;
	}
	
	
}
