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
<div class="eject_con" id="content">
<jsp:include page="/web/top.jsp" />
<div class="add" style="margin-top: 50px;">
    <form method="post" action="../../site/web/order!getSaveAddress" id="address_form">
    <input type="hidden" name="lid" value="<s:property value='sa.shoppingAddressId'/>">
    <ul class="form_address">
        <li>
             <h3>收货人姓名: </h3> 
            <input type="text" class="text width_normal" name="name">
            <label class="field_message"><span class="field_notice"></span></label>
        </li>
        <li>
             <h3>详细地址: </h3>
            <input type="text" class="text width_normal" name="address" ><label class="field_message"><span class="field_notice"></span></label>
        </li>
        <li>
             <h3>手机号码:</h3> 
            <input type="text" class="text width_normal" name="phone"><label class="field_message"><span class="field_notice"></span></label>
        </li>
    </ul>
    <div class="submit"><input type="submit" class="btn enter" value="新增地址"></div>
    </form>
</div>
</div>
<jsp:include page="/web/bottom.jsp" />

</body></html>