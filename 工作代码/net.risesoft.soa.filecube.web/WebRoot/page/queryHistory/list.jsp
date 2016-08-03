<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询列表</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
</head>
<body>
	<!-- 导航区域 -->	
	<div style="width:100%;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;
		border-bottom: 1px solid #E5E5E5;">
		<p style="margin-left: 2px;">
		       您的位置：个人中心 >> 我的历史记录 >> 查询记录  			
		</p>				
	</div>	
	<!-- 数据区域 -->
	<div class="listDataTableStyle">
		<table cellpadding=0 cellspacing=0>
			<thead >
				<tr >
					<th width="3%" >&nbsp;</th>				
					<th width="40%"> 查询内容</th>
					<th width="30%" > 查询日期 </th>
				</tr>			
			</thead>
			<tbody>
				<s:if test="#queryHistories.size == 0">
					<tr><td colspan="3" align = "center"><font color=red>暂无数据！</font></td></tr>
				</s:if>		
				<s:iterator value="queryHistories" id="queryHistory" status="index">					
					<tr 
						<s:if test="#index.count % 2 == 0">
							class = "changeColor"
						</s:if>
					>
						<td style="text-align: center;"><s:property value='#index.count'/></td>
						<td>					
							<s:property value='#queryHistory.queryContent'/></font>				
						</td>
						<td><s:date name="#queryHistory.queryDate" format="yyyy-MM-dd" /></td>
					</tr>
				</s:iterator>
			</tbody>		
		</table> 
	</div>
</body>
</html>