<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html class="ui-mobile">
<head>

<title>会员管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../../web/css/jquery.mobile-1.2.0.min.css" />
<script src="../../web/js/jquery-1.8.3.js"></script>
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
		<div data-role="header" data-theme="b">
			<h1>历史积分</h1>
			<a href="../../site/web/vip-web!getOne?enterId=${enterId }&id=${id }&cardId=${cardId }"
				style="text-decoration: none;float: right;">返回</a>
		</div>
		<div data-role="content" align="center">
			<table border="1" cellpadding="0" cellspacing="0" style="position:relative; width:100%;">
				<tr style="background:gray;">
				<!-- 	<td width="15%" align="center">店面</td>
					<td width="15%" align="center">积分</td>
					<td width="30%" align="center">时间</td>
					<td width="20%" align="center">备注</td>
					<td width="20%" align="center">查看</td> -->
					<td  align="center">店面</td>
					<td  align="center">积分</td>
					<td  align="center">时间</td>
					<td  align="center">备注</td>
					<td  align="center">查看</td>
				</tr>
				 <c:forEach items="${inte2 }" var="in">
				<tr>
					<td style="position:relative; width:15%; text-overflow:clip;" valign="top"  align="center"><span style="position:absolute;height:16px;overflow:hidden;">${in.integralsBusiness }</span></td>
					<td style="position:relative; width:15%;" align="center">${in.integralsOne }</td>
					<td style="position:relative; width:35%;" align="center">${in.integralsTime }</td>
				<%-- 	<td style="position:relative; width:20%;" align="center">${in.integralsRemark }</td> --%>
					<td style="position:relative; width:15%; text-overflow:clip;" valign="top"  align="center"><span style="position:absolute;height:16px;overflow:hidden;">${in.integralsRemark }</span></td>
					<td style="position:relative; width:15%;" align="center"><a href="../../site/web/vip-web!xiangxi?enterId=${enterId }&id=${id }&cardId=${cardId }&intid=${in.integralsId }">详细</a></td>
				</tr>
				</c:forEach>
			</table>
			<div align="center" class="sort_page" style="margin-top: 15px;">
				<span>共<s:property value='listCount' />条，<s:property
				value='currentpage' />/<s:property value='pageCount' />页</span>

		<s:if test="currentpage==1">
			<a class="shouye1" disabled="disabled">首页</a>
		</s:if>
		<s:else>
			<a href="/wxpt/site/web/vip-web!getList?currentpage=1&enterId=${enterId }&id=${id }&cardId=${cardId }">首页</a>
		</s:else>
		<s:if test="currentpage==1">
			<a class="shouye1" disabled="disabled">上一页</a>
		</s:if>
		<s:else>
			<a href="/wxpt/site/web/vip-web!getList?currentpage=<s:property value='currentpage-1'/>&enterId=${enterId }&id=${id }&cardId=${cardId }">上一页</a>
		</s:else>
		<s:if test="currentpage==pageCount||pageCount==0">
			<a class="shouye1" disabled="disabled">下一页</a>
		</s:if>
		<s:else>
			<a href="/wxpt/site/web/vip-web!getList?currentpage=<s:property value='currentpage+1'/>&enterId=${enterId }&id=${id }&cardId=${cardId }">下一页</a>
		</s:else>
		<s:if test="currentpage==pageCount||pageCount==0">
			<a class="shouye1" disabled="disabled">尾页</a>
		</s:if>
		<s:else>
			<a href="/wxpt/site/web/vip-web!getList?currentpage=<s:property value='pageCount'/>&enterId=${enterId }&id=${id }&cardId=${cardId }">尾页</a>
		</s:else>
		</div>
		</div>
	 <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微信通</h4>
   </div><!-- /footer -->
	</div>
</body>
</html>
