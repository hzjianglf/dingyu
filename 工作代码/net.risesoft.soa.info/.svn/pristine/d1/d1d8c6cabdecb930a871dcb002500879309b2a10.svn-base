<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="net.risesoft.soa.info.manager.menu.ContainerMenu"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
ContainerMenu rm = new ContainerMenu(request);
String menu = rm.getMenuJson();
%>
<html>
<head>
<title></title>
<script type="text/javascript">
  document.getElementById("nodePath").innerHTML="路径：" + getBreadcrumbPath();
  var nodeID = '${resource.id}';
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
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">资源名称</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px" width="150">${resource.name}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">唯一标识</td>
       <td colspan="3" style="border-style: dotted; border-width: 1px; padding:5px">${resource.id}</td>
     </tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">资源别名</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${resource.aliasName}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">显示图标</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${resource.icon}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">打开类型</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px" width="150">${resource.openType}</td>
     </tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">链接地址</td>
       <td colspan="5" style="border-style: dotted; border-width: 1px; padding:5px; word-break: break-all; word-wrap:break-word"><a href=${resource.url} target=_blank>${resource.url}</a></td>
     </tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">站点地址</td>
       <td colspan="5" style="border-style: dotted; border-width: 1px; padding:5px; word-break: break-all; word-wrap:break-word">${resource.site}</td>
     </tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">打开位置</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${resource.target}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">资源状态</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${resource.status}</td>
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">是否继承</td>
       <td style="border-style: dotted; border-width: 1px; padding:5px">${resource.inherit}</td>
     </tr>
     <tr height="30">
       <td style="border-style: dotted; border-width: 1px" align="center" width="80">资源描述</td>
       <td colspan="5" style="border-style: dotted; border-width: 1px; padding:5px" width="150">${resource.description}</td>
     </tr>
     </table>
  </td></tr>
</table>
<br>
</body>
</html>
