package com.whcyz.permission;

import java.lang.reflect.Method;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.whcyz.controller.BaseController;
import com.whcyz.model.Account;

public class PermissionInterceptor implements Interceptor {

	@Override
	public void intercept(ActionInvocation ai) {
		//检测是否需要验证
		Method m = ai.getMethod();
		if (m.isAnnotationPresent(UnCheck.class)) {
			ai.invoke();
			return;
		}
		BaseController controller=(BaseController) ai.getController();
		Permission permission=m.getAnnotation(Permission.class);
		if(permission==null){
			controller.renderHtml("<center><h3>对不起，当前用户无权操作！</h3></center>");
			return ;
		}
		//如果设置了注解但是忘了写内容也不行
		int[] permissions=permission.value();
		if(permissions==null||permissions.length==0){
			controller.renderHtml("<center><h3>对不起，当前用户无权操作！</h3></center>");
			return ;
		}
		//判断用户是否登录
		Account account =controller.getSessionAccount();
		if (account == null) {
			controller.renderHtml("<center><script>setTimeout(function(){window.location.href='/login';},2000)</script><h3>对不起，您当前处于离线状态，请重新<a href='/login'>登录</a>！</h3></center>");
			return;
		}
		/**
		 * 如果是所有用户都能访问的权限PERMISSION_ALL 直接过 因为上面已经登录了
		 */
		if(permissions.length==1&&permissions[0]==Account.PERMISSION_ALL){
			ai.invoke();
			return;
		}
		//想验证的时候 读取指定设置的权限 然后进行循环检测 看看设置的是否包含自己的权限
		int userPermission=account.getPermission();
		for(int p:permissions){
			if(userPermission==p){
				ai.invoke();
				return;
			}
		}
		//循环结束发现没有自己的权限 跳转无权界面
		controller.renderHtml("<center><h3>对不起，当前用户无权操作！</h3></center>");
		return;

	}

}
