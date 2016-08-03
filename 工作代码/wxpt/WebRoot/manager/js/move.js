$(function() {

	$("#ff").css("visibility", "visible");

	$("#toolbar").css("visibility", "visible");
	$('#ff').hide();
	$('#tt')
			.datagrid(
					{

						title : '活动管理',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-move!getList',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						onRowContextMenu : onRowContextMenu,
						columns : [ [

								{
									field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : 40,
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.moveId + "  />";
									}
								}, {
									title : '活动名称',
									field : 'moveName',
									width : '120',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '活动内容',
									field : 'moveContent',
									width : '700',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '开启时间',
									field : 'moveStartTime',
									width : '120',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '结束时间',
									field : 'moveEndTime',
									width : '120',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '活动状态',
									field : 'moveStateDesc',
									width : '80',
									align : 'center',
									visibility : 'hidden',
									sortable : true
								} 
								, {
									title : '时间',
									field : 'awardTime',
									width : '80',
									align : 'center',
									visibility : 'hidden',
									hidden : true
								} ] ],
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
						onDblClickRow : function() {
							var selected = $('#tt').datagrid('getSelected');
							if (selected) {
								// window.open("DataView.action?Id="+selected.ID);
								// queryVote();
							}
						},
						rownumbers : true,

						toolbar : "#toolbar"
					});
	displayMsg();
});

function onRowContextMenu(e, rowIndex, rowData) {
	/* e.preventDafault(); */
	e.preventDefault();
}
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
function AddUser() {
	$.ajax({
		type : "POST",
		url : "../site/manage-move!ss",
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function(text) {
			$("#selects").css("display", "");
			$("#moveNames").css("display", "none");
			$('#moveName').html(text);
			$("#adduser").css("visibility", "visible");
			$('#adduser').window('open');
			$('#addUser').form('clear');
			$("#addUser").css("visibility", "visible");
			$("#addUser").show();
			$("#addUser").appendTo('#eeXL');

		},
		error : function() {
			$.messager.alert('消息', '删除失败!');
			$('#tt').datagrid('reload');

		}
	});
	/*
	 * $("#adduser").css("visibility", "visible"); $('#adduser').window('open');
	 * $('#addUser').form('clear'); $("#addUser").css("visibility", "visible");
	 * $("#addUser").show(); $("#addUser").appendTo('#eeXL');
	 */
}
function add() {
	$.ajax({
		type : "POST",
		url : "/wxpt/site/manage-move!save",
		data : '' + $("#addUser").serialize(),
		dataType : "json",
		success : function() {
			$.messager.alert('消息', '添加成功!');
			close();
			$('#tt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息', '添加失败!');
			close();
			$('#tt').datagrid('reload');
			close1();

		}
	});
}
function close1() {
	$("#adduser").window("close");
	$("#updateuser").window("close");
}
function UpdateUser() {
	var select = $("#tt").datagrid('getSelected');
	$("#selects").css("display", "none");
	$("#moveNames").css("display", "");
	if (select) {
		if(select.moveName=="微会员"){
			$("#updateuser").css("visibility", "visible");
			$('#updateuser').window('open');
			$('#addUser').form('clear');
			$("#addUser").css("visibility", "visible");
			$("#addUser").show();
			$('#moveNames').val(select.moveName);
			$("#moveContent").val(select.moveContent);
			$("#startTime").val(select.moveStartTime);
			$("#endTime").val(select.moveEndTime);
			$("#moveId").val(select.moveId);
			$("#endTimes").val(select.awardTime);
			$("#awardTr").css("display", "none");
			$("#hao").css("display", "none");
			$("#hyEdnTime").css("display", "");
			$("#end").css("display", "");
			$("#addUser").appendTo('#update');
		
		}else if(select.moveName=="答题"){
			$("#updateuser").css("visibility", "visible");
			$('#updateuser').window('open');
			$('#addUser').form('clear');
			$("#addUser").css("visibility", "visible");
			$("#addUser").show();
			$('#moveNames').val(select.moveName);
			$("#moveContent").val(select.moveContent);
			$("#startTime").val(select.moveStartTime);
			$("#endTime").val(select.moveEndTime);
			$("#moveId").val(select.moveId);
			$("#awardTime").val(select.awardTime);
			$("#hyEdnTime").css("display", "none");
			$("#end").css("display", "none");
			$("#awardTr").css("display", "");
			$("#hao").css("display", "");
			$("#addUser").appendTo('#update');
			
		}else{
			$("#updateuser").css("visibility", "visible");
			$('#updateuser').window('open');
			$('#addUser').form('clear');
			$("#addUser").css("visibility", "visible");
			$("#addUser").show();
			$('#moveNames').val(select.moveName);
			$("#moveContent").val(select.moveContent);
			$("#startTime").val(select.moveStartTime);
			$("#endTime").val(select.moveEndTime);
			$("#moveId").val(select.moveId);
			$("#awardTime").val(select.awardTime);
			$("#hyEdnTime").css("display", "none");
			$("#end").css("display", "none");
			$("#awardTr").css("display", "none");
			$("#hao").css("display", "none");
			$("#addUser").appendTo('#update');
			
		}
		
		
	} else {
		$.messager.alert('消息', "请选择一行");
	}
}
function update() {
	$.ajax({
		type : "POST",
		url : "../site/manage-user!updateMove",
		data : '' + $("#addUser").serialize(),
		dataType : "json",
		success : function() {
			$.messager.alert('消息', '修改成功!');
			close();
			$('#tt').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息', '修改失败!');
			close();
			$('#tt').datagrid('reload');
			close1();

		}
	});
}

function query() {
	$('#tt').datagrid({
		url : "../site/manage-move!getList",
		queryParams : {
			/* paixu:$("#paixu").val(), */
			queryName : $("#queryName").val()
		/*
		 * startTime:$("#startTime").val(), endTime:$("#endTime").val()
		 */
		}

	});
	displayMsg();
}
function DeleteUser() {

	var checks = document.getElementsByName("xuanze");

	var ids = "";
	for ( var i = 0; i < checks.length; i++) {
		if (checks[i].checked == true) {
			ids += checks[i].value + ",";

		}

	}
	if (ids == "") {
		$.messager.alert('消息', '请选中要删除的数据', 'warning');
		return;
	}

	$.messager.confirm('消息', '确认删除么?', function(id) {

		if (id) {

			$.ajax({
				type : "POST",
				url : "../site/manage-user!deleteMove",
				data : {
					'moveIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息', "删除成功!");
					$('#tt').datagrid('reload');

				},
				error : function() {
					$.messager.alert('消息', '删除失败!');
					$('#tt').datagrid('reload');

				}
			});
		}
		$('#tt').datagrid('reload');
	});

}
var s_caocuo = "false";
function selectAll() {
	var checks = document.getElementsByName("xuanze");
	// 测试全选
	var str = $("#option").html();
	// alert(s_caocuo);
	// $.messager.alert('信息', str.indexOf("checked"));//值为-1
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
function queryVote() {

	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$('#la').datagrid({
			iconCls : 'icon-save',
			fit : true,
			pagination : true,// 设置分页条显示
			pageSize : 5,
			pageList : [ 5, 10, 15, 20 ],
			nowrap : false,
			striped : true,
			collapsible : true,
			singleSelect : true,
			url : '../site/manage-user!getUserCount?userId=' + select.userId,
			loadMsg : '数据装载中......',
			sortName : 'code',
			sortOrder : 'desc',
			remoteSort : false,
			columns : [ [ {
				title : '投票人',
				field : 'sendUser',
				width : 252,
				rowspan : 2,
				align : 'center'
			}, {
				title : '投票时间',
				field : 'sendTime',
				width : 252,
				rowspan : 2,
				align : 'center'
			} ] ],

			onDblClickRow : function() {
				var selected = $('#la').datagrid('getSelected');
				if (selected) {
					// window.open("DataView.action?Id="+selected.ID);
					// judgeGetSelected();
				}
			},
			rownumbers : true,
			toolbar : "#toolbar1"
		});
		displayMsg1();
		$("#userNamename").html(select.userName + "得票情况");
		$("#lan").css("visibility", "visible");
		$('#lan').window('open');
	} else {
		$.messager.alert('消息', "请选择一行!");
	}
}
function displayMsg1() {

	$('#la').datagrid('getPager').pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function change() {
	var changName = $("#moveName").val();
	if (changName == "答题") {
		$("#awardTr").css("display", "");
		$("#hao").css("display", "");
	} else {
		$("#awardTr").css("display", "none");
		$("#hao").css("display", "none");

	}
	if (changName == "微会员") {
		$("#hyEdnTime").css("display", "");
		$("#end").css("display", "");
	} else {
		$("#hyEdnTime").css("display", "none");
		$("#end").css("display", "none");
	}
}

function SetPrizeChance(){
	
	var select = $("#tt").datagrid('getSelected');
	if(select){
		var moveName=select.moveName;
		
		$.ajax({
			type : "POST",
			url : '../site/manage-move!checkMoveTime?move_name='+moveName,
			dataType : "text",
			success : function(data) {
			
				if(select.moveState==0){
					$.messager.alert('信息', "活动已开启不可以再更改");
				}else{
					if(moveName=="签到"){
						
						var s = select.gailv;
						var p=select.probability;
						var pnumber=p.split(";");
						var number = s.split(";");
						
						 var str1 =p.split("mm");
						
						 var day=str1[0].split(";");
						 var zhuanpan1=str1[1].split(";");
						 var zhuanpan2=str1[2].split(";");
						 var zhuanpan3=str1[3].split(";");
						 $("#zhuanpan1").val(day[0]);
						 $("#zhuanpan2").val(day[1]);
						 $("#zhuanpan3").val(day[2]);
						
						
						//中奖人数显示设置
						$("#onePrizeCount").val(number[0]);
						$("#twoPrizeCount").val(number[1]);
						$("#threePrizeCount").val(number[2]);
						$("#fourPrizeCount").val(number[3]);
						$("#fivePrizeCount").val(number[4]);
						$("#sixPrizeCount").val(number[5]);
						$("#sevenPrizeCount").val(number[6]);
						$("#eightPrizeCount").val(number[7]);
						$("#ninePrizeCount").val(number[8]);
						$("#tenPrizeCount").val(number[9]);
						
						//概率显示设置
						//第一次
						$("#oneProbability").val(zhuanpan1[0]);
						$("#twoProbability").val(zhuanpan1[1]);
						$("#threeProbability").val(zhuanpan1[2]);
						$("#fourProbability").val(zhuanpan1[3]);
						$("#fiveProbability").val(zhuanpan1[4]);
						$("#sixProbability").val(zhuanpan1[5]);
						$("#sevenProbability").val(zhuanpan1[6]);
						$("#eightProbability").val(zhuanpan1[7]);
						$("#nineProbability").val(zhuanpan1[8]);
						$("#tenProbability").val(zhuanpan1[9]);
						
						//第二次
						$("#oneProbability2").val(zhuanpan2[0]);
						$("#twoProbability2").val(zhuanpan2[1]);
						$("#threeProbability2").val(zhuanpan2[2]);
						$("#fourProbability2").val(zhuanpan2[3]);
						$("#fiveProbability2").val(zhuanpan2[4]);
						$("#sixProbability2").val(zhuanpan2[5]);
						$("#sevenProbability2").val(zhuanpan2[6]);
						$("#eightProbability2").val(zhuanpan2[7]);
						$("#nineProbability2").val(zhuanpan2[8]);
						$("#tenProbability2").val(zhuanpan2[9]);
						
					
						//第三次
				
						$("#oneProbability3").val(zhuanpan3[0]);
						$("#twoProbability3").val(zhuanpan3[1]);
						$("#threeProbability3").val(zhuanpan3[2]);
						$("#fourProbability3").val(zhuanpan3[3]);
						$("#fiveProbability3").val(zhuanpan3[4]);
						$("#sixProbability3").val(zhuanpan3[5]);
						$("#sevenProbability3").val(zhuanpan3[6]);
						$("#eightProbability3").val(zhuanpan3[7]);
						$("#nineProbability3").val(zhuanpan3[8]);
						$("#tenProbability3").val(zhuanpan3[9]);
						
						
					$("#move_name").val(moveName);
					$('#editPrize').window('open');
					$('#ffPrize').show();
					$("#editPrize").css("visibility", "visible");
					$("#ffPrize").css("visibility", "visible");
					$("#prizeCountNum").val("0");
					$("#lianxu1").css("display", "");
					$("#lianxu2").css("display", "");
					$("#lianxu3").css("display", "");
					$("#id1").css("display", "");
					$("#id2").css("display", "");
					$("#id3").css("display", "");
					$("#id4").css("display", "");
					$("#id5").css("display", "");
					$("#id6").css("display", "");
					/*$("#id7").css("display", "");
					$("#id8").css("display", "");
					$("#id9").css("display", "");
					$("#id10").css("display", "");*/
					$("#id12").css("display", "");
					$("#id13").css("display", "");
					$("#id22").css("display", "");
					$("#id23").css("display", "");
					$("#id32").css("display", "");
					$("#id33").css("display", "");
					$("#id42").css("display", "");
					//$("#id43").css("display", "");
					//$("#id52").css("display", "");
					/*$("#id53").css("display", "");
					$("#id62").css("display", "");
					$("#id63").css("display", "");
					$("#id72").css("display", "");
					$("#id73").css("display", "");
					$("#id82").css("display", "");
					$("#id83").css("display", "");
					$("#id92").css("display", "");
					$("#id93").css("display", "");
					$("#id102").css("display", "");
					$("#id103").css("display", "");*/
					$('#ffPrize').appendTo('#eePrize');
				
					}else{

						var s = select.gailv;
						var p=select.probability;
						var pnumber=p.split(";");
						var number = s.split(";");
						
						//中奖人数显示设置
						$("#onePrizeCount").val(number[0]);
						$("#twoPrizeCount").val(number[1]);
						$("#threePrizeCount").val(number[2]);
						$("#fourPrizeCount").val(number[3]);
						$("#fivePrizeCount").val(number[4]);
						$("#sixPrizeCount").val(number[5]);
						$("#sevenPrizeCount").val(number[6]);
						$("#eightPrizeCount").val(number[7]);
						$("#ninePrizeCount").val(number[8]);
						$("#tenPrizeCount").val(number[9]);
						
						//概率显示设置
						//第一次
						$("#oneProbability").val(pnumber[0]);
						$("#twoProbability").val(pnumber[1]);
						$("#threeProbability").val(pnumber[2]);
						$("#fourProbability").val(pnumber[3]);
						$("#fiveProbability").val(pnumber[4]);
						$("#sixProbability").val(pnumber[5]);
						$("#sevenProbability").val(pnumber[6]);
						$("#eightProbability").val(pnumber[7]);
						$("#nineProbability").val(pnumber[8]);
						$("#tenProbability").val(pnumber[9]);
						
					
						
						
						$("#move_name").val(moveName);
						$('#editPrize').window('open');
						$('#ffPrize').show();
						$("#editPrize").css("visibility", "visible");
						$("#ffPrize").css("visibility", "visible");
						$("#lianxu1").css("display", "none");
						$("#lianxu2").css("display", "none");
						$("#lianxu3").css("display", "none");
						$("#id1").css("display", "");
						$("#id2").css("display", "");
						$("#id3").css("display", "");
						$("#id4").css("display", "");
						$("#id5").css("display", "");
						$("#id6").css("display", "");
						$("#id7").css("display", "");
						$("#id8").css("display", "");
						$("#id9").css("display", "");
						$("#id10").css("display", "");
						$("#prize7").css("display", "");
						$("#prize8").css("display", "");
						$("#prize9").css("display", "");
						$("#prize10").css("display", "");

						$("#id12").css("display", "none");
						$("#id13").css("display", "none");
						$("#id22").css("display", "none");
						$("#id23").css("display", "none");
						$("#id32").css("display", "none");
						$("#id33").css("display", "none");
						$("#id42").css("display", "none");
						$("#id43").css("display", "none");
						$("#id52").css("display", "none");
						$("#id53").css("display", "none");
						$("#id62").css("display", "none");
						$("#id63").css("display", "none");
						$("#id72").css("display", "none");
						$("#id73").css("display", "none");
						$("#id82").css("display", "none");
						$("#id83").css("display", "none");
						$("#id92").css("display", "none");
						$("#id93").css("display", "none");
						$("#id102").css("display", "none");
						$("#id103").css("display", "none");
						$('#ffPrize').appendTo('#eePrize');
					
						$('#ffPrize').appendTo('#eePrize');
						$("#prizeCountNum").val("0");
					}
				}
			}
		});
	}else{
		$.messager.alert('信息', "请选择一行数据");
	}
	
}

function setChance(){
	var day1=$("#zhuanpan1").val();
	var day2=$("#zhuanpan2").val();
	var day3=$("#zhuanpan3").val();
	if(parseInt(day2) !=0 && parseInt(day2) <= parseInt(day1)){
		$.messager.alert('信息', "第二次天数需大于第一次的");
		return;
	}
	if(parseInt(day3) !=0 && parseInt(day3) <= parseInt(day2)){
		$.messager.alert('信息', "第三次天数需大于第二次的");
		return;
	}
	
	$.ajax({
		type : "POST",
		url : "/wxpt/site/manage-move!setPrizeChance",
		data :$('#ffPrize').serialize(),
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function() {
			$.messager.alert('信息', "设置成功~~~");
			$('#editPrize').window('close');
			$('#tt').datagrid("reload");

		},
		error : function() {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
			$('#editPrize').window('close');
			$('#tt').datagrid("reload");

		}
	});
	
	/*var opt = {
		url : "/wxpt/site/manage-move!setPrizeChance",
		success : function(text) {
			$.messager.alert('信息', "设置成功~~~");
			$('#editPrize').window('close');
			$('#tt').datagrid("reload");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(textStatus);
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
			$('#editPrize').window('close');
			$('#tt').datagrid("reload");
		}
	};
	$("#ffPrize").ajaxSubmit(opt);*/
}
function closePrizeChance(){
	$('#editPrize').window('close');
}
function note_click(target)
{
 if(target.value=='-1')
 {
  target.style.color="#B0B0B0";
  target.value="请输入数字";
 }
 else if(target.value=="请输入数字")
 {
  target.style.color="#000000";
  target.value="";
 }
}

function note_click2(target){

	 if(target.value=='0')
	 {
	  target.style.color="#B0B0B0";
	  target.value="格式如0.01,0.001";
	 }
	 else if(target.value=="格式如0.01,0.001")
	 {
	  target.style.color="#000000";
	  target.value="";
	 }

}