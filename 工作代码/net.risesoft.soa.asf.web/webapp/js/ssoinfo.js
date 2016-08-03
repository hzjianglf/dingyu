App.SSOInfo = function() {

      var store = new Ext.data.JsonStore({
               url : ctx + '/ssoinfo/onlineUsers.json',
               fields : [{
                        name : 'id'
                     }, {
                        name : 'loginName'
                     },{
                        name : 'lastTimeUsed'
                     }, {
                        name : 'NAME'
                     }, {
                        name : 'DN'
                     }, {
                        name : 'loginTime'
                     }, {
                        name : 'IDNUM'
                     }, {
                        name : 'logonServices'
                     }, {
                    	name : 'index' 
                     }]
            });

      var selModel = new Ext.grid.CheckboxSelectionModel({
               singleSelect : false
            });

      var column = new Ext.grid.ColumnModel([selModel, {
    	       header : '序号',
    	       dataIndex : 'index',
               width : 30
            }, {
               // id : 'name',
               header : '登录名',
               dataIndex : 'loginName',
               width : 70
            }, {
               header : '姓名',
               dataIndex : 'NAME'
            }, {
               header : '用户DN',
               dataIndex : 'DN'
            }, {
               header : '登录时间',
               dataIndex : 'loginTime',
               renderer : function(v, m, r){
                  return new Date(v).toLocaleString();   
               }
            }, {
               header : '最后活动时间',
               dataIndex : 'lastTimeUsed',
               renderer : function(v, m, r){
                  return new Date(v).toLocaleString();   
               }
            }, {
               header : '已访问应用',
               dataIndex : 'logonServices',
               renderer : function(v, m, r){
                  if(v && v.length){
                     m.attr = 'ext:qtitle="此用户已访问的应用: " ext:qtip="' + v.join('<br />') + '" ext:qwidth="500"'; 
                     return v.length;
                  }
                  return v;   
               }
            }]);

   var searchField = new Ext.ux.form.SearchField({
            store : store,
            paramName : 'keyword',
            emptyText : '输入登录名、姓名或者用户DN',
            style : 'margin-left: 5px;'
         });

   var grid = new Ext.ux.grid.EditorGridPanel({
				store : store,
				cm : column,
				sm : selModel,
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
							text : '注销用户',
                     iconCls : 'asf-logout-icon',
							handler : function() {
								var selected = selModel.getSelections();
								if (selected && selected.length > 0) {
									var userId = selected[0].data['loginName'];
                           var infoMsg = selected.length > 1 ? '确定要注销这些用户吗? ' : '确定要注销 用户[' + userId + '] 吗? ';
                           var tickets = [];
                           for(var i=0;i<selected.length;i++){
                              tickets.push(selected[i].data['id']);
                           }
									Ext.Msg.confirm('确认', infoMsg, function(btn) {
												if (btn == 'yes') {
													$.ajax({
																url : ctx + '/ssoinfo/logout.do',
																type : 'POST',
																data : {
                                                   ticketIds: tickets.join(',')
																},
																success : function(result, status, xhr) {
																	Ext.MessageBox.show({
																				title : '提示',
																				msg : '所选用户的会话已注销.',
																				width : 250,
																				buttons : Ext.MessageBox.OK,
																				icon : Ext.MessageBox.INFO
																			});
																	store.reload();
																}
															});

												}
											});
								}
							}
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

Ext.onReady(App.SSOInfo.init);
