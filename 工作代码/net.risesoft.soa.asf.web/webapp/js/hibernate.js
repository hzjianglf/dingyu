App.Hibernate = function() {
	var valueRenderer = function(value, metadata, record) {
		metadata.attr = 'style="white-space:normal;"';
		return value;
	};
	var reader = new Ext.data.JsonReader({}, [{
						name : 'key'
					}, {
						name : 'value'
					}, {
						name : 'group'
					}]);

	var columns = [{
				id : 'key',
				header : "名称",
				width : 30,
				sortable : true,
				dataIndex : 'key'
			}, {
				header : "键值",
				dataIndex : 'value',
				renderer : valueRenderer
			}, {
				header : "分组",
				dataIndex : 'group'
			}];

	var store = new Ext.data.GroupingStore({
				reader : reader,
				url : ctx + '/hibernate/statAll.json',
				// sortInfo : {
				// field : 'key',
				// direction : "ASC"
				// },
				groupField : 'group'
			});
	var view = new Ext.grid.GroupingView({
				forceFit : true,
				startCollapsed : false,
				hideGroupedColumn : true,
				groupTextTpl : '{text}'
			});

	return {
		init : function() {
			// store.setDefaultSort('key', 'ASC');
			var grid = new Ext.ux.grid.EditorGridPanel({
						loadMask : true,
						store : store,
						columns : columns,
						tbar : [{
									text : '刷新数据',
									iconCls : 'asf-refresh-icon',
									handler : function() {
										store.reload();
									}
								}, '-', {
									text : '清空二级缓存',
									iconCls : 'asf-reset-icon',
									handler : function() {
										$.ajax({
													url : ctx + '/hibernate/clearL2.do',
													type : 'POST',
													data : {
														// e = EntityRegion
														// c = CollectionRegion
														// q = QueryRegion
														regions : 'e,c,q'
													},
													success : function(result, status, xhr) {
														Ext.MessageBox.show({
																	title : '提示',
																	msg : 'Hibernate 二级缓存已清空.',
																	width : 250,
																	buttons : Ext.MessageBox.OK,
																	icon : Ext.MessageBox.INFO
																});
														store.reload();
													}
												});
									}
								}],
						view : view
					});
			store.load();
			var vp = new Ext.Viewport({
						layout : 'fit',
						items : grid
					});
		}
	};
}();

Ext.onReady(function() {
			App.Hibernate.init();
		});
