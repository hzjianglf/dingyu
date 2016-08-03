<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件查看</title>
<style>
	body { margin: 0px; overflow:hidden }
</style>

<script type="text/javascript" src="<%=path %>/commons/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/commons/flexpaper/flexpaper.js"></script>
<script type="text/javascript">
var winWidth = 0;  
var winHeight = 0; 
//获取浏览器高、宽
function findDimensions() {  
	//获取窗口宽度  
	if (window.innerWidth)  
		winWidth = window.innerWidth;  
	else if ((document.body) && (document.body.clientWidth))  
		winWidth = document.body.clientWidth;  
	//获取窗口高度  
	if (window.innerHeight)  
		winHeight = window.innerHeight;  
	else if ((document.body) && (document.body.clientHeight))  
		winHeight = document.body.clientHeight;  
	//通过深入Document内部对body进行检测，获取窗口大小  
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth){  
		winHeight = document.documentElement.clientHeight;  
		winWidth = document.documentElement.clientWidth;  
	}
	//alert("高度:" + winHeight + " 宽度：" + winWidth);
	$('#viewerPlaceHolder').css('height',winHeight + "px");	
}

$(document).ready(function(){	
	findDimensions();  
	//调用函数，获取数值  
	window.onresize=findDimensions;
	$.ajax({
		  type: 'POST',
		  url: '<%=path%>/attachment_convertFile.action',
		  dataType: 'JSON',
		  data:{attachmentUid:'${attachmentUid}'},
		  async: false,      //ajax同步
		  success: function(data){	
			    var swfFilePath = '<%=path%>/attachment_viewFile.action?attachmentUid=${attachmentUid}';
				$('#viewerPlaceHolder').FlexPaperViewer({ config : {
						 FlexPaperViewerSrc:'<%=path%>/commons/flexpaper/FlexPaperViewer.swf',
						 SwfFile : swfFilePath,
						 Scale : 0.6, 
						 ZoomTransition : 'easeOut',
						 ZoomTime : 0.5,
						 ZoomInterval : 0.2,
						 FitPageOnLoad : true,
						 FitWidthOnLoad : true,
						 FullScreenAsMaxWindow : false,
						 ProgressiveLoading : true,
						 MinZoomSize : 0.2,
						 MaxZoomSize : 5,
						 SearchMatchAll : false,
						 InitViewMode : 'Portrait',
						 PrintPaperAsBitmap : false,
						 
						 ViewModeToolsVisible : true,
						 ZoomToolsVisible : true,
						 NavToolsVisible : true,
						 CursorToolsVisible : true,
						 SearchToolsVisible : true,					
						 localeChain: 'zh_CN'
				 }});
		  },
		  error:function(){			 
			  alert("服务器异常，请稍后再试！");
		  }			  
	});	
});
</script>
</head>
<body scroll="no">
<div id="viewerPlaceHolder" style="width:100%;">
    <div id="progressDialog" style="font-size: 16px;font-style: normal;vertical-align: middle;">
		<center>		
			<br/>
			数据加载中,请稍后……
			<br/>
			<br/>
			<img src="<%=path %>/images/view/progress.gif"/>
		</center>
	</div>
</div>
</body>
</html>