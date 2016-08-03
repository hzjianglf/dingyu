<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

						<option value="-1">选择企业</option>
						<s:iterator value="enterList" var="en" id="en">
							<option value='<s:property value="#en.enterInforId" />'>
								<s:property value="#en.enterName"></s:property>
							</option>
						</s:iterator>
