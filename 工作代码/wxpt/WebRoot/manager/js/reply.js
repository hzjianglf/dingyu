$(function() {
	$("#toolbar").css("visibility", "visible");
$('#tt').datagrid(
					{
						title : '回复管理',
						iconCls : 'icon-save',
						fitColumns : true,
						width : '100%',
						height : '100%',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-reply',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						onRowContextMenu:onRowContextMenu,
						columns : [ [ {
							title : '回复内容',
							field : 'replyContent',
							width : '900',
							align : 'center',
							visibility : 'hidden'
						}, {
							title : '回复类型',
							field : 'replyType',
							width : '200',
							align : 'center',
							visibility : 'hidden'
						},{
							title : 'id',
							field : 'replyId',
							width : '200',
							align : 'center',
							hidden : true
						} ] ],
						
						/*onDblClickRow : function() {
							var selected = $('#tt').datagrid('getSelected');
							if (selected) {
								// window.open("DataView.action?Id="+selected.ID);
								getSelect();
							}
						},*/
						rownumbers : true,
						toolbar : "#toolbar"
					});
	displayMsg();
});

function displayMsg() {

	$('#tt').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [5,10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}


function UpdateReply(){
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		id = select.replyId;
		$("#updatereply").css("visibility", "visible");
		$('#updatereply').window('open');
		$('#addReply').form('clear');
		$("#addReply").css("visibility", "visible");
		$('#addReply').show();
		$("#replyContent").val(select.replyContent);
		$("#keywordID").val(select.keyId);
		$("#keyName").val(select.replyContent);
		$('#addReply').appendTo('#update');
	}else{
		alert("请选中一行~");
	}
}

var id = "";

function getSelect() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		id = select.replyId;
		$("#addreply").css("visibility", "visible");
		$('#addreply').window('open');
		
		$("#add").html(select.replyContent);
	}
}

function displayMsg1() {

	$('#la').datagrid('getPager').pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function chooseKey(){
	$('#la').datagrid({
		iconCls : 'icon-save',
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		onRowContextMenu:onRowContextMenu,
		url : '../site/manage-key!key',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		columns : [ [ {
			title : '规则',
			field : 'rule',
			width : 244,
			rowspan : 2,
			align : 'center'
		}, {
			title : '内容',
			field : 'keywordcontent',
			width : 244,
			rowspan : 2,
			align : 'center'
		} ] ],

		onDblClickRow : function() {
			var selected = $('#la').datagrid('getSelected');
			if (selected) {
				// window.open("DataView.action?Id="+selected.ID);
				judgeGetSelected();
			}
		},
		rownumbers : true,
		toolbar : "#toolbar1"
	});
	displayMsg1();
	$("#lan").css("visibility", "visible");
	$('#lan').window('open');
}
function add() {
	$.ajax({
		url : "../manage/manage-reply!add",
		type : 'post',
		data : '' + $("#addReply").serialize(),
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

function update() {
	$.ajax({
		url : "../site/manage-reply!update",
		type : 'post',
		data : {
			'replyId' : id,
			'keyName' : $("#keyName").val()
		},
		//dataType : "json",
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

function close1() {
	$('#updatereply').window('close');
}
function judgeGetSelected(){
	var select = $('#la').datagrid('getSelected');
	if(select){
		$('#lan').window('close');
		$("#keyName").val(select.keywordcontent);
		$("#keywordID").val(select.keywordId);
	}
}


function ClearReply(){
	var select = $('#tt').datagrid('getSelected');
	if(select){
		$.ajax({
			url : "../site/manage-reply!clearReply",
			type : 'post',
			data : {
				'replyId' : select.replyId
			},
			//dataType : "json",
			success : function() {
				$.messager.alert('消息', '取消成功~~~');
				$('#tt').datagrid('reload');
			},
			error : function() {
				$.messager.alert('消息', '取消失败~~~');
				$('#tt').datagrid('reload');
			}

		});
	}else{
		$.messager.alert('消息','选择一行数据~~~');
	}
}
