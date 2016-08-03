App.BizLog = function() {
	var store = new Ext.data.JsonStore({
				url : App.Context['RootPath'] + '/bizlog/list.json',
				root : 'data',
				baseParams : {
					app : App.Context['currentApp']
				},
				totalProperty : 'totalCount',
				fields : [{
							name : 'id'
						}, {
							name : 'app'
						}, {
							name : 'operator'
						}, {
							name : 'message'
						}, {
							name : 'operateTime'
						}, {
							name : 'method'
						}, {
							name : 'logPoint'
						}]
			});

	var column = new Ext.grid.ColumnModel([{
				id : 'id',
				header : 'ID',
				dataIndex : 'id'
			}, {
				header : '应用',
				dataIndex : 'app'
			}, {
				header : '日志内容',
				dataIndex : 'message',
				renderer : function(v, m, r) {
					m.attr = 'ext:qtitle="" ext:qtip="' + v + '"';
					return v;
				}
			}, {
				header : '操作者',
				dataIndex : 'operator'
			}, {
				header : '操作时间',
				dataIndex : 'operateTime',
				renderer : function(v, m, r) {
					return $.format.date(new Date(v), "yyyy-MM-dd HH:mm:ss")
				}
			}, {
				header : '方法全名',
				dataIndex : 'method',
				renderer : function(v, m, r) {
					m.attr = 'ext:qtitle="" ext:qtip="' + v + '"';
					return v;
				}
			}, {
				header : '日志记录点',
				dataIndex : 'logPoint'
			}]);
	column.defaultSortable = true;

	var selModel = new Ext.grid.CheckboxSelectionModel({
				singleSelect : true
			});

	var appDropdown = new Ext.form.ComboBox({
				store : new Ext.data.JsonStore({
							fields : [{
										name : 'name'
									}, {
										name : 'value'
									}],
							url : App.Context['RootPath'] + '/bizlog/appNames.json'
						}),
				displayField : 'name',
				valueField : 'value',
				typeAhead : true,
				editable : false,
				triggerAction : 'all',
				value : ('all' == App.Context['currentApp'] ? '全部显示' : App.Context['currentApp'])
			});

	appDropdown.on('select', function(combo, record, index) {
				var app = record.data['value'];
				Ext.apply(store.baseParams, {
							'app' : app
						});
				store.load();
			});

	var grid = new Ext.grid.GridPanel({
				store : store,
				colModel : column,
				selModel : selModel,
				
				tbar : new Ext.PagingToolbar({
							pageSize : 20,
							store : store,
							displayInfo : true,
							items : ['-', {
                     text : '应用过滤:&nbsp;&nbsp;',
                     xtype : 'tbtext'
                  }, appDropdown]
						}),
				autoScroll : 'auto',
				el : 'bizlog-grid-panel',
				region : 'center',
				loadMask : true,
				viewConfig : {
					forceFit : true
				},
				layoutConfig : {
					autoWidth : true,
					autoHeight : true
				},
				stripeRows : true
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

Ext.onReady(App.BizLog.init);
