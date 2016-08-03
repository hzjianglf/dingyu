<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
var file_jsp_folderUid = '${folderUid}';
/**
 * 上传按钮
 */
var uploadButton = new Ext.ButtonGroup(
	{	 
		xtype:'buttongroup',		
		items: {
			 id:'uploadFileButton',
	         text: '上传文件',
	         icon: 'images/button_operation/upload.png',
	         handler:function(){
	        	 ajaxUpload_customUpload('<%=path%>/file_upload.action',{folderUid:file_jsp_folderUid});	        	
	         }	        	 
		}		
	}
);

/**
 * 新建文件夹按钮
 */
var addFolderButton = new Ext.ButtonGroup(
	{	 
		xtype:'buttongroup',		
		items: {
			 id:'addFolderButton',
	         text: '添加文件夹',
	         icon: 'images/button_operation/folder_add.png',
	         handler:function(){
	        	 preAddFolder();
	         }
		}		
	}
);
//判断当前用户对此文件夹 是否有添加权限,且列表不是个人文件夹时 判断权限
if(global_FOLDER_TREE_TYPE_CUR != global_FOLDER_TREE_TYPE_PERSONNEL){	
	var allowed = permission_hasPermission(file_jsp_folderUid,
			global_RESOURCE_TYPE_FOLDER_EN,global_OperationType_ADD);
	//判断对此文件夹是否具有添加 文件夹和文件夹的权限
	if(!eval(allowed)){		
		Ext.getCmp('uploadFileButton').purgeListeners();
		Ext.getCmp('addFolderButton').purgeListeners();
		Ext.getCmp('uploadFileButton').disable();
		Ext.getCmp('addFolderButton').disable();
	}	 
}

/**
 * 删除按钮
 */
var deleteButton = new Ext.ButtonGroup(
	{	 
		xtype:'buttongroup',
		items: {
			 id:"deleteFileButton",
	         title: '删除',	         
	         text: '删除',
	         disabled:true,
	         icon: 'images/button_operation/trash.gif'
		}		
	}
);
/**
 * 修改按钮
 */
var editButton = new Ext.ButtonGroup(
	{	 
		xtype:'buttongroup',
		items: {
			 id:"editFileButton",
	         title: '修改',	         
	         text: '修改',
	         disabled:true,
	         icon: 'images/button_operation/edit.png'
		}		
	}
);
/**
 * 移动位置按钮
 */
var moveButton = new Ext.ButtonGroup(
	{	 
		xtype:'buttongroup',
		items: {
			 id:"moveFileButton",
	         title: '移动位置',
	         text: '&nbsp;移动位置',
	         disabled:true,
	         icon: 'images/button_operation/movePlace.bmp'
		}		
	}
);
/**
 * 授权按钮
 */
var authorizeButton = new Ext.ButtonGroup(
	{	 
		xtype:'buttongroup',
		items: {
			 id:"authorizeButton",
	         title: '授权',
	         text: '授权',
	         disabled:true,
	         icon: 'images/button_operation/authorize.png'
		}		
	}
);
/**
 * 设置适配器按钮
 */
var adapterButton = new Ext.ButtonGroup(
	{	 
		xtype:'buttongroup',
		items: {
			 id:"setAadapterButton",
	         title: '设置适配器',
	         text: '设置适配器',
	         disabled:true,
	         icon: 'images/button_operation/set.png'
		}		
	}
);

/**
 * 刷新按钮
 */
var reloadButton = new Ext.ButtonGroup(
	{	 
		xtype:'buttongroup',		
		items: {
	         title: '刷新',
	         icon: 'images/button_operation/refresh.png',
	         handler:function(){
	        	 location.reload();
	         }	        	 
		}		
	}
);

/**
 * 展示方式按钮
 */
var viewButton = new Ext.ButtonGroup(
	{	 
		xtype:'buttongroup',		
		items: [{				
		        text: '列表',
		        id:global_FILE_VIEWMODE_LIST,
		        handler : viewClick,	
		        handleMouseEvents :false,
		        icon: 'images/button_view/view_liebiao.jpg'	         	        	 
			},
			{
		         text: '图标',	
		         id:global_FILE_VIEWMODE_IMG,
		         handler : viewClick,
		         handleMouseEvents :false,
		         icon: 'images/button_view/view_tubiao.jpg'	         	        	 
			}
		]		
	}
);
var _file_view_fun;
var _file_imgView_pageSize = "";
//视图按钮单击事件
function viewClick(button){
	var clickBtnId = button.id;
	_file_view_fun = clickBtnId;
	file_imgViewPageSize();
	changeViewBtnStyle(clickBtnId);
	global_CUR_FILE_VIEWMODE = clickBtnId;
	Ext.getCmp('showFile').
	load({url:'file_' + clickBtnId + '.action?showFile=' + clickBtnId +
			'&folderUid=' + '${folderUid}' + _file_imgView_pageSize,scripts:true});	
}

function file_imgViewPageSize(){
	if('${showFile}' == global_FILE_VIEWMODE_IMG || _file_view_fun == global_FILE_VIEWMODE_IMG){
		//减去左边区域
		var column = _leftWinWidth - 260;
		//行数
		var rows =  parseInt((_leftWinHeight - 180) / 165);
		//除以每个图标的宽度，得到列个数,只显示3行
		_file_imgView_pageSize = parseInt(column / 160);
		_file_imgView_pageSize = "&pageSize=" + _file_imgView_pageSize * rows;
	}
}

//改变视图按钮样式
function changeViewBtnStyle(btnId){	
	if(btnId == global_FILE_VIEWMODE_LIST){
		$('#' + global_FILE_VIEWMODE_IMG).attr('class','');
		$('#' + global_FILE_VIEWMODE_IMG).attr('class','x-btn x-btn-text-icon');	
	}else{
		$('#' + global_FILE_VIEWMODE_LIST).attr('class','');
		$('#' + global_FILE_VIEWMODE_LIST).attr('class','x-btn x-btn-text-icon');
	}	
	$('#' + btnId).attr('class','x-btn x-btn-focus x-btn-text-icon x-btn-over');
}

Ext.onReady(function(){	
	 if(_file_imgView_pageSize == ""){
		 file_imgViewPageSize();
		
	 }
	 new Ext.Panel({
		//title: '文件资源立方后台管理',		
        border: false,        
        renderTo: 'operationDomain',
		tbar:[
			      uploadButton,
			      addFolderButton,
			      reloadButton,
			      editButton,
			      deleteButton,			      
			      moveButton,
			      /*
			      authorizeButton,//去掉授权按钮，文件夹使用ac授权，去掉文件的授权；
			      */
			      /*
			      adapterButton,
			      */
			      /*moreOperaButton,*/
			      '->',
			      viewButton
		      ],
	 	items: {	
				id:"showFile",
				margins:'5 0 0 0',
				layout: 'fit',  
				border: false,				
				autoLoad:{url:'file_${showFile}.action?showFile=${showFile}&folderUid=' + file_jsp_folderUid + _file_imgView_pageSize,scripts:true}
		}
	 });
});
	changeViewBtnStyle('${showFile}');
	
</script>
</head>
<body>
	<div id="operationDomain">
	
	</div>
	<div id="showFileDiv">
	</div>
	
	<div id="shareDialogParent">
		<!-- 共享对话框 -->	
	</div>
</body>
</html>