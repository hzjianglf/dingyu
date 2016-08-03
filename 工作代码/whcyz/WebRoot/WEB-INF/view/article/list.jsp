<%@page import="com.whcyz.model.Article"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="assets/css/dataTables.bootstrap.css">
<script type="text/javascript" language="javascript" src="assets/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="assets/js/dataTables.bootstrap.js"></script>
<script type="text/javascript" charset="utf-8">
$(function() {
	//配置弹出框modal  modal是bootstrap打开一个模式窗口插件，所谓模式窗口就是打开子窗口后父窗口内的内容不可以被操作，只能操作子窗口中的内容
	var modal="#modal";
	var modalTitle="#modalTitle";
	var modalHtml="#modalHtml";
	var modalSubmitBtn="#submitModalBtn";
	var lurl=window.location.hash;//location是javascript里边管理地址栏的内置对象，比如location.href就管理页面的url，用location.href=url就可以直接将页面重定向url。
	                              //而location.hash则可以用来获取或设置页面的标签值。
	var category=0;
	var searchparam="";
	
	if(lurl.indexOf("category=")!=-1){
		category=lurl.replace("#/articlemgr?category=","");
	}
	 $(".nav-tabs li.active").removeClass("active");
	 $("a.navitem[category='"+category+"']").parent("li").addClass("active"); 
	var articleTable = $('#articleTable').DataTable({
		"oLanguage": {
			"sUrl": "/assets/js/dtcn.json"//从配置文件中读取语言包  默认英文显示
		},
		"processing": true,
		"bProcessing": true,//开关，以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
		"serverSide": true,//在服务器端整理数据
	
		"ajaxSource": "/article/ajax?category="+category+"&rand="+Math.random(),//使用ajax，在服务器端整理数据
		"columns": [
		    //第一排的序号 
			{
				"searchable": false,//设置列的数据是否过滤
			    "orderable" : false,//设置列是否可以排序
			    "sClass": "tcenter",//设置列的class属性值
			    "data" : "index",//设置单元格里的值
			    "defaultContent": "",//设置列的默认值
			    "targets" : 0//  "targets": 0,//第一列隐藏     "targets": -2,//编辑     "targets": -1,//删除
			},
				{ "data": "title", "sClass": "tcenter"},
			/* { "data": null, "sClass": "tcenter",
			 	"orderable": false,
				"mRender": function (obj) {
					return "<a href='article/detail/"+obj.category+"-"+obj.id+"' target='_blank' >"+obj.title+"</a>";
				}	
			}, */
			{ "data": null, "sClass": "tcenter",
				  "orderable": false,//设置列是否可以排序
		            "mRender": function (obj) {
		            	switch (obj.category) {//获取数据库     category字段值 数据库里存的是 1，  2  ，3   ，4 ，
						case 1:
							return "福州变电站"
						case 2:
							return "龙岩变电站"
						case 3:
							return "泉州变电站"
						case 4:
							return "厦门变电站"
						}
		            }
			},
			{ "data": "author", "sClass": "tcenter"},
			{ "data": "postTime", "sClass": "tcenter"},
			{ "data": "readCount", "sClass": "tcenter"},
			{ "data": "commentCount", "sClass": "tcenter"},
            //放置最后一排的操作按钮
            {
	            "targets": -1,//"targets": -1,//删除
	            "data": null,//设置单元格里的值
	            "sClass": "tcenter",//设置列的class属性值
	            "orderable": false,//设置列是否可以排序
	            "mRender": function (obj) {
	            	var opt = '<a class="cus_editbtn" objId = "'+obj.id+'" permission="'+obj.permission+'" href="javascript:void(0)" title="修改基本信息"><img src="/assets/css/imgs/update.png" align="absmiddle" /></a>&nbsp';
					// 修改简介内容
            		/* opt += '<a class="cus_contenteditbtn" objId = "'+obj.id+'" href="javascript:void(0)" title="修改文章详情"><img src="/assets/css/imgs/info.png" align="absmiddle" /></a>&nbsp'; */
					// 异步删除
            		opt += '<a class="cus_delbtn" objId = "'+obj.id+'" href="javascript:void(0)" title="删除"><img src="/assets/css/imgs/delete.png" align="absmiddle" /></a>&nbsp';
	            	return opt;
	            }
	        }
        ],
        "order": [[ 4, 'desc' ]],	//默认排序行
        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			$('td:eq(0)', nRow).html((articleTable.page()*articleTable.page.len())+iDisplayIndex+1);
			return nRow;
		}
	});
	
	articleTable.on('init.dt', function () {
		$(modalSubmitBtn).unbind("click");
		/* $(".cus_editbtn").unbind("click");
		$(".cus_delbtn").unbind("click");
		$(".cus_contenteditbtn").unbind("click"); */
		$(".dataTables_filter input").unbind();
		$(".dataTables_filter input").attr("placeholder","敲回车查询");
		$(".dataTables_filter input").keyup( function (event) {
			if(event.keyCode == 13){
				articleTable.search($(this).val()).draw();
			}
	    });
		
		$(".articlepage").on("click",'table tr',function () {
        	articleTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
    });
	
	//删除事件
	$(".articlepage").on("click",'#articleTable .cus_delbtn',function () {
		if(confirm('确定删除?')){
			appLoading("正在删除信息,请稍后...");
			var _this = $(this);
			$.ajax({
				type:"POST",
				url:"/article/delete",
				data: {id:_this.attr("objId")},
				datatype:"json",
				success:function(result){
					if(result.success){
						articleTable.row('.selected').remove().draw( false );
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
		closeWaittingForm("articleForm");
		if(data.success){
			$(modal).modal("hide");
			appSuccessMsg("操作成功", 800);
			setTimeout(function(){
				articleTable.draw( false );
			},800);
		}else{
			appErrorMsg(result.msg==null?result:result.msg, 2000);
		}
	}
	
	//编辑简介
	$(".articlepage").on("click",".cus_contenteditbtn",function () {
			appLoading("正在执行,请稍后...");
			var _this = $(this);
			var id=_this.attr("objId");
			var url="/article/editContent?id="+id+"&category="+category+"&rand="+Math.random();
			 window.location.hash=url;
			    $.get(url,function(data,status){
			    	closeAppLoading();
			    	$("#mainContainer").empty();
			    	if(status=="success"){
			    		$("#mainContainer").html(data);
			    	}else{
			    		alert("读取失败！");
			    	}
			    });
			
	});
	
	$(modalSubmitBtn).click(function(){
		submitMe(function(data){
			modalSubmitCallback(data);
		});
	});
	//编辑
	$(".articlepage").on("click",".cus_editbtn",function () {
			appLoading("正在执行,请稍后...");
			var _this = $(this);
			$(modalTitle).text("录波器基本信息编辑");
			var id=_this.attr("objId");
			var url="/article/edit?id="+id+"&rand="+Math.random();
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
<div class="articlepage">
<div class="row ">
	<div class="col-lg-6 tleft">
	<ul class="nav nav-tabs" role="tablist">
  <li><a class="navitem"  category="0" href="/articlemgr?category=0"  role="tab" data-toggle="tab">全部</a></li>
  <li><a class="navitem"  category="<%=Article.CATEGORY_STORY %>" href="/articlemgr?category=<%=Article.CATEGORY_STORY %>" role="tab" data-toggle="tab">福州变电站</a></li>
  <li><a class="navitem"  category="<%=Article.CATEGORY_FINANCING %>" href="/articlemgr?category=<%=Article.CATEGORY_FINANCING %>" role="tab" data-toggle="tab">龙岩变电站</a></li>
  <li><a class="navitem"  category="<%=Article.CATEGORY_VIEWPOINT %>" href="/articlemgr?category=<%=Article.CATEGORY_VIEWPOINT %>" role="tab" data-toggle="tab">泉州变电站</a></li>
  <li><a class="navitem"  category="<%=Article.CATEGORY_COMMUNICATION %>" href="/articlemgr?category=<%=Article.CATEGORY_COMMUNICATION %>" role="tab" data-toggle="tab">厦门变电站</a></li> 
</ul>
	</div>
	<div class="col-lg-6 tright">
	<div class="btn-group">
		<button class="btn btn-primary cus_editbtn" objId="0">新增录波器</button>
	</div>
	</div>
</div>
<div class="hline1"><div></div></div>
<div class="row pt10">
	<div class="col-lg-12">
		<table id="articleTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered " id="example">
		<thead>
		<tr>
			 	<th style="width:40px">编号</th>
				<th style="width:70px">主站</th>
				<th style="width:70px">变电站</th>
				<th style="width:110px">录波器</th>
				<th style="width:120px">录波文件时间</th>
				<th style="width:40px">接受方式</th>
				<th style="width:40px">描述</th>
				<th style="width:90px">操作</th>
			</tr>
		<thead>
		<!-- <tfoot>
			<tr>
			 	<th>序号</th>
				<th>标题</th>
				<th>分类</th>
				<th>作者</th>
				<th>时间</th>
				<th>阅读</th>
				<th>评论</th>
				<th>操作</th>
			</tr>
		</tfoot> -->
		</table>
	</div>
</div>
</div>