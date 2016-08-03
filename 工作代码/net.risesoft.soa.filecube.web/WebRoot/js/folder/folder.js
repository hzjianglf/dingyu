/**
 * 添加文件夹
 */
function preAddFolder(){	
	var url = 'folder_preAdd.action?folder.parentUid=' + file_jsp_folderUid;
	Ext.PopWin.init('新建文件夹', 300, 260);	
	popWin.load({		
		url : url,
		scripts : true
	});
}

/**
 * 修改文件夹
 * @param folderUid
 */
function preEditFolder(folderUid){
	var url = 'folder_preEdit.action?folder.folderUid=' + folderUid;
	Ext.PopWin.init('修改文件夹', 300, 260);	
	popWin.load({		
		url : url,
		scripts : true
	});
}
/**
 * 逻辑删除文件夹
 * @param folderUid
 */
function logicDeleteFolder(folderUid){
	Ext.Msg.confirm('提示', '确定删除该文件夹吗？',function(button,text){
		if(button == 'yes'){			
			$.ajax({
			  type: 'POST',
			  url: 'folder_logicDelete.action',
			  data: {'folder.folderUid':folderUid},
			  dataType: 'JSON',
			  success: function(date){
				  Ext.Msg.alert('操作成功',date.success);     
			  },
			  error:function(){
				  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
			  }			  
			});
		}
	});
}

/**
 * 设置适配器
 * @param folderUid
 */
function setAadapter(folderUid){
	alert('setAadapter==' + folderUid);
}
/**
 * 目录导航
 * @param folderUid -- 文件夹的uid
 */
function nav(folderUid){
	if(!(global_FOLDER_TREE_TYPE_CUR == global_FOLDER_TREE_TYPE_PERSONNEL)){
		var allowed = permission_getPermissionByFolderUid(folderUid,global_OperationType_VIEW);
		if(!allowed){
			alert("对不起您没有查看此文件夹的权限！");
			return;
		}
	}
	Ext.getCmp('index-center-panel').load({url:'file_showFile.action?showFile=' + global_CUR_FILE_VIEWMODE + '&folderUid=' + folderUid
    	,scripts:true});
}
