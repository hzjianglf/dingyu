
function sign(){ 
 $.ajax({
		url : "/wxpt/site/check-in!check",
		type : 'post',
		data : '' + $("#ff").serialize(),	
		success : function(data) {
			if (data == "1") {
				$.messager.alert('warning','成功');
				$('#tt').datagrid('reload');
			} else {
				$.messager.alert('warning','失败！');
			}
			;
		},
		error : function(a, x, e) {
			alert(e.message);
		}
	});

    }