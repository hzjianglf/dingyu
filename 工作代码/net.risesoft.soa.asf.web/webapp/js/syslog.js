App.SysLog = function() {

	var logFilesMgr = function() {
		var store = new Ext.data.JsonStore({
					url : ctx + '/syslog/logFiles.json',
					fields : [{
								name : 'name'
							}, {
								name : 'path'
							}, {
								name : 'lastModified'
							}, {
								name : 'length'
							}]
				});

		var selModel = new Ext.grid.CheckboxSelectionModel({
					singleSelect : false
				});

		var column = new Ext.grid.ColumnModel([selModel, {
					// id : 'name',
					header : '文件名称',
					dataIndex : 'name',
					renderer : 'htmlEncode',
					width : 50
				}, {
					header : '所在位置',
					renderer : function(v, m, r) {
						m.attr = 'ext:qtitle="" ext:qtip="' + v + '"';
						return v;
					},
					dataIndex : 'path'
				}, {
					header : '修改时间',
					dataIndex : 'lastModified',
					width : 80,
					renderer : function(v, m, r) {
						// console.log(v, m, r);
						return new Date(v).toLocaleString();
					}
				}, {
					header : '文件大小',
					dataIndex : 'length',
					width : 50
				}]);

		var grid = new Ext.grid.GridPanel({
					store : store,
					cm : column,
					sm : selModel,
					autoExpandColumn : 'path',
					layout : 'fit',
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
            
      var downloadLog = function(logFiles) {
         // console.log('downloadLog');
         if ( !OSGI_DEV_MODE) {
            $.download(ctx + '/syslog/downloadLog.do', 'logFiles=' + logFiles, 'POST');
         } else {
            Ext.MessageBox.show({
                     title : '提示',
                     msg : '系统日志下载仅在产品部署模式下可用.',
                     width : 250,
                     buttons : Ext.MessageBox.OK,
                     icon : Ext.MessageBox.WARNING
                  });
         }
      };

      var downloadLogAction = new Ext.Action({
         text : '下载',
         iconCls : 'asf-download-icon',
         handler : function(){
            var selected = selModel.getSelections();
            if (selected && selected.length > 0) {
               var logFiles = [];
               $.each(selected, function(key, value){
                  logFiles.push(value.data['name']);
               });
               downloadLog(logFiles.join(','));
            } else {
               //必须选择至少一个文件
            }
         }
      });            

		var win;

		return {
			show : function() {
				if (!win) {
					win = new Ext.Window({
								layout : 'fit',
								title : '下载系统日志',
								iconCls : 'asf-syslog-node',
								width : 700,
								height : 400,
								closeAction : 'hide',
								plain : true,
								modal : true,
								items : grid,
								tbar : ['-', {
											text : '刷新',
											iconCls : 'asf-refresh-icon',
											handler : function() {
												store.reload();
											}
										}, '-', downloadLogAction, '-', {
											text : '关闭',
											iconCls : 'asf-close-icon',
											handler : function() {
												win.hide();
											}
										}]
							});
				};
				store.reload();
				win.show();
			}
		};
	}();

	var store = new Ext.data.JsonStore({
				url : ctx + '/syslog/loggers.json',
				fields : [{
							name : 'logger'
						}, {
							name : 'level'
						}, {
							name : 'effectiveLevel'
						}]
			});

	var loggerLevelCombo = new Ext.form.ComboBox({
				typeAhead : true,
				triggerAction : 'all',
				lazyRender : true,
				mode : 'local',
				store : new Ext.data.ArrayStore({
							id : 0,
							fields : ['level', 'displayText'],
							data : [['OFF', 'OFF'], ['ERROR', 'ERROR'], ['WARN', 'WARN'], ['INFO', 'INFO'],
									['DEBUG', 'DEBUG'], ['TRACE', 'TRACE'], ['ALL', 'ALL']]
						}),
				valueField : 'level',
				displayField : 'displayText'
			});

	var column = new Ext.grid.ColumnModel([{
				header : '日志记录器',
				dataIndex : 'logger',
				width : 200
			}, {
				header : '输出级别',
				// width : 80,
				dataIndex : 'level',
				editor : loggerLevelCombo,
				renderer : function(value) {
					return value;
				}
			}, {
				header : '叠加后的输出级别',
				// width : 80,
				dataIndex : 'effectiveLevel'
			}]);
	column.defaultSortable = true;

	var searchField = new Ext.ux.form.SearchField({
				store : store,
				paramName : 'keyword',
				emptyText : '输入日志记录器名称',
				style : 'margin-left: 5px;'
			});

	var saveLoggersLevel = function() {
		// console.log('saveLoggersLevel.');
		var modified = store.getModifiedRecords();
		var loggers = [];
		var levels = [];
		for (var i = 0; i < modified.length; i++) {
			var logger = modified[i].data.logger;
			var level = modified[i].data.level;
			loggers.push(logger);
			levels.push((!level ? 'null' : level));
		}
		if (loggers.length > 0 && levels.length > 0) {
			loggers = loggers.join(',');
			levels = levels.join(',');
			$.ajax({
						url : ctx + '/syslog/setLoggerLevel.do',
						type : 'POST',
						data : {
							loggers : loggers,
							levels : levels
						},
						// async:false,
						success : function(result, status, xhr) {
							store.modified = [];
							store.reload();
						}
					});
		}
	};

	var resetLoggersLevel = function() {
		// console.log('resetLoggersLevel.');
		store.reload();
	};

	var viewLogFiles = function() {
		if ( !OSGI_DEV_MODE ) {
			//console.log('查看系统日志');
			// logFilesMgr.init();
			logFilesMgr.show();
		} else {
			Ext.MessageBox.show({
						title : '提示',
						msg : '查看/下载系统日志文件仅在产品部署模式下可用.',
						width : 250,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.WARNING
					});
		}
	};

	var deleteHistoryLog = function() {
		if (!OSGI_DEV_MODE) {
			Ext.Msg.confirm('确认', '确定要清除历史日志吗? ', function(btn) {
						if (btn == 'yes') {
							$.ajax({
										url : ctx + '/syslog/deleteHistoryLog.do',
										type : 'POST',
										success : function(result, status, xhr) {
											Ext.MessageBox.show({
														title : '提示',
														msg : '历史日志已清除.',
														width : 250,
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.INFO
													});
											grid.getStore().reload();
										}
									});

						}
					});
		} else {
			Ext.MessageBox.show({
						title : '提示',
						msg : '清除系统日志仅在产品部署模式下可用.',
						width : 250,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.WARNING
					});
		}
	}

	var grid = new Ext.ux.grid.EditorGridPanel({
				store : store,
				cm : column,
				loadMask : true,
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
							text : '应用日志记录器级别',
							iconCls : 'asf-save-icon',
							handler : saveLoggersLevel
						}, '-', {
							text : '清除修改',
							iconCls : 'asf-reset-icon',
							handler : resetLoggersLevel
						}, '-', {
							text : '下载日志文件',
							iconCls : 'asf-download-icon',
							handler : viewLogFiles
						}/*
							 * , '-', { text : '清除历史日志', iconCls :
							 * 'asf-delete-icon', handler : deleteHistoryLog }
							 */]
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

Ext.onReady(App.SysLog.init);
