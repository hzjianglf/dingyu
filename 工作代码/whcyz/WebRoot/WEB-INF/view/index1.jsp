<%@page import="com.whcyz.common.MainConfig"%>
<%@page import="com.whcyz.model.Article"%>

<%@ page language="java"
 import="java.util.*" pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj=session.getAttribute("user");
if(obj==null){
	response.sendRedirect(basePath+"account/lorr?action=login");
}else{
	%> 
	
	
	
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>前台管理</title>
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	
	<link rel="icon" type="image/png" href="assets/i/favicon.ico">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/admin.css">
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="assets/js/ie-emulation-modes-warning.js"></script>
	<!--[if lt IE 9]>
	  <script src="assets/js/html5shiv.min.js"></script>
	  <script src="assets/js/respond.min.js"></script>
	<![endif]-->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/jquery.cookie.js"></script>
	<script src="assets/js/jquery.form.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/bootstrap.autocomplete.js"></script>
	<script language="javascript" type="text/javascript" src="WdatePicker.js"></script>
	<script src="assets/js/admin.js"></script>
	<script src="assets/js/bootstrap-treeview.js"></script>     
	<!--[if lt IE 8]>
	<script src="assets/js/ie.js"></script>
	<![endif]-->
  </head>
  
  <body>
  
<div class="appLoading" style="display: none;" tabindex="-1" id="appLoading">
  <div class="loading-dialog">
    <div class="loading-msg" id="appLoadingMsg">正在处理...</div>
    <div class="loading-icon" id="appicon"></div>
  </div>
</div>
< <div class="modal fade" id="modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="modalTitle"></h4>
      </div>
      <div class="modal-body" id="modalHtml">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="closeModalBtn" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="submitModalBtn">提交</button>
      </div>
    </div>/.modal-content
  </div>/.modal-dialog
</div>/.modal 

<div class="modal fade bs-example-modal-lg" id="modal-lg">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="modalTitle-lg"></h4>
      </div>
      <div class="modal-body" id="modalHtml-lg">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="closeModalBtn-lg" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="submitModalBtn-lg">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


  <%-- <nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/admin">故障录波联网系统</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
       <!--  <li class="active"><a class="navitem" href="/admin/home" onclick="return false;">聚合首页</a></li> -->
       <!--  <li><a class="navitem" href="/accountmgr"  onclick="return false;">管理用户</a></li> -->
      <!--   <li><a class="navitem" href="/linkmgr"  onclick="return false;">友情链接</a></li> -->
        <li><a class="navitem" href="/personmgr"  onclick="return false;">管理主站</a></li>
        <li><a class="navitem" href="/companymgr"  onclick="return false;">管理变电站</a></li>
       <li><a class="navitem" href="/articlemgr"  onclick="return false;">文章管理</a></li>
       
       
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/admin" style="color:red;" target="_blank">后台</a></li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.nickname } <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a  onclick="return false;" style="cursor: pointer;" id="modifyPwdBtn" >修改密码</a></li>
            <li class="divider"></li>
            <li><a href="logout">退出</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
    
       
    
    
    
  </div><!-- /.container-fluid -->
</nav> --%>
 <div class="header box">
  	<!-- header top -->
	  <div class="header-top">
	  <!-- 	<a class="logo fleft"></a> -->
	  	<div class="box clearfix logoright">
	  		<div class="fleft t-wh shadowfont pt15 font-20 ml20">故障录波器联网系统</div>
	  		<div class="fright ">
	  		<c:choose><c:when test="${not empty user}">
	  			<c:if test="${user.permission!=2 }">
	  			<a href="admin"  class="fleft pr10 t-wh" style="line-height: 34px;">[后台]</a>
	  			</c:if>
		  		<a href="account/detail" title="账户详情  密码修改" class="fleft plr10 t-wh" style="line-height: 34px;">[${user.nickname }]</a>
		  		<a href="logout" class="fleft plr10 t-wh" style="line-height: 34px;">[退出]</a>
	  		</c:when><c:when test="${empty user}">
	  		<a href="login" class="fleft plr10 t-wh" style="line-height: 34px;">[登录]</a>
	  		<!-- <a href="register" class="fleft pr10 t-wh" style="line-height: 34px;">[注册]</a> -->
	  		</c:when>
	  		</c:choose> <%-- <div class="inline searchDiv">
				      <input type="text" id="keywords" maxlength="100" value="${q }"  class="search inline fleft" placeholder="创业"  />
				      <div class="searchBtn inline fleft" onclick="search()">搜索</div>
			      </div>  --%>
	  		</div>
	  	</div>
	  </div>
	  <!-- header 导航 -->
	  <div class="header-navs">
	  	<ul>
	    <!--   <li class="active"><a class="navitem" href="/companymgr"  onclick="return false;">管理变电站</a></li>  -->
	  	<!--  <li><a class="navitem" href="/personmgr"  onclick="return false;">管理主站</a>	  	 	  	 
	  	 </li>  -->
	  	 
	  	<li><a class="navitem" href="/articlemgr"  onclick="return false;">录波器</a></li>
	  	
	  	
	  	
	  	</ul>
	  
	  </div>
  </div> 
      <!--  <div class="row">
           <div class="col-sm-4">
            <h2>Default</h2>
           <div id="treeview1" class=""></div>
        </div>
        
      </div> 
 -->
     	
      


<!-- <div class="container-fluid" id="mainContainer" style="background: #FFF;padding: 10px;">

</div>
  -->
<script type="text/javascript">
$("#modifyPwdBtn").click(function(){
			appLoading("正在执行,请稍后...");
			$("#modalTitle").text("密码修改");
			var url="/account/initModifyPwd?rand="+Math.random();
			$("#modalHtml").empty();
			$.get(url,function(data){
				closeAppLoadingNow();
				$("#modalHtml").html(data);
				$("#modal").modal("show");
				$("#submitModalBtn").unbind().click(function(){
					submitMe();
				});
				
			});
			
});

     //给注册按钮绑定单击处理
	

$(function() {

        var defaultData = [
          {
            text: 'Parent 1',
            href: '#parent1',
            tags: ['4'],
            nodes: [
              {
                text: 'Child 1',
                href: '#child1',
                tags: ['2'],
                nodes: [
                  {
                    text: 'Grandchild 1',
                    href: '#grandchild1',
                    tags: ['0']
                  },
                  {
                    text: 'Grandchild 2',
                    href: '#grandchild2',
                    tags: ['0']
                  }
                ]
              },
              {
                text: 'Child 2',
                href: '#child2',
                tags: ['0']
              }
            ]
          },
          {
            text: 'Parent 2',
            href: '#parent2',
            tags: ['0']
          },
          {
            text: 'Parent 3',
            href: '#parent3',
             tags: ['0']
          },
          {
            text: 'Parent 4',
            href: '#parent4',
            tags: ['0']
          },
          {
            text: 'Parent 5',
            href: '#parent5'  ,
            tags: ['0']
          }
        ];

        var alternateData = [
          {
            text: 'Parent 1',
            tags: ['2'],
            nodes: [
              {
                text: 'Child 1',
                tags: ['3'],
                nodes: [
                  {
                    text: 'Grandchild 1',
                    tags: ['6']
                  },
                  {
                    text: 'Grandchild 2',
                    tags: ['3']
                  }
                ]
              },
              {
                text: 'Child 2',
                tags: ['3']
              }
            ]
          },
          {
            text: 'Parent 2',
            tags: ['7']
          },
          {
            text: 'Parent 3',
            icon: 'glyphicon glyphicon-earphone',
            href: '#demo',
            tags: ['11']
          },
          {
            text: 'Parent 4',
            icon: 'glyphicon glyphicon-cloud-download',
            href: '/demo.html',
            tags: ['19'],
            selected: true
          },
          {
            text: 'Parent 5',
            icon: 'glyphicon glyphicon-certificate',
            color: 'pink',
            backColor: 'red',
            href: '',
            tags: ['available','0']
          }
        ];

        var json = '[' +
          '{' +
            '"text": "Parent 1",' +
            '"nodes": [' +
              '{' +
                '"text": "Child 1",' +
                '"nodes": [' +
                  '{' +
                    '"text": "Grandchild 1"' +
                  '},' +
                  '{' +
                    '"text": "Grandchild 2"' +
                  '}' +
                ']' +
              '},' +
              '{' +
                '"text": "Child 2"' +
              '}' +
            ']' +
          '},' +
          '{' +
            '"text": "Parent 2"' +
          '},' +
          '{' +
            '"text": "Parent 3"' +
          '},' +
          '{' +
            '"text": "Parent 4"' +
          '},' +
          '{' +
            '"text": "Parent 5"' +
          '}' +
        ']';


        $('#treeview1').treeview({
          data: defaultData
         
        });
       
        });
</script>
  </body>
 <%-- <div class="clearfix"></div>
<%@include file="common/footer.html" %> --%>
<div class="container-fluid">
  <div class="row-fluid">
  <!--   <div class="span2">
     <div class="row">
           <div class="col-sm-4">
            <h2>Default</h2>
           <div id="treeview1" class=""></div>
        </div>
        
      </div>
      </div> -->
    <div class="span10">


     <div class="container-fluid" id="mainContainer" style="background: #FFF;padding: 10px;">

    </div>
    </div>
  </div>
</div>
</html>
 
	<%
}
%>
