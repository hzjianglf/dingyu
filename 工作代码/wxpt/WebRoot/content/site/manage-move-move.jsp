<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="testCookie.jsp" flush="true" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../manager/css/default.css">
<link rel="stylesheet" type="text/css" href="../manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"
	href="../manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="../manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="../manager/js/hd.js"></script>
<script type="text/javascript" src="../manager/js/jquery.form.js"></script>
<script type="text/javascript" src="../manager/js/move.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>

<script type="text/javascript">
	document.oncontextmenu = function() {
		var selection = "";//定义鼠标拖选值
		if (document.selection) {//***** IE
			selection = document.selection.createRange().text;
		} else {//***** 其他浏览器
			selection = document.getSelection();
		}
		//if来判断拖选值、被单击的区域是不是表单域值
		if (selection == ""
				&& (event.srcElement.value == undefined || event.srcElement.value == 0)
				&& event.srcElement.value != 0)
			return false;
	}

	function onRowContextMenu(e, rowIndex, rowData) {
		/*e.preventDafault();*/
		e.preventDefault();
	}
</script>
<style type="text/css">
#serchTest,#edit,#add,#ff,#addUser,#adduser,#updateuser,#toolbar {
	visibility: hidden;
}

.serchTest {
	_margin-top: -20px;
	_margin-bottom: -18px;
}

*+html #serchTest {
	*margin-top: -20px;
	*margin-bottom: -18px;
}

.test a {
	vertical-align: center;
	display: block;
	float: left;
	height: 20px;
	padding-bottom: 2px;
	border: 1px solid #EFEFEF;
}

.test a:link {
	text-decoration: none;
	color: #000;
}

.test a:active {
	text-decoration: none;
	color: #000
}

.test a:visited {
	text-decoration: none;
	color: #000
}

.test a:hover {
	text-decoration: none;
	color: #000;
	background: #eaf2ff;
	border: 1px solid #dddddd;
}

.xx21 {
	margin: 10px 10px 10px 10px;
	border: 1px solid #91ABD1;
	border-radius: 8px;
}

.ww {
	border: 1px solid #26A0DA
}
</style>

</head>
<body style="background-color:white;padding:0px;">

	<table style="float:left;" id="tt">
	</table>

	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td><form id="query">
						<table align="left">
							<tr>
								<td><span style="color:#3967a3 ">活动名称:</span><input
									type="text" name="queryName" id="queryName" class="ww">
								</td>


								<td align="left"><a href="javascript:query()"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询</span>&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table>
					</form></td>
				<td><table align="right">
						<tr>
							<td><a href="javascript:AddUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:UpdateUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>

							<td><a href="javascript:DeleteUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:SetPrizeChance()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设定活动中奖人数</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>

	<form id="addUser" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<div
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;*margin-top:10px; *margin-bottom: 10px; *margin-left: 15px;">
				<table>

					<tr style="line-height: 20px;">
						<td><span>活动名称：</span></td>
						<td>
						<input type="text" class="ww" name="moveNames"
							style="width:128px;[;width:134px;];*width: 130px;width:135px\9\0;display: none;"
							id="moveNames" />
							 <div class="ww" id="selects" style="display: none;">
								<select id="moveName" name="moveName"
									style="width:130px;[;width:135px;];*width: 133px;width:138px\9\0;"
									onchange="change()">
								</select>
							</div> </td>
						<input type="hidden" name="moveId" id="moveId" />
					</tr>
					<tr style="line-height: 20px;">
						<td><span>活动内容：</span></td>
						<td><input type="text" class="ww" name="moveContent"
							style="width:128px;[;width:134px;];*width: 130px;width:135px\9\0"
							id="moveContent" />
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>开始时间：</span></td>
						<td><input name="moveStartTime" id="startTime" type="text"
							id="date" onclick="SelectDate(this,'yyyy/MM/dd');"
							value="点击此处选择活动开始时间"
							style="border:1px solid #26A0DA; width:127px;[;width:133px;];*width: 130px;width:135px\9\0">
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>结束时间：</span></td>
						<td><input name="moveEndTime" id="endTime" type="text"
							id="date" onclick="SelectDate(this,'yyyy/MM/dd');"
							value="点击此处选择活动结束时间"
							style="border:1px solid #26A0DA;width:127px;[;width:133px;];*width: 130px;width:135px\9\0">
						</td>
					</tr>
					<tr id="awardTr" style="line-height: 20px; display: none;">
						<td><span>抽奖时间：</span> <!-- id="awarsSpan" style="display: none;" -->
						</td>
						<td><input name="awardTime" id="awardTime" type="text"
							id="date" value="点击此处选择活动抽奖时间"
							style="border:1px solid #26A0DA;width:127px;[;width:133px;];*width: 130px;width:135px\9\0">
						</td>
					</tr>
					<tr id="hao" style="display: none;">
						<td colspan="2"><span style="color: red;">*每月的几号抽奖;如：28,29
								*</span>
						</td>
					</tr>
					<tr id="hyEdnTime" style="line-height: 20px; display: none;">
						<td><span>会员卡有效时间：</span> <!-- id="awarsSpan" style="display: none;" -->
						</td>
						<td><input name="endTimes" id="endTimes" type="text"
							id="endTimes" value="会员卡有效时间"
							style="border:1px solid #26A0DA;width:127px;[;width:133px;];*width: 130px;width:135px\9\0">
						</td>
					</tr>
					<tr id="end" style="display: none;">
						<td colspan="2"><span style="color: red;">*设置会员卡的有效时间
								“年”为单位！ *</span>
						</td>
					</tr>

				</table>
			</div>
		</div>
		<input type="hidden" id="userId" name="userId">
	</form>
	<!--height:245px;[;height:245px;];*height:255px;height:240px\9\0;  -->
	<div id="updateuser" class="easyui-window" title="修改"
		style="overflow:hidden;background:#E2EDFF;width:270px;height:auto;"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="update"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="update()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="close1()">取消</a>
		</div>
	</div>
	<div id="adduser" class="easyui-window" title="添加活动"
		style="overflow:hidden;background:#E2EDFF;width:270px; height: auto;[;height:auto;];*height:auto;height:auto\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<!-- height:245px;[;height:225px;];*height:255px;height:240px\9\0; -->
		<div id="eeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="add()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="close1()">取消</a>
		</div>
	</div>
	<div id="lan" class="easyui-window" title="查看关键字"
		style="padding:0;width:550px;height:350px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="la">
		</table>
		<div id="toolbar1" class="test">

			<table width="100%">
				<tr>
					<td><span id="userNamename"></span></td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- --中奖概率设置开始 -->
	<div id="editPrize" class="easyui-window" title="活动人数设置 "
		style=" background:#E2EDFF;width:auto;height:auto;"
		iconCls="icon-add" 
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="eePrize">
		<form id="ffPrize" method="post">
		<div class="xx21">
			<table
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;">
				<tr style="line-height: 20px;">
				<td width="300">
						<span>	活动名<input type="text" id="move_name"  readonly="readonly" 	name="move_name"/></span>
						
					</td>
					<td width="300"  id="lianxu1">
						<span> 
						连续签到<input type="text" id="zhuanpan1" 	name="zhuanpan1" style=" width:122px;"/>天转盘	</span>
						
					</td>
					<td width="300"   id="lianxu2">
						<span> 连续签到<input type="text" id="zhuanpan2"    	 name="zhuanpan2" style=" width:122px;">天转盘	</span>
						
					</td>
					<td width="300"  id="lianxu3">
						<span>  连续签到<input type="text" id="zhuanpan3"   	name="zhuanpan3" style=" width:122px;"/>天转盘	</span>
						
					</td>
				</tr>
				<tr style="line-height: 20px;" >
					<td>
						<span>	一等奖<input type="text" id="onePrizeCount"	name="onePrizeCount" value="-1"  onclick="note_click(this)"/>人</span>
					</td>
					<td style="display: none;" id="id1">
						<span>	中奖概率<input  type="text" id="oneProbability"	name="oneProbability"  value="0"  onclick="note_click2(this)"/></span>
					</td>
					<td style="display: none;" id="id12">
						<span>	中奖概率<input  type="text" id="oneProbability2"	name="oneProbability2"  value="0"  onclick="note_click2(this)"/></span>
					</td>
					<td style="display: none;" id="id13">
						<span>	中奖概率<input  type="text" id="oneProbability3"	name="oneProbability3"  value="0"  onclick="note_click2(this)"/></span>
					</td>
				</tr>
				<tr style="line-height: 20px;" >
					<td>
						<span>	二等奖<input type="text" id="twoPrizeCount"	name="twoPrizeCount" value="-1"   onclick="note_click(this)"/>人</span>
					</td>
					<td style="display: none;" id="id2">
						<span>	中奖概率<input type="text" id="twoProbability"	name="twoProbability"  value="0"  onclick="note_click2(this)"/></span>
					</td>
					<td style="display: none;" id="id22">
						<span>	中奖概率<input type="text" id="twoProbability2"	name="twoProbability2"  value="0"  onclick="note_click2(this)"/></span>
					</td>
					<td style="display: none;" id="id23">
						<span>	中奖概率<input type="text" id="twoProbability3"	name="twoProbability3"  value="0"  onclick="note_click2(this)"/></span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span>	三等奖<input type="text" id="threePrizeCount"	name="threePrizeCount" value="-1"   onclick="note_click(this)"/>人</span>
					</td>
					<td style="display: none;" id="id3">
						<span>	中奖概率<input type="text" id="threeProbability"	name="threeProbability"  value="0"  onclick="note_click2(this)"/></span>
					</td>
					<td style="display: none;" id="id32">
						<span>	中奖概率<input type="text" id="threeProbability2"	name="threeProbability2"  value="0"  onclick="note_click2(this)"/></span>
					</td>
					<td style="display: none;" id="id33">
						<span>	中奖概率<input type="text" id="threeProbability3"	name="threeProbability3"  value="0"  onclick="note_click2(this)"/></span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span>	四等奖<input type="text" id="fourPrizeCount"	name="fourPrizeCount" value="-1"   onclick="note_click(this)"/>人</span>
					</td>
                    
					<td style="display: none;" id="id4">
						<span>	中奖概率<input type="text" id="fourProbability"	name="fourProbability"  value="0"  onclick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id42">
						<span>	中奖概率<input type="text" id="fourProbability2"	name="fourProbability2"  value="0"  onclick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id43">
						<span>	中奖概率<input type="text" id="fourProbability3"	name="fourProbability3"  value="0"  onclick="note_click2(this)"/></span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span>	五等奖<input type="text" id="fivePrizeCount"	name="fivePrizeCount" value="-1"  onclick="note_click(this)"/>人</span>
					</td>
					<td style="display: none;" id="id5">
						<span>	中奖概率<input type="text" id="fiveProbability"	name="fiveProbability"  value="0"  onclick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id52">
						<span>	中奖概率<input type="text" id="fiveProbability2"	name="fiveProbability2"  value="0"  onclick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id53">
						<span>	中奖概率<input type="text" id="fiveProbability3"	name="fiveProbability3"  value="0"  onclick="note_click2(this)"/></span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td >
						<span>	六等奖<input type="text" id="sixPrizeCount"	name="sixPrizeCount" value="-1"   onclick="note_click(this)"/>人</span>
					</td>
					<td style="display: none;" id="id6">
						<span>	中奖概率<input type="text" id="sixProbability"	name="sixProbability"  value="0"  onclick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id62">
						<span>	中奖概率<input type="text" id="sixProbability2"	name="sixProbability2"  value="0"  onclick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id63">
						<span>	中奖概率<input type="text" id="sixProbability3"	name="sixProbability3"  value="0"  onclick="note_click2(this)"/></span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td style="display: none;" id="prize7">
						<span>	七等奖<input type="text" id="sevenPrizeCount"	name="sevenPrizeCount" value="-1"   onclick="note_click(this)"/>人</span>
					</td>
					<td style="display: none;" id="id7">
						<span>	中奖概率<input type="text" id="sevenProbability"	name="sevenProbability"  value="0"  onclick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id72">
						<span>	中奖概率<input type="text" id="sevenProbability2"	name="sevenProbability2"  value="0"  onclick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id73">
						<span>	中奖概率<input type="text" id="sevenProbability3"	name="sevenProbability3"  value="0"  onclick="note_click2(this)"/></span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td style="display: none;" id="prize8">
						<span>	八等奖<input type="text" id="eightPrizeCount"	name="eightPrizeCount" value="-1"   onclick="note_click(this)"/>人</span>
					</td>
					<td style="display: none;" id="id8">
						<span>	中奖概率<input type="text" id="eightProbability"	name="eightProbability"  value="0" onClick="note_click2(this)" /></span>
					</td>
                    <td style="display: none;" id="id82">
						<span>	中奖概率<input type="text" id="eightProbability2"	name="eightProbability2"  value="0" onClick="note_click2(this)" /></span>
					</td>
                    <td style="display: none;" id="id83">
						<span>	中奖概率<input type="text" id="eightProbability3"	name="eightProbability3"  value="0" onClick="note_click2(this)" /></span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					 
					<td style="display: none;" id="prize9">
						<span>	九等奖<input type="text" id="ninePrizeCount"	name="ninePrizeCount" value="-1"   onclick="note_click(this)"/>人</span>
					</td>
					<td style="display: none;" id="id9">
						<span>	中奖概率<input type="text" id="nineProbability"	name="nineProbability"  value="0" onClick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id92">
						<span>	中奖概率<input type="text" id="nineProbability2"	name="nineProbability2"  value="0" onClick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id93">
						<span>	中奖概率<input type="text" id="nineProbability3"	name="nineProbability3"  value="0" onClick="note_click2(this)"/></span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td style="display: none;" id="prize10">
						<span>	十等奖<input type="text" id="tenPrizeCount"	name="tenPrizeCount" value="-1"   onclick="note_click(this)"/>人</span>
					</td>
					<td style="display: none;" id="id10">
						<span>	中奖概率<input type="text" id="tenProbability"	name="tenProbability"  value="0" onClick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id102">
						<span>	中奖概率<input type="text" id="tenProbability2"	name="tenProbability2"  value="0" onClick="note_click2(this)"/></span>
					</td>
                    <td style="display: none;" id="id103">
						<span>	中奖概率<input type="text" id="tenProbability3"	name="tenProbability3"  value="0" onClick="note_click2(this)"/></span>
					</td>
				</tr>
				<!-- <tr style="line-height: 20px;">
					<td>
						<span>	总计中奖人数为<input type="text" id="prizeCountNum"	name="prizeCountNum" disabled="disabled" readonly="readonly"/></span>
					</td>
				</tr> -->
				<tr style="line-height: 20px; " >
					<td colspan="4" align="center">
						<span  ><font style="color:red;" >*注意：中奖人数值为-1，意为不限制人数，<br>中奖概率默认为0，假设中奖概率为1%，<br>则输入0.01即可</font></span>
					</td>
				</tr>
			</table>
		</div>
		
	</form>
		</div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;"
				onClick="setChance()">确定</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="closePrizeChance()">取消</a>
		</div>
	</div>
	<!-- 中奖概率设置结束 -->
	
	
	
	
</body>
</html>