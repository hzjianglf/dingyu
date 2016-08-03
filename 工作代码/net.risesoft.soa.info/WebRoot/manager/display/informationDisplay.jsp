<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="net.risesoft.soa.info.manager.menu.InformationMenu"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
InformationMenu im = new InformationMenu(request);
String menu = im.getMenuJson();
%>
<html>
<head>
<title></title>
<script type="text/javascript">
  document.getElementById("nodePath").innerHTML="路径：" + getBreadcrumbPath();
  var nodeID = '${information.id}';
<%
  if (menu != null && menu.length() > 2) {
	out.println("new Ext.Toolbar({renderTo: 'info-tbar',items: "
			+ menu + "});");
  }
%> 
</script>

</head>
<body style="text-align:center" topmargin="50">
<div id="info-tbar"></div>
<table border="0" width="95%" align="center">
  <tr><td height="25" style="border-bottom: 1px solid #C0C0C0" id="nodePath"></td></tr>
  <tr><td>
     <table border="0" align="center" width="700" style="border-collapse: collapse; table-layout:fixed">
     <tr><td colspan="6" align="center" height="30" class="titleFont">基本信息</td></tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">信息名称</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px" width="150">${information.name}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">唯一标识</td>
       <td colspan="3" style="border-style: dotted; border-width: 1px; padding:5px">${information.id}</td>
     </tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">显示图标</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${information.icon}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">所属部门</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${information.module}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">信息类别</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px" width="150">${information.infoType}</td>
     </tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">链接地址</td>
       <td colspan="5" style="border-style: dotted; border-width: 1px; padding:5px; word-break: break-all; word-wrap:break-word"><a href=${information.url} target=_blank>${information.url}</a></td>
     </tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">数据主表</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${information.tableName}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">标题字段</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${information.titleField}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">排序方式</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${information.sortField}</td>
     </tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">列表页数</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${information.pageSize}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">显示方式</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${information.showStyle}</td>
       <td colspan="2" style="border-style: dotted; border-width: 1px" align="center"><a href="/info/downTemplate.action?node=${information.id}">模板下载</a></td>
     </tr>
     </table>
     <br>
     <table border="0" align="center" width="800" style="border-collapse: collapse; table-layout:fixed">
     <tr><td align="center" height="30" class="titleFont">数据映射</td></tr>
     <tr><td>
     <table id="elements-table" height=30 border="1" width="100%" align="center" cellpadding="0" cellspacing="0" style="border-collapse:collapse;" bordercolor="#E1E1E1">
     	<tr align=center height=30 style="background-color: #fafafa;">
      	    <td width=120>字段名称</td>
		    <td width=100>字段类型</td>
		 	<td width=50>列表</td>
		 	<td width=120>列表名称</td>
		 	<td width=60>列表宽度</td>
		 	<td width=60>文字宽度</td>
		 	<td width=100>文字修饰</td>
		 	<td width=100>默认值</td>
		 	<td width=50>搜索</td>
		 	<td width=60>搜索类型</td>
      	</tr>
      	<s:iterator id="list" value="information.elements">
      		<tr align=center height=30>
	      		<td><s:property value="#list.columnName"/></td>
			    <td><s:property value="#list.columnTypeName"/></td>
			 	<td><s:property value="#list.list"/></td>
			 	<td><s:property value="#list.listName"/></td>
			 	<td><s:property value="#list.listLength"/></td>
			 	<td><s:property value="#list.textLength"/></td>
			 	<td><s:property value="#list.textDec"/></td>
			 	<td><s:property value="#list.defaultValue"/></td>
			 	<td><s:property value="#list.search"/></td>
			 	<td><s:property value="#list.searchType"/></td>
      		</tr>
      	</s:iterator>
      	</table>
      	</td>
      </tr>
     </table>
  </td></tr>
</table>
<br>
<br>
</body>
</html>