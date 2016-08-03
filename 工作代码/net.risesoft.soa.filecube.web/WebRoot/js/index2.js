/**
 * 主页面
 */
//左边树
var leftTree;
//加载布局
Ext.onReady(function(){	
	var viewport = new Ext.Viewport({
		 layout: 'border',
		 items: [
		  /*{
		    region: 'north',
		    height: 37,
		    enabled: false,
		    margins: '0 0 0 0',
		    border: false,
		    contentEl:'topTools'
		    //autoLoad: {url: '/framework/layout/head.jsp', nocache: true, scripts:true}
		  },    		  
			{
				region: 'west',
                id: 'index-west-panel',                
                split: true,
                width: 260,
                minSize: 260,
                maxSize: 400,                
                margins: '0 0 0 0', 
                layout: 'fit',
                collapsible: true,
                items:leftTree
			}, */ 
			{								
				region:'center',
				id:"index-center-panel",				
                margins:'0 0 0 0',
                autoScroll: false,
                layout: 'fit'
			}
			/*,{
	        region: 'south',
	        border: true,
	        collapsible: false,
	        height: 24,	        
	        margins:'-1 0 0 0',
	        html:"<center><div style='margin-top: 5px;'>文件资源立方系统 </div></center> "
	      }*/
		 ]
	});	
	
});
//replaceAll 方法
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}

