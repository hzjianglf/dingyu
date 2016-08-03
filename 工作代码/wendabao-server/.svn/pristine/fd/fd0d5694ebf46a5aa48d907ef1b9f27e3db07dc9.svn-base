<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>

	<!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
	<meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>采供网</title>

    <!-- Bootstrap -->
    <!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css">
	
	<!-- 可选的Bootstrap主题文件（一般不用引入） -->
	<link rel="stylesheet" href="<%=basePath %>css/bootstrap-theme.min.css">
	
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="<%=basePath %>js/jquery-1.11.3.min.js"></script>	
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=basePath %>js/bootstrap.min.js"></script>
	<script src="<%=basePath %>js/md5-min.js"></script>
	<style type="text/css">
	.mfont{font-size:15px;}
	.s-top{margin-top:100px;}
	</style>
<meta content="" name="description"/>
<meta content="" name="author"/>

<title>采供网</title>
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=basePath %>css/shop/layoutb.css" rel="stylesheet" type="text/css" />
</head>
<body>
	
 <div class="container" style="padding-top: 300px;">
 
 <div class="form-group">		  
		  	<label for="inputPassword3" class="col-xs-2 control-label">三级联动</label>
		    <div class="col-xs-3">		      
				<select class="form-control zs-select-input" id='province' onchange='searchCity()'>					
				</select>				
		    </div>
		    <div class="col-xs-3">
			    <select class="form-control zs-select-input" id='city'  onchange='searchCounty()'>			    	
			    </select></div>
		    <div class="col-xs-3">
		    	<select class="form-control zs-select-input" id='county' >		    			
		    	</select> </div>
		    
		  </div>

 </div>
<script src="<%=basePath %>js/region.js"></script>
<script>
	
/* 
	$(document).ready(function(){
			selectProvince(); 			  			
		});
	function selectProvince(){  	
		
			$.ajax({
				url:"js/region.json",
				type:"post",
				dataType:"json",
				success:function(data){  					
					var p = data.region.provinces;
					
					$("#province").append("<option value='0'>选择省份</option>");
					for(var i = 0; i < p.length; i ++){
						$("#province").append("<option value='"+p[i].c+"'>"+p[i].n+"</option>");						
					}
					
				},
				error:function(xmlHttp,result){
					//alert(xmlHttp.status);
				}
			}); 
		}
	
	function searchCity(){
		$("#city").empty();
		$("#county").empty();
		var proviceCode = $("#province").find("option:selected").val();		
		if(proviceCode != '0'){
			$.ajax({
				url:"js/region.json",
				type:"post",
				dataType:"json",
				success:function(data){  					
					var cities = data.region.cities;
					for(var i = 0; i < cities.length; i ++){
						if(cities[i].province == proviceCode){
							var c = cities[i].city;
							$("#city").append("<option value='0'>选择城市</option>");
							for(var j = 0; j < c.length; j ++){
								$("#city").append("<option value='"+c[j].c+"'>"+c[j].n+"</option>");
							}
						}
					}
					
				}
			}); 
		}
	}
	
	function searchCounty(){
		$("#county").empty();
		var cityCode = $("#city").find("option:selected").val();
		if(cityCode != '0'){
			$.ajax({
				url:"js/region.json",
				type:"post",
				dataType:"json",
				success:function(data){  					
					var counties = data.region.counties;
					for(var i = 0; i < counties.length; i ++){
						if(counties[i].city == cityCode){
							var c = counties[i].county;
							$("#county").append("<option value='0'>选择县区</option>");
							
							for(var j = 0; j < c.length; j ++){
								$("#county").append("<option value='"+c[j].c+"'>"+c[j].n+"</option>");
							}
						}
					}
					
				}
			}); 
		}
		
	}
	 */
	

</script>       
	
	
	
	
	
	
</body>
