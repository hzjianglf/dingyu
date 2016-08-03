<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/templates/"
			+ request.getAttribute("templateName") + "/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/0" + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--自适应宽度,并不允许缩放-->
<script src="/wxpt/templates/sheying/js/html5.js"></script>
<script src="/wxpt/templates/sheying/js/html5media.min.js"></script>
<script src="/wxpt/templates/sheying/js/wgw.gs.lib.min.js"></script>
<script src="/wxpt/templates/sheying/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="/wxpt/templates/sheying/js/qy_touch.js"></script>

<script type="text/javascript">

	function show(value, fansImageId, fansUserId) {
	//alert(fansImageId);
	var fansName=$("#fansName").val();
	var enterId2=$("#enterId2").val();
	//alert(fansName);
		$
				.ajax({
					url : "/wxpt/site/index!getImageByUserId",
					type : "post",
					datatype : "json",
					data : {
						"fansUserId" : fansUserId,
						"fansImageId":fansImageId,
						"enterId2":enterId2,
						"fansName":fansName
					},
					success : function(date) {
		                var ulStr="<div class=\"FrontSlide\"><div class=\"bannerScrollWrap\"><div id=\"bannerScroll\">";
						 ulStr += "<ul class=\"bannerList sc_scroller\">";
						var str = eval(date);
						for ( var i = 0; i < str.length; i++) {
							ulStr += "<li><img id=\"Img"+str[i].fansImageId+"\" src=\""
									+ str[i].fansImageValue
									+ "\" width='100%' height='320' style='margin-left:5%;' />";
							ulStr +="<div style=\"margin-left:5%; background:#000;\">";
							ulStr +="<a onClick=\"huZan('"
													+ str[i].fansImageId
													+ "','"
													+ str[i].fansName
													+ "')\"><span style=' background:#000; width:50px;  '><img height='15' src='/wxpt/templates/sheying/images/zan.png'  /><span style='color:#FFFFFF;  font-size:15px; '  id=\"zanStr"+str[i].fansImageId+"\" >"+str[i].state+"</span></span></a>";
						    ulStr +="<a  onClick=\"closeDiv("+str[i].fansImageId+")\"> <div style='width:60px; background:#000; color:#FFFFFF; float:right; margin-right:-10%; font-size:15px;'>&nbsp;&nbsp;返回</div> </a> </div>";
						    ulStr +="</li>";
						}
						ulStr += "</ul> ";
						ulStr +="</div></div></div>";
						$("#dlDiv").html(ulStr);
					//	alert(ulStr);
						//document.getElementById("Img").src = value;
						var script = document.createElement("SCRIPT");
						script.defer = true;
						script.type = "text/javascript";
						script.src = "/wxpt/templates/sheying/js/qy_touch.js";
						document.getElementsByTagName("HEAD")[0]
								.appendChild(script);
								
					},
					error : function() {
					}
				});
		document.getElementById("overDiv").style.display = "block";
		document.getElementById("dlDiv").style.display = "block";

	}
	function huZan(fansImageId, fansName) {
	//alert(fansImageId);
	var enterId2=$("#enterId2").val();
		$.ajax({
			url : "/wxpt/site/index!huZan",
			data : {
				"fansImageId" : fansImageId,
				"enterId2" : enterId2,
				"fromUser" : fansName,
				
			},
			dataType : "json",
			success : function(date) {
				var str = eval(date);
				
				if (str.state == 1) {
				
					$("#zanStr"+fansImageId)
							.text("已赞");
					
				}
				if (str.state == 0) {
				
					$("#zanStr"+fansImageId)
							.text("赞");
				}
			},
			error : function() {
				alert("网络异常");
			}
		});
	}
	function closeDiv(famageid) {

	//alert(famageid);
		document.getElementById("overDiv").style.display = "none";
		document.getElementById("dlDiv").style.display = "none";
		var dizhi = window.location.href;
		window.location.href = dizhi;
	}
</script>
<link href="/wxpt/templates/sheying/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="/wxpt/templates/sheying/css/m_style.css" rel="stylesheet"
	type="text/css" />

<script src="http://a.tbcdn.cn/mw/base/libs/seajs/1.2.0/sea.js"></script>
<title>微聚家</title>
</head>

<body>
	<div class="top" style="margin-top:20px ">
		<h3>微摄影大赛</h3>
	</div>
	<div class="line"></div>


	<s:iterator value="fansList" var="fansUser" status="status">
		<section class="menu1" style="margin-top:10px">
			<div style="width:5%; float:left;">
				<img
					src="/wxpt/web/images/${enterId2}/<s:property value="#fansUser.touxiang"/>"
					class="menu1_img">
			</div>
			<div style="width:82%; float:right;">
				<ul>
					<li><strong><s:property value="#fansUser.nickname" />
					</strong>
					</li>
					<li class="menu_font"><s:property value="#fansUser.signature" />
					</li>
					<li style="background: #ccc; height: 80px;">
						<div id="tbh5v0">
							<div class="screen-wrap fullscreen" id="bodyCont">
								<div>
									<script>
										seajs
												.use('http://a.tbcdn.cn/mw/app/phone_cate/h5/phone_cate_tms.js');
									</script>
								</div>
								<div class="brandList">
									<div class="middList">
										<ul>
											<s:iterator value="fansImageList" var="fansImage"
												status="index">
												<s:if
													test="#fansImage.fansUser.fansUserId==#fansUser.fansUserId">
													<li><a
														onClick="show('<s:property value="#fansImage.fansImageValue"/>','<s:property value="#fansImage.fansImageId"/>','<s:property value="#fansUser.fansUserId"/>')">
															<img
															src='<s:property value="#fansImage.fansImageValue"/>'
															> </a>
													</li>
												</s:if>
											</s:iterator>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li class="menu_font">
						<div class="menu_list1">
							<s:property value="#fansUser.updateTime" />
						</div>
						<div class="menu_list2">

							<img src="/wxpt/templates/sheying/images/zan.png"> <span
								id="<s:property value="#status.index"/>zan"> <s:if
									test="fansImageVisitList.isEmpty()">
						</s:if> <s:if test="#fansUser.count!=0">									
										已赞(<s:property value="#fansUser.count" />)
															
									</s:if> <s:if test="#fansUser.count==0">
											赞												
									</s:if> </span>

						</div></li>
				</ul>
			</div>
		</section>
		<div class="line"></div>

	</s:iterator>
	<div
		style="background: #d7d5d5; width: 50px; height: 100px; position: absolute; z-index: 1"></div>
	<input id="page" type="hidden" value="${page}">
	<input id="sumPage" type="hidden" value="${sumPage}">
	<input id="fansName" type="hidden" value="${fansName}">

	<input id="enterId2" type="hidden" value="${enterId2}">



	<div align="center">
		<a href="javascript:last()">上一页</a> <a href="javascript:next()">下一页</a>
	</div>



	<script type="text/javascript"
		src="/wxpt/templates/sheying/js/m_common.js"></script>
	<div id="dlDiv" style="display:none;">

	</div>

	<div id="overDiv" style="display:none;"></div>
	<script type="text/javascript">
		function zan(index, fansUserId) {
			var page = $("#page").val();
			if (page != 1) {
				var fromUser = window.location.hreenterId2f.split("=")[2];
			} else {
				var fromUser = window.location.href.split("=")[1];
			}

			$.ajax({
				url : "/wxpt/site/index!huZan",
				data : {
					"fansUserId" : fansUserId,
					"fromUser" : fromUser
				},
				dataType : "json",
				success : function(date) {
					var str = eval(date);
					if (str.state == 1) {
						$("#" + index + "zan").html("已赞");
					}
					if (str.state == 0) {
						$("#" + index + "zan").html("赞");
					}
				},
				error : function() {
					alert("网络异常");
				}
			});
		}

		function next() {
		var fromUser=$("#fansName").val();
			var page = $("#page").val();
			var sumPage = $("#sumPage").val();
			if (page == 1&&page != sumPage) {
				var pages = page * 1 + 1;
				//var fromUser = window.location.href.split("=")[1];
				window.location.href = "/wxpt/site/index!getFansAndFansImage?page="
						+ pages + "&fansName=" + fromUser+ "&enterId2=" + $("#enterId2").val();
			} if (page == sumPage) {
				alert("已到最后一页！！");
				return;
			} else {
				var pages = page * 1 + 1;
				//var fromUser = window.location.href.split("=")[2];
				
				window.location.href = "/wxpt/site/index!getFansAndFansImage?page="
						+ pages + "&fansName=" + fromUser +  "&enterId2=" + $("#enterId2").val();
			}

		}
		function last() {
	   var fromUser=$("#fansName").val();
			var page = $("#page").val();
			if (page == 1) {
				alert("已经是第一页！！");
				return;
			}

			var pages = page * 1 - 1;
			//var fromUser = window.location.href.split("=")[2];
			if (pages == 1) {
				window.location.href = "/wxpt/site/index!getFansAndFansImage?fansName="
						+ fromUser+ "&enterId2=" + $("#enterId2").val();
			} else {
				window.location.href = "/wxpt/site/index!getFansAndFansImage?page="
						+ pages + "&fansName=" + fromUser+ "&enterId2=" + $("#enterId2").val();
			}

		}
	</script>

</body>
</html>
