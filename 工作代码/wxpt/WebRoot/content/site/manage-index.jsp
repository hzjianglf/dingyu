<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePathindex = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>

<html>
<head>
<jsp:include page="testCookie.jsp" flush="true" />
<meta charset="UTF-8">
<title>微家聚后台管理系统</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePathindex%>manager/css/default.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePathindex%>manager/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePathindex%>manager/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePathindex%>manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"
	href="<%=basePathindex%>manager/themes/default/easyui.css"
	type="text/css">
<script type="text/javascript"
	src="<%=basePathindex%>manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePathindex%>manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePathindex%>manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePathindex%>manager/js/hd.js"></script>
<style type="text/css">
.hidden {
	visibility: hidden;
}

#north,#south,#mainPanle {
	visibility: hidden;
}

#mm {
	visibility: hidden;
}

#w {
	visibility: hidden;
}

.xx21 {
	margin: 5px 5px 5px 5px;
	border: 1px solid #91ABD1;
	border-radius: 8px;
}
</style>
<script type="text/javascript">
	$(function(){
	$("#north").css("visibility","visible");
	$("#south").css("visibility","visible");
	$("#mainPanle").css("visibility","visible");
	$(".easyui-accordion .hidden").css("visibility","visible");
	$("#tabs").append('<div title="我的主页" style="width:100%;height:100%;padding:0;margin:0;" id="home">');
	addTab("我的主页","<%=basePathindex%>site/manage-welcome","mainFrame1");
	InitLeftMenu();
	tabClose();
	tabCloseEven();
	

});

function out(){
	$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
           if (r) {
          	
               window.location.href="<%=basePathindex%>site/manage!manage?enterId=${enterId}";
               var date=new Date();
				 date.setTime(date.getTime()-10000);
		     	 document.cookie="wxpts"+"=a;expires="+date.toGMTString()+"; path=/";
           }
	});
}
function close(){
	$('#xiugai').window('close');
}
function edit(){
	  $("#xiugai").css("visibility","visible");
	  $("#ff").css("visibility","visible");
	  $('#xiugai').window('open');
	  $('#ff').show();
	  $('#ff').form('clear');
	  $('#ff').appendTo('#xx');
}
function shiyong(){
var tianqi=$("#tianqi").val();
var kongqi=$("#kongqi").val();
var shouji=$("#shouji").val();
var kuaidi=$("#kuaidi").val();
var huoche=$("#huoche").val();
var daohang=$("#daohang").val();
var zhoubian=$("#zhoubian").val();
var gongjiao=$("#gongjiao").val();
var chengyu=$("#chengyu").val();
var shiyi=$("#shiyi").val();
var shenfen=$("#shenfen").val();
var shengao=$("#shengao").val();
if(tianqi=="1"){
  document.getElementById("tianqi").checked=true;
}
if(kongqi=="1"){
  document.getElementById("kongqi").checked=true;
}
if(shouji=="1"){
  document.getElementById("shouji").checked=true;
}
if(kuaidi=="1"){
  document.getElementById("kuaidi").checked=true;
}
if(huoche=="1"){
  document.getElementById("huoche").checked=true;
}
if(daohang=="1"){
  document.getElementById("daohang").checked=true;
}
if(zhoubian=="1"){
  document.getElementById("zhoubian").checked=true;
}
if(gongjiao=="1"){
  document.getElementById("gongjiao").checked=true;
}
if(chengyu=="1"){
  document.getElementById("chengyu").checked=true;
}
if(shiyi=="1"){
  document.getElementById("shiyi").checked=true;
}
if(shenfen=="1"){
  document.getElementById("shenfen").checked=true;
}
if(shengao=="1"){
  document.getElementById("shengao").checked=true;
}


$("#shiyong").css("visibility","visible");
	

}
function closeshiyong(){
$("#shiyong").css("visibility","hidden");
}
function xuanze(){
	$.ajax({
		type : "POST",
		url : "../site/manage!updateShiyong?enterId=${enterId}",
		data : '' + $("#shi").serialize(),
		fileElementId : 'touxiang',
		dataType : "json",
		success : function() {
			$.messager.alert('消息', '添加成功!');
			close();
			$('#tt').datagrid('reload');
			close();
		},
		error : function() {
			$.messager.alert('消息', '添加失败败!');
			close();
			$('#tt').datagrid('reload');
			close();

		}
	});

}
function ti(name,value){

if(value==0){
document.getElementById(name).value =1;
}else{
document.getElementById(name).value =0;
}

}
function change(id){
	if($("#pwd1").val()==""){
		$.messager.alert('消息',"旧密码不能为空!");
	}else if(($("#pwd2").val() != $("#pwd3").val())||$("#pwd2").val()==""||$("#pwd3").val()==""){
		$.messager.alert('消息',"两次密码输入不一致!");
	}else{
		$.ajax({
			type:"post",
			url:"<%=basePathindex%>site/manage-user!getUp",
			data : {
				'pwd2':$("#pwd2").val(),
				'pwd1':$("#pwd1").val(),
				'userName':id
			},
			success:function(text){ 
				var str = eval(text);		
				if(str.name=="1"){
					$.messager.confirm('消息', '确认修改么?', function(id) {
					if(id){
					
              		 window.location.href="<%=basePathindex%>site/manage!manage?enterId=${enterId}";
              		 var date=new Date();
					 date.setTime(date.getTime()-10000);
		     		 document.cookie="wxpts"+"=a;expires="+date.toGMTString()+"; path=/";
					}
					});
               
					
				}else{
				 	$.messager.alert('消息',str.name); 
					$('#xiugai').window('close');
					
					<%-- var date=new Date();
				 date.setTime(date.getTime()-10000);
		     	 document.cookie="cityin"+"=a;expires="+date.toGMTString()+"; path=/";
               window.location.href="<%=basePathindex %>site/manage!manage"; --%>
					
				}
			},
			error:function (){
			}
		});
	}
}


function chanage(){
var enId=$("#enId").val();
	var enterId = $("#qyId").val();
	var type = $('#type').val();
	$.messager
			.confirm(
					'系统提示',
					'您确定要切换?',
					function(r) {
						if (r) {

							var date = new Date();
							date.setTime(date.getTime() - 10000);
							document.cookie = "wxpts" + "=a;expires="
									+ date.toGMTString() + "; path=/";
							$
									.ajax({
										type : "POST",
										url : "../site/manage!qiehuan",
										data : {
											'enterId' : enterId	,
											'enId':enId									},
										dataType : "json",
										success : function() {
											top.location.href = "http://www.uniqyw.com/wxpt/site/manage?enterId="
													+ enterId
													+ "&type=1&qiehuan=1&enId="+enId;
													/* top.location.href = "http://www.uniqyw.com/wxpt/site/manage?enterId="
													+ enterId
													+ "&type=1&qiehuan=1&enId="+enId; */
											// top.location.replace("http://localhost:8080/wxpt/site/manage?enterId="+enterId+"&type=1");
										},
										error : function() {
											$.messager.alert('消息', '修改失败!');
											close();
											$('#tt').datagrid('reload');
											close1();

										}
									});
						}
					});









}

function shouye(){
window.location.href="<%=basePathindex%>site/manage";

}
function refesh(){

window.location.reload();

}
</script>
<!-- 屏蔽浏览器右键菜单功能- -->
<script type="text/javascript">
document.oncontextmenu=function(){
var selection="";//定义鼠标拖选值
if(document.selection){//***** IE
selection=document.selection.createRange().text;
}else{//***** 其他浏览器
selection=document.getSelection();
}
//if来判断拖选值、被单击的区域是不是表单域值
if(selection==""&&(event.srcElement.value==undefined||event.srcElement.value==0)&&event.srcElement.value!=0)return false;
}



</script>


<style>
#ff,#xiugai {
	visibility: hidden;
}

#admin_txt {
	float: right;
	font-size: 12px;
	text-decoration: none;
	position: 100%;
	line-height: 38px;
	font-family: Arial, Helvetica, sans-serif;
	margin-right: 20px;
}
</style>
</head>
<body style=" padding:0px;overflow-y: hidden;" class="easyui-layout"
	ondragstart="return false;">
	<div id="north" data-options="region:'north'"
		style="overflow: hidden;height: 100px;">
		<div
			style="overflow:hidden; height: 68px; 
        background: url(<%=basePathindex%>manager/images/top-bg.png) #7f99be repeat-x center 0%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
			<img src="<%=basePathindex%>manager/images/logo1.png" width="363"
				height="70" /> <span id="admin_txt" class="head"
				style="*margin-top: -70px"> <s:if test="qy">
					<%--   <a href="javascript:void(0);" onClick="shouye();"> 
				<img src="<%=basePathindex %>manager/images/shoye.png" border="none" />
				</a> --%>
					<a href="http://www.ifinding.cn" target="_blank"> <img
						src="<%=basePathindex%>manager/images/shoye.png" border="none" />
					</a>
				</s:if> <a href="javascript:void(0);" onClick="shiyong();"> <img
					src="<%=basePathindex%>manager/images/shiyong.jpg" border="none" />
			</a> <a href="javascript:void(0);" onClick="edit();"> <img
					src="<%=basePathindex%>manager/images/mima.png" border="none" /> </a>
				<a href="javascript:void(0);" onClick="refesh();"> <img
					src="<%=basePathindex%>manager/images/refesh.png" border="none" />
			</a> <a href="javascript:void(0);" onClick="out();"><img
					src="<%=basePathindex%>manager/images/exit.png" border="none" /> </a>
			</span>
		</div>
		<div>
			<div id="bg"
				style="overflow:hidden;  float:left; height: 34px;repeat-x center 0% background: url(<%=basePathindex%>manager/images/botom_bg.png);">
				&nbsp; <img alt="" src="<%=basePathindex%>manager/images/top_b1.png">
				<font color="#3967a3" style="font-size: 18px; font-family:  宋体;">欢迎您:&nbsp;</font><font
					style="font-size: 20px; font-family:  宋体; color: red;">${userName}</font>

			</div>
			<div
				style="width:200px; height:34px; float:right; padding-top:10px;font-size: 18px; font-family:  宋体;">

				<s:if test="qy">
					<select id="qyId" name="qyId" onChange="chanage()">
						<option>选择企业</option>
						<s:iterator value="enterList" var="en" id="en">
							<option value='<s:property value="#en.enterInforId" />'>
								<s:property value="#en.enterName"></s:property>
							</option>
						</s:iterator>
					</select>
          :切换企业
        </s:if>
				<input type="hidden" value="${enId} " id="enId">

			</div>
		</div>

	</div>
	<div data-options="region:'west',split:true" title="导航菜单"
		style="width:180px;" id="west">
		<div class="easyui-accordion" data-options="fit:true,border:false">

			<div title="我的主页" icon="icon-sys" data-options="selected:true"
				style="overflow:auto;">

				<ul class="hidden">
					<li>
						<div class="dd">
							<a target="mainFrame1"
								href="<%=basePathindex%>site/manage-welcome"> <span
								class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>我的主页</a>
						</div></li>
				</ul>
			</div>

			<s:if test="peizhi">
				<div title="基本信息" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame2"
									href="<%=basePathindex%>site/manage!disposition"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;配置信息</span> </a>
							</div></li>
						<s:if test="qy">
							<li>
								<div class="dd">
									<a target="mainFrame3"
										href="<%=basePathindex%>site/manage!qydisposition?enId=${enId}">
										<span class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业配置管理</span>
									</a>
								</div>
							</li>
						</s:if>
						<s:if test="caidan">
							<li>

								<div class="dd">
									<a target="mainFrame4"
										href="<%=basePathindex%>site/manage!Menu"> <span
										class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;菜单管理</span> </a>
								</div></li>
						</s:if>
						<li>
							<div class="dd">
								<a target="mainFrame5"
									href="<%=basePathindex%>site/manage!keywords"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>关键字设置</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame6"
									href="<%=basePathindex%>site/manage!reply"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>回复设置</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame7"
									href="<%=basePathindex%>site/manage!page"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>图文管理</a>
							</div>
						</li>
						<li>
							<div class="dd">
								<a target="mainFrame8"
									href="<%=basePathindex%>site/manage!tongji"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>统计demo</a>
							</div>
						</li>
					</ul>
				</div>
			</s:if>

			<%-- <s:if test="guanjianzi">
				<div title="关键字管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame1"
									href="<%=basePathindex%>site/manage!keywords"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>关键字设置</a>
							</div>
						</li>
					</ul>
				</div>
			</s:if>
			<s:if test="huifu">
				<div title="回复管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame1"
									href="<%=basePathindex%>site/manage!reply"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>回复设置</a>
							</div>
						</li>
					</ul>
				</div>
			</s:if> --%>
			<s:if test="huodong">
				<div title="活动管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame9" href="<%=basePathindex%>site/manage-move">
									<span class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>活动管理</a>


							</div>
						</li>
					</ul>
				</div>
			</s:if>
			<s:if test="toupiao">
				<div title="投票管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame10"
									href="<%=basePathindex%>site/manage!voteSheZhi"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投票设置</span> </a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame11" href="<%=basePathindex%>site/manage!vote">
									<span class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投票活动</span>
								</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame12"
									href="<%=basePathindex%>site/manage!userLuck"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>投票抽奖</a>
							</div></li>
					</ul>
				</div>
			</s:if>
			<s:if test="dati">
				<div title="答题管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame13"
									href="<%=basePathindex%>site/manage!questionRule"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>答题规则</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame14"
									href="<%=basePathindex%>site/manage!questionTishi"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>提示设置</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame15"
									href="<%=basePathindex%>site/manage!question"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>问题设置</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame16"
									href="<%=basePathindex%>site/manage!questionLuck"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>答题抽奖</a>
							</div></li>
					</ul>
				</div>
			</s:if>
			<s:if test="guaguale">
				<div title="刮刮乐管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">

						<li>
							<div class="dd">
								<a target="mainFrame17"
									href="<%=basePathindex%>site/manage!cardRecords"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>刮刮乐中奖</a>
							</div></li>

						<li>
							<div class="dd">
								<a target="mainFrame18"
									href="<%=basePathindex%>site/manage!cardMan"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>刮刮乐图片</a>
							</div></li>

					</ul>

				</div>
			</s:if>
			<s:if test="qiandao">
				<div title="签到转盘管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">

						<li>
							<div class="dd">
								<a target="mainFrame19"
									href="<%=basePathindex%>site/manage!prizeluck"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>大转盘中奖</a>
							</div>
						</li>
						<li>
							<div class="dd">
								<a target="mainFrame20"
									href="<%=basePathindex%>site/manage!prize"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>六格转盘图片</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame21"
									href="<%=basePathindex%>site/manage!prize1"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>四格转盘图片</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame22"
									href="<%=basePathindex%>site/manage!prize2"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>三格转盘图片</a>
							</div>
						</li>
						<li>
							<div class="dd">
								<a target="mainFrame23"
									href="<%=basePathindex%>site/manage!checkin"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>签到管理</a>
							</div>
						</li>
					</ul>
				</div>
			</s:if>

			<s:if test="quyu">
				<div title="区域客户管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame24"
									href="<%=basePathindex%>site/manage!industry"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>行业设置</a>
							</div></li>


						<li>
							<div class="dd">
								<a target="mainFrame1" href="<%=basePathindex%>site/manage!area">
									<span class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>区域设置</a>
							</div></li>

						<li>
							<div class="dd">
								<a target="mainFrame25"
									href="<%=basePathindex%>site/manage!canton"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>行政区域设置</a>
							</div></li>


						<li>
							<div class="dd">
								<a target="mainFrame26"
									href="<%=basePathindex%>site/manage!radius"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>搜索范围设置</a>
							</div></li>

						<li>
							<div class="dd">
								<a target="mainFrame27"
									href="<%=basePathindex%>site/manage!customers"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>客户信息</a>
							</div></li>
					</ul>
				</div>
			</s:if>
			<s:if test="huiyuan">
				<div title="微会员管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame28"
									href="<%=basePathindex%>site/manage!huiYuanGrade"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>微会员等级设置</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame29"
									href="<%=basePathindex%>site/member!huiyuan"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>微会员管理</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame30"
									href="<%=basePathindex%>site/member!showActivity"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>促销活动管理</a>
							</div></li>
					</ul>
				</div>
			</s:if>
			<%-- 
			<s:if test="caidan">
				<div title="自定义菜单" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame1" href="<%=basePathindex%>site/manage!Menu">
									<span class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;菜单管理</span>
								</a>
							</div>
						</li>
					</ul>
				</div>
			</s:if> --%>
			<s:if test="sheying">
				<div title="摄影管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame31"
									href="<%=basePathindex%>site/manage!sheUser"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>摄影用户管理</a>
							</div>
						</li>
						<li>
							<div class="dd">
								<a target="mainFrame32"
									href="<%=basePathindex%>site/manage!sheying"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>摄影图片授权</a>
							</div>
						</li>
					</ul>
				</div>
			</s:if>
			<s:if test="heka">
				<div title="微贺卡管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame1"
									href="<%=basePathindex%>site/manage!greetingCard"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>微贺卡管理</a>
							</div>
						</li>
					</ul>
				</div>

			</s:if>
			<s:if test="wangzhan">
				<div title="微网站管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame33"
									href="<%=basePathindex%>site/manage!webSite"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>设置</a>
							</div></li>

					</ul>
				</div>

			</s:if>
			<s:if test="vshop">
				<div title="微商城管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">

					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame34"
									href="<%=basePathindex%>site/manage!vshop"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>微商城基本设置</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame35"
									href="<%=basePathindex%>site/product-type!type"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>商品分类信息管理</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame36"
									href="<%=basePathindex%>site/manage!showProductView"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>商品信息管理</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame37"
									href="<%=basePathindex%>site/order-manage"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>商品订单管理</a>
							</div></li>

						<li>
							<div class="dd">
								<a target="mainFrame38"
									href="<%=basePathindex%>site/manage!appraise"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品评价管理</span> </a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame39"
									href="<%=basePathindex%>site/manage!logistic"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;物流管理</span> </a>
							</div></li>


						<li>
							<div class="dd">
								<a target="mainFrame40"
									href="<%=basePathindex%>site/manage!manageYPay"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;银联在线支付配置</span>
								</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame41"
									href="<%=basePathindex%>site/manage!manageZPay"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付宝在线支付配置</span>
								</a>
							</div></li>
					</ul>

				</div>
			</s:if>
			<s:if test="guanli">
				<div title="企业管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame42"
									href="<%=basePathindex%>site/manage!enterInfor?enId=${enId}">
									<span class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>企业管理</a>
							</div></li>

					</ul>
				</div>
			</s:if>
			<s:if test="diaoyan">
				<div title="调研管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame43"
									href="<%=basePathindex%>site/manage!survey"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>用户信息</a>
							</div></li>
						<li>
							<div class="dd">
								<a target="mainFrame44"
									href="<%=basePathindex%>site/manage!surveyquestion"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>题目设置</a>
							</div></li>

						<li>
							<div class="dd">
								<a target="mainFrame45"
									href="<%=basePathindex%>site/manage!suroption"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>选项设置</a>
							</div></li>

					</ul>
				</div>
			</s:if>
			<s:if test="quanxian">
				<div title="权限管理" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame46"
									href="<%=basePathindex%>site/manage!quanxian"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>权限管理</a>
							</div></li>

					</ul>
				</div>
			</s:if>
			<s:if test="tongji">
				<div title="网站访问统计" icon="icon-sys" data-options="selected:true"
					style="overflow:auto;">
					<ul class="hidden">
						<li>
							<div class="dd">
								<a target="mainFrame47"
									href="<%=basePathindex%>baidu/manage-dailystatistic.jsp"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>最近一天内统计</a>
							</div></li>


						<li>
							<div class="dd">
								<a target="mainFrame48"
									href="<%=basePathindex%>baidu/manage-weekstatistic.jsp"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>最近一周内统计</a>
							</div></li>

						<li>
							<div class="dd">
								<a target="mainFrame49"
									href="<%=basePathindex%>baidu/manage-mouthstatistic.jsp"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>最近一月内统计</a>
							</div></li>
					</ul>
				</div>
			</s:if>

		</div>
	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false"
			overflow="hidden"></div>
	</div>

	<input type="hidden" id="basePath" name="basePath"
		value="<%=basePathindex%>" />
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div style="border:0;" id="mm-tabclose">关闭</div>
		<div style="border:0;" id="mm-tabcloseall">全部关闭</div>
		<div style="border:0;" id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div style="border:0;" id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div style="border:0;" id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div style="border:0;" id="mm-exit">退出</div>
	</div>
	<div>
		<form id="ff" style="margin-left: 52px;">

			<table>
				<tr>
					<td>旧&nbsp;密&nbsp;码：</td>
					<td><input type="password" name="pwd1" id="pwd1">
					</td>
				</tr>
				<tr>
					<td>新&nbsp;密&nbsp;码：</td>
					<td><input type="password" name="pwd2" id="pwd2">
					</td>
				</tr>
				<tr>
					<td>确认新密码：</td>
					<td><input type="password" name="pwd3" id="pwd3">
					</td>
				</tr>
			</table>

		</form>
	</div>
	<div id="shiyong"
		style="width:500px;height:450px;background-color:#26A3DF; border-color:black; border:2px; visibility: hidden; position: relative;margin-top: 30px;margin-left: 850px;">
		<!-- background:#e0edff; -->

		<div id="yong" style="border-color: black;">
			<form id="shi">
				<table style="position: relative; margin-top: 20px;  width: 480px;">
					<tr>
						<td width="80px;" style="position: relative; left: 20px; ">
							<table style="position: relative; margin-top: 20px;">
								<tr>
									<td><img src="<%=basePathindex%>manager/images/tianqi.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" name="tianqi"
										id="tianqi" value="${tianqi }"
										onClick="ti(this.name,${tianqi})"></td>
								</tr>

							</table></td>
						<td width="80px;" style="position: relative; left: 20px; ">
							<table
								style="position: relative; margin-top: 20px; margin-left: 30px;">
								<tr>
									<td><img src="<%=basePathindex%>manager/images/kongqi.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="kongqi"
										name="kongqi" value="${kongqi }"
										onClick="ti(this.name,${kongqi})"></td>
								</tr>

							</table></td>
						<td width="80px;" style="position: relative; left: 20px; ">
							<table
								style="position: relative; margin-top: 20px; margin-left: 30px;">
								<tr>
									<td><img src="<%=basePathindex%>manager/images/shouji.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="shouji"
										name="shouji" value="${shouji }"
										onClick="ti(this.name,${shouji})"></td>
								</tr>

							</table></td>
						<td width="80px;" style="position: relative; left: 20px; ">
							<table
								style="position: relative; margin-top: 20px; margin-left: 30px;">
								<tr>
									<td><img src="<%=basePathindex%>manager/images/kuaidi.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="kuaidi"
										name="kuaidi" value="${kuaidi }"
										onClick="ti(this.name,${kuaidi})"></td>
								</tr>

							</table></td>
					</tr>
					<tr>
						<td width="80px;" style="position: relative; left: 20px; ">
							<table style="position: relative; margin-top: 20px; ">
								<tr>
									<td><img src="<%=basePathindex%>manager/images/huoche.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="huoche"
										name="huoche" value="${huoche }"
										onClick="ti(this.name,${huoche})"></td>
								</tr>

							</table></td>
						<td width="80px;" style="position: relative; left: 20px; ">
							<table
								style="position: relative; margin-top: 20px; margin-left: 30px;">
								<tr>
									<td><img
										src="<%=basePathindex%>manager/images/daohang.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="daohang"
										name="daohang" value="${daohang }"
										onClick="ti(this.name,${daohang})"></td>
								</tr>

							</table></td>
						<td width="80px;" style="position: relative; left: 20px;">
							<table
								style="position: relative; margin-top: 20px; margin-left: 30px;">
								<tr>
									<td><img
										src="<%=basePathindex%>manager/images/zhoubian.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="zhoubian"
										name="zhoubian" value="${zhoubian} }"
										onClick="ti(this.name,${zhoubian})"></td>
								</tr>

							</table></td>
						<td width="80px;" style="position: relative; left: 20px;">
							<table
								style="position: relative; margin-top: 20px;margin-left: 30px;">
								<tr>
									<td><img
										src="<%=basePathindex%>manager/images/gongjiao.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="gongjiao"
										name="gongjiao" value="${gongjiao }"
										onClick="ti(this.name,${gongjiao})"></td>
								</tr>

							</table></td>
					</tr>

					<tr>
						<td width="80px;" style="position: relative; left: 20px; ">
							<table style="position: relative; margin-top: 20px;">
								<tr>
									<td><img
										src="<%=basePathindex%>manager/images/chengyu.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="chengyu"
										name="chengyu" value="${chengyu }"
										onClick="ti(this.name,${chengyu})"></td>
								</tr>

							</table></td>
						<td width="80px;" style="position: relative; left: 20px; ">
							<table
								style="position: relative; margin-top: 20px;margin-left: 30px;">
								<tr>
									<td><img src="<%=basePathindex%>manager/images/shiyi.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="shiyi"
										name="shiyi" value="${shiyi }"
										onClick="ti(this.name,${shiyi})"></td>
								</tr>

							</table></td>
						<td width="80px;" style="position: relative; left: 20px;">
							<table
								style="position: relative; margin-top: 20px;margin-left: 30px;">
								<tr>
									<td><img
										src="<%=basePathindex%>manager/images/shenfen.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="shenfen"
										name="shenfen" value="${shenfen }"
										onClick="ti(this.name,${shenfen})"></td>
								</tr>

							</table></td>
						<td width="80px;" style="position: relative; left: 20px;">
							<table
								style="position: relative; margin-top: 20px;margin-left: 30px;">
								<tr>
									<td><img
										src="<%=basePathindex%>manager/images/shengao.png"
										width="69px;" height="69px;"></td>
								</tr>
								<tr>
									<td align="center"><input type="checkbox" id="shengao"
										name="shengao" value="${shengao }"
										onClick="ti(this.name,${shengao})"></td>
								</tr>

							</table></td>
					</tr>

				</table>
			</form>
		</div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				style="position:relative;top:0px;*top:-20px;"
				href="javascript:void(0)" onClick="xuanze()">选择</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				style="margin-left:40px; position:relative;top:0px;*top:-20px;"
				href="javascript:closeshiyong()">取消</a>
		</div>
	</div>
	<div id="xiugai" class="easyui-window" title="修改"
		style="padding: 10px;width:350px;height:180px;[;height:185px;];*height:180px;overflow:hidden;background:#e0edff;top:0px;*left:600px;"
		iconCls="icon-edit" closed="true"
		data-options="modal:true,closed:true" maximizable="false"
		minimizable="false" collapsible="false" draggable="true">
		<div id="xx"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				style="position:relative;top:0px;*top:-20px;"
				href="javascript:void(0)" onClick="change('${userName}')">修改</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				style="margin-left:40px; position:relative;top:0px;*top:-20px;"
				href="javascript:close()">取消</a>
		</div>
	</div>

	<style type="text/css">
#winpop {
	width: 250px;
	height: 500px;
	position: absolute;
	right: 0;
	bottom: 0;
	border: 1px solid #666;
	margin: 0;
	padding: 1px;
	overflow: hidden;
	display: none;
}

#winpop .title {
	width: 100%;
	height: 20px;
	line-height: 20px;
	background: #FFCC00;
	font-weight: bold;
	text-align: center;
	font-size: 12px;
}

#winpop .con {
	width: 100%;
	height: 120px;
	line-height: 40px;
	font-weight: bold;
	font-size: 12px;
	color: #FF0000;
	text-align: center
}

#silu {
	font-size: 12px;
	color: #666;
	position: absolute;
	right: 0;
	text-align: right;
	text-decoration: underline;
	line-height: 22px;
}

.close {
	position: absolute;
	right: 4px;
	top: -1px;
	color: #FFF;
	cursor: pointer
}
</style>
	<input type="hidden" value="${tiyan}" id="tiyan">
	<!-- <s:if test="tiyan"> -->
	<div id="winpop">
		<div class="title">
			体验用户！！<span class="close" onClick="tips_pop()">X</span>
		</div>
		<div class="con">
			尊敬的用户，您的体验截止日期为：<br>${endTime } <br />
		</div>
	</div>
	<!-- </s:if> -->

</body>

</html>
<script type="text/javascript">
function tips_pop(){
  var MsgPop=document.getElementById("winpop");
  var popH=parseInt(MsgPop.style.height);//将对象的高度转化为数字
   if (popH==0){
   MsgPop.style.display="block";//显示隐藏的窗口
  show=setInterval("changeH('up')",2);
   }
  else { 
   hide=setInterval("changeH('down')",2);
  }
}
function changeH(str) {
 var MsgPop=document.getElementById("winpop");
 var popH=parseInt(MsgPop.style.height);
 if(str=="up"){
  if (popH<=100){
  MsgPop.style.height=(popH+4).toString()+"px";
  }
  else{  
  clearInterval(show);
  }
 }
 if(str=="down"){ 
  if (popH>=4){  
  MsgPop.style.height=(popH-4).toString()+"px";
  }
  else{ 
  clearInterval(hide);   
  MsgPop.style.display="none";  //隐藏DIV
  }
 }
}
window.onload=function(){//加载
var tiyan=$("#tiyan").val();
if(tiyan=="true"){
var Win=document.getElementById("winpop");
Win.style.height=0+"px";
setTimeout("tips_pop()",800);//3秒后调用tips_pop()这个函数
}else{
}

}
</script>