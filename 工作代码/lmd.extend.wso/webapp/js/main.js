$(document).ready(function(){
	$("div.condiv div.allcont").attr("id", function(){return idNumber("Bo")+ $("div.condiv div.allcont").index(this)});
    $("ul.dhul li.bigmu").click(function(){
       var c = $("ul.dhul li.bigmu");
        var index = c.index(this);
       var p = idNumber("Bo");
       show2(c,index,p);
    });

    function show2(controlMenu,num,prefix){
        var content= prefix + num;
        $('#'+content).siblings().hide();
       $('#'+content).show();
	   controlMenu.eq(num).addClass("libg1").siblings().removeClass("libg1");
   };
	//---
	$("div.lftmmdl div.tree").attr("id", function(){return idNumber("No")+ $("div.lftmmdl div.tree").index(this)});
	$("div.clobtn div.lftBT").attr("id", function(){return idNumber("Nno")+ $("div.clobtn div.lftBT").index(this)});
    $("div.clobtn div.lftBT").click(function(){
       
        var index =$("div.clobtn div.lftBT").index(this);
		//alert(index);
       var p = idNumber("No");
	   var ppo = idNumber("Nno");
       show(index,p,ppo);
    });

    function show(num,prefix,pp){
        var content= prefix + num;
		var ppNn = pp + num;
		//var ididi='#'+content;
		 //alert(ididi);
        $('#'+content).siblings().hide();
       $('#'+content).show();
   };
   //---
//   $("div.rmdl div.rconall").attr("id", function(){return idNumber("Ao")+ $("div.rmdl div.rconall").index(this)});
//    $("div.rdiv li.topli").click(function(){
//       var c = $("div.rdiv li.topli");
//        var index = c.index(this);
//       var p = idNumber("Ao");
//       show1(c,index,p);
//    });

    function show1(controlMenu,num,prefix){
        var content= prefix + num;
        $('#'+content).siblings().hide();
       $('#'+content).show();
       controlMenu.eq(num).removeClass("topli2");
	   controlMenu.eq(num).addClass("topli1").siblings().removeClass("topli1");
	   controlMenu.eq(num).siblings().addClass("topli2");
   };
   //-----
   function idNumber(prefix){
        var idNum = prefix;
       return idNum;
    };

//---
$("div.lmnu").click(function(){
	var twomnu=$(this).siblings("div.twomnu");
	if(twomnu.is(":hidden"))
	{
		twomnu.slideDown(600);
		$(this).removeClass("lmnu1");
		$(this).addClass("lmnu2");
		$(this).parent().siblings().find("div.twomnu").hide();
		$(this).parent().siblings().find("div.lmnu").removeClass("lmnu2");
		$(this).parent().siblings().find("div.lmnu").addClass("lmnu1");
	}
	else{
		twomnu.slideUp(600);
		$(this).removeClass("lmnu2");
		$(this).addClass("lmnu1");
	}
	});
//-----
/*var wifm=$("#bodyw").width()-$("#lbutn").width();
	$("#rifm").width(wifm);
	var titw=$("#rifm").width()-$("#fgx").width();
	$("#titie").width(titw);*/
    var muntre=$(window).height()-180;
	$(".cdheight").height(muntre);
	$(".Rcont").height(muntre);
	$(".ifrwidow").height(muntre);
	var muntre1=$(window).height()-130;
	$("#pdf2").height(muntre1);
	var ywidth=$(window).width()-$(".zwid").width()-10;
	$(".rdiv").width(ywidth);	
	$(window).resize(function(){
		var ywidth2=$(window).width()-$(".zwid").width()-10;
	    $(".rdiv").width(ywidth2);
		/*var wifm=$("#bodyw").width()-$("#lbutn").width();
	    $("#rifm").width(wifm);
		var titw=$("#rifm").width()-$("#fgx").width();
	    $("#titie").width(titw);*/
	    var muntre=$(window).height()-180;
	    $(".cdheight").height(muntre);
		$(".Rcont").height(muntre);
		$(".ifrwidow").height(muntre);
			
		})
	
});

function addIframe(index){
	var url = $("#input"+index).val();
	document.getElementById("ywzy"+index).src=url;
	document.getElementById("ywzy"+index).contentWindow.location.load(true);

}


window.onload=function(){
//	$("div.condiv div.allcont").attr("id", function(){return idNumber("Bo")+ $("div.condiv div.allcont").index(this)});
	var controlMenu = $("ul.dhul li.bigmu");
    var num = controlMenu.index($("ul.dhul li.bigmu"));
    var prefix = "Bo";
    var content= prefix + num;
    $('#'+content).siblings().hide();
};


