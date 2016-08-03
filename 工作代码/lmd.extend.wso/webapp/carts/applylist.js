Ext.BLANK_IMAGE_URL = "/framework/images/s.gif";
		Ext.onReady(function(){
	  		Ext.QuickTips.init();
			//使用方法：创建store、CM、toolbar，然后是Grid并load，往toolbar上加按钮
			var detailListStore = new Ext.data.Store({
		    	remoteSort: true,
				proxy: new Ext.data.HttpProxy({
			     	url: '../carts/list.do?userid='+userId
				}),
		    	reader: new Ext.data.JsonReader({
		    		root: 'results',
		        	totalProperty: 'totalCount',
		        	id: 'id'
		        }, [
					{name: 'user_id'},
		   			{name: 'resource_id'},
		   			{name: 'resource_name'},
		   			{name: 'task_id'},
		   			{name: 'createtime'},
		   			{name: 'status'}
		    	])
			}); 
			var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:false});
			var detailListColumnModel = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),           //--在这里设置行号
				sm,
		  	    {id:'id', hidden:true, align:'center', header:"唯一标识", sortable:true, dataIndex:'id'},
		  	    {header: "用户ID", sortable:true, dataIndex:'user_id',width: 50},
		  	    {header: "资源ID", sortable:true, dataIndex:'resource_id',width: 50},
		  	    {header: "资源名称", sortable:true, dataIndex:'resource_name',width: 50},
		  	    {header: "任务ID", sortable:true, dataIndex:'task_id',width: 50},
			  	{header: "请求时间", sortable:true, dataIndex:'createtime',width: 50},
			  	{header: "状态", sortable:true, dataIndex:'status',width: 50}
		  	]);  
			 
				 detailListGrid = new Ext.grid.GridPanel({
					store: detailListStore,
			    	cm: detailListColumnModel,
			    	id: 'detailListGrid',
			    	sm: sm,
			    	style: 'margin:0 auto; width: 100% ;',
			    	viewConfig: {
			  	 		autoFill: true, 
			        	forceFit: true
			    	},
			    	layout:"fit",
		        	frame:true,
		        	bdetail:true,
			    	autoWidth:true,
		        	autoHeight:true,	    
			    	enableColumnHide: true,
			    	collapsible: true,
			    	animCollapse: false,
			    	title: '购物车内资源列表',
			    	iconCls: 'icon-grid',
			    	trackMouseOver:false,
			    	disableSelection:false,
			    	singleSelect:true,//是否单选模式
			    	renderTo: 'detailList-panel-div',
			    	tbar:  new Ext.Toolbar({
										width:500, 
								        autoShow:true,  
								        items:['->',{
											    id: 'commitCart',
											    text: '提交申请',
											    tooltip: '提交申请',
											    disabled : false,
											    iconCls : 'save-icon',
											    handler: function(){
											    	var rows  = detailListGrid.getSelectionModel().getSelections();
											    	if(rows.length>0){
											    	//此处修改状态
											    	var strResoureIds=gerStrResoureId(rows);//获取所有id 用分号隔开
											    	window.location.href="http://10.10.10.207:8000/xtkf/createservicetask.jsp?resourceID="+strResoureIds;
											    	}else{
											    	Ext.Msg.alert('提示',"请先选择要提交的申请数据");
											    	}
											    	
											    }
											   },{
											    id: 'deleteCart',
											    text: '删除',
											    tooltip: '删除申请',
											    disabled : false,
											    iconCls : 'delete-icon',
											    handler: function(){
											    	var rows  = detailListGrid.getSelectionModel().getSelections();
											    	if(rows.length>0){
											    	detailListGrid.getStore().remove(rows);
											    	var strIds=gerStrId(rows);//获取所有id 用分号隔开
											    	//alert(rows.ge);
											    	//此处数据库执行删除方法
											    	Ext.Ajax.request({ 
															url : '../carts/delete.do', 
															method : 'post', 
															params : { 
															ids : strIds,
															userid : userId
															}, 
															success : function(response, options) {
																detailListGrid.getStore().commitChanges();//提交更改
																detailListGrid.getStore().reload();//重新加载数据 
																var status = response.responseText; 
																Ext.Msg.alert('提示',status);
																	}, 
															failure : function() { 
																Ext.Msg.alert('提示',"其他错误");
															} 
															}); 
											    	
											    	}else{
											    	Ext.Msg.alert('提示',"请先选择要删除的申请数据");
											    	}
											    }
											   }
											   
											  ] 
		    							}),
			    	//分页
		        	bbar: new Ext.PagingToolbar({
		            	pageSize: 10,
		            	store: detailListStore,
		            	displayInfo: true, //显示分页
		            	emptyMsg: "没有数据"
		        	}),
			    	listeners: {
			      	 	 cellclick  : function(grid, rowIndex, columnIndex, e) {
			             }
			         }
			});	
			detailListGrid.getStore().load({params:{start:0, limit:10}});	
		});
		
		
		function gerStrId(rows){
			var strId="";
			for(var i=0;i<rows.length;i++){
				if(i==rows.length-1){
					strId += "'"+rows[i].id+"'";
				}else{
					strId += "'"+rows[i].id+"',";
				}
			}
			return strId;
		}
		
		function gerStrResoureId(rows){
			var ResoureId="";
			for(var i=0;i<rows.length;i++){
				if(i==rows.length-1){
					ResoureId += rows[i].data.resource_id;
				}else{
					ResoureId += rows[i].data.resource_id+",";
				}
			}
			return ResoureId;
		}
		