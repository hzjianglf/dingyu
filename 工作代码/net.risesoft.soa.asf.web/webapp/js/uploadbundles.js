var popWin;
function popWinShow(title, varWidth, varHeight, border){
	popWin = new Ext.Window({
		 closeAction: 'close',
		 modal: true,
		 resizable: false,
		 autoHeight: false,
		 border: false,
		 layout: 'fit',
		 title: title,
		 width: varWidth,
		 height: varHeight
	});
	if (border){
		popWin.border = true;
	}
	popWin.show();
	popWin.center();
}
App.Bundle = function() {
	var store = new Ext.data.JsonStore({
		url : App.Context['RootPath'] + '/bundles/listuploadbundles.json',
		// root : 'bundles',
		fields : [{
					name : 'name'
				}, {
					name : 'description'
				}, {
					name : 'operation'
				}, {
					name : 'delete'
				}]
	});
//	store.on('load', function(store, records, options) {
//		Ext.Ajax.request({
//			url : App.Context['RootPath'] + '/bundles/count.json',
//			params : options.params //,
////							success : function(r) {
////								var o = eval('(' + r.responseText + ')');
////								if (o)
////									refreshCount(o);
////							}
//		});
//	});
	var column = new Ext.grid.ColumnModel([{
		header : '名称',
		dataIndex : 'name'
	}, {
		header : '描述',
		dataIndex : 'description'
	}, {
		header : '安装',
		dataIndex : 'operation'
	}, {
		header : '删除',
		dataIndex : 'delete'
	}]);
	
	column.defaultSortable = true;
	var grid = new Ext.ux.grid.EditorGridPanel({
		store : store,
		cm : column,
		loadMask : true,
		tbar : [{
			id: "bundles-count-upload",
			text: "上传",
			xtype: "button",
			handler : function() {
				popWinShow('新增构件', 400, 300);
				popWin.load( {
					url : App.Context['RootPath']+'/js/addComponent.jsp',
					scripts : true
				});
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
function operation(oper,id){// add by sunming 20150821
	var vurl="";
    if(oper=='setup'){
	    vurl=ctx + '/bundles/setup/' + id ;
	}
    if(oper=='delete'){
	    vurl=ctx + '/bundles/delete/' + id ;
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
