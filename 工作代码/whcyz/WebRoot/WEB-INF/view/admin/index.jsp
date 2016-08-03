<%@page import="com.whcyz.common.MainConfig"%>
<%@page import="com.whcyz.model.Article"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <title>后台管理</title>
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
<div class="modal fade" id="modal">
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
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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


  <nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/admin">故障录波联网后台</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a class="navitem" href="/admin/home" onclick="return false;">聚合首页</a></li>
        <li><a class="navitem" href="/accountmgr"  onclick="return false;">管理用户</a></li>
     <!--   <li><a class="navitem" href="/linkmgr"  onclick="return false;">连接</a></li>  -->
        <li><a class="navitem" href="/personmgr"  onclick="return false;">管理主站</a></li>
        <li><a class="navitem" href="/companymgr"  onclick="return false;">管理变电站</a></li>
       <!--  <li><a class="navitem" href="/articlemgr"  onclick="return false;">文章管理</a></li> -->
       
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/home#/articlemgr" style="color:red;" target="_blank">前台</a></li>
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
</nav>
<div class="container-fluid" id="mainContainer" style="background: #FFF;padding: 10px;">

</div>
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
</script>
  </body>
</html>
	<%
}
%>
