<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>刮刮乐</title>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
		<script type="text/javascript"
		src="<%=basePath%>/manager/js/jquery-1.8.2.min.js"></script>

</head>

<body style="background: white;">
	<input id="fileName" style="display: none;" value="${fileName }">
	<div id="wScratchPad3" class="show"
		style="display:inline-block; position:relative; border:solid black 1px;
		margin: 0px auto;position: absolute;left: 30%;top: 30%;overflow:hidden;">
		
		</div>
		<div class="hidden" style="display: none">
	<img id="imageE" src="/wxpt/web/images/${fileName }" width="500"  height="300" hidden="hidden">
		</div>
	<script type="text/javascript"
		src="<%=basePath%>/manager/js/wScratchPad.js"></script>
	<script type="text/javascript">
	
		$("#wScratchPad3").wScratchPad({
			cursor : '../cursors/mario.png',
			scratchMove : function(e, percent) {
				if (percent > 70)
					this.clear();
					
			},
		});
	</script>
</body>
</html>
