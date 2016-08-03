$(function() {
	$("#toolbar").css("visibility", "visible");	
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt').datagrid(
					{
						
						title : '问题设置',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-question!getQuestionList',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						onRowContextMenu:onRowContextMenu,
						remoteSort : false,
						
						columns : [ [{
							field : 'Opt',
							title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
							width : 40,
							align : 'center',
							formatter : function(value, rec) {
								return "<input type=\"checkbox\" name=\"xuanze\" value="
										+ rec.questionid + "  />";
							}
						} ,
								{
									title : '难易程度',
									field : 'questionTypeStr',
									width : '80',
									align : 'center',
									visibility : 'hidden'
								},{
									title : '题目标题',
									field : 'questionTitle',
									width : '80',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '题目内容',
									field : 'qustionContent',
									width : '220',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '答案A',
									field : 'answerA',
									width : '170',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '答案B',
									field : 'answerB',
									width : '170',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '答案C',
									field : 'answerC',
									width : '170',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '答案D',
									field : 'answerD',
									width : '170',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '正确答案',
									field : 'rightAnswer',
									width : '80',
									align : 'center',
									visibility : 'hidden'
								}
								
								] ],
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
								UpdateQuestion();
							}
						},
						rownumbers : true,

						toolbar : "#toolbar"
					});
	displayMsg();
});

function onRowContextMenu(e,rowIndex,rowData){
	/*e.preventDafault();*/
	e.preventDefault();
}
function displayMsg() {

	$('#tt').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [5,10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}

var id = "";



function AddQuestion() {
	$("#addquestion").css("visibility", "visible");
	$('#addquestion').window('open');
	$('#addQuestion').form('clear');
	$("#addQuestion").css("visibility", "visible");
	$("#addQuestion").show();
	$("#addQuestion").appendTo('#eeXL');
	showQuestionType();
}

function showQuestionType(){
	$('#questionType').combobox({
		type:'post',
		url : '../site/manage-question',
		valueField : 'id',
		textField : 'text'
	});
}
function add() {
	var opt = {
			url:"../site/manage-question!addQuestion",
			success : function(text) {
				$.messager.alert('消息',text);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messager.alert('消息'," 系统繁忙，请稍后再试...");
			}
	};
	$("#addQuestion").ajaxSubmit(opt);

}

function UpdateQuestion() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		id = select.questionid;
		$("#updatequestion").css("visibility", "visible");
		$('#updatequestion').window('open');
		$('#addQuestion').form('clear');
		$("#addQuestion").css("visibility", "visible");
		$('#addQuestion').show();
		$("#qustionTitle").val(select.questionTitle);
		$("#content").val(select.qustionContent);
		$("#answera").val(select.answerA);
		$("#answerb").val(select.answerB);
		$("#answerc").val(select.answerC);
		$("#answerd").val(select.answerD);
		var radioObjects = document.getElementsByName("rightAnswer");
		 
        for(var i = 0;i < radioObjects.length;i++){
            if(radioObjects[i].value == select.rightAnswer){
                radioObjects[i].checked =  'checked';
            }
        }
        showQuestionType();
		$('#addQuestion').appendTo('#update');
	} else {
		$.messager.alert('消息',"请选中一行");
	}
}
function update() {
	$("#questionId").val(id);
	var opt = {
			url:"../site/manage-question!updateQuestion",
			success : function(text) {
				$.messager.alert('消息',text);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messager.alert('消息'," 系统繁忙，请稍后再试...");
			}
	};
	$("#addQuestion").ajaxSubmit(opt);
}


function close1() {
	$('#addquestion').window('close');
	$('#add').window('close');
	$('#updatequestion').window('close');
}

// 删除教室信息

function DeleteQuestion() {

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
				url : "../site/manage-question!deleQuestion",
				data : {
					'questionIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息', '删除成功!');
					$('#tt').datagrid('reload');
					//window.location.reload();
				},
				error : function() {
					$.messager.alert('消息', '删除失败!');
					$('#tt').datagrid('reload');
					//window.location.reload();
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
function changeDiv(){
	if($("#type").val() == 0||$("#type").val()==2){
		$("#textareaDiv").hide();
		$("#fileDivs").show();
		$("#titleTr").show();
	}
	if($("#type").val() == 1){
		$("#textareaDiv").show();
		$("#fileDivs").hide();
		$("#titleTr").hide();
	}
}
function getFileName(){
	$("#fileName").val($("#qustionContent").val());
}
function callback(message, success) {
	$.messager.alert('消息',message);
	close1();
	$('#tt').datagrid('reload');
	close1();
	/*close2();
*/	//window.location.reload();
}