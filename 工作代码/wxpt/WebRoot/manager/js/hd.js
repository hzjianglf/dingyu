var themeName1 = "";
var changeTheme = function(themeName) { 
	themeName1 = themeName;
	alert(themeName1+"=========");
var $easyuiTheme = $('#easyuiTheme'); 
var url = $easyuiTheme.attr('href'); 
var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css'; 
$easyuiTheme.attr('href', href); 
var $iframe = $('iframe');
if ($iframe.length> 0) { 
for ( var i = 0; i< $iframe.length; i++) { 
var ifr = $iframe[i]; 
$(ifr).contents().find('#easyuiTheme').attr('href', href); 
} 
} 
$.cookie('easyuiThemeName', themeName, { 
expires : 7 
}); 
};



function InitLeftMenu(){	
$('.easyui-accordion li a').click(function(){
	   //alert(themeName1+"99999999");
		var tabTitle = $(this).text();
		var url = $(this).attr("href");
		var target = $(this).attr("target");
		var title = tabTitle.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');        //截取空格留下我的主页 
		addTab(title,url,target);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
		
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

}

function addTab(tabTitle,url,target){
	if(!$('#tabs').tabs('exists',tabTitle)){

		$('#tabs').tabs('add',{
			title:tabTitle,
			content:createFrame(url,target),
			closable:true,
			width:$('#mainPanle').width()-10,
			height:$('#mainPanle').height()-26
		});
		
	}else{
		$('#tabs').tabs('select',tabTitle);
        refreshTab({tabTitle:tabTitle,url:url}); 
	}
	
	tabClose();
	
}
function refreshTab(cfg){ 
    var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');  
    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
    var _refresh_ifram = refresh_tab.find('iframe')[0];  
    var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
    //_refresh_ifram.src = refresh_url;  
    _refresh_ifram.contentWindow.location.href=refresh_url;  
    }  
}  
function createFrame(url,target)
{
	var s = '<iframe name="'+target+'" scrolling="no" frameborder="0" src="'+url+'" style="width:100%;height:99.7%;padding:0;margin:0;overflow:hidden;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var t = $(this).children("span").text();
		$('#tabs').tabs('close',t);
	});

	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
		
		var subtitle =$(this).children("span").text();
		$('#mm').data("currtab",subtitle);
		
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});	
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!=currtab_title)
				$('#tabs').tabs('close',t);
		});	
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	});
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}