<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html  class="ui-mobile">
  <head>
    
    <title>会员管理</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="../../web/css/jquery.mobile-1.2.0.min.css" />
  <script src="../../web/js/jquery-1.8.3.js"></script>
  <script src="../../web/js/jquery.mobile-1.2.1.min.js"></script>
  </head>
  <body class="ui-mobile-viewport">
       <div data-role="page" id="zhu">
    		<div data-role="header" data-theme="b">
    			<h1>个人会员中心</h1>
    			<a href="#change" style="text-decoration: none;float: right;">完善资料</a>
    		</div>
    		<div data-role="content" align="left">
    		
    		<div style="margin:0 auto; position:relative;bottom:10px; width:270px;height:150px;background:url(../../web/images/beijing.png) no-repeat; ">
    			<table>
    				<tr>
    					<!-- <td><img src="../../web/images/new.gif" width="100px;" height="100px;"></td> -->
    					<td><img src="../../web/images/<s:property value='member.cardId'/>.jpg" width="100px;" height="100px;"></td>
    					<td>
    						<table>
    							<tr><td style="color: #9E854C; font: bold 16pt  Microsoft JhengHei "><s:property value="member2.memberGrade"/></td></tr>
    							<tr><td>积分：<s:property value="jifen" /></td></tr>
    							<tr><td>储值：<s:property  value="member2.saveMoney" /></td></tr>
    							<tr><td>有效期：<s:property value="member2.endTime" /></td></tr>
    						</table>
    					</td>
    				</tr>
    			</table>
    		<hr size="1" noshade="noshade" style="position:relative;top: -10px; color:#ffffff;height:1px;width:250px;">
    		<div style="position:relative;top: -15px;left: 5px;"><s:property value="member.cardId" /></div>
    		</div>
    			<ul data-role="listview" data-dividertheme="d" style="margin-top: 0;">
   					<!-- <div style="background-color: #c5c0c0;width: 100%;color: #ea2b2b;">查询</div> -->
   					<li data-role="list-divider" style="color: #ea2b2b;">查询</li>
   					<table>
   						<tr>
   							<td width="60%"><a href="../../site/web/vip-web!getOne?id=<s:property  value='member.memberId' />&enterId=${enterId }&cardId=<s:property value='member.cardId'/>" 
   							style="text-decoration: none;">积分查询</a></td>
   							<td><a href="/wxpt/site/web/storerecord!storerecordMessage?memberId=<s:property value="member.memberId" />&enterId=${enterId }&carId=<s:property value='member.cardId'/>" style="text-decoration: none;">储值记录</a></td>
   						</tr><%--
   						<tr>
   							<td><a href="#" style="text-decoration: none;">可用计次</a></td>
   							<td><a href="#" style="text-decoration: none;">可用电子券</a></td>
   						</tr>
   					--%></table>
   					<!-- <div style="background-color: #c5c0c0;width: 100%;color: #ea2b2b;">互动</div> -->
   					 <li data-role="list-divider" style="color: #ea2b2b;">互动</li>
   					 
   					<table><%--
   						<tr>
   							<td width="65%"><a href="#" style="text-decoration: none;">订购商品</a></td>
   							<td><a href="#" style="text-decoration: none;">兑换礼品</a></td>
   						</tr>
   						--%><tr>
   							<td><a href="/wxpt/site/web/vip-web!setMessages?cardId=<s:property value="member.cardId" />&enterId=${enterId }" data-ajax="false" style="text-decoration: none;">意见建议</a></td>
   						</tr>
   					</table>
   					<!-- <div style="background-color: #c5c0c0;width: 100%;color: #ea2b2b;">优惠</div> -->
   					<li data-role="list-divider" style="color: #ea2b2b;">优惠</li>
   					<table>
   						<tr>
   							<td width="55%"><a href="/wxpt/site/web/vip-web!showActivity?cardId=<s:property value="member.cardId" />&enterId=${enterId }" data-ajax="false" style="text-decoration: none;">优惠活动</a></td>
   							<td><a href="/wxpt/site/web/vip-web!showTicket?memberId=<s:property value="member.memberId" />&cardId=<s:property value="member.cardId" />&enterId=${enterId }" data-ajax="false" style="text-decoration: none;">活动优惠券</a></td>
   						</tr>
   					</table>
   				</ul>
   			</div>
   			 <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微聚家</h4>
   </div><!-- /footer -->
    	</div>
    	
    	 <div data-role="page" id="change">
    		<div data-role="header" data-theme="b">
    			<h1>修改资料</h1>
    			<a href="#zhu" style="text-decoration: none;">返回</a>
    			<a href="#password" style="text-decoration: none;">密码修改</a>
    		</div>
    		<div data-role="content" align="left">
    			<form id="ff">
    			<input type="hidden" value='<s:property value="member.memberId"/>' name="id" id="id">
    			<input type="hidden" value='<s:property value="enterId"/>' name="enterId" id="enterId">
    				<table>
    					<tr>
    						<td width="60px;">姓名:</td>
    						<td><input width="10px;" type="text" name="xingming" id="xingming" value='<s:property value="member.username"/>'></td>
    					</tr>
    					<tr>
    						<td width="60px;">性别:</td>
    						<td><select name="xingbie" id="xingbie">
    						<s:if test="member.sex==0">
					      	<option value="2">男</option>
    					  	<option value="1">女</option>
    					  	</s:if>
    					  	<s:if test="member.sex==1">
					      	
    					  	<option value="1">女</option>
    					  	<option value="2">男</option>
    					  	</s:if>
    					  	<s:if test="member.sex==2">
					      	<option value="2">男</option>
    					  	<option value="1">女</option>
    					  	</s:if>
    					  	</select></td>
    					</tr>
    					<tr>
    						<td width="60px;">年龄:</td>
    						<td><input width="10px;" type="text" name="nianling" id="nianling" value='<s:property value="member.age"/>'></td>
    					</tr>
    					<tr>
    						<td width="60px;">手机号:</td>
    						<td><input width="10px;" type="text" name="shoujihao" id="shoujihao" value='<s:property value="member.phone"/>'></td>
    					</tr>
    					<tr>
    						<td width="60px;">详细地址:</td>
    						<td><input width="10px;" type="text" name="xiangxidizhi" id="xiangxidizhi" value='<s:property value="member.address"/>'></td>
    					</tr>
    				</table>
    				<input type="button" value="保存" onclick="save()">
   				</form>
   			</div>
   			 <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微聚家</h4>
   </div><!-- /footer -->
    	</div>
    	<script>
    		function save(){
    		var ps4=$("#cid").val();
    			 $.ajax({
		          type:"POST",
		          url:"../../site/web/vip-web!getUp",
					data : '' + $("#ff").serialize(),

		         success:function (){
		        	 alert("修改信息成功");
		        	window.location.href="/wxpt/site/web/vip-web?enterId=${enterId }&cardId="+ps4;	   
		         },
			  	 error:function (){
			  		 alert("修改失败!!!");
			  	 }
		       });
    			
    		}
    	</script>
    	 <div data-role="page" id="password">
    		<div data-role="header" data-theme="b">
    			<h1>密码修改</h1>
    			<a href="#change" style="text-decoration: none;">返回</a>
    		</div>
    		<div data-role="content" align="left">
    		<input type="hidden" value='<s:property value="member.cardId"/>' name="cid" id="cid">
    		
    			<form id="fff">
    			<input type="hidden" value='<s:property value="member.memberId"/>' name="id" id="id">
    			<input type="hidden" value='<s:property value="member.password"/>' name="password3" id="password3">
    			<input type="hidden" value='<s:property value="enterId"/>' name="enterId" id="enterId">
    				<table>
    					<tr>
    						<td width="80px;">旧密码：</td><td><input type="password" name="password4" id="password4"></td>
    					</tr>
    					<tr>
    						<td>新密码：</td><td><input type="password" name="password1" id="password1"></td>
    					</tr>
    					<tr>
    						<td>确认密码：</td><td><input type="password" name="password2" id="password2"></td>
    					</tr>
    				</table>
    				<input type="button" value="提交" onclick="edit()">
    			</form>
   			</div>
   			 <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微聚家</h4>
   </div><!-- /footer -->
    	</div>
    	<script>
    		function edit(){
    			var ps=$("#password3").val();
    			var ps1=$("#password4").val();
    			if(ps==ps1){
    			var ps2=$("#password1").val();
    			var ps3=$("#password2").val();
    			var ps4=$("#cid").val();
    				if(ps2==ps3){
    					$.ajax({
		          type:"POST",
		          url:"../../site/web/vip-web!edit",
					data : '' + $("#fff").serialize(),

		         success:function (){
		        	 alert("修改成功");
		        	window.location.href="/wxpt/site/web/vip-web?enterId=${enterId }&cardId="+ps4;	   
		         },
			  	 error:function (){
			  		 alert("修改失败!!!");
			  	 }
		       });
    			
    				}else{
    					alert("新密码不一致！！");
    				}
    			}else{
    				alert("旧密码输入不正确！！");
    			}
    		}
    		
    	</script>
  </body>
</html>
