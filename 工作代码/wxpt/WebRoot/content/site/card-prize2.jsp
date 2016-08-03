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
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<div data-role="page">
<body style="background: black;">
	<input id="prvFileName" style="display: none;" value="${prvFileName }">
	<div id="wScratchPad3"
		style="width:378px; height:265px; display:inline-block; border:solid white 0px;
		margin: 0px auto;position: absolute;left: 30%;top: 30%"></div>
	<script type="text/javascript"
		src="<%=basePath%>/manager/js/jquery-1.8.3.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/manager/js/wScratchPad.js"></script>
		<script type="text/javascript"
		src="<%=basePath%>/manager/js/jquery.mobile-1.2.1.min.js"></script>
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
</div>
</html>
