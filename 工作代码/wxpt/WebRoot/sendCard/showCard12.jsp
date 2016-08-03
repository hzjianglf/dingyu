<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/sendCard/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/" + request.getAttribute("enterID")
			+ "/sendCard/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
<script src="js/jquery-1.8.3.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title><s:property value="card.cardName"/></title>
</head>
<body>
<input type="hidden" name="bg_msg" id="bg_msg" value="<%=filePath%>/<s:property value="card.cardBgImage"/>"/>
	<input type="hidden" name="txt_msg" id="txt_msg" value="<%=filePath%>/<s:property value="card.cardTxtImage"/>"/>
	<input type="hidden" name="interface_msg" id="i_type" value="<s:property value="card.cardId"/>"/>
	<input type="hidden" name="enterName" id="enterName" value="<s:property value="enterName"/>"/>
	<input type="hidden" name="title_image" id="title_image" value="<%=filePath%>/<s:property value="card.cardTitleImage"/>">
	<input type="hidden" name="errorUrl" id="errorUrl" value="<s:property value="cardProterty.guideUrl"/>">
<div style="background:url(<%=filePath%>/<s:property value="card.cardBgImage"/>) no-repeat; background-size:100% 100%; margin:0; padding:0;">
	<div class="sd12">
		<textarea class="test test12" id="s_content"><s:property value="cardPojo.cardValue" /></textarea>
	    <input type="text" value="<s:property value="fromUser"/>" class="inp inp12" id="f_name" disabled="disabled"/>
		<input type="text" value="<s:property value="time"/>" class="inp inp12" disabled="disabled" style="float:right; margin-right:25%; margin-top:10%;"/>
	</div>
	<div class="card1"></div>
</div>
<div style="margin-top:5%;" >
	<img src="images/fs3.png" style="width:100%;" id="send_show_image">
	<a class="guanzhuBtn" id="btn_guanzhu_card">关注【<s:property value="enterName"/>】账号</a>
</div>
<div class="box-guide hide">
		<div class="box-img">
			<img src="/wxpt/sendCard/images/guide-1.png" data-pinit="registered">
		</div>
	</div>
	<div id="prompt" class="prompt fn-hide">
		<dl>
			<dt>
				<div class="tle">确认提示</div>
			</dt>
			<dd>
				<p></p>
			</dd>
			<dd id="prompt-close">
				<a class="btn btn-green btn-prompt">确定</a>
			</dd>
		</dl>
	</div>
	<div id="share-shadow" class="shade"></div>
	<script type="text/javascript" src="js/zepto-min.js"></script>
	<script type="text/javascript" src="js/config.js"></script>
	<script type="text/javascript" src="js/comment.js"></script>
	<script type="text/javascript" src="js/webchat.js"></script>
</body>
</html>

