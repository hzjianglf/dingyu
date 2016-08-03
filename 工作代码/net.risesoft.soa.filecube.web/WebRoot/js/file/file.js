/**
 * 所有当前选择的文件及文件夹
 */
var file_allSelectDatas = [];

/**
 * 获取所有选择的数据的uids
 */
function getAllSelectDataUids(){
	var uids = "";
	for(var i = 0; i < file_allSelectDatas.length;i++){
		uids += file_allSelectDatas[i].uid + ",";
	}
	uids.substring(0,uids.length - 1);
	return uids;
}					  
var _isPersonnelFolder;
var _isAdmin = (global_CUR_SESSION_USER_ORGTYPE == global_USER_ADMIN_TYPE);
/**
 * 批量启用操作按钮
 */
function enableBatchButton(){
	var alowUpdateBatch = true;
	for(var i = 0; i < file_allSelectDatas.length;i++){
		var value = file_allSelectDatas[i].uid;
		var type = file_allSelectDatas[i].fileType;
		var extendFolderUids = file_allSelectDatas[i].extendFolderUids;
		//判断是否拥有删除、修改、移动的权限
		var alowUpdate = true;
		if(extendFolderUids.indexOf(file_jsp_folderUid) != -1){
			alowUpdate = false;
			alowUpdateBatch = false;
		}
		var allowedMove;
		var allowedDelete;
		if(!_isPersonnelFolder){
			//启用移动文件的按钮	
			allowedMove = permission_hasPermission(value,type,global_OperationType_MODIFY);					
			//启用删除文件的按钮	
			allowedDelete = permission_hasPermission(value,type,global_OperationType_DELETE);
		}
		
		//判断对此文件夹或者文件的删除权限
		if(_isPersonnelFolder || (eval(allowedDelete) && alowUpdateBatch)){
			//启用删除按钮
			Ext.getCmp('deleteFileButton').enable();			
		}	
		//判断对此文件夹或者文件的移动权限
		if(_isPersonnelFolder || (eval(allowedMove) && alowUpdateBatch)){			
			//启用移动按钮
			Ext.getCmp('moveFileButton').enable();			
		} 
	}
	if(!alowUpdateBatch){
		Ext.getCmp('moveFileButton').disable();
		Ext.getCmp('deleteFileButton').disable();
	}
	if(false == Ext.getCmp('moveFileButton').disabled){
		Ext.getCmp('moveFileButton').addListener("click",function(){
			preMoveFiles(getAllSelectDataUids());
		});
	}
	if(false == Ext.getCmp('deleteFileButton').disabled){
		Ext.getCmp('deleteFileButton').addListener("click",function(){
			batchLogicDeleteFile(getAllSelectDataUids());
		});	
	}
}
/**
 * 控制按钮的启用、禁用状态
 */
function file_controlButtonState(){
	_isPersonnelFolder = (global_FOLDER_TREE_TYPE_CUR == global_FOLDER_TREE_TYPE_PERSONNEL);
	//移除按钮上所有的监听器
	Ext.getCmp('editFileButton').purgeListeners();
	Ext.getCmp('moveFileButton').purgeListeners();
	Ext.getCmp('deleteFileButton').purgeListeners();
	/*Ext.getCmp('setAadapterButton').purgeListeners();*/
	Ext.getCmp('authorizeButton').purgeListeners();
	
	if(file_allSelectDatas.length == 1){
		var type = file_allSelectDatas[0].fileType;
		var value = file_allSelectDatas[0].uid;
		var extendFolderUids = file_allSelectDatas[0].extendFolderUids;
		//判断是否拥有删除、修改、移动的权限
		var alowUpdate = true;
		if(extendFolderUids.indexOf(file_jsp_folderUid) != -1){
			alowUpdate = false;
		}
		//不是个人文件夹时  进入
		if(!_isPersonnelFolder){	
			var allowed = permission_hasPermission(value,type,global_OperationType_MODIFY);
			//判断对此文件夹或者文件的修改权限
			if(eval(allowed) && alowUpdate){
				//启用修改按钮
				Ext.getCmp('editFileButton').enable();	
			}	 
		}else{
			//启用修改按钮
			Ext.getCmp('editFileButton').enable();	
		}
								
		//资源为文件夹的时候，绑定文件夹的修改事件
		Ext.getCmp('editFileButton').addListener("click",function(){
			if(type == global_RESOURCE_TYPE_FOLDER_EN){
				preEditFolder(value);
			}else{
				preEditFile(value);
			}			
		});			
		if(!_isPersonnelFolder && _isAdmin && !_isPersonnelFolder){				
			//启用授权按钮
			Ext.getCmp('authorizeButton').enable();
			Ext.getCmp('authorizeButton').addListener("click",function(){
				authorize(type,value);
			});
		}		
	
		enableBatchButton();
	}
	if(file_allSelectDatas.length > 1){
		//禁用修改按钮
		Ext.getCmp('editFileButton').disable();
		//禁用设置适配器按钮
		Ext.getCmp('setAadapterButton').disable();
		//禁用授权按钮
		Ext.getCmp('authorizeButton').disable();
		
		enableBatchButton();
	}
	if(file_allSelectDatas.length < 1){
		//禁用按钮
		Ext.getCmp('editFileButton').disable();
		Ext.getCmp('moveFileButton').disable();
		Ext.getCmp('deleteFileButton').disable();
		/*Ext.getCmp('setAadapterButton').disable();*/
		Ext.getCmp('authorizeButton').disable();
	}
	//如果是个人文件夹，禁用授权、设置适配器的按钮
	if(_isPersonnelFolder){	
		Ext.getCmp('authorizeButton').disable();
		/*Ext.getCmp('setAadapterButton').disable();*/
	}
}

//文件类型
var file_fileType = {
		
	"document":"文档",
	"image":"图片",
	"video":"视频",
	"audio":"音乐",
	"other":"其它",
	"folder":"文件夹"
}

/**
 * 预处理的文件修改
 * @param fileUID -- 文件标识
 */
function preEditFile(fileUID){
	Ext.PopWin.init("修改文件", 300, 320);	
	popWin.load({		
		url : 'file_preEdit.action?acFileInfo.fileUid=' + fileUID,
		scripts : true
	});
}


/**
 * 逻辑删除文件
 * @param fileUID -- 文件标识
 */
function logicDeleteFile(fileUID){
	Ext.Msg.confirm('提示', '确定删除该文件吗？',function(button,text){
		if(button == 'yes'){			
			$.ajax({
			  type: 'POST',
			  url: 'file_logicDelete.action',
			  data: {'acFileInfo.fileUid':fileUID},
			  dataType: 'JSON',
			  success: function(date){
				  Ext.Msg.alert('操作成功',date.success); 
				  _left_refreshTreeAndList(file_jsp_folderUid);
			  },
			  error:function(){
				  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
			  }			  
			});
		}
	});
}

/**
 * 逻辑删除文件
 * @param uids -- 批量删除的uids
 */
function batchLogicDeleteFile(uids){
	Ext.Msg.confirm('提示', '确定放入回收站吗？',function(button,text){
		if(button == 'yes'){
			var url = 'file_batchLogicDeleteFiles.action';
			var data = {batchUids:uids};
			var rtnData = global_ajax(url,data,false);
			if(rtnData == global_ajax_error){
				Ext.Msg.alert('操作失败！','服务器出现异常，请稍后再试！'); 
				return;
			}
			Ext.Msg.alert('操作成功',rtnData.success);    
			_left_refreshTreeAndList(file_jsp_folderUid);
		}
	});
}
/**
 * 将传入的字符串的首字母大写
 * @param str -- 传入的字符串
 */
function file_uppercaseFirstChar(str){
	var c = str.substring(0,1);	
	return c.toUpperCase() + str.substring(1);
} 

/**
 * 预处理文件查看
 * @param fileUID -- 文件标识
 */
function preViewdocument(fileUID){
	
	if(!(global_FOLDER_TREE_TYPE_CUR == global_FOLDER_TREE_TYPE_PERSONNEL)){
		var allowed = permission_getPermissionByFileUid(fileUID,'');
		if(!allowed){
			alert("对不起您没有查看此文件的权限！");
			return;
		}
	}
	
	var url = 'file_preViewFile.action?showFile=' + global_CUR_FILE_VIEWMODE + 
	'&acFileInfo.fileUid=' + fileUID;
	window.open(url) ;
	
	/*Ext.getCmp('index-center-panel').load(
		{
		url:'file_preViewFile.action?showFile=' + global_CUR_FILE_VIEWMODE + 
			'&acFileInfo.fileUid=' + fileUID + "&folderUid=" + file_jsp_folderUid
		,scripts:true
		}
	);*/
}

/**
 * 预处理打开视频
 * @param fileUID
 */
function preViewvideo(fileUID){
	downLoadFile(fileUID);
}
/**
 * 预处理打开音频
 * @param fileUID
 */
function preViewaudio(fileUID){
	downLoadFile(fileUID);
}
/**
 * 预处理打开其它文件
 * @param fileUID
 */
function preViewother(fileUID){
	downLoadFile(fileUID);
}
/**
 * 下载文件
 * @param fileUID
 */
function downLoadFile(fileUID){	
	if(!(global_FOLDER_TREE_TYPE_CUR == global_FOLDER_TREE_TYPE_PERSONNEL)){
		var allowed = permission_getPermissionByFileUid(fileUID,global_OperationType_DOWNLOAD);
		if(!allowed){
			alert("对不起您没有下载此文件的权限！");
			return;
		}
	}
	location.href='file_download.action?acFileInfo.fileUid=' + fileUID;	
}
/**
 * 预处理打开文件夹
 * @param folderUid
 */
function preViewfolder(folderUid){	
	if(!(global_FOLDER_TREE_TYPE_CUR == global_FOLDER_TREE_TYPE_PERSONNEL)){
		var allowed = permission_getPermissionByFolderUid(folderUid,global_OperationType_VIEW);
		if(!allowed){
			alert("对不起您没有查看此文件夹的权限！");
			return;
		}
	}
	Ext.getCmp('index-center-panel').load({url:'file_showFile.action?showFile=' + global_CUR_FILE_VIEWMODE
		+ '&folderUid=' + folderUid
    	,scripts:true});
}
/**
 * 收藏
 * @param uid   -- 收藏的文件或文件夹的uid
 * @param type -- 收藏的类型 Folder:文件夹
 */
function favorite(uid,type,img){	
	_isPersonnelFolder = (global_FOLDER_TREE_TYPE_CUR == global_FOLDER_TREE_TYPE_PERSONNEL);
	
	var allowed; 
	if(!_isPersonnelFolder){
		//判断权限	
		if(type == global_RESOURCE_TYPE_FOLDER_EN){		
			allowed = permission_getPermissionByFolderUid(uid,'');		
		}else{		
			allowed = permission_getPermissionByFileUid(uid,'');		
		}
		if(!allowed){
			alert("对不起您没有收藏此文件的权限！");
			return;
		}
	}
	
	var img_src = $(img).attr('imgSrc');
	var star_on = 'on';
	var star_off = 'off';
	var url = "";
	var data = {};
	var title = "";
	if(img_src == star_on){
		url = 'favorite_delete.action';		
		data = {'favorites.favoritesUid':$(img).attr('favoriteUid')};
		$(img).attr('src','images/button_operation/star-off.png');
		$(img).attr('imgSrc',star_off);
		title = "取消收藏！";
		$(img).attr('favoriteUid','');
	}if(img_src == star_off){
		if(type == global_RESOURCE_TYPE_FOLDER_EN){
			data = {'favorites.fileFolder.folderUid':uid};
		}else{	
			data = {'favorites.fileInfo.fileUid':uid};		
		}
		url = 'favorite_add.action';	
		$(img).attr('imgSrc',star_on);
		$(img).attr('src','images/button_operation/star-on.png');
		title = "收藏成功！";
	}
	$.ajax({
		  type: 'POST',
		  url: url,
		  data: data,
		  dataType: 'JSON',
		  success: function(rtnDate){
			  if(img_src == star_off){
				 $(img).attr('favoriteUid',rtnDate.favoritesUid); 				 
			  }			  
			  alert(title); 
		  },
		  error:function(){
			  alert('服务器出现错误请稍后再试！');
		  }			  
	});
}
/**
 * 预处理移动位置
 * @param uids   -- 移动的文件或文件夹的uids
 */
function preMoveFiles(uids){	
	Ext.PopWin.init("移动位置", 260, 320);	
	popWin.load({		
		url : 'folder_preMoveFiles.action?batchUids=' + uids,
		scripts : true
	});
}
/**
 * 移动位置
 * @param uids      -- 移动的文件或文件夹的uids
 * @param folderUid -- 移动到此文件夹下
 */
function moveFiles(uids,folderUid){	
	var url = 'folder_moveFiles.action'; 
	var data = {
		'batchUids':uids,
		'folder.folderUid':folderUid,
		folderTreeType:global_FOLDER_TREE_TYPE_CUR
	}
	popWin.close();	
	$.ajax({
		  type: 'POST',
		  url: url,
		  dataType: 'JSON',
		  data:data,
		  async: true,      //ajax同步
		  success: function(data){
			  Ext.Msg.alert('操作成功',data.success);	
			  _left_refreshTreeAndList(folderUid);	
		  },
		  error:function(){			 
			 
		  }			  
	});
	
}
/**
 * 共享
 * @param id   -- 共享的文件或文件夹的id
 * @param type -- 共享的类型 Folder:文件夹
 */
function share(id,type){
	_isPersonnelFolder = (global_FOLDER_TREE_TYPE_CUR == global_FOLDER_TREE_TYPE_PERSONNEL);	
	var param = "";
	//判断权限	
	var allowed;	
	if(!_isPersonnelFolder){
		if(type == global_RESOURCE_TYPE_FOLDER_EN){		
			allowed = permission_getPermissionByFolderUid(id,global_OperationType_SHARE);		
		}else{		
			allowed = permission_getPermissionByFileUid(id,global_OperationType_SHARE);
		}
		if(!allowed){		
			alert("对不起您没有共享此文件的权限！");
			return;
		}
	}
	if(type == global_RESOURCE_TYPE_FOLDER_EN){				
		param += "fileShare.fileFolder.folderUid=" + id;
	}else{		
		param += "fileShare.fileInfo.fileUid=" + id;
	}
	
	$('#shareDialogParent').html('<div id="shareDialog" style="padding: 0px 0px;"></div>');
	$('#shareDialog').load('share_preShare.action?' + param);
	$( "#shareDialog" ).dialog({
		resizable: false,
		title:'请选择共享用户',
		width:300,
		height:455,
		modal: true,
		buttons: {
			"确定": function() {
				orgTree_doShare();
				$( this ).dialog( "close" );
				$('#shareDialog').remove();
				$('#shareDialogParent').html('');
			},
			'取消': function() {
				$( this ).dialog( "close" );
				$('#shareDialog').remove();
				$('#shareDialogParent').html('');
			}
		}
	});
}
/**
 * 文件夹或文件的授权
 * @param type -- 资源类型
 * @param uid  -- 文件或文件夹的uid
 */
function authorize(type,uid){	
	if(type != global_RESOURCE_TYPE_FOLDER_EN){
		type = global_RESOURCE_TYPE_FILE_EN;
	}	
	var resourceUid = permission_getResourceUid(uid,type);
	window.open('/ac/grantPermission.jsp?node=' + resourceUid);
}
/**
 *  彻底删除文件
 * @param fileUid -- 文件uid
 */
function deleteFile(fileUid){
	$.ajax({
		  type: 'POST',
		  url: 'file_delete.action',
		  dataType: 'JSON',
		  data:{'acFileInfo.fileUid':fileUid},
		  async: false,      //ajax同步
		  success: function(data){
			  alert(data.success);
		  },
		  error:function(){		
			  alert("服务器出现异常，请稍后再试！");
		  }			  
	});
}