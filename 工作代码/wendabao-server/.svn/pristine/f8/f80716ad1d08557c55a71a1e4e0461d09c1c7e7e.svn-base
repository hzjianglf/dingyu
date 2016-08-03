<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.handany.bm.model.BmProduct"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.handany.base.common.Constants"%>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
 <%@include file="/common.jsp" %>
<title></title>
	<link rel="stylesheet" href="<%=basePath %>/css/mall.css">

<script type="text/javascript">
	$(function(){
		showLevel(1,0);
	});
	
	function showLevel(level,parentId){
		
		var curName = $("#class_"+parentId).html();
		var curLevel= Number(level)-1;
		$("#name").val(curName);
		$("#id").val(parentId);
		$("#level").val(curLevel);
		
		
		if(level == 2){
			$("#classLvl2").empty();
			$("#classLvl3").empty();
		}else if(level == 3){
			$("#classLvl3").empty();
		}
		$("#class_"+parentId).parent().children().each(function(i){
			
			$(this).removeClass("active");
			
		});
		
		$("#class_"+parentId).addClass("active");
		
		if(level ==4){
			return;
		}
		
		var nextLevel = Number(level)+1;
		$.getJSON(
			toServerUrl("/bm/product/querySubClassById.do"),
			{parentId:parentId},
			function(json){

				for(var i=0;i<json.list.length;i++){
					var item = json.list[i];
					$("#classLvl"+level)
					.append(" <a class=\"list-group-item\" id=\"class_"+item.id
							+"\" classId="+item.id+" level="+nextLevel
							+" href=\"javascript:showLevel("+nextLevel+",'"+item.id+"')\">"+item.name+"</a>");
				}
				
		});
	}
	
	function deleteClass(){
		var level = $("#level").val();
		var id = $("#id").val();
		
		if(id == ""){
			alert("请选择商品分类");
			return;
		}
		
		if(level ==1  ){
			if($("#classLvl2").html() != ""){
				alert("该分类下存在子分类，不可删除");
				return;
			}
		}else if(level == 2 ){
			if($("#classLvl3").html() != ""){
				alert("该分类下存在子分类，不可删除");
				return;
			}
		}
		
		if(!window.confirm("是否确定要删除分类:"+$("#name").val()+"")){
			return;
		}
		
		$.getJSON(
			toServerUrl("/bm/product/saveClass.do"),
			{id:id,valid:'F'},
			function(json){
				$("#class_"+id).remove();
			}
		);
	}
	
	function saveName(){
		
		var name = $("#name").val();
		
		if(getByteNum(name) > 100){
			alert("分类名称超长");
			return;
		}
		
		var id = $("#id").val();
		
		
		if(id == ""){
			alert("请选择商品分类");
			return;
		}
		
		if($.trim(name) == ""){
			alert("分类名称为空");
			return;
		}
		
		$.ajax({
				type: "POST",
				url:toServerUrl("/bm/product/saveClass.do"),
				data:{id:id,name:name},
				success:function(json){
					$("#class_"+id).html(name);
				},
				contentType: "application/x-www-form-urlencoded; charset=utf-8"
		});
	}
	
	function toAdd(){
		
		var id = $("#id").val();
		
		var level= $("#level").val();
		
		
		if(level == "" || level == 3){
			return;
		}
		
		
		if(id == ""){
			alert("未选择商品分类");
			return;
		}
		
		$("#parentName").val( $("#name").val());
		
		$('#myModal').modal("show");
	}
	
	function save(){
		
		var name =	$("#newName").val();
		
		if(getByteNum(name) > 100){
			alert("分类名称超长");
			return;
		}
		
		var id = $("#id").val();
		
		var level= $("#level").val();
		
		var nextLevel = Number(level)+1;
		
		var displayOrder =  $("#displayOrder").val();
		
		
		$.ajax({
			type: "POST",
			url:toServerUrl("/bm/product/saveClass.do"),
			data:{parentId:id,name:name,displayOrder:displayOrder,valid:'T'},
			success:function(json){
				$("#classLvl"+nextLevel).append(" <a class=\"list-group-item\" id=\"class_"+json.id
						+"\" classId="+json.id+" level="+nextLevel
						+" href=\"javascript:showLevel("+(nextLevel+1)+",'"+json.id+"')\">"+name+"</a>");
				$('#myModal').modal("hide");
			},
			contentType: "application/x-www-form-urlencoded; charset=utf-8"
	});
		
	}
	
	
	function publish(){
		
		$("#publishBtn").attr("disabled","disabled");
		
		$.getJSON(
				toServerUrl("/bm/product/refreshClass.do"),
				function(json){
					
					alert("产品分类发布成功");
					
					$("#publishBtn").removeAttr("disabled");
				}
			);
	}
	
</script>
</head>
<body>
<div class="container" style="width:100%;">

	<div class="row" style="border:0;margin-top:-10px;">
	<div class="col-sm-12">
	<h3 class="zs-iframe-title">商品分类管理</h3>
	</div>
	</div>
	
	
<div class=row>
<div class="col-sm-12" style="margin-bottom:15px;">
当前分类：
	<input type="hidden" placeholder="分类级别" id="level">
	<input type="text"  id="name">
	<input type="hidden" id="id">
	<input type="button"  class="zs-btn" value="保存" onclick="saveName()">
	<input type="button"  class="zs-btn" value="删除" onclick="deleteClass()">
	<input type="button"  class="zs-btn" value="新增下级" onclick="toAdd()">
	<button type="button" class="zs-btn" id="publishBtn" onclick="publish()">发布</button>
</div>	


</div>

<div class=row  style="margin-bottom:15px;">
<div class="col-sm-4" >一级分类</div>
<div class="col-sm-4" >二级分类</div>
<div class="col-sm-4" >三级分类</div>
</div>

<div class=row>
<div class="col-sm-4" >
<div class="list-group" id="classLvl1">
  
</div>
</div>
<div class="col-sm-4" >
<div class="list-group" id="classLvl2">
 
</div>
</div>
<div class="col-sm-4" >
<div class="list-group" id="classLvl3">
  
</div>
</div>

</div>
</div>

<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">新增分类</h4>
      </div>
      <div class="modal-body">
        <label >上级分类名称</label>
        <input type="text" class="form-control" id="parentName" readonly style="background:#fff">
        <label >新增分类名称</label>
        <input type="text" class="form-control" id="newName">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="save()">保存</button>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>