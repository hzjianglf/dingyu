<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  class="ui-mobile">
  <head>
    
    <title>会员储值记录</title>
    
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
     <script src="../../web/js/jquery-1.8.3.js"></script>
  <link rel="stylesheet" href="../../web/css/jquery.mobile-1.2.0.min.css" />
  <script src="../../web/js/jquery.mobile-1.2.1.min.js"></script>
 <style type="text/css">
	

/*火狐*/
@-moz-document url-prefix() {

.sort_page .shouye1{color:gray;}

.sort_page a.shouye1:hover{color:gray;}
}
/*火狐 end*/

 /*谷歌*/
@media screen and (-webkit-min-device-pixel-ratio:0) {

.sort_page .shouye1{color:gray;}

.sort_page a.shouye1:hover{color:gray;}


}
 /*谷歌 end*/
</style>
  </head>

  <body class="ui-mobile-viewport">
   	<div data-role="page">
		<div data-role="header" data-theme="b" align="center">
			<h1>充值记录</h1>
			<a  href="/wxpt/site/web/vip-web?enterId=${enterId }&cardId=${carId}"
				style="text-decoration: none;float: right;">返回</a>
		</div>
		<div data-role="content" align="center">
			<table border="1" cellpadding="0" cellspacing="0" style="position:relative; width:100%;">
				<tr style="background-color: gray;" >
					<td align="center">
					店面</td>
					<td align="center">充值</td>
					<td align="center">时间</td>
					<td align="center">金额</td>
					<td align="center">
					卡号</td>
					<td align="center">
					查看详细信息</td>
				</tr>
				 <c:forEach items="${pageBean.list}" var="bean">
				<tr>
					<td align="center">${bean.businessName}</td>
					<td  align="center">${bean.money}</td>
					<td  align="center">${bean.recordtime}</td>
					<td align="center">${bean.presentMoney }</td>
					<td  align="center">${bean.member.cardId}</td>
					<td  align="center"><a href="/wxpt/site/web/storerecord!singleStorerecord?idStorerecord=${bean.id}&enterId=${enterId}&memberId=${memberId}">查看</a></td>
					
					
					<%-- ?idStorerecord=${bean.id}& --%>
				</tr>
				</c:forEach>
				</table>
						<div align="center" class="sort_page" style="margin-top: 15px;">
				<span>共<s:property value='pageBean.recordCnt' />条，<s:property
				value='pageBean.curPage' />/<s:property value='pageBean.pageCnt' />页</span>

		<s:if test="pageBean.curPage==1">
			<a class="shouye1" disabled="disabled">首页</a>
		</s:if>
		<s:else>
			<a href="/wxpt/site/web/storerecord!storerecordMessage?curPage=1&enterId=${enterId }&memberId=${memberId}&carId=${carId}">首页</a>
		</s:else>
		<s:if test="pageBean.curPage==1">
			<a class="shouye1" disabled="disabled">上一页</a>
		</s:if>
		<s:else>
			<a href="/wxpt/site/web/storerecord!storerecordMessage?curPage=<s:property value='pageBean.prePage'/>&enterId=${enterId }&memberId=${memberId}&carId=${carId}">上一页</a>
		</s:else>
		<s:if test="curPage==nextPage">
			<a class="shouye1" disabled="disabled">下一页</a>
		</s:if>

		<s:else>
			<a href="/wxpt/site/web/storerecord!storerecordMessage?curPage=<s:property value='pageBean.nextPage'/>&enterId=${enterId }&memberId=${memberId}&carId=${carId}">下一页</a>
		</s:else>
	 	<s:if test="curPage==pageBean.lastPage">
			<a class="shouye1" disabled="disabled">尾页</a>
		</s:if>
		<s:else>
			<a href="/wxpt/site/web/storerecord!storerecordMessage?curPage=<s:property value='pageBean.lastPage'/>&enterId=${enterId }&memberId=${memberId}&carId=${carId}">尾页</a>
		</s:else>
		<%-- 跳转到
						<select id="sp" name="select2" onchange="gopag()">
							<c:forEach begin="1" end="${pageBean.pageCnt}" varStatus="pstate">
								<option value="${pstate.count }" ${pstate.count==pageBean.curPage?
									"selected='selected'":""}>
									${pstate.count }
								</option>
							</c:forEach>
						</select> --%>
		</div>
	
			
	
		</div>
		<div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微信通</h4>
   </div><!-- /footer -->
	</div>
<!-- 从后台获取会员的id -->
<input value="1" id="memberId" type="hidden"/>
  </body>
		<script type="text/javascript">
		
		function gopag(){
			<%--用来获取下拉列表的 选项的值--%>
			var sp=document.getElementById("sp").value;
			var memberId=document.getElementById("memberId").value;
			document.location.href="/wxpt/site/web/storerecord!storerecordMessage?curPage="+sp+"&enterId=${enterId }&memberId="+memberId;
		}
</script>
</html>
