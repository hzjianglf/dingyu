<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>微商城</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script src="../../web/js/jquery-1.8.3.js"></script>
<link href="../../web/css/shoppingstyle.css" rel="stylesheet" type="text/css">

</head>
<body>

<div id="content">
<jsp:include page="/web/top.jsp" />
        <div class="wrap" style="margin-top: 50px;">
            <div class="eject_btn" title="新增地址"><a class="enter" href="../../site/web/order!getSave">新增地址</a></div> 
<ul class="address_list">
	<s:iterator value="lsa" var="lsa">
	<li>
    	<p>姓名：<s:property value="#lsa.name" /> (<s:property value="#lsa.phone" />)</p>
        <p>地址：<s:property value="#lsa.address" /></p>
        <p class="new_line"><br></p>
        <p class="address_action">
        	<span class="edit"><a href="../../site/web/order!getChangeAddress?lid=<s:property value="#lsa.shoppingAddressId" />"><i class="edit_icon"></i>编辑</a></span>
            <span><a href="../../site/web/order!getDeleteAddress?lid=<s:property value="#lsa.shoppingAddressId" />" class="delete float_none"><i class="delete_icon"></i>删除</a></span>
        </p>
    </li>
    </s:iterator>
</ul>
            <div class="wrap_bottom"></div>
        </div>
    </div>
<jsp:include page="/web/bottom.jsp" />
</body></html>
