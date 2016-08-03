package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Appraise;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.pojo.Ap;
import com.wxpt.site.service.AppraiseService;
import com.wxpt.site.service.MemberService;
import com.wxpt.site.service.ProductService;
@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
@SuppressWarnings("serial")
public class AppraiseAction extends ParentAction{
	AppraiseService appraiseService;
	private int page;
	private int rows;
	private String appraiseId;
	int count;
	boolean f;
	JSONArray jsonls;
	int appId;
	String huifuneirong;
	Appraise app;
	TimeUtil time;
	MemberService memberService;
	ProductService productService;
	Member member;
	Product product;
	List<Appraise> apList =new ArrayList<Appraise>();
	List<Ap> aplist =new ArrayList<Ap>();
	GetCookie cookies= new GetCookie();
	int enterId=cookies.getCookie();
	String sql="SELECT * FROM wxpt"+enterId+".appraise";
	
	//查询订单
	public void getList(){
		apList=appraiseService.getAll(sql, page, rows);
		if(apList==null){
			
		}else{
		
		for(int i=0;i<apList.size();i++){
			Ap ap=new Ap();
			ap.setAppraiseId(apList.get(i).getAppraiseId());
			member=memberService.jiedong(enterId,apList.get(i).getMember().getMemberId());
			if(member==null){
				ap.setMember("");
				ap.setPhone("");
				ap.setAddress("");
			}
			ap.setMember(member.getUsername());
			String sql="SELECT * FROM wxpt"+enterId+".product WHERE `product_id`="+apList.get(i).getProduct().getProductId();
			product= productService.getProduct(sql).get(0);
			if(product==null){
				ap.setProduct("");
			}
			ap.setProduct(product.getProductName());
			ap.setAppraiseContent(apList.get(i).getAppraiseContent());
			ap.setAppraiseTime(apList.get(i).getAppraiseTime());
			ap.setAppraiseAdd(apList.get(i).getAppraiseAdd());
			ap.setAppraiseAddTime(apList.get(i).getAppraiseAddTime());
			ap.setMoney(product.getPrice()+"");
			ap.setPhone(member.getPhone());
			ap.setAddress(member.getAddress());
			aplist.add(ap);
		}
		//String sql2="SELECT * FROM wxpt"+enterId+".appraise";
		count=appraiseService.getCount(sql);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonls = JSONArray.fromObject(aplist,jsonConfig);
			String  json = jsonls.toString();
			System.out.println(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
	}
	//查询所有
	public String getAll(){
		//String sql="SELECT * FROM wxpt"+enterId+".appraise";
		sql+=" where `appraise_id`="+appId;
		app=appraiseService.getAll2(sql).get(0);
		return "all";
		
	}
	//验证是否回复过
	public void yanzheng(){
		String value="";
		try {
			sql+=" where `appraise_id`="+appId;
			app=appraiseService.getAll2(sql).get(0);
			System.out.println(app.getAppraiseHuifu());
			if(app.getAppraiseHuifu()==null||app.getAppraiseHuifu()==""){
				value="1";//可以恢复
			}else{
				value="0";//可以恢复
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				
				PrintWriter out = ServletActionContext.getResponse().getWriter();
				
				out.print(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	//更新 回复内容
public String update(){
	sql+=" where `appraise_id`="+appId;
	System.out.println(huifuneirong);
	System.out.println(appId);
	String sql2="UPDATE wxpt"+enterId+".appraise SET `appraise_huifu`='"+huifuneirong+"',`appraise_huifu_time`='"+time.getTime()+"' WHERE `appraise_id`="+appId;
	appraiseService.update(sql2);
	app=appraiseService.getAll2(sql).get(0);
	return "all";
	
}	
	//删除
	public String delete(){
		String[] appId=appraiseId.split(",");
		System.out.println(appraiseId);
		for(int i=0;i<appId.length;i++){
			String sql="DELETE FROM wxpt"+enterId+".appraise WHERE `appraise_id`="+appId[i];
			appraiseService.delete(sql);
		}
		return "success";
	}
	public AppraiseService getAppraiseService() {
		return appraiseService;
	}
	public void setAppraiseService(AppraiseService appraiseService) {
		this.appraiseService = appraiseService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	public String getAppraiseId() {
		return appraiseId;
	}
	public void setAppraiseId(String appraiseId) {
		this.appraiseId = appraiseId;
	}
	public List<Appraise> getApList() {
		return apList;
	}

	public void setApList(List<Appraise> apList) {
		this.apList = apList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<Ap> getAplist() {
		return aplist;
	}

	public void setAplist(List<Ap> aplist) {
		this.aplist = aplist;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public Appraise getApp() {
		return app;
	}
	public void setApp(Appraise app) {
		this.app = app;
	}
	public String getHuifuneirong() {
		return huifuneirong;
	}
	public void setHuifuneirong(String huifuneirong) {
		this.huifuneirong = huifuneirong;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	
}
