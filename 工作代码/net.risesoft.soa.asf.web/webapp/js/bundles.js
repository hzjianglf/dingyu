App.Bundle = function() {
	var store = new Ext.data.JsonStore({
				url : App.Context['RootPath'] + '/bundles/list.json',
				// root : 'bundles',
				fields : [{
							name : 'BundleId',
							type : 'int'
						}, {
							name : 'SymbolicName'
						}, {
							name : 'Name'
						}, {
							name : 'State'
						}, {
							name : 'Version'
						},{
							name : 'Gjly'
						}, {
							name : 'Operation'
						}]
			});

	var refreshCount = function(values) {
		var o = values;

		var f = Ext.getCmp('bundles-count-all');
		if (f && o.all)
			f.setText('插件总计: ' + o.all + ',   ');

		f = Ext.getCmp('bundles-count-display');
		if (f && o.display)
			f.setText('当前显示: ' + o.display + ',   ');

		f = Ext.getCmp('bundles-count-active');
		if (f && o.active)
			f.setText('已启动: ' + o.active + ',   ');

		f = Ext.getCmp('bundles-count-resolved');
		if (f && o.resolved)
			f.setText('已解析: ' + o.resolved + ',   ');

		f = Ext.getCmp('bundles-count-installed');
		if (f && o.installed)
			f.setText('已安装: ' + o.installed + ',   ');

		f = Ext.getCmp('bundles-count-unknown');
		if (f && o.unknown)
			f.setText('未知状态: ' + o.unknown + ',   ');
	};

	store.on('load', function(store, records, options) {
				Ext.Ajax.request({
							url : App.Context['RootPath'] + '/bundles/count.json',
							params : options.params,
							success : function(r) {
								var o = eval('(' + r.responseText + ')');
								if (o)
									refreshCount(o);
							}
						});
			});

	var expander = new Ext.ux.grid.RowExpander({
				tpl : new Ext.Template('<div style="padding-left: 80px">{bundleDetail}</div>'),
				lazyRender : true,
				getDataFn : function(record, expander, callback) {
					Ext.Ajax.request({
								url : ctx + '/bundles/detail/' + record.data['BundleId'] + '.json',
								success : function(response) {
									var data = {
										bundleDetail : Ext.decode(response.responseText)
									}
									record.data.detailDataLoaded = true;
									record.data['bundleDetail'] = data.bundleDetail.join('<br />');
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

	var column = new Ext.grid.ColumnModel([expander, {
				id : 'BundleId',
				header : 'ID',
				width : 20,
				dataIndex : 'BundleId'
			}, {
				header : '标识',
				dataIndex : 'SymbolicName'
			}, {
				header : '显示名',
				dataIndex : 'Name'
			}, {
				header : '版本',
				dataIndex : 'Version'
			}, {
				header : '状态',
				dataIndex : 'State'
			}, {
				header : '构件来源',
				dataIndex : 'Gjly'
			}, {
				header : '操作',
				dataIndex : 'Operation'
			}]);
	column.defaultSortable = true;

	var searchField = new Ext.ux.form.SearchField({
				store : store,
				paramName : 'keyword',
				emptyText : '请输入插件名称或标识',
				style : 'margin-left: 5px;'
			});

	var grid = new Ext.ux.grid.EditorGridPanel({
				store : store,
				cm : column,
				loadMask : true,
				plugins : expander,
				tbar : [{
							text : '刷新数据',
							iconCls : 'asf-refresh-icon',
							handler : function() {
								store.reload();
							}
						}, '-', {
							text : '快速查找: ',
							xtype : 'tbtext'
						}, searchField, '-', '-', {
							id : 'bundles-count-all',
							text : '插件总计: 0,  ',
							xtype : 'tbtext'
						}, {
							id : 'bundles-count-display',
							text : '当前显示: 0,  ',
							xtype : 'tbtext'
						}, {
							id : 'bundles-count-active',
							text : '已启动: 0,  ',
							xtype : 'tbtext'
						}, {
							id : 'bundles-count-resolved',
							text : '已解析: 0,  ',
							xtype : 'tbtext'
						}, {
							id : 'bundles-count-installed',
							text : '已安装: 0,  ',
							xtype : 'tbtext'
						}, {
							id : 'bundles-count-unknown',
							text : '未知状态: 0.  ',
							xtype : 'tbtext'
						}]
			});

	return {
		init : function() {
			grid.render();
			store.load();
			var vp = new Ext.Viewport({
						layout : 'fit',
						items : grid
					});
		}
	};
}();
function operation(oper,bundleid){// add by sunming 20150821
	var vurl="";
    if(oper=='start'){
	    vurl=ctx + '/bundles/start/' + bundleid ;
	}
	if(oper=='stop'){
		vurl=ctx + '/bundles/stop/' + bundleid ;
	}
	if(oper=='logout'){
		vurl=ctx + '/bundles/logout/' + bundleid ;
	}  
	Ext.Ajax.request({
		url : vurl,
		method : "post",
		success : function(response) {
			var res=Ext.util.JSON.decode(response.responseText);
			if(res.status=='sucess'){
				Ext.MessageBox.alert("提示框",res.message);  
				Ext.onReady(App.Bundle.init);
			}else{
				Ext.MessageBox.alert("提示框",res.message);
				Ext.onReady(App.Bundle.init);
			}
		},
		failure : function(response) {
			var res=Ext.util.JSON.decode(response.responseText);
			if(res.status=='error'){
				Ext.MessageBox.alert("提示框",res.message);  
				Ext.onReady(App.Bundle.init);
			}else{
				Ext.MessageBox.alert("提示框",res.message);  
				Ext.onReady(App.Bundle.init);
			}
		}
	})
}
Ext.onReady(App.Bundle.init);
