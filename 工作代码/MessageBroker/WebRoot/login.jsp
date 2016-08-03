<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,com.ibm.hibernate.*, com.opensymphony.xwork2.util.*" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<!-- -->
<!-- Licensed Materials - Property of IBM -->
<!-- 5724-N72 5655-W17 -->
<!-- (c) Copyright IBM Corp. 2006, 2009 All Rights Reserved -->
<!-- US Government Users Restricted Rights - Use, duplication or -->
<!-- disclosure restricted by GSA ADP Schedule Contract with -->
<!-- IBM Corp. -->
<!-- -->

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Type" content="image/svg+xml; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />

<style type="text/css">
/* The browser agent is mozilla/4.0 (compatible; msie 6.0; windows nt 5.1; sv1; .net clr 2.0.50727; .net clr 3.0.04506.648; .net clr 3.5.21022; ciba) */
/* The browser type is ie */
/* The browser version is 6 */
/* The agent locale is zh */
/* The agent OS is nt */
/* The font size multiplier is 1.2 */

</style>

<link rel="stylesheet" href="theme/standard/css/logonStyles.css" type="text/css"/>

<style type="text/css">
body {
  font-size: 0.78em;
}

</style>




<title>WebSphere Message Broker 控制台登录</title>


<script type="text/javascript">
var TRANS_LOGON_ERROR="必须指定用户名。";

var leaveMsg=false;
function clearMsg()
{
  var errorField=document.getElementById("errormsg");
  if (errorField)
  {
    errorField.innerHTML="";
    errorField.style.display="none";
  }
}

function handleFocus()
{
  clearMsg();
}

function handleKeypress(evt)
{
  clearMsg();
}

function handleSubmit(evt)
{
  var okToSubmit=false;
  var userNameInput=document.getElementById("j_username");
  if (userNameInput)
  {
    var userName=userNameInput.value;
    if (userName)
      okToSubmit=true;
    else
    {
      var errorField=document.getElementById("errormsg");
      if (errorField)
      {
        errorField.style.display="block";
	      errorField.innerHTML="<span class='required'>"+TRANS_LOGON_ERROR+"</span>";
      }
    }
  }
  return(okToSubmit);
}
</script>
</head>

<body id="mainbody" class="ie ie6">



<div class="loginbackground" id="loginbackground">
</div>

<div class="loginbanner" id="loginbanner">
  <img src="theme/standard/images/productLogo.png" alt="" id="webspherelogo"/>
  <img src="theme/standard/images/productName.png" alt="WebSphere Message Broker" title="WebSphere Message Broker" id="productname"/>
  <img src="theme/standard/images/companyLogo.gif" alt="IBM 徽标" title="IBM 徽标" id="ibmlogo"/>
</div>

<div class="logincontainer" id="logincontainer">

<div class="dropshadowtopright">
<div class="dropshadowbottomleft">
<div class="dropshadow">

<div class="loginpanel" id="loginpanel">
  <h1>登录</h1>
  <p>输入您的信息。</p>
  <p id="errormsg" style="display: none"></p>
          
     <form name="logonForm" action="j_security_check.action" method="post" focus="j_username" onsubmit="return handleSubmit(event)">
    
				<div class="logininput">
				<label for="j_username">
				  用户名：
				</label>
				<br/>
				<input type="text" name="j_username" id="j_username" tabindex="1" onfocus="handleFocus()" onkeypress="return handleKeypress(event)"/>
				</div>
				
				<div class="logininput">
				  <label for="j_password">
				    密码：
				  </label>
				  <br/>
				  <input type="password" name="j_password" id="j_password" tabindex="1"/>
				</div>
    
		    <p>注意：停止活动一段时间后，该系统会自动将您注销，并要求您重新登录。</p>
		    <div>
		      <input type="submit" name="action" value="登录" class="standardbutton" id="login" tabindex="1"/>
		    </div>
  	</form>
  <p>Licensed Materials - Property of IBM Corp.<br/> &copy; Copyright IBM Corporation and other(s) 1997, 2011. IBM 是 IBM Corporation 在美国和/或其他国家或地区的注册商标。</p>
</div>

</div>
</div>
</div>

</div>

<noscript>
  必须启用 JavaScript，才能正确使用该 Web 站点。
</noscript>

<script type="text/javascript">
document.logonForm.j_username.focus()
</script>
</body>
</html>
