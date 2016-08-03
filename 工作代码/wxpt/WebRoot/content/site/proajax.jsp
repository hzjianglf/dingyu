<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:iterator value="listType1" var="list">
	
		<option value="<s:property value='#list.productTypeId' />">
		<s:property value='#list.typeName' />
		
		</option>
	</s:iterator>