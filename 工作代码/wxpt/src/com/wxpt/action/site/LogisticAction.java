package com.wxpt.action.site;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.wxpt.site.entity.Logistics;
import com.wxpt.site.service.AppraiseService;
import com.wxpt.site.service.LogisticService;


@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
public class LogisticAction extends ParentAction{
	JSONArray jsonls;
	int page;
	int rows;
	int count;
	String logisticsId;
	private String payway;
	private String paymoney;
	private int payId;
	LogisticService logisticService;
	AppraiseService appraiseService;
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	List<Logistics> loList=new ArrayList<Logistics>();
	public void getList() {
		String sql="SELECT * FROM wxpt"+enterId+".logistics";
		loList=logisticService.getAll(sql, page, rows);
		count=logisticService.getcount(sql);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			  jsonConfig.setExcludes(new String[] {"orders"});
			jsonls = JSONArray.fromObject(loList,jsonConfig);
			String  json = jsonls.toString();
			System.out.println(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
	}
	//删除
	public String delete(){
		String logId[]=logisticsId.split(",");
		System.out.println(logisticsId);
		for(int i=0;i<logId.length;i++){
			String sql="DELETE FROM wxpt"+enterId+".logistics WHERE `logistics_id`="+logId[i];
			appraiseService.delete(sql);
		}
		return "success";
		
	}
	//添加
	public String save(){
		try {
			String sql="INSERT INTO wxpt"+enterId+".logistics( `logistics_way`, `logistics_price`) VALUES ('"+payway+"',"+paymoney+")";
			logisticService.save(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	//更新
	public String update(){
		String sql="UPDATE `logistics` SET `logistics_way`='"+payway+"',`logistics_price`="+paymoney+" WHERE `logistics_id`="+payId+"";
		logisticService.update(sql,enterId);
		return "success";
	}
	public LogisticService getLogisticService() {
		return logisticService;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public List<Logistics> getLoList() {
		return loList;
	}
	public void setLoList(List<Logistics> loList) {
		this.loList = loList;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getLogisticsId() {
		return logisticsId;
	}
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}
	public AppraiseService getAppraiseService() {
		return appraiseService;
	}
	public void setAppraiseService(AppraiseService appraiseService) {
		this.appraiseService = appraiseService;
	}
	
	public String getPayway() {
		return payway;
	}
	public void setPayway(String payway) {
		this.payway = payway;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	public int getPayId() {
		return payId;
	}
	public void setPayId(int payId) {
		this.payId = payId;
	}
}
