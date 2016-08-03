package com.whcyz.controller;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.model.Account;
import com.whcyz.model.Company;
import com.whcyz.model.Person;
import com.whcyz.permission.Permission;
import com.whcyz.permission.PermissionInterceptor;
import com.whcyz.permission.UnCheck;
import com.whcyz.service.impl.CompanyService;
import com.whcyz.service.impl.PersonService;
import com.whcyz.util.PinYinUtil;
/**
 * 创业公司controller
 * @author 牟孟孟
 *
 */
@Before(PermissionInterceptor.class)
public class CompanyController extends BaseController {
	/**
	 * 首页
	 */
	@Before(NoUrlPara.class)
	@UnCheck
	public void index() {
		Page<Company> companyPage=(Page<Company>) CompanyService.me.paginateWithExtras(getParaToInt(0,1), 12, "id,name,imgUrl", " order by rank desc ");
		setAttr("companyInfo", companyPage);//将一个属性存储在此请求(companyInfo名称字符串指定属性的名称companyPage值对象存储)
		render("index.jsp");
	}
	@UnCheck
	public void page() {
		index();
	}
	/**
	 * 读取关联信息
	 */
	@UnCheck
	public void relinfo() {
		Integer id=getParaToInt();
		if(id==null||id<=0){
			renderJsonFail();//返回失败json信息			
			return;
		}
		Company company=CompanyService.me.findById(id,"id,name,imgUrl,stage,industry");
		if(company==null){
			renderJsonFail();//返回失败json信息		
		}else{
			renderJsonData(true, company);//返回json信息
		}
		return;
	
	}
	@UnCheck
	public void autolist() {
		String matchInfo=getPara("matchInfo");
		Integer matchCount=getParaToInt("matchCount",8);
		if(StrKit.isBlank(matchInfo)){
			renderJson(CompanyService.me.getListWithExtras("id,name,pinyin", null,"limit 0,"+matchCount));
		}else{
			renderJson(CompanyService.me.getListWithExtras("id,name,pinyin", "pinyin like '%"+matchInfo+"%' or name like  '%"+matchInfo+"%' ","limit 0,"+matchCount));
		}
	}
	@UnCheck
	public void detail(){
		Integer id=getParaToInt();
		if(id==null||id<=0){
			renderError(404);
		}
		Company company=CompanyService.me.findById(id);
		if(company==null){
			renderError(404);
		}
		setAttr("company", company);//将一个属性存储在此请求
		render("detail.jsp");
		
	}
	@UnCheck
	public void pyindex(){
		String py=getPara();
		if(py==null){
			renderJsonFail("数据读取失败，参数错误！");
			return;
		}
		List<Company> companys=CompanyService.me.getListByPyIndex(py);
		renderJsonData(true, companys);
	
	}
	@ActionKey("companymgr")
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void list(){
		render("list.jsp");
	}
	/**
	 * 进入编辑页面
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void edit(){
		Integer id=getParamModelId();
		if(id!=null&&id>0){
			setAttr("action","update");////将一个属性存储在此请求
			Company company= CompanyService.me.findById(id,"id,name,stage,rank,website,industry,foundTime,address");
			if(company==null){
				setErrorMsg("数据不存在！");
			}else{
				setAttr("company",company);
			}
		}else{
			setAttr("action","add");//将一个属性存储在此请求
		}
		render("edit.jsp");
	}
	/**
	 * 进入编辑页面 简介
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void editContent(){
		Integer id=getParamModelId();
		if(id!=null&&id>0){
			setAttr("action","update");
			Company company= CompanyService.me.findById(id);
			if(company==null){
				setErrorMsg("数据不存在！");
			}else{
				setAttr("company",company);
			}
		}else{
			setAttr("action","add");
		}
		render("editcontent.jsp");
	}
	/**
	 * 新增保存
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void add(){
		Company company=getModel(Company.class, "company");//从http请求获取模型。
		if(company==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			company.set("foundTime",new Date());
			processCompany(company);
			boolean success=CompanyService.me.save(company);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("新增创业公司信息失败,请重试!");
			}
		}
	
	}
	private void processCompany(Company company) {
		Boolean simple=getParaToBoolean("simple");
		if(simple==null||simple.booleanValue()==false){
			String content=company.get("content");
			if(StrKit.isBlank(content)){
				renderJsonFail("请输入简介内容！");
				return;
			}
			String imgUrl=company.getStr("imgUrl");
			if(StrKit.isBlank(imgUrl)||imgUrl.indexOf("companydefaultimg.png")!=-1){
				company.set("imgUrl","assets/css/imgs/defaultlogobg.jpg");
			}
		}
		company.set("pinyin", PinYinUtil.getSpells(company.getStr("name")));
		company.set("pinyinFirst", PinYinUtil.getFirstSpell(company.getStr("name").substring(0, 1)));
		String website=company.getStr("website");
		if(StrKit.notBlank(website)&&website.indexOf("http://")==-1){
			website="http://"+website;
			company.set("website", website);
		}
	}
	/**
	 * 更新
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void update(){
		Company company=getModel(Company.class, "company");
		if(company==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			processCompany(company);
			boolean success=CompanyService.me.update(company);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("修改创业公司信息失败,请重试!");
			}
		}
	}
	/**
	 * 删除
	 */
	@Permission({Account.PERMISSION_ADMIN})
	public void delete(){
		Integer id=getParamModelId();
		if(id==null||id==0){
			renderJsonFail("数据不存在!");
		}
		boolean success=CompanyService.me.deleteById(id);
		if(success){
			renderJsonSuccess();
		}else{
			renderJsonFail("删除失败，请重试！");
		}
	}
	
	/**
	 * ajax获取数据
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void ajax(){
		renderJson(CompanyService.me.getListByPage(this));
	}
}
