var ctx = App.Context['RootPath'];

App.Index = {
	header : '/framework/layout/head.jsp',
	sysmgrTreeUrl : ctx + "/sysmgrTree.json",
   serviceTreeUrl : ctx + '/serviceTree.json',
	monitor : ctx + '/monitor',
	log : ctx + '/services/log.html',
   hideHeader : isConsole,
   hideCopyright : isConsole,
   hideSysmgrTree : !isSysmgrTree,
   hideServiceTree : !isServiceTree
}

var Index = App.Index;

var isServiceNode = function(node) {
	if (node.id == '_services') {
		return true;
	}
	var p = node.parentNode;
	if (p) {
		if (p.id == '_services') {
			return true;
		} else {
			return isServiceNode(p);
		}
	}
	return false;
}

// 头部
Index.headerPanel = new Ext.Panel({
			region : 'north',
			border : false,
			margins : '0 0 0 0',
			maxSize : 87,
			height : 87,
         hidden : App.Index.hideHeader, //如果/asf/index.do?console=true, 那么隐藏headerPanel
			bodyStyle : 'background-color:transparent;',
			autoLoad : {
				url : Index.header,
				scripts : true,
				nocache : true
			}
		});

Index.sysmgrTree = new Ext.tree.TreePanel({
			useArrows : false,
			autoScroll : true,
			animate : true,
			containerScroll : true,
			border : false,
			rootVisible : false,
			hidden : App.Index.hideSysmgrTree,
			title : '系统管理',
			iconCls : 'asf-sysmgr-icon',
			margins : '2 2 2 2',
			loader : new Ext.tree.TreeLoader({
						dataUrl : Index.sysmgrTreeUrl,
						clearOnLoad : true
					}),
			root : {
				expanded : true,
				id : '_root'
			},
			listeners : {
				'click' : function(node, e) { // 点击事件
					if (node.attributes.url) { // 如果是链接 node.isLeaf()
						Share.openTab(node, node.attributes.url, Index.tabPanel);
					} else {
						e.stopEvent();
					}
				}
			}
		});

Index.serviceTree = new Ext.tree.TreePanel({
         useArrows : false,
         autoScroll : true,
         animate : true,
         containerScroll : true,
         hidden : App.Index.hideServiceTree,
         title : '服务管理',
         iconCls : 'asf-service-icon',
         border : false,
         rootVisible : false,
         margins : '2 2 2 2',
         loader : new Ext.tree.TreeLoader({
                  dataUrl : Index.serviceTreeUrl,
                  clearOnLoad : true
               }),
         root : {
            expanded : true,
            id : '_root'
         },
         listeners : {
            'click' : function(node, e) { // 点击事件
               var _node = (isServiceNode(node)) ? {
						id : '_services',
						text : '服务管理',
                  parentNode : node.parentNode,
						attributes : node.attributes
					} : node;
               var params = node.attributes;
               if(node.id.indexOf('_services') == 0){
                  params['_TargetType'] = 'repo';
               }
               if(node.id.indexOf('_repo_') == 0){
                  var repoId = node.attributes['rid'];
                  params['_TargetType'] = 'module';
                  params['repoName'] = repoId;
               }
               if(node.id.indexOf('_module_') == 0){
                  var moduleId = node.attributes['rid'];
                  var repoId = node.parentNode.attributes['rid'];
                  params['_TargetType'] = 'comp';
                  params['repoName'] = repoId;
                  params['moduleName'] = moduleId;
               }
               if(node.id.indexOf('_comp_') == 0){
                  var parentNode = node.parentNode;
                  var repoId = parentNode.parentNode.attributes['rid'];
                  var moduleId = parentNode.attributes['rid'];
                  var compId = node.attributes['rid'];
                  params['_TargetType'] = 'operate';
                  params['repoName'] = repoId;
                  params['moduleName'] = moduleId;
                  params['compName'] = compId;
               }
               var _iconCls = (isServiceNode(node)) ? 'asf-service-icon' : node.attributes.iconCls;
					if (node.attributes.url) { // 如果有链接 node.isLeaf()
						Share.openTab(_node, 
                                node.attributes.url, 
                                Index.tabPanel, 
                                _iconCls,
								        params);
                  window.selectedTreeNode = node;                                
					} else {
						e.stopEvent();
					}
            }
         }
      });      
      
// 菜单面板
Index.menuPanel = new Ext.Panel({
			region : 'west',
			title : '主菜单',
			iconCls : 'home',
			margins : '0 0 0 0',
			//layout : 'fit',
         layout : 'accordion',
         activeItem : App.Index.hideServiceTree ? 1 : 0,
         layoutConfig : {
				animate : true
			},         
			width : 200,
			minSize : 100,
			maxSize : 300,
			split : true,
			collapsible : true,
			tools : [{
						id : 'refresh',
						handler : function() {
							Index.sysmgrTree.root.reload();
                     Index.serviceTree.root.reload();
						}
					}],
			items : [Index.serviceTree, Index.sysmgrTree]
		});

// tab主面板
Index.tabPanel = new Ext.TabPanel({
			id : 'mainTabPanel',
			region : 'center',
			activeTab : 0,
			deferredRender : false,
			enableTabScroll : true,
			// bodyStyle:'height:100%',
			defaults : {
				layout : 'fit',
				autoScroll : true
			},
			plugins : new Ext.ux.TabCloseMenu({
						closeTabText : '关闭标签页',
						closeOtherTabsText : '关闭其他标签页',
						closeAllTabsText : '关闭所有标签页'
					}),
			items : [
			   App.Index.hideSysmgrTree 
			   ? {
		           id : '_servicelog_div_panel',
	               title : '服务日志',
	               iconCls : 'asf-servicelog-node',
	               closable : true,
	               autoScroll : true,
	               html : '<iframe id="_servicelog_div_panel-iframe" src="' + Index.log
	               + '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'		            
			   }  
			   : {
				id : '_monitor_div_panel',
				title : '性能监控',
				iconCls : 'asf-monitor-node',
				closable : true,
				autoScroll : true,
				html : '<iframe id="_monitor_div_panel-iframe" src="' + Index.monitor
						+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
			}],
			listeners : {
				'bodyresize' : function(panel, neww, newh) {
					// 自动调整tab下面的panel的大小
					var tab = panel.getActiveTab();
					var centerpanel = Ext.getCmp(tab.id/* + "_div_panel" */);
					if (centerpanel) {
						centerpanel.setHeight(newh - 2);
						centerpanel.setWidth(neww - 2);
					}
				}
			}
		});
      
Index.copyrightPanel = new Ext.Panel({
         region : 'south',
         border : false,
         hidden : App.Index.hideCopyright, //如果/asf/index.do?console=true, 那么隐藏copyrightPanel
         //frame : false,
         minSize : 100,
         maxSize : 100,
//         collapsible : true,
//         collapsed : true,
//         split : true,
         html : '<div style="margin: 0 auto;width: 200px;text-align:center;">版权所有</div>'
      });   

// 初期化页面Layout
Index.viewport = new Ext.Viewport({
			layout : 'border',
			items : [Index.headerPanel, Index.menuPanel, Index.tabPanel, Index.copyrightPanel/*
																		 * ,
																		 * Index.msgPanel
																		 */]
		});
