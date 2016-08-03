<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/left.css" />

<script type="text/javascript" src="<%=path %>/commons/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/relevanceFile/fileList.js"></script>
<title>关联文件列表</title>
<script type="text/javascript">
	
</script>
<style type="text/css">
html{
	margin: 0px 0px;
	overflow: hidden;
}
.relevanceOption{
	line-height: 35px;
	height:35px;
	background: none repeat scroll 0 0 #F5F5F5;	
	
}
.relevanceOptionTable{
	margin-top:15px;
	margin-left:5px;
	width: 200px;
	font-size: 14px;
	font-family: '微软雅黑','黑体',arial;
}
.relevanceOptionTable tr{
	
}
.relevanceOption td{	
	padding-left: 30px;
	border-bottom: 1px dashed #E5E5E5;		
	vertical-align: middle;	
	cursor: pointer;
	padding-left: 50px;
}
</style>
<script type="text/javascript">
	//文件uid
	_fileList_fileUid = '${fileInfo.fileUid}';
	_fileList_fileName = '${fileInfo.name}';
</script>
</head>
<body>
<div style="width: 100%;">
<div style="width:15%;float: left;">
<table cellpadding=0 cellspacing=0 class="relevanceOptionTable" >				
	<tr class="relevanceOption" id="relevanceByName">
		<td>
			按名称关联
		</td>
	</tr>
	
	<tr class="relevanceOption" id="relevanceByContent">
		<td>
			按内容关联
		</td>
	</tr>			
</table>
</div>
<div id="fileList" style="width: 85%;float: right;">
	<iframe height="660px" width="100%" frameborder="0" style="margin: 0px 0px;" id="fileListIframe"
		src=""></iframe>
</div>
</div>
</body>
</html>