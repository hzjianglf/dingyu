<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<script type="text/javascript" src="../manager/js/huiyuan.js"></script>
<script type="text/javascript" src="../manager/js/ajaxfileupload.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>

<style type="text/css">
#serchTest,#edit,#add,#ff,#addCheckForm,#updatequestion,#addcheckdiv,#toolbar,#huiyuanxingqing
	{
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

.kehus { /* background:#FFFFFF; */
	background: #E2EDFF;
	border: none;
}

.kehustable {
	width: 700px;
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
	margin: 10px auto;
}

* html .kehustable {
	width: 700px;
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
	margin: 10px auto;
}

*+html .kehustable {
	width: 700px;
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
	margin: 10px auto;
}

.kehustable td {
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
}

.liuyanban {
	width: 700px;
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
	margin: 10px auto;
}

.liuyanban td {
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
}

.beijing {
	background: #E5F0F4;
}

.ww {
	border: 1px solid #26A0DA
}
</style>

</head>

<body style="background-color:white;padding:0px;" onload="shuanggji()">
	<!--    sssssssssssss -->
	<script type="text/javascript">
		function shuanggji() {

			$('#huiyuan').datagrid({
				onDblClickRow : getSelects2
			});
			displayMsg();
		}

		//双击触发的事件
		function getSelects2() {
			//alert("jspjsp");

			$('#huiyuanxingqing').window('open');
			$('#huiyuanxingqing').css("visibility", "visible");
			var select = $('#huiyuan').datagrid('getSelected');

			if (select) {
				var memberId = select.memberId;
				$('#huiyuanid').val(memberId);
				$('#huiyuankahao').val(select.cardId);
				$('#weixinhao').val(select.weixinId);
				$('#mima').val(select.password);
				$('#yonghuming').val(select.username);
				$('#xingbie').val(select.sex);
				$('#nianling').val(select.age);
				$('#tianjiashijian').val(select.addTime);
				$('#jieshushijian').val(select.endTime);
				$('#dizhi').val(select.address);
				$('#dianhua').val(select.phone);
				$('#cunkuan').val(select.saveMoney);
				$('#huiyuanjibie').val(select.memberGrade);
				$('#zhuangtai').val(select.memberFreeze);

				$.ajax({
					secureuri : false,
					type : "POST",
					url : "../site/member!quliuyan",
					dataType : "text",
					data : {
						'memberId' : memberId
					},

					secureuri : false,//一般设置为false

					success : function(text) {
						// alert("111");
						//alert("333");
						$("#liuyanban").html(text);
						// alert("222");
					},
					error : function(text) {

					}
				});

			}

		}
	</script>
	<table id="huiyuan">
	</table>

	<form style="display: none;" id="ejob_form" name="imageForm"
		method="post" target="hidden_frame" enctype="multipart/form-data">

		<div align="center">
			上传企业图片:<input id="upload" type="file" name="upload"
				style="width:200px; height:20px; margin-left:20px;" /></br> <span
				style="position:relative;left:30000px;">*</span><span
				style="font-size: 10px;">上传图片的长度必须是138px,高度104px;</span>
		</div>

	</form>


	<div id="enterprisepicture" class="easyui-window" title="企业图片"
		style="background:#E0ECFF;padding: 8px;width:400px;height:auto;top: 50px;display: none; overflow:hidden"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div
			style="border:1px solid #95B8E7;border-radius:8px;margin:0 auto;height:auto;line-height:30px;">
			<div id="ejobpicture"></div>

		</div>
		<div>&nbsp;</div>
		<div style="position:relative;left:100px;display: none;" id="uploadok">
			<a class="easyui-linkbutton" style="left: 40px;" iconCls="icon-ok"
				href="javascript:uploadFile();">上传</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:closeFileUpload();"
				onClick="close2()">取消</a>
		</div>


	</div>

	<div id="toolbar" class="test">
		<table>
			<tr>
				<td>
					<table width="800" border="0">
						<tr>
							<td>用户名：<input type="text" id="userName" class="ww" /></td>
							<td>会员卡号：<input id="weixin_id" type="text" class="ww" /></td>
							<td>添加时间：<input type="text" id="add_time"
								onclick="SelectDate(this,'yyyy-MM-dd');" class="ww" /></td>
							<td>
							<td><a href="javascript:searchJob();"><span
									class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询</span>&nbsp;&nbsp;
							</a>
							</td>
							<td>
						</tr>
					</table></td>
				<td width="100px;"></td>
				<td>
					<table width="350px;">
						<tr>
							<td><a href="javascript:updateJiFen()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改积分</span>&nbsp;&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:uploadUi()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传</span>&nbsp;&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:dongjie()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;冻结</span>&nbsp;&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:jiedong()"><div
										style="padding-top: 5px;"></div>&nbsp;&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;解冻</span>&nbsp;&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>

		<!--双击显示div  -->
		<div id="huiyuanxingqing" class="easyui-window" scroll="no"
			title="会员详情及留言回复"
			style="background:#E2EDFF;width:800px;height:500px; top:20px;visibility:hidden;"
			iconCls="icon-edit" data-options="modal:true,closed:true"
			closed="true" maximizable="false" minimizable="false"
			collapsible="false">
			<!-- [;height:490px;];*height:490px;*top:40;height:470px\9\0;visibility:hidden;
	<div id="huiyuanxingqing" scroll="no" class="easyui-window" title="会员详情及留言回复" style="background:#E2EDFF;width:800px;height:500px;top:20px;"
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false"> -->
			<div
				style="border:1px solid #7CB7DD;border-radius:8px;width:760px;margin:10px auto;*position:relative;*left:10px; ">
				<table class="kehustable" cellpadding="0" cellspacing="0"
					style="*position:relative;*left:4%;">
					<input type="hidden" id="huiyuanid" name="huyuanid" />
					<tr>
						<td class="beijing">会员卡号：</td>
						<td><input type="text" id="huiyuankahao" name="huiyuankahao"
							readonly="true" class="kehus" /></td>

						<td class="beijing">微信号：</td>
						<td><input type="text" id="weixinhao" name="weixinhao"
							readonly="true" class="kehus" /></td>
					</tr>

					<tr>
						<td class="beijing">密码：</td>
						<td><input type="text" id="mima" name="mima" readonly="true"
							class="kehus" /></td>

						<td class="beijing">用户名：</td>
						<td><input type="text" id="yonghuming" name="yonghuming"
							readonly="true" class="kehus" /></td>
					</tr>

					<tr>
						<td class="beijing">性别：</td>
						<td><input type="text" id="xingbie" name="xingbie"
							readonly="true" class="kehus" /></td>

						<td class="beijing">年龄：</td>
						<td><input type="text" id="nianling" name="nianling"
							readonly="true" class="kehus" /></td>
					</tr>

					<tr>
						<td class="beijing">添加时间：</td>
						<td><input type="text" id="tianjiashijian"
							name="tianjiashijian" readonly="true" class="kehus" /></td>

						<td class="beijing">结束时间：</td>
						<td><input type="text" id="jieshushijian"
							name="jieshushijian" readonly="true" class="kehus" /></td>
					</tr>


					<tr>
						<td class="beijing">地址：</td>
						<td><input type="text" id="dizhi" name="dizhi"
							readonly="true" class="kehus" /></td>

						<td class="beijing">电话：</td>
						<td><input type="text" id="dianhua" name="dianhua"
							readonly="true" class="kehus" /></td>
					</tr>
					<tr>
						<td class="beijing">存款：</td>
						<td><input type="text" id="cunkuan" name="cunkuan"
							readonly="true" class="kehus" /></td>

						<td class="beijing">会员级别：</td>
						<td><input type="text" id="huiyuanjibie" name="huiyuanjibie"
							readonly="true" class="kehus" /></td>
					</tr>
					<tr>
						<td class="beijing">状态：</td>
						<td colspan="3"><input type="text" id="zhuangtai"
							name="zhuangtai" readonly="true" class="kehus" /></td>
					</tr>
				</table>
			</div>


			<!--留言表  -->
			<div
				style="border:1px solid #7CB7DD;border-radius:8px;width:760px;height:240px;margin:10px auto;overflow-y:scroll;*position:relative;*left:10px;  ">


				<div id="liuyanban"></div>
				<!--  <table border="1"><tr><td>dddd</td></tr></table> -->
				<!--留言表  -->
			</div>
			<!--双击显示div  end  -->
			<!-- 修改积分 -->
			<form id="ff1" target="hidden_frame" enctype="multipart/form-data"
				method="post" style=" visibility: hidden;">
				<div class="xx21">
					<div
						style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;*margin-top:10px; *margin-bottom: 10px; *margin-left: 15px;">
						<table>
							<tr style="line-height: 20px;">
								<td><span>目前积分
										：</span>
								</td>
								<td><input class="ww" style="width: 120px;" readonly="readonly" type="text" id="jifen0" name="jifen0"></td>
							</tr>

							<tr style="line-height: 20px;">
								<td>修改后积分：</td>
								<td><input type="text" class="ww" value="" id="jifen1"
									name="jifen2" style="width: 120px;"></td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>



		<div id="update" class="easyui-window" title="修改积分"
			style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:270px;height:auto;[;height:auto;];*height:auto; visibility: hidden;"
			iconCls="icon-add" data-options="modal:true,closed:true"
			maximizable="false" closed="true" maximizable="false"
			minimizable="false" collapsible="false">
			<div id="update1"></div>
			<div>&nbsp;</div>
			<div align="center">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:void(0)"
					style="position:relative;top:-15px;*top:-23px;"
					onClick="updatejiFen()">修改</a> <a class="easyui-linkbutton"
					iconCls="icon-cancel" href="javascript:void(0)"
					style="position:relative;top:-15px;*top:-23px;" onClick="closes()">取消</a>
			</div>
		</div>
</body>
</html>
