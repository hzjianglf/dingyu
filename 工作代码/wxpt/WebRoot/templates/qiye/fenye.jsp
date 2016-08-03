<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/templates/"
			+ request.getAttribute("templateName") + "/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/" + request.getAttribute("enterID")
			+ "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<base href="<%=basePath%>">
<script type="text/javascript" src="<%=basePath%>js/url.js"></script>
<footer>
	<div class="foot">
		<ul>
			<li><a href="javascript:fenye(1)">首页</a></li>
			<li><s:if test="pageNo == 1">
				上一页
			</s:if> <s:if test="pageNo > 1">
					<a href="javascript:fenye(<s:property value="pageNo-1"/>)">上一页</a>
				</s:if></li>
			<li><s:if test="pageNo == count">
			下一页
			</s:if></li>
			<li><s:if test="pageNo < count">
					<a href="javascript:fenye(<s:property value="pageNo+1"/>)">下一页</a>
				</s:if></li>
			<li><a href="javascript:fenye(<s:property value="count"/>);">尾页</a></li>
		</ul>
	</div>
</footer>