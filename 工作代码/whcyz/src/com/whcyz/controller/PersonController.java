package com.whcyz.controller;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.model.Account;
import com.whcyz.model.Person;
import com.whcyz.permission.Permission;
import com.whcyz.permission.PermissionInterceptor;
import com.whcyz.permission.UnCheck;
import com.whcyz.service.impl.CompanyService;
import com.whcyz.service.impl.PersonService;
import com.whcyz.util.PinYinUtil;
/**
 * 创业人物controller
 * @author 牟孟孟
 *
 */
@Before(PermissionInterceptor.class)
public class PersonController extends BaseController {
	/**
	 * 首页
	 */
	@Before(NoUrlPara.class)
	@UnCheck
	public void index() {
		Page<Person> personPage=(Page<Person>) PersonService.me.paginateWithExtras(getParaToInt(0,1), 12,"id,name,imgUrl"," order by foundTime desc ");
		setAttr("personInfo", personPage);
		render("index.jsp");
	}
	@UnCheck
	public void page() {
		index();
	}
	/**
	 * 关联人物信息查询
	 */
	@UnCheck
	public void relinfo(){
		Integer id=getParaToInt();
		if(id==null||id<=0){
			renderJsonFail();
			return;
		}
		Person person=PersonService.me.findById(id,"id,name,sex,imgUrl,jobTitle,companyId,companyName");
		if(person==null){
			renderJsonFail();
		}else{
			renderJsonData(true, person);
		}
		return;
	}
	@UnCheck
	public void hot(){
		List<Person> persons=PersonService.me.getHotList(30);
		if(persons==null||persons.size()==0){
			renderJsonFail();
			return;
		}
		renderJsonData(true, persons);
	}
	@UnCheck
	public void detail(){
		Integer id=getParaToInt();
		if(id==null||id<=0){
			renderError(404);
		}
		Person person=PersonService.me.findById(id);
		if(person==null){
			renderError(404);
		}
		setAttr("person", person);
		setAttr("relCompanyId",person.getCompanyId());
		render("detail.jsp");
	}
	@UnCheck
	public void pyindex(){
		String py=getPara();
		if(py==null){
			renderJsonFail("数据读取失败，参数错误！");
			return;
		}
		List<Person> persons=PersonService.me.getListByPyIndex(py);
		renderJsonData(true, persons);
	}
	@ActionKey("personmgr")
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
			setAttr("action","update");
			Person person= PersonService.me.findById(id);
			if(person==null){
				setErrorMsg("数据不存在！");
			}else{
				setAttr("person",person);
			}
		}else{
			setAttr("action","add");
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
			Person person= PersonService.me.findById(id);
			if(person==null){
				setErrorMsg("数据不存在！");
			}else{
				setAttr("person",person);
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
		Person person=getModel(Person.class, "person");
		if(person==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			person.set("foundTime",new Date());
			processPerson(person);
			boolean success=PersonService.me.save(person);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("新增创业人物信息失败,请重试!");
			}
		}
	
	}
	/**
	 * 更新
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void update(){
		Person person=getModel(Person.class, "person");
		if(person==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			processPerson(person);
			boolean success=PersonService.me.update(person);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("修改创业人物信息失败,请重试!");
			}
		}
	}
	/**
	 * 处理人物信息
	 * @param person
	 */
	private void processPerson(Person person) {
		Boolean simple=getParaToBoolean("simple");
		if(simple==null||simple.booleanValue()==false){
			String content=person.get("content");
			if(StrKit.isBlank(content)){
				renderJsonFail("请输入简介内容！");
				return;
			}
			String imgUrl=person.getStr("imgUrl");
			if(StrKit.isBlank(imgUrl)||imgUrl.indexOf("persondefaultimg.png")!=-1){
				person.set("imgUrl","assets/css/imgs/defaultavatar.png");
			}
		}
		person.set("pinyin", PinYinUtil.getSpells(person.getStr("name")));
		person.set("pinyinFirst", PinYinUtil.getFirstSpell(person.getStr("name").substring(0, 1)));
		String website=person.getStr("website");
		if(StrKit.notBlank(website)&&website.indexOf("http://")==-1){
			website="http://"+website;
			person.set("website", website);
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
		boolean success=PersonService.me.deleteById(id);
		if(success){
			renderJsonSuccess();
		}else{
			renderJsonFail("删除失败，请重试！");
		}
	}
	@UnCheck
	public void autolist() {
		String matchInfo=getPara("matchInfo");
		Integer matchCount=getParaToInt("matchCount",8);
		if(StrKit.isBlank(matchInfo)){
			renderJson(PersonService.me.getListWithExtras("id,name,pinyin", null,"limit 0,"+matchCount));
			//select id,name,pinyin from company limit 0,8;
		}else{
			renderJson(PersonService.me.getListWithExtras("id,name,pinyin", "pinyin like '%"+matchInfo+"%' or name like  '%"+matchInfo+"%' ","limit 0,"+matchCount));
			//select id,name,pinyin from company where pinyin like '%matchInfo%' or name like '%matchInfo%' limit 0,8;
		}
	}
	
	/**
	 * ajax获取数据
	 */
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void ajax(){
		renderJson(PersonService.me.getListByPage(this));
	}
}
