 function qudingjiesuan(){
 // var zhifubao = $('#zhifubao').val();
 // var yinlian = $('#yinlian').val();
  
 alert("sss");
   var zhifu=document.getElementsByName('1');
     var strNew;
     for(var i=0;i<zhifu.length;i++)
   {
     if(zhifu[i].checked)
       
        strNew=zhifu.item(i).getAttribute("value");  
   } 
   
   window.location.href="/vshop/site/web/zaixianzhifu!xuanzhifu?zhifu="+strNew+"";
//			  $.ajax({
//						  type:"post",
//				          url: '/vshop/site/web/zhifubao!xuanzhifu',
//				          data: {
//				        
//				        	 'zhifu':strNew
//				          },
//				        success: function (date) {
//				        	  
//			        	if(date==0){
//			        		alert("请选择支付方式"); 	
//			        	}else if(date==1){
//			        		alert("支付宝");
//				        }else if(date==2){
//				        	alert("银联");
//				        }
//				          },
//				          error: function (date) {
//				        	
//				          }
//				      });  
  } 
  
 
 
	function goToPay() {
		$("#frm_order").submit();
	}