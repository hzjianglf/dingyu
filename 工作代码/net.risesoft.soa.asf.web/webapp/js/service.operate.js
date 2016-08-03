Ext.ns('App.Service');

App.Service.Operate = {};

App.Service.Operate.store = new Ext.data.JsonStore({
			url : ctx + '/services/operates.json',
			fields : [{
						name : 'name'
					}, {
						name : 'chsname'
					}, {
						name : 'params'
					}, {
						name : 'returnType'
					}, {
						name : 'methodDesc'
					}, {
						name : 'paramNames'
					}, {
						name : 'errors'
					}, {
						name : 'result'
					}]
		});
      
App.Service.Operate.store.on('load', function(store, records, options) {
   var params = options.params;
   var repoName = params['repoName'];
   var moduleName = params['moduleName'];
   var compName = params['compName'];
   App.Service.Operate.currentRepo = repoName;
   App.Service.Operate.currentModule = moduleName;
   App.Service.Operate.currentComp = compName;
      
   var form = Ext.getCmp('comp-form').getForm();
   App.Service.Comp.switchFormState(form);
   
   if((!records) || records.length == 0){
       form.loadRecord(new Ext.data.Record({
            id : ' ',
            name : ' ',
            description : ' ',
            version : ' '
       }));
       form.items.each(function(f) {
            f.disable();
            f.el.addClass('x-item-disabled');
       });
   }
   App.Service.Comp.switchActionState();
   
   var currentServicePanel = Ext.getCmp('current-service-comp');
	if (currentServicePanel) {
		currentServicePanel.setText('服务组件 ID: ' + App.Service.Operate.currentComp + '   ');
	}
         
});
        
App.Service.Operate.viewLog = new Ext.Action({
         text : '查看服务日志',
         iconCls : 'asf-servicelog-node',
         handler : function(){
            var top = window.parent;
            var Share = top.Share;
            var Index = top.App.Index;
            Share.openTab(
                  {id:'_servicelog', text:'服务日志'}, 
                  ctx + '/services/log.html', 
                  Index.tabPanel, 
                  'asf-servicelog-node'/*, 
                  {a:'b'}*/
            )
         }
});    

App.Service.Operate.viewWsdl = new Ext.Action({
         text : '查看 WSDL',
         iconCls : 'asf-wsdl-icon',
         handler : function(){
            //view wsdl
            var repoName = App.Service.Operate.currentRepo;
            var moduleName = App.Service.Operate.currentModule;
            var compName = App.Service.Operate.currentComp;
            window.open(ctx + '/services/viewWsdl.do?repoName=' + repoName 
                  + '&moduleName=' + moduleName
						+ '&compName=' + compName);
         }
});

App.Service.Operate.refreshOperate = new Ext.Action({
         text : '刷新列表',
         iconCls : 'asf-refresh-icon',
         handler : function() {
            App.Service.Operate.store.reload();
         }
      });
      

App.Service.Operate.selModel = new Ext.grid.RowSelectionModel({
			width : 25,
			singleSelect : true
		});

App.Service.Operate.store.on('load', function() {
			App.Service.Operate.selModel.selectFirstRow();
		})

App.Service.Operate.rowExpander = new Ext.ux.grid.RowExpander({
			tpl : new Ext.Template('<p style="text-indent: 50px"><b>服务描述:&nbsp;&nbsp;</b> {methodDesc}</p>',
					'<p style="text-indent: 50px"><b>参数说明:&nbsp;&nbsp;</b> {paramNames}</p>',
					'<p style="text-indent: 50px"><b>异常说明:&nbsp;&nbsp;</b> {errors}</p>',
					'<p style="text-indent: 50px"><b>返回值说明:&nbsp;&nbsp;</b> {result}</p>'),
			lazyRender : true,
			getDataFn : function(record, expander, callback) {
				Ext.Ajax.request({
							url : ctx + '/services/operateDetail.json',
                     params : {
                     },
							success : function(response) {
								//var data = Ext.decode(response.responseText);
								record.data.detailDataLoaded = true;
								//record.data['methodDesc'] = data.methodDesc;
                        //record.data['paramNames'] = data.paramNames;
                        //record.data['errors'] = data.errors;
                        //record.data['result'] = data.result;
								record.commit();

								if (callback) {
									callback(expander);// 一定要回调该函数，否则不能展开
								}
							},
							failure : function() {
								if (callback) {
									callback(expander);
								}
							}
						})
			}

		});

App.Service.Operate.column = new Ext.grid.ColumnModel([App.Service.Operate.rowExpander, {
			id : 'name',
			header : '名称',
			dataIndex : 'name'
		}, {
			header : '说明',
			dataIndex : 'chsname',
         renderer : function(v, m, r) {
				m.attr = 'ext:qtitle="" ext:qtip="' + v + '"';
            
            return v;
			}
		}, {
			header : '参数列表',
			dataIndex : 'params'
		}, {
			header : '返回值类型',
			dataIndex : 'returnType'
		}]);

App.Service.Operate.grid = new Ext.grid.GridPanel({
			// id : 'operate-grid-panel',
			id : 'operate-main-panel',
			// region : 'center',
			title : '服务操作',
			frame : false,
			iconCls : 'asf-service-operate-icon',
			store : App.Service.Operate.store,
			cm : App.Service.Operate.column,
			sm : App.Service.Operate.selModel,
			plugins : App.Service.Operate.rowExpander,
			// layout : 'fit',
			border : false,
			viewConfig : {
				forceFit : true
			},
			layoutConfig : {
				autoWidth : true,
				autoHeight : true
			},
			stripeRows : true,
			loadMask : true,
			tbar : ['-', {iconCls : 'asf-service-comp-icon'}, {
                  id : 'current-service-comp',
						text : '',
						xtype : 'tbtext'
					}, '-', 
               App.Service.Operate.viewWsdl, '->', 
               App.Service.Operate.refreshOperate, '-',
					App.Service.Operate.viewLog, '-']
		});

App.Service.Operate.mainPanel = App.Service.Operate.grid;/*
															 * new Ext.Panel({
															 * id :
															 * 'operate-main-panel',
															 * title : '服务操作',
															 * iconCls :
															 * 'asf-service-operate-icon',
															 * border : false,
															 * layout :
															 * 'border', items :
															 * [App.Service.Operate.grid]
															 * });
															 */

App.Service.Operate.mainPanel.on('activate', function() {
			// console.log('Repo activate');
			// App.Service.Comp.store.reload();
			// alert();
		});
