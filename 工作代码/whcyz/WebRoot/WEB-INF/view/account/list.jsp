<%@page import="com.whcyz.model.Account"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="assets/css/dataTables.bootstrap.css">
<script type="text/javascript" language="javascript" src="assets/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="assets/js/dataTables.bootstrap.js"></script>
<script type="text/javascript" charset="utf-8">
$(function() {
	//配置弹出框modal modal是bootstrap打开一个模式窗口插件，所谓模式窗口就是打开子窗口后父窗口内的内容不可以被操作，只能操作子窗口中的内容
	var modal="#modal";
	var modalTitle="#modalTitle";
	var modalHtml="#modalHtml";
	var modalSubmitBtn="#submitModalBtn";
	
	var accountTable = $('#accountTable').DataTable({
		"oLanguage": {
			 "sUrl": "/assets/js/dtcn.json"//从配置文件中读取语言包  默认英文显示
		},
		"stateSave": true,//开关，是否打开客户端状况记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或从头打开浏览器，之前的状况都是保存下来的
		"processing": true,
		"bProcessing": true,//开关，以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
		"serverSide": true,//在服务器端整理数据
		"ajaxSource": "/account/ajax?rand="+Math.random(),//使用ajax，在服务器端整理数据
		"columns": [
		    //第一排的序号 
			{
				 "searchable": false,//设置列的数据是否过滤
			     "orderable" : false,//设置列是否可以排序
			    "sClass": "tcenter",//设置列的class属性值
			    "data" : "index",//设置单元格里的值
			    "defaultContent": "",//设置列的默认值
			    "targets" : 0   //  "targets": 0,//第一列隐藏     "targets": -2,//编辑     "targets": -1,//删除
			},
			{ "data": "username", "sClass": "tcenter"},
			{ "data": null, "sClass": "tcenter",
				 "orderable": false,//设置列是否可以排序
		         "mRender": function (obj) {
		        	 var str="未知";
		        	 switch(obj.permission){//获取account数据库     permission字段值
		        	 case 1:
		        		 str="超级管理员";
		        		break;
		        	 case 2:
		        		 str="普通用户";
		        		break;
		        	 case 3:
		        		 str="网站编辑";
		        		break;
		        	 }
		            	return str;
		            }	
			},
			{ "data": "registerTime", "sClass": "tcenter"},
			{ "data": "nickname", "sClass": "tcenter"},
			{ "data": "phone", "sClass": "tcenter"},
			{ "data": "qq", "sClass": "tcenter"},
			{ "data": "email", "sClass": "tcenter"},
            //放置最后一排的操作按钮
            {
	            "targets": -1,//"targets": -1,//删除
	            "data": null,//设置单元格里的值
	            "sClass": "tcenter",//设置列的class属性值
	            "orderable": false,//设置列是否可以排序
	            "mRender": function (obj) {
	            	var opt = '<a class="cus_editbtn" objId = "'+obj.id+'" permission="'+obj.permission+'" href="javascript:void(0)" title="修改"><img src="/assets/css/imgs/update.png" align="absmiddle" /></a>&nbsp';
					// 异步删除
            		opt += '<a class="cus_delbtn" objId = "'+obj.id+'" href="javascript:void(0)" title="删除"><img src="/assets/css/imgs/delete.png" align="absmiddle" /></a>&nbsp';
	            	return opt;
	            }
	        }
        ],
        "order": [[ 7, 'desc' ]],	//默认排序行
        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			$('td:eq(0)', nRow).html((accountTable.page()*accountTable.page.len())+iDisplayIndex+1);
			return nRow;
		}
	});
	//给查询框绑定回车事件
	accountTable.on('init.dt', function (e, settings) {
		$(modalSubmitBtn).unbind("click");
		/* $(".cus_editbtn").unbind("click");
		$(".cus_delbtn").unbind("click");
		$(".cus_contenteditbtn").unbind("click"); */
		$(".dataTables_filter input").unbind();
		$(".dataTables_filter input").attr("placeholder","敲回车查询");
		$(".dataTables_filter input").keyup( function (event) {
			if(event.keyCode == 13){
				accountTable.search($(this).val()).draw();
			}
	    });
	

	$('.accountpage').on('click','table tr',function () {
        	accountTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
    });
	
	//删除事件
	$('.accountpage').on('click','.cus_delbtn',function () {
		if(confirm('确定删除?')){
			appLoading("正在删除信息,请稍后...");
			var _this = $(this);
			$.ajax({
				type:"POST",
				url:"/account/delete",
				data: {id:_this.attr("objId")},
				datatype:"json",
				success:function(result){
					if(result.success){
						accountTable.row('.selected').remove().draw( false );
						closeAppLoading();
					}else{
						appErrorMsg(result.msg==null?result:result.msg,1000);
					}
				}
			});
		}else{
			closeAppLoadingNow();
		}
	});
	function modalSubmitCallback(data){
		closeWaittingForm("accountForm");
		if(data.success){
			$(modal).modal("hide");
			appSuccessMsg("操作成功", 800);
			setTimeout(function(){
			accountTable.draw( false );
			},800);
		}else{
			appErrorMsg(data.msg==null?data:data.msg, 2000);
		}
	}
	
	$(modalSubmitBtn).click(function () {
			submitMe(function(data){
				modalSubmitCallback(data);
			});
	});
	//编辑事件
	$('.accountpage').on('click',".cus_editbtn",function () {
			appLoading("正在执行,请稍后...");
			var _this = $(this);
			$(modalTitle).text("用户信息编辑");
			var id=_this.attr("objId");
			var permission=_this.attr("permission");
			var url="/account/edit?id="+id+"&permission="+permission+"&rand="+Math.random();
			$(modalHtml).empty();
			$.get(url,function(data){
				closeAppLoadingNow();
				$(modalHtml).html(data);
				$(modal).modal("show");
				
			});
			
	});
	
	
	});
	
		
});
</script>
<div class="accountpage">
<div class="row ">
	<div class="col-lg-12 tright">
	<div class="btn-group">
		<button class="btn btn-primary cus_editbtn" objId="0" permission="<%=Account.PERMISSION_ADMIN%>">新增管理员</button>
		<button class="btn btn-primary cus_editbtn" objId="0" permission="<%=Account.PERMISSION_EDITOR%>">新增站点编辑</button>
		<button class="btn btn-primary cus_editbtn" objId="0" permission="<%=Account.PERMISSION_NORMAL%>">新增普通账户</button>
	</div>
	</div>
</div>
<div class="hline1"><div></div></div>
<div class="row pt10">
	<div class="col-lg-12">
		<table id="accountTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered " id="example">
		<thead>
		  <tr>
		  	  <th rowspan="2" style="line-height: 65px;width:60px;">序号</th>
              <th colspan="2" class="tcenter">基础信息</th>
              <th colspan="5"  class="tcenter">联系方式</th>
              <th rowspan="2" style="line-height: 65px;">操作</th>
          </tr>
		<tr>
				<th style="width:100px;">用户名</th>
				<th>权限</th>
				<th style="width: 150px;">注册时间</th>
				<th>昵称</th>
				<th>手机</th>
				<th>QQ</th>
				<th>邮箱</th>
			</tr>
		<thead>
		<tfoot>
			<tr>
			 	<th >序号</th>
				<th>用户名</th>
				<th>权限</th>
				<th>注册时间</th>
				<th>昵称</th>
				<th>手机</th>
				<th>QQ</th>
				<th>邮箱</th>
				<th>操作</th>
			</tr>
		</tfoot>
		</table>
	</div>
</div>
</div>