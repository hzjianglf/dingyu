<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>共享列表</title>
<link rel="stylesheet" type="text/css" href="/org/css/org.css"/>
<style type="text/css">
.org-grid-row td{
    width:100%;
    height:25px;
  	vertical-align:middle;
}
.org-grid-panel{
	border-top:1px solid;
	border-right:1px solid;
	border-color:#EDEDED;
}
</style>
<%
	String times = UUID.randomUUID() + "";	
	times = times.replaceAll("-","").trim();  
%>
<script type="text/javascript">
	//文件夹树	
	Ext.onReady(function(){
		var leftFolderTree<%=times%> = new Ext.tree.TreePanel({
			  rootVisible: false,
			  renderTo: 'leftFolderTree<%=times%>',
			  useArrows: true,    
			  animate: true,
			  rootVisible:false,
			  containerScroll: true,
			  border: false, 
			  id:'leftFolderTree_${folderUid}',
			  tbar:['搜索:',new Ext.form.TextField({
			    		id: 'org-search<%=times%>',
			        	width: 140,
			        	emptyText:'Name'
			         }),' ',{
			            tooltip:'搜索',
			            icon:'/framework/images/search.png',
			            handler: function(){
			            	var searchValue = Ext.getCmp("org-search<%=times%>").getValue();
			            	if (searchValue != ''){
			            		leftFolderTree<%=times%>.body.mask('Searching...', 'x-mask-loading');
			            		leftFolderTree<%=times%>.loader.baseParams = {folderName:searchValue};
			            		leftFolderTree<%=times%>.getRootNode().reload();    
			            	}
			            }	
			         },{
			            tooltip:'重装载',
			            icon:'/framework/images/refresh.png',
			            handler: function(){
					    	leftFolderTree<%=times%>.body.mask('Loading...', 'x-mask-loading');
					    	Ext.getCmp("org-search<%=times%>").setValue("");
					    	leftFolderTree<%=times%>.loader.baseParams = {};
					    	leftFolderTree<%=times%>.getRootNode().reload();
					    	_left_refreshTreeAndList('${folderUid}');
			            }
			         },{
			        	 tooltip:'折叠',
			             icon:'/framework/images/collapse-all.gif',
			             handler: function() {
			        	   leftFolderTree<%=times%>.collapseAll();
			             }
			         }],
			  loader: new Ext.tree.TreeLoader({   
				  dataUrl:'<%=path%>/permission_getSubResourcesTree.action',
				  listeners: {
					    beforeload: function(treeLoader, node) {
					    	var searchValue = Ext.getCmp("org-search<%=times%>").getValue();
					    	if(node.id == 'root'){
					    		treeLoader.baseParams.folderUid = '${folderUid}';
							}else if(searchValue == ''){
								treeLoader.baseParams.folderUid = node.id;
							}
					    }
				  }
			  }),
			  root: new Ext.tree.AsyncTreeNode({
    				id: 'root',
			        draggable: false,
			        expanded: true,
			        listeners:{
						load:function(node){							
			            	leftFolderTree<%=times%>.body.unmask();
			        	}
  					}
			  }),
			  listeners: {
			        click:function(n){
			        	Ext.getCmp('index-center-panel').
			        	load({url:'file_showFile.action?showFile=' + global_CUR_FILE_VIEWMODE + '&folderUid=' + n.id,scripts:true});
			        }
			  }
			});		
	});
</script>
</head>
<body>	
	<div id="leftFolderTree<%=times%>">
		
	</div>	
</body>
</html>