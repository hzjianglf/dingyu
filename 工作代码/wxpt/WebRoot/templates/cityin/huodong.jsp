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
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
<script src="js/html5.js"></script>
<script src="js/html5media.min.js"></script>
<script src="js/wgw.gs.lib.min.js"></script>
<script src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	function show(value,fansImageId){
		document.getElementById("overDiv").style.display = "block" ;
		document.getElementById("dlDiv").style.display = "block" ;
		document.getElementById("Img").src=value;
		var fromUser = window.location.href.split("=")[1];
		$.ajax({
			url : "/cityin/web/index!zanEdit",
			data : {
				"fansImageId" : fansImageId,
				"fromUser" : fromUser
			},
			dataType : "json",
			success : function(date){
				var str = eval(date);
				if(str.state == 1){
					$("#zanStr").html("<a href=\"javascript:huZan('"+fansImageId+"','"+fromUser+"')\"><img src='images/zan.png'/><span style='color:#FFFFFF; font-size:12px;'>已赞 </span></a>");
					$("#zan").html("已赞");
				}
				if(str.state == 0){
					$("#zanStr").html("<a href=\"javascript:huZan('"+fansImageId+"','"+fromUser+"')\"><img src='images/zan.png'/><span style='color:#FFFFFF; font-size:12px;'>赞 </span></a>");
				}
			},	
			error : function (){
				alert("网络异常");
			}
		});
		
	}
	function huZan(fansImageId,fromUser){
		$.ajax({
			url : "/cityin/web/index!huZan",
			data : {
				"fansImageId" : fansImageId,
				"fromUser" : fromUser
			},
			dataType : "json",
			success : function(date){
				var str = eval(date);
				if(str.state == 1){
					$("#zanStr").html("<a href=\"javascript:huZan('"+fansImageId+"','"+fromUser+"')\"><img src='images/zan.png'  /><span style='color:#FFFFFF; font-size:12px;'>已赞 </span></a>");
					$("#zan").html("已赞");
				}
				if(str.state == 0){
					$("#zanStr").html("<a href=\"javascript:huZan('"+fansImageId+"','"+fromUser+"')\"><img src='images/zan.png'  /><span style='color:#FFFFFF; font-size:12px;'>赞 </span></a>");
				}
			},	
			error : function (){
				alert("网络异常");
			}
		});
	}
	function closeDiv(){
		document.getElementById("overDiv").style.display = "none" ;
		document.getElementById("dlDiv").style.display = "none" ;
	}
</script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/m_style.css" rel="stylesheet" type="text/css" />
<title>城色</title>
</head>

<body>
<div class="top1">
	<ul>
		<li>微摄影大赛</li>
	</ul>
</div>
<div class="line"> </div>
<s:iterator value="fansUserList" var="fansUser" status="status">
<section  class="menu1">
	<div style="width:5%; float:left;"><img src="images/left.gif"  class="menu1_img"></div>
	<div style="width:93%; float:right;">
		<ul>
			<li><strong><s:property value="#fansUser.fansUserName"/></strong></li>
			<li class="menu_font">用心灵和眼睛发现美</li>
			<li>
				<div>
					<div class="navBarL active" id="columnprev" onClick="goPrev();">				
					<span ></span></div>
					<div class="navBody" id="singleScroll">
					<ul class="sc_scroller" id="singleScrollUl" style="width:6000px">
						<s:iterator value="fansImageList" var="fansImage" status="index">
							<s:if test="#fansImage.fansUser.fansUserId==#fansUser.fansUserId">
								<li><span class="active" id="column1"><a onClick="show('<s:property value="#fansImage.fansImageValue"/>','<s:property value="#fansImage.fansImageId"/>')"><img src='<s:property value="#fansImage.fansImageValue"/>'></a></span></li>
							</s:if>
						</s:iterator>
					</ul>
					</div>
					<div class="navBarR active" id="columnnext" onClick="goNext();">
					<span ></span></div>
					<script type="text/javascript">
						function setColumnWidth(){
							<s:iterator value="fansImageList" var="fansImage" status="index">
								getTotalWidth(<s:property value="#status.count"/>);
							</s:iterator>
							$("#singleScrollUl").css("width",ulwidth);		
						}
					</script>
					</div> 
				
			</li>
			<li class="menu_font">
				<div class="menu_list1"><s:property value="#fansUser.updateTime"/></div>
				<div class="menu_list2">
					<a href="javascript:zan('<s:property value="#status.index"/>','<s:property value="#fansUser.fansUserId"/>')" style="color: #666;"><img src="images/zan.png">
						<span id="<s:property value="#status.index"/>zan">
						<s:if test="fansImageVisitList.isEmpty()">
							赞
						</s:if>
						<s:if test="fansImageVisitList.isEmpty() == false">
							<s:iterator value="fansImageVisitList" var="visit">
								<s:if test="#visit.fansUser.fansUserId==#fansUser.fansUserId || #visit.fansImage.fansUser.fansUserId == #fansUser.fansUserId ">
									<s:if test="#visit.fansVisitValue == 1">
										已赞
									</s:if>
									<s:set name="fansImageVisitList" value="null"/>
									<s:if test="#visit.fansVisitValue == 0">
										赞
									</s:if>
										
								</s:if>
								<s:if test="#visit.fansImage.fansUser == null && #visit.fansUser.fansUserId == null">
									赞
								</s:if>
							</s:iterator>
						</s:if>
						</span></a>
				</div>
			</li>
		</ul>
	</div>
</section>
<div class="line"> </div>

</s:iterator>
<script type="text/javascript" src="js/qy_touch.js"></script>
<script type="text/javascript" src="js/m_common.js"></script>
<div id="dlDiv" style="display:none;">
	<img id="Img" width="100%" height="300">	
	<div style="margin-left:10px;">
	<span id ="zanStr" ></span>
	<a id="a1" onClick="closeDiv()">返回</a>
	</div>	
</div>
<div id="overDiv" style="display:none;"></div>
<script type="text/javascript">
	function zan(index,fansUserId){
		var fromUser = window.location.href.split("=")[1];
		$.ajax({
			url : "/cityin/web/index!huZan",
			data : {
				"fansUserId" : fansUserId,
				"fromUser" : fromUser
			},
			dataType : "json",
			success : function(date){
				var str = eval(date);
				if(str.state == 1){
					$("#"+index+"zan").html("已赞");
				}
				if(str.state == 0){
					$("#"+index+"zan").html("赞");
				}
			},	
			error : function (){
				alert("网络异常");
			}
		});
	}
</script>

</body>
</html>
