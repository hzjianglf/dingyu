/**
 * 全局ajax错误 返回值
 */
var global_ajax_error = 'error';
//全局方法，方法命名规则 js文件名_方法名();
/**
 * 带返回值的ajax
 * @param url     -- 访问的url
 * @param params  -- 参数 json格式
 * @param async   -- 是否异步，true：异步,false：同步
 */
function global_ajax(url,params,async){
	var rtnData;
	$.ajax({
		  type: 'POST',
		  url: url,
		  dataType: 'JSON',
		  data:params,
		  async: async,      //ajax同步
		  success: function(data){
			  rtnData = data;
		  },
		  error:function(){			 
			  rtnData = global_ajax_error;
		  }			  
	});
	return rtnData;
}
/**
 * 获取资源uid
 * @param uid          -- 文件或文件夹的uid
 * @param resourceType -- 文件或者文件的资源类型
 * @returns -- 资源uid
 */
function permission_getResourceUid(uid,resourceType){
	var params;
	var url;
	if(resourceType == global_RESOURCE_TYPE_FOLDER_EN){
		params = {folderUid:uid};
		url = "folder_getResourceUid.action";
	}else{
		params = {'acFileInfo.fileUid':uid};
		url = "file_getResourceUid.action";
	}	
	var rtnData = global_ajax(url,params,false);
	if(rtnData == global_ajax_error){
		Ext.Msg.alert("错误","获取资源uid失败！请稍后再试……");
		return;
	}
	return rtnData.resourceUid;
}
/**
 * 根据资源id获取当前用户对此资源的操作集合
 * @param   resourceUid -- 资源uid
 * @returns 
 */
function permission_getOperationByResourceUid(resourceUid){
	var url = "permission_getOperationByResourceUid.action";
	var params = {resourceUid:resourceUid};
	var async = false;//同步访问
	var rtnData = global_ajax(url,params,async);	
	if(rtnData = global_ajax_error){
		Ext.Msg.alert("错误","获取对此资源uid的操作集合失败！请稍后再试……");
		return;
	}
	return rtnData;
}
/**
 * 判断当前登录用户，对某资源是否有某种操作权限
 * @param uid           -- 文件或文件夹的uid
 * @param resourceType  -- 资源类型：文件或文件夹
 * @param operationKey  -- 操作类型的key
 * @returns -- true：允许操作；false：不允许操作
 */
function permission_hasPermission(uid,resourceType,operationKey){
	var allowed;
	if(resourceType == global_RESOURCE_TYPE_FOLDER_EN){
		allowed = permission_getPermissionByFolderUid(uid,operationKey)
	}else{
		allowed = permission_getPermissionByFileUid(uid,operationKey)
	}
	return allowed;
}

/**
 * 判断此当前用户对此文件是否有某种操作权限
 * @param   fileUID       -- 文件的uid，即solrDoc的id
 * @param   operationType -- 操作类型关键字 查看" page/global_js_const.jsp "页面
 * @returns true：有；false：没有
 */
function permission_getPermissionByFileUid(fileUID,operationType){	
	var rtnData = global_ajax(
			'permission_getPermissionByFileUid.action',
			{
				  fileUid:fileUID,
				  operationType:operationType
			},
			false
	);
	if(rtnData == global_ajax_error){
		alert("服务器出现异常，请稍候再试！");
	}
	return eval(rtnData.allowed);
}

/**
 * 判断此当前用户对此文件是否有某种操作权限
 * @param   folderUid     -- 文件夹的uid，即solrDoc的id
 * @param   operationType -- 操作类型关键字 查看" page/global_js_const.jsp "页面
 * @returns true：有；false：没有
 */
function permission_getPermissionByFolderUid(folderUid,operationType){	
	var rtnData = global_ajax(
			'permission_getPermissionByFolderUid.action',
			{
				  folderUid:folderUid,
				  operationType:operationType
			},
			false
	);
	if(rtnData == global_ajax_error){
		alert("服务器出现异常，请稍候再试！");
	}
	return eval(rtnData.allowed);
}