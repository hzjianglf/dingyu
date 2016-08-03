var myScroll,columnScroll,othercolumnsScroll,listRefresh;
var browseMode='';
localStorage["browseMode"]=browseMode;
	$(function(){			
		var currentColumn = sessionStorage["currentColumn"];	
		$("#singleScrollUl .active").removeClass('current');
		if(currentColumn != null){
			$("#column" + currentColumn).addClass('current');
		}
		if(true){		
			setColumnWidth();
			columnScroll=new iScroll("singleScroll",{
					zoom: false,
					hScrollbar: false,
					vScrollbar : false,
					bounce: false,
					snap:"li",
					momentum:false,
					vScroll:false,
					onScrollEnd:disableArrow
				});		
			if(currentColumn != null){
				var x = parseFloat(sessionStorage["columnx"]);
				columnScroll.scrollTo(x,0,100);				
			}
		}
		if(false){		
			setColumnHeight();
			columnScroll=new iScroll("singleScrollDiv",{
					zoom: false,
					hScrollbar: false,
					vScrollbar : false,
					bounce: false,					
					momentum:false,
				    onBeforeScrollStart: function(e) {
						var target = e.target;
						columnScroll.targetElement = target;
						columnScroll.clickFunc = $(target).attr("onclick");
						while (target.nodeType != 1) target = target.parentNode;
						if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA' && target.tagName != 'BUTTON'){
							if(!$.os.ios){
								e.preventDefault();
							}
						}
					},
					onScrollMove :function(event){
						event.preventDefault();
						event.stopPropagation();
						columnScroll.touchMoveFlag = true;
					},
					onTouchEnd : function(){
						if(columnScroll.touchMoveFlag){
							$(columnScroll.targetElement).removeAttr("onclick");
							setTimeout(function(){
								$(columnScroll.targetElement).attr("onclick",columnScroll.clickFunc);
								columnScroll.targetElement = null;
								columnScroll.clickFunc = null;
								columnScroll.startY = null;
								columnScroll.endY = null;
								columnScroll.touchMoveFlag = false;
							},50);
						}
					}			
				});	
				$("#singleScroll").on("touchmove",function(event){
					event.preventDefault();
					event.stopPropagation();
				});
				$(".more.active").click(function(){
					if(columnScroll){
						setTimeout(function(){
							columnScroll.refresh();
						},10);
					}
				});						
		}		
		var windowH = $(window).height(),
			headerH = $("#box_header").height() + ($.os.ios ? (-68) : 0),
			footerH = $("#box_footerBody").height();		
		$("#othercolumns").css("height",windowH-headerH-footerH +"px").bind("touchmove",function(event){
			event.preventDefault();
			event.stopPropagation();
		});
		$(document).tap(function(){
			$('#othercolumns').hide();
		});
		$("#othercolumns").tap(function(e){
			e.stopPropagation();			
		});
		$(".navBarR").tap(function(e){
			e.stopPropagation();
			showSelect();
		});
		$(window).on("resize",function(){
			setTimeout(function(){
				var windowH = $(window).height(),
					headerH = $("#box_header").height() + ($.os.ios ? (-68) : 0),
					footerH = $("#box_footerBody").height();		
				$("#othercolumns").css("height",windowH-headerH-footerH +"px");
				if(othercolumnsScroll) {
					othercolumnsScroll.refresh();
				}		
				if(columnScroll){
					setColumnHeight();
					columnScroll.refresh();
				}
				if(listRefresh){
					listRefresh();
				}
				//setHeight();
				if(myScroll){
					myScroll.refresh();
				}				
			},100);
		});
			var wholeWidth = parseFloat($("#singleScroll").width());
			for(var i = 0,l = $("#singleScroll li").length; i < l; i++){				
				wholeWidth -= parseFloat($("#singleScroll li").eq(i).width());				
				if( wholeWidth < 0 ){
					$("#dropDownButton").removeClass("disTap");
					break;
				}	
			}
			for(var j = l - 1; j >= i ; j--) {
				$("#singleScroll li").eq(j).prependTo($("#othercolumns ul"));
			}
	});
	function setColumnHeight(){
		var windowH = $(window).height(),
			headerH = $(".z3g-headerSmall").height() + ($.os.ios ? (-68) : 0),
			footerH = $("#box_footerBody").height();		
		$("#singleScrollDiv").css("max-height",windowH-headerH-footerH +"px");
	}
	function disableArrow(){
		var x=columnScroll.x;
		var w=ulwidth+x;
		var wholeWidth = parseFloat($("#singleScroll").width());
		if(w==wholeWidth){
			$('#columnnext').addClass('disTap');
			$('#columnnext').removeClass('active');			
		}else{
			$('#columnnext').removeClass('disTap');
			$('#columnnext').addClass('active');			
		}
		if(w==ulwidth){
			$('#columnprev').addClass('disTap');
			$('#columnprev').removeClass('active');			
		}else{
			$('#columnprev').removeClass('disTap');
			$('#columnprev').addClass('active');			
		}				
	}
	var othercolumnsScroll;
	function showSelect(){
		if($("#othercolumns").css("display")=="block"){
			$('#othercolumns').hide();			
		}else{
			$('#othercolumns').show();			
			if(!othercolumnsScroll){
				othercolumnsScroll = new iScroll("othercolumns",{
					  zoom: false,
					  hScroll: false,
					  hScrollbar: false,
					  vScrollbar : false,
					  bounce: false,
					  onBeforeScrollStart: function(e) {
						  var target = e.target;
						  othercolumnsScroll.targetElement = target;
						  othercolumnsScroll.clickFunc = $(target).attr("onclick");
						  while (target.nodeType != 1) target = target.parentNode;
						  if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA' && target.tagName != 'BUTTON'){
							  if(!$.os.ios){
								  e.preventDefault();
							  }
						  }
					  },
					  onScrollMove :function(event){
						  event.preventDefault();
						  event.stopPropagation();
						  othercolumnsScroll.touchMoveFlag = true;
					  },
					  onTouchEnd : function(){
						  if(othercolumnsScroll.touchMoveFlag){
							  $(othercolumnsScroll.targetElement).removeAttr("onclick");
							  setTimeout(function(){
								  $(othercolumnsScroll.targetElement).attr("onclick",othercolumnsScroll.clickFunc);
								  othercolumnsScroll.targetElement = null;
								  othercolumnsScroll.clickFunc = null;
								  othercolumnsScroll.startY = null;
								  othercolumnsScroll.endY = null;
								  othercolumnsScroll.touchMoveFlag = false;
							  },50);
						  }
					  }
				  });
			}
		}	
	}
	function spanOnClick(id,url){
		setCurrentColumn(id);
		if(/mobile/.test(navigator.userAgent)){
			if(context.loadUrl(url)){
			}else{
				window.location.href=url;
			}
		}else{
			window.location.href=url;
		}		
	}
	function setCurrentColumn(clickColumn){
		sessionStorage["currentColumn"]=clickColumn;
		if(true){		
			if(columnScroll){
				sessionStorage["columnx"]=columnScroll.x;
			}					
		}		
	}	
	var ulwidth=0;
	function getTotalWidth(id){		
		var w=$('#column'+id)[0].offsetWidth;
		ulwidth=ulwidth+w;		
	}	
	function goNext(){		
		columnScroll.scrollToPage('next', 0);return false;
	}
	function goPrev(){	
		columnScroll.scrollToPage('prev', 0);return false;
	}

var privateobj=new Array;
		var slideobj=new Array;
		var silderHeight=0;	
		window.onload=initclickAndstyle;
		function initclickAndstyle(){
			$('.titleBar').bind('click', function() {
				listRefresh();
  				$(".showMoreCont").css("left","0px");
			});
			$(".barWrap").bind('click',function(){
				$(".showMoreCont").css("left","100%");
			});
			var totle= $("#ULColorChange li span");
				for(var i=0;i<totle.length;i++){
					privateobj[i]= totle[i];
				}
			var slidetotle= $("#thelist li div div span");
				for(var i=0;i<slidetotle.length;i++){
					slideobj[i]= slidetotle[i];
				}	
				var smileface= $("#smileface")[0];
				if(smileface){
					window.setTimeout(function(){window.history.go(-1)}, 3000);
				}
		}
var thelistScroll;
	function listRefresh(){
		$(".showMoreCont").css("top",myScroll.y*(-1) + "px");
		var wHeight = $(window).height(),
			footerH=$('#box_footerBody').height(),
			titleH = $(".titleTabWrap").height();
		$("#hScrollList").css("height",(wHeight - footerH - titleH)+"px");
		if(thelistScroll){
			thelistScroll.refresh();
		}
	}
	$(function(){
		var wHeight = $(window).height(),
			footerH=$('#box_footerBody').height(),
			titleH = $(".titleTabWrap").height();
		$("#hScrollList").css("height",(wHeight - footerH - titleH)+"px");
		thelistScroll = new iScroll('hScrollList', {
			zoom: false,
			hScroll: false,
			hScrollbar: false,
			vScrollbar : false,
			bounce: false,
			onBeforeScrollStart: function(e) {
				var target = e.target;
				thelistScroll.targetElement = target;
				thelistScroll.clickFunc = $(target).attr("onclick");
				while (target.nodeType != 1) target = target.parentNode;
				if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA' && target.tagName != 'BUTTON'){
					if(!$.os.ios){
						e.preventDefault();
					}
				}
			},
			onScrollMove :function(event){
				event.preventDefault();
				event.stopPropagation();
				thelistScroll.touchMoveFlag = true;
			},
			onTouchEnd : function(){
				if(thelistScroll.touchMoveFlag){
					$(thelistScroll.targetElement).removeAttr("onclick");
					setTimeout(function(){
						$(thelistScroll.targetElement).attr("onclick",thelistScroll.clickFunc);
						thelistScroll.targetElement = null;
						thelistScroll.clickFunc = null;
						thelistScroll.startY = null;
						thelistScroll.endY = null;
						thelistScroll.touchMoveFlag = false;
					},50);
				}
			}
		});
		$(".showMoreCont").bind("touchmove",function(event){
			event.preventDefault();
			event.stopPropagation();	
		});
	});
$(function(){
            var screenWidth=$(window).width();
            if(screenWidth>480){
                    screenWidth=480;
                    $("#bannerScroll ul li a img").css("width",screenWidth+"px");
                    $(".bannerScrollWrap").css("width",screenWidth+"px");
            }else{
                    $("#bannerScroll ul li a img").css("width",screenWidth+"px");
                    $(".bannerScrollWrap").css("width",screenWidth+"px");
            }
            var jsonData={"contents":[{"linkUrl":"","smallPicPath":"","bigPicPath":""},{"linkUrl":"","smallPicPath":"","bigPicPath":""},{"linkUrl":"","smallPicPath":"","bigPicPath":""}],"setData":{"descriPtion":"320*300","changeStyle":3,"slideName":""}};
            if(jsonData.setData){
                    var imgStr = '',indexStr = '',heightStr = jsonData.setData.descriPtion.split("*")[1];
                    $("#bannerNav").addClass("height" + heightStr);
                    for(var i = 0, jl = jsonData.contents.length; i < jl; i++){
                                    imgStr += '<li><a href="' + (jsonData.contents[i].linkUrl || '#') + '">' +
                                    '<img style="max-width:320px;" alt="" src="' + (jsonData.contents[i].bigPicPath.indexOf('content')!=-1 ? " /manager":"") + jsonData.contents[i].bigPicPath + '" />'+
                                    '</a> </li>';
                                    indexStr += '<em class=' + (i==0?'active':'') + '>'+ (i+1) +'</em> '
                    }
                    $("#bannerScroll ul").css("width",320*(jsonData.contents.length+1)+"px");
                    if(jsonData.contents.length<=1){
                            $("#bannerNav,#prev,#next").hide();
                    } else {
                            $("#indicator").html(indexStr);
                            var sc2=new SwipeCarousel({wraper:'#bannerScroll',item:'li',itemWidth:screenWidth,loop:true,navs:'.sc_nav em',delay:((jsonData.setData.changeStyle*1000)||5000)});
                            $("#prev").bind("click",function(){
                                    sc2.prev();
                            });
                            $("#next").bind("click",function(){
                                    sc2.next();
                            });
                            $(".prev,.next").tap(function(){
                                    $("#prev,#next").show();
                                    clearTimeout(timeout);
                                    timeout = setTimeout(function(){
                                            $("#prev,#next").hide();
                                    },3000);
                            });
                            var timeout = setTimeout(function(){
                                    $("#prev,#next").hide();
                            },3000);
                            sc2.onChange(function(index,isTouch){
                                    if(!isTouch) return;
                                    $("#prev,#next").show();
                                    clearTimeout(timeout);
                                    timeout = setTimeout(function(){
                                            $("#prev,#next").hide();
                                    },3000);
                            });
                    }
            } else {
                    $("#bannerScroll,#bannerNav").hide();
            }
    });
function openToolbar(flag,el){
		if(flag=='d'){
			$(el).toggleClass("current").siblings().removeClass("current");
			$("#userbox").toggle();
		}
	}
 $(function(){
	$("span[name=openT]").tap(function(e){
		e.stopPropagation();
		if($(this).hasClass("userbox")){
			openToolbar('d',this);
		}
	});	
})

function curl(contentid){
     window.location.href=""+contentid+"";
}