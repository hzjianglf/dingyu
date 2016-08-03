
package com.whcyz.controller;

import java.io.File;
import java.util.Date;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.ext.render.CaptchaRender;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.whcyz.common.Constant;
import com.whcyz.model.Account;
import com.whcyz.permission.Permission;
import com.whcyz.permission.PermissionInterceptor;
import com.whcyz.permission.UnCheck;
import com.whcyz.service.impl.AccountService;
import com.whcyz.util.IPUtil;
import com.whcyz.util.MD5;

/**
 * 账户相关操作的controller
 * @author 牟孟孟
 * 
 */
@Before(PermissionInterceptor.class)
public class AccountController extends BaseController {
	/**
	 * 首页
	 */
	@Before(NoUrlPara.class)
	@UnCheck
	public void index(){
	}
	@ActionKey("accountmgr")
	@Permission(Account.PERMISSION_ADMIN)
	public void list() {
		render("list.jsp");
	}
	@Permission(Account.PERMISSION_ALL)
	public void detail() {
		setAttr("account", AccountService.me.findById(getSessionAccountId()));
		render("detail.jsp");
	}
	@Permission(Account.PERMISSION_ALL)
	@Before(Tx.class)
	public void updateDetail(){
		Account account=getModel(Account.class,"account");
		if(account==null){
			renderJsonFail("更新失败，参数有误！");
			return;
		}
		if(account.getId()==null||account.getId().intValue()!=getSessionAccountId().intValue()){
			renderJsonFail("更新失败，参数有误！");
			return;
		}
		String imgUrl=account.get("imgUrl");
		if(imgUrl.indexOf("avatar_"+account.getId())==-1){
			String abFile=PathKit.getWebRootPath()+"/"+imgUrl;
			File oldFile=new File(abFile);
			String newName="avatar_"+account.getId()+".jpg";
			String newUrl="/upload/images/account/avatar/"+newName;
			File newFile=new File(PathKit.getWebRootPath()+newUrl);
			if(newFile.exists()){
			    if(newFile.getName().indexOf("assets")!=-1){
			        System.out.println("警告...");
			    }
			    newFile.delete();
			    }
			oldFile.renameTo(newFile);
			account.set("imgUrl", newUrl);
			setAttr("imgUrl", newUrl);
		}
		boolean success=AccountService.me.update(account);
		if(success){
			setAttr("success", true);
			renderJson();
		}else{
			renderJsonFail("更新失败，请重试！");
		}
		return;
	}
	/**
	 * 生成注册页面验证码
	 */
	@UnCheck
	public void img() {	
		CaptchaRender img = new CaptchaRender("whcyz");
		render(img);
	}
	@ActionKey("login")
	@UnCheck
	public void initLogin(){
		setAttr("action", "login");
		render("lorr.jsp");
	}
	@ActionKey("register")
	@UnCheck
	public void initReg(){
		setAttr("action", "reg");
		render("lorr.jsp");
	}
	/**
	 * 登录或者注册跳转
	 */
	@UnCheck
	public void lorr(){
		setAttr("action", getPara("action", "login"));
		render("lorr.jsp");
	}
	/**
	 * 修改密码
	 */
	@Permission(Account.PERMISSION_ALL)
	public void initModifyPwd(){
		render("modifypwd.jsp");
	}
	
	@Permission(Account.PERMISSION_ALL)
	@Before(Tx.class)
	public void modifyPwd(){
		String password=getPara("password");
		String newPassword=getPara("newPassword");
		if(StrKit.isBlank(password)||StrKit.isBlank(newPassword)){
			renderJsonFail("请输入正确的原始密码和新密码！");
			return;
		}
		Account account=AccountService.me.checkOldPassword(getSessionAccountId(),password);
		if(account==null){
			renderJsonFail("原密码错误！");
			return;
		}
		if(newPassword.equals(password)){
			renderJsonSuccess();
		}
		account.set("password", MD5.MD5Encode(newPassword, "whcyz"));
		boolean success=AccountService.me.update(account);
		if(success){
			renderJsonSuccess();
		}else{
			renderJsonFail("修改密码失败！");
		}
		return;
		
	}
	/**
	 * 登录
	 */
	@UnCheck
	public void login(){
		String username=getPara("username");
		String password=getPara("password");
		if(StrKit.isBlank(username)||StrKit.isBlank(password)){
			renderJsonFail("用户名或密码不正确！");
			return;
		}
		Account account=AccountService.me.checkLogin(username,password);
		if(account==null){
			renderJsonFail("用户名或密码不正确！");
		}else{
			processLoginInfo(account);
			AccountService.me.update(account);
			setSessionAttr(Constant.USER_SESSION_KEY, account);
			renderJsonSuccessData(account.getPermission());
		}
		
	}
	private void processLoginInfo(Account account) {
		Date loginTime=new Date();
		String loginIp=IPUtil.getRealIpAddr(getRequest());
		Date LastLoginTime=account.get("loginTime");
		String lastLoginIp=account.get("loginIp");
		if(LastLoginTime==null){
			LastLoginTime=loginTime;
		}
		if(StrKit.isBlank(lastLoginIp)){
			lastLoginIp=loginIp;
		}
		account.set("loginTime", loginTime);
		account.set("loginIp", loginIp);
		account.set("lastLoginTime", LastLoginTime);
		account.set("lastLoginIp", lastLoginIp);
	}
	/**
	 * 退出
	 */
	@ActionKey("logout")
	@UnCheck
	public void logout(){
		getSession().invalidate();
		redirect("/home");
	}
	/**
	 * 注册
	 */
	@Before(Tx.class)
	@UnCheck
	public void reg(){
		String username=getPara("username");
		String password=getPara("password");
		String repassword=getPara("repassword");
		String captchaCode=getPara("captchaCode");
		if(StrKit.isBlank(username)||StrKit.isBlank(password)){
			renderJsonFail("请输入正确的用户名或密码！");
			return;
		}
	
		if(StrKit.isBlank(captchaCode)){
			renderJsonFail("验证码输入不正确！");
			return;
		}
		if(!password.equals(repassword)){
			renderJsonFail("两次密码输入不一致！");
			return;
		}
		boolean success=CaptchaRender.validate(this, captchaCode.toUpperCase(), "whcyz");
		if(!success){
			renderJsonFail("验证码输入不正确！");
			return;
		}
		boolean exist= AccountService.me.isExist("username=?", username);
		if(exist){
			renderJsonFail("用户名已经存在，请换一个重新注册!");
			return;
		}
		Account account=AccountService.me.doReg(username,password,IPUtil.getRealIpAddr(getRequest()));
		if(account==null){
			renderJsonFail("注册失败，请重试！");
		}else{
			setSessionAttr(Constant.USER_SESSION_KEY, account);
			renderJsonSuccess();
		}
		
	
	}
	/**
	 * 进入编辑页面
	 */
	@Permission(Account.PERMISSION_ADMIN)
	public void edit(){
		setAttr("permission", getPara("permission"));
		Integer id=getParamModelId();
		if(id!=null&&id>0){
			setAttr("action","update");
			Account account= AccountService.me.findById(id);
			if(account==null){
				setErrorMsg("数据不存在！");
			}else{
				setAttr("account",account);
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
		Account account=getModel(Account.class, "account");
		if(account==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			if(account.getNickname()==null){
				account.setNickname(account.getUsername());
			}
			account.set("registerTime",new Date());
			String password=account.get("password");
			if(password==null){
				renderJsonFail("请填写密码！");
				return;
			}
			account.set("password", MD5.MD5Encode(password, "whcyz"));
			boolean success=AccountService.me.save(account);
			if(success){
				String imgUrl=account.get("imgUrl");
				if(imgUrl.indexOf("avatar_"+account.getId())==-1&&imgUrl.indexOf("assets/css/imgs/defaultavatar.png")==-1){
					String abFile=PathKit.getWebRootPath()+"/"+imgUrl;
					System.out.println("源地址"+abFile);
					File oldFile=new File(abFile);
					String newName="avatar_"+account.getId()+".jpg";
					File newFile=new File(PathKit.getWebRootPath()+"/upload/images/account/avatar/"+newName);
					if(newFile.exists()){newFile.delete();}
					oldFile.renameTo(newFile);
					account.set("imgUrl", "/upload/images/account/avatar/"+newName);
					AccountService.me.update(account);
				}
				renderJsonSuccess();
			}else{
				renderJsonFail("新增用户信息失败,请重试!");
			}
		}
	
	}
	/**
	 * 更新
	 */
	@Permission(Account.PERMISSION_ADMIN)
	public void update(){
		Account account=getModel(Account.class, "account");
		if(account==null){
			renderJsonFail("提交数据有误，请重试!");
		}else{
			String imgUrl=account.get("imgUrl");
			if(imgUrl.indexOf("avatar_"+account.getId())==-1&&imgUrl.indexOf("assets/css/imgs/defaultavatar.png")==-1){
				String abFile=PathKit.getWebRootPath()+"/"+imgUrl;
				System.out.println("源地址"+abFile);
				File oldFile=new File(abFile);
				String newName="avatar_"+account.getId()+".jpg";
				File newFile=new File(PathKit.getWebRootPath()+"/upload/images/account/avatar/"+newName);
				System.out.println("新地址"+newFile);
				if(newFile.exists()){newFile.delete();}
				oldFile.renameTo(newFile);
				account.set("imgUrl", "/upload/images/account/avatar/"+newName);
			}
			boolean success=AccountService.me.update(account);
			if(success){
				renderJsonSuccess();
			}else{
				renderJsonFail("修改用户信息失败,请重试!");
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
		boolean success=AccountService.me.deleteById(id);
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
		renderJson(AccountService.me.getListByPage(this));
	}
	
	
}

