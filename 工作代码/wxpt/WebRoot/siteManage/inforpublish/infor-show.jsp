<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title> ${infor.title}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="<%=basePath %>web/css/jquery.mobile-1.2.1.css" />
  <script src="<%=basePath %>web/js/jquery-1.8.3.js"></script>
  <script src="<%=basePath %>web/js/jquery.mobile-1.2.1.js"></script>
</head>
<body>
  <div data-role="content">
    <center>
  <h1>${infor.title}</h1>
 发布时间：${infor.addTime}
 <%-- <s:if test="${infor.image} !=null ">
 <p><a ><img src="http://www.uniqyw.com/schooloa/web/${infor.image}" style="height: 400px;width: 278px;"></img> </a></p>
</s:if> --%>
 <p><a >${infor.content}</a></p>
    </center>
  </div>
</body>
<script>
</script>
</html>