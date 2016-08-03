var popWin;
Ext.PopWin = function(){
	return{
		init:
			function(title, varWidth, varHeight, border){
				popWin = new Ext.Window({
					 closeAction: 'close',
					 modal: true,
					 resizable: false,
					 autoHeight: false,
					 border: false,
					 layout: 'fit',
					 title: title,
					 width: varWidth,
					 height: varHeight,
					 //maximizable: true,
					 autoScroll: true
				});
				if (border){
					popWin.border = true;
				}
				popWin.show();
				popWin.center();
			}
	}
}();
/**
 * 公共ajax
 * @param url      -- 访问的url
 * @param data     -- 提交后台的参数
 * @param isReload -- 是否刷新页面
 */
function commonsAjax(url,data,isReload){
	$.ajax({
		  type: 'POST',
		  url: url,
		  data: data,
		  dataType: 'JSON',
		  success: function(rtnData){
			  Ext.Msg.alert('操作成功',rtnData.success);     
			  if(isReload){
				  //刷新页面
				  location.reload();
			  }
		  },
		  error:function(){
			  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
		  }			  
	});
}
