<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title></title>

<script language="javascript" type="text/javascript">
var infoTree = new Ext.tree.TreePanel({
  id: 'info-tree',
  rootVisible: false,
  renderTo: 'info-tree-div',
  padding: '5 5 5 5',
  animCollapse: false,
  animate: false,
  height: Ext.getCmp('infoTreePanel').getInnerHeight(),
  autoScroll: true,
  containerScroll: true,
  border: false,
  collapseFirst: true,
  enableDD: false,
  lines: true,
  useArrows: true,
  //singleExpand: true,
  tbar:['->',{
            tooltip:'刷新',
            iconCls:'refresh-icon',
            handler: function(){
            	infoTree.body.mask('Loading...', 'x-mask-loading');
            	infoTree.getRootNode().reload();
            }
         },{
        	 tooltip:'折叠',
             iconCls:'collapse-all-icon',
             handler: function() {
            	 infoTree.collapseAll();
             }
         }],
  loader: new Ext.tree.TreeLoader({   
	  dataUrl:'/info/infoManagerTree.action',
	  listeners: {
	    beforeload: function(treeLoader, node) {   
 			treeLoader.baseParams.type = node.attributes.type;
	    }
	  }
  }),
  root: new Ext.tree.AsyncTreeNode({
    				id: 'root',
			        draggable: false,
			        expanded: true,
			        listeners:{
				          load:function(node){
				        	infoTree.body.unmask();
				        }}
  })
});

infoTree.on('click', function(node,event){
	Ext.getCmp('centerLayout').removeAll(true);
	var type = node.attributes.type;
	var id = node.id;
	if (type == 'infoContainer'){
		Ext.getCmp("centerLayout").load({
	   		url: '/info/containerAction.action',
	   		scripts:true,
	   		params: {node:id, operation:'display'}
	   	});
	}
	if (type == 'information'){
		Ext.getCmp("centerLayout").load({
	   		url: '/info/informationAction.action',
	   		scripts:true,
	   		params: {node:id, operation:'display'}
	   	});
	}
});

</script>

</head>
<body>
<div id="info-tree-div"></div>
</body>
</html>