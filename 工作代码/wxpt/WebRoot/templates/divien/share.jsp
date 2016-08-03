<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/templates/"+request.getAttribute("templateName")+"/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/"+request.getAttribute("enterID")+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/m_style.css"/>
<script type="text/javascript" src="js/lib.min.js"></script>
<script src="js/jquery-1.8.3.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<title>分享</title>
<script type="text/javascript">
	function getUrl(menuID,menuNamID){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		if(menuID == 0){
			window.location.href=url[0]+"!index?"+url[1].split("?")[1];
		}else{
			
			window.location.href = url[0]+"!getMenuPage?"+url[1].split("?")[1]+"&menuNameID="+menuNamID +"&menuID="+menuID;
			//$("#url").html(url[0]+"!getMenuPage?"+url[1].split("?")[1]+"&menuNamID="+menuNamID);
		}
		
	}
	function getMap(){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getMap?"+url[1].split("?")[1];
		//+"&menuNameID="+menuNamID +"&menuID="+menuID;
	}
	function getMsg(){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getMsg?"+url[1].split("?")[1];
	}
	
	function getShare(){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getShare?"+url[1].split("?")[1];
	}
</script>
</head>
<body>
<header> <img
		src="<%=filePath%>/<s:property value="logoName"/>" width="144" height="34"/> </header>
<div id="box_root" class ="pageIndex">
<div id="box_mainBody">
<div id="box_main" >
<header id="box_header" class="header">
<div class="columnSpace" id="elem-navigation" name="meun"> 
<div id="Columns_navigation" class="Columns_navigation">	
<nav class="navigation01" >
<div class="navBarL active" id="columnprev" onClick="goPrev();">				
<span ></span></div>
<div class="navBody" id="singleScroll">
<ul class="sc_scroller" id="singleScrollUl" style="width:6000px">
<li><span class="active" id="column1"><a href="javascript:getUrl(0,0)">首页</a></span></li>
<s:iterator status="i" value="siteMenuList" var="siteMenu">
<li><span class="active" id="column<s:property value="#i.index+1"/>" ><a href="javascript:getUrl(<s:property value="#siteMenu.menuId"/>,<s:property value="#i.index+1"/>)"><s:property value="#siteMenu.menuName" escapeHtml="false"/></a></span></li>
</s:iterator>
</ul>
</div>
<div class="navBarR active" id="columnnext" onClick="goNext();">
<span ></span></div>
</nav>
<script type="text/javascript">
function setColumnWidth(){getTotalWidth("1");getTotalWidth("2");getTotalWidth("3");getTotalWidth("4");getTotalWidth("5");$("#singleScrollUl").css("width",ulwidth);		
}
</script>

</div> 
</div>
</header>

﻿</div>
</div>
</div>

<div id="box_root" class ="pageIndex">
<div id="box_mainBody">
<div id="box_main" >
<link rel="stylesheet" href="css/mShare.css"/>
<script type="text/javascript">
//<![CDATA[
	var url="http://testhtml5.uniqyw.com/";
	var summary="";
	var shareTitle="微信通官网";
	var pic="";
	$(function(){
	//中文网站	 
		$("#sina").click(function(){
			location.href="http://api.bshare.cn/share/mweibo?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;		
		});	
		$("#tengXun").click(function(){
			location.href="http://api.bshare.cn/share/qqmb?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});		
		$("#qzone").click(function(){
			location.href="http://api.bshare.cn/share/mqzone?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#wangyi").click(function(){
			location.href="http://api.bshare.cn/share/neteasemb?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#souhu").click(function(){
			location.href="http://api.bshare.cn/share/sohuminiblog?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#pengyou").click(function(){
			location.href="http://api.bshare.cn/share/qqxiaoyou?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#renren").click(function(){
			location.href="http://api.bshare.cn/share/renren?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;
			
		});
		$("#kaixin").click(function(){
			location.href="http://api.bshare.cn/share/kaixin001?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#douban").click(function(){
			location.href="http://api.bshare.cn/share/9dian?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#souhubai").click(function(){
			location.href="http://api.bshare.cn/share/sohubai?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#fanfou").click(function(){
			location.href="http://api.bshare.cn/share/fanfou?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;
			
		});
		$("#renjian").click(function(){
			location.href="http://api.bshare.cn/share/renjian?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#taojiang").click(function(){
			location.href="http://api.bshare.cn/share/taojianghu?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});	
		$("#hexun").click(function(){
			location.href="http://api.bshare.cn/share/hexunmb?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;		
		});
		$("#diandian").click(function(){
			location.href="http://api.bshare.cn/share/diandian?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#xinlangqing").click(function(){
			location.href="http://api.bshare.cn/share/sinaqing?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
		$("#tianya").click(function(){
			location.href="http://api.bshare.cn/share/tianya?url="+url+"&title="+shareTitle+"&publisherUuid=5e842597-ce5c-4fc4-9223-8c9659ea2ab8&summary="+summary;	
		});
	//英文网站
		$("#detail_more").tap(function(){
            if(document.getElementById('box_header').style.display != "block"){
                document.getElementById('box_header').style.display = "block";
				columnScroll.refresh();
				setColumnWidth();
           	 }else{
                document.getElementById('box_header').style.display = "none";
            }
			});	
	});
	
		function returnPage(){
			var url=document.referrer;
			if(url!=""){
				history.back(1);
			}else{
				location.href="/";
			}			
		}
// ]]>
</script>

<div id="PublicConfig_showShare01-001" class="null">
<section class="showShare">
<div class="shareWrap">
<p class="shareText">请点击分享到相应平台</p>
<ul>
<li class="active weiboSina" id="sina"><div><span class="icon"></span><span class="text">新浪微博</span></div></li>
<li class="active weiboQQ" id="tengXun"><div><span class="icon"></span><span class="text">腾讯微博</span></div></li>							
<li class="active spaceQQ" id="qzone"><div><span class="icon"></span><span class="text">QQ空间</span></div></li>
<li class="active weiboWangyi"id="wangyi"><div><span class="icon"></span><span class="text">网易微博</span></div></li>							
<li class="active weiboSohu" id="souhu"><div><span class="icon"></span><span class="text">搜狐微博</span></div></li>
<li class="active friend" id="pengyou"><div><span class="icon"></span><span class="text">朋友网</span></div></li>							
<li class="active renren" id="renren"><div><span class="icon"></span><span class="text">人人网</span></div></li>
<li class="active kaixin" id="kaixin"><div><span class="icon"></span><span class="text">开心网</span></div></li>							
<li class="active douban" id="douban"><div><span class="icon"></span><span class="text">豆瓣网</span></div></li>
<li class="active baishequ" id="souhubai"><div><span class="icon"></span><span class="text">搜狐白社区</span></div></li>				
<li class="active tianya" id="tianya"><div><span class="icon"></span><span class="text">天涯社区</span></div></li>
<li class="active fanfou" id="fanfou"><div><span class="icon"></span><span class="text">饭否</span></div></li>
<li class="active renjian" id="renjian"><div><span class="icon"></span><span class="text">人间网</span></div></li>														
<li class="active taojianghu" id="taojiang"><div><span class="icon"></span><span class="text">淘江湖</span></div></li>							
<li class="active hexun" id="hexun"><div><span class="icon"></span><span class="text">和讯</span></div></li>							 
<li class="active diandian" id="diandian"><div><span class="icon"></span><span class="text">点点</span></div></li>
<li class="active sinaQingbo" id="xinlangqing"><div><span class="icon"></span><span class="text">新浪轻博</span></div></li>							
</ul>
</div>
</section>				
</div>
</div>
</div>
</div>
<br><br><br>
<script type="text/javascript" src="js/qy_touch.js"></script>
<script type="text/javascript" src="js/m_common.js"></script>
<jsp:include page="foot.jsp"/>
</body>
</html>
