<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<!DOCTYPE html>
<%
String id = request.getParameter("id");
String isExist = request.getParameter("isExist");
%>
<html>
<head>
<script type="text/javascript" src="/info/flexpaper/flexpaper.js"></script>
<script language="javascript">
function autoHeight() {
	var h = $(window).height() - 20;
	if (h < 400){
		h = 400;
	}
	$(".left").css('height', h);
}
 
$(document).ready(function(){
	autoHeight();
	//$(window).resize(autoHeight);
});
</script>
<style type=”text/css”>
.left {
}
 </style>
</head>
<body>
<div id="viewerPlaceHolder" class="left">
<script type="text/javascript">    	
	$('#viewerPlaceHolder').FlexPaperViewer(
			 { config : {
			 SwfFile : '/info/infoFileShow.action?id=<%=id%>',
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
			 
			 src: '/info/flexpaper/FlexPaperViewer.swf',
			
			 localeChain: 'zh_CN'
	}});
</script>
</div>
</body>
</html>
