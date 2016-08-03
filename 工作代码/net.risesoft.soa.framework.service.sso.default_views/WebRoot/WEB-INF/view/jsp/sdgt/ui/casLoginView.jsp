﻿<%@ page session="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@page import="sso.sdgt_views.CvmConfig"%>
<%@page import="java.util.List"%>
<%@page import="com.itrus.cert.Names"%>
<%@page import="com.itrus.cvm.CVM"%>
<%@page import="com.itrus.cert.X509Certificate"%>
<%@page import="egov.appservice.asf.service.ServiceClient"%>
<%@page import="egov.appservice.org.model.Person"%>
<%@page import="egov.appservice.org.service.PersonManager"%>
<%@page import="egov.appservice.asf.service.ServiceClientFactory"%>
<%@page import="egov.appservice.asf.exception.ServiceClientException"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% long aaa= System.currentTimeMillis();%>
<spring:theme code="mobile.custom.css.file" var="mobileCss" text="" />
<%System.out.println("1>>>>>>>>.defaultLoginBgPath"); %>
<%!
private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger("casLoginView.jsp");
%><%
   String defaultLoginBgPath = "Reload.gif";  //缺省背景图
   String lastLoginBgPath = "http://localhost:8000";
   
   defaultLoginBgPath = lastLoginBgPath.equals("") ? defaultLoginBgPath : lastLoginBgPath;
   System.out.println("1>>>>>>>>.defaultLoginBgPath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>综合服务管理平台</title>
</head>
<body>
<%			
	response.setHeader("Pragma","No-Cache"); 

    response.setHeader("Cache-Control","No-Cache"); 

    response.setDateHeader("Expires",   0); 
    System.out.print("1>>>>>>>>.defaultLoginBgPath");
    CvmConfig.init();

	X509Certificate cert = X509Certificate
					.getInstanceFromHttpsClient(request);
			String commonName = null;
			String loginname="";
			if (cert != null) {
				try {
					int ret = CVM.verifyCertificate(cert);
					System.out.println("证书验证结果，Return=[" + ret + "]，");
					if (ret != CVM.VALID) {
						out.println("<div class='asd'><FONT face=Arial color=#0066ff size=2>证书验证出错，请联系管理员，错误信息如下：");
						switch (ret) {
						case CVM.CVM_INIT_ERROR:
							out.println("CVM初始化错误，请检查配置文件或给CVM增加支持的CA。</FONT></div>");
							break;
						case CVM.CRL_UNAVAILABLE:
							out.println("CRL不可用，未知状态。</FONT></div>");
							break;
						case CVM.EXPIRED:
							out.println("证书已过期。</FONT></div>");
							break;
						case CVM.ILLEGAL_ISSUER:
							out.println("非法颁发者。</FONT></div>");
							break;
						case CVM.REVOKED:
							out.println("证书已吊销。</FONT></div>");
							break;
						case CVM.UNKNOWN_ISSUER:
							out.println("不支持的颁发者。</FONT></div>");
							break;
						case CVM.REVOKED_AND_EXPIRED:
							out.println("证书被吊销且已过期。</FONT></div>");
							break;
						case CVM.ACCOUNT_MISMATCH:
							out.println("RA账户不匹配。</FONT></div>");
							break;
						}
						return;
					}
				
					//out.println("证书状态有效。");
                    long ll= System.currentTimeMillis();
					Names certNames = cert.getSubjectNames();
		            commonName = certNames.getItem("SN");
					ServiceClient sc = ServiceClientFactory.getServiceClient();
					PersonManager pm = null;
                                        commonName=commonName.trim();
					System.out.println("登录用户的身份证号码为"+commonName);
					/*
					if("1".equals(commonName)){
					commonName="372501201301050001";
					}
					*/
					try {
						pm = sc.getServiceByName("org.PersonManager");
						List<Person> p = pm.search(" idnum='"+commonName +"'");
						System.out.println(p);
						for (Person p1 : p) {
							loginname = p1.getLoginName();
						}
                        //loginname="gic_zh";
						System.out.println("登录用户的登录名为：" + loginname);
					} catch (ServiceClientException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage()+"---------");
				}
			} else {
				out.println("<div style='MARGIN-RIGHT: auto;MARGIN-LEFT: auto;vertical-align:middle;line-height:600px'><P align=center><FONT face=Arial color=#0066ff size=2>未使用证书登陆，请配置WEB服务器为SSL双向认证。</FONT></P></div>");
			}
			if (cert != null) {
		%>
		<% long bbb= System.currentTimeMillis();%>
		<div style="MARGIN-RIGHT: auto;MARGIN-LEFT: auto;vertical-align:middle;line-height:600px">
<P align=center><FONT face=Arial color=#0066ff size=2>正在登录，请稍候....</FONT></p><p align=center>  
<INPUT style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-WEIGHT: bolder; PADDING-BOTTOM: 0px; COLOR: #0066ff; BORDER-TOP-style: none; PADDING-TOP: 0px; FONT-FAMILY: Arial; BORDER-RIGHT-style: none; BORDER-LEFT-style: none; BACKGROUND-COLOR: white; BORDER-BOTTOM-style: none" size=46 name=chart> 
    <BR>
    <INPUT 
style="BORDER-RIGHT: medium none; BORDER-TOP: medium none; BORDER-LEFT: medium none; COLOR: #0066ff; BORDER-BOTTOM: medium none; TEXT-ALIGN: center" 
size=47 name=percent></P>
</div>
<form:form method="post" id="fm1" name="loginForm" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
   <form:errors path="*" Class="text3" id="status" element="td" />
<div  class="asd"  style="display: none">
   <form:input
                id="username" name="username" onKeyUp="preparaSubmit(event)"  
                tabindex="1" accesskey="${userNameAccessKey}" path="username"
                autocomplete="false" htmlEscape="true" value="<%=loginname%>"/>
   <form:password
                 id="password" name="password" onKeyUp="preparaSubmit(event)" 
                tabindex="2" path="password" accesskey="${passwordAccessKey}"
                htmlEscape="true" autocomplete="off" value="111111"/>
<input type="hidden" name="lastLoginBgPath" id="lastLoginBgPath" value="" />
<input type="hidden" name="lt" value="${flowExecutionKey}" />
<input type="hidden" name="_eventId" value="submit" />
</div>
</form:form>
	<% long bbbb= System.currentTimeMillis(); %>
<div style="MARGIN-RIGHT: auto;MARGIN-LEFT: auto;vertical-align:middle;line-height:600px">
<P align=center><FONT face=Arial color=#0066ff size=2>数字证书验证通过，正在登录，请稍候....</FONT></p><p align=center>  
<INPUT style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-WEIGHT: bolder; PADDING-BOTTOM: 0px; COLOR: #0066ff; BORDER-TOP-style: none; PADDING-TOP: 0px; FONT-FAMILY: Arial; BORDER-RIGHT-style: none; BORDER-LEFT-style: none; BACKGROUND-COLOR: white; BORDER-BOTTOM-style: none" size=46 name=chart> 
    <BR>
    <INPUT 
style="BORDER-RIGHT: medium none; BORDER-TOP: medium none; BORDER-LEFT: medium none; COLOR: #0066ff; BORDER-BOTTOM: medium none; TEXT-ALIGN: center" 
size=47 name=percent></P>
</div>
<script>
	///alert(document.getElementById('username').value);
	document.getElementById('fm1').submit(); 

	var bar=0; 
	var line="||" ;
	var amount="||"; 
	count(); 
	function count(){ 
		bar=bar+2 ;
		amount =amount + line ;
		document.getElementById('chart').value=amount ;
		document.getElementById('percent').value=bar+"%" ;
		if (bar<99) 
		{
			setTimeout("count()",100);
		}
	}    
</script>
<%
			}	
%>
</body>
<%
	 long aaaa= System.currentTimeMillis();
					  System.out.println("*******aaaaaaaaa***********"+(aaaa-aaa));
%>
</html>