<!-- -->
<!-- Licensed Materials - Property of IBM -->
<!-- 5724-N72 5655-W17 -->
<!-- (c) Copyright IBM Corp. 2006, 2009 All Rights Reserved -->
<!-- US Government Users Restricted Rights - Use, duplication or -->
<!-- disclosure restricted by GSA ADP Schedule Contract with -->
<!-- IBM Corp. -->
<!-- -->
<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,com.ibm.hibernate.*, com.opensymphony.xwork2.util.*" %>
<%@taglib prefix="s" uri="/struts-tags" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<!-- Logon.jsp R5 edition --> 
<html  lang="zh-cn" xml:lang="zh-cn" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />

<style type="text/css"> 
/* The browser agent is mozilla/4.0 (compatible; msie 8.0; windows nt 5.1; trident/4.0; .net clr 2.0.50727; .net clr 3.0.04506.648; .net clr 3.5.21022; ciba) */
/* The browser type is ie */
/* The browser version is 8 */
/* The agent locale is zh */
/* The agent OS is nt */
/* The font size multiplier is 1.2 */
 
</style>
 
 <script type="text/javascript" src='scripts/dojo/dojo.js' djConfig="parseOnLoad:true, locale:'zh-cn', gfxRenderer: 'svg,silverlight,vml'"></script>
<script type="text/javascript" src='scripts/menu.js'></script>
<script type="text/javascript" src='scripts/utils.js'></script>
<script type="text/javascript" src='scripts/preferences.js'></script>
<script type="text/javascript" src='scripts/customAction.js'></script>

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
 
<body id="mainbody" class="ie ie8">
 
 
 
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
  <p>
		<img src="theme/standard/images/Error.gif" alt="错误" title="错误"/>
		
		<s:property value="comment"/>
		
  </p>
  <form name="loginerror" action="j_logoff.action" method="post">
    <div>
    	<input type="hidden" name="j_username" id="j_username" tabindex="1">
			<input type="submit" name="action" class="standardbutton" id="other" value='确定' tabindex="1">
    </div>
  </form>
</div>
 
</div>
</div>
</div>
 
</div>
</body>
</html>

