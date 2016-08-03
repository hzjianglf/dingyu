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

<title><s:property value="card.cardName"/></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/card.css">
</head>
<body>
	<input type="hidden" name="bg_msg" id="bg_msg" value="<%=filePath%>/<s:property value="cardPojo.cardBgImage"/>"/>
	<input type="hidden" name="txt_msg" id="txt_msg" value="<%=filePath%>/<s:property value="cardPojo.cardTxtImage"/>"/>
	<input type="hidden" name="interface_msg" id="i_type" value="<s:property value="cardPojo.cardTypeId"/>"/>
	<input type="hidden" name="enterName" id="enterName" value="<s:property value="enterName"/>"/>
	<input type="hidden" name="title_image" id="title_image" value="<%=filePath%>/<s:property value="card.cardTitleImage"/>">
	<input type="hidden" name="errorUrl" id="errorUrl" value="<s:property value="cardProterty.guideUrl"/>">
	<div class="wap cardWrap">
		<div class="hot">
			<p></p>
		</div>
		<div class="mainBox">
			<div class="wish-box">
				<div class="user">
					<div class="wish-txt">
						<textarea class="content" id="s_content"><s:property value="cardPojo.cardValue" /></textarea>
					</div>
					<div class="wish-sign">
						<div class="li-h">
							<input type="text" value="<s:property value="fromUser"/>" class="f-name" id="f_name"
								placeholder="" id="fname" name="fname"/>
						</div>
						<div class="li-h">
							<span class="time"><s:property value="time"/></span>
						</div>
					</div>
				</div>
			</div>

			<!-- btn发送贺卡 -->
			<div class="sendBtn-box">
				<a class="sendBtn" id="send_show_image">发送贺卡</a>
				<a class="guanzhuBtn" id="btn_guanzhu_card">关注<s:property value="enterName"/>账号</a>
			</div>
			<!-- /btn发送贺卡 -->

		</div>
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
