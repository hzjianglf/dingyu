<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>



  
		
		
	<s:iterator value="productTypeList" var="bean">
	
		<option value="${area.countryAreaId}">
		<s:property value='#area.areaName' />
		
		</option>
	</s:iterator>

