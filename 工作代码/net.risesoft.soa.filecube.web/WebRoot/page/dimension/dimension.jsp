<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择维度页面</title>
<script type="text/javascript" src="<%=path %>/js/dimension/dimension.js"></script>
<script type="text/javascript">
//维度列表分页的起始位置
var _dFilePageStart = 0;
//分页的行数
var _dFilePagePageSize = parseInt((_leftWinHeight - 220)/30);
</script>
</head>
<body>	
	<div id="chooseDimensionButton"></div>
    <div id="showDimensionTree">
    	
	</div>	
</body>
</html>