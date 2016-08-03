<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
	<title></title>
	<script language="javascript" type="text/javascript">
			var resourceTree = new Ext.tree.TreePanel({
			  id: 'resource-tree',
			  rootVisible: false,
			  renderTo: 'resource-tree-div',
			  animCollapse: false,
			  animate: false,
			  height: Ext.getCmp('treeTabPanel').getInnerHeight(),
			  autoScroll: true, 
			  containerScroll: true,
			  border: false,
			  collapseFirst: true,
			  enableDD: false,
			  //lines: true,
			  useArrows: true,
			  loader: new Ext.tree.TreeLoader({   
				  dataUrl:'/operlist.do?id=${empty param.id?'_OlwmEQXnEeWkVNsIwPc8Aw': param.id}'
			  }),  //tree.json   
			  tbar:[' ',
					new Ext.form.TextField({
						width:150,
						emptyText:'快速检索',
						enableKeyEvents: true,
						listeners:{
							keyup:function(node, event) {
								findByKeyWordFiler(node, event);
							},
							scope: this
						}
					})
				],
			  root: new Ext.tree.AsyncTreeNode({
								id: 'root',
								draggable: false,
								autoExpanded: true,

								listeners:{
								  load:function(node){
									resourceTree.body.unmask();
								}}
			  })
			});   
			
			var timeOutId = null;

			var treeFilter = new Ext.tree.TreeFilter(resourceTree, {
				clearBlank : true,
				autoClear : true
			});
			// 保存上次隐藏的空节点
			var hiddenPkgs = [];
			var findByKeyWordFiler = function(node, event) {
				clearTimeout(timeOutId);// 清除timeOutId
				resourceTree.expandAll();// 展开树节点
				// 为了避免重复的访问后台，给服务器造成的压力，采用timeOutId进行控制，如果采用treeFilter也可以造成重复的keyup
				timeOutId = setTimeout(function() {
					// 获取输入框的值
					var text = node.getValue();
					// 根据输入制作一个正则表达式，'i'代表不区分大小写
					var re = new RegExp(Ext.escapeRe(text), 'i');
					// 先要显示上次隐藏掉的节点
					Ext.each(hiddenPkgs, function(n) {
						n.ui.show();
					});
					hiddenPkgs = [];
					if (text != "") {
						treeFilter.filterBy(function(n) {
							// 只过滤叶子节点，这样省去枝干被过滤的时候，底下的叶子都无法显示
							return !n.isLeaf() || re.test(n.text);
						});
						// 如果这个节点不是叶子，而且下面没有子节点，就应该隐藏掉
						resourceTree.root.cascade(function(n) {
							if(n.id!='0'){
								if(!n.isLeaf() &&judge(n,re)==false&& !re.test(n.text)){
									hiddenPkgs.push(n);
									n.ui.hide();
								}
							}
						});
					} else {
						treeFilter.clear();
						return;
					}
				}, 500);
			}
			// 过滤不匹配的非叶子节点或者是叶子节点
			var judge =function(n,re){
				var str=false;
				n.cascade(function(n1){
					if(n1.isLeaf()){
						if(re.test(n1.text)){ str=true;return; }
					} else {
						if(re.test(n1.text)){ str=true;return; }
					}
				});
				return str;
			};
			
			  resourceTree.getRootNode().expand(true,false);

			resourceTree.on('click', function(node,event){
                //alert(Ext.getCmp('centerLayout').getInnerHeight()+200);//node.attributes.url 
				if(node.isLeaf()){
				if(node.attributes.openType == "panel"){

					var height=Ext.getCmp('centerLayout').getInnerHeight();
					Ext.getCmp('centerLayout').removeAll(true);
					Ext.getCmp("centerLayout").add({
					//url: node.attributes.url,
					//	scripts:true						
					region: 'center',
					html: '<iframe width=100% height='+height+' name=aa frameborder=0 src='+node.attributes.url+'></iframe>',
					autoHeight: true,
					border: false,
					margins: '0 0 5 0'
				//		params: {node:node.id, type:node.attributes.type}
					});//node.id, node.attributes.orgType
					Ext.getCmp("centerLayout").doLayout();
					
					return ;
				}
				window.open(node.attributes.url);
  			    
				}
			});
	</script>
	</head>
	<body>
		<div id="resource-tree-div"></div>
	</body>
</html>