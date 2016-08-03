<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
	</head>
	<script type="text/javascript">
	    function saveBundle(){
            //if($('#fm').form('validate')){
        	   document.getElementById("fm").action="/asf/bundles/upload";
        	   document.getElementById("fm").submit();
            //}else{
            //	return;
            //}           
        }
	</script>
	<body>
	   <center>
			<div title="基本信息"  style="overflow:auto;">
				 <form id="fm" method="post"  enctype="multipart/form-data"  novalidate>  
				    <br><br>
					<table >
					   <tr>
							<td> 
								<div class="fitem">
									<label>名称:</label>
									<input id="bundlename"  name="bundlename"  >
								</div>							
							</td>      
						</tr>    
						<tr>
							<td> 
								<div class="fitem">
									<label>描述:</label>
									<input id="bundledesc"  name="bundledesc"  >
								</div>							
							</td>      
						</tr>    
						<tr>
							<td> 
								<div class="fitem">
								   <label>构件:</label>
								   <input type="file" name="myfiles" >	   	
								</div>							
							</td>      
						</tr> 
					</table>	
					<a href="javascript:void(0)" onClick="saveBundle();return false;">保存</a>
<!-- 					<a href="javascript:void(0)" onClick="javascript:window.history.back(-1);return false;">返回</a>             
 -->				</form>
			</div>
		</center>
	</body>
</html>