Ext.ns('App.Service');

App.Service.Module = {};

App.Service.Module.store = new Ext.data.JsonStore({
			url : App.Context['RootPath'] + '/services/modules.json',
			fields : [{
						name : 'id'
					}, {
						name : 'name'
					}, {
						name : 'description'
					}, {
						name : 'version'
					}, {
						name : 'dependences'
					}]
		});
      
App.Service.Module.store.on('load', function(store, records, options) {
   var params = options.params;
   var repoTitle = params['text'];
   var repoName = params['repoName'];
   App.Service.Module.currentRepo = repoName;
//   var text = Ext.getCmp('module-repo-text');
//   if (text)
//      text.setText('服务仓库: ' + repoTitle);
//      
   var form = Ext.getCmp('module-form').getForm();
   App.Service.Module.switchFormState(form);
   
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
   App.Service.Module.switchActionState();
         
});
      
App.Service.Module.switchFormState = function(form, record) {
   if (App.Service.Module.currentRepo && record && record.data) {
      var currentRepo = App.Service.Module.currentRepo;
      if (currentRepo != 'asf-external-repository') {
         form.items.each(function(f) {
                  f.disable();
               });
      } else {
         form.items.each(function(f) {
                  f.enable();
                  if (record.data['id'] && f.name == 'id') {
                     f.setReadOnly(true);
                     // x-form-field x-item-disabled
                     f.el.addClass('x-item-disabled');
                  } else {
                     f.setReadOnly(false);
                     f.el.removeClass('x-item-disabled');
                  }                  
               });
      }
   }
};

App.Service.Module.addModule = new Ext.Action({
         text : '添加',
         iconCls : 'asf-add-icon',
         handler : function() {
            var form = Ext.getCmp('module-form').getForm();
            var record = {
               data : [],
               newrecord : true
            }
            App.Service.Module.switchFormState(form, record);
            App.Service.Module.switchActionState(record);
            form.items.each(function(f) {
                     f.setValue('');
                  });
                              
         }
      });

App.Service.Module.saveModule = new Ext.Action({
			text : '保存',
			iconCls : 'asf-save-icon',
			handler : function() {
				var form = Ext.getCmp('module-form').getForm();
				form.submit({
							waitMsg : '正在保存...',
							url : ctx + '/services/saveModule.do',
							params : {
								repoId : App.Service.Module.currentRepo
							},
							success : function() {
								App.Service.Module.store.reload();
								if (window.parent && window.parent.selectedTreeNode) {
									var node = window.parent.selectedTreeNode;
									node.reload();
								}
							}
						});
			}
		});

App.Service.Module.delModule = new Ext.Action({
         text : '删除',
         iconCls : 'asf-delete-icon',
         handler : function() {
            var grid = Ext.getCmp('module-grid-panel');
            var rec = grid.getSelectionModel().getSelected();
            if (rec) {
               var moduleName = rec.data['id'];
               var repoName = App.Service.Module.currentRepo;
               Ext.Msg.confirm('确认', '确定要删除服务模块 [' + moduleName + '] 吗? ', function(btn) {
                        if (btn == 'yes') {
                           $.ajax({
                                    url : ctx + '/services/delModule.do',
                                    type : 'POST',
                                    data : {
                                       moduleId : moduleName,
                                       repoId: repoName
                                       
                                    },
                                    success : function(result, status, xhr) {
                                       Ext.MessageBox.show({
                                                title : '提示',
                                                msg : '服务模块[' + moduleName + ']已删除.',
                                                width : 250,
                                                buttons : Ext.MessageBox.OK,
                                                icon : Ext.MessageBox.INFO
                                             });
                                       grid.getStore().reload();
                                       if(window.parent && window.parent.selectedTreeNode){
                                          var node = window.parent.selectedTreeNode;
                                          node.reload();
                                       }
                                    }
                                 });

                        }
                     });
            }
         }
      });
      
App.Service.Module.viewLog = new Ext.Action({
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

App.Service.Module.refreshModule = new Ext.Action({
         text : '刷新列表',
         iconCls : 'asf-refresh-icon',
         handler : function() {
            App.Service.Module.store.reload();
         }
      });
      
App.Service.Module.cancelOp = new Ext.Action({
         text : '取消',
         iconCls : 'asf-refresh-icon',
         handler : function() {
            var form = Ext.getCmp('module-form').getForm();
            var grid = Ext.getCmp('module-grid-panel');
            var rec = grid.getSelectionModel().getSelected();
            form.loadRecord(rec);
            App.Service.Module.switchFormState(form, rec);
            App.Service.Module.switchActionState(rec);
         }
      });
      
App.Service.Module.switchActionState = function(record) {
   if (App.Service.Module.currentRepo) {
		var currentRepo = App.Service.Module.currentRepo;
		if (currentRepo != 'asf-external-repository' ) {
			App.Service.Module.cancelOp.disable();
			App.Service.Module.delModule.disable();
			App.Service.Module.saveModule.disable();
			App.Service.Module.addModule.disable();
		} else if (record && record['newrecord'] == true) {
			App.Service.Module.cancelOp.enable();
			App.Service.Module.delModule.disable();
			App.Service.Module.saveModule.enable();
			App.Service.Module.addModule.enable();
		} else {
			App.Service.Module.cancelOp.enable();
			App.Service.Module.delModule.enable();
			App.Service.Module.saveModule.enable();
			App.Service.Module.addModule.enable();
		}
	}
};

App.Service.Module.selModel = new Ext.grid.RowSelectionModel({
         width : 25,
         singleSelect : true,
         listeners : {
            rowselect : function(sm, row, rec) {
               var form = Ext.getCmp('module-form').getForm();
               form.loadRecord(rec);
               App.Service.Module.switchFormState(form, rec);
               App.Service.Module.switchActionState(rec);
            }
         }
      });

App.Service.Module.store.on('load', function() {
         App.Service.Module.selModel.selectFirstRow();
      })

App.Service.Module.column = new Ext.grid.ColumnModel([/* App.Service.Module.selModel, */{
			id : 'id',
			header : 'ID',
			dataIndex : 'id',
         name : 'model.id'
		}, {
			header : '名称',
			dataIndex : 'name',
         name : 'model.name'
		}, {
			header : '说明',
			dataIndex : 'description',
         name : 'model.description'
		}, {
			header : '版本',
			dataIndex : 'version',
         name : 'model.version'
		}, {
			header : '依赖模块',
			dataIndex : 'dependences',
			renderer : function(v, m, r) {
				if (!v) return v;
				var result = '';
				$.each(v, function(key, value) {
							if (v[key] != null) {
								result += key + ':' + v[key] + ', ';
							}
						});
            return result;
			}
		}]);
// App.Service.Module.column.defaultSortable = true;

App.Service.Module.grid = new Ext.grid.GridPanel({
         id : 'module-grid-panel',
         region : 'center',
         height : 200,
         store : App.Service.Module.store,
         cm : App.Service.Module.column,
         sm : App.Service.Module.selModel,
         layout : 'fit',
         border : true,
         viewConfig : {
            forceFit : true
         },
         layoutConfig : {
            autoWidth : true,
            autoHeight : true
         },
         stripeRows : true,
         loadMask : true,
         tbar : ['-', /*{
                     id : 'module-repo-text',
                     text : '服务仓库: ',
                     xtype : 'tbtext'
                  },*/ '->', 
                  App.Service.Module.refreshModule, '-', 
                  App.Service.Module.viewLog, '-']
      });

App.Service.Module.form = new Ext.FormPanel({
         id : 'module-form',
         region : 'east',
         //frame : true,
         labelAlign : 'right',
         width : 350,
         // layout : 'fit',
         labelWidth : 90,
         // viewConfig : {
         // forceFit : true
         // },
         bodyStyle : 'padding:20px;background-color:#DFE8F6',
         defaultType : 'textfield',
         items : [{
						fieldLabel : '名称',
						name : 'id',
						allowBlank : false,
						anchor : '100%'
					}, {
						fieldLabel : '显示名',
						name : 'name',
						allowBlank : false,
						anchor : '100%'
					}, {
						fieldLabel : '版本',
						name : 'version',
						allowBlank : false,
						anchor : '100%'
					}, new Ext.form.TextArea({
								fieldLabel : '模块说明',
								name : 'description',
								allowBlank : true,
								anchor : '100%'
							})],
         tbar : [App.Service.Module.addModule, '-', 
                 App.Service.Module.saveModule, '-', 
                 App.Service.Module.delModule, '-', 
                 App.Service.Module.cancelOp]
      });

App.Service.Module.mainPanel = new Ext.Panel({
         id : 'module-main-panel',
         title : '服务模块',
         iconCls : 'asf-module-node',
         border : false,
         layout : 'border',
         items : [App.Service.Module.form, App.Service.Module.grid]
      });

App.Service.Module.mainPanel.on('activate', function() {
         // console.log('Repo activate');
         //App.Service.Module.store.reload();
      });
