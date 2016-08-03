<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的历史记录</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
</head>
<body>	
	<!-- 导航区域 -->
	<div style="padding-left: 10px;padding-top: 10px;padding-bottom: 10px;
	border-bottom: 1px solid #E5E5E5;">您的位置：
		
			个人中心 >> 我的历史记录 >> 浏览记录  
		
		<br/>
	</div>	
	<div class="listDataTableStyle">
		<table cellpadding=0 cellspacing=0>
			<thead>
				<tr >
					<th width="3%">&nbsp;</th>				
					<th width="25%" >名称</th>
					<th width="15%" >浏览时间</th>
				</tr>			
			</thead>
			<tbody>
				<s:if test="#fileOpenHistories.size == 0">
					<tr><td colspan="3" align = "center"><font color=red>暂无数据！</font></td></tr>
				</s:if>		
				<s:iterator value="fileOpenHistories" id="openHistory" status="index">
					<tr valign="middle" 
						<s:if test="#index.count % 2 == 0">
							class = "changeColor"
						</s:if>
					>
						<td style="text-align: center;"><s:property value='#index.count'/></td>
						<td>						
							<img src="<%=path %>/images/file/small/files/<s:property value='#openHistory.fileInfo.extension'/>.gif" style="vertical-align: middle;">
							
							<font color="blue" style="cursor: pointer;" 
							onclick=preViewdocument('<s:property value='#openHistory.fileInfo.fileUid'/>') >
							<s:property value='#openHistory.fileInfo.name'/></font>				
						</td>
						<td><s:date name="#openHistory.openDate" format="yyyy-MM-dd" /></td>					
					</tr>
				</s:iterator>
			</tbody>		
		</table> 
	</div>
</body>
</html>