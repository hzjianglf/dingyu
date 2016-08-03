
$(document).ready(function() {
		$(".stk").click(function() {
			checkNum();
		});
	});
function checkNum(){
	var num = $("#quantity").val();
	var invent = $("#invent").val();
	if(num<=invent){
		addCar();
	}else{
		checkShow();
	}
	
}
function checkShow(){
	var time;
	var shopcarnum = new Object();
	shopcarnum = $("#checkNum");
	shopcarnum.slideDown("slow");
	window.setTimeout("checkclose()", 7000);
	clearTimeout(time);
}
function checkclose() {
	$("#checkNum").slideUp("slow");
}
	function addCar() {
		var show;
		var sum;
		var time;
		var shopcarBuy = new Object();
		var shopcar = new Object();
		var shopcarFail = new Object();
		shopcarBuy = $("#shopcarBuy");
		shopcar = $("#shopcar");
		shopcarFail = $("#shopcarFail");
		$.ajax({
			url : "product!saveCar",
			type : 'post',
			dataType : "json",
			data : {
				'productId' : $("#productId").val(),
				'productSum' : $("#quantity").val(),
			},
			success : function(json) {
				var obj = eval(json);
				show = obj[0].show;
				sum = obj[0].shopSum + 1;
				if (show == "2") {
					carclose();
					shopcarBuy.slideDown("slow");
					window.setTimeout("buyclose()", 7000);
					clearTimeout(time);
				} else if (show == "1") {
					shopcarFail.slideDown("slow");
					window.setTimeout("failclose()",7000);
					clearTimeout(time);
				} else {
					buyclose();
					shopcar.slideDown("slow");
					$("#prosum").html(sum);
					window.setTimeout("carclose()", 7000);
					clearTimeout(time);
				}
			},
			error : function(json) {
				show = eval(json).show;
				sum = eval(json).shopSum;
				if (show == "2") {
					carclose();
					shopcarBuy.slideDown("slow");
					window.setTimeout("buyclose()", 7000);
					clearTimeout(time);
				} else if (show == "1") {
					shopcarFail.slideDown("slow");
					window.setTimeout("failclose()", 7000);
					clearTimeout(time);
				} else {
					buyclose();
					shopcar.slideDown("slow");
					$("#prosum").html(sum);
					window.setTimeout("carclose()", 7000);
					clearTimeout(time);
				}
			}
		});
	}
	$(function() {
		$("#appraise").hide();
		$("#default").show();
	});
	function buyclose() {
		$("#shopcarBuy").slideUp("slow");
	}
	function carclose() {
		$("#shopcar").slideUp("slow");
	}
	function failclose() {
		$("#shopcarFail").slideUp("slow");
	}
	function showUrl() {
		$("#appraise").hide();
		$("#default").show();
	}
	function showApp() {
		var str = "\<table width='200' style='table-layout:fixed;'\>";
		$.ajax({
			url : "appraise!getAppraise",
			type : 'post',
			dataType : "json",
			data : {
				'productId' : $("#productId").val(),
				'page':1,
			},
			success : function(json) {
				var obj = eval(json);
				for ( var int = 0; int < obj.length; int++) {
					if(obj[int].appraiseContent == ""){
						
					}else{
						str = str+"\<tr\> \<table  width='100%'\>\<tr\> \<td width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员评价：\</a\>"+ obj[int].appraiseContent  +"\</td\>\</tr\>" +
						"\<tr \>\<td  align='right' width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员：\</a\>"+ obj[int].username  +"\</td\> \</tr\>" +
						"\<tr \>\<td  align='right'width='70%' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseTime  +"\</td\>\</tr\>"
						if(obj[int].appraiseHuifu != ""){
							str = str+ "\<tr \> \<td width='100%' align='left' style='word-break:break-all;'\>\<a style='color: red'\>商家回复：\</a\>"+ obj[int].appraiseHuifu  +"\</td\>\</tr\>" +
							"\<tr\>\<td width='100%' align='right' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseHuifuTime  +"\</td\>\</tr\>" +
							"\</table\>\</tr\>\<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
						}else{
							str = str+"\</table\>\</tr\> \<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
						}
					}
				}
				str=str+"\</table\>"
				document.getElementById("apptable").innerHTML = str;
				$("#pageCount").html(obj[0].pageCount);
				$("#listCount").html(obj[0].listNumber);
				$("#page").val(obj[0].currentPage);
				if(obj[0].currentPage == 1){
					$("#last").hide();
					$("#next").show();
				}
				if(obj[0].currentPage == obj[0].pageCount){
					$("#next").hide();
					$("#last").show();
				}
				
			},
			error : function(json){
				var obj = eval(json);
				for ( var int = 0; int < obj.length; int++) {
					if(obj[int].appraiseContent == ""){
						
					}else{
						str = str+"\<tr\> \<table  width='100%'\>\<tr\> \<td width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员评价：\</a\>"+ obj[int].appraiseContent  +"\</td\>\</tr\>" +
						"\<tr \>\<td  align='right' width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员：\</a\>"+ obj[int].username  +"\</td\> \</tr\>" +
						"\<tr \>\<td  align='right'width='70%' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseTime  +"\</td\>\</tr\>"
						if(obj[int].appraiseHuifu != ""){
							str = str+ "\<tr \> \<td width='100%' align='left' style='word-break:break-all;'\>\<a style='color: red'\>商家回复：\</a\>"+ obj[int].appraiseHuifu  +"\</td\>\</tr\>" +
							"\<tr\>\<td width='100%' align='right' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseHuifuTime  +"\</td\>\</tr\>" +
							"\</table\>\</tr\>\<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
						}else{
							str = str+"\</table\>\</tr\> \<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
						}
					}
				}
				str=str+"\</table\>"
				document.getElementById("apptable").innerHTML = str;
				$("#pageCount").html(obj[0].pageCount);
				$("#listCount").html(obj[0].listNumber);
				$("#page").val(obj[0].currentPage);
				if(obj[0].currentPage == 1){
					$("#last").hide();
				}
				if(obj[0].currentPage == obj[0].pageCount){
					$("#next").hide();
				}
			}
		});
		$("#default").hide();
		$("#appraise").show();
	}
		function nextApp() {
			var str = "";
			var i=$("#page").val();
			var page=i*1+1;
			$.ajax({
				url : "appraise!getAppraise",
				type : 'post',
				dataType : "json",
				data : {
					'productId' : $("#productId").val(),
					'page':page,
				},
				success : function(json) {
					var obj = eval(json);
					for ( var int = 0; int < obj.length; int++) {
						if(obj[int].appraiseContent == ""){
							
						}else{
							str = str+"\<tr\> \<table  width='100%'\>\<tr\> \<td width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员评价：\</a\>"+ obj[int].appraiseContent  +"\</td\>\</tr\>" +
							"\<tr \>\<td  align='right' width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员：\</a\>"+ obj[int].username  +"\</td\> \</tr\>" +
							"\<tr \>\<td  align='right'width='70%' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseTime  +"\</td\>\</tr\>"
							if(obj[int].appraiseHuifu != ""){
								str = str+ "\<tr \> \<td width='100%' align='left' style='word-break:break-all;'\>\<a style='color: red'\>商家回复：\</a\>"+ obj[int].appraiseHuifu  +"\</td\>\</tr\>" +
								"\<tr\>\<td width='100%' align='right' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseHuifuTime  +"\</td\>\</tr\>" +
								"\</table\>\</tr\>\<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
							}else{
								str = str+"\</table\>\</tr\> \<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
							}
						}
					}
					str=str+"\</table\>"
					document.getElementById("apptable").innerHTML = str;
					$("#pageCount").html(obj[0].pageCount);
					$("#listCount").html(obj[0].listNumber);
					$("#page").val(obj[0].currentPage);
					if(obj[0].currentPage == 1){
						$("#last").hide();
					}
					if(obj[0].currentPage == obj[0].pageCount){
						$("#next").hide();
						$("#last").show();
						
					}
					
				},
				error : function(json){
					var obj = eval(json);
					for ( var int = 0; int < obj.length; int++) {
						if(obj[int].appraiseContent == ""){
							
						}else{
							str = str+"\<tr\> \<table  width='100%'\>\<tr\> \<td width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员评价：\</a\>"+ obj[int].appraiseContent  +"\</td\>\</tr\>" +
							"\<tr \>\<td  align='right' width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员：\</a\>"+ obj[int].username  +"\</td\> \</tr\>" +
							"\<tr \>\<td  align='right'width='70%' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseTime  +"\</td\>\</tr\>"
							if(obj[int].appraiseHuifu != ""){
								str = str+ "\<tr \> \<td width='100%' align='left' style='word-break:break-all;'\>\<a style='color: red'\>商家回复：\</a\>"+ obj[int].appraiseHuifu  +"\</td\>\</tr\>" +
								"\<tr\>\<td width='100%' align='right' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseHuifuTime  +"\</td\>\</tr\>" +
								"\</table\>\</tr\>\<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
							}else{
								str = str+"\</table\>\</tr\> \<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
							}
						}
					}
					str=str+"\</table\>"
					document.getElementById("apptable").innerHTML = str;
					$("#pageCount").html(obj[0].pageCount);
					$("#listCount").html(obj[0].listNumber);
					$("#page").val(obj[0].currentPage);
					if(obj[0].currentPage == 1){
						$("#last").hide();
					}
					if(obj[0].currentPage == obj[0].pageCount){
						$("#next").hide();
					}
				}
			});
			$("#default").hide();
			$("#appraise").show();
		}
			function lastApp() {
				var str = "";
				var page=$("#page").val()*1-1;
				$.ajax({
					url : "appraise!getAppraise",
					type : 'post',
					dataType : "json",
					data : {
						'productId' : $("#productId").val(),
						'page':page,
					},
					success : function(json) {
						var obj = eval(json);
						for ( var int = 0; int < obj.length; int++) {
							if(obj[int].appraiseContent == ""){
								
							}else{
								str = str+"\<tr\> \<table  width='100%'\>\<tr\> \<td width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员评价：\</a\>"+ obj[int].appraiseContent  +"\</td\>\</tr\>" +
								"\<tr \>\<td  align='right' width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员：\</a\>"+ obj[int].username  +"\</td\> \</tr\>" +
								"\<tr \>\<td  align='right'width='70%' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseTime  +"\</td\>\</tr\>"
								if(obj[int].appraiseHuifu != ""){
									str = str+ "\<tr \> \<td width='100%' align='left' style='word-break:break-all;'\>\<a style='color: red'\>商家回复：\</a\>"+ obj[int].appraiseHuifu  +"\</td\>\</tr\>" +
									"\<tr\>\<td width='100%' align='right' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseHuifuTime  +"\</td\>\</tr\>" +
									"\</table\>\</tr\>\<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
								}else{
									str = str+"\</table\>\</tr\> \<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
								}
							}
						}
						str=str+"\</table\>"
						document.getElementById("apptable").innerHTML = str;
						$("#pageCount").html(obj[0].pageCount);
						$("#listCount").html(obj[0].listNumber);
						$("#page").val(obj[0].currentPage);
						if(obj[0].currentPage == 1){
							$("#last").hide();
							$("#next").show();
						}
						if(obj[0].currentPage == obj[0].pageCount){
							$("#next").hide();
						}
					},
					error : function(json){
						var obj = eval(json);
						for ( var int = 0; int < obj.length; int++) {
							if(obj[int].appraiseContent == ""){
								
							}else{
								str = str+"\<tr\> \<table  width='100%'\>\<tr\> \<td width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员评价：\</a\>"+ obj[int].appraiseContent  +"\</td\>\</tr\>" +
								"\<tr \>\<td  align='right' width='100%' style='word-break:break-all;'\>\<a style='color: red'\>会员：\</a\>"+ obj[int].username  +"\</td\> \</tr\>" +
								"\<tr \>\<td  align='right'width='70%' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseTime  +"\</td\>\</tr\>"
								if(obj[int].appraiseHuifu != ""){
									str = str+ "\<tr \> \<td width='100%' align='left' style='word-break:break-all;'\>\<a style='color: red'\>商家回复：\</a\>"+ obj[int].appraiseHuifu  +"\</td\>\</tr\>" +
									"\<tr\>\<td width='100%' align='right' style='word-break:break-all;'\>\<a style='color: red'\>时间：\</a\>"+ obj[int].appraiseHuifuTime  +"\</td\>\</tr\>" +
									"\</table\>\</tr\>\<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
								}else{
									str = str+"\</table\>\</tr\> \<hr color='#ADADAD'  size='2' noshade='noshade' /\>"
								}
							}
						}
						str=str+"\</table\>"
						document.getElementById("apptable").innerHTML = str;
						$("#pageCount").html(obj[0].pageCount);
						$("#listCount").html(obj[0].listNumber);
						$("#page").val(obj[0].currentPage);
						alert(obj[0].currentPage);
						alert(obj[0].pageCount);
						if(obj[0].currentPage == 1){
							$("#last").hide();
							$("#next").show();
						}
						if(obj[0].currentPage == obj[0].pageCount){
							$("#next").hide();
						}
					}
				});
				$("#default").hide();
				$("#appraise").show();
			}