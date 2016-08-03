<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<option>--选择--</option>
<s:iterator value="mpList" var="list">
	
		<option value="<s:property value='#list.moveName' />">
		<s:property value='#list.moveName' />
		
		</option>
	</s:iterator>