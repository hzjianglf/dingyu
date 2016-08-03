<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择文件夹</title>
<script type="text/javascript">
//文件夹树
var folderTree;
var folderUid = "";	
var dataUrl = "";
if(global_FOLDER_TREE_TYPE_CUR == global_FOLDER_TREE_TYPE_PERSONNEL){
	folderUid = global_CUR_SESSION_USER_UID;
	dataUrl = 'folder_personnelFolderTree.action';
}else{
	folderUid = global_CUR_FOLDER_ROOT_UID;
	dataUrl = 'permission_getSubResourcesTree.action';
}
Ext.onReady(function(){		
	folderTree = new Ext.tree.TreePanel({	   
	    useArrows: true,
	    id:'selectMoveFolderTree',
	    renderTo:'selectFolderTree',
	    autoHeight: true,	    
	    animate: true,
	    enableDD: true,
	    containerScroll: true,
	    loader: new Ext.tree.TreeLoader({   
			  dataUrl:dataUrl,
			  listeners: {
				    beforeload: function(treeLoader, node) {				    	
				    	if(node.id == 'root'){			    		
				    		treeLoader.baseParams.folderUid = folderUid;
						}else{
							treeLoader.baseParams.folderUid = node.id;
						}
				    }
			  }
		}),
	    border: false,
	    root: new Ext.tree.AsyncTreeNode({
	        expanded: true, 
	        draggable: false,
	        id:'root',
	        expanded:true
	    }),
	    rootVisible: false,
	    tbar : [{text : '确定',width: 240,handler:folderSelect}]
	});	
	$('#loading').remove();
});
function folderSelect(){
	var folderUid = folderTree.getSelectionModel().getSelectedNode().id;
	moveFiles('${batchUids}',folderUid);	
}
</script>
</head>
<body>
	<div id="selectFolderTree">
		<div class="loading-indicator" id="loading">加载中...</div>
	</div>
</body>
</html>