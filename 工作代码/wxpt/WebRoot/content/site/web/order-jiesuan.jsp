<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>微商城</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script src="../../web/js/jquery-1.8.3.js"></script>
<link href="../../web/css/shoppingstyle.css" rel="stylesheet" type="text/css">

</head>
<body>

<script type="text/javascript">
	function tijiao(){
	var str=0.1;
	var radio1=document.getElementsByName("shoppingAddressId");
	for(var i=0;i<radio1.length;i++){
		if(radio1.item(i).checked==true){
			str=radio1[i].value;
			break;
		}
		
	}
		if(str!=0.1){
			if(str!=0){
				$.ajax({
				type:"POST",
		          url:"../../site/web/order!getSaveOrder",
				  data : '' + $("#order_form").serialize(),
				  success:function (time){
		        		   window.location.href="/wxpt/site/web/order!getLastOneOrder?orderNum="+time;
		         }

		       });
			}else{
				var name=$("#name").val();
				var address=$("#address").val();
				var phone=$("#phone").val();
				if(name!=""&&address!=""&&phone!=""){
				$.ajax({
					type:"POST",
			          url:"../../site/web/order!getSaveOrder",
					  data : '' + $("#order_form").serialize(),
					  success:function (time){
			        		   window.location.href="/wxpt/site/web/order!getLastOneOrder?orderNum="+time;
			         }
	
			       });
				}else{
					alert("请完善收货地址信息！！");
				}
			}
		}else{
			alert("请设置收货地址信息！！");
		}
	}               
</script>
<div id="content">
<jsp:include page="/web/top.jsp" />
<form method="post" id="order_form" action="../../site/web/order!getSaveOrder">
                <h4 class="add_title" style="margin-top: 50px;"><a class="enter" href="../../site/web/order!getaddress" target="_blank">管理收货地址</a></h4>
                <div class="order_address_list">
                <h4 class="add_title">收货人地址</h4>
                                <script type="text/javascript">
                function ss(){
              
                var aa = document.getElementById("address_form");
                aa.style.display= ''; 
                }
                
                 function ss2(){
              
                var aa = document.getElementById("address_form");
                aa.style.display= 'none'; 
                }
                </script>
                <s:if test="no!=0">
                	<s:iterator value="lsa" var="asl">
                	<ul class="receive_add address_item selected_address">
                		<li class="radio"><input id="address_162" type="radio"  name="shoppingAddressId" value="<s:property value="#asl.shoppingAddressId"/>" onclick="ss2()" ></li>
	                    <li class="particular"><s:property value="#asl.address"/></li>
	                    <li class="name">收货人姓名: <s:property value="#asl.name"/></li>
	                    <li class="mobile">手机号码:<s:property value="#asl.phone"/></li>
                	</ul>
                	</s:iterator>
                	<ul class="new_receive_add address_item">
	                    <li class="radio"><input type="radio" name="shoppingAddressId"  id="shoppingAddressId" value="0" onclick="ss()"  ><!--checked="checked"  -->
	                    </li><li class="particular">使用新的收货地址</li>
                	</ul>
                	<ul class="fill_in_content" id="address_form" style="display:none; " ><!-- style="display:none; " -->
                </s:if>
                <s:if test="no==0">
                	<ul class="new_receive_add address_item">
                    <li class="radio"><input type="radio" checked="checked" name="shoppingAddressId"  id="shoppingAddressId" value="0" onclick="ss()"  ><!--checked="checked"  -->
                    </li><li class="particular">使用新的收货地址</li>
                </ul>
                <ul class="fill_in_content" id="address_form" style="display:black; " ><!-- style="display:none; " -->
                </s:if>
                                
                
                
                    <li>
                        <p class="title">收货人姓名</p>
                        <p><input type="text" name="name" id="name" class="text"></p>
                    </li>
                    <li>
                        <p class="title">详细地址</p>
                        <p><input type="text" name="address" id="address" class="text width1"></p>
                    </li>
                    <li>
                        <p class="title">手机号码</p>
                        <p><input type="text" id="phone" name="phone" class="text"></p>
                    </li>
                    <li>
                        <p class="title">&nbsp;</p>
                        <p>
                            <label>
                                <input type="checkbox" value="1" id="save_address" name="save_address">&nbsp;自动保存收货地址
                                <span class="explain">&nbsp;(&nbsp;选择后该地址将会保存到您的收货地址列表&nbsp;)&nbsp;</span>
                            </label>
                        </p>
                    </li>
                </ul>
                </div>
				
				<div class="order_delivery">
                <h4 class="add_title">选择配送方式</h4>
                <div class="fashion_list">
                	<s:iterator value="lll" var="lll">
                		
                		 <ul class="receive_add" shipping_id="2">
	                        <li class="radio"><input type="radio" name="logisticsId" value="<s:property value='#lll.logisticsId'/>" checked="checked"></li>
	                        <!-- <li class="fashion">EMS</li> -->
	                        <li class="pay"><s:property value="#lll.logisticsWay"/>:&nbsp;<span class="money" ectype="shipping_fee">¥<s:property value="#lll.logisticsPrice"/></span></li>
	                        <li class="explain"></li>
                    	</ul>
                	</s:iterator>
                </div>
                </div>                <div class="message_box">
                <script type="text/javascript">
                function postscript_activation(tt){
              var id=  document.getElementById("postscript");
              id.innerHTML="";
                   /*  if (!tt.name)
                    {
                        tt.value    = '';
                        tt.name = 'postscript';
                    } */

                }
                
                </script>
                <span class="add_title">给卖家的附言</span>
                <div class="message">
                    <textarea id="postscript" name="leave" onClick="postscript_activation(this);">选填，可以告诉卖家您对商品的特殊需求，如颜色、尺码等</textarea>
                </div>
                </div>               
                <div class="make_sure">
                    <p class="order_amount">产品总价:<strong class="fontsize3" id="order_amount">¥<span id="zongjia">${dsum }</span></strong></p>
                    <input type="hidden" name="zongjia" value="${dsum }">
                                        <div>
                       
                    <!-- <div class="submit"><input type="submit" class="btn enter" value="下单完成并支付"></div> -->
                    <div class="submit"><a class="btn enter" href="javascript:tijiao()">下单完成并支付</a></div>
                     <a href="../../site/web/order!getShopping" class="back">返回购物车</a> 
                    </div>
                </div>
                </form>
                
                </div>
<jsp:include page="/web/bottom.jsp" />
</body></html>