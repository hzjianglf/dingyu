Ext.ns('App.Service');

App.Service.Repo = {};

App.Service.Repo.store = new Ext.data.JsonStore({
			url : App.Context['RootPath'] + '/services/repos.json',
			fields : [{
						name : 'alias'
					}, {
						name : 'description'
					}, {
						name : 'ip'
					}, {
						name : 'port'
					}, {
						name : 'basePath'
					}, {
						name : 'state'
					}, {
                  name : 'stateMsg'
               }]
		});

App.Service.Repo.switchFormState = function(form, record) {
	if (record && record.data) {
		var data = record.data;
		if (data['alias'] == 'asf-local-repository' || data['alias'] == 'asf-external-repository') {
			form.items.each(function(f) {
						f.disable();
					});
		} else {
			form.items.each(function(f) {
						f.enable();
						if (data['alias'] && f.name == 'alias') {
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

App.Service.Repo.addRepo = new Ext.Action({
			text : '添加',
			iconCls : 'asf-add-icon',
			handler : function() {
				var form = Ext.getCmp('repository-form').getForm();
				var record = {
					data : [],
					newrecord : true
				}
				App.Service.Repo.switchFormState(form, record);
            App.Service.Repo.switchActionState(record);
				form.items.each(function(f) {
							f.setValue('');
						});
                              
			}
		});

App.Service.Repo.saveRepo = new Ext.Action({
			text : '保存',
			iconCls : 'asf-save-icon',
			handler : function() {
				var form = Ext.getCmp('repository-form').getForm();
				form.submit({
							waitMsg : '正在保存...',
							url : ctx + '/services/saveRepo.do',
							success : function() {
								App.Service.Repo.store.reload();
								if (window.parent && window.parent.selectedTreeNode) {
									var node = window.parent.selectedTreeNode;
									node.reload();
								}
							}
						});
			}
		});

App.Service.Repo.delRepo = new Ext.Action({
			text : '删除',
			iconCls : 'asf-delete-icon',
			handler : function() {
				var grid = Ext.getCmp('repo-grid-panel');
				var rec = grid.getSelectionModel().getSelected();
				if (rec) {
					var repoName = rec.data['alias'];
					Ext.Msg.confirm('确认', '确定要删除服务仓库 [' + repoName + '] 吗? ', function(btn) {
								if (btn == 'yes') {
									$.ajax({
												url : ctx + '/services/delRepo.do',
												type : 'POST',
												data : {
													repoName : repoName
												},
												success : function(result, status, xhr) {
													Ext.MessageBox.show({
																title : '提示',
																msg : '服务仓库[' + repoName + ']已删除.',
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

App.Service.Repo.toCompPanel = new Ext.Action({
			text : '转到服务组件',
			iconCls : 'asf-service-comp-icon',
			handler : function() {
				App.Service.Manager.switchPanel('comp-main-panel');
			}
		});

App.Service.Repo.refreshRepo = new Ext.Action({
			text : '刷新列表',
			iconCls : 'asf-refresh-icon',
			handler : function() {
				App.Service.Repo.store.reload();
			}
		});
      
App.Service.Repo.cancelOp = new Ext.Action({
			text : '取消',
			iconCls : 'asf-refresh-icon',
			handler : function() {
				var form = Ext.getCmp('repository-form').getForm();
				var grid = Ext.getCmp('repo-grid-panel');
            var rec = grid.getSelectionModel().getSelected();
				form.loadRecord(rec);
				App.Service.Repo.switchFormState(form, rec);
            App.Service.Repo.switchActionState(rec);
			}
		});
      
App.Service.Repo.switchActionState = function(record) {
	var data = record.data;
	if (data['alias'] == 'asf-local-repository' || data['alias'] == 'asf-external-repository') {
		App.Service.Repo.cancelOp.disable();
		App.Service.Repo.delRepo.disable();
		App.Service.Repo.saveRepo.disable();
		App.Service.Repo.addRepo.enable();
	} else if (record['newrecord'] == true) {
		App.Service.Repo.cancelOp.enable();
		App.Service.Repo.delRepo.disable();
		App.Service.Repo.saveRepo.enable();
		App.Service.Repo.addRepo.enable();
	} else {
		App.Service.Repo.cancelOp.enable();
		App.Service.Repo.delRepo.enable();
		App.Service.Repo.saveRepo.enable();
		App.Service.Repo.addRepo.enable();
	}
};

App.Service.Repo.selModel = new Ext.grid.RowSelectionModel({
			width : 25,
			singleSelect : true,
			listeners : {
				rowselect : function(sm, row, rec) {
					var form = Ext.getCmp('repository-form').getForm();
					form.loadRecord(rec);
					App.Service.Repo.switchFormState(form, rec);
               App.Service.Repo.switchActionState(rec);
				}
			}
		});

App.Service.Repo.store.on('load', function() {
			App.Service.Repo.selModel.selectFirstRow();
		})

App.Service.Repo.column = new Ext.grid.ColumnModel([/*App.Service.Repo.selModel, */{
			//id : 'id',
			header : '名称',
			dataIndex : 'alias',
			renderer : 'htmlEncode'
		}, {
			header : '显示名',
			dataIndex : 'description',
			renderer : 'htmlEncode'
		}, {
			header : '主机',
			dataIndex : 'ip',
			renderer : 'htmlEncode'
		}, {
			header : '端口',
			dataIndex : 'port',
			renderer : 'htmlEncode'
		}, {
			header : '根路径',
			dataIndex : 'basePath',
			renderer : 'htmlEncode'
		}, {
			header : '状态',
			renderer : function(val, metadata, record) {
				var ok = '<font color="green">正常</font>';
				var wrong = '<font color="red">异常</font>';
				var data = record.data;
            
            metadata.attr = 'ext:qtitle="" ext:qtip="' + data['stateMsg'] + '"';  
            
				if (data['alias'] == 'asf-local-repository' || data['alias'] == 'asf-external-repository') {
					return ok;
				}
				return val == true ? ok : wrong;
			}
		}]);
// App.Service.Repo.column.defaultSortable = true;

App.Service.Repo.grid = new Ext.grid.GridPanel({
			id : 'repo-grid-panel',
			region : 'center',
			store : App.Service.Repo.store,
			cm : App.Service.Repo.column,
			sm : App.Service.Repo.selModel,
			layout : 'fit',
			viewConfig : {
				forceFit : true
			},
			layoutConfig : {
				autoWidth : true,
				autoHeight : true
			},
			stripeRows : true,
         loadMask : true,
         tbar : ['->', App.Service.Repo.refreshRepo, '-'/*, App.Service.Repo.toCompPanel*/]
		});

App.Service.Repo.form = new Ext.FormPanel({
			id : 'repository-form',
			region : 'east',
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
						fieldLabel : '名称(非中文)',
						name : 'alias',
						allowBlank : false,
						anchor : '100%'
					}, {
						fieldLabel : '显示名',
						name : 'description',
						allowBlank : false,
						anchor : '100%'
					}, {
						fieldLabel : '主机',
						name : 'ip',
						allowBlank : false,
						anchor : '100%'
					}, {
						fieldLabel : '端口',
						name : 'port',
						xtype : 'numberfield',
						allowNegative : false,
						anchor : '100%'
					}, {
						fieldLabel : '根路径',
						name : 'basePath',
						allowNegative : false,
						anchor : '100%'
					}],
			tbar : [App.Service.Repo.addRepo, '-', 
                 App.Service.Repo.saveRepo, '-', 
                 App.Service.Repo.delRepo, '-', 
                 App.Service.Repo.cancelOp]
		});

App.Service.Repo.mainPanel = new Ext.Panel({
			id : 'repo-main-panel',
			title : '服务仓库',
         iconCls : 'asf-repo2-node',
			border : false,
			layout : 'border',
			items : [App.Service.Repo.form, App.Service.Repo.grid]
		});

App.Service.Repo.mainPanel.on('activate', function() {
			// console.log('Repo activate');
			//App.Service.Repo.store.reload();
		});
