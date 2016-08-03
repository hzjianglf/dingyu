var name = "tt";
var menuId=0 ;
var menuNameID=0;
$(function() {
	$("#toolbar").css("visibility", "visible");
	
	$('#tt')
			.datagrid(
					{
						 //onRowContextMenu:onRowContextMenu,
						title : '栏目设置',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-website!menuEdit',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						//onRowContextMenu:onRowContextMenu,
						remoteSort : false,
						columns : [ [
								{
									title : '栏目',
									field : 'menuNameStr',
									width : '570',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '栏目名',
									field : 'menuName',
									width : '600',
									align : 'center',
									visibility : 'hidden'
								},
								/*{
									field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : 50,
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.menuId + "  />";
									}
								}*/ ] ],
						onClickRow : function(value, rec) {
							var str = $("#option").html();
							if (s_caocuo == "true") {
								str = $("#option").html().replace(
										"id=\"caozuo\"",
										"id=\"caozuo\" checked=\"checked\"");
							} else {
								str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
							}
							$("#option").html(str);
						},
						rownumbers : true,

						toolbar : "#toolbar"
					});
	displayMsg();
});
function displayMsg() {

	$('#tt')
			.datagrid('getPager')
			.pagination(
					{
						pageSize : 10,// 每页显示的记录条数，默认为10
						pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
						beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
						afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
						displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
					});
}

//获取模板ID
function getTemplateID(){
	var templateIdStr = 100;
	$.ajax({
		type : "POST",
		url : "../site/manage-website!getCheckChooseTemp",
		dataType : "json",
		async:false,
		success : function(date) {
			var str =  eval(date);
				templateIdStr = str.templateId;
		},
		error : function(){
			templateIdStr = 0;
		}
	});
	return templateIdStr;
}

function addMenu() {
	$.ajax({
		type : "POST",
		url : "../site/manage-website!getCheckChooseTemp",
		dataType : "json",
		success : function(date) {
			var str =  eval(date);
			if(str.state == 1){
				$.messager.alert("消息","请选择模板");
				chooseTemplate();
			}else{
				$.ajax({
					url : "../site/manage-website!getMenu",
					dataType : "json",
					type : "post",
					success : function(date) {
						var str = eval(date);
						if (str.state == 0) {
							$("#addmenu").css("visibility", "visible");
							$('#addmenu').window('open');
							$('#addMenu').form('clear');
							$("#addMenu").css("visibility", "visible");
							$("#addMenu").show();
							$("#addMenu").appendTo('#eeXL');
							if(getTemplateID()==5){
								$("#tishi2").html("*图片大小：480*320");
							 }else{
								 $("#tishi2").html("*图片大小：50*50");
							 }
							
							$("#menuNo").html(str.message);
							document.getElementById("idmetaValueww").innerHTML = 
								"\<textarea id='menuName' name='menuName' cols='1' rows='10' class='ww'\>\</textarea\>";
							$("#menuName").val(select.menuName);
							kindEditor2();
							if(str.menuImageState == 0){
								$("#tr_menu_image").hide();
							}
							if(str.menuImageState == 1){
								$("#tr_menu_image").show();
								$('#addmenu').height(138);
							}
						} else {
							$.messager.alert("消息",str.message);
						}
					},
					error : function(date) {
						$.messager.alert("消息","网络异常");
					}
				});
			}
		},
		error : function() {
			$.messager.alert('消息', "失败");
			$('#tt').datagrid('reload');
		}
	});
	
}
function add() {
	name = "tt";
	if ($("#menuName").val() == "") {
		$.messager.alert("消息","请填写栏目名");
		return;
	} else {
		var opt = {
			url : "../site/manage-website!addMenu",
			success : function(text) {
				closeAddmenu();
				$.messager.alert('消息', text);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeAddmenu();
				$.messager.alert('消息', " 系统繁忙，请稍后再试...");
			}
		};
		$("#addMenu").ajaxSubmit(opt);
		
		
		
		/*$.ajax({
			type : "post",
			url : "../site/manage-website!addMenu",
			datatype : "json",
			data : '' + $("#addMenu").serialize(),
			success : function(date) {
				var str = eval(date);
				closeAddmenu();
				alert(str.message);
				$('#tt').datagrid('reload');
			},
			error : function(date) {
				alert("网络异常!");
			}
		});*/
	}
}
function closeAddmenu(){
	$('#addmenu').window('close');
}
function updateMenu(){
	$.ajax({
		type : "POST",
		url : "../site/manage-website!getCheckChooseTemp",
		dataType : "json",
		success : function(date) {
			var str =  eval(date);
			if(str.state == 1){
				$.messager.alert("消息","请选择模板");
				chooseTemplate();
			}else{
				var select = $('#tt').datagrid('getSelected');
				if (select) {
					$("#updatemenu").css("visibility", "visible");
					$('#updatemenu').window('open');
					$('#addMenu').form('clear');
					$("#addMenu").css("visibility", "visible");
					$("#addMenu").show();
					$("#addMenu").appendTo('#updateeeXL');
					
					$("#menuNo").html(select.menuNameStr);
					$("#menuId").val(select.menuId);
					$("#menuName").val(select.menuName);
					if(getTemplateID()==5){
						$("#tishi2").html("*图片大小：480*320");
					 }else{
						 $("#tishi2").html("*图片大小：50*50");
					 }
					document.getElementById("idmetaValueww").innerHTML = 
						"\栏目名：<textarea id='menuName' name='menuName' cols='1' rows='10' class='ww'\>\</textarea\>";
					$("#menuName").val(select.menuName);
					kindEditor2();
					if(select.menuImageState == 0){
						$("#tr_menu_image").hide();
					}
					if(select.menuImageState == 1){
						$("#tr_menu_image").show();
						$('#updatemenu').height(380);
					}
				}else{
					$.messager.alert("消息","请选择一行要修改的数据!");
				}
			}
		},
		error : function(){
			$.messager.alert("消息","网络异常！");
		}
	});
	
}
function closeUpdatemenu(){
	$('#updatemenu').window('close');
}
function update(){
	name = "tt";
	if ($("#menuName").val() == "") {
		$.messager.alert("消息","请填写栏目名");
		return;
	} else {
		var opt = {
			url : "../site/manage-website!updateMenu",
			success : function(text) {
				closeUpdatemenu();
				$.messager.alert('消息', text);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeUpdatemenu();
				$.messager.alert('消息', " 系统繁忙，请稍后再试...");
			}
		};
		$("#addMenu").ajaxSubmit(opt);
		/*$.ajax({
			type : "post",
			url : "../site/manage-website!updateMenu",
			datatype : "json",
			data : '' + $("#addMenu").serialize(),
			success : function(date) {
				var str = eval(date);
				closeAddmenu();
				alert(str.message);
				$('#tt').datagrid('reload');
				window.location.reload();
			},
			error : function(date) {
				alert("网络异常!");
			}
		});*/
	}
}
function tuijian(){
	$.ajax({
		type : "POST",
		url : "../site/manage-website!getCheckChooseTemp",
		dataType : "json",
		success : function(date) {
			var str =  eval(date);
			if(str.state == 1){
				$.messager.alert("消息","请选择模板");
				chooseTemplate();
			}else{
				var select = $('#tt').datagrid('getSelected');
				if (select) {
					$.ajax({
						type : "post",
						url : "../site/manage-website!tuijian",
						data :{
							'menuNameID' : select.menuNameID,
							'menuId' : select.menuId
						},
						datatype : "json",
						success : function (date){
							var str = eval(date);
							$.messager.alert('消息', str.message);
						},
						error : function (date){
							$.messager.alert('消息', "网络异常!");
						}
					});
				}else{
					$.messager.alert('消息',"请选择要推荐的栏目!");
				}
			}
		},
		error : function(){
			$.messager.alert('消息',"网络异常!");
		}
	});
}

function bannerUpload(){
	name = "bannerTt";
	$.ajax({
		url:"../site/manage-website!getBannerNum",
		type:"post",
		datatype:"json",
		data : {
			'menuNameID' : menuNameID
		},
		success : function(date){
			var str = eval(date);
			if(str.state == 0){
				$.messager.alert("消息",str.message);
			}else{
				$("#uploadbanner").css("visibility", "visible");
				$('#uploadbanner').window('open');
				$('#uploadBanner').form('clear');
				$("#uploadBanner").css("visibility", "visible");
				$("#uploadBanner").show();
				$("#uploadBanner").appendTo('#uploadeeXL');
				$("#nameStr").html("banner名");
				$("#nameValueStr").html("banner");
				if(getTemplateID()==2){
					$("#tishi").html("*banner建议：640*190");
				 }
				if(getTemplateID()==3){
					$("#tishi").html("*banner建议：640*320");
				 }
				$("#bannerName").html(str.message);
			}
		},
		error : function(date){
			$.messager.alert("消息","网络异常!");
		}
	});
}
function uploadBanner(){
	name = "bannerTt";
	if($("#optionValue").val()==""){
		$.messager.alert('消息',"请选择上传的图片!");
		return ;
	}
	var opt = {
		url : "../site/manage-website!uploadBanner",
		data : {
			'menuNameID' : menuNameID
		},
		success : function(text) {
			closeUploadBanner();
			$.messager.alert('消息', text);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			closeUploadBanner();
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#uploadBanner").ajaxSubmit(opt);
}

function closeUploadBanner(){
	$('#uploadbanner').window('close');
}
//查看banner
function banner(menuId,menuNameID){
	$.ajax({
		type : "POST",
		url : "../site/manage-website!getCheckChooseTemp",
		dataType : "json",
		success : function(date) {
			var str =  eval(date);
			if(str.state == 1){
				$.messager.alert('消息',"请选择模板");
				chooseTemplate();
			}else{
				window.menuId = menuId;
				window.menuNameID = menuNameID;
				/*this.menuId = menuId;
				this.menuNameID = menuNameID;*/
				$("#lan").css("visibility", "visible");
				$("#lan").window('open');
				$("#bannerToolbar").css("visibility", "visible");
				$('#bannerTt').datagrid(
								{
									fit : true,
									pagination : true,// 设置分页条显示
									pageSize : 10,
									pageList : [ 5, 10, 15, 20 ],
									nowrap : false,
									striped : true,
									collapsible : true,
									singleSelect : true,
									//onRowContextMenu:onRowContextMenu,
									url : '../site/manage-website!bannerEdit',
									queryParams : {
										menuId : menuId,
										menuNameID : menuNameID
									},
									loadMsg : '数据装载中......',
									sortName : 'code',
									sortOrder : 'desc',
									remoteSort : false,
									columns : [ [
											{
												title : 'banner名',
												field : 'optionNameStr',
												width : '255',
												align : 'center',
												visibility : 'hidden'
											},
											{
												title : 'banner',
												field : 'optionValue',
												width : '255',
												align : 'center',
												visibility : 'hidden'
											}] ],
									onClickRow : function(value, rec) {
										var str = $("#option").html();
										if (s_caocuo == "true") {
											str = $("#option").html().replace(
													"id=\"caozuo\"",
													"id=\"caozuo\" checked=\"checked\"");
										} else {
											str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
										}
										$("#option").html(str);
									},
									rownumbers : true,

									toolbar : "#bannerToolbar"
								});
					displayMsg1();
			}
		},
		error : function(){
			$.messager.alert("消息","网络异常");
		}
	});
}
function displayMsg1() {

	$('#bannerTt')
			.datagrid('getPager')
			.pagination(
					{
						pageSize : 10,// 每页显示的记录条数，默认为10
						pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
						beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
						afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
						displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
					});
}

function getUpdateBanner(){
	
	name = "bannerTt";
	var select = $('#bannerTt').datagrid('getSelected');
	if (select) {
		var templateIDstr = getTemplateID();
		$("#updatebanner").css("visibility", "visible");
		$('#updatebanner').window('open');
		$('#uploadBanner').form('clear');
		$("#uploadBanner").css("visibility", "visible");
		$("#uploadBanner").show();
		$("#uploadBanner").appendTo('#uploadBannereeXL');
		$("#bannerName").html(select.optionNameStr);
		$("#nameStr").html("banner名");
		$("#nameValueStr").html("banner");
		if(templateIDstr==2){
			$("#tishi").html("*banner建议：640*190");
		 }
		if(templateIDstr==3){
			$("#tishi").html("*banner建议：640*320");
		 }
		$("#optionId").val(select.optionId);
	}else{
		$.messager.alert('消息',"请选择要修改的数据!");
	}
}
function closeUpdateBanner(){
	$("#updatebanner").window("close");
}
function updateBanner(){
	name = "bannerTt";
	if($("#optionValue").val()==""){
		$.messager.alert('消息',"请选择上传的图片!");
		return ;
	}
	var opt = {
		url : "../site/manage-website!updateBanner?optionId = "+ $("#optionId").val(),
		success : function(text) {
			closeUpdateBanner();
			$.messager.alert('消息', text);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			closeUpdateBanner();
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#uploadBanner").ajaxSubmit(opt);
}
var s_caocuo = "false";
function selectAll() {
	var checks = document.getElementsByName("xuanze");
	// 测试全选
	var str = $("#option").html();
	// alert(s_caocuo);
	// alert(str.indexOf("checked"));//值为-1
	if (s_caocuo == "true") {
		s_caocuo = "false";
		str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
	} else {
		s_caocuo = "true";
		str = $("#option").html().replace("id=\"caozuo\"",
				"id=\"caozuo\" checked=\"checked\"");

	}
	$("#option").html(str);
	if (str.indexOf("checked") != -1 || str.indexOf("CHECKED") != -1) {
		// checks2[0].checked=true;
		for ( var i = 0; i < checks.length; i++) {
			// alert("已经选择");
			checks[i].checked = true;
		}

	} else {
		for ( var i = 0; i < checks.length; i++) {
			// alert("为选择");
			checks[i].checked = false;
		}
	}
}
// 设置子栏目
function childMenu(){
	$.ajax({
		type : "POST",
		url : "../site/manage-website!getCheckChooseTemp",
		dataType : "json",
		success : function(date) {
			var str =  eval(date);
			if(str.state == 1){
				$.messager.alert('消息',"请选择模板");
				chooseTemplate();
			}else{
				var select = $('#tt').datagrid('getSelected');
				if (select) {
					$.ajax({
						type : "post",
						url : "../site/manage-website!childMenu",
						data :{
							'menuNameID' : select.menuNameID,
							'menuId' : select.menuId
						},
						datatype : "json",
						success : function (date){
							var str = eval(date);
							if(str.state == 0){
								$.messager.alert('消息', str.message);
							}else{
								childMenuList(select.menuId,select.menuName);
							}
							
						},
						error : function (date){
							$.messager.alert('消息', "网络异常!");
						}
					});
				}else{
					$.messager.alert('消息', "请选择要设置的栏目!");
				}
			}
		},
		error : function(){
			$.messager.alert('消息', "网络异常！");
		}
	});
			
}

function childMenuList(menuId,menuName){
	$("#parentMenuID").val(menuId);
	$("#parentMenuName").val(menuName);
	$("#laChildMenu").css("visibility", "visible");
	$("#laChildMenu").window('open');
	$('#childMenuTt').datagrid(
					{
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						//onRowContextMenu:onRowContextMenu,
						url : '../site/manage-website!childMenuEdit',
						queryParams : {
							menuId : menuId
						},
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						columns : [ [
								
								{
									title : '子栏目名',
									field : 'menuName',
									width : '530',
									align : 'center',
									visibility : 'hidden'
								}] ],
						onClickRow : function(value, rec) {
							var str = $("#option").html();
							if (s_caocuo == "true") {
								str = $("#option").html().replace(
										"id=\"caozuo\"",
										"id=\"caozuo\" checked=\"checked\"");
							} else {
								str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
							}
							$("#option").html(str);
						},
						rownumbers : true,

						toolbar : "#childMenuToolbar"
					});
	childMenuDisplayMsg();
}
function childMenuDisplayMsg() {

	$('#childMenuTt').datagrid('getPager').pagination(
	{
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}

//添加广告
function advertUpload(){
	name = "advertTt";
	$.ajax({
		url:"../site/manage-website!getAdvertNum",
		type:"post",
		data :{
			'menuNameID' : menuNameID,
			'menuId' : menuId
		},
		datatype:"json",
		success : function(date){
			var str = eval(date);
			if(str.state == 0){
				$.messager.alert('消息',str.message);
			}else{
				$("#uploadadvert").css("visibility", "visible");
				$('#uploadadvert').window('open');
				$('#uploadBanner').form('clear');
				$("#uploadBanner").css("visibility", "visible");
				$("#uploadBanner").show();
				$("#uploadBanner").appendTo('#uploadAdverteeXL');
				$("#nameStr").html("广告名");
				$("#nameValueStr").html("广告");
				if(getTemplateID()==1||getTemplateID()==3){
					$("#tishi").html("*广告建议：640*480");
				 }
				if(getTemplateID()==2||getTemplateID()==4||getTemplateID()==5){
					$("#tishi").html("*广告建议：640*300");
				 }
				$("#bannerName").html(str.message);
				$("#tr_url").show();
			}
		},
		error : function(date){
			$.messager.alert('消息',"网络异常!");
		}
	});
}
function closeUploadAdvert(){
	$("#uploadadvert").window('close');
}
function uploadAdvert(){
	name = "advertTt";
	if($("#optionValue").val()==""){
		$.messager.alert('消息',"请选择上传的图片!");
		return ;
	}
	var opt = {
		url : "../site/manage-website!uploadAdvert",
		data :{
			'menuNameID' : menuNameID,
			'menuId' : menuId
		},
		success : function(text) {
			closeUploadBanner();
			$.messager.alert('消息', text);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			closeUploadBanner();
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#uploadBanner").ajaxSubmit(opt);
}
function closeUpdateAdvert(){
	$("#updateadvert").window("close");
}
function advert(menuId,menuNameID){
	$.ajax({
		type : "POST",
		url : "../site/manage-website!getCheckChooseTemp",
		dataType : "json",
		success : function(date) {
			var str =  eval(date);
			if(str.state == 1){
				$.messager.alert('消息',"请选择模板");
				chooseTemplate();
			}
			if(str.state == 0){
				this.menuId = menuId;
				this.menuNameID = menuNameID;
				$("#la").css("visibility", "visible");
				$("#la").window('open');
				$("#advertToolbar").css("visibility", "visible");
				$('#advertTt').datagrid(
								{
									fit : true,
									pagination : true,// 设置分页条显示
									pageSize : 10,
									pageList : [ 5, 10, 15, 20 ],
									nowrap : false,
									striped : true,
									collapsible : true,
									singleSelect : true,
									//onRowContextMenu:onRowContextMenu,
									url : '../site/manage-website!advertEdit',
									queryParams : {
										menuId : menuId,
										menuNameID : menuNameID
									},
									loadMsg : '数据装载中......',
									sortName : 'code',
									sortOrder : 'desc',
									remoteSort : false,
									columns : [ [
											{
												title : '广告名',
												field : 'optionNameStr',
												width : '255',
												align : 'center',
												visibility : 'hidden'
											},
											{
												title : '广告',
												field : 'optionValue',
												width : '255',
												align : 'center',
												visibility : 'hidden'
											}] ],
									onClickRow : function(value, rec) {
										var str = $("#option").html();
										if (s_caocuo == "true") {
											str = $("#option").html().replace(
													"id=\"caozuo\"",
													"id=\"caozuo\" checked=\"checked\"");
										} else {
											str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
										}
										$("#option").html(str);
									},
									rownumbers : true,

									toolbar : "#advertToolbar"
								});
					displayAdvertMsg2();
			}
		},
		error : function(){
		}
	});
}
function displayAdvertMsg2() {

	$('#advertTt')
			.datagrid('getPager')
			.pagination(
					{
						pageSize : 10,// 每页显示的记录条数，默认为10
						pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
						beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
						afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
						displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
					});
}

function getUpdateAdvert(){
	name = "advertTt";
	var select = $('#advertTt').datagrid('getSelected');
	if (select) {
		$("#updateadvert").css("visibility", "visible");
		$('#updateadvert').window('open');
		$('#uploadBanner').form('clear');
		$("#uploadBanner").css("visibility", "visible");
		$("#uploadBanner").show();
		$("#uploadBanner").appendTo('#updateAdverteeXL');
		$("#bannerName").html(select.optionNameStr);
		$("#nameStr").html("广告名");
		$("#nameValueStr").html("广告");
		if(getTemplateID()==1||getTemplateID()==3){
			$("#tishi").html("*广告建议：640*480");
		 }
		if(getTemplateID()==2||getTemplateID()==4||getTemplateID()==5){
			$("#tishi").html("*广告建议：640*300");
		 }
		$("#optionId").val(select.optionId);
		$("#url").val(select.url);
	}else{
		$.messager.alert('消息',"请选择要修改的数据!");
	}
}

function updateAdvert(){
	name = "advertTt";
	if($("#optionValue").val()==""){
		$.messager.alert('消息',"请选择上传的图片!");
		return ;
	}
	var opt = {
		url : "../site/manage-website!updateAdvert?optionId = "+ $("#optionId").val(),
		success : function(text) {
			closeUpdateBanner();
			$.messager.alert('消息', text);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			closeUpdateBanner();
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#uploadBanner").ajaxSubmit(opt);
}




//获取logo
function logo(){
	$.ajax({
		type : "POST",
		url : "../site/manage-website!getCheckChooseTemp",
		dataType : "json",
		success : function(date) {
			var str =  eval(date);
			if(str.state == 1){
				$.messager.alert('消息',"请选择模板");
				chooseTemplate();
			}else{
				$("#laLogo").css("visibility", "visible");
				$("#laLogo").window('open');
				$("#logoTt").datagrid({
					fit : true,
					pagination : true,// 设置分页条显示
					pageSize : 10,
					pageList : [ 5, 10, 15, 20 ],
					nowrap : false,
					striped : true,
					collapsible : true,
					singleSelect : true,
					//onRowContextMenu:onRowContextMenu,
					url : '../site/manage-website!logoEdit',
					loadMsg : '数据装载中......',
					sortName : 'code',
					sortOrder : 'desc',
					remoteSort : false,
					columns : [ [
							{
								title : 'Logo',
								field : 'optionNameStr',
								width : '255',
								align : 'center',
								visibility : 'hidden'
							},
							{
								title : 'Logo',
								field : 'optionValue',
								width : '255',
								align : 'center',
								visibility : 'hidden'
							}] ],
					onClickRow : function(value, rec) {
						var str = $("#option").html();
						if (s_caocuo == "true") {
							str = $("#option").html().replace(
									"id=\"caozuo\"",
									"id=\"caozuo\" checked=\"checked\"");
						} else {
							str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
						}
						$("#option").html(str);
					},
					rownumbers : true,

					toolbar : "#logoToolbar"
				});
				displayLogoMsg();
			}
		},
		error : function(){
			$.messager.alert("消息","网络异常!");
		}
	});
}
function displayLogoMsg() {

	$('#logoTt').datagrid('getPager').pagination(
	{
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}

function getUploadLogo(){
	name = "logoTt";
	$.ajax({
		url:"../site/manage-website!getLogoNum",
		type:"post",
		datatype:"json",
		success : function(date){
			var str = eval(date);
			if(str.state == 0){
				$.messager.alert('消息',str.message);
			}else{
				$("#uploadlogo").css("visibility", "visible");
				$('#uploadlogo').window('open');
				$('#uploadBanner').form('clear');
				$("#uploadBanner").css("visibility", "visible");
				$("#uploadBanner").show();
				$("#uploadBanner").appendTo('#uploadLogoeeXL');
				$("#nameStr").html("Logo");
				$("#nameValueStr").html("Logo");
				$("#tishi").html("*logo建议：144*34");
				$("#bannerName").html(str.message);
			}
		},
		error : function(date){
			$.messager.alert('消息',"网络异常!");
		}
	});
}

function getUpdateLogo(){
	name = "logoTt";
	var select = $('#logoTt').datagrid('getSelected');
	if (select) {
		$("#updatelogo").css("visibility", "visible");
		$('#updatelogo').window('open');
		$('#uploadBanner').form('clear');
		$("#uploadBanner").css("visibility", "visible");
		$("#uploadBanner").show();
		$("#uploadBanner").appendTo('#updateLogoeeXL');
		$("#bannerName").html(select.optionNameStr);
		$("#nameStr").html("Logo名");
		$("#nameValueStr").html("Logo");
		$("#tishi").html("*logo建议：144*34");
		$("#optionId").val(select.optionId);
	}else{
		$.messager.alert('消息',"请选择要修改的数据!");
	}
}

function closeUploadLogo(){
	$("#uploadlogo").window('close');
}

function closeUpdateLogo(){
	$("#updatelogo").window('close');
}

function uploadLogo(){
	name = "logoTt";
	if($("#optionValue").val()==""){
		$.messager.alert('消息',"请选择上传的图片!");
		return ;
	}
	var opt = {
		url : "../site/manage-website!upLoadLogo",
		success : function(text) {
			closeUploadLogo();
			$.messager.alert('消息', text);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			closeUploadLogo();
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#uploadBanner").ajaxSubmit(opt);
}

function updateLogo(){
	name = "logoTt";
	if($("#optionValue").val()==""){
		$.messager.alert('消息',"请选择上传的图片!");
		return ;
	}
	var opt = {
		url : "../site/manage-website!updateLogo?optionId = "+ $("#optionId").val(),
		success : function(text) {
			closeUpdateLogo();
			$.messager.alert('消息', text);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			closeUpdateLogo();
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#uploadBanner").ajaxSubmit(opt);
}

function getAddChildMenu(){
	$("#addchildMenu").css("visibility", "visible");
	$('#addchildMenu').window('open');
	$('#addMenu').form('clear');
	$("#addMenu").css("visibility", "visible");
	$("#addMenu").show();
	$("#addMenu").appendTo('#addchildMenueeXL');
	$("#menuNo").html($("#parentMenuName").val());
	$("#menuText").html("子");
}

function addChildMenu(){
	$("#menuId").val($("#parentMenuID").val());
	if ($("#menuName").val() == "") {
		$.messager.alert('消息',"请填写子栏目名");
		return;
	} else {
		$.ajax({
			type : "post",
			url : "../site/manage-website!addChildMenu",
			datatype : "json",
			data : '' + $("#addMenu").serialize(),
			success : function(date) {
				var str = eval(date);
				closeAddmenu();
				$("#addchildMenu").window('close');
				$.messager.alert('消息', str.message);
				$('#childMenuTt').datagrid('reload');
			},
			error : function(date) {
				$.messager.alert('消息',"网络异常!");
			}
		});
	}
}

function getUpdateChildMenu(){
	var select = $('#childMenuTt').datagrid('getSelected');
	if(select){
		$("#updatechildMenu").css("visibility", "visible");
		$('#updatechildMenu').window('open');
		$('#addMenu').form('clear');
		$("#addMenu").css("visibility", "visible");
		$("#addMenu").show();
		$("#addMenu").appendTo('#updatechildMenueeXL');
		$("#menuNo").html(select.menuNameStr);
		$("#menuId").val(select.menuId);
		$("#menuName").val(select.menuName);
		$("#menuText").html("子");
		$("#menuNo").html($("#parentMenuName").val());
	}else{
		$.messager.alert('消息',"请选择要修改的数据!");
	}
}

function closeAddChildMenu(){
	$("#addchildMenu").window('close');
}

function closeUpdateChileMenu(){
	$("#updatechildMenu").window('close');
}

function updateChileMenu(){
	if ($("#menuName").val() == "") {
		$.messager.alert('消息',"请填写栏目名");
		return;
	} else {
		name = "childMenuTt";
		var opt = {
			url : "../site/manage-website!updateMenu",
			success : function(text) {
				closeUpdateChileMenu();
				$.messager.alert('消息', text);
				$('#childMenuTt').datagrid('reload');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeUpdateChileMenu();
				$.messager.alert('消息', " 系统繁忙，请稍后再试...");
			}
		};
		$("#addMenu").ajaxSubmit(opt);
		
		
		
		/*$.ajax({
			type : "post",
			url : "../site/manage-website!updateMenu",
			datatype : "json",
			data : '' + $("#addMenu").serialize(),
			success : function(date) {
				var str = eval(date);
				closeUpdateChileMenu();
				$.messager.alert('消息',str.message);
				$('#childMenuTt').datagrid('reload');
			},
			error : function(date) {
				$.messager.alert('消息',"网络异常!");
			}
		});*/
	}
}
function getdeleteChildMenu(){
	var select = $('#childMenuTt').datagrid('getSelected');
	if(select){
		$.messager.confirm('消息', '确认删除么?', function(id) {
			$.ajax({
				type : "post",
				url : "../site/manage-website!deletMenu",
				datatype : "json",
				data : {
					'menuId' : select.menuId
				},
				success : function(date) {
					var str = eval(date);
					$.messager.alert('消息',str.message);
					$('#childMenuTt').datagrid('reload');
				},
				error : function(date) {
					$.messager.alert('消息',"网络异常!");
				}
			});
		});
	}else{
		$.messager.alert('消息',"请选择要删除的数据!");
	}
}

function seeMenu(){
	$.ajax({
		type : "POST",
		url : "../site/manage-website!getCheckChooseTemp",
		dataType : "json",
		success : function(date) {
			var str =  eval(date);
			if(str.state == 1){
				$.messager.alert('消息',"请选择模板");
				chooseTemplate();
			}else{
				var select = $('#tt').datagrid('getSelected');
				if (select) {
					menuId = select.menuId;
					menuNameID = select.menuNameID;
					showMenu(select.menuId,select.menuNameID);
				}else{
					$.messager.alert('消息',"请选择要查看的数据!");
				}
			}
		},
		error : function(){
			$.messager.alert('消息',"网络异常!");
		}
	});
}
//根据当前栏目判断要显示的内容,templateMenuType 获取模板栏目属性
function showMenu(menuId,menuNameID){
	$.ajax({
		type : "post",
		url : "../site/manage-website!chooseShow",
		data : {
			'menuNameID' : menuNameID
		},
		datatype : "json",
		success : function (date){
			var str = eval(date);
			$.ajax({
				type : "post",
				url : "../site/manage-website!checkTyepMenu",
				data : {
					'menuNameID' : menuNameID
				},
				data : {
					'templateTypeMenuId' : str.templateTypeMenuId
				},
				datatype : "json",
				success : function (d){
					var templatePageProperty = eval(d);
					if(str.menuTypeId == 0){
						if(templatePageProperty.title == 1 && templatePageProperty.textboxNum ==0 && templatePageProperty.bigImageNum==0 && templatePageProperty.compilerNum == 1){
							showNullImagePage(menuId,menuNameID);
						}else{
							showPage(menuId,menuNameID);
						}
						
					}
					if(str.menuTypeId == 1){
						if(str.parseNum != 0){
							showChildPage(menuId,menuNameID);
						} else if(templatePageProperty.title == 0 && templatePageProperty.textboxNum ==0 && templatePageProperty.bigImageNum==1  && templatePageProperty.compilerNum == 0){
							//&& templatePageProperty.advertisingNum == 0
							showImagePage(menuId,menuNameID);
						}
						else if(templatePageProperty.title == 1 && templatePageProperty.textboxNum ==1 && templatePageProperty.bigImageNum==1 && templatePageProperty.compilerNum == 1){
							//&& templatePageProperty.advertisingNum == 0 
							showTxexBoxPage(menuId,menuNameID);
						}else if(templatePageProperty.title == 1 && templatePageProperty.textboxNum ==0 && templatePageProperty.bigImageNum==0 && templatePageProperty.compilerNum == 1){
							showNullImagePage(menuId,menuNameID);
						}else if(str.parseNum != 0){
							showChildPage(menuId,menuNameID);
						}else {
							showPage(menuId,menuNameID);
						}
					}
					if(str.menuTypeId == 2){
						showTelPage(menuId,menuNameID);
					}
					if(str.menuTypeId == 3){
						showNullPage(menuId,menuNameID);
					}
					
				},
				error :function (date){
					$.messager.alert("消息","网络异常,请稍后再试!");
				}
			});
			
		},
		error :function (date){
			$.messager.alert("消息","网络异常,请稍后再试!");
		}
	});
}

function showNullImagePage(menuId,menuNameID){
	name ="pageTt";
	$("#laPage").css("visibility", "visible");
	$("#laPage").window('open');
	$("#pageTt").datagrid({
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		//onRowContextMenu:onRowContextMenu,
		url : '../site/manage-website!pageEdit',
		queryParams : {
			menuId : menuId,
			menuNameID : menuNameID
		},
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [
				{
					title : '栏目名',
					field : 'menuName',
					width : '150',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '标题',
					field : 'pageTitle',
					width : '200',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '内容',
					field : 'metaValue',
					width : '410',
					align : 'center',
					visibility : 'hidden'
				}] ],
		onClickRow : function(value, rec) {
			var str = $("#option").html();
			if (s_caocuo == "true") {
				str = $("#option").html().replace(
						"id=\"caozuo\"",
						"id=\"caozuo\" checked=\"checked\"");
			} else {
				str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
			}
			$("#option").html(str);
		},
		rownumbers : true,

		toolbar : "#pageToolbar"
	});
	displayPageMsg();
}

function showNullPage(menuId,menuNameID){
	name ="pageTt";
	$("#laPage").css("visibility", "visible");
	$("#laPage").window('open');
	$("#pageTt").datagrid({
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		//onRowContextMenu:onRowContextMenu,
		url : '../site/manage-website!pageEdit',
		queryParams : {
			menuId : menuId,
			menuNameID : menuNameID
		},
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [
				{
					title : '栏目名',
					field : 'menuName',
					width : '120',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '联系人',
					field : 'msgContacts',
					width : '120',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '联系方式',
					field : 'msgWay',
					width : '170',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '留言内容',
					field : 'msgContent',
					width : '380',
					align : 'center',
					visibility : 'hidden'
				}] ],
		onClickRow : function(value, rec) {
			var str = $("#option").html();
			if (s_caocuo == "true") {
				str = $("#option").html().replace(
						"id=\"caozuo\"",
						"id=\"caozuo\" checked=\"checked\"");
			} else {
				str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
			}
			$("#option").html(str);
		},
		rownumbers : true,

		toolbar : "#pageToolbar"
	});
	displayPageMsg();
}


//
function showChildPage(menuId,menuNameID){
	name ="pageTt";
	$("#laPage").css("visibility", "visible");
	$("#laPage").window('open');
	$("#pageTt").datagrid({
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		//onRowContextMenu:onRowContextMenu,
		url : '../site/manage-website!pageEdit',
		queryParams : {
			menuId : menuId,
			menuNameID : menuNameID
		},
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [
				{
					title : '栏目名',
					field : 'menuName',
					width : '120',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '子栏目',
					field : 'childMenuName',
					width : '120',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '标题',
					field : 'pageTitle',
					width : '170',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '内容',
					field : 'metaValue',
					width : '380',
					align : 'center',
					visibility : 'hidden'
				}] ],
		onClickRow : function(value, rec) {
			var str = $("#option").html();
			if (s_caocuo == "true") {
				str = $("#option").html().replace(
						"id=\"caozuo\"",
						"id=\"caozuo\" checked=\"checked\"");
			} else {
				str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
			}
			$("#option").html(str);
		},
		rownumbers : true,

		toolbar : "#pageToolbar"
	});
	displayPageMsg();
}

function showPage(menuId,menuNameID){
	name ="pageTt";
	$("#laPage").css("visibility", "visible");
	$("#laPage").window('open');
	$("#pageTt").datagrid({
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		//onRowContextMenu:onRowContextMenu,
		url : '../site/manage-website!pageEdit',
		queryParams : {
			menuId : menuId,
			menuNameID : menuNameID
		},
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [
				{
					title : '栏目名',
					field : 'menuName',
					width : '120',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '标题',
					field : 'pageTitle',
					width : '170',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '内容',
					field : 'metaValue',
					width : '300',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '文章配图',
					field : 'metaImageValue',
					width : '200',
					align : 'center',
					visibility : 'hidden'
				}] ],
		onClickRow : function(value, rec) {
			var str = $("#option").html();
			if (s_caocuo == "true") {
				str = $("#option").html().replace(
						"id=\"caozuo\"",
						"id=\"caozuo\" checked=\"checked\"");
			} else {
				str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
			}
			$("#option").html(str);
		},
		rownumbers : true,

		toolbar : "#pageToolbar"
	});
	displayPageMsg();
}

function displayPageMsg() {

	$('#pageTt').datagrid('getPager').pagination(
	{
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}

// 只有图片 无标题
function showImagePage(menuId,menuNameID){
	name = "pageTt";
	$("#laPage").css("visibility", "visible");
	$("#laPage").window('open');
	$("#pageTt").datagrid({
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../site/manage-website!pageEdit',
		queryParams : {
			menuId : menuId,
			menuNameID : menuNameID
		},
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [
				{
					title : '栏目名',
					field : 'menuName',
					width : '250',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '内容',
					field : 'metaImageValue',
					width : '550',
					align : 'center',
					visibility : 'hidden'
				}] ],
		onClickRow : function(value, rec) {
			var str = $("#option").html();
			if (s_caocuo == "true") {
				str = $("#option").html().replace(
						"id=\"caozuo\"",
						"id=\"caozuo\" checked=\"checked\"");
			} else {
				str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
			}
			$("#option").html(str);
		},
		rownumbers : true,

		toolbar : "#pageToolbar"
	});
	displayPageMsg();
}
//标题，概述，配图，详细信息
function showTxexBoxPage(menuId,menuNameID){
	name = "pageTt";
	$("#laPage").css("visibility", "visible");
	$("#laPage").window('open');
	$("#pageTt").datagrid({
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		//onRowContextMenu:onRowContextMenu,
		url : '../site/manage-website!pageEdit',
		queryParams : {
			menuId : menuId,
			menuNameID : menuNameID
		},
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [
				{
					title : '栏目名',
					field : 'menuName',
					width : '80',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '标题',
					field : 'pageTitle',
					width : '120',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '概述',
					field : 'metaDetail',
					width : '150',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '内容',
					field : 'metaValue',
					width : '200',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '文章配图',
					field : 'metaImageValue',
					width : '200',
					align : 'center',
					visibility : 'hidden'
				}] ],
		onClickRow : function(value, rec) {
			var str = $("#option").html();
			if (s_caocuo == "true") {
				str = $("#option").html().replace(
						"id=\"caozuo\"",
						"id=\"caozuo\" checked=\"checked\"");
			} else {
				str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
			}
			$("#option").html(str);
		},
		rownumbers : true,

		toolbar : "#pageToolbar"
	});
	displayPageMsg();
}
//联系我们
function showTelPage(menuId,menuNameID){
	name = "pageTt";
	$("#laPage").css("visibility", "visible");
	$("#laPage").window('open');
	$("#pageTt").datagrid({
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		//onRowContextMenu:onRowContextMenu,
		url : '../site/manage-website!pageEdit',
		queryParams : {
			menuId : menuId,
			menuNameID : menuNameID
		},
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [
				{
					title : '栏目名',
					field : 'menuName',
					width : '80',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '标题',
					field : 'pageTitle',
					width : '100',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '公司地址',
					field : 'address',
					width : '120',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '热线电话',
					field : 'hotline',
					width : '80',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '手机',
					field : 'telephone',
					width : '70',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '座机',
					field : 'landline',
					width : '50',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : 'EMAIL',
					field : 'email',
					width : '100',
					align : 'center',
					visibility : 'hidden'
				},
				{
					title : '地理位置',
					field : 'localtion',
					width : '200',
					align : 'center',
					visibility : 'hidden'
				}] ],
		onClickRow : function(value, rec) {
			var str = $("#option").html();
			if (s_caocuo == "true") {
				str = $("#option").html().replace(
						"id=\"caozuo\"",
						"id=\"caozuo\" checked=\"checked\"");
			} else {
				str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
			}
			$("#option").html(str);
		},
		rownumbers : true,

		toolbar : "#pageToolbar"
	});
	displayPageMsg();
}


function getAddPage(){
	$.ajax({
		type : "post",
		url : "../site/manage-website!checkAdd",
		data : {
			'menuNameID' : menuNameID,
			'menuId' : menuId
		},
		datatype : "json",
		success : function (date){
			var str = eval(date);
			if(str.state == 0){
				$.messager.alert('消息', str.message);
				return;
			}
			$.ajax({
				type : "post",
				url : "../site/manage-website!chooseShow",
				data : {
					'menuNameID' : menuNameID
				},
				datatype : "json",
				success : function (date){
					var str = eval(date);
					if(str.menuTypeId == 0){
						checkTyepMenu(menuNameID,str);
					}
					if(str.menuTypeId == 1){
						checkTyepMenu(menuNameID,str);
					}
					if(str.menuTypeId == 2){
						checkTyepMenu(menuNameID,str);
					}
				},
				error :function (date){
					$.messager.alert('消息', "网络异常");
				}
			});
		},
		error : function (date){
			$.messager.alert('消息', "网络异常");
		}
	});
}

function checkTyepMenu(menuNameID,str){
	$.ajax({
		type : "post",
		url : "../site/manage-website!checkTyepMenu",
		data : {
			'menuNameID' : menuNameID
		},
		data : {
			'templateTypeMenuId' : str.templateTypeMenuId
		},
		datatype : "json",
		success : function (date){
			if(str.menuTypeId == 0){
				showAddPageForm(eval(date),str);
			}
			if(str.menuTypeId == 1){
				showAddPageForm(eval(date),str);
			}
			if(str.menuTypeId == 2){
				showAddTelPage(menuNameID,eval(date));
			}
			if(str.parseNum == 1){
				ChildName(menuId);
			}
		},
		error : function (date){
			$.messager.alert('消息', "网络异常");
		}
	});
}

function showAddPageForm(str,templateProperty){
	$('#addPage').height(79);
	$("#addPage").css("visibility", "visible");
	$('#addPage').window('open');
	$('#pageform').form('clear');
	$("#pageform").css("visibility", "visible");
	$("#pageform").show();
	$("#pageform").appendTo('#addPageeeXL');
	if(str.title == 0){
		$("#tr1").hide();
		$("#pageTitle").val("  ");
		//$('#addPage').height($('#addPage').height()-$('#tr1').height());
	}else{
		$("#tr1").show();
		$('#addPage').height($('#addPage').height()+29);
	}
	if(str.textboxNum ==1){
		$("#tr2").show();
		$('#addPage').height($('#addPage').height()+78);
	}else{
		$("#tr2").hide();
		$("#metaDetail").val("  ");
		//$('#addPage').height($('#addPage').height()-$('#tr3').height());
	}
	
	if(str.compilerNum == 0){
		$("#tr3").hide();
		$("#metaValue").val("  ");
		//$('#addPage').height($('#addPage').height()-$('#tr2').height()-$('#addPage').height()*0.2);
	}else{
		$("#tr3").show();
		$('#addPage').height($('#addPage').height()+215);
		document.getElementById("idmetaValue").innerHTML = 
			   "\<textarea id='metaValue' name='metaValue' cols='50' rows='10' class='ww'\>\</textarea\>";
		//metaValue();
		kindEditor();
	}
	if(str.bigImageNum == 1){
		$("#tr4").show();
		$("#pageFile").html("<input type=\"file\" name=\"bigImage\" id=\"bigImage\"/>");
		$("#pageFileName").html("配&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图");
		$('#addPage').height($('#addPage').height()+24);
	}else{
		$("#tr4").hide();
		$("#bigImage").val(null);
	}
	if(templateProperty.parseNum == 1){
		$("#tr5").show();
		$('#addPage').height($('#addPage').height()+24);
	}else{
		$("#tr5").hide();
	}
}
function closeAddPage(){
	$("#addPage").window('close');
}

function showAddTelPage(menuNameID,str){
	$('#addTelPage').height(240);
	$("#addTelPage").css("visibility", "visible");
	$('#addTelPage').window('open');
	$('#telPageform').form('clear');
	$("#telPageform").css("visibility", "visible");
	$("#telPageform").show();
	$("#telPageform").appendTo('#addTelPageeeXL');
	if(str.title == 0){
		$("#telTr1").hide();
		$("#touchPageTitle").val("  ");
		//$('#addPage').height($('#addPage').height()-$('#tr1').height());
	}else{
		$("#telTr1").show();
		$('#addTelPage').height($('#addTelPage').height()+29);
	}
	if(str.bigImageNum == 0){
		$("#telTr2").hide();
		$("#touchPageTitle").val("  ");
		//$('#addPage').height($('#addPage').height()-$('#tr1').height());
	}else{
		$("#telTr2").show();
		$('#addTelPage').height($('#addTelPage').height()+29);
	}
}
function closeAddTelPage(){
	$("#addTelPage").window('close');
}

function updateTelPage(){
	var opt = {
		url : "../site/manage-website!updateTelPage?menuNameID="+menuNameID,
		success : function(text) {
			closeUpdatePage();
			$.messager.alert('消息', "修改成功");
			$('#'+name).datagrid('reload');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			closeUpdatePage();
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#telPageform").ajaxSubmit(opt);
}

function addPage(){
	if($("#pageTitle").val()==""){
		$.messager.alert("消息","请填写文章标题!");
		return ;
	}
	if($("#metaDetail").val()==""){
		$.messager.alert("消息","请添加文章概述!");
	}
	if($("#metaValue").val()==""){
		$.messager.alert("消息","请填写详细内容!");
		return ;
	}
	if($("#bigImage").val()==""){
		$.messager.alert("消息","请上传文章配图!");
		return ;
	}
	var opt = {
		url : "../site/manage-website!addPage?menuId="+menuId,
		success : function(text) {
			$.messager.alert('消息', "添加成功");
			$('#'+name).datagrid('reload');
			$('#addPage').window('close');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#addPage').window('close');
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#pageform").ajaxSubmit(opt);
}


function metaValue(){
	new tqEditor(
		'metaValue',
		{
			toolbar : 'full'
		}
	);
}
function addTelPage(){
	var opt = {
		url : "../site/manage-website!addTelPage?menuId="+menuId,
		success : function(text) {
			closeAddTelPage();
			$.messager.alert('消息', text);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			closeAddTelPage();
			$.messager.alert('消息', " 系统繁忙，请稍后再试...");
		}
	};
	$("#telPageform").ajaxSubmit(opt);
}

function showUpateTelPageForm(str,select){
	$('#updateTelPage').height(240);
	$("#updateTelPage").css("visibility", "visible");
	$('#updateTelPage').window('open');
	$('#telPageform').form('clear');
	$("#telPageform").css("visibility", "visible");
	$("#telPageform").show();
	$("#telPageform").appendTo('#updateTelPageeeXL');
	if(str.title == 0){
		$("#telTr1").hide();
		//$('#addPage').height($('#addPage').height()-$('#tr1').height());
	}else{
		$("#telTr1").show();
		$('#updateTelPage').height($('#updateTelPage').height()+29);
	}
	if(str.bigImageNum == 0){
		$("#telTr2").hide();
		$("#touchPageTitle").val("  ");
		//$('#addPage').height($('#addPage').height()-$('#tr1').height());
	}else{
		$("#telTr2").show();
		$('#updateTelPage').height($('#updateTelPage').height()+29);
	}
	$("#touchPageTitle").val(select.pageTitle);
	$("#address").val(select.address);
	$("#hotline").val(select.hotline);
	$("#landline").val(select.landline);
	$("#telephone").val(select.telephone);
	$("#email").val(select.email);
	$("#contacts").val(select.contacts);
	$("#touchPageID").val(select.pageId);
}



function getUpdatePage(){
	var select = $("#pageTt").datagrid("getSelected");
	if(getTemplateID()==4||getTemplateID()==5){
		$("#tishi1").text("*配图建议：640*320");
		if(getTemplateID()==5&&menuNameID==1){
			$("#tishi1").text("*配图建议：440*320");
		}
		if(getTemplateID()==5&&menuNameID==2){
			$("#tishi1").text("*配图建议：440*320");
		}
		if(getTemplateID()==5&&menuNameID==3){
			$("#tishi1").text("*配图建议：440*320");
		}
	 }
	else{
		 $("#tishi1").text("*配图建议：150*150");
	 }
	if(select){
		$.ajax({
			type : "post",
			url : "../site/manage-website!chooseShow",
			data : {
				'menuNameID' : menuNameID
			},
			datatype : "json",
			success : function (date){
				var str = eval(date);
				if(str.menuTypeId == 0){
					//alert("00000");
					upCheckTyepMenu(menuNameID,str,select);
				}
				if(str.menuTypeId == 1){
					//alert("111111");
					if(getTemplateID()==1||getTemplateID()==2){
						$("#tishi1").text("*配图建议：640*320");
					}
					upCheckTyepMenu(menuNameID,str,select);
				}
				if(str.menuTypeId == 2){
					//alert("2222222");
					upCheckTyepMenu(menuNameID,str,select);
				}
				if(str.menuTypeId == 3){
					$.messager.alert('消息', "无需修改!");
				}
			},
			error :function (date){
				$.messager.alert('消息', "网络异常");
			}
		});
	}else{
		$.messager.alert("消息","请选择要修改的数据!");
	}
}
function upCheckTyepMenu(menuNameID,str,select){
	$.ajax({
		type : "post",
		url : "../site/manage-website!checkTyepMenu",
		data : {
			'menuNameID' : menuNameID
		},
		data : {
			'templateTypeMenuId' : str.templateTypeMenuId
		},
		datatype : "json",
		success : function (date){
			//var str = eval(date);
			if(str.menuTypeId == 0){
				showUpatePageForm(eval(date),select,str);
			}
			if(str.menuTypeId == 1){
				showUpatePageForm(eval(date),select,str);
			}
			if(str.menuTypeId == 2){
				showUpateTelPageForm(eval(date),select);
			}
		},
		error : function (date){
			$.messager.alert('消息', "网络异常");
		}
	});
}

function showUpatePageForm(str,select,templateProperty){
	$('#updatePage').height(79);
	$("#updatePage").css("visibility", "visible");
	$('#updatePage').window('open');
	$('#pageform').form('clear');
	$("#pageform").css("visibility", "visible");
	$("#pageform").show();
	$("#pageform").appendTo('#updatePageeeXL');
	if(str.title == 0){
		$("#tr1").hide();
		$("#pageTitle").val("  ");
		//$('#addPage').height($('#addPage').height()-$('#tr1').height());
	}else{
		$("#tr1").show();
		$('#updatePage').height($('#updatePage').height()+29);
	}
	if(str.textboxNum ==1){
		$("#tr2").show();
		$('#updatePage').height($('#updatePage').height()+78);
		$("#metaDetail").val(select.metaDetail);
	}else{
		$("#tr2").hide();
		$("#metaDetail").val("  ");
		//$('#addPage').height($('#addPage').height()-$('#tr3').height());
	}
	if(str.compilerNum == 0){
		$("#tr3").hide();
		$("#metaValue").val("  ");
		//$('#addPage').height($('#addPage').height()-$('#tr2').height()-$('#addPage').height()*0.2);
	}else{
		$("#tr3").show();
		$('#updatePage').height($('#updatePage').height()+215);
		document.getElementById("idmetaValue").innerHTML = 
			   "\<textarea id='metaValue' name='metaValue' cols='50' rows='10' class='ww'\>\</textarea\>";
		$("#metaValue").val(select.metaValue);
		//metaValue();
		kindEditor();
	}
	if(str.bigImageNum == 1){
		$("#tr4").show();
		$("#pageFile").html("<input type=\"file\" name=\"bigImage\" id=\"bigImage\"/>");
		$("#pageFileName").html("配&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图");
		$('#updatePage').height($('#updatePage').height()+24);
	}else{
		$("#tr4").hide();
	}
	if(templateProperty.parseNum == 1){
		ChildName(select.pageMenu);
		$("#tr5").show();
		$('#updatePage').height($('#updatePage').height()+24);
		$('#childMenuId').combobox('setValue',select.childMenuID);
	}else{
		$("#tr5").hide();
	}
	
	$("#pageUrl").val(select.pageUrl);
	$("#pageTitle").val(select.pageTitle);
	$("#pageID").val(select.pageId);

}

function getAddBanner(){
	$.ajax({
		type : "post",
		url : "../site/manage-website!checkBanner",
		data : {
			'menuNameID' : menuNameID,
			'menuId' : menuId
		},
		datatype : "json",
		success : function (date){
			var str = eval(date);
			if(str.state == 0){
				$.messager.alert('消息', str.message);
				return;
			}else{
				banner(menuId,menuNameID);
			}
		},
		errror : function (){
			$.messager.alert('消息', "网络异常!");
		}
	});
}


function updatePage(){
	name = "pageTt";
	if($("#pageTitle").val()==""){
		$.messager.alert("消息","请填写文章标题!");
		return ;
	}
	if($("#metaDetail").val()==""){
		$.messager.alert("消息","请添加文章概述!");
		return ;
	}
	if($("#metaValue").val()==""){
		$.messager.alert("消息","请填写详细内容!");
		return ;
	}
	
	var opt = {
			url : "../site/manage-website!updatePage?menuNameID="+menuNameID,
			success : function(text) {
				closeUpdatePage();
				$.messager.alert('消息',"修改成功");
				$('#'+name).datagrid('reload');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				closeUpdatePage();
				$.messager.alert('消息', " 系统繁忙，请稍后再试...");
			}
		};
		$("#pageform").ajaxSubmit(opt);
}

function closeUpdatePage(){
	$("#updatePage").window('close');
}
function closeUpdateTelPage(){
	$("#updateTelPage").window('close');
}
function getDeletePage(){
	var select = $("#pageTt").datagrid("getSelected");
	if(select){
		$.messager.confirm('消息', '确认删除么?', function(id) {
			if(id ==false){
				return;
			}
			$.ajax({
				type : "post",
				url : "../site/manage-website!deletePage",
				data :{
					'pageID' : select.pageId,
					'menuNameID' : menuNameID
				},
				datatype : "json",
				success : function (date){
					var str = eval(date);
					$.messager.alert('消息', str.message);
					$('#'+name).datagrid('reload');
				},
				error : function (){
					$.messager.alert('消息', "网络异常!");
				}
			});
		});
	}else{
		$.messager.alert('消息', "请选择要修改的数据!");
	}
}

function callback(message, success) {
	closeUpdateChileMenu();
	closeUpdatemenu();
	closeAddmenu();
	closeUploadBanner();
	closeUpdateBanner();
	closeUploadAdvert();
	closeUpdateAdvert();
	closeUploadLogo();
	closeUpdateLogo();
	closeAddPage();
	closeUpdatePage();
	closeAddTelPage();
	closeUpdateTelPage();
	$.messager.alert('消息', message);
	$('#'+name).datagrid('reload');
}
function ChildName(menuID){
	  $("#childMenuId").combobox({
		     url:"../site/manage-website!getchildMenuList?menuId="+menuID,
					valueField:"menuId",
					textField:"menuName",
					editable:false,
		            missingMessage:'请选择分类！！',
		            listHeight:'15xp'
		});  
}

function getAddAdvert(){
	$.ajax({
		type : "post",
		url : "../site/manage-website!chooseShow",
		data : {
			'menuNameID' : menuNameID
		},
		datatype : "json",
		success : function (date){
			var str = eval(date);
			$.ajax({
				type : "post",
				url : "../site/manage-website!checkTyepMenu",
				data : {
					'menuNameID' : menuNameID
				},
				data : {
					'templateTypeMenuId' : str.templateTypeMenuId
				},
				datatype : "json",
				success : function (d){
					var templatePageProperty = eval(d);
					if(templatePageProperty.advertisingNum == 0){
						$.messager.alert('消息', "改栏目下无需上传广告!");
					}else{
						advert(menuId,menuNameID);
					}
				},
				error : function (){
					$.messager.alert('消息', "网路异常!");
				}
			});
		},
		error : function (){
			$.messager.alert('消息', "网路异常!");
		}
	});
				
}

function getUrl(){
	$("#addexplicit").css("visibility", "visible");
	$('#addexplicit').window('open');
	$('#addExplicit').form('clear');
	$("#addExplicit").css("visibility", "visible");
	$("#addExplicit").show();
	$("#addExplicit").appendTo('#eeXL1');
	getUrlStr();
}
function getUrlStr(){
	$.ajax({
		type : "post",
		url : "../site/manage-website!getCookie",
		datatype : "json",
		success : function (date){
			var str = eval(date);
			var urlStr = window.location.href;
			var url = urlStr.split("\/");//<a target=\"_blank\" href=\""+url[0]+"//"+url[2]+"/wxpt/site/web/template/index!index?enterID="+str.enterID+" \">
			
			$("#urlStr").html("微网站访问路径：</br><span id=\"enterID\">"+url[0]+"//"+url[2]+"/wxpt/site/web/template/index!index?enterID="+str.enterID+"</span>");
		},
		error : function (){
			$.messager.alert("警告","网络异常!");
		}
	});
	
}
function chooseTemplate() {
	queryTemplate();
	$("#template").css("visibility", "visible");
	$('#template').window('open');
}
function queryTemplate() {
	$('#temp').datagrid({
		iconCls : 'icon-save',
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		//onRowContextMenu:onRowContextMenu,
		url : '../site/manage-website!getTemplate',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [ {
			title : '模板类型',
			field : 'templateTypeStr',
			width : 60,
			rowspan : 2,
			align : 'center'
		}, {
			title : '模板价格',
			field : 'templatePrice',
			width : 80,
			rowspan : 2,
			align : 'center'
		}, {
			title : '模板名称',
			field : 'folder',
			width : 200,
			rowspan : 2,
			align : 'center'
		}, {
			title : '模板图片',
			field : 'thumbnail',
			width : 195,
			rowspan : 2,
			align : 'center'
		} ] ],

		onDblClickRow : function() {
			var selected = $('#temp').datagrid('getSelected');
			if (selected) {
				// window.open("DataView.action?Id="+selected.ID);
				getChoose();
			}
		},
		rownumbers : true,
		toolbar : "#toolbar2"
	});
	displayMsg2();
}
function displayMsg2() {
	$('#temp').datagrid('getPager').pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function getChoose(){
	var select = $('#temp').datagrid('getSelected');
	if (select) {
		$.ajax({
			type : "POST",
			url : "../site/manage-website!chooseTemplate",
			data : {
				'templateId' : select.templateId
			},
			dataType : "json",
			success : function(date) {
				var str =  eval(date);
				$.messager.alert('消息',str.message);
				$('#tt').datagrid('reload');
				//window.location.reload();
			},
			error : function() {
				$.messager.alert('消息', "失败");
				$('#tt').datagrid('reload');
				//window.location.reload();
			}
		});
	}
}

function kindEditor() {
	var editor = KindEditor.create('textarea[name="metaValue"]',{
		cssPath : '/wxpt/manager/js/kindeditor/plugins/code/prettify.css',
		uploadJson : '/wxpt/manager/js/kindeditor/jsp/upload_json.jsp',
        fileManagerJson : '/wxpt/manager/js/kindeditor/jsp/file_manager_json.jsp',
        allowFileManager : true,
		 afterBlur:function(){this.sync();}
	});
	editor.remove().create();
}


function kindEditor2() {
	var editor = KindEditor.create('textarea[name="menuName"]',{
	
		uploadJson : '/wxpt/manager/js/kindeditor/jsp/upload_json.jsp',
        fileManagerJson : '/wxpt/manager/js/kindeditor/jsp/file_manager_json.jsp',
        allowFileManager : true,
		 afterBlur:function(){this.sync();}
	});
	editor.remove().create();
}
