<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>平台应用模块开发示例</title>
	<!--加载ExtJs资源-->
	<script type="text/javascript" src="/extjs/bootstrap3.4.js"></script>
	<link rel="stylesheet" type="text/css" href="css/demoGlobal.css"/>
	<script type="text/javascript">
		function onItemClick() {
			alert("onItemClick");
		}
		function newOrderFun() {
        	var add_winForm = new Ext.form.FormPanel({  
                width: 360,  
            	height: 120,  
            	plain: true,  
        		layout: "form",  
        		defaultType: "textfield",  
                labelWidth: 75,  
                baseCls: "x-plain",  
                //锚点布局-  
                buttonAlign: "center",  
                bodyStyle: "padding:0 0 0 0",  
                items:[  
                       {  
                            fieldLabel: "名称",  
                            width: 160,  
                            id: "orderName",  
                            name: "orderName",  
                            maxLength: 20,  
                            maxLengthText: '名称长度不能超过20位！',  
                            allowBlank: false,  
                            blankText: "请填写名称！"  
                        },{  
                            fieldLabel: "地址",  
                            width: 160,  
                            id: "address",  
                            name: "address",  
                            maxLength: 20,  
                            maxLengthText: '地址长度不能超过20位！',  
                            allowBlank: false,  
                            blankText: "请填写地址！"  
                        },{  
                            fieldLabel: "描述",  
                            width: 160,  
                            id: "orderDesc",  
                            name: "orderDesc",  
                            maxLength: 20,  
                            maxLengthText: '描述长度不能超过20位！',  
                            allowBlank: false,  
                            blankText: "请填写描述！"  
                        }
                 ]  
    	 });    
    	 var syswin = new Ext.Window({  
        		title: "新建模块信息",  
       			width: 360,  
        		height: 180,  
        		plain: true,  
        		iconCls: "addicon",  
        		//不可以随意改变大小  
        		resizable: false,  
        		//是否可以拖动  
        		//draggable:false,  
        		collapsible: true, //允许缩放条  
        		closeAction: 'close',  
        		closable: true,  
        		//弹出模态窗体  
        		modal: 'true',  
        		buttonAlign: "center",  
       			bodyStyle: "padding:10px 0 0 10px",  
        		items: [add_winForm],  
        		buttons: [{  
            		text: "保 存",  
            		minWidth: 70,  
            		handler: function() {  
                		if (add_winForm.getForm().isValid()) {  
                    		add_winForm.getForm().submit({  
                    			url: 'operAction!add.action',  
                        		waitTitle: '请稍等...',  
                        		waitMsg: '正在提交信息...',    
                        		success: function(fp, o) {  
                            		if (o.result.success == true) {  
                                		syswin.close();  
                                		orderListGrid.getStore().reload();  
                            		} else {  
                                		msg('信息提示', '添加时出现异常！');  
                            		}  
                        		},  
                        		failure: function() {  
                            		msg('信息提示', '添加失败！');  
                        		}  
                    		});    
                		}  
            		}  
        			}, {  
            			text: "关 闭",  
            			minWidth: 70,  
            			handler: function() {  
                			syswin.close();  
            			}  
					}]    
        	});    
        	syswin.show(); 
		 }
		function modifyOrderFun() {
		     var record = orderListGrid.getSelectionModel().getSelected();//获取当前选中行的数据
		     if(record==undefined){
		          Ext.Msg.alert('提示', '请选择要修改的行！'); 
		          return;
		     }
		     var add_winForm = new Ext.form.FormPanel({  
                width: 360,  
            	height: 120,  
            	plain: true,  
        		layout: "form",  
        		defaultType: "textfield",  
                labelWidth: 75,  
                baseCls: "x-plain",  
                //锚点布局-  
                buttonAlign: "center",  
                bodyStyle: "padding:0 0 0 0",  
                items:[  
                       {  
                            fieldLabel: "名称",  
                            width: 160,  
                            id: "orderName",  
                            name: "orderName",  
                            maxLength: 20,  
                            maxLengthText: '名称长度不能超过20位！',  
                            allowBlank: false,  
                            blankText: "请填写名称！"  
                        },{  
                            fieldLabel: "地址",  
                            width: 160,  
                            id: "address",  
                            name: "address",  
                            maxLength: 20,  
                            maxLengthText: '地址长度不能超过20位！',  
                            allowBlank: false,  
                            blankText: "请填写地址！"  
                        },{  
                            fieldLabel: "描述",  
                            width: 160,  
                            id: "orderDesc",  
                            name: "orderDesc",  
                            maxLength: 20,  
                            maxLengthText: '描述长度不能超过20位！',  
                            allowBlank: false,  
                            blankText: "请填写描述！"  
                        }
                 ]  
    	 });    
    	 var syswin = new Ext.Window({  
        		title: "修改模块信息",  
       			width: 360,  
        		height: 180,  
        		plain: true,  
        		iconCls: "addicon",  
        		//不可以随意改变大小  
        		resizable: false,  
        		//是否可以拖动  
        		//draggable:false,  
        		collapsible: true, //允许缩放条  
        		closeAction: 'close',  
        		closable: true,  
        		//弹出模态窗体  
        		modal: 'true',  
        		buttonAlign: "center",  
       			bodyStyle: "padding:10px 0 0 10px",  
        		items: [add_winForm],  
        		buttons: [{  
            		text: "保 存",  
            		minWidth: 70,  
            		handler: function() {  
                		if (add_winForm.getForm().isValid()) {  
                    		add_winForm.getForm().submit({  
                    			url: 'operAction!add.action',  
                    			params:{  
                   					id:record.get('orderId')
                				},  
                        		waitTitle: '请稍等...',  
                        		waitMsg: '正在提交信息...',    
                        		success: function(fp, o) {  
                            		if (o.result.success == true) {  
                                		syswin.close();  
                                		orderListGrid.getStore().reload();  
                            		} else {  
                                		msg('信息提示', '添加时出现异常！');  
                            		}  
                        		},  
                        		failure: function() {  
                            		msg('信息提示', '添加失败！');  
                        		}  
                    		});    
                		}  
            		}  
        			}, {  
            			text: "关 闭",  
            			minWidth: 70,  
            			handler: function() {  
                			syswin.close();  
            			}  
					}]    
        	});        		      
		    add_winForm.getForm().loadRecord(record);
		    syswin.show(); 
		}
		function delOrderFun(){
		    var record = orderListGrid.getSelectionModel().getSelected();//获取当前选中行的数据
		    if(record==undefined){
		          Ext.Msg.alert('提示', '请选择要修改的行！'); 
		          return;
		     }	    
		     window.location.href("delAction.action?id="+record.get('orderId'));
		     
	/*	    alert("dfdfdfddddddddd1111111111111111");
		    Ext.Ajax.request({     
      		  url:'delAction.action',  
                params:{  
                   id:record.get('orderId')
                },  
                success: function(resp,opts) {   
                   // var respText = Ext.util.JSON.decode(resp.responseText);                                                   
                   // name=respText.name;  
                   // oid=respText.id; 
                   //orderListGrid.getStore().reload();  
                   alert("successsuccess");               
                },   
                failure: function(resp,opts) {   
                   //var respText = Ext.util.JSON.decode(resp.responseText);   
                   // Ext.Msg.alert('错误', respText.error);  
                   alert("faulurefaulurefaulurefaulurefaulure");      
                }              
           }); */    
		}
		var orderListGrid ;
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
			var orderListStore = new Ext.data.Store({
		    	remoteSort: true,
				//baseParams: {processId: processId, server: server},
				sortInfo: {field: "updateDate", direction: "desc"},
				proxy: new Ext.data.HttpProxy({
			     	url: 'orderListAction.action'
				}),
		    	reader: new Ext.data.JsonReader({
		    		root: 'results',
		        	totalProperty: 'totalCount',
		        	//queryString: 'queryString',
		        	//successProperty: 'queryString',
		        	id: 'id'
		        }, [
		   			{name: 'orderId'},
		  			{name: 'orderName'},
		  			{name: 'address'},
		  			{name: 'orderDesc'},
		  			{name: 'createDate'},
		  			{name: 'updateDate'}
		    	]),
	//	    	autoLoad:true, 
		    	listeners: {
		    		load  : function() {
		           	 	//activityInstanceGrid.getStore().reload();
		        	}
		    	}
			}); 
			var orderListColumnModel = new Ext.grid.ColumnModel([
		  	    new Ext.grid.CheckboxSelectionModel(),
		  	    {id:'id', hidden:true, align:'center', header: "唯一标识", sortable: true, dataIndex: 'orderId'},
		  	    {header: "名称", sortable: true, dataIndex: 'orderName'},
		  	    {header: "地址", sortable: true, dataIndex: 'address'},
		  	    {header: "描述", sortable: true, dataIndex: 'orderDesc'},
		  	    {header: "创建时间", sortable: true, dataIndex: 'createDate', renderer  : renderDate},
		  	    {header: "更新时间", sortable: true, dataIndex: 'updateDate', renderer  : renderDate}
		  	]);  
			var orderListToolBar = new Ext.Toolbar({
				autoWidth:true,  
		        autoShow:true,  
		        items:[{
					    id: 'newOrder',
					    text: '新建模块信息',
					    tooltip: '新建模块信息',
					    disabled : false,
					    iconCls : 'add-icon',
					    handler: newOrderFun
					  },'-',{
					    id: 'modifyOrder',
					    text: '修改模块信息',
					    tooltip: '修改模块信息',
					    disabled : false,
					    iconCls : 'edit-icon',
					    handler: modifyOrderFun
					  }
										 
					//  {
					//    id: 'back',
					//    iconCls: 'home-icon',
					//    tooltip: '返回首页',
					//    handler: onItemClick
					// }
					  ] 
		    	});  
				orderListGrid = new Ext.grid.GridPanel({
					store: orderListStore,
			    	cm: orderListColumnModel,
			    	id: 'orderListGrid',
			    	sm: new Ext.grid.RowSelectionModel({singleSelect:true}),
		//	    	style: 'margin:0 auto; width: 95%',
			    	viewConfig: {
			  	 		autoFill: true, 
			        	forceFit: true
			    	},
			    	layout:"fit",
		        	frame:true,
		        	border:true,
			    	autoWidth:true,
		        	autoHeight:true,	    
			    	width: 800,
			    	height: 800,
			    	//width: Ext.getCmp('popAutoWindow').getInnerWidth(),  //width default is auto
			    	enableColumnHide: true,
			    	collapsible: true,
			    	animCollapse: false,
			    	title: '模块信息列表',
			    	iconCls: 'icon-grid',
			    	trackMouseOver:false,
			    	disableSelection:false,
			    	singleSelect:true,//是否单选模式
			    	renderTo: 'orderList-panel-div',
			    	tbar: orderListToolBar,
			    	//bbar:Bpm.Ext.createPagingToolbar(processInstanceStore), 
			    	//分页
		        	bbar: new Ext.PagingToolbar({
		            	pageSize: 10,
		            	store: orderListStore,
		            	displayInfo: true, //显示分页
		            	emptyMsg: "NO DATA"
		        	}),
			    	listeners: {
			      	 	 cellclick  : function(grid, rowIndex, columnIndex, e) {
			               //var uid = grid.getStore().getAt(rowIndex).get('orderId');
			               //alert('uid=' + uid);
			               //activityInstanceGrid.getStore().load({params:{instanceId: uid, start:0, limit:Bpm.Const.pagesize}});
			               //worklistGrid.getStore().load({params:{instanceId: uid, start:0, limit:Bpm.Const.pagesize}});
			             }
			         }
			});	
			orderListGrid.getStore().load({params:{start:0, limit:10}});	
		});
	</script>
</head>
<body>
    <center><br><br>
 	平台应用模块(构件)开发示例。<%//=new java.util.Date()%>
 	<br><br><br><br><br>
	<div id="orderList-panel-div-Panel"></div>
	<div id="orderList-panel-div"></div>
    </center>
</body>
</html>