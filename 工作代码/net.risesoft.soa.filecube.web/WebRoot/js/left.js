
/**
 * 文件列表视图页面的js文件
 */
var _leftWinWidth = 0;  
var _leftWinHeight = 0; 
//获取浏览器高、宽
function findDimensions() {  
	//获取窗口宽度  
	if (window.innerWidth)  
		_leftWinWidth = window.innerWidth;  
	else if ((document.body) && (document.body.clientWidth))  
		_leftWinWidth = document.body.clientWidth;  
	//获取窗口高度  
	if (window.innerHeight)  
		_leftWinHeight = window.innerHeight;  
	else if ((document.body) && (document.body.clientHeight))  
		_leftWinHeight = document.body.clientHeight;  
	//通过深入Document内部对body进行检测，获取窗口大小  
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth){  
		_leftWinHeight = document.documentElement.clientHeight;  
		_leftWinWidth = document.documentElement.clientWidth;  
	}
	//alert("高度:" + winHeight + " 宽度：" + winWidth);
}  
findDimensions();  
//调用函数，获取数值  
window.onresize=findDimensions; 

//左边整体树的布局的定义
leftTree = new Ext.TabPanel({
	deferredRender: false,
	border: false,	
	activeTab: 0
});

var folderTreePanel = new Ext.Panel({
	title: '文件夹展示',	
	border: false,
	layout:"accordion",	
	layoutConfig:{
	    animate:true
	}
});
//文件夹展示 
leftTree.add(folderTreePanel);

//文件夹展示---我的文件
var personnelFolderTreePanel = new Ext.Panel({
	xtype:"panel",
	title:"个人中心",	
	autoScroll: true,
	height:_leftWinHeight - 200,
	border: false,
	contentEl: 'personnelFolderTreeParentTable',
	listeners: {
		activate:function(p,state){
			refreshPersonnelTree();
		}
	}
});
//个人中心树
var loader= new Ext.tree.TreeLoader( {
	dataUrl: 'folder_personnelFolderTree.action',
	params: {folderUid: global_CUR_SESSION_USER_UID}
});
//加载个人树节点
loader.on('beforeload',function(loader,node){
	  if(node.id == 'myFiles') {
		  this.baseParams.folderUid = global_CUR_SESSION_USER_UID;
	  }else{
		  this.baseParams.folderUid = node.id;
	  }	  
},loader);
/**
 * 个人文件树
 */
var personnelFolderTree = new Ext.tree.TreePanel({
  useArrows: true,    
  animate: true,
  rootVisible:false,
  containerScroll: true,
  border: false, 
  root: {
      nodeType: 'async',        
      draggable: false,        
      id:'myFiles',
      loader: loader
  },listeners: {
      click:personnelTreeNodeClick
  }
});
/**
 * 个人tree点击事件
 * @param node -- 单击的树节点
 */
function personnelTreeNodeClick(node){
	global_FOLDER_TREE_TYPE_CUR = global_FOLDER_TREE_TYPE_PERSONNEL;
	var nodeId = node.id;	
		Ext.getCmp('index-center-panel').
  	load({url:'file_showFile.action?showFile=' + global_CUR_FILE_VIEWMODE + '&folderUid=' + nodeId,scripts:true});			
}
/**
 * 刷新个人文件树
 */
function refreshPersonnelTree(){
	global_FOLDER_TREE_TYPE_CUR = global_FOLDER_TREE_TYPE_PERSONNEL;
	$('.personnelCenterOption').each(function(){
		$(this).css('background-color','');
		$(this).attr('click','');
	});
	//personnelFolderTree.body.mask('Loading...', 'x-mask-loading');	
	loader.baseParams.folderUid = global_CUR_SESSION_USER_UID;
	personnelFolderTree.getRootNode().reload();
	var rtnData = global_ajax("folder_getPersonnelFolderTreeRootUid.action",'',false);	
	uid = rtnData.uid; 
	Ext.getCmp('index-center-panel').
	load({url:'file_showFile.action?showFile=' + global_CUR_FILE_VIEWMODE +
		'&folderUid=' + uid,scripts:true});				
}

/**
 * ===修改对维度的查看方式===
 * 修改为只有管理员组才能看见。
 */
if(global_CUR_SESSION_USER_ORGTYPE == global_USER_ADMIN_TYPE){
	//多维度展示
	leftTree.add({
		//contentEl: 'dimensionTab',	
		title: '多维度展示',
		id:'dimensionExtTab',
		border: false,
		autoScroll: true,
		autoLoad:{
			url:'dimension_preDimension.action',
			scripts:true
		}
	});
}

Ext.onReady(function(){
	var rtnData = global_ajax("permission_getResourceRoots.action",{},false);
	Ext.getCmp('index-center-panel').load({url:'file_showFile.action?showFile=' + global_CUR_FILE_VIEWMODE + '&folderUid=' +
	((rtnData == undefined || rtnData == '') ? global_CUR_SESSION_USER_UID : rtnData[0].id),scripts:true});
	
	for(var i = 0; i < rtnData.length;i++){
		folderTreePanel.add(new Ext.Panel({
			xtype:"panel",
			title:rtnData[i].name,
			id:rtnData[i].id,
			autoScroll: true,
			height:_leftWinHeight - 200,
			border: false,
			autoLoad:{
				url:'permission_preSubResourcesTree.action?folderUid=' + rtnData[i].id,
				scripts:true
			},
			listeners: {
				activate:function(p,state){		
					if('' != global_CUR_FOLDER_ROOT_UID){
						//如果不自动刷新树，节点可能加载不同步，手动不如自动(就是每次从树根开始，？)。
						var tree = Ext.getCmp('leftFolderTree_' + global_CUR_FOLDER_ROOT_UID);				 		
				 		tree.getRootNode().reload();
					}
					global_CUR_FOLDER_ROOT_UID = p.id;
					global_FOLDER_TREE_TYPE_CUR = 'leftFolderTree_' + p.id;
					Ext.getCmp('index-center-panel').load({url:'file_showFile.action?showFile=' + global_CUR_FILE_VIEWMODE 
						+ '&folderUid=' + p.id,scripts:true});					
				}
			}
		}));
	}
	//如果只有个人文件树，则刷新个人文件夹的右侧列表
	if(rtnData.length == 0){
		refreshPersonnelTree();
	}
	//添加个人文件夹树
	folderTreePanel.add(personnelFolderTreePanel);
	personnelFolderTree.render("personnelFolderTree");	
})
/**
 * 刷新左侧树 及右侧列表
 * @param folderUid -- 当前文件夹uid(父文件夹)
 */
function _left_refreshTreeAndList(folderUid){
	var _isPersonnelFolder = (global_FOLDER_TREE_TYPE_CUR == global_FOLDER_TREE_TYPE_PERSONNEL);
 	if(_isPersonnelFolder){ 		
 		loader.baseParams.folderUid = folderUid; 		
 		var cur_node = personnelFolderTree.getNodeById(folderUid);
 		if(cur_node != undefined){
 			cur_node.reload();
 		}else{
 			personnelFolderTree.getRootNode().reload();
 		}
 	}else{ 		 		
 		var tree = Ext.getCmp('leftFolderTree_' + global_CUR_FOLDER_ROOT_UID);
 		var cur_node = tree.getNodeById(folderUid);
 		if(cur_node != undefined){
 			cur_node.reload();
 		}else{
 			tree.getRootNode().reload();
 		} 		
 	}
 	//计算图标视图的 行数及列数
	file_imgViewPageSize();
 	Ext.getCmp('index-center-panel').
	load({url:'file_showFile.action?showFile=' + global_CUR_FILE_VIEWMODE 
		+ '&folderUid=' + folderUid,scripts:true});	
}
