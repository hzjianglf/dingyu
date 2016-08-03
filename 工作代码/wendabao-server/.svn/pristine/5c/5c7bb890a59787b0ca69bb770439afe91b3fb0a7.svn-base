<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.handany.bm.model.BmContactor"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	PageInfo<BmContactor> pageInfo = (PageInfo<BmContactor>) request.getAttribute("data");
	List<BmContactor> list = (List<BmContactor>) pageInfo.getList();

	/*一共有多少页  */
	int totalPage = pageInfo.getPages();
	/* 当前页显示的数量  */
	int size = pageInfo.getSize();
	/* 当前页  */
	int currentPage = pageInfo.getPrePage() + 1;
	/* 一页显示多少条  */
	int length = pageInfo.getPageSize();
	/* 是否有下一页  */
	boolean isNext = pageInfo.isHasNextPage();
	/* 页数数组 */
	int[] data = pageInfo.getNavigatepageNums();

	String tokenId = (String) request.getAttribute("tokenId");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />

<!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>采供网</title>
<%@include file="/common.jsp"%>
<script src="<%=basePath%>js/mallDialog.js"></script>
<meta content="" name="description" />
<meta content="" name="author" />

<link rel="stylesheet" href="<%=basePath%>/css/mall.css">
</head>
<body>

	<div class="container" style="width: 100%;">
		<div class="row" style="border: 0; margin-top: -10px;">
			<div class="col-sm-12">
				<h3 class="zs-iframe-title">联系人管理</h3>
				<button class="btn btn-default zs-table-top-btn zs-btn-green"
					data-toggle="modal" data-target="#myModal" style="float: right">新增</button>
			</div>
		</div>
		<div class="data_content row" style="margin-top: 10px;">
			<div class="col-sm-12">
				<table class="table table-hover">
					<thead>
						<tr>
							<th width="110">名称</th>
							<th width="110">联系方式</th>
							<th width="110">操作</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (list != null && list.size() != 0) {
								for (int i = 0; i < list.size(); i++) {
						%>
						<tr>
							<td align="center"><%=list.get(i).getContactorName()%></td>
							<td align="center"><%=list.get(i).getTelephone()%></td>
							<td align="center"><a data-toggle="modal"
								data-target="#myModal<%=list.get(i).getId()%>">修改</a> <a
								class="zs-table-delete"
								onclick="deleteContactor(<%="'" + list.get(i).getId() + "'"%>)">删除</a>
								<%-- 
			            <em><a href="#" data-toggle="modal" data-target="#myModal<%=list.get(i).getId() %>">修改</a></em>
					     <em><a href="javascript:deleteContactor(<%="'" + list.get(i).getId() +"'"%>)"><font color="#003399">&nbsp;删除</font></a></em> --%>
							</td>
						</tr>
						<%
							}
							}
						%>

					</tbody>
				</table>

				<nav style="float: right;">
					<ul class="pagination">
						<li><a href="javascript:prePage()" aria-label="Previous">
								<span aria-hidden="true">上一页</span>

						</a></li>
						<%
							if (null != data && data.length != 0) {
								for (int x = 0; x < data.length; x++) {

									if (currentPage == data[x]) {
						%>
						<li><a style="background: #E0E0E0"
							href="javascript:toPage(<%="'" + data[x] + "'"%>)"><%=data[x]%></a></li>
						<%
							} else {
						%>
						<li><a href="javascript:toPage(<%="'" + data[x] + "'"%>)"><%=data[x]%></a></li>
						<%
							}
								}
							}
						%>

						<li><a href="javascript:nextPage()" aria-label="Next"> <span
								aria-hidden="true">下一页</span>
						</a></li>
					</ul>
				</nav>

			</div>

		</div>
	</div>






	<%
		if (list != null && list.size() != 0) {
			for (int j = 0; j < list.size(); j++) {
	%>

	<!-- Modal 修改商铺联系人 -->
	<div class="modal fade" id="myModal<%=list.get(j).getId()%>"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改联系人信息</h4>
				</div>
				<div class="modal-body">
					<form id="update<%=list.get(j).getId()%>"
						class="form-horizontal form"
						action="updateContactor.do?tokenId=<%=tokenId%>" method="post"
						onsubmit="return check(this);">
						<input type="hidden" name="id" value="<%=list.get(j).getId()%>" />
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control name"
									name="contactorName" placeholder="姓名"
									value="<%=list.get(j).getContactorName()%>">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">联系电话</label>
							<div class="col-sm-4">
								<input type="text" class="form-control phone" name="telephone"
									id="update_phone<%=list.get(j).getId()%>" placeholder="联系电话"
									value="<%=list.get(j).getTelephone()%>">
							</div>
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<%-- <button type="button" class="btn btn-primary" onclick="updateContactor(<%=list.get(j).getId() %>)">保存</button> --%>
					<input type="submit"
						class="btn btn-primary save'<%=list.get(j).getId()%>'"
						id="s_<%=list.get(j).getId()%>" value="保存" />
				</div>
				</form>
			</div>
		</div>
	</div>
	<script>
			function check(form){
				 if(!$(form).find(".name").val()){
					showDialog("请输入联系人名称","toast",",");
					return false;
				}
				if(!checkDataReg(REG_TEL,$(form).find(".phone").val())){
					showDialog("请输入正确的电话格式","toast","","");
					return false;
				} 
				return true;
			}
			</script>

	<%
		}
		}
	%>

	<!-- Modal 添加商铺联系人 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加联系人信息</h4>
				</div>
				<div class="modal-body">
					<form id="add" class="form-horizontal"
						action="addContactor.do?tokenId=<%=tokenId%>" method="post">

						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="add_name"
									name="contactorName" placeholder="姓名">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">联系电话</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="telephone"
									id="add_phone" placeholder="联系电话">
							</div>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="addContactor()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="currentPage" value="<%=currentPage%>">
	<input type="hidden" id="isNext" value="<%=isNext%>">
	<input type="hidden" id="length" value="<%=length%>">
	<input type="hidden" id="tokenId" value="<%=tokenId%>" />
	<script>
var tokenId = $("#tokenId").val();
var currentPage = $("#currentPage").val();
var isNext = $("#isNext").val();
var length = $("#length").val();


function deleteContactor(data)
{
	showDialog("确定要删除该联系人吗？","confirm",
			{onOk:function(){
				$.ajax({
					url:toServerUrl("/bm/shop/deleteContactor.do"),
					data:{				
						"id":data,		
					},
					type:"post",
					dataType:"json",
					success:function(data){
						if(true == data.header.success){
							setTimeout(function(){
								window.location=toServerPageUrl("/bm/shop/contactsManage.do");
							},1000);
						}else{
							alert("删除失败！！");
						}
					}
					
				}); 
			}}
			,"");

}

function addContactor(){	
	if(!$("#add_name").val()){
		showDialog("请输入姓名","toast","","");
		return;
	}
	if(!checkDataReg(REG_TEL, $("#add_phone").val())){
		showDialog("请输入正确的手机格式","toast","","");
		return;
	}
	$("#add").action = toServerPageUrl("/bm/shop/addContactor.do");
	$("#add").submit();
}

/* function updateContactor(){	
	if(!$("#update_name").val()){
		showDialog("请输入姓名","toast","","");
		return;
	}
	if(!checkDataReg(REG_TEL, $("#update_phone").val())){
		showDialog("请输入正确的手机格式","toast","","");
		return;
	}
	
	$("#add").action = toServerPageUrl("/bm/shop/addContactor.do");
	$("#add").submit();
}
 */
/* 页数跳转 */
function toPage(data){
	var start = (data - 1) * length;
	window.location=toServerPageUrl("/bm/shop/contactsManage.do?length=" + length + "&start=" + start);
}
/* 下一页  */
function nextPage(data){
	if(isNext == 'true'){
		var start = currentPage * length;
		window.location=toServerPageUrl("/bm/shop/contactsManage.do?length=" + length + "&start=" + start);
	}
}
/* 上一页  */
function prePage(data){
	if(currentPage == '1'){
		
	}else{
		var start = (currentPage - 2) * length;
		window.location=toServerPageUrl("/bm/shop/contactsManage.do?length=" + length + "&start=" + start);
	}
}

</script>



</body>