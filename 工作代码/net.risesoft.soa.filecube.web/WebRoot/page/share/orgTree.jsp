<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
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
 <!--[if lt IE 8]>
	<script type="text/javascript">
		$(function(){
			$('#org-tree-di').css('margin-top','-15px');
		});
	</script>
 <![endif]--> 
<script type="text/javascript">
	var sharedOrgUids;
	$.ajax({
		  type: 'POST',
		  url: 'share_getShareOrgUid.action',
		  data: {'fileShare.fileInfo.fileUid':'${fileShare.fileInfo.fileUid }',
			'fileShare.fileFolder.folderUid':'${fileShare.fileFolder.folderUid }'},
		  dataType: 'JSON',
		  async:false,
		  success: function(data){
			  $('#org-tree-di').html('');
			  sharedOrgUids = data;
		  },
		  error:function(){
			  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
		  }			  
	});
	Ext.onReady(function(){
		var orgTree = new Ext.tree.TreePanel({			  
			rootVisible: false,
			  renderTo: 'org-tree-di',
			  id:'share_OrgTeee',
			  animCollapse: false,
			  animate: false,
			  height: 350,
			  autoScroll: true,
			  containerScroll: true,
			  border: false,
			  collapseFirst: true,
			  enableDD: false,
			  lines: true,
			  useArrows: true,
			  listeners: {
		          checkchange:function(node,checked){
		        	  eachTree(node,checked);
		          },load:function(nodep){		
		        	  //勾选已共享的组织
	        		  nodep.eachChild(function(node){		        			 
	        			  for(var i = 0;i < sharedOrgUids.length;i++){
	        				if(node.id == sharedOrgUids[i]){	        					
	        					node.attributes.checked=true;
			        		}
	        			  }
        			  });		        	  
		          } 
		      },
			  tbar:['搜索:',new Ext.form.TextField({
			    		id: 'org-search',
			        	width: 140,
			        	emptyText:'Name'
			         }),' ',{
			            tooltip:'搜索',
			            icon:'/framework/images/search.png',
			            handler: function(){
			            	var searchValue = Ext.getCmp("org-search").getValue();
			            	if (searchValue != ''){
			            		orgTree.body.mask('Searching...', 'x-mask-loading');
			            		orgTree.loader.baseParams = {searchValue:searchValue};
			            		orgTree.getRootNode().reload();    
			            	}
			            }
			         },{
			            tooltip:'重装载',
			            icon:'/framework/images/refresh.png',
			            handler: function(){
					    	orgTree.body.mask('Loading...', 'x-mask-loading');
					    	Ext.getCmp("org-search").setValue("");
					    	orgTree.loader.baseParams = {};
					    	orgTree.getRootNode().reload();
			            }
			         },{
			        	 tooltip:'折叠',
			             icon:'/framework/images/collapse-all.gif',
			             handler: function() {
			        	   orgTree.collapseAll();
			             }
			         }],
			  loader: new Ext.tree.TreeLoader({   
				  dataUrl:'<%=path%>/share_orgTree.action',
				  listeners: {
					    beforeload: function(treeLoader, node) {   
				 			treeLoader.baseParams.orgType = node.attributes.orgType;
					    }
				  }
			  }),
			  root: new Ext.tree.AsyncTreeNode({
    				id: 'root',
			        draggable: false,
			        expanded: true,
			        listeners:{
						load:function(node){
			            	orgTree.body.unmask();
			        	}
  					}
			  })
			});
		$('#buttonOrgTree').css('display','');
		
	});
	
	function orgTree_doShare(){		
		var orgTree = Ext.getCmp('share_OrgTeee');
		var checkedTreeNode = orgTree.getChecked('',orgTree.getRootNode());
		//提交后台的数据
		var postData = [];
		for(var i = 0;i < checkedTreeNode.length;i++){
			postData[i] = {};
			var dn = checkedTreeNode[i].attributes.dn;
			postData[i].fileUid = '${fileShare.fileInfo.fileUid }';
			postData[i].folderUid = '${fileShare.fileFolder.folderUid }';
			postData[i].orgUid = checkedTreeNode[i].id;
			postData[i].orgType = checkedTreeNode[i].attributes.orgType;		
		}
		if(checkedTreeNode.length == 0){
			postData[0] = {};
			postData[0].fileUid = '${fileShare.fileInfo.fileUid }';
			postData[0].folderUid = '${fileShare.fileFolder.folderUid }';
		}
		var rtnData = global_ajax("share_share.action",{shareDataJson:Ext.encode(postData)},false);
		if(rtnData == global_ajax_error){
			alert('共享失败！服务器出现异常！');
		}else{
			alert('共享成功！');
		}
	}
	
	/**
	 * 循环遍历维度预览树
	 * @param treeNode
	 */
	function eachTree(treeNode,checked){	
		treeNode.eachChild(function(node){		
			node.getUI().toggleCheck(checked);
			eachTree(node,checked);
		});		
	}
	
	var orgType = {Organization:'Organization',
			Department:'Department',
			Person:'Person'};
	
</script>
</head>
<body style="padding: 0px 0px;">	
	<div id="org-tree-di" style="margin: 0px 0px;">
		<div class="loading-indicator">加载中...</div>
	</div>	
</body>
</html>