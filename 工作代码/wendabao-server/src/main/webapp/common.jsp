<%
	String bPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath() + "/";
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=bPath%>css/jquery.mobile.flatui.css">

 <!-- Bootstrap -->
<link href="<%=bPath%>css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=bPath %>css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=bPath %>css/list.css">
<link rel="stylesheet" href="<%=bPath %>css/uploadImg.css">
<link rel="stylesheet" href="<%=bPath%>css/custom.css">
<link rel="stylesheet" href="<%=bPath%>css/xcConfirm.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script src="<%=bPath%>js/jquery-1.11.3.min.js"></script>
<script src="<%=bPath%>js/storage.js"></script>
<script src="<%=bPath%>js/jquery.lazyload.js"></script>
<script src="<%=bPath%>js/bootstrap.min.js"></script>
<script src="<%=bPath%>js/common.js"></script>
<script src="<%=bPath%>js/constant.js"></script>
<script src="<%=bPath%>js/page.js"></script>
<script src="<%=bPath%>js/modalDialog.js"></script>
<script src="<%=bPath%>js/xcConfirm.js"></script>

<script src="<%=bPath%>js/md5-min.js"></script>
<%-- <script src="<%=bPath%>js/region.js"></script> --%>
<script src="<%=bPath %>js/ajaxfileupload.js" type="text/javascript"></script>  

<!-- 去掉底部滚动条 -->
<%-- <link rel="stylesheet" href="<%=bPath %>css/scolleHidden.css"> --%>

