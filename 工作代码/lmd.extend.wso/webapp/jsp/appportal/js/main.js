$(document).ready(function(){

	function idNumber(prefix){
			var idNum = prefix;
		   return idNum;
	};
//---------------------------------------------------------------------------------------------------------------------
	$("td.acdiv ul.acul").attr("id", function(){return idNumber("ac")+ $("td.acdiv ul.acul").index(this)});
	$("div.super1 li.acli").mouseenter(function(){
		   var c = $("div.super1 li.acli");
		   var index = c.index(this);
		   var p = idNumber("ac");
		   AC1(c,index,p);
		});
	
	function AC1(controlMenu,num,prefix){
		   var content= prefix + num;
		   $('#'+content).siblings().hide();
		   $('#'+content).show();
		  // controlMenu.eq(num).removeClass("colrhs");
		   controlMenu.eq(num).addClass("actionliA").siblings().removeClass("actionliA");
		  // controlMenu.eq(num).siblings().addClass("colrhs");
   };	
//---------------------------------------------------------------------------------------------------------------------	
	$("div.actball table.actba").attr("id", function(){return idNumber("sup")+ $("div.actball table.actba").index(this)});
	$("div.actwdiv li.actli").mouseenter(function(){
		   var c = $("div.actwdiv li.actli");
		   var index = c.index(this);
		   var p = idNumber("sup");
		   SU(c,index,p);
		});
	
	function SU(controlMenu,num,prefix){
		   var content= prefix + num;
		   $('#'+content).siblings().hide();
		   $('#'+content).show();
		  // controlMenu.eq(num).removeClass("colrhs");
		   controlMenu.eq(num).addClass("atliA").siblings().removeClass("atliA");
		  // controlMenu.eq(num).siblings().addClass("colrhs");
   };
//---------------------------------------------------------------------------------------------------------------------
	$("div.bsondall div.bsondiv").attr("id", function(){return idNumber("bsfat")+ $("div.bsondall div.bsondiv").index(this)});
	$("div.bustwdiv li.butli").mouseenter(function(){
		   var c = $("div.bustwdiv li.butli");
		   var index = c.index(this);
		   var p = idNumber("bsfat");
		   BUF(c,index,p);
		});
	
	function BUF(controlMenu,num,prefix){
		   var content= prefix + num;
		   $('#'+content).siblings().hide();
		   $('#'+content).show();
		  // controlMenu.eq(num).removeClass("colrhs");
		   controlMenu.eq(num).addClass("atliA").siblings().removeClass("atliA");
		  // controlMenu.eq(num).siblings().addClass("colrhs");
   };	

	$("div.bssdiv table.bssul").attr("id", function(){return idNumber("bus")+ $("div.bssdiv table.bssul").index(this)});
	$("div.bstfwdiv li.bstli").mouseenter(function(){
		   var c = $("div.bstfwdiv li.bstli");
		   var index = c.index(this);
		   var p = idNumber("bus");
		   BUS(c,index,p);
		});
	
	function BUS(controlMenu,num,prefix){
		   var content= prefix + num;
		   $('#'+content).siblings().hide();
		   $('#'+content).show();
		  // controlMenu.eq(num).removeClass("colrhs");
		   controlMenu.eq(num).addClass("bshdcol").siblings().removeClass("bshdcol");
		  // controlMenu.eq(num).siblings().addClass("colrhs");
   };	
//---------------------------------------------------------------------------------------------------------------------
	$("div.afftdiv table.afftul").attr("id", function(){return idNumber("afft")+ $("div.afftdiv table.afftul").index(this)});
	$("div.afftit li.affli").mouseenter(function(){
		   var c = $("div.afftit li.affli");
		   var index = c.index(this);
		   var p = idNumber("afft");
		   AFF(c,index,p);
		});
	
	function AFF(controlMenu,num,prefix){
		   var content= prefix + num;
		   $('#'+content).siblings().hide();
		   $('#'+content).show();
		  // controlMenu.eq(num).removeClass("colrhs");
		   controlMenu.eq(num).addClass("affcol").siblings().removeClass("affcol");
		  // controlMenu.eq(num).siblings().addClass("colrhs");
   };	
   //---------------------------------------------------------------------------------------------------------------------
	$("div.socodiv ul.socoul").attr("id", function(){return idNumber("soci")+ $("div.socodiv ul.socoul").index(this)});
	$("table.socitba td.sobtn").mouseenter(function(){
		   var c = $("table.socitba td.sobtn");
		   var index = c.index(this);
		   var p = idNumber("soci");
		   SOC(c,index,p);
		});
	
	function SOC(controlMenu,num,prefix){
		   var content= prefix + num;
		   $('#'+content).siblings().hide();
		   $('#'+content).show();
		   controlMenu.eq(num).removeClass("sotd2");
		   controlMenu.eq(num).addClass("sotd1").siblings().removeClass("sotd1");
		   controlMenu.eq(num).siblings().addClass("sotd2");
   };	



})