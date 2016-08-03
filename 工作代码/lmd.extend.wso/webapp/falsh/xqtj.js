Ext.BLANK_IMAGE_URL = "/framework/images/s.gif";
		Ext.onReady(function(){
	  		Ext.QuickTips.init();
			/**
			 * 绘制日期
			 */
		 	function renderDate (v, p, r) {
	      		return new Date(v).toLocaleString();
	    	}
			//使用方法：创建store、CM、toolbar，然后是Grid并load，往toolbar上加按钮
			var detailListStore = new Ext.data.Store({
		    	remoteSort: true,
//				sortInfo: {field: "monitorid", direction: "desc"},
				proxy: new Ext.data.HttpProxy({
			     	url: '../mnicontro/detail.do?'+'mc='+mc+'&hoursstart='+hoursstart+'&hoursend='+hoursend
				}),
		    	reader: new Ext.data.JsonReader({
		    		root: 'results',
		        	totalProperty: 'totalCount',
		        	id: 'id'
		        }, [
					{name: 'xtmc'},
		   			{name: 'monitorid'},
		   			{name: 'enddate'},
		   			{name: 'maxwaittime'},
		   			{name: 'remoteip'},
		   			{name: 'startdate'},
		   			{name: 'wsurl'},
		   			{name: 'attribute'},
		  			{name: 'resstatus'}
		    	])
			}); 
			var detailListColumnModel = new Ext.grid.ColumnModel([
		  	    {id:'id', hidden:true, align:'center', header: "唯一标识", sortable: true, dataIndex: 'monitorid'},
		  	    {header: "系统名称", sortable: true, dataIndex: 'xtmc',width: 50},
		  	    {header: "ID", sortable: true, dataIndex: 'monitorid',hidden: true},
		  	    {header: "结束时间", sortable: true, dataIndex: 'enddate',width: 50},
		  	    {header: "最大等待时间", sortable: true, dataIndex: 'maxwaittime',width: 50},
			  	{header: "请求IP", sortable: true, dataIndex: 'remoteip',width: 50},
			  	{header: "开始时间", sortable: true, dataIndex: 'startdate',width: 50},
			  	{header: "请求地址", sortable: true, dataIndex: 'wsurl',width: 200},
			  	{header: "资源名称", sortable: true, dataIndex: 'attribute',width: 100},
			  	{header: "请求状态", sortable: true, dataIndex: 'resstatus',width: 50}
		  	]);  
//		  	var s = ""; 
//			for (var property in detailListStore) { 
//			s = s + "<br> "+property +": " + detailListStore[property] ; 
//			} 
//			alert(s);
			
			
				detailListGrid = new Ext.grid.GridPanel({
					store: detailListStore,
			    	cm: detailListColumnModel,
			    	id: 'detailListGrid',
			    	sm: new Ext.grid.RowSelectionModel({singleSelect:true}),
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
			    	title: '详细统计列表',
			    	iconCls: 'icon-grid',
			    	trackMouseOver:false,
			    	disableSelection:false,
			    	singleSelect:true,//是否单选模式
			    	renderTo: 'detailList-panel-div',
			    	
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