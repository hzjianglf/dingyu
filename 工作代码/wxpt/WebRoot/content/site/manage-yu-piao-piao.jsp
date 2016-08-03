<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'manage-yu-piao-piao.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>manager/css/yupiao.css">
</head>

<body>
	<div id="gridbox" width="100%" height="350px" align="left"
		style="background-color: white;  width: 100%; height: auto; cursor: default; "
		class="gridbox gridbox_light">
		<table cellpadding="0" cellspacing="0" width="100%"
			style="table-layout: fixed; ">
			<tbody>
				<tr>
					<td style="vertical-align: top; "><div
							style="width: 100%; height: 200px; overflow-x: hidden; overflow-y: hidden; position: relative; ">
							<img style="position: absolute; display: none; "
								src="/otsquery/dhtmlxGrid/codebase/grid_imgs/sort_desc.gif">
							<table class="xhdr" cellpadding="0" cellspacing="0"
								style="width: 100%; ">
								<tbody>
									<tr>
										<td><table
												style="table-layout: fixed; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-color: gray; border-right-color: gray; border-bottom-color: gray; border-left-color: gray; border-image: initial; "
												cellspacing="0" cellpadding="0" class="hdr" width="100%">
												<tbody>
													<tr style="height: auto; ">
														<th style="height: 0px; width: 60px; "></th>
														<th style="height: 0px; width: 94px; "></th>
														<th style="height: 0px; width: 94px; "></th>
														<th style="height: 0px; width: 53px; "></th>
														<th style="height: 0px; width: 53px; "></th>
														<th style="height: 0px; width: 53px; "></th>
														<th style="height: 0px; width: 53px; "></th>
														<th style="height: 0px; width: 53px; "></th>
														<th style="height: 0px; width: 67px; "></th>
														<th style="height: 0px; width: 52px; "></th>
														<th style="height: 0px; width: 52px; "></th>
														<th style="height: 0px; width: 52px; "></th>
														<th style="height: 0px; width: 52px; "></th>
														<th style="height: 0px; width: 52px; "></th>
														<th style="height: 0px; width: 52px; "></th>
													</tr>
													<tr>
														<td rowspan="2" style="cursor: default; "><div
																class="hdrcell">
																<div style="text-align:center;">车次&nbsp;&nbsp;</div>
															</div>
														</td>
														<td colspan="3" style="cursor: default; "><div
																class="hdrcell">
																<div style="text-align:center;">查询区间</div>
															</div>
														</td>
														<td colspan="11" style="cursor: default; "><div
																class="hdrcell">
																<div style="text-align:center;">余票信息</div>
															</div>
														</td>
													</tr>
													<tr>
														<td style="cursor: default; ">发站</td>
														<td style="cursor: default; ">到站</td>
														<td style="cursor: default; ">历时</td>
														<td style="cursor: e-resize; ">商务座</td>
														<td style="cursor: default; ">特等座</td>
														<td>一等座</td>
														<td>二等座</td>
														<td>高级软卧</td>
														<td>软卧</td>
														<td>硬卧</td>
														<td>软座</td>
														<td>硬座</td>
														<td style="cursor: default; ">无座</td>
														<td style="cursor: default; ">其他</td>
													</tr>
												</tbody>
											</table>
										</td>
										<td style="width: 100%; " class="xhdr_last">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td><div
							style="width: 100%;  position: relative; top: -148px; height: auto; "
							class="objbox">
							<div style="width: 892px; ">
								<table cellspacing="0" cellpadding="0"
									style="table-layout: fixed; width: 892px; " class="obj row20px">
									<tbody>
										<tr style="height: auto; ">
											<th style="height: 0px; width: 60px; "></th>
											<th style="height: 0px; width: 94px; "></th>
											<th style="height: 0px; width: 94px; "></th>
											<th style="height: 0px; width: 53px; "></th>
											<th style="height: 0px; width: 53px; "></th>
											<th style="height: 0px; width: 53px; "></th>
											<th style="height: 0px; width: 53px; "></th>
											<th style="height: 0px; width: 53px; "></th>
											<th style="height: 0px; width: 67px; "></th>
											<th style="height: 0px; width: 52px; "></th>
											<th style="height: 0px; width: 52px; "></th>
											<th style="height: 0px; width: 52px; "></th>
											<th style="height: 0px; width: 52px; "></th>
											<th style="height: 0px; width: 52px; "></th>
											<th style="height: 0px; width: 52px; "></th>
										</tr>
										<s:iterator  var="piao" value="piaoList" id="piao">
										<tr class="ev_light ">
											<td align="center" valign="" title="1415"><span
												id="id_01000014180S" class="base_txtdiv"
												onmouseover="javascript:onStopHover('01000014180S#YCK#JNK')"
												onmouseout="onStopOut()" title="1415"><s:property value="#piao.checi"/></span>
											</td>
											<td align="center" valign=""
												title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禹城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;15:09">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#piao.chufa"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#piao.ddtime"/></td>
											<td align="center" valign=""
												title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;济南&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;16:02">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#piao.daozhan"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#piao.cftime"/></td>
											<td align="center" valign=""><s:property value="#piao.lishi"/></td>
											<td align="center" valign="" title="--"><s:property value="#piao.shangwu"/></td>
											<td align="center" valign=""><s:property value="#piao.tedeng"/></td>
											<td align="center" valign=""><s:property value="#piao.yi"/></td>
											<td align="center" valign=""><s:property value="#piao.er"/></td>
											<td align="center" valign=""><s:property value="#piao.gao"/></td>
											<td align="center" valign=""><s:property value="#piao.ruan"/></td>
											<td align="center" valign=""><s:property value="#piao.ying"/></td>
											<td align="center" valign=""><s:property value="#piao.ruanzuo"/></td>
											<td align="center" valign=""><s:property value="#piao.yingzuo"/></td>
											<td align="center" valign=""><s:property value="#piao.wuzuo"/></td>
											<td align="center" valign=""><s:property value="#piao.qita"/></td>
										</tr>
										</s:iterator>
										<!-- <tr class="odd_light ">
											<td align="center" valign=""><span id="id_040000134436"
												class="base_txtdiv"
												onmouseover="javascript:onStopHover('040000134436#YCK#JNK')"
												onmouseout="onStopOut()">1341</span>
											</td>
											<td align="center" valign="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禹城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;15:59</td>
											<td align="center" valign=""
												title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;济南&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;17:03">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;济南&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;17:03</td>
											<td align="center" valign="">01:04</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="" title="14">14</td>
											<td align="center" valign="">27</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">26</td>
											<td align="center" valign="">140</td>
											<td align="center" valign="">--</td>
										</tr> -->
										<!-- <tr class="ev_light ">
											<td align="center" valign="" title="1469"><span
												id="id_010000147204" class="base_txtdiv"
												onmouseover="javascript:onStopHover('010000147204#YCK#JNK')"
												onmouseout="onStopOut()">1469</span>
											</td>
											<td align="center" valign="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禹城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;17:13</td>
											<td align="center" valign=""
												title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;济南&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;18:02">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;济南&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;18:02</td>
											<td align="center" valign="" title="00:49">00:49</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="" title="--">--</td>
											<td align="center" valign="" title="20">20</td>
											<td align="center" valign="">23</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">24</td>
											<td align="center" valign="">38</td>
											<td align="center" valign="">--</td>
										</tr> -->
										<!-- <tr class="odd_light ">
											<td align="center" valign=""><span id="id_030000145207"
												class="base_txtdiv"
												onmouseover="javascript:onStopHover('030000145207#YCK#JNK')"
												onmouseout="onStopOut()">1449</span>
											</td>
											<td align="center" valign=""
												title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禹城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;23:00">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禹城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;23:00</td>
											<td align="center" valign=""
												title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;济南&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;23:47">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;济南&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;23:47</td>
											<td align="center" valign="">00:47</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="" title="--">--</td>
											<td align="center" valign="" title="--">--</td>
											<td align="center" valign="">4</td>
											<td align="center" valign="">4</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">18</td>
											<td align="center" valign="">122</td>
											<td align="center" valign="">--</td>
										</tr> -->
										<!-- <tr class="ev_light ">
											<td align="center" valign=""><span id="id_240000K2850Q"
												class="base_txtdiv"
												onmouseover="javascript:onStopHover('240000K2850Q#YCK#JAK')"
												onmouseout="onStopOut()">K285</span>
											</td>
											<td align="center" valign=""
												title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禹城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;23:18">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禹城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;23:18</td>
											<td align="center" valign=""
												title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;济南东&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;00:30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;济南东&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;00:30</td>
											<td align="center" valign="">01:12</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="" title="--">--</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="" title="--">--</td>
											<td align="center" valign="">5</td>
											<td align="center" valign="">29</td>
											<td align="center" valign="">--</td>
											<td align="center" valign="">30</td>
											<td align="center" valign="">147</td>
											<td align="center" valign="">--</td>
										</tr> -->
									</tbody>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
