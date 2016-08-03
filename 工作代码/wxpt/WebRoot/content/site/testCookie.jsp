<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <script type="text/javascript">

document.oncontextmenu=function(){
var selection="";//定义鼠标拖选值
if(document.selection){//***** IE
selection=document.selection.createRange().text;
}else{//***** 其他浏览器
selection=document.getSelection();
}
//if来判断拖选值、被单击的区域是不是表单域值
if(selection==""&&(event.srcElement.value==undefined||event.srcElement.value==0)&&event.srcElement.value!=0)return false;
}

function onRowContextMenu(e,rowIndex,rowData){
	/*e.preventDafault();*/
	e.preventDefault();
}
  
</script>
<%
	String path = request.getContextPath();
	String b = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + path + "/";
%>
<%
	Cookie[] cookies = request.getCookies();
	Cookie c = null;
	if (cookies == null || cookies.length == 1) {
%>
<script language="JavaScript"> 

 function myrefresh() 
{ 
//alert("1秒一次");
window.location.reload(); 
} 
setTimeout('myrefresh()',1000); //指定1秒刷新一次  
</script>
<script>

	alert("请先登录！");

	window.parent.location.href = '<%=b%>site/manage!manage?enterId=${enterId}';

</script>
<%
	} else {
		for (int i = 0; i < cookies.length; i++) {
			c = cookies[i];
			if (c.getName().equals("wxpts")) {
				if (c.getValue().equals("") || c.getValue() == null) {
%>
<script>
	//alert("请先登录！");

	window.parent.location.href = '<%=b%>site/manage!manage';

</script>
<%
	break;
				} else {
					String[] cString = c.getValue().split(":");
					request.setAttribute("userName", cString[0]);
					request.setAttribute("enterId", cString[2]);
					/* String[] values = c.getValue().split(":");
					request.setAttribute("userId", values[1]);
					request.setAttribute("userName", values[0]); */
					break;
				}
			} else {
				//
				if (i + 1 == cookies.length) {
%>
<script>

	alert("请先登录！");
	window.parent.location.href = '<%=b%>site/manage!manage';

</script>
<%
	}
			}
		}
	}
%>