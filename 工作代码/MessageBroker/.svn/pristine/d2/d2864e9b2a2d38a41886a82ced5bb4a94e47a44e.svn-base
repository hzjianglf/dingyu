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
<!-- header.jsp R5 edition -->

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Type" content="image/svg+xml; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />


<style type="text/css">
/* The browser agent is mozilla/5.0 (windows; u; windows nt 5.1; zh-cn; rv:1.9.2.11) gecko/20101012 firefox/3.6.11 */
/* The browser type is firefox */
/* The browser version is 3 */
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

<body id="mainbody" class="firefox firefox3">


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
		<img src="theme/standard/images/Warning.gif" alt="警告" title="警告"/>
		另一用户当前已使用同一用户标识登录。请从以下选项中进行选择：
  </p>
  <form name="sessiontimeout" action="transferpage.action" method="post">
      
    <div>

        <input type="radio" class="noborder autowidth" name="choiceButton" value="logoutOtherUser" checked="checked" id="logoutOtherUser" tabindex="1"/>
        <label for="logoutOtherUserRadioButton">注销另一使用相同用户标识的用户。您可以恢复在另一用户的会话中进行的更改。</label>
        <br/>
        <br/>
        <input type="radio" class="noborder autowidth" name="choiceButton" value="returnToLogon" id="returnRadio" tabindex="1"/>
        <label for="returnRadioButton">返回“登录”页面，并输入其他用户标识。</label>
        <br/>
        <br/>
		<input type="hidden" name="j_username" id="j_username" tabindex="1">
		<input type="submit" name="okAction" class="standardbutton" id="other" value='确定' tabindex="1">
    </div>
  </form>
</div>

</div>
</div>
</div>

</div>
</body>
</html>