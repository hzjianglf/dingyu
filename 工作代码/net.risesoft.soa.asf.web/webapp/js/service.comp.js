Ext.ns('App.Service');

App.Service.Comp = {};

App.Service.Comp.urlRegex = "^((https|http|ftp|rtsp|mms)?://)" + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                    + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                    + "|" // 允许IP和DOMAIN（域名）
                    + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
                    + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
                    + "[a-z]{2,6})" // first level domain- .com or .museum
                    + "(:[0-9]{1,4})?" // 端口- :80
                    + "((/?)|" // a slash isn't required if there is no file name
                    + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";

App.Service.Comp.store = new Ext.data.JsonStore({
         url : App.Context['RootPath'] + '/services/comps.json',
         fields : [{
                  name : 'id'
               }, {
                  name : 'name'
               }, {
                  name : 'interfaze'
               }, {
                  name : 'version'
               }, {
                  name : 'wsdl'
               }, {
                  name : 'helpUrl'
               }/*, {
                  name : 'state'
               }*/]
      });

      
App.Service.Comp.store.on('load', function(store, records, options) {
   var params = options.params;
   var repoName = params['repoName'];
   var moduleName = params['moduleName'];
   App.Service.Comp.currentRepo = repoName;
   App.Service.Comp.currentModule = moduleName;
      
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
         
});
      
App.Service.Comp.switchFormState = function(form, record) {
   if (record && record.data) {
      var data = record.data;
      var currentRepo = App.Service.Comp.currentRepo;
      if (currentRepo == 'asf-local-repository' || currentRepo != 'asf-external-repository') {
         form.items.each(function(f) {
                  f.disable();
               });
      } else {
         form.items.each(function(f) {
                  f.enable();
                  if (data['id'] && f.name == 'id') {
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

App.Service.Comp.addComp = new Ext.Action({
         text : '添加',
         iconCls : 'asf-add-icon',
         handler : function() {
            var form = Ext.getCmp('comp-form').getForm();
            var record = {
               data : [],
               newrecord : true
            }
            App.Service.Comp.switchFormState(form, record);
            App.Service.Comp.switchActionState(record);
            form.items.each(function(f) {
                     f.setValue('');
                  });
                              
         }
      });

App.Service.Comp.saveComp = new Ext.Action({
			text : '保存',
			iconCls : 'asf-save-icon',
			handler : function() {
				var form = Ext.getCmp('comp-form').getForm();
				form.submit({
							waitMsg : '正在保存...',
							params : {
								repoId : App.Service.Comp.currentRepo,
								moduleId : App.Service.Comp.currentModule
							},
							url : ctx + '/services/saveComp.do',
							success : function() {
								App.Service.Comp.store.reload();
								if (window.parent && window.parent.selectedTreeNode) {
									var node = window.parent.selectedTreeNode;
									node.reload();
								}
							}
						});
			}
		});

App.Service.Comp.delComp = new Ext.Action({
         text : '删除',
         iconCls : 'asf-delete-icon',
         handler : function() {
            var grid = Ext.getCmp('comp-grid-panel');
            var rec = grid.getSelectionModel().getSelected();
            if (rec) {
               var compId = rec.data['id'];
               Ext.Msg.confirm('确认', '确定要删除服务组件 [' + compId + '] 吗? ', function(btn) {
                        if (btn == 'yes') {
                           $.ajax({
                                    url : ctx + '/services/delComp.do',
                                    type : 'POST',
                                    data : {
                                       repoName : App.Service.Comp.currentRepo,
                                       moduleName : App.Service.Comp.currentModule,
                                       compName : compId
                                    },
                                    success : function(result, status, xhr) {
                                       Ext.MessageBox.show({
                                                title : '提示',
                                                msg : '服务组件[' + compId + ']已删除.',
                                                width : 250,
                                                buttons : Ext.MessageBox.OK,
                                                icon : Ext.MessageBox.INFO
                                             });
                                       grid.getStore().reload();
                                       if (window.parent && window.parent.selectedTreeNode) {
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

App.Service.Comp.toRepoPanel = new Ext.Action({
         text : '转到服务仓库',
         iconCls : 'asf-repo-node',
         handler : function() {
            App.Service.Manager.switchPanel('repo-main-panel');
         }
      });
      
App.Service.Comp.viewLog = new Ext.Action({
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

App.Service.Comp.refreshComp = new Ext.Action({
         text : '刷新列表',
         iconCls : 'asf-refresh-icon',
         handler : function() {
            App.Service.Comp.store.reload();
         }
      });
      
App.Service.Comp.cancelOp = new Ext.Action({
         text : '取消',
         iconCls : 'asf-refresh-icon',
         handler : function() {
            var form = Ext.getCmp('comp-form').getForm();
            var grid = Ext.getCmp('comp-grid-panel');
            var rec = grid.getSelectionModel().getSelected();
            form.loadRecord(rec);
            App.Service.Comp.switchFormState(form, rec);
            App.Service.Comp.switchActionState(rec);
         }
      });
      
App.Service.Comp.switchActionState = function(record) {
   if (App.Service.Comp.currentRepo) {
      var currentRepo = App.Service.Comp.currentRepo;
      if (currentRepo != 'asf-external-repository') {
         App.Service.Comp.cancelOp.disable();
         App.Service.Comp.delComp.disable();
         App.Service.Comp.saveComp.disable();
         App.Service.Comp.addComp.disable();
      } else if (record && record['newrecord'] == true) {
         App.Service.Comp.cancelOp.enable();
         App.Service.Comp.delComp.disable();
         App.Service.Comp.saveComp.enable();
         App.Service.Comp.addComp.enable();
      } else {
         App.Service.Comp.cancelOp.enable();
         App.Service.Comp.delComp.enable();
         App.Service.Comp.saveComp.enable();
         App.Service.Comp.addComp.enable();
      }
   }
};

App.Service.Comp.selModel = new Ext.grid.RowSelectionModel({
         width : 25,
         singleSelect : true,
         listeners : {
            rowselect : function(sm, row, rec) {
               var form = Ext.getCmp('comp-form').getForm();
               form.loadRecord(rec);
               App.Service.Comp.switchFormState(form, rec);
               App.Service.Comp.switchActionState(rec);
            }
         }
      });

App.Service.Comp.store.on('load', function() {
         App.Service.Comp.selModel.selectFirstRow();
      })

App.Service.Comp.column = new Ext.grid.ColumnModel([/*App.Service.Comp.selModel, */{
         id : 'id',
         header : 'ID',
         dataIndex : 'id',
         renderer : 'htmlEncode'
      }, {
         header : '名称',
         dataIndex : 'name',
         renderer : 'htmlEncode'
      }, {
         header : '服务接口',
         dataIndex : 'interfaze',
         renderer : 'htmlEncode'
      }, {
         header : '版本',
         dataIndex : 'version',
         renderer : 'htmlEncode'
      }, {
         header : 'WSDL地址',
         dataIndex : 'wsdl',
         renderer : 'htmlEncode'
      }, {
         header : '状态',
         renderer : function(val, metadata, record) {
            var ok = '<font color="green">正常</font>';
            var bad = '<font color="red">异常</font>';
            //return val == true ? ok : bad;
            return ok;
         }
      }]);
// App.Service.Comp.column.defaultSortable = true;

App.Service.Comp.grid = new Ext.grid.GridPanel({
         id : 'comp-grid-panel',
         region : 'center',
         height : 200,
         store : App.Service.Comp.store,
         cm : App.Service.Comp.column,
         sm : App.Service.Comp.selModel,
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
         loadMask : true
      });

App.Service.Comp.form = new Ext.FormPanel({
         id : 'comp-form',
         region : 'north',
         labelAlign : 'right',
         width : 350,
         labelWidth : 90,
         bodyStyle : 'padding:20px;background-color:#DFE8F6',
         defaultType : 'textfield',
         items : [{
						fieldLabel : 'ID',
						name : 'id',
                  dataIndex : 'id',
						allowBlank : false,
						anchor : '100%'
					}, {
						fieldLabel : '显示名',
						name : 'name',
                  dataIndex : 'name',
						allowBlank : false,
						anchor : '100%'
					}, {
						fieldLabel : '服务接口',
						name : 'interfaze',
                  dataIndex : 'interfaze',
						allowBlank : false,
						anchor : '100%'
					}, {
						fieldLabel : '版本',
						name : 'version',
                  dataIndex : 'version',
						allowBlank : false,
						anchor : '100%'
					}, {
						fieldLabel : 'WSDL地址',
						name : 'wsdl',
                  dataIndex : 'wsdl',
						allowBlank : false,
						anchor : '100%',
						regex : new RegExp(App.Service.Comp.urlRegex, 'i'),
						regexText : '该输入项必须是正确的URL地址(例如http://hostname/path)'
						//vtype      : 'url'
				  }, {
						fieldLabel : '帮助页面',
						name : 'helpUrl',
                  dataIndex : 'helpUrl',
						allowBlank : true,
						anchor : '100%',
						regex : new RegExp(App.Service.Comp.urlRegex, 'i'),
						regexText : '该输入项必须是正确的URL地址(例如http://hostname/path)'
						//vtype      : 'url'
				}],
           
         tbar : [App.Service.Comp.addComp, '-', 
                 App.Service.Comp.saveComp, '-', 
                 App.Service.Comp.delComp, '-', 
                 App.Service.Comp.cancelOp, '-', '->', 
                 App.Service.Comp.refreshComp, '-', 
                 App.Service.Comp.viewLog]
      });

App.Service.Comp.mainPanel = new Ext.Panel({
         id : 'comp-main-panel',
         title : '服务组件',
         iconCls : 'asf-service-comp-icon',
         border : false,
         layout : 'border',
         items : [App.Service.Comp.form, App.Service.Comp.grid]
      });

App.Service.Comp.mainPanel.on('activate', function() {
         // console.log('Repo activate');
         //App.Service.Comp.store.reload();
      });
