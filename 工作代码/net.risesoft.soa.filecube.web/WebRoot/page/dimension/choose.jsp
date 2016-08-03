<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择维度</title>
<style type="text/css">
	.dimensionContent{
		line-height: 30px;
		background-color: #C0C0C0;
		margin-top: 1px;
		cursor: pointer;
	}
</style>
<script type="text/javascript" src="<%=path %>/js/dimension/choose.js"></script>
</head>
<body>
	<div style="width: 100%;float: left;">
		
		<fieldset style="width: 40%;height:260px;float: left;
				margin-left: 30px;margin-top: 10px;border:  2px solid #F7F5F5;  ">
			<legend style="margin-left: 10px;text-align: center;">
					<h1 style="color: #333333;
	    			font-family: '微软雅黑','黑体',arial;
	    			font-size: 12px;">
	    				选择维度
	    			</h1>
    		</legend>		
			<div id="chooseDimensionDiv" style="padding-left: 10px;
				padding-top: 10px;padding-right: 10px;font-family: '微软雅黑','黑体',arial;
	    			font-size: 14px;text-align: center;">				
				
			</div>
		</fieldset>
		
		<fieldset style="width: 40%;float: right;height:260px;
		margin-right: 30px;margin-top: 10px;border:  2px solid #F7F5F5; ">
			<legend style="margin-left: 10px;text-align: center;">
					<h1 style="color: #333333;
	    			font-family: '微软雅黑','黑体',arial;
	    			font-size: 12px;">
	    				选择维度顺序
	    			</h1>
    		</legend>		
			<div id="previewDimensionDiv" style="padding-left: 10px;
				padding-top: 10px;padding-right: 10px;">				
				
			</div>
		</fieldset>			
	</div>
	<center>
		<button style="margin-top: 15px;" onclick="chooseDimension()">确定</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="popWin.close()">取消</button>
	</center>
</body>
</html>