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
<script src="../manager/js/TQEditor/TQEditor.full.min.js"
	type="text/javascript"></script>
<script src="../manager/js/TQEditor/TQEditor.min.js" 
	type="text/javascript"></script>
<link href="../manager/js/TQEditor/TQEditor.css" rel="stylesheet"
	type="text/css" />
<link href="../manager/js/TQEditor/QuirksMode.css" rel="stylesheet"
	type="text/css" />
	<script src="../manager/js/TQEditor/TQEditor.full.min.js"
	type="text/javascript"></script>
<script src="../manager/js/TQEditor/TQEditor.min.js"
	type="text/javascript"></script>
<link href="../manager/js/TQEditor/TQEditor.css" rel="stylesheet"
	type="text/css" />
<link href="../manager/js/TQEditor/QuirksMode.css" rel="stylesheet"
	type="text/css" />

<link href="<%=basePath%>manager/js/kindeditor/themes/default/default.css"/>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor/lang/zh_CN.js" type="text/javascript"></script>



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
<script type="text/javascript" src="../manager/js/webSiteMenu.js"></script>


<style type="text/css">
#serchTest,#edit,#add,#ff,#lan,#la,#addMenu,#uploadBanner,#uploadbanner,#ff1,#addmenu,#updatemenu,#toolbar,#template,#bannereToolbar,#advertToolbar,#laLogo,#laChildMenu,#laPage,#pageform,#addPage,#telPageform
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

.ww {
	border: 1px solid #26A0DA
}
</style>

</head>
<body>
	<table style="float:left;" id="tt">
	</table>
	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				
				<td><table align="right">
						<tr>
							<td><a href="javascript:chooseTemplate()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选择模板</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:getUrl()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;访问地址</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:logo()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;logo设置</span>
							</a>
							</td>
							<td><a href="javascript:advert(0,0)"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广告设置</span>
							</a>
							</td>
							<td><a href="javascript:banner(0,0)"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;banner设置</span>
							</a>
							</td>
							<td><a href="javascript:childMenu()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设置子栏目</span>
							</a>
							</td>
							<td><a href="javascript:tuijian()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设置推荐栏目</span>
							</a>
							</td>
							<td><a href="javascript:addMenu()">
									<div style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:updateMenu()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:seeMenu()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	<div id="template" class="easyui-window" title="查看模板"
		style="padding:0;width:600px;height:350px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="temp">
		</table>
		<div id="toolbar2" class="test">

			<table width="100%">
				<tr>

					<td>
						<table align="right">
							<tr>
								<td id="xzDomain"><a href="javascript:getChoose()"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选择</span>&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="lan" class="easyui-window" title="查看banner"
		style="padding:0;width:550px;height:350px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="bannerTt">
		</table>
		<div id="bannerToolbar" class="test">

			<table width="100%">
				<tr>

					<td>
						<table align="right">
							<tr>
								<td  id="xzDomain"><a href="javascript:bannerUpload()"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传</span>&nbsp;&nbsp;</a>
								</a>
								</td>
								<td id="xzDomain"><a href="javascript:getUpdateBanner();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="la" class="easyui-window" title="查看广告"
		style="padding:0;width:550px;height:350px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="advertTt">
		</table>
		<div id="advertToolbar" class="test">

			<table width="100%">
				<tr>

					<td>
						<table align="right">
							<tr>
								<td id="xzDomain"><a href="javascript:advertUpload()"><div 
										style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</span>
								</a></td>
								<td id="xzDomain"><a href="javascript:getUpdateAdvert();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="laLogo" class="easyui-window" title="Logo设置"
		style="padding:0;width:550px;height:350px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="logoTt">
		</table>
		<div id="logoToolbar" class="test">

			<table width="100%">
				<tr>

					<td>
						<table align="right">
							<tr>
								<td id="xzDomain"><a href="javascript:getUploadLogo();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传Logo</span>&nbsp;&nbsp;</a>
								</td>
								<td id="xzDomain"><a href="javascript:getUpdateLogo();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改Logo</span>&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="laChildMenu" class="easyui-window" title="子栏目设置"
		style="padding:0;width:550px;height:350px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="childMenuTt">
		</table>
		<div id="childMenuToolbar" class="test">

			<table width="100%">
				<tr>

					<td>
						<table align="right">
							<tr>
								<td><input type="hidden" name="parentMenuID" id="parentMenuID"/>
									<input type="hidden" name="parentMenuName" id="parentMenuName"/>
								</td>
								<td id="xzDomain"><a href="javascript:getAddChildMenu();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
								</td>
								<td id="xzDomain"><a href="javascript:getUpdateChildMenu();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
								</td>
								<td id="xzDomain"><a href="javascript:getdeleteChildMenu();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
	</div>
	
	
	<form id="addMenu" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<table
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;">
				<tr style="line-height: 20px;">
					<td style="*padding-left: 20px;" >栏&nbsp;&nbsp;&nbsp;目： <span id="menuNo"></span></td>
				</tr>
				<tr style="line-height: 20px;">
					<td style="*padding-left: 20px;" id="idmetaValueww">栏目名：
					
					</td>
				</tr>
			
				<tr id="tr_menu_image" style="display: none">
					<td style="*padding-left: 20px;" >图&nbsp;&nbsp;&nbsp;片：<input type="file" name="menuImage" id="menuImage"  class="ww"/></td>
				</tr>
				<tr>
					<td style="color: #ff0000" id="tishi2"></td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="menuId" name="menuId">
	</form>
	<div id="addmenu" class="easyui-window" title="添加"
		style="top:50px ;overflow:hidden;background:#E2EDFF;width:600px;height:500px;[;height:500px;];*height:500px;height:500px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="eeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="add()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeAddmenu()">取消</a>
		</div>
	</div>
	<div id="updatemenu" class="easyui-window" title="修改"
		style="top:50px ;overflow:hidden;background:#E2EDFF;width:800px;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="updateeeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="update()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUpdatemenu()">取消</a>
		</div>
	</div>
	<div id="uploadbanner" class="easyui-window" title="上传banner"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:360px;height:180px;[;height:200px;];*height:170px;height:180px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="uploadeeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="uploadBanner()">上传</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUploadBanner()">取消</a>
		</div>
	</div>
	<div id="updatebanner" class="easyui-window" title="上传banner"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:360px;height:180px;[;height:200px;];*height:170px;height:180px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="uploadBannereeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="updateBanner()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUpdateBanner()">取消</a>
		</div>
	</div>
	<form id="uploadBanner" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<table
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px; *padding: 10px;">
				<tr style="line-height: 20px;">
					<td style="*padding-left: 20px; width: 200px; " colspan="2" >
					<span id="nameStr"></span>：<span id="bannerName"></span></td>
				</tr>
				<tr style="line-height: 20px;">
					<td style="*padding-left: 20px;width: 40px;"><span id="nameValueStr"></span>：
					</td>
					<td>
					<input type="file" name="optionValue" class="ww" id="optionValue"/>
					</td>
				</tr>
				<tr id="tr_url" style="line-height: 20px;">
					<td  style="*padding-left: 20px; width: 40px;">链接：
					</td>
					<td>
					<input type="text" name="url" id="url" size="32" class="ww"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="color: #ff0000" id="tishi"></td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="optionId" name="optionId">
	</form>
	<div id="uploadadvert" class="easyui-window" title="上传广告"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:360px;height:180px;[;height:200px;];*height:170px;height:180px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="uploadAdverteeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="uploadAdvert()">上传</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUploadAdvert()">取消</a>
		</div>
	</div>
	<div id="updateadvert" class="easyui-window" title="修改广告"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:360px;height:180px;[;height:200px;];*height:170px;height:180px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="updateAdverteeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="updateAdvert()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUpdateAdvert()">取消</a>
		</div>
	</div>
	<div id="uploadlogo" class="easyui-window" title="上传Logo"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:360px;*width:330px;height:180px;[;height:200px;];*height:170px;height:180px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="uploadLogoeeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="uploadLogo()">上传</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUploadLogo()">取消</a>
		</div>
	</div>
	<div id="updatelogo" class="easyui-window" title="修改Logo"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:360px;height:180px;[;height:200px;];*height:180px;height:180px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="updateLogoeeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="updateLogo()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUpdateLogo()">取消</a>
		</div>
	</div>
	
	
	<div id="addchildMenu" class="easyui-window" title="添加"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:800px;height:400px;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="addchildMenueeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="addChildMenu()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeAddChildMenu()">取消</a>
		</div>
	</div>
	<div id="updatechildMenu" class="easyui-window" title="修改"
		style="top:50px; overflow:hidden;background:#E2EDFF;width:800px;height:400px;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="updatechildMenueeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="updateChileMenu()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUpdateChileMenu()">取消</a>
		</div>
	</div>
	
	<!-- 显示详细内容 -->
	
	<div id="laPage" class="easyui-window" title="查看栏目下详细信息"
		style="padding:0;width:800px;height:500px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="pageTt">
		</table>
		<div id="pageToolbar" class="test">

			<table width="100%">
				<tr>

					<td>
						<table align="right">
							
							<tr>
								<td id="xzDomain"><a href="javascript:getAddBanner()"><div 
										style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;banner设置</span></span>
								</a></td>
								<td id="xzDomain"><a href="javascript:getAddAdvert()"><div 
										style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广告设置</span>
								</a></td>
								<td id="xzDomain"><a href="javascript:getAddPage()"><div 
										style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;
								</a></td>
								<td id="xzDomain"><a href="javascript:getUpdatePage();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
								</td>
								<td id="xzDomain"><a href="javascript:getDeletePage();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
								</td>
								
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
	</div>
	<form id="pageform" method="post">
	<div class="xx21">
		<table style="margin-top:10px;margin-bottom:10px; margin-left:10px;">
			<tr id="tr5" style="display: none">
				<td>所属分类</td>
				<td><select class="easyui-combobox" name="childMenuId" id="childMenuId" style="width:130px;" ></select></td>
			</tr>
		
			<tr id ="tr1" style="display: none">
				<td>
						内容标题 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="pageID" name="pageID" class="ww" >
				</td>
				<td>
					<input type="text" id="pageTitle" name="pageTitle" style="width:240px; height:23px;" class="ww" />
				</td>
			</tr>
			<tr id="tr2" style="display: none">
				<td>
						内容概述 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					<textarea id="metaDetail" name="metaDetail" rows="5" cols="50" class="ww"></textarea>
				</td>
			</tr>
			<tr id = "tr3" style="display: none">
				<td valign="top">
					  内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    </td>
			    <td id="idmetaValue">
				<textarea id="metaValue" name="metaValue" rows="5" cols="50" class="ww"></textarea>
				</td>
			</tr>
			
			<tr id="tr4" style="display: none">
				<td><span id="pageFileName"></span></td>
				<td><span id="pageFile"></span><span style="color: #ff0000" id="tishi1"></span></td>
			</tr>
			<tr>
				<td>文章链接</td>
				<td><input type="text" id="pageUrl" name="pageUrl" style="width:400px;"  size="100" /></td>
			</tr>
		</table>
	</div>
	</form>
	
	<form id="telPageform" method="post">
	<div class="xx21">
		<table style="margin-top:10px;margin-bottom:10px; margin-left:10px;" id="telTable" align="center">
			<tr id ="telTr1" style="display: none">
				<td style="*padding-left: 20px;">
						内容标题 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="touchPageID" name="touchPageID" >
				</td>
				<td style="*padding-left: 20px;">
					<input type="text" id="touchPageTitle" name="touchPageTitle" class="ww" />
				</td>
			</tr>
			<tr>
				<td style="*padding-left: 20px;">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				<td style="*padding-left: 20px;"><input type="text" id="address" name="address" class="ww" /></td>
			</tr>
			<tr>
				<td style="*padding-left: 20px;">热线电话 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td style="*padding-left: 20px;"><input type="text" id="hotline" name="hotline" class="ww" /></td>
			</tr>
			<tr>
				<td style="*padding-left: 20px;">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</td>
				<td style="*padding-left: 20px;"><input type="text" id="telephone" name="telephone" class="ww" /></td>
			</tr>
			<tr>
				<td style="*padding-left: 20px;">座&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</td>
				<td style="*padding-left: 20px;"><input type="text" id="landline" name="landline" class="ww" /></td>
			</tr>
			<tr>
				<td style="*padding-left: 20px;">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</td>
				<td style="*padding-left: 20px;"><input type="text" id="email" name="email" class="ww" /></td>
			</tr>
			<tr>
				<td style="*padding-left: 20px;">联&nbsp;&nbsp;系&nbsp;人</td>
				<td style="*padding-left: 20px;"><input type="text" id="contacts" name="contacts"class="ww" /></td>
			</tr>
			<tr>
				<td style="*padding-left: 20px;">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图</td>
				<td style="*padding-left: 20px;"><input type="text" id="localtion" name="localtion" class="ww"/><a href="http://api.map.baidu.com/lbsapi/getpoint/index.html" target="blank">点击此话拾取中心坐标</a></td>
			</tr>
			<tr id="telTr2" style="display: none">
				<td style="*padding-left: 20px;">配&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图</td>
				<td style="*padding-left: 20px;"><input type="file" name="touchBigImage" id="touchBigImage" class="ww"/></td>
			</tr>
		</table>
	</div>
	</form>
	
	<!-- 单页 -->
	<div id="addPage" class="easyui-window" title="添加"
		style="top:100px; ;overflow:hidden;background:#E2EDFF;width:800px;height:105px;[;height:105px;];*height:105px;height:105px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="addPageeeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="addPage()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeAddPage()">取消</a>
		</div>
	</div>
	
	<div id="updatePage" class="easyui-window" title="修改"
		style="top:100px; ;overflow:hidden;background:#E2EDFF;width:800px;height:105px;[;height:105px;];*height:105px;height:105px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="updatePageeeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="updatePage()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUpdatePage()">取消</a>
		</div>
	</div>
	<div id="addTelPage" class="easyui-window" title="添加"
		style="top:100px; ;overflow:hidden;background:#E2EDFF;width:550px;height:105px;[;height:105px;];*height:105px;height:105px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="addTelPageeeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="addTelPage()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeAddTelPage()">取消</a>
		</div>
	</div>
	
	<div id="updateTelPage" class="easyui-window" title="修改"
		style="top:100px; ;overflow:hidden;background:#E2EDFF;width:550px;height:105px;[;height:105px;];*height:105px;height:105px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="updateTelPageeeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="updateTelPage()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="closeUpdateTelPage()">取消</a>
		</div>
	</div>
	<div id="addexplicit" class="easyui-window" title="访问路径" 
		style="top:100px; ;overflow:hidden;background:#E2EDFF;width:500px;height:100px;[;height:100px;];*height:100px;height:100px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="eeXL1"></div>
		<div>&nbsp;</div>
	</div>
	<form id="addExplicit" target="hidden_frame"
		enctype="multipart/form-data" method="post">
		<div class="xx21" >
			<div>
				<table>
					<tr>
						<td>
							<span id="urlStr"></span>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>
