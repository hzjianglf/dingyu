<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>意见建议</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	 <link rel="stylesheet" href="/wxpt/web/css/jquery.mobile-1.2.0.min.css" />
	  <link rel="stylesheet" href="/wxpt/web/css/jquery.mobile.theme-1.2.1.css" />
  <script src="/wxpt/web/js/jquery-1.8.3.js"></script>
  <script src="/wxpt/web/js/jquery.mobile-1.2.1.min.js"></script>

 
  </head>
  
  <body>
  
  <script>
 
 	function tijiao()
 	{
 	 //alert("lail"+$("#cardId").val()+$("#title").val()+$("#content").val());
 		$.ajax({
 			
 			type:'post',
 			url:"/wxpt/site/web/vip-web!addMessage",
 			data:{
 				cardId:delTrim($("#cardId").val()),
 				title:delTrim($("#title").val()),
 				content:delTrim($("#content").val()),
 				enterId: delTrim($("#enterId").val())
 			},
 			success:function(data)
 			{
 				if(data)
 				{
 					
 					window.location.href="/wxpt/site/web/vip-web?enterId=${enterId }&cardId="+$('#cardId').val();
 					alert("留言成功");
 				}
 			},
 		});
 		
 	}
 	function see()
 	{
 		window.location.href="/wxpt/site/web/vip-web!setOneMessage?enterId=${enterId }&cardId="+$('#cardId').val();
 	}
 	function back()
 	{
 		window.location.href="/wxpt/site/web/vip-web?enterId=${enterId }&cardId="+$('#cardId').val();
 		
 	}
 	
 	//去除两头空格
 	  function delTrim(str){
 	  	return str.replace(/^\s+|\s+$/g,"");
 	  }
 	  
 
 
 </script>
  
  
    <div data-role="page">
   <div data-role="header" data-theme="b">
   <a href="javascript:see()" data-role="button" data-theme="b" data-icon="arrow-d" data-iconpos="right">商家回复</a>
       
			<h4 align="center">留言</h4>
	
	<a href="javascript:back()" data-role="button" data-theme="b" data-icon="arrow-l">返回</a>
	
   </div><!-- /header -->
   <div data-role="content" class="content">
   <input type="hidden" value="${cardId }" name = "cardId" id="cardId">
   <input type="hidden" value="${enterId }" name = "enterId" id="enterId">
      标题：<input type="text" name="title" id="title">   
      内容：<textarea rows="3" cols="30" style="height: 200px; overflow-x:hidden" name="content" id="content"></textarea>
   <a href="javascript:tijiao()" data-role="button" data-theme="e" >提交</a>
   </div><!-- /content -->
   <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微信通</h4>
   </div><!-- /footer -->
</div><!-- /page -->
  </body>
</html>
