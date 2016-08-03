<%@ page import="com.handany.base.common.ComponentFactory"%>
<%@ page import="com.handany.base.sequence.impl.Sequence_MySql" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

String password = request.getParameter("password");

if("Lufsx966".equals(password)){
		//序列信息初始化 
		try {
			if("2".equals(request.getParameter("option"))){
				ComponentFactory.getBean("sequence_mysql",Sequence_MySql.class).updateAll();
			}
			ComponentFactory.getBean("sequence_mysql",Sequence_MySql.class).initAll();
			out.println("<h1>初始化成功</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		};
}else{
%>

<form method="post">
输入初始化密码:<input type="password" name="password">

<select name="option" >
	<option value="2">非首次</option>
	<option value="1">首次</option>
</select>
<input type="submit" value="提交">
</form>

<%
}
%>