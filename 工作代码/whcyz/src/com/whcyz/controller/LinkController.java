package com.whcyz.controller;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.whcyz.model.Account;
import com.whcyz.model.Link;
import com.whcyz.permission.Permission;
import com.whcyz.permission.PermissionInterceptor;
import com.whcyz.permission.UnCheck;
import com.whcyz.service.impl.LinkService;
/**
 * 友情链接controller
 * @author 牟孟孟
 *
 */
@Before(PermissionInterceptor.class)
public class LinkController extends BaseController {
	@Before(NoUrlPara.class)
	@UnCheck
	public void index(){}
	/**
	 * 前台调用ajax 下方的超链接
	 */
	@UnCheck
	public void all(){
		List<Link> links=LinkService.me.getList();
		if(links==null||links.size()==0){
			renderJsonFail();
			return;
		}
		renderJsonData(true, links);
	}
	@ActionKey("linkmgr")
	@Permission(Account.PERMISSION_ADMIN)
	public void list(){
		render("list.jsp");
	}
	/**
	 * 进入编辑页面
	 */
	@Permission(Account.PERMISSION_ADMIN)
	public void edit(){
		Integer id=getParamModelId();
		if(id!=null&&id>0){
			setAttr("action","update");
			Link link= LinkService.me.findById(id);
			if(link==null){
				setErrorMsg("数据不存在！");
			}else{
				setAttr("link",link);
			}
		}else{
			setAttr("action","add");
		}
		render("edit.jsp");
	}
	/**
	 * 新增保存
	 */
	@Permission(Account.PERMISSION_ADMIN)
	public void add(){
		Link link=getModel(Link.class, "link");
		if(link==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			link.set("createTime",new Date());
			boolean success=LinkService.me.save(link);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("新增友情链接信息失败,请重试!");
			}
		}
	
	}
	/**
	 * 更新
	 */
	@Permission(Account.PERMISSION_ADMIN)
	public void update(){
		Link link=getModel(Link.class, "link");
		if(link==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			boolean success=LinkService.me.update(link);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("修改友情链接信息失败,请重试!");
			}
		}
	}
	/**
	 * 删除
	 */
	@Permission(Account.PERMISSION_ADMIN)
	public void delete(){
		Integer id=getParamModelId();
		if(id==null||id==0){
			renderJsonFail("数据不存在!");
		}
		boolean success=LinkService.me.deleteById(id);
		if(success){
			renderJsonSuccess();
		}else{
			renderJsonFail("删除失败，请重试！");
		}
	}
	
	/**
	 * ajax获取数据
	 */
	@Permission(Account.PERMISSION_ADMIN)
	public void ajax(){
		renderJson(LinkService.me.getListByPage(this));
	}
}
