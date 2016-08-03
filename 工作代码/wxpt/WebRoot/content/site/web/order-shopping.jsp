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
<div id="content">
<jsp:include page="/web/top.jsp" />
			<s:if test="lshopsize==0">
				<div class="null_shopping">
            	<div class="cart_pic"></div>
                <h4>您还没有宝贝，赶快去逛逛吧！</h4>
                <p>
                    <a href="../../site/web/index" class="enter">马上去逛逛</a>
                </p>
            </div>
			</s:if>
			<s:if test="lshopsize!=0">
            <h3 id="chose_all"><b class="ico">全选商品</b></h3>
            <form method="post" id="ff">
            	<s:iterator value="lshop" var="ll">
                    <ul class="cart_list">
                            <li id="cart_item_1912">
                        	<p class="goods_info">
                            	<span class="img"><a href="product!getPro?productId=<s:property value='#ll.product.productId'/>" target="_blank"><img src="../../web/images/<s:property value='#ll.product.productXimage'/>" alt="V商城" width="80" height="80"></a></span>
                                <span class="tit">
                                	<a href="product!getPro?productId=<s:property value='#ll.product.productId'/>" target="_blank"><s:property value="#ll.product.productName"/></a><br>
                                	<span>原价:</span><span class="old_price"><s:property value="#ll.product.price"/></span>
                                    <span>现价:</span><span class="price1" id="money<s:property value="#ll.shoppingId"/>"><s:property value="#ll.product.cheapPrice"/></span><br>
                                    <span>数量:</span>
                                    <span>
                                    	<input type="hidden" name="ppId" value="<s:property value='#ll.product.productId'/>">
                                    	<img src="../../web/images/subtract.gif" onClick="minus<s:property value="#ll.shoppingId"    />()" alt="decrease" width="12" height="13">
		                                <input id="num<s:property value="#ll.shoppingId"/>" name="ppsum" value="<s:property value='#ll.productSum'/>" orig="1" changed="1" onKeyUp="change_quantity(8, 1912, 25, this);" class="text1 width3" type="text" readonly="readonly">
		                                <img src="../../web/images/add.gif" onClick="add<s:property value="#ll.shoppingId"/>()" alt="increase" width="12" height="13">
                                    </span><br>
                                    <span>
                                    	<a class="del" href="/wxpt/site/web/order!getDelete?sid=<s:property value="#ll.product.productId"/>" > <img src="../../web/images/delete.png" width="15px" height="15px"></a>
                                    	<!-- <a class="del" href="javascript:;" onClick="drop_cart_item(8, 1912);"> <img src="../../web/images/delete.png" width="15px" height="15px"></a> -->
                                    	<%-- <a class="del" href="javascript:void(0);" onClick="dele(<s:property value='#ll.product.productId'/>);"> <img src="../../web/images/delete.png" width="15px" height="15px"></a> --%>
                                    </span>
                                </span>
                            </p>
                           <!--  <p class="buy_info">
                            <span class="total"><span>小计:</span><span class="price2" id="item1912_subtotal">¥19,800.00</span></span>
                            </p> -->                        
                        </li>
                       </ul>
                       
                       
                       
                       
                       <SCRIPT type="text/javascript"> 
            	
            	function addValue(value1,value2){
					    if(value1=="")value1="0";  
					    if(value2=="")value2="0";  
					    var temp1=0;  
					    var temp2=0; 
					      
					    if(value1.indexOf(".")!=-1)  
					     temp1=value1.length - value1.indexOf(".")-1;  
					    if(value2.indexOf(".")!=-1)  
					     temp2=value2.length - value2.indexOf(".")-1;   
					    var temp=0;  
					      
					    if(temp1>temp2)  
					    temp = (parseFloat(value1)+parseFloat(value2)).toFixed(temp1);  
					    else 
					    temp = (parseFloat(value1)+parseFloat(value2)).toFixed(temp2);   
					      
					    return temp;  
					    
					}
                  function addValue2(value1,value2){  
					    if(value1=="")value1="0";  
					    if(value2=="")value2="0";  
					    var temp1=0;  
					    var temp2=0;  
					    if(value1.indexOf(".")!=-1)  
					     temp1=value1.length - value1.indexOf(".")-1;  
					    if(value2.indexOf(".")!=-1)  
					     temp2=value2.length - value2.indexOf(".")-1;   
					      
					    var temp=0;  
					      
					    if(temp1>temp2)  
					    temp = (parseFloat(value1)-parseFloat(value2)).toFixed(temp1);  
					    else 
					    temp = (parseFloat(value1)-parseFloat(value2)).toFixed(temp2);   
					      
					    return temp;  
					    
					}
       
        function add<s:property value="#ll.shoppingId"/>(){  
              var money=document.getElementById("money<s:property value='#ll.shoppingId'/>").innerHTML;
              //alert(document.getElementById("money").innerHTML);
              // var money=$("#money").innerHTML();
                 document.getElementById("num<s:property value="#ll.shoppingId"    />").value++;
                 
                var xiao=document.getElementById("xiaoji").innerHTML;
                
                 /* BigDecimal a=new BigDecimal(xiao);
                BigDecimal b=new BigDecimal(money);
                BigDecimal c=a.add(b); */ 
                var sum1 =addValue(xiao,money);
                 	/* var sum1=c.toString(); */ 
                 	if(sum1>=100000){      
                 	 var num=document.getElementById("num<s:property value="#ll.shoppingId"    />").value; 
                 	 var num=num-1;    
                 	 document.getElementById("num<s:property value="#ll.shoppingId"    />").value=num;      
                 	alert("交易金额小于10万！！");
                 	
                 	 var sum1 =addValue(xiao,money);
                 	 var sum1=sum1-money;
                 		document.getElementById("xiaoji").innerHTML=sum1;
                 	}
                	 document.getElementById("xiaoji").innerHTML=sum1;
  				
        }  
          
        function minus<s:property value="#ll.shoppingId"    />(){  
            var num = document.getElementById("num<s:property value="#ll.shoppingId"    />").value;  
            if(num==1){  
             alert("产品数量不能为0、负！！")
                document.getElementById("num<s:property value="#ll.shoppingId"    />").value = 1; 
                
            }else{  
            var money=document.getElementById("money<s:property value="#ll.shoppingId"    />").innerHTML;
                document.getElementById("num<s:property value="#ll.shoppingId"    />").value--;           
                 var xiao=document.getElementById("xiaoji").innerHTML;          
              var zmoney=0;
               /*  var sum1 =   parseFloat(xiao)-parseFloat(money*1); */
                	 var sum1 =addValue2(xiao,money);
                	 document.getElementById("xiaoji").innerHTML=sum1;
                 
            }  
        }  
          
        function check(){  
            var num = document.getElementById("num");  
            if (isNaN(num.value)){  
                alert("只能输入数字！");  
                num.value="";  
            }  
        } 
        
        function yan(){
        alert("订单金额不能超过10万");
        }
        
         /* function update(){
         url="/vshop/site/web/order!getShopping";
              $("#content").load(url);
              
              //document.getElementById("content").location.reload();
              }  */
          /* function jiesuan(){
          var jiage=document.getElementById("xiaoji").innerHTML;
          window.location.href="/vshop/site/web/order!getJiesuan?jiage="+jiage;
         } */
         function jiesuan(){
        
         var jiage=document.getElementById("xiaoji").innerHTML;
         $.ajax({
		          type:"POST",
		          url:"/wxpt/site/web/order!getChangeShoppingCar",
					data : '' + $("#ff").serialize()+"&jiage="+jiage,
		         success:function (size){
		         if(size==0){
		         	window.location.href="/wxpt/site/web/order!getJiesuan";
		         }else{
		         
		         	alert("订单中某商品数量超过库存量");
		         }
		        	  
		        		   
		         },
			  	 error:function (){
			  	 }
		       });
          
         }
        //--> 
    </SCRIPT> 
                       </s:iterator>
                    <div class="buy_foot">
                        <p class="goods_amount"><span>商品总价:</span><strong class="fontsize1" id="cart8_amount">¥<span id="xiaoji"><s:property value="dsum"/></span></strong></p>
                        <%-- <input id="xiaoji" value="<s:property value="dsum"/>"> --%>
                        <p class="jiesuan_btn">
                            <a href="javascript:void(0)" onclick="jiesuan()" class="btn"><span>去结算</span><img src="../../web/images/qujiesuan.png" width="50%"></a>
                        </p>
                    </div>
</form>
</s:if>
        </div>
        <jsp:include page="/web/bottom.jsp" />

</body>
</html>
