App.System = function() {
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
				url : ctx + '/system/sysInfo.json',
				// sortInfo : {
				// field : 'key',
				// direction : "ASC"
				// },
				groupField : 'group'
			});
	var view = new Ext.grid.GroupingView({
				forceFit : true,
            loadMask : true,
				startCollapsed : true,
				hideGroupedColumn : true,
				groupTextTpl : '{text}'
			});
         

   var uploadPanel = new Ext.form.FormPanel({
         fileUpload : true,
         width : 500,
         frame : true,
         autoHeight : true,
         bodyStyle : 'padding: 10px 10px 0 10px;',
         labelWidth : 100,
         defaults : {
            anchor : '95%',
            allowBlank : false,
            msgTarget : 'side'
         },
         items : [{
                  xtype : 'fileuploadfield',
                  emptyText : '选择 License 文件',
                  fieldLabel : 'License 文件',
                  name : 'licenseFile',
                  buttonText : '',
                  buttonCfg : {
                     iconCls : 'query'
                  }
               }],
         buttons : [{
                  text : '上传',
                  handler : function() {
                     if (uploadPanel.getForm().isValid()) {
                        uploadPanel.getForm().submit({
                                 url : ctx + '/system/uploadLicense.do',
                                 waitMsg : '正在上传 License...',
                                 success : function(fp, o) {
                                    uploadWindow.hide();
                                    Ext.Msg.show({
                                             title : '提示',
                                             msg : 'License 文件更新成功.',
                                             minWidth : 200,
                                             modal : true,
                                             icon : Ext.Msg.INFO,
                                             buttons : Ext.Msg.OK,
                                             fn : function(btn) {
                                                store.reload();   
                                             }
                                          });
                                 },
                                  failure: function(form, action) {
                                      switch (action.failureType) {
                                          case Ext.form.Action.CONNECT_FAILURE:
                                             Ext.Msg.show({
                                                      title : '错误',
                                                      //msg : 'License 文件校验失败, 请将正确的 License 文件放到<RC7>/license目录后重启服务器.',
                                                      msg : '通信错误, 请稍后重试.',
                                                      minWidth : 200,
                                                      modal : true,
                                                      icon : Ext.Msg.ERROR,
                                                      buttons : Ext.Msg.OK
                                                   });                                              
                                             break;
                                          case Ext.form.Action.SERVER_INVALID:
                                             Ext.Msg.show({
                                                title : '错误',
                                                msg : 'License 文件校验失败, 请重新选择.',
                                                minWidth : 200,
                                                modal : true,
                                                icon : Ext.Msg.ERROR,
                                                buttons : Ext.Msg.OK
                                             });
                                      }
                                  }
                              });
                     }
                  }
               }, {
                  text : '重置',
                  handler : function() {
                     uploadPanel.getForm().reset();
                  }
               }]
      });

   var uploadWindow = new Ext.Window({
         layout : 'fit',
         width : 500,
         //height : 190,
         title : '上传 License ',
         closeAction : 'hide',
         plain : true,
         modal : true,
         resizable : false,
         items : [uploadPanel]
      });
         

	return {
		init : function() {
			// store.setDefaultSort('key', 'ASC');
			var grid = new Ext.ux.grid.EditorGridPanel({
						store : store,
						columns : columns,
						view : view,
						tbar : [{
									text : '刷新数据',
									iconCls : 'asf-refresh-icon',
									handler : function() {
										store.reload();
									}
								}, '-', {
									text : '更新 License 文件',
									iconCls : 'asf-upload-icon',
									handler : function() {
										if (!OSGI_DEV_MODE) {
											// upload license
                                 uploadWindow.show();
                                 uploadPanel.getForm().reset();
										} else {
											Ext.MessageBox.show({
														title : '提示',
														msg : '更新 License 文件仅在产品部署模式下可用.',
														width : 250,
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.WARNING
													});
										}
									}
								}]
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
			App.System.init();
		});
