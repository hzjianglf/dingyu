App.LoginLog = function() {
   var store = new Ext.data.JsonStore({
            url : App.Context['RootPath'] + '/ssoinfo/loginLogs.json',
            root : 'content',
            totalProperty : 'totalCount',
            fields : [{
                     name : 'id'
                  }, {
                     name : 'loginName'
                  }, {
                     name : 'userDN'
                  }, {
                     name : 'userID'
                  }, {
                     name : 'loginTime'
                  }, {
                     name : 'userHost'
                  }, {
                     name : 'serverHost'
                  }, {
                     name : 'logoutTime'
                  }]
         });

   var column = new Ext.grid.ColumnModel([{
            id : 'id',
            header : 'TGTID',
            dataIndex : 'id',
            renderer : function(v, m, r) {
               m.attr = 'ext:qtitle="" ext:qtip="' + v + '"';
               return v;
            }
         }, {
            header : '登录名',
            dataIndex : 'loginName'
         }, {
            header : '用户DN',
            dataIndex : 'userDN',
            renderer : function(v, m, r) {
               m.attr = 'ext:qtitle="" ext:qtip="' + v + '"';
               return v;
            }
         }, {
            header : '用户ID',
            dataIndex : 'userID'
         }, {
            header : '登录时间',
            dataIndex : 'loginTime',
            renderer : function(v, m, r) {
               return $.format.date(new Date(v), "yyyy-MM-dd HH:mm:ss")
            }
         }, {
            header : '客户端IP',
            dataIndex : 'userHost'
         }, {
            header : '服务端IP',
            dataIndex : 'serverHost'
         }/*, {
            header : '注销时间',
            dataIndex : 'logoutTime',
            renderer : function(v, m, r) {
               return v ? $.format.date(new Date(v), "yyyy-MM-dd HH:mm:ss"): '<font color="red">未注销</font>';
            }
         }*/]);
   column.defaultSortable = true;

   var selModel = new Ext.grid.CheckboxSelectionModel({
            singleSelect : true
         });

   var searchField = new Ext.ux.form.SearchField({
            store : store,
            paramName : 'keyword',
            emptyText : '请输入用户DN或登录名',
            style : 'margin-left: 5px;'
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
										iconCls : 'asf-query-icon',
										text : '复制 TGT ID',
										handler : function() {
											var record = selModel.getSelected();
											if (record) {
												var id = record['id'];
												Ext.MessageBox.alert("TGTID: ", id);
											}
										}
									}, '-', searchField]
						}),
            autoScroll : 'auto',
            el : 'loginlog-grid-panel',
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

Ext.onReady(App.LoginLog.init);
