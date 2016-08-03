package com.wxpt.action.site.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Appraise;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.pojo.appraiseUtil;
import com.wxpt.site.service.AppraiseService;
import com.wxpt.site.service.MemberService;
import com.wxpt.site.service.ProductService;


@Results({
	@Result(name = "app", type = "json", params = { "root", "result" }),
	@Result(name = "success", type = "json", params = { "root", "result" }),
})
@ParentPackage("json-default") 
public class AppraiseAction extends ActionSupport{
	@Autowired
	AppraiseService appraiseservice;
	MemberService memberService;
	@Autowired
	ProductService productService;
	private String hql;
	private int page = 1;
	private int pageSize = 5;
	private int startIndex;
	private int listCount = 0;
	private int pageCount = 0;
	private int productId;
	private String sql;
	private int memberId;
	private int orderId;
	private int enterId;
	private List<Appraise> appList;
	private List<appraiseUtil> appUtilList;
	protected JSONArray jsonls;
	private String appContent;
	private String time;
	protected PrintWriter out = null;
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	public AppraiseAction(){
		response.setCharacterEncoding("utf-8");
		memberId = GetCurrentUser.getUserID();
		enterId=GetCurrentUser.getEnterId();
		if(memberId == 0||enterId==0){
			System.out.println("cookie错误！");
		}
	}
	
	public String getAppraise(){
		hql = "select * from wxpt"+enterId+".appraise where product_id = "+productId+" order by appraise_add_time desc";
		pageSize = 5;	
		startIndex = (page-1)*pageSize;
		listCount = appraiseservice.getNumber(hql);
		appList = new ArrayList<Appraise>();
		appUtilList = new ArrayList<appraiseUtil>();
		if (listCount % pageSize == 0) {
			pageCount = listCount / pageSize;
		}
		else {
			pageCount = listCount / pageSize + 1;
		}			
		appList = appraiseservice.getAppraiseByPage(hql, startIndex, pageSize);
		for (int i = 0; i < appList.size(); i++) {
			Product pro = new Product();
			Member mem = new Member();
			Appraise app = new Appraise();
			appraiseUtil  au = new appraiseUtil();
			app = appList.get(i);
			String sql="SELECT * FROM wxpt"+enterId+".product WHERE `product_id`="+app.getProduct().getProductId();
			pro=productService.getProduct(sql).get(0);
			mem=memberService.getOne(app.getMember().getMemberId(),enterId);
			au.setAppraiseAdd(app.getAppraiseAdd());
			au.setAppraiseAddTime(app.getAppraiseAddTime());
			au.setAppraiseContent(app.getAppraiseContent());
			au.setAppraiseHuifu(app.getAppraiseHuifu());
			au.setAppraiseHuifuTime(app.getAppraiseHuifuTime());
			au.setAppraiseId(app.getAppraiseId());
			au.setAppraiseTime(app.getAppraiseTime());
			au.setMemberId(mem.getMemberId());
			au.setUsername(mem.getUsername());
			au.setProductDetails(pro.getProductDetails());
			au.setProductId(pro.getProductId());
			au.setProductName(pro.getProductName());
			au.setListNumber(listCount);
			au.setCurrentPage(page);
			au.setPageCount(pageCount);
			appUtilList.add(au);
		}
		if(appList.size() == 0){
			appraiseUtil  au = new appraiseUtil();
			au.setAppraiseAdd("");
			au.setAppraiseAddTime("");
			au.setAppraiseContent("");
			au.setAppraiseHuifu("");
			au.setAppraiseHuifuTime("");
			au.setAppraiseId(0);
			au.setAppraiseTime("");
			au.setMemberId(0);
			au.setUsername("");
			au.setProductDetails("");
			au.setProductId(0);
			au.setProductName("");
			au.setListNumber(listCount);
			au.setCurrentPage(page);
			au.setPageCount(pageCount);
			appUtilList.add(au);
		}
		try {
			out = response.getWriter();
		} catch (IOException e) {

		}
		jsonls = JSONArray.fromObject(appUtilList);
		System.out.println(jsonls);
		out.print(jsonls);
		out.flush();
		out.close();
		return "app";
	}
	public String sava(){
		time = TimeUtil.getTime();
		sql = "insert into appraise(member_id,product_id,appraise_content,appraise_time,order_id" +
				") values('"+memberId+"','"+productId+"','"+appContent+"','"+time+"','"+orderId+"')";
		int i = appraiseservice.save(sql);
		try {
			out = response.getWriter();
		} catch (IOException e) {

		}
		if(i>0){
			out.print(true);
		}else{
			out.print(false);
		}
		out.flush();
		out.close();
		return "success";
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getAppContent() {
		return appContent;
	}

	public void setAppContent(String appContent) {
		this.appContent = appContent;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
}